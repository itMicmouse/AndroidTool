package com.yangyakun.androidtool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.Utils;

import java.util.logging.SocketHandler;

public class TextView extends View {

    private Paint mPaint;
    private Shader mShader;
    private Bitmap mBitmap;

    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_directory_grey600_36dp);
        mPaint.setAntiAlias(true);//设置抗锯齿效果
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mShader = new LinearGradient(0,0,500,500,
//                new int[]{Color.RED,Color.BLUE,Color.GREEN},new float[]{0.5f,0.7f,1}, Shader.TileMode.CLAMP);
//        mPaint.setShader(mShader);
//        canvas.drawRect(0,0,500,500,mPaint);

//        mShader = new RadialGradient(250,250,250,
//                new int[]{Color.GREEN,Color.YELLOW,Color.RED},null,Shader.TileMode.CLAMP);
//        mPaint.setShader(mShader);
//        canvas.drawCircle(250,250,250,mPaint);

//        mShader = new SweepGradient(250,250,Color.RED,Color.GREEN);
//        mPaint.setShader(mShader);
//        canvas.drawCircle(250,250,250,mPaint);

//        mShader = new BitmapShader(mBitmap,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        mPaint.setShader(mShader);
//        canvas.drawRect(0,0,mBitmap.getWidth(),mBitmap.getHeight(),mPaint);

        LinearGradient mShader1 = new LinearGradient(0,0,500,500,
                new int[]{Color.RED,Color.BLUE,Color.GREEN},new float[]{0.5f,0.7f,1}, Shader.TileMode.CLAMP);
        BitmapShader mShader2 = new BitmapShader(mBitmap,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mShader = new ComposeShader(mShader1,mShader2, PorterDuff.Mode.MULTIPLY);
        canvas.drawCircle(250,250,250,mPaint);
    }
}
