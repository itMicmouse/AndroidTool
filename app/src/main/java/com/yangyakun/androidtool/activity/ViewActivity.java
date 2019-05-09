package com.yangyakun.androidtool.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.view.ColorFilterView;
import com.yangyakun.androidtool.view.XfermodeEraserView;
import com.yangyakun.androidtool.view.XfermodesView;
import com.yangyakun.androidtool.view.canvas.TransformView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TransformView(this));
    }
}
