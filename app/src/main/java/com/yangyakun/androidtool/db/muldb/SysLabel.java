package com.yangyakun.androidtool.db.muldb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yangyakun.androidtool.base.SQLiteSDCardHelper;
import com.yangyakun.androidtool.utils.DbUpdateHelper;

public class SysLabel extends SQLiteSDCardHelper {

    public static final String NAME = "label.db";
    private static final int VERSION = 15;

    public static final String T_ID = "id";

    public SysLabel(Context context) {
        super(context, NAME, context.getExternalFilesDir("").getAbsolutePath(), VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //region 系统标签主表
        StringBuilder sb_sys_label_main_str = new StringBuilder();
        sb_sys_label_main_str.append("create table sys_label_main ");
        sb_sys_label_main_str.append("(");
        sb_sys_label_main_str.append(" id                   varchar(40)                   primary key,");
        sb_sys_label_main_str.append(" clinicId             varchar(40)                   not null,	  ");
        sb_sys_label_main_str.append(" name                 varchar(40)                   not null,	  ");
        sb_sys_label_main_str.append(" code                 varchar(20)                   not null,	  ");
        sb_sys_label_main_str.append(" parentId             varchar(40)                   null,		  ");
        sb_sys_label_main_str.append(" sort                 int                           null,		  ");
        sb_sys_label_main_str.append(" status               int                           null,       ");
        sb_sys_label_main_str.append(" base_version         varchar(40)                   null        ");
        sb_sys_label_main_str.append(" )");
        sqLiteDatabase.execSQL(sb_sys_label_main_str.toString());
        //endregion

        //region 系统标签从表
        StringBuilder sb_sys_label_details_str = new StringBuilder();
        sb_sys_label_details_str.append("create table sys_label_details ");
        sb_sys_label_details_str.append("(");
        sb_sys_label_details_str.append(" id                   varchar(40)                    primary key,");
        sb_sys_label_details_str.append(" clinicId             varchar(40)                    not null,   ");
        sb_sys_label_details_str.append(" labelId              varchar(40)                    not null,   ");
        sb_sys_label_details_str.append(" groupObjId           varchar(40)                    not null,   ");
        sb_sys_label_details_str.append(" sort                 int                            null,		  ");
        sb_sys_label_details_str.append(" flag                 varchar(1)                     null,       ");
        sb_sys_label_details_str.append(" status               int                            null,       ");
        sb_sys_label_details_str.append(" base_version         varchar(40)                    null        ");
        sb_sys_label_details_str.append(" )");
        sqLiteDatabase.execSQL(sb_sys_label_details_str.toString());
        //endregion

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDbVersion, int newDbVersion) {
        DbUpdateHelper dbUpdateHelper = new DbUpdateHelper();
        db.beginTransaction();
        try {
            switch (oldDbVersion) {
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }
}
