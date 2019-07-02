package com.yangyakun.androidtool.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.utils.Formatter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class OpenCloseActivity extends AppCompatActivity {

    private static final String TAG = "OpenCloseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_close);
    }

    public void openClose(View view) {
        writePropRockchip(1,-1800000,1,46800000);
    }

    public static void writePropRockchip(int timedOpen, int timedOpenTime, int timedOff, int timedOffTime) {
        Date date = new Date();
        String format = Formatter.DATE_FORMAT8.format(date);
        String formatOpen = Formatter.DATE_FORMAT11.format(timedOpenTime);
        String formatClose = Formatter.DATE_FORMAT11.format(timedOffTime);
        String formatDisable = Formatter.DATE_FORMAT11.format(0L);
        StringBuilder standby = new StringBuilder();
        standby.append("/system/xbin/test ");
//        standby.append("/system/xbin/test 201511062007 201511071505 enable");
        if(timedOpen!=1 && timedOff!=1){
            standby.append(" "+formatOpen+" "+formatClose+" disable");
        }else {
            if (timedOpen == 1) {
//                standby.append(" ").append(format).append(formatOpen);
                standby.append(" 201906261105");//关机
            } else {
                standby.append(" ").append(formatDisable);
            }
            if (timedOff == 1) {
//                standby.append(" ").append(format).append(formatClose);
                standby.append(" 201906261108");//开机
            } else {
                standby.append(" ").append(formatDisable);
            }
            standby.append(" enable");
        }
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(proc.getOutputStream());
            os.writeBytes(standby.toString()+"\n");
            os.writeBytes("exit\n");
            os.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            Log.e(TAG,output.toString());
            // Waits for the command to finish.
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, standby + "设置失败");
        } finally {
            Log.e(TAG, standby + "设置结束");
            if(proc!=null) {
                proc.destroy();
            }
        }
    }
}
