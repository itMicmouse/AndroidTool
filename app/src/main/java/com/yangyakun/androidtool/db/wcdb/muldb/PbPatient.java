package com.yangyakun.androidtool.db.wcdb.muldb;

import android.content.Context;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

import com.yangyakun.androidtool.utils.DbUpdateHelper;

public class PbPatient extends SQLiteOpenHelper {

    public static final String NAME = "patient1.db";
    private static final int VERSION = 15;

    public static final String T_ID = "id";

    public PbPatient(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //region 患者表
        StringBuilder sb_pb_patient = new StringBuilder();
        sb_pb_patient.append("create table pb_patient ");
        sb_pb_patient.append("(");
        sb_pb_patient.append("   id                   varchar(40)                    primary key,");
        sb_pb_patient.append("   userName             varchar(20)                    not null,	 ");
        sb_pb_patient.append("   userShortName        varchar(20)                    not null,	 ");
        sb_pb_patient.append("   birthday             date                           null,		 ");
        sb_pb_patient.append("   sex                  varchar(40)                    not null,	 ");
        sb_pb_patient.append("   phone                varchar(40)                    null,		 ");
        sb_pb_patient.append("   illHistory           varchar(800)                   null,		 ");
        sb_pb_patient.append("   allergicHistory      varchar(800)                   null,		 ");
        sb_pb_patient.append("   address              varchar(100)                   null,		 ");
        sb_pb_patient.append("   totalArrears         double                         DEFAULT(0), ");
        sb_pb_patient.append("   status               int                            null,		 ");
        sb_pb_patient.append("   clinicId             varchar(40)                    null, 		 ");
        sb_pb_patient.append("   idCardNo             varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   agency               varchar(800)                   DEFAULT(''),");
        sb_pb_patient.append("   folk                 varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   fingerPrint          varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   validitytime         varchar(800)                   DEFAULT(''),");
        sb_pb_patient.append("   diagnose             varchar(800)                   DEFAULT(''),");
        sb_pb_patient.append("   deviceType           varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   isDelete             varchar(40)                    null,		 ");
        sb_pb_patient.append("   flag                 varchar(1)                     null,		 ");
        sb_pb_patient.append("   patientSource        varchar(40)                    null,		 ");
        sb_pb_patient.append("   base_version         varchar(40)                    null 		 ");

        sb_pb_patient.append(");");
        sqLiteDatabase.execSQL(sb_pb_patient.toString());
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
