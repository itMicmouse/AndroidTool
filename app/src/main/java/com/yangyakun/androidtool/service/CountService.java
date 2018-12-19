package com.yangyakun.androidtool.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.yangyakun.androidtool.db.dbmanage.DBManager;
import com.yangyakun.androidtool.db.domain.Commondity;
import com.yangyakun.androidtool.db.domain.LableDetail;
import com.yangyakun.androidtool.db.domain.LableMain;
import com.yangyakun.androidtool.db.domain.Patient;
import com.yangyakun.androidtool.db.domain.Prescription;

public class CountService extends Service {
    /**
     * 创建参数
     */
    boolean threadDisable =true;
    int count;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("onBind.....");
        IBinder result = null;
        if ( null == result ) result = new ServiceBinder() ;
        Toast.makeText(this, "onBind",Toast.LENGTH_LONG);
        return result;
    }

    @Override
    public void onCreate() {
        // get the Rx variant of the note DAO
        super.onCreate();
    }
    public void startInsertData(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                Patient patient = new Patient();
                patient.doMain(100000);
                long end = System.currentTimeMillis();

                System.out.println("患者耗时："+(end-start));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                LableMain lableMain = new LableMain();
                LableDetail lableDetail = new LableDetail();
                lableMain.doMain(10000);
                lableDetail.doMain(100000);
                long end = System.currentTimeMillis();

                System.out.println("标签耗时："+(end-start));
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                Prescription prescription = new Prescription();
                prescription.doMain(100000,10);
                long end = System.currentTimeMillis();

                System.out.println("处方耗时："+(end-start));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                Commondity commondity = new Commondity();
                commondity.doMain(100000);
                long end = System.currentTimeMillis();
                System.out.println("药品耗时："+(end-start));
            }
        }).start();


    }

    public void startInsertDataTemp(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                LableDetail lableDetail = new LableDetail();
                lableDetail.doMain(100000);
                long end = System.currentTimeMillis();

                System.out.println("耗时："+(end-start));
            }
        }).start();

    }

    public void testSql(){
        String sql01 = "select count(1)as count from pb_prescription_details";

        SQLiteDatabase sqLiteDatabase = DBManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql01, null);
        if(cursor.moveToNext()){
            int anInt = cursor.getInt(0);
            Log.e("test",anInt+"");
        }
        DBManager.getInstance().closeDatabase();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /** 服务停止时，终止计数进程 */
        this.threadDisable = true;
    }

    public int getConunt() {
        return count;
    }

//此方法是为了可以在Acitity中获得服务的实例

    public class ServiceBinder extends Binder {
        public CountService getService() {
            return CountService.this;
        }
    }
}
