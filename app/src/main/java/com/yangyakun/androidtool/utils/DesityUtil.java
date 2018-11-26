package com.yangyakun.androidtool.utils;

import android.content.Context;
import android.widget.Toast;

import com.yangyakun.androidtool.app.BaseApplication;

/**
 * Created by GuiYanBing on 2018/3/27 13:10
 * E-Mail Address：guiyanbing@zhiyihealth.com.cn
 */

public class DesityUtil {

    private static final String tag = "DesityUtil";
    private static Toast mToast;


    /**
     * 单例Toast
     *
     * @param msg 要显示的信息
     */
    public static void showToast(final Context context, final String msg) {
        BaseApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(BaseApplication.getInstance(), "", Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }
    public static void showToast(final String msg) {
        BaseApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(BaseApplication.getInstance(), "", Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

    public static boolean isEmpty(String s) {
        if (null == s) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }
        return s.trim().length() == 0;
    }

}
