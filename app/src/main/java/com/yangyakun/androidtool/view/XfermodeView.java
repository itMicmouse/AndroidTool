package com.yangyakun.androidtool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yangyakun.androidtool.db.domain.Patient;

import java.security.MessageDigest;

public class XfermodeView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1、ComposeShaer
        //2、画笔Patient.setXfermode()
        //3、PorterDuffcolorFilter

        // 禁止硬件加速 图像混合某些事不支持硬件加速的
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        setBackgroundColor(Color.GRAY);

        //离屏绘制
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        //绘制目标图
        canvas.drawBitmap(createRectBitmap(mWidth,mHeight),0,0,mPaint);

        //设置混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //源图， 重叠区域右下角的部分
        canvas.drawBitmap(createCircleBitmap(mWidth,mHeight),0,0,mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);

    }

    private Bitmap createCircleBitmap(int mWidth, int mHeight) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(new Rect(mWidth/20,mHeight/3,2*mWidth,19*mHeight/20),paint);
        return bitmap;
    }

    private Bitmap createRectBitmap(int mWidth, int mHeight) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(mWidth*2/3,mHeight/3,mHeight/4,paint);
        return bitmap;
    }
}
