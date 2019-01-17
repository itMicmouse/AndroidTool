package com.yangyakun.androidtool.db.wcdb.domain;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteStatement;

import com.yangyakun.androidtool.db.wcdb.dbmanage.SysLabelDBManager;

import java.util.ArrayList;
import java.util.UUID;

public class LableMain {
    public void doMain(int magnitude) {
        SQLiteDatabase db = null;
        try {
            db = SysLabelDBManager.getInstance().openDatabase();
//            db.beginTransaction();

            ArrayList<String> needdelete = new ArrayList<>();
            String sql_insert = " replace into sys_label_main (id, clinicId, name, code, parentId, sort, base_version, status ) "
                    + "values (?,?,?,?,?,?,?,?) ";
            SQLiteStatement stat_insert = db.compileStatement(sql_insert);

            for (int i = 0; i < magnitude; i++) {
                stat_insert.bindString(1, UUID.randomUUID().toString());
                stat_insert.bindString(2, "clinicId");
                stat_insert.bindString(3, "name");
                stat_insert.bindString(4, "code");
                stat_insert.bindString(5, "parentId");
                stat_insert.bindString(6, "sort");
                stat_insert.bindString(7, "base_version");
                stat_insert.bindString(8, "status");
                stat_insert.executeInsert();
                System.out.println("添加标签主表:"+i);
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
