package com.yangyakun.androidtool.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.yangyakun.androidtool.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends ViewGroup {

    private Adapter adapter;
    /**
     * 当前显示的View
     */
    private List<View> viewList;

    /**
     * 当前滑动的Y值
     */
    private int currentY;

    /**
     * 行数
     */
    private int rowCount;

    /**
     * view 的第一行 是占内存的几行
     */
    private int firstRow;
    /**
     * y 偏移量
     */
    private int scrollY;

    /**
     * 限制调用layout的次数
     */
    private boolean needRelayout;


    private int width;

    private int height;

    /**
     * 每个item高度数组
     */
    private int[] heights;//items 高度

    /**
     * 缓存值
     */
    Recycle recycle;

    /**
     * 最小滑动距离
     */
    private int touchSlop;


    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            recycle = new Recycle(adapter.getVIewTypeCount());
            scrollY = 0;
            firstRow = 0;
            needRelayout = true;
            requestLayout();
        }
    }


    public RecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.viewList = new ArrayList<>();
        this.needRelayout = true;
    }

    /**
     * 初始化
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needRelayout || changed) {
            needRelayout = false;

            viewList.clear();
            removeAllViews();

            if (adapter != null) {
                width = r - l;
                height = b - t;
                int left = 0, top = 0, bottom, right;
                for (int i = 0; i < rowCount && top < height; i++) {
                    right = width;
                    bottom = top + heights[i];
                    makeAndStp(i, 0, top, width, bottom);

                    top = bottom;
                }
            }
        }
    }

    private View makeAndStp(int row, int left, int top, int right, int bottom) {
        View view = obtainView(row, right - left, bottom - top);
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtainView(int row, int width, int heitht) {
        //key type
        //获取类型key
        int itemViewType = adapter.getItemViewType(row);
        //从缓存中获取View
        View reclyView = recycle.get(itemViewType);
        View view;
        if (reclyView == null) {
            view = adapter.onCreateViewHodler(row, reclyView, this);
            if (view == null) {
                throw new RuntimeException("onCreateViewHodler 必须实现");
            }
        } else {
            view = adapter.onBinderViewHodler(row, reclyView, this);
        }

        view.setTag(R.id.tag_type_view, itemViewType);

        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(heitht, MeasureSpec.EXACTLY));

        addView(view, 0);

        return view;
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int h = 0;
        if (adapter != null) {
            this.rowCount = adapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }
        }
        int tempH = sumArray(heights, 0, heights.length);
        h = Math.min(heightSize, tempH);
        setMeasuredDimension(widthSize, h);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int sumArray(int array[], int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += array[i];
        }
        return sum;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                currentY = (int) event.getRawY();
                System.out.println("MotionEvent.ACTION_DOWN");
                break;
            }
            case MotionEvent.ACTION_MOVE:
                int y2 = (int) Math.abs(currentY - event.getRawY());
                if (y2 > touchSlop) {
                    intercept = true;
                }
                System.out.println("MotionEvent.ACTION_MOVE");
        }

        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_HOVER_MOVE: {
                int y2 = (int) event.getRawY();
                int diffY = currentY - y2;
                scrollBy(0, diffY);
            }

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void scrollBy(int x, int y) {
        //scrolly表示 第一个可见Item 的左上顶点 距离屏幕的左上顶点的距离
        scrollY += y;
        scrollY = scrollBounds(scrollY);
        if (scrollY > 0) {
            //上滑  边界值
            while (scrollY > heights[firstRow]) {
                //1 上滑移除 2、上滑加载 3、下滑移除  4下滑加载
                removeView(viewList.remove(firstRow));
                scrollY -= heights[firstRow];
                firstRow++;
            }
            while (getFillHeight() < height) {
                int addLast = firstRow + viewList.size();
                View view = obtainView(addLast, width, heights[addLast]);
                viewList.add(viewList.size(), view);
            }
        } else if (scrollY < 0) {
            //下滑
            //4下滑加载
            while (scrollY < 0) {
                int firstAddRow = firstRow - 1;
                View view = obtainView(firstAddRow, width, heights[firstAddRow]);
                viewList.add(0, view);
                firstRow--;
                scrollY += heights[firstAddRow + 1];
            }
            while (sumArray(heights, firstRow, viewList.size()) - scrollY - heights[firstRow + viewList.size()] >= height) {
                removeView(viewList.remove(viewList.size() - 1));
            }
        } else {

        }
        repositionViews();

    }

    private int scrollBounds(int scrollY) {
        if (scrollY > 0) {
            scrollY = Math.min(scrollY, sumArray(heights, firstRow, heights.length - firstRow) - height);
        } else {
            //极限值 取0 其他区scrolly
            scrollY = Math.max(scrollY, -sumArray(heights, 0, firstRow));
        }
        return scrollY;
    }

    private void repositionViews() {
        int left, top, right, bottom, i;
        top = -scrollY;
        i = firstRow;
        for (View view : viewList) {
            bottom = top + heights[i++];
            view.layout(0, top, width, bottom);
            top = bottom;
        }
    }

    private int getFillHeight() {
        //数据高度-scrolly
        return sumArray(heights, firstRow, viewList.size()) - scrollY;
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        int key = (int) view.getTag(R.id.tag_type_view);
        recycle.put(view, key);
    }

    public interface Adapter {
        View onCreateViewHodler(int postion, View converView, ViewGroup parent);

        View onBinderViewHodler(int postion, View converView, ViewGroup parent);

        int getItemViewType(int row);

        int getVIewTypeCount();

        int getCount();

        int getHeight(int index);
    }
}
