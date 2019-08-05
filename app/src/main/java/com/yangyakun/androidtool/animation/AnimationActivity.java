package com.yangyakun.androidtool.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;

import com.yangyakun.androidtool.R;

public class AnimationActivity extends AppCompatActivity {

    Button button;
    private static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        button= findViewById(R.id.btn);

    }
}
