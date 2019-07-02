package com.yangyakun.androidtool.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yangyakun.androidtool.R;

public class AidlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    public void startService(View view) {
        Intent intent = new Intent(AidlActivity.this,
                com.yangyakun.androidtool.service.RemoteService.Controller.class);
        startActivity(intent);
    }


    public void bindService(View view) {
        Intent intent = new Intent(AidlActivity.this,
                com.yangyakun.androidtool.service.RemoteService.Binding.class);
        startActivity(intent);
    }

    public void bindServiceOption(View view) {
        Intent intent = new Intent(AidlActivity.this,
                com.yangyakun.androidtool.service.RemoteService.BindingOptions.class);
        startActivity(intent);
    }
}
