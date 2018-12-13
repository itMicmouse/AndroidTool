package com.yangyakun.androidtool.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.yangyakun.androidtool.db.DBHelper;
import com.yangyakun.androidtool.db.DBManager;

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

        SQLiteOnWeb.init(this).start();
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
