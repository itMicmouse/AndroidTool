package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.eventbus.SqlData;
import com.yangyakun.androidtool.service.CountService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class DBActivity extends Activity {

    private TextView tv_result;

    private CheckBox cb_patient;
    private CheckBox cb_commondity;
    private CheckBox cb_lable;

    private EditText et_sql;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        EventBus.getDefault().register(this);
        Intent intent = new Intent(DBActivity.this, CountService.class);
        /** 进入Activity开始服务 */
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        tv_result = findViewById(R.id.tv_result);
        cb_patient = findViewById(R.id.cb_patient);
        cb_commondity = findViewById(R.id.cb_commondity);
        cb_lable = findViewById(R.id.cb_lable);
        et_sql = findViewById(R.id.et_sql);
        tv_result.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    public void initData(View view) {
        if (countService != null) {
            List<String> dataList = new ArrayList<>();
            List<String> dataListName = new ArrayList<>();
            if(cb_patient.isChecked()){
                dataList.add("patient.db");
                dataListName.add("patient");
            }
            if(cb_commondity.isChecked()){
                dataList.add("commoditymain.db");
                dataListName.add("commoditymain");
            }
            if(cb_lable.isChecked()){
                dataList.add("label.db");
                dataListName.add("label");
            }
            if(TextUtils.isEmpty(et_sql.getText())){
                Toast.makeText(countService, "请写sql", Toast.LENGTH_SHORT).show();
                return;
            }
            countService.execSql(dataList,dataListName,et_sql.getText().toString());
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
            countService = ((CountService.ServiceBinder) service).getService();
        }

        /** 无法获取到服务对象时的操作 */
        @Override
        public void onServiceDisconnected(ComponentName name) {
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
        tv_result.setText("");
    }
}
