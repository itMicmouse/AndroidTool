package com.yangyakun.androidtool;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.yangyakun.androidtool.scan.ScanGun;

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        SystemUIUtils.setStickFullScreen(getWindow().getDecorView());
    }

    public void opensettting(View view) {

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String packageName = "cn.trinea.android.developertools";
//        String className = "cn.trinea.android.developertools.MainActivity";
//        intent.setClassName(packageName, className);

        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent); // 打开系统设置界面
//        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    private boolean keyEven = false;
    private ScanGun mScanGun = null;
    private boolean isHandleKey = true;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyEven) {
            if (keyCode <= 6) {
                return super.dispatchKeyEvent(event);
            }
            if (mScanGun.isMaybeScanning(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // 拦截物理键盘事件
        if (keyEven) {
            if (event.getKeyCode() != 67) {
                if (isHandleKey && event.getKeyCode() > 6) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        this.onKeyDown(event.getKeyCode(), event);
                        return true;

                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        this.onKeyUp(event.getKeyCode(), event);
                        return true;
                    }
                    return true;
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
