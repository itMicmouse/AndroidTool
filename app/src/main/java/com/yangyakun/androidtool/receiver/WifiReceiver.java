package com.yangyakun.androidtool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.yangyakun.androidtool.event.WifiState;

import org.greenrobot.eventbus.EventBus;

public class WifiReceiver extends BroadcastReceiver {
    private static final String TAG = "wifiReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
            WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
            int wifi = mWifiInfo.getRssi();//获取wifi信号强度
            if (wifi > -50 && wifi < 0) {//最强
                Log.i(TAG, "WIFI信号已达到最强王者"+wifi);
            } else if (wifi > -70 && wifi < -50) {//较强
                Log.i(TAG, "WIFI信号已达到永恒钻石"+wifi);
            } else if (wifi > -80 && wifi < -70) {//较弱
                Log.i(TAG, "WIFI信号已达不屈白银"+wifi);
            } else if (wifi > -100 && wifi < -80) {//微弱
                Log.i(TAG, "WIFI信号已达到倔强青铜"+wifi);
            } else {
                //无连接
            }
        }
        //wifi连接上与否
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                Log.i(TAG, "wifi断开");
                EventBus.getDefault().post(new WifiState(NetworkInfo.State.DISCONNECTED));
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //获取当前wifi名称
                Log.i(TAG, "连接到网络 " + wifiInfo.getSSID());
                EventBus.getDefault().post(new WifiState(NetworkInfo.State.CONNECTED));
            } else if(info.getState().equals(NetworkInfo.State.DISCONNECTING)){
                EventBus.getDefault().post(new WifiState(NetworkInfo.State.CONNECTED));
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                Log.i(TAG, "正在连接 " + wifiInfo.getSSID());
            }
        }

        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            //获取当前的wifi状态int类型数据
            int mWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (mWifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    //已打开
                    Log.i(TAG, "正在连接 已打开");
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    //打开中
                    Log.i(TAG, "正在连接 打开中");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    //已关闭
                    Log.i(TAG, "正在连接 已关闭");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    //关闭中
                    Log.i(TAG, "正在连接 关闭中");
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    //未知
                    Log.i(TAG, "正在连接 未知");
                    break;
            }
        }


        //wifi打开与否
        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                Log.i(TAG, "系统关闭wifi");
                EventBus.getDefault().post(new WifiState(NetworkInfo.State.DISCONNECTED));
            } else if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                Log.i(TAG, "系统开启wifi");
                EventBus.getDefault().post(new WifiState(NetworkInfo.State.CONNECTED));
            }
        }
    }
}
