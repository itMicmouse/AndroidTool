package com.yangyakun.androidtool.view.canvas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.yangyakun.androidtool.R;

public class SplashVIew extends View {

    private Paint mPaint;
    private Paint mHolePaint;
    private ValueAnimator valueAnimator;
    /**
     * 背景色
     */
    private int mBackGroundColor = Color.WHITE;
    private int[] mCircleCOlors;

    private float mCenterX;
    private float mCenterY;
    //表示斜对角先长度的一半，扩散圆最大半径
    private float mDistance;

    //小球半径
    private float mCircleRadius = 18;


    //旋转大圆的半径
    private float mRotateRadius = 90;
    //当前大圆的旋转角度
    private float mCurrentRotateAngle = 0;
    //当前大圆的半径
    private float mCurrentRotateRadius = mRotateRadius;

    private int mRotateDuration = 1200;


    public SplashVIew(Context context) {
        this(context,null);
    }

    public SplashVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplashVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackGroundColor);
        mCircleCOlors = context.getResources().getIntArray(R.array.splash_circle_colors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w*1f/2;
        mCenterY = h*1f/2;
        //三角形的算法
        mDistance = (float) (Math.hypot(w,h)/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mState ==null){
            mState = new RotateState();
        }
        mState.drawState(canvas);
    }
    private SplashState mState;


    private abstract class SplashState{
        abstract void drawState(Canvas canvas);
    }

    /**
     * 旋转动画
     */
    private class RotateState extends SplashState{

        public RotateState() {
            valueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI*2));
//            valueAnimator.setRepeatCount(2);
            valueAnimator.setDuration(mRotateDuration);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateAngle = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new MerginState();
                }
            });
            valueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackGround(canvas);
            //绘制6个小球
            drawCircles(canvas);
        }
    }

    /**
     * 画小球
     * @param canvas 画布
     */
    private void drawCircles(Canvas canvas) {
        double rotateAngle = Math.PI * 2 / mCircleCOlors.length;
        for (int i = 0; i < mCircleCOlors.length; i++) {
            double angle = i * rotateAngle + mCurrentRotateAngle;
            float cx = (float) (mCenterX+Math.cos(angle)*mCurrentRotateRadius);
            float cy = (float) (mCenterX+Math.sin(angle)*mCurrentRotateRadius);
            mPaint.setColor(mCircleCOlors[i]);
            canvas.drawCircle(cx,cy,mCircleRadius,mPaint);
        }
    }

    /**
     * 画背景
     * @param canvas 画布
     */
    private void drawBackGround(Canvas canvas) {
        canvas.drawColor(mBackGroundColor);
    }

    private class MerginState extends SplashState{
        public MerginState() {
            valueAnimator = ValueAnimator.ofFloat(mCircleRadius,mRotateRadius);
            valueAnimator.setDuration(mRotateDuration);
            valueAnimator.setInterpolator(new OvershootInterpolator(10f));
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });
            valueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackGround(canvas);
            drawCircles(canvas);
        }
    }
}
