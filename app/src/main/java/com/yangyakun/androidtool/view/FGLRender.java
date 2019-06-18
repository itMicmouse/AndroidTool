package com.yangyakun.androidtool.view;

import android.opengl.GLSurfaceView;
import android.view.View;

import com.yangyakun.androidtool.view.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FGLRender implements GLSurfaceView.Renderer {

    protected View view;
    private Triangle triangle;


    public FGLRender(FGLView fglView) {
        this.view = fglView;
        triangle = new Triangle();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }
    //不断被调用
    @Override
    public void onDrawFrame(GL10 gl) {

    }
}
