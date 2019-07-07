package com.yangyakun.androidtool.layoutmanager;

import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 界面无线循环的LayoutManager
 */
public class PathLayoutManager extends RecyclerView.LayoutManager {

    private int mOrientation;
    private int mItemOffset;
    private Keyframes mKeyframes;

    /**
     * 屏幕中最多能同时显示的Item个数
     */
    private int mItemCountInScreen;

    /**
     * 第一个可见的Item索引
     */
    private int mFirstVisibleItemPos;

    /**
     * x轴偏移量和y轴偏移量
     */
    private float mOffsetX, mOffsetY;

    public PathLayoutManager(Path path,int itemOffset) {
        this(path,RecyclerView.VERTICAL,itemOffset);
    }

    public PathLayoutManager(Path path, int mOrientation, int mItemOffset) {
        this.mOrientation = mOrientation;
        this.mItemOffset = mItemOffset;
        upDatePath(path);
    }

    private void upDatePath(Path path) {
        if(path!=null){
            mKeyframes = new Keyframes(path);
            if(mItemOffset == 0){
                throw new IllegalStateException("itemOffset must be > 0 !!!");
            }
            //计算出这个Path最多能同时出现几个Item
            mItemCountInScreen = mKeyframes.getPathLength() / mItemOffset + 1;
        }
        requestLayout();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0) {
            //没有Item可布局，就回收全部临时缓存 (参考自带的LinearLayoutManager)
            //这里的没有Item，是指Adapter里面的数据集，
            //可能临时被清空了，但不确定何时还会继续添加回来
            removeAndRecycleAllViews(recycler);
            return;
        }
        //暂时分离和回收全部有效的Item
        detachAndScrapAttachedViews(recycler);

        List<PosTan> needLayoutItems = new ArrayList<>();
        //获取需要布局的items
        initNeedLayoutItems(needLayoutItems, state.getItemCount());
        //检查一下
        if (needLayoutItems.isEmpty() || mKeyframes == null) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        //开始布局
        onLayout(recycler, needLayoutItems);
    }

    /**
     * 确定Item位置，角度以及尺寸
     *
     * @param needLayoutItems 需要布局的Item
     */
    private void onLayout(RecyclerView.Recycler recycler, List<PosTan> needLayoutItems) {
        int x, y;
        View item;
        for (PosTan tmp : needLayoutItems) {
            //根据position获取View
            item = recycler.getViewForPosition(tmp.index);
            //添加进去，当然里面不一定每次都是调用RecyclerView的addView方法的，
            //如果是从缓存区里面找到的，只需调用attachView方法把它重新连接上就行了。
            addView(item);
            //测量item，当然，也不是每次都会调用measure方法进行测量的，
            //它里面会判断，如果已经测量过，而且当前尺寸又没有收到更新的通知，就不会重新测量。
            measureChild(item, 0, 0);

            //Path线条在View的中间
            x = (int) tmp.x - getDecoratedMeasuredWidth(item) / 2;
            y = (int) tmp.y - getDecoratedMeasuredHeight(item) / 2;

            //进行布局
            layoutDecorated(item, x, y, x + getDecoratedMeasuredWidth(item), y + getDecoratedMeasuredHeight(item));
            //旋转item
            item.setRotation(tmp.getChildAngle());
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        //设置了滑动方向是水平，才能接受水平滚动事件
        return mOrientation == RecyclerView.HORIZONTAL;
    }

    @Override
    public boolean canScrollVertically() {
        //设置了滑动方向是垂直，才能接受垂直滚动事件
        return mOrientation == RecyclerView.VERTICAL;
    }

    /**
     * 初始化需要布局的Item数据
     *
     * @param result    结果
     * @param itemCount Item总数
     */
    private void initNeedLayoutItems(List<PosTan> result, int itemCount) {
        float currentDistance;
        //必须从第一个item开始，因为要拿到最小的，也就是最先的
        for (int i = 0; i < itemCount; i++) {
            currentDistance = i * mItemOffset - getScrollOffset();
            //判断当前距离 >= 0的即表示可见
            if (currentDistance >= 0) {
                //得到第一个可见的position
                mFirstVisibleItemPos = i;
                break;
            }
        }

        //结束的position
        int endIndex = mFirstVisibleItemPos + mItemCountInScreen;
        //防止溢出
        if (endIndex > getItemCount()) {
            endIndex = getItemCount();
        }
        float fraction;
        PosTan posTan;
        for (int i = mFirstVisibleItemPos; i < endIndex; i++) {
            //得到当前距离
            currentDistance = i * mItemOffset - getScrollOffset();
            //得到百分比
            fraction = currentDistance / mKeyframes.getPathLength();
            //根据百分比从Keyframes中取出对应的坐标和角度
            posTan = mKeyframes.getValue(fraction);
            if (posTan == null) {
                continue;
            }
            //添加进list中
            result.add(new PosTan(posTan, i, fraction));
        }
    }
    /**
     * 根据当前设置的滚动方向来获取对应的滚动偏移量
     */
    private float getScrollOffset() {
        return mOrientation == RecyclerView.VERTICAL ? mOffsetY : mOffsetX;
    }

    /**
     * 回收屏幕外需回收的Item
     */
    private void recycleChildren(RecyclerView.Recycler recycler) {
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        for (int i = 0; i < scrapList.size(); i++) {
            RecyclerView.ViewHolder holder = scrapList.get(i);
            removeView(holder.itemView);
            recycler.recycleView(holder.itemView);
        }
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //检查Keyframes是否已初始化，即检测是否设置了Path
        checkKeyframes();
        //分离和临时回收
        detachAndScrapAttachedViews(recycler);
        //临时记录上一次的offset
        float lastOffset = mOffsetX;
        //更新偏移量
        updateOffsetX(dx);
        //布局item
        relayoutChildren(recycler, state);
        //如果offset没有改变，那么就直接return 0，表示不消费本次滑动
        return lastOffset == mOffsetX ? 0 : dx;
    }

    private void updateOffsetX(int offsetX) {
        //更新offset
        mOffsetX += offsetX;
        //路径总长度
        int pathLength = mKeyframes.getPathLength();
        //item总长度
        int itemLength = getItemLength();
        //item总长度相对于路径总长度多出来的部分
        int overflowLength = itemLength - pathLength;
        if (mOffsetX < 0) {
            //避免第一个item脱离顶部向下滚动
            mOffsetX = 0;
        } else if (mOffsetX > overflowLength) {//滑动到底部，并且最后一个item即将脱离底部时
            //如果列表能滚动的话，则直接设置为可滑动的最大距离，避免最后一个item向上移
            if (itemLength > pathLength) {
                mOffsetX = overflowLength;
            } else {
                //如果列表内容很少，不用滚动就能显示完的话，就不更新offset
                //那为什么这里是减呢？因为最上面执行了一句+=，所以现在这样做是抵消第一句的操作。
                mOffsetX -= offsetX;
            }
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //检查Keyframes是否已初始化，即检测是否设置了Path
        checkKeyframes();
        //分离和临时回收
        detachAndScrapAttachedViews(recycler);
        //临时记录上一次的offset
        float lastOffset = mOffsetY;
        //更新偏移量
        updateOffsetY(dy);
        //布局item
        relayoutChildren(recycler, state);
        //如果offset没有改变，那么就直接return 0，表示不消费本次滑动
        return lastOffset == mOffsetY ? 0 : dy;
    }

    private void checkKeyframes() {
        if (mKeyframes == null) {
            throw new NullPointerException("Path not set!");
        }
    }

    /**
     * 更新Y轴偏移量
     *
     * @param offsetY 偏移量
     */
    private void updateOffsetY(float offsetY) {
        //更新offset
        mOffsetY += offsetY;
        //路径总长度
        int pathLength = mKeyframes.getPathLength();
        //item总长度
        int itemLength = getItemLength();
        //item总长度相对于路径总长度多出来的部分
        int overflowLength = itemLength - pathLength;
        if (mOffsetY < 0) {
            //避免第一个item脱离顶部向下滚动
            mOffsetY = 0;
        } else if (mOffsetY > overflowLength) {//滑动到底部，并且最后一个item即将脱离底部时
            //如果列表能滚动的话，则直接设置为可滑动的最大距离，避免最后一个item向上移
            if (itemLength > pathLength) {
                mOffsetY = overflowLength;
            } else {
                //如果列表内容很少，不用滚动就能显示完的话，就不更新offset
                //那为什么这里是减呢？因为最上面执行了一句+=，所以现在这样做是抵消第一句的操作。
                mOffsetY -= offsetY;
            }
        }
    }

    /**
     * @return Item总长度
     */
    private int getItemLength() {
        //这里 +1 是为了让最后一个item 显示出来 (让最后一个item的距离相对于Path长度的百分比<1，
        // 即使其满足mKeyframes.getValue()方法里面获取有效坐标点的条件)
        return getItemCount() * mItemOffset - mItemOffset + 1;
    }

    private void relayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        List<PosTan> needLayoutItems = new ArrayList<>();
        //获取需要布局的items
        initNeedLayoutItems(needLayoutItems, state.getItemCount());
        //判断一下状态
        if (needLayoutItems.isEmpty() || mKeyframes == null) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        //开始布局
        onLayout(recycler, needLayoutItems);
        recycleChildren(recycler);
    }

}
