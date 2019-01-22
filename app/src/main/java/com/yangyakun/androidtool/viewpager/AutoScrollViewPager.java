package com.yangyakun.androidtool.viewpager;

/**
 * Created by GuiYanBing on 2018/3/24 17:35
 * E-Mail Address：guiyanbing@zhiyihealth.com.cn
 */

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AutoScrollViewPager extends ViewPager {

    private long currentTime;

    private boolean isRunning = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (isRunning && msg.what == 0) {
                //让viewPager 滑动到下一页
                AutoScrollViewPager.this.setCurrentItem(getCurrentItem() + 1, true);
                handler.sendEmptyMessageDelayed(0, currentTime);
            }
        };
    };

    public void setTime(long time){
        handler.removeMessages(0);
        currentTime=time;
        handler.sendEmptyMessageDelayed(0,currentTime);
    }

    public void startAutoScroll() {
        isRunning = true;
        handler.sendEmptyMessageDelayed(0, currentTime);
    }

    public void stopAutoScroll() {
        isRunning = false;
        handler.removeMessages(0);
    }

    private PagerAdapter wrappedPagerAdapter;
    private PagerAdapter wrapperPagerAdapter;

    private InnerOnPageChangeListener listener;

    public AutoScrollViewPager(Context context) {
        super(context);
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        listener = new InnerOnPageChangeListener();
        super.setOnPageChangeListener(listener);
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.listener.setOnPageChangeListener(listener);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        wrappedPagerAdapter = adapter;
        wrapperPagerAdapter = (wrappedPagerAdapter == null) ? null : new AutoScrollPagerAdapter(adapter);
        //为viewPager设置的是包装的adapter
        super.setAdapter(wrapperPagerAdapter);
        if (adapter != null && adapter.getCount() != 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    setCurrentItem(0, false);
                }
            });
        }
    }

    @Override
    public PagerAdapter getAdapter() {
        return wrappedPagerAdapter;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item + 1);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item + 1, smoothScroll);
    }

    @Override
    public int getCurrentItem() {
        int curr = super.getCurrentItem();
        return getRealPosition(curr);
    }

    /**
     * 根据虚拟获取真实位置
     * @param curr 位置
     * @return
     */
    private int getRealPosition(int curr) {
        if (wrappedPagerAdapter != null && wrappedPagerAdapter.getCount() > 1) {
            if (curr == 0) {
                curr = wrappedPagerAdapter.getCount() - 1;
            } else if (curr == wrapperPagerAdapter.getCount() - 1) {
                curr = 0;
            } else {
                curr = curr - 1;
            }
        }
        return curr;
    }

    private int getCurrentItemOfWrapper() {
        return super.getCurrentItem();
    }

    private int getCountOfWrapper() {
        if (wrapperPagerAdapter != null) {
            return wrapperPagerAdapter.getCount();
        }
        return 0;
    }

    private int getCount() {
        if (wrappedPagerAdapter != null) {
            return wrappedPagerAdapter.getCount();
        }
        return 0;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                stopAutoScroll();
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                startAutoScroll();
                break;
                default:
        }
        return super.onTouchEvent(ev);
    }

    private class InnerOnPageChangeListener implements OnPageChangeListener {

        private OnPageChangeListener listener;

        public void setOnPageChangeListener(OnPageChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (listener != null && position > 0 && position < getCount()) {

                listener.onPageScrolled(position - 1, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            final int pos = getRealPosition(position);
            if (listener != null) {
                AutoScrollViewPager.this.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onPageSelected(pos);
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == SCROLL_STATE_IDLE && getCount() > 1) {
                if (getCurrentItemOfWrapper() == 0) {
                    setCurrentItem(getCount() - 1, false);

                } else if (getCurrentItemOfWrapper() == getCountOfWrapper() - 1) {
                    setCurrentItem(0, false);
                }
            }
            if (listener != null) {
                listener.onPageScrollStateChanged(state);
            }
        }
    }

}
