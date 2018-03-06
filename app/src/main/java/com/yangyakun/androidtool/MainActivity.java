package com.yangyakun.androidtool;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        SystemUIUtils.setStickFullScreen(getWindow().getDecorView());
    }

    public void opensettting(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String packageName = "cn.trinea.android.developertools";
        String className = "cn.trinea.android.developertools.MainActivity";
        intent.setClassName(packageName, className);


//        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}
