package com.yangyakun.androidtool.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.yangyakun.androidtool.view.canvas.SplashVIew;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplashVIew(this));
    }
}
