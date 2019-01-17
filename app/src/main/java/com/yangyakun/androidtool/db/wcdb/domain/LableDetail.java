package com.yangyakun.androidtool.db.wcdb.domain;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteStatement;

import com.yangyakun.androidtool.db.wcdb.dbmanage.SysLabelDBManager;

import java.util.UUID;

public class LableDetail {
    public void doMain(int magnitude) {
        SQLiteDatabase db = null;
        try {
            db = SysLabelDBManager.getInstance().openDatabase();
//            db.beginTransaction();
            // 标签附表
            String sql_insert1 = " replace into sys_label_details (id, clinicId, labelId, groupObjId, sort , base_version, status ) "
                    + "values (?,?,?,?,?,?,?) ";
            SQLiteStatement stat_insert1 = db.compileStatement(sql_insert1);
            for (int i = 0; i < magnitude; i++) {
                stat_insert1.bindString(1, UUID.randomUUID().toString());
                stat_insert1.bindString(2, "clinicId");
                stat_insert1.bindString(3, "labelId");
                stat_insert1.bindString(4, "groupObjId");
                stat_insert1.bindString(5, "sort");
                stat_insert1.bindString(6, "base_version");
                stat_insert1.bindString(7, "status");
                System.out.println("添加标签附表:"+i);
                stat_insert1.executeInsert();
            }
//            db.setTransactionSuccessful();
        } catch (Exception e) {
            // 异常处理
            // DBError.insertDBerror(" getUpdateSysLableDetails - " + e.getMessage());
        } finally {
            if (null != db) {
//                db.endTransaction();
                SysLabelDBManager.getInstance().closeDatabase();
            }
        }
    }
}
