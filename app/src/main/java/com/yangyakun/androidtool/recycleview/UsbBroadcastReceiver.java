package com.yangyakun.androidtool.recycleview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author 92155
 */
public class UsbBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_MEDIA_MOUNTED)){
            Toast.makeText(context, "插入", Toast.LENGTH_LONG).show();
            System.out.println("挂载的...");
        }else if(action.equals(Intent.ACTION_MEDIA_UNMOUNTED)){
            Toast.makeText(context, "拔出", Toast.LENGTH_LONG).show();
            System.out.println("非挂载......");
        }
    }
}
