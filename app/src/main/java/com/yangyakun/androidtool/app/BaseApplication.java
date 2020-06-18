package com.yangyakun.androidtool.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.yangyakun.androidtool.db.dbmanage.PbCommodityMainDBManager;
import com.yangyakun.androidtool.db.dbmanage.PbPatientDBManager;
import com.yangyakun.androidtool.db.dbmanage.PbPrescriptionDBManager;
import com.yangyakun.androidtool.db.dbmanage.SysLabelDBManager;
import com.yangyakun.androidtool.db.muldb.DBHelper;
import com.yangyakun.androidtool.db.dbmanage.DBManager;
import com.yangyakun.androidtool.db.muldb.PbCommodityMain;
import com.yangyakun.androidtool.db.muldb.PbPatient;
import com.yangyakun.androidtool.db.muldb.PbPrescription;
import com.yangyakun.androidtool.db.muldb.SysLabel;

public class BaseApplication extends MultiDexApplication {

    /**
     * 获取到主线程的handler
     */
    private static Handler mMainThreadHanler;

    private static Application mApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        mMainThreadHanler = new Handler();
        mApplication = this;

        DBManager.initializeInstance(new DBHelper(mApplication));
        PbCommodityMainDBManager.initializeInstance(new PbCommodityMain(mApplication));
        PbPatientDBManager.initializeInstance(new PbPatient(mApplication));
        PbPrescriptionDBManager.initializeInstance(new PbPrescription(mApplication));
        SysLabelDBManager.initializeInstance(new SysLabel(mApplication));



       com.yangyakun.androidtool.db.wcdb.dbmanage.DBManager.initializeInstance
               (new com.yangyakun.androidtool.db.wcdb.muldb.DBHelper(mApplication));
       com.yangyakun.androidtool.db.wcdb.dbmanage.PbCommodityMainDBManager.initializeInstance
               (new com.yangyakun.androidtool.db.wcdb.muldb.PbCommodityMain(mApplication));
       com.yangyakun.androidtool.db.wcdb.dbmanage.PbPatientDBManager.initializeInstance
               (new com.yangyakun.androidtool.db.wcdb.muldb.PbPatient(mApplication));
       com.yangyakun.androidtool.db.wcdb.dbmanage.PbPrescriptionDBManager.initializeInstance
               (new com.yangyakun.androidtool.db.wcdb.muldb.PbPrescription(mApplication));
       com.yangyakun.androidtool.db.wcdb.dbmanage.SysLabelDBManager.initializeInstance
               (new com.yangyakun.androidtool.db.wcdb.muldb.SysLabel(mApplication));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    /**
     * 是否主进程
     */
    private static boolean isMainProcess(Application application) {


        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) application.getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return application.getPackageName().equals(processName);
    }

    public static Handler getMainThreadHandlerApp   () {
        return mMainThreadHanler;
    }

    public static Application getInstance() {
        return mApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
