package com.yangyakun.androidtool.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;

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

import cn.hikyson.android.godeye.toolbox.crash.CrashFileProvider;
import cn.hikyson.android.godeye.toolbox.rxpermission.RxPermissionRequest;
import cn.hikyson.godeye.core.GodEye;
import cn.hikyson.godeye.core.installconfig.BatteryConfig;
import cn.hikyson.godeye.core.installconfig.CpuConfig;
import cn.hikyson.godeye.core.installconfig.CrashConfig;
import cn.hikyson.godeye.core.installconfig.FpsConfig;
import cn.hikyson.godeye.core.installconfig.HeapConfig;
import cn.hikyson.godeye.core.installconfig.LeakConfig;
import cn.hikyson.godeye.core.installconfig.PageloadConfig;
import cn.hikyson.godeye.core.installconfig.PssConfig;
import cn.hikyson.godeye.core.installconfig.RamConfig;
import cn.hikyson.godeye.core.installconfig.SmConfig;
import cn.hikyson.godeye.core.installconfig.ThreadConfig;
import cn.hikyson.godeye.core.installconfig.TrafficConfig;
import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb;

public class BaseApplication extends Application {

    /**
     * 获取到主线程的handler
     */
    private static Handler mMainThreadHanler;

    private static Application mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        GodEye.instance().init(this);

        mMainThreadHanler = new Handler();
        mApplication = this;

        if (isMainProcess(this)) {//安装只能在主进程
            GodEye.instance()
                    .install(new BatteryConfig(this))
                    .install(new CpuConfig())
                    .install(new CrashConfig(new CrashFileProvider(this)))
                    .install(new FpsConfig(this))
                    .install(new HeapConfig())
                    .install(new LeakConfig(this,new RxPermissionRequest()))
                    .install(new PageloadConfig(this))
                    .install(new PssConfig(this))
                    .install(new RamConfig(this))
                    .install(new SmConfig(this))
                    .install(new ThreadConfig())
                    .install(new TrafficConfig());
        }

        DBManager.initializeInstance(new DBHelper(mApplication));
        PbCommodityMainDBManager.initializeInstance(new PbCommodityMain(mApplication));
        PbPatientDBManager.initializeInstance(new PbPatient(mApplication));
        PbPrescriptionDBManager.initializeInstance(new PbPrescription(mApplication));
        SysLabelDBManager.initializeInstance(new SysLabel(mApplication));

        SQLiteOnWeb.init(this).start();

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

    public static Handler getMainThreadHandler() {
        return mMainThreadHanler;
    }

    public static Application getInstance() {
        return mApplication;
    }
}
