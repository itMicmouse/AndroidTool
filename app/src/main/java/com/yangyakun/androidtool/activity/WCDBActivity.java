package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.service.WCDBService;


public class WCDBActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcdb);

        Intent intent = new Intent(WCDBActivity.this, WCDBService.class);
        /** 进入Activity开始服务 */
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public void opensettting(View view) {
        addNote();
    }

    private void addNote() {
        if (countService != null) {
            countService.startInsertData();
        }
    }


    /**
     * 参数设置
     */
    WCDBService countService;

    private ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            countService = ((WCDBService.ServiceBinder) service).getService();
        }

        /** 无法获取到服务对象时的操作 */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            countService = null;
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(conn);
        Log.v("MainStadyServics", "out");
    }

}
