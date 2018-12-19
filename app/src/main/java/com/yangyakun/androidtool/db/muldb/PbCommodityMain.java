package com.yangyakun.androidtool.db.muldb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yangyakun.androidtool.utils.DbUpdateHelper;

public class PbCommodityMain extends SQLiteOpenHelper {

    public static final String NAME = "commoditymain.db";
    private static final int VERSION = 15;

    public static final String T_ID = "id";

    public PbCommodityMain(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //region 商品主表
        // [1:'药品',2:'器械',3:'服务']
        StringBuilder sb_pb_commodity_main = new StringBuilder();
        sb_pb_commodity_main.append("create TABLE pb_commodity_main ");
        sb_pb_commodity_main.append("(");
        sb_pb_commodity_main.append(" id                        varchar(40)                    primary key, ");
        sb_pb_commodity_main.append(" commodityCategory         varchar(40)                    null,  	    ");//药品分类 0其他1西药2器械3服务4中药
        sb_pb_commodity_main.append(" commodityCode             varchar(40)                    null,  	    ");//药品码
        sb_pb_commodity_main.append(" commodityName             varchar(70)                    null,  	    ");//药品名称
        sb_pb_commodity_main.append(" commodityShortName        varchar(20)                    null,  	    ");//药品名称简码
        sb_pb_commodity_main.append(" commonNum	                varchar(40)                    null,  	    ");//库存 拼接好
        sb_pb_commodity_main.append(" simpleName                varchar(50)                    null,  	    ");//药品简称
        sb_pb_commodity_main.append(" simpleShortName           varchar(20)                    null,  	    ");//药品简称简码
        sb_pb_commodity_main.append(" typeId                    varchar(40)                    null,  	    ");//0处方药 1非处方药
        sb_pb_commodity_main.append(" categoryId                varchar(40)                    null,  	    ");//分类id
        sb_pb_commodity_main.append(" dosageFormId              varchar(40)                    null,  	    ");//剂型
        sb_pb_commodity_main.append(" dosageUnitId              varchar(40)                    null,  	    ");//用量单位id
        sb_pb_commodity_main.append(" dosage                    varchar(20)                    null,  	    ");//用量
        sb_pb_commodity_main.append(" commonUnit                varchar(40)                    null,  	    ");//包装单位
        sb_pb_commodity_main.append(" minimumUnit               varchar(40)                    null,  	    ");//最小单位
        sb_pb_commodity_main.append(" taboo                     varchar(400)                   null,  	    ");//商品禁忌
        sb_pb_commodity_main.append(" direction                 varchar(6)                     null,  	    ");//用法
        sb_pb_commodity_main.append(" rate                      varchar(6)                     null,  	    ");//频度
        sb_pb_commodity_main.append(" quantum                   varchar(8)                     null,  	    ");//用量
        sb_pb_commodity_main.append(" quantum_unit              varchar(6)                     null,  	    ");//用量单位
        sb_pb_commodity_main.append(" specs                     varchar(50)                    null,  	    ");//规格
        sb_pb_commodity_main.append(" enterpriseName            varchar(20)                    null,  	    ");//厂商
        sb_pb_commodity_main.append(" conversionValue           int                         null,  	    ");//换算量
        sb_pb_commodity_main.append(" prescriptionPrice1       varchar(20)                          null,  	    ");//包装单位价格
        sb_pb_commodity_main.append(" prescriptionPrice2        varchar(20)                         null,  	    ");//最小单位价格
        sb_pb_commodity_main.append(" prescriptionNum1         DOUBLE		                   null,  	    ");//包装单位数量
        sb_pb_commodity_main.append(" prescriptionNum2          DOUBLE		                   null,  	    ");//最小单位数量
        sb_pb_commodity_main.append(" remark                    varchar(600)                   null,  	    ");//备注
        sb_pb_commodity_main.append(" medicinalCatagory         varchar(32)                    DEFAULT(''), ");//分类       1.0.8 --添加--
        sb_pb_commodity_main.append(" natureSmell               varchar(30)                    DEFAULT(''), ");//性味       1.0.8 --添加--
        sb_pb_commodity_main.append(" medicinalEffectLocation   varchar(30)                    DEFAULT(''), ");//归经       1.0.8 --添加--
        sb_pb_commodity_main.append(" medicinalEffect           varchar(100)                   DEFAULT(''), ");//功效       1.0.8 --添加--
        sb_pb_commodity_main.append(" indication                varchar(100)                   DEFAULT(''), ");//适应症      1.0.8 --添加--
        sb_pb_commodity_main.append(" announcements             varchar(100)                   DEFAULT(''), ");//注意事项    1.0.8 --添加--
        sb_pb_commodity_main.append(" defaultDosage             float                          DEFAULT(0),  ");//一剂默认用量 1.0.8 --添加--
        sb_pb_commodity_main.append(" activeTime                varchar(30)                    DEFAULT(''), ");//过期时间    1.0.8 --添加--
        sb_pb_commodity_main.append(" approvalNumber            varchar(30)                    DEFAULT(''), ");//批准文号    1.1.5 --添加--
        sb_pb_commodity_main.append(" overedThreshold           varchar(1)                     null,  	    ");//
        sb_pb_commodity_main.append(" showedRedPoint            varchar(1)                     null,  	    ");//小红点标识
        sb_pb_commodity_main.append(" priceLastUpdated          varchar(40)                    DEFAULT(''), ");//最后一次修改药品价格时间
        sb_pb_commodity_main.append(" commoditySource           varchar(40)                    DEFAULT(''), ");//药品来源 0或空为云诊室 1 合伙人
        sb_pb_commodity_main.append(" clinicId                  varchar(40)                    null,  	    ");//诊所id
        sb_pb_commodity_main.append(" flag                      varchar(1)                     null,		");//是否上传服务器
        sb_pb_commodity_main.append(" status                    varchar(40)                            null,  	    ");//删除状态
        sb_pb_commodity_main.append(" base_version              varchar(40)                    null,  	    ");//
        sb_pb_commodity_main.append(" level                     varchar(40)                    null,  	    ");//
        sb_pb_commodity_main.append(" threshold                 double                    null,  	    ");//
        sb_pb_commodity_main.append(" thresholdUnit             varchar(40)                    null,  	    ");//
        sb_pb_commodity_main.append(" defaultDosageUnit         varchar(40)                    null  	    ");//
        sb_pb_commodity_main.append(");");
        sqLiteDatabase.execSQL(sb_pb_commodity_main.toString());
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
