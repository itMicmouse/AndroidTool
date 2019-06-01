package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.fragment.Mine_Screen_Manager_Fragment;

/**
 * @author 92155
 */
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

    public void openRecycleVIew(View view) {
        Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
        startActivity(intent);
    }
    public void manageMonitor(View view) {
        Intent intent = new Intent(MainActivity.this, MonitorActivity.class);
        startActivity(intent);
    }

    public void mDNS(View view) {
        Intent intent = new Intent(MainActivity.this, MdnsActivity.class);
        startActivity(intent);
    }
    public void mAudio(View view) {
        Intent intent = new Intent(MainActivity.this, AudioManagerActivity.class);
        startActivity(intent);
    }
    public void mDb(View view) {
        Intent intent = new Intent(MainActivity.this, DBActivity.class);
        startActivity(intent);
    }
    public void makeTracerouteLinux(View view) {
        Intent intent = new Intent(MainActivity.this, TracerouteLinuxActivity.class);
        startActivity(intent);
    }

    public void wcdbDemo(View view) {
        Intent intent = new Intent(MainActivity.this, WCDBActivity.class);
        startActivity(intent);
    }
    public void testRealm(View view) {
        Intent intent = new Intent(MainActivity.this, DbRealmActivity.class);
        startActivity(intent);
    }

    public void testRetrofit(View view) {
        Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
        startActivity(intent);
    }
    public void testView(View view) {
        Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
        startActivity(intent);
    }
    public void testQr(View view) {
        Intent intent = new Intent(MainActivity.this, QrActivity.class);
        startActivity(intent);
    }

    public void testWebView(View view) {
        Mine_Screen_Manager_Fragment yyl = Mine_Screen_Manager_Fragment.newInstance("杨亚坤");
        yyl.show(getFragmentManager(),"yyl");
    }

    public void testGlide(View view) {
        Intent intent = new Intent(MainActivity.this, GlideActivity.class);
        startActivity(intent);
    }
}
