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
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.eventbus.SqlData;
import com.yangyakun.androidtool.service.CountService;
import com.yangyakun.androidtool.utils.FileUtils;
import com.yangyakun.androidtool.utils.MarqueeText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class DBActivity extends Activity {

    private TextView tv_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        EventBus.getDefault().register(this);
        Intent intent = new Intent(DBActivity.this, CountService.class);
        /** 进入Activity开始服务 */
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        tv_result = findViewById(R.id.tv_result);
        tv_result.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void opensettting(View view) {
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
        EventBus.getDefault().unregister(this);
        Log.v("MainStadyServics", "out");
    }

    public void selectSample(View view) {
        if (countService != null) {
            countService.selectSample();
        }
    }

    public void selectSample_1(View view) {
        if (countService != null) {
            countService.selectSample_1();
        }
    }

    public void updateSample_1(View view) {
        if (countService != null) {
            countService.updateSample_1();
        }
    }

    public void unionSelect(View view) {
        if (countService != null) {
            countService.unionSelect();
        }
    }

    public void attachSelect(View view) {
        if (countService != null) {
            countService.attachSelect();
        }
    }

    public void unionSelect_2(View view) {
        if (countService != null) {
            countService.unionSelect_2();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SqlData event) {
        if(event.clean) {
            tv_result.setText("");
        }
        tv_result.append(event.Message);
    }

    public void cleanLog(View view) {
        tv_result.append("");
    }
}
