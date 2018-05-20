package com.yangyakun.androidtool;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Class c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            Log.i("sunmi", "the sn:" + (String) get.invoke(c, "ro.serialno"));
            Log.i("sunmi", "First four characters:" + (String) get.invoke(c, "ro.serialno"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void opensettting(View view) {

        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    public void openFlexBox(View view) {
        Intent intent = new Intent(MainActivity.this,FlexboxActivity.class);
        startActivity(intent);
    }

}
