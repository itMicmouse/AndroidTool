package com.yangyakun.androidtool.db.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.yangyakun.androidtool.db.dbmanage.DBManager;
import com.yangyakun.androidtool.db.dbmanage.PbCommodityMainDBManager;

import java.util.UUID;

public class Commondity {
    public void doMain(int magnitude) {
        SQLiteDatabase db = null;
        try {
            db = PbCommodityMainDBManager.getInstance().openDatabase();
//            db.beginTransaction();
            // 主表
            String sql_insert = " replace into pb_commodity_main (id, commodityCategory, commodityCode, commodityName, commodityShortName, commonNum, simpleName, simpleShortName, " +
                    "typeId, categoryId, dosageFormId, dosageUnitId, dosage, commonUnit, minimumUnit, taboo, direction, rate, quantum, quantum_unit, specs, " +
                    "enterpriseName, conversionValue, activeTime, prescriptionPrice1, prescriptionPrice2, prescriptionNum1, prescriptionNum2, showedRedPoint, overedThreshold, remark, " +
                    "base_version, status,clinicId,approvalNumber,priceLastUpdated,commoditySource,threshold,thresholdUnit) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            SQLiteStatement stat_insert = db.compileStatement(sql_insert);
            for (int j = 0; j < magnitude; j++) {
                stat_insert.bindString(1, UUID.randomUUID().toString());
                stat_insert.bindString(2, "commodityCategory");
                stat_insert.bindString(3, "commodityCode");
                stat_insert.bindString(4, "commodityName");
                stat_insert.bindString(5, "commodityShortName");
                stat_insert.bindString(6, "commonNum");
                stat_insert.bindString(7, "simpleName");
                stat_insert.bindString(8, "simpleShortName");
                stat_insert.bindString(9, "typeId");
                stat_insert.bindString(10, "categoryId");
                stat_insert.bindString(11, "dosageFormId");
                stat_insert.bindString(12, "dosageUnitId");
                stat_insert.bindString(13, "dosage");
                stat_insert.bindString(14, "commonUnit");
                stat_insert.bindString(15, "minimumUnit");
                stat_insert.bindString(16, "taboo");
                stat_insert.bindString(17, "direction");
                stat_insert.bindString(18, "rate");
                stat_insert.bindString(19, "quantum");
                stat_insert.bindString(20, "quantum_unit");
                stat_insert.bindString(21, "specs");
                stat_insert.bindString(22, "enterpriseName");
                stat_insert.bindString(23, "conversionValue");
                stat_insert.bindString(24, "activeTime");
                stat_insert.bindString(25, "prescriptionPrice1");
                stat_insert.bindString(26, "prescriptionPrice2");
                stat_insert.bindString(27, "prescriptionNum1");
                stat_insert.bindString(28, "prescriptionNum2");
                stat_insert.bindString(29, "showedRedPoint");
                stat_insert.bindString(30, "overedThreshold");
                stat_insert.bindString(31, "remark");
                stat_insert.bindString(32, "base_version");
                stat_insert.bindString(33, "status");
                stat_insert.bindString(34, "clinicId");
                stat_insert.bindString(35, "approvalNumber");
                stat_insert.bindString(36, "priceLastUpdated");
                stat_insert.bindString(37, "commoditySource");
                stat_insert.bindString(38, "threshold");
                stat_insert.bindString(39, "thresholdUnit");
                stat_insert.executeInsert();
                System.out.println("添加药品:"+j);
            }
//            db.setTransactionSuccessful();

        } catch (Exception e) {
            // 异常处理
        } finally {
            if (null != db) {
//                db.endTransaction();
                PbCommodityMainDBManager.getInstance().closeDatabase();
            }
        }
    }
}
