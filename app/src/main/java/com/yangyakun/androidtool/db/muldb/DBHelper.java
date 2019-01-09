package com.yangyakun.androidtool.db.muldb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yangyakun.androidtool.base.SQLiteSDCardHelper;
import com.yangyakun.androidtool.utils.DbUpdateHelper;


public class DBHelper extends SQLiteSDCardHelper {

    public static final String NAME = "name.db";
    private static final int VERSION = 15;

    public static final String T_ID = "id";

    public DBHelper(Context context) {
        super(context, NAME, context.getExternalFilesDir("").getAbsolutePath(), VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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
        sb_pb_patient.append("   totalArrears         double                            DEFAULT(0), ");
        sb_pb_patient.append("   status               int                            null,		 ");
        sb_pb_patient.append("   clinicId             varchar(40)                    null, 		 ");
        sb_pb_patient.append("   idCardNo             varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   agency               varchar(800)                   DEFAULT(''),");
        sb_pb_patient.append("   folk                 varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   fingerPrint          varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   validitytime         varchar(800)                   DEFAULT(''),");
        sb_pb_patient.append("   diagnose             varchar(800)                   DEFAULT(''),");
        sb_pb_patient.append("   deviceType           varchar(40)                    DEFAULT(''),");
        sb_pb_patient.append("   isDelete             varchar(40)                    null,		 ");//0未删 1已删
        sb_pb_patient.append("   flag                 varchar(1)                     null,		 ");//是否上传服务器
        sb_pb_patient.append("   patientSource        varchar(40)                    null,		 ");
        sb_pb_patient.append("   base_version         varchar(40)                    null 		 ");

        sb_pb_patient.append(");");
        db.execSQL(sb_pb_patient.toString());
        //endregion

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
        db.execSQL(sb_pb_commodity_main.toString());
        //endregion

        //region 处方附表
        StringBuilder pb_prescription_details = new StringBuilder();
        pb_prescription_details.append(" create table pb_prescription_details ");
        pb_prescription_details.append(" ( ");
        pb_prescription_details.append(" 		   id                   varchar(40)                    primary key, ");//主键id
        pb_prescription_details.append(" 		   commodityCategory    varchar(40)                    not null,    ");//商品类型
        pb_prescription_details.append(" 		   diagnosisId          varchar(40)                    not null,    ");//处方主表id
        pb_prescription_details.append(" 		   commodityId          varchar(40)                    not null,    ");//商品id
        pb_prescription_details.append(" 		   showCommodityName    varchar(40)                    null, 	    ");//商品名称
        pb_prescription_details.append(" 		   groupId              int                            null, 	    ");//组别
        pb_prescription_details.append(" 		   useLevel             varchar(40)                    null, 	    ");//用量字典项
        pb_prescription_details.append(" 		   useLevelUnit         varchar(40)                    null, 	    ");//用量单位
        pb_prescription_details.append(" 		   frequency            varchar(40)                    null, 	    ");//频度
        pb_prescription_details.append(" 		   usage                varchar(40)                    null, 	    ");//用法
        pb_prescription_details.append(" 		   batchId              varchar(40)                    null, 	    ");//批次id
        pb_prescription_details.append(" 		   validityDate         date                           null, 	    ");//有效日期
        pb_prescription_details.append(" 		   priceUnit            varchar(40)                    null, 	    ");//价格单位
        pb_prescription_details.append(" 		   totalNumber          varchar(40)                    null, 	    ");//总量
        pb_prescription_details.append(" 		   receivablePrice      varchar(40)                    null,        ");//售出单价
        pb_prescription_details.append(" 		   commonUnit           varchar(40)                    null, 	    ");//常用单位
        pb_prescription_details.append(" 		   minimumUnit          varchar(40)                    null, 	    ");//最小单位
        pb_prescription_details.append(" 		   isBlank    			int                    	   	   null, 	    ");//是否医嘱(根据登陆有没有药品id判断0不是医嘱 1是医嘱)
        pb_prescription_details.append(" 		   isSetMethod 			int                    	   	   null, 	    ");//暂未使用
        pb_prescription_details.append(" 		   orderNum		 		int                    	   	   null, 	    ");//序号
        pb_prescription_details.append(" 		   base_version 		varchar(40)                    null, 	    ");
        pb_prescription_details.append(" 		   is_delete		 	int                    	   	   null, 	    ");
        pb_prescription_details.append(" 		   clinicId         	varchar(40)                    null,	    ");//诊所id
        pb_prescription_details.append(" 		   specifcations        varchar(50)                    null,	    ");//规格     1.0.6 --添加--
        pb_prescription_details.append(" 		   docterOrder          varchar(50)                    DEFAULT '',	");//医嘱     1.0.8 --添加--
        pb_prescription_details.append(" 		   dosageFormId         varchar(50)                    DEFAULT '', 	");//注射液标识1.0.8 --添加--
        pb_prescription_details.append(" 		   dosage               varchar(50)                    DEFAULT '', 	");//注射液剂量1.1.0 --添加--
        pb_prescription_details.append(" 		   unitType             varchar(50)                    DEFAULT '', 	");//卖药类别1.1.2 --添加--
        pb_prescription_details.append(" 		   dosageUnit           varchar(50)                    DEFAULT '' 	");//注射液剂量单位1.1.0 --添加--
        pb_prescription_details.append(" ); ");
        db.execSQL(pb_prescription_details.toString());
        //endregion

        //region 处方主表
        StringBuilder sb_pb_prescription_main = new StringBuilder();
        sb_pb_prescription_main.append("create TABLE pb_prescription_main ");
        sb_pb_prescription_main.append("(");
        sb_pb_prescription_main.append(" id                   varchar(40)                    primary key, ");
        sb_pb_prescription_main.append(" patientId            varchar(40)                    not null,    ");
        sb_pb_prescription_main.append(" regiId               varchar(40)                    null,        ");
        sb_pb_prescription_main.append(" doctorMainId         varchar(40)                 not null,    "); //医生id
        sb_pb_prescription_main.append(" diagnoseDate         datetime                       not null,    ");
        sb_pb_prescription_main.append(" height               decimal(3,1)                   null,        ");
        sb_pb_prescription_main.append(" temperature          varchar                        null,        ");
        sb_pb_prescription_main.append(" weight               varchar(20)                    null,        ");
        sb_pb_prescription_main.append(" systolicPressure     int                            null,        ");
        sb_pb_prescription_main.append(" diastolicPressure    int                            null,        ");
        sb_pb_prescription_main.append(" complaint            varchar(400)                   null,        ");
        sb_pb_prescription_main.append(" illHistory           varchar(400)                   null,        ");
        sb_pb_prescription_main.append(" diagnose             varchar(400)                   null,  	  ");
        sb_pb_prescription_main.append(" taboo                varchar(400)                   null,        ");
        sb_pb_prescription_main.append(" allergicHistory      varchar(400)                   null,        ");
        sb_pb_prescription_main.append(" diagnosisStatus      varchar(40)                    null,        ");
        sb_pb_prescription_main.append(" medDisCost           varchar(40)                    null,        ");
        sb_pb_prescription_main.append(" otherCost            varchar(40)                    null,        ");
        sb_pb_prescription_main.append(" isPrint              varchar(1)                     null,        ");
        sb_pb_prescription_main.append(" regiFlag		 	  varchar(40)                    null, 		  ");
        sb_pb_prescription_main.append(" clinicId             varchar(40)                    null,		  ");
        sb_pb_prescription_main.append(" outpatient_no        varchar(20)                    null,		  ");//门诊号1.0.6--添加--
        sb_pb_prescription_main.append(" pay_time             datetime                       null,		  ");//处方时间1.0.6--添加--
        sb_pb_prescription_main.append(" prescriptionFlag     varchar(40)                    null,		  ");//处方类型（1或""是西药，2是中药）1.0.9--添加--
        sb_pb_prescription_main.append(" debtCost             varchar(40)                    null,		  ");//欠款金额1.0.9--添加--
        sb_pb_prescription_main.append(" paidCost             varchar(40)                    null,		  ");//实收金额1.0.9--添加--
        sb_pb_prescription_main.append(" pulse                varchar(40)                    null,		  ");//脉搏1.1.0--添加--
        sb_pb_prescription_main.append(" examOther            varchar(40)                    null,		  ");//其它1.1.0--添加--
        sb_pb_prescription_main.append(" bloodGlucose         varchar(20)                    DEFAULT '',  ");//血糖 1.1.1--添加--+
        sb_pb_prescription_main.append(" payType              varchar(1)                     DEFAULT('0'),");//支付方式 1.1.1--添加--+  0:'现金',1:'医保卡',2:'微信',3:'支付宝',4:'银行卡',5:'其它'
        sb_pb_prescription_main.append(" patientName          varchar(20)                    null,	      ");//患者姓名 1.1.4--添加--
        sb_pb_prescription_main.append(" sex                  varchar(1)                     null,		  ");//性别 1.1.4--添加--
        sb_pb_prescription_main.append(" age                  varchar(40)                    null,	      ");//年龄 1.1.4--添加--
        sb_pb_prescription_main.append(" homeAddress          varchar(40)                    null,	      ");//地址 1.1.8--添加--
        sb_pb_prescription_main.append(" phone                varchar(40)                    null,	      ");//电话 1.1.8--添加--
        sb_pb_prescription_main.append(" lastUpdated          varchar(40)                    DEFAULT '',  ");//最后更新时间 1.1.5--添加--
        sb_pb_prescription_main.append(" busiOrderNo          varchar(50)                    DEFAULT '',  ");//支付订单 1.1.7--添加--
        sb_pb_prescription_main.append(" onlinePayStatus      varchar(1)                     DEFAULT '',  ");//在线支付状态 1:待支付 2:支付成功 3:支付失败 3不可以修改处方 1.1.7--添加--
        sb_pb_prescription_main.append(" realName             varchar(20)                    DEFAULT '',  ");//医生真实姓名 1.1.7--添加--
        sb_pb_prescription_main.append(" flag                 varchar(1)                     null,        ");//上传标识,是否上传 117启用
        sb_pb_prescription_main.append(" is_delete            int                            DEFAULT 0,        ");
        sb_pb_prescription_main.append(" base_version         varchar(40)                    null,        ");
        sb_pb_prescription_main.append(" invalidTime          varchar(200)                   null,        ");
        sb_pb_prescription_main.append(" invalidSysUser       varchar(200)                   null,        ");
        sb_pb_prescription_main.append(" invalidReason        varchar(200)                   null         ");
        sb_pb_prescription_main.append(");");
        db.execSQL(sb_pb_prescription_main.toString());
        //endregion

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
        db.execSQL(sb_sys_label_main_str.toString());
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
        db.execSQL(sb_sys_label_details_str.toString());
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
