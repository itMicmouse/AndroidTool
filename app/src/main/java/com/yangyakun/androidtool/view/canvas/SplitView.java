package com.yangyakun.androidtool.view.canvas;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yangyakun.androidtool.R;

import java.util.ArrayList;
import java.util.List;

public class SplitView extends View {

    private Paint paint;
    private Bitmap bitmap;
    private float d = 3;//粒子直径
    private List<Ball> list = new ArrayList<>();
    private ValueAnimator valueAnimator;

    public SplitView(Context context) {
        this(context,null);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic1);
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = bitmap.getPixel(i,j);
                ball.x = i*d+d/2;
                ball.y = j*d+d/2;
                ball.r = d/2;

                //速度(-20,20)  Math.pow(x,y)  x的y次方 Math.ceil 向上取证 Math.Random()函数能够返回带正号的double 取值范围是[0.0,1.0)
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);
                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;

                list.add(ball);
            }
        }
        valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                upDateBall();
                invalidate();
            }
        });
    }

    private void upDateBall() {
        //更新粒子的位置
        for (Ball ball : list) {
            ball.x += ball.vX;
            ball.y += ball.vY;

            ball.vX += ball.aX;
            ball.vY += ball.aY;
        }
    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,paint);
        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight(),paint);

        canvas.translate((getWidth()-3f*bitmap.getWidth())/2,(getHeight()-3f*bitmap.getHeight())/2);

        for (Ball ball : list) {
            paint.setColor(ball.color);
            canvas.drawCircle(ball.x,ball.y,ball.r,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //执行动画
            valueAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
