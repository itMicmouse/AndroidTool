package com.yangyakun.androidtool.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yangyakun.androidtool.utils.Utils;

public class TextView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        path.reset();
        path.addRect(getWidth()/2-150,getHeight()/2-300,getWidth()/2+150,getHeight()/2,Path.Direction.CCW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(100,100,200,200,paint);

        canvas.drawCircle(getWidth()/2,getHeight()/2,Utils.dp2px(150),paint);
    }
}
