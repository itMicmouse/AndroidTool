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
import com.yangyakun.androidtool.db.dbmanage.PbPatientDBManager;
import com.yangyakun.androidtool.db.dbmanage.PbPrescriptionDBManager;
import com.yangyakun.androidtool.db.domain.Commondity;
import com.yangyakun.androidtool.db.domain.LableDetail;
import com.yangyakun.androidtool.db.domain.LableMain;
import com.yangyakun.androidtool.db.domain.Patient;
import com.yangyakun.androidtool.db.domain.Prescription;
import com.yangyakun.androidtool.eventbus.SqlData;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountService extends Service {
    /**
     * 创建参数
     */
    boolean threadDisable = true;
    int count;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("onBind.....");
        IBinder result = null;
        if (null == result) result = new ServiceBinder();
        Toast.makeText(this, "onBind", Toast.LENGTH_LONG);
        return result;
    }

    @Override
    public void onCreate() {
        // get the Rx variant of the note DAO
        super.onCreate();
    }

    private ThreadPoolExecutor scheduledExecutorService = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(5));

    public void startInsertData() {

        long startAll = System.currentTimeMillis();

        scheduledExecutorService.execute(() -> {
            long start = System.currentTimeMillis();
            Patient patient = new Patient();
            patient.doMain(100000);
            long end = System.currentTimeMillis();

            System.out.println("患者耗时：" + (end - start));
            System.out.println("患者耗时总耗时：" + (end - startAll));
            SqlData sqlData = new SqlData();
            sqlData.Message = "患者耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });

        scheduledExecutorService.execute(() -> {
            long start = System.currentTimeMillis();
            LableMain lableMain = new LableMain();
            LableDetail lableDetail = new LableDetail();
            lableMain.doMain(10000);
            lableDetail.doMain(100000);
            long end = System.currentTimeMillis();
            System.out.println("标签耗时：" + (end - start));
            System.out.println("标签耗时总耗时：" + (end - startAll));
            SqlData sqlData = new SqlData();
            sqlData.Message = "标签耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });

        scheduledExecutorService.execute(() -> {
            long start = System.currentTimeMillis();
            Prescription prescription = new Prescription();
            prescription.doMain(100000, 10);
            long end = System.currentTimeMillis();
            System.out.println("处方耗时：" + (end - start));
            System.out.println("处方耗时总耗时：" + (end - startAll));
            SqlData sqlData = new SqlData();
            sqlData.Message = "处方耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });

        scheduledExecutorService.execute(() -> {
            long start = System.currentTimeMillis();
            Commondity commondity = new Commondity();
            commondity.doMain(100000);
            long end = System.currentTimeMillis();
            System.out.println("药品耗时：" + (end - start));
            System.out.println("药品耗时总耗时：" + (end - startAll));
            SqlData sqlData = new SqlData();
            sqlData.Message = "药品耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });

    }

    /**
     * 随机查询
     */
    public void selectSample() {
        scheduledExecutorService.execute(() -> {
            long start = System.currentTimeMillis();
            String sql01 = "SELECT userName FROM pb_patient ORDER BY random() LIMIT 1;";
            SQLiteDatabase sqLiteDatabase = PbPatientDBManager.getInstance().openDatabase();
            for (int i = 0; i < 10000; i++) {
                Cursor cursor = sqLiteDatabase.rawQuery(sql01, null);
                if (cursor.moveToNext()) {
                    String anInt = cursor.getString(0);
                    Log.e(i + "随机id", anInt);
                }
                cursor.close();
            }
            DBManager.getInstance().closeDatabase();
            long end = System.currentTimeMillis();
            System.out.println("10000查询耗时：" + (end - start));
            SqlData sqlData = new SqlData();
            sqlData.clean = true;
            sqlData.Message = "10000查询耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });
    }

    /**
     * 9
     * 查询
     */
    public void selectSample_1() {
        scheduledExecutorService.execute(() -> {
            String sql01 = "SELECT userName FROM pb_patient where id = ?";
            SQLiteDatabase sqLiteDatabase = PbPatientDBManager.getInstance().openDatabase();
            String sqlindex = "SELECT id FROM pb_patient ORDER BY random();";
            Cursor cursorindex = sqLiteDatabase.rawQuery(sqlindex, null);
            List<String> patientId = new ArrayList();
            while (cursorindex.moveToNext()) {
                String anInt = cursorindex.getString(0);
                patientId.add(anInt);
            }
            long start = System.currentTimeMillis();

            for (int i = 0; i < patientId.size(); i++) {
                Cursor cursor = sqLiteDatabase.rawQuery(sql01, new String[]{patientId.get(i)});
                if (cursor.moveToNext()) {
                    String anInt = cursor.getString(0);
                    Log.e(i + "查询id", anInt);
                }
                cursor.close();
            }
            DBManager.getInstance().closeDatabase();
            long end = System.currentTimeMillis();
            System.out.println("10000查询耗时：" + (end - start));
            SqlData sqlData = new SqlData();
            sqlData.clean = true;
            sqlData.Message = "10000查询耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });

    }

    /**
     * 更新
     */
    public void updateSample_1() {
        scheduledExecutorService.execute(() -> {
            long start = System.currentTimeMillis();
            String sql01 = "UPDATE pb_prescription_main set weight = '110' where id = ?";
            SQLiteDatabase sqLiteDatabase = PbPrescriptionDBManager.getInstance().openDatabase();
            for (int i = 0; i < 100000; i++) {
                sqLiteDatabase.execSQL(sql01, new String[]{"5c1cd64a-4236-445e-b646-61bdbcf2eeed"});
                System.out.println("更新耗时" + i);
            }
            DBManager.getInstance().closeDatabase();
            long end = System.currentTimeMillis();
            System.out.println("100000更新耗时：" + (end - start));
            SqlData sqlData = new SqlData();
            sqlData.Message = "10000更新耗时：" + (end - start);
            EventBus.getDefault().post(sqlData);
        });
    }

    /**
     * 简单联合查询
     */
    public void unionSelect() {
        scheduledExecutorService.execute(() -> {
            String sql01 = "SELECT id FROM pb_prescription_main ORDER BY random() LIMIT 1;";
            SQLiteDatabase sqLiteDatabase = PbPrescriptionDBManager.getInstance().openDatabase();
            List<String> list = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.rawQuery(sql01, null);
            while (cursor.moveToNext()) {
                String anInt = cursor.getString(0);
                list.add(anInt);
            }
            cursor.close();
            long start = System.currentTimeMillis();
            String sqlunion = "select pb_prescription_main.id FROM pb_prescription_main " +
                    " LEFT JOIN pb_prescription_details ON pb_prescription_main.id = pb_prescription_details.diagnosisId " +
                    " WHERE pb_prescription_main.id = ?";
            for (String id : list) {
                Cursor cursorPrescription = sqLiteDatabase.rawQuery(sqlunion, new String[]{id});
                if (cursorPrescription.moveToNext()) {
                    String anInt = cursorPrescription.getString(0);
                    System.out.println("处方id" + anInt);
                }
                cursorPrescription.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("简单联合查询：" + (end - start));
            DBManager.getInstance().closeDatabase();
        });
    }

    /**
     * 附加数据库
     */
    public void attachSelect() {
        scheduledExecutorService.execute(() -> {
            try {
                long start = System.currentTimeMillis();
                SQLiteDatabase sqLiteDatabase = PbPrescriptionDBManager.getInstance().openDatabase();
                File externalFilesDir = getExternalFilesDir("patient.db");
                sqLiteDatabase.execSQL(String.format("ATTACH DATABASE \'%s\' AS \'%s\'", externalFilesDir.getAbsolutePath(), "patient"));

                String sql = "SELECT patient.pb_patient.id from patient.pb_patient " +
                        " LEFT JOIN pb_prescription_main on pb_prescription_main.patientId = patient.pb_patient.id " +
                        " where patient.pb_patient.id = ? ";
                Cursor cursorPrescription = sqLiteDatabase.rawQuery(sql, new String[]{"00030aa6-78ed-477b-9c0a-ef34d4c78158"});
                if (cursorPrescription.moveToNext()) {
                    String anInt = cursorPrescription.getString(0);
                    System.out.println("复杂联合查询" + anInt);
                }
                cursorPrescription.close();

                sqLiteDatabase.execSQL(String.format("DETACH DATABASE %s", "patient"));
                DBManager.getInstance().closeDatabase();
                long end = System.currentTimeMillis();

                System.out.println("附加数据库耗时：" + (end - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void unionSelect_2() {
        scheduledExecutorService.execute(() -> {
            try {
                SQLiteDatabase sqLiteDatabase = PbPrescriptionDBManager.getInstance().openDatabase();
                File externalFilesDir = getExternalFilesDir("patient.db");
                sqLiteDatabase.execSQL(String.format("ATTACH DATABASE \'%s\' AS \'%s\'", externalFilesDir.getAbsolutePath(), "patient"));
                long start = System.currentTimeMillis();
                String sqlunion = "SELECT pb_prescription_details.id from patient.pb_patient \n" +
                        " LEFT JOIN pb_prescription_main on pb_prescription_main.patientId = patient.pb_patient.id \n" +
                        " LEFT JOIN pb_prescription_details on pb_prescription_main.id = pb_prescription_details.diagnosisId \n" +
                        " where patient.pb_patient.id = ?";
                Cursor cursorPrescription = sqLiteDatabase.rawQuery(sqlunion, new String[]{"97e8be1b-c432-4547-8672-ed8ab9c3374e"});
                if (cursorPrescription.moveToNext()) {
                    String anInt = cursorPrescription.getString(0);
                    System.out.println("复杂联合查询" + anInt);
                }
                cursorPrescription.close();
                sqLiteDatabase.execSQL(String.format("DETACH DATABASE %s", "patient"));
                DBManager.getInstance().closeDatabase();
                long end = System.currentTimeMillis();

                System.out.println("附加数据库三层联合查询：" + (end - start));
                SqlData sqlData = new SqlData();
                sqlData.Message = "附加数据库三层联合查询：" + (end - start);
                EventBus.getDefault().post(sqlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        /** 服务停止时，终止计数进程 */
        this.threadDisable = true;
    }

//此方法是为了可以在Acitity中获得服务的实例

    public class ServiceBinder extends Binder {
        public CountService getService() {
            return CountService.this;
        }
    }
}
