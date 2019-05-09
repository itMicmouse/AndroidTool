package com.yangyakun.androidtool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yangyakun.androidtool.R;

public class ColorFilterView extends View {

    private Paint paint;
    private Bitmap bitmap;

    private ColorMatrixColorFilter mColorMatrixColorFilter;

    public ColorFilterView(Context context) {
        super(context);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * R' = R * mul.R / 0xff + add.R
         * G' = G * mul.G / 0xff + add.G
         * B' = B * mul.B / 0xff + add.B
         */
        //去掉红色
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff,0x000000);
//        paint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,paint);
        // 原始图片
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff,0x000000);
//        paint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,paint);
        //绿色更亮
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff,0x003000);
//        paint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,paint);

//        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
//        paint.setColorFilter(porterDuffColorFilter);
//        canvas.drawBitmap(bitmap, 100, 0, paint);


        float[] colorMatrix = {
                2,0,0,0,0,   //red
                0,1,0,0,0,   //green
                0,0,1,0,0,   //blue
                0,0,0,1,0    //alpha
        };

        ColorMatrix cm = new ColorMatrix();
//        //亮度调节
//        cm.setScale(1,2,1,1);

//        //饱和度调节0-无色彩， 1- 默认效果， >1饱和度加强
//        cm.setSaturation(2);

        //色调调节
//        cm.setRotate(0, 45);

        mColorMatrixColorFilter = new ColorMatrixColorFilter(colormatrix_fanse);
        paint.setColorFilter(mColorMatrixColorFilter);
        canvas.drawBitmap(bitmap, 100, 0, paint);
    }

    // 胶片
    public static final float colormatrix_fanse[] = {
            -1.0f, 0.0f, 0.0f, 0.0f, 255.0f,
            0.0f, -1.0f, 0.0f, 0.0f, 255.0f,
            0.0f, 0.0f, -1.0f, 0.0f, 255.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
}
