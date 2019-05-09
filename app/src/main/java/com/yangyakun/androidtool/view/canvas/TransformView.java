package com.yangyakun.androidtool.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TransformView extends View {

    private Paint paint;

    public TransformView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //1、平移操作
//        canvas.drawRect(0,0,400,400,paint);
//        canvas.translate(50,50);
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(0,0,400,400,paint);
//        canvas.drawLine(0,0,600,600,paint);

        //1、平移操作
//        canvas.drawRect(200,200,700,700,paint);
//        canvas.scale(0.5f,0.5f);
        //先translate（px,py） 然后scale（sx,xy） 再反向translate
//        canvas.scale(0.5f,0.5f,200,200);

//        canvas.translate(200,200);
//        canvas.scale(0.5f,0.5f);
//        canvas.translate(-200,-200);
//
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(200,200,700,700,paint);

        //旋转操作
//        canvas.translate(200,200);
//        canvas.drawRect(0,0,700,700,paint);
//        canvas.rotate(45);
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(0,0,700,700,paint);

//        canvas.drawRect(400,400,900,900,paint);
//        canvas.rotate(45,650,650);
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(400,400,900,900,paint);

        //倾斜值
        canvas.drawRect(0,0,400,400,paint);
//        canvas.skew(1,0);//在x轴上倾斜45度
        canvas.skew(0,1);//在y轴上倾斜45度
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,0,400,400,paint);

    }
}
