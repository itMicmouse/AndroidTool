package com.yangyakun.androidtool.utils;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeText extends TextView implements Runnable {
    //当前滚动的位置
    private int currentScrollX;
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;

    public MarqueeText(Context context) {
        super(context);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
// TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        currentScrollX = this.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isMeasure) {
            getTextWidth();// 文字宽度只需要获取一次就可以了
            isMeasure = true;
        }
    }

    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
    }

    @Override
    public void run() {
        // 滚动速度.+号表示往左边-
        currentScrollX += 0;
        scrollTo(currentScrollX, 0);
        if (isStop) {
            return;
        }
        if (getScrollX() >= (textWidth)) {
            // 当前出现的位置
            currentScrollX = -(this.getWidth());
        }
        postDelayed(this, 1);
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        isStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        isStop = true;
    }

    /**
     * 从头开始滚动
     */
    public void startFromHead() {
        currentScrollX = 0;
        startScroll();
    }

}