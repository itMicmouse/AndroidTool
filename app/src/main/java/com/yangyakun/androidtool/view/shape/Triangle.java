package com.yangyakun.androidtool.view.shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {

    //opengl 操作

    static float triangleCoords[]={
            0.5f,  0.5f, 0.0f,
            -0.5f,-0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
    };

    private FloatBuffer vertextBuffer;

    public Triangle() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertextBuffer = byteBuffer.asFloatBuffer();

    }
}
