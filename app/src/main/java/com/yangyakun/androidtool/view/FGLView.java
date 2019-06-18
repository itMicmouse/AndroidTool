package com.yangyakun.androidtool.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class FGLView extends GLSurfaceView {
    public FGLView(Context context) {
        super(context);
    }

    public FGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setRenderer(new FGLRender(this));

    }
}
