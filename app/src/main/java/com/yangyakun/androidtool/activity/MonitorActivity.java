package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.yangyakun.androidtool.R;

import cn.hikyson.godeye.monitor.GodEyeMonitor;

public class MonitorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
    }

    public void closeMonitor(View view) {
        GodEyeMonitor.shutDown();
    }

    public void openMonitor(View view) {
        GodEyeMonitor.work(this);
    }
}
