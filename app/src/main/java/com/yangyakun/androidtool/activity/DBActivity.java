package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.service.CountService;
import com.yangyakun.androidtool.utils.FileUtils;
import com.yangyakun.androidtool.utils.MarqueeText;

import java.io.File;

public class DBActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        MarqueeText viewById = findViewById(R.id.tv_device_name);
        viewById.setSelected(true);
        viewById.startScroll();
        MarqueeText tv_device_name1 = findViewById(R.id.tv_device_name1);
        tv_device_name1.setSelected(true);
        tv_device_name1.startScroll();

        TextView tv_device_name3 = findViewById(R.id.tv_device_name3);
        tv_device_name3.setSelected(true);
        TextView tv_device_name4 = findViewById(R.id.tv_device_name4);
        tv_device_name4.setSelected(true);

        Intent intent = new Intent(DBActivity.this, CountService.class);
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
    CountService countService;

    private ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            countService = ((CountService.ServiceBinder) service).getService();
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

    public void getCount(View view) {
        if (countService != null) {
            countService.testSql();
        }
    }

    public void dataCopy(View view) {

        String from = "/data/data/com.yangyakun.androidtool/databases/c.db";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            File dataDir = getDataDir();
        }
        File databasePath = getDatabasePath("c.db");
        String target = Environment.getExternalStorageDirectory().getAbsolutePath() + "/c.db";
        FileUtils.copyFile(databasePath, target, false);
    }

    public void dataTemp(View view) {
        if (countService != null) {
            countService.startInsertDataTemp();
        }
    }
}
