package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.yangyakun.androidtool.R;

import java.lang.reflect.Method;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void opensettting(View view) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    public void openFlexBox(View view) {
        Intent intent = new Intent(MainActivity.this, FlexboxActivity.class);
        startActivity(intent);
    }

    public void openRecycleVIew(View view) {
        Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
        startActivity(intent);
    }
    public void manageMonitor(View view) {
        Intent intent = new Intent(MainActivity.this, MonitorActivity.class);
        startActivity(intent);
    }

    public void netTest(View view) {
        Intent intent = new Intent(MainActivity.this, NEtTestActivity.class);
        startActivity(intent);
    }

    public void mDNS(View view) {
        Intent intent = new Intent(MainActivity.this, MDNSActivity.class);
        startActivity(intent);
    }
    public void mAudio(View view) {
        Intent intent = new Intent(MainActivity.this, AudioManagerActivity.class);
        startActivity(intent);
    }
}
