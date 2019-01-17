package com.yangyakun.androidtool.db.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.yangyakun.androidtool.db.dbmanage.DBManager;
import com.yangyakun.androidtool.db.dbmanage.SysLabelDBManager;

import java.util.ArrayList;
import java.util.UUID;

public class LableMain {
    public void doMain(int magnitude) {
        SQLiteDatabase db = null;
        try {
            db = SysLabelDBManager.getInstance().openDatabase();
//            db.beginTransaction();

            ArrayList<String> needdelete = new ArrayList<>();
            String sql_insert = " insert into sys_label_main (id, clinicId, name, code, parentId, sort, base_version, status ) "
                    + "values (?,?,?,?,?,?,?,?) ";
            SQLiteStatement stat_insert = db.compileStatement(sql_insert);

            for (int i = 0; i < magnitude; i++) {
                if (i % 10000 == 0) {
                    db.beginTransaction();
                }
                stat_insert.bindString(1, UUID.randomUUID().toString());
                stat_insert.bindString(2, "clinicId");
                stat_insert.bindString(3, "name");
                stat_insert.bindString(4, "code");
                stat_insert.bindString(5, "parentId");
                stat_insert.bindString(6, "sort");
                stat_insert.bindString(7, "base_version");
                stat_insert.bindString(8, "status");
                stat_insert.executeInsert();
                System.out.println("添加标签主表:" + i);
                if (i % 10000 == (10000 - 1)) {
                    db.setTransactionSuccessful();
                    db.endTransaction();
                }
            }
            if (db.inTransaction()) {
                db.setTransactionSuccessful();
                db.endTransaction();
            }
//            db.setTransactionSuccessful();
        } catch (Exception e) {
            // DBError.insertDBerror(" getUpdateSysLableMain - " + e.getMessage());
        } finally {
            if (null != db) {
//                db.endTransaction();
                SysLabelDBManager.getInstance().closeDatabase();
            }
        }
    }
}
