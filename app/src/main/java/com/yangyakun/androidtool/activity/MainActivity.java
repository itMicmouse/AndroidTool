package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.app.BaseApplication;
import com.yangyakun.androidtool.fragment.Mine_Screen_Manager_Fragment;
import com.yangyakun.androidtool.glide.GlideActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

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
    public void testViewPager2(View view) {
        Intent intent = new Intent(MainActivity.this, ViewPager2Activity.class);
        startActivity(intent);
    }
    public void testWebSocket(View view) {
        Intent intent = new Intent(MainActivity.this, WebSocketActivity.class);
        startActivity(intent);
    }

    public void testAIDL(View view) {
        Intent intent = new Intent(MainActivity.this,
                AidlActivity.class);
        startActivity(intent);
    }

    public void testOpenClose(View view) {
        Uri uri = Uri.parse("saaslocalservice://MainActivity");
        Intent intent1 = new Intent();
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.setData(uri);
        startActivity(intent1);
    }

    public void getLocalIp(View view) {
        new  Thread(){
            @Override
            public void run() {
                String ip = getIp();
                System.out.println(ip+"------------");
            }
        }.start();
    }

    public String getIp() {

        String eth0Ip = null;
        String wlan0Ip = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if(networkInterface.getDisplayName().equals("wlan0")){
                    wlan0Ip = getIpByDisplayName4Address(networkInterface);
                }else if(networkInterface.getDisplayName().equals("eth0")){
                    eth0Ip = getIpByDisplayName4Address(networkInterface);
                }


            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        if(!TextUtils.isEmpty(eth0Ip)){
            return eth0Ip;
        }
        if(!TextUtils.isEmpty(wlan0Ip)){
            return wlan0Ip;
        }

        return null;
    }

    private String getIpByDisplayName4Address(NetworkInterface networkInterface) {
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()){
            InetAddress inetAddress = inetAddresses.nextElement();
            if(inetAddress instanceof java.net.Inet4Address){
                return inetAddress.getHostAddress();
            }
        }
        return "";
    }

}
