package com.yangyakun.androidtool.db.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.yangyakun.androidtool.db.dbmanage.PbPatientDBManager;

import java.util.Random;
import java.util.UUID;

public class Patient {
    public void doMain(int magnitude) {
        SQLiteDatabase db = null;
        try {
            db = PbPatientDBManager.getInstance().openDatabase();
//            db.beginTransaction();
            String sql_insert = " insert into pb_patient (id, userName, userShortName, birthday, sex, phone, isDelete, illHistory, "
                    + " allergicHistory, address, totalArrears, flag, base_version, status, clinicId ,idCardNo ,agency ,folk,validitytime ,fingerPrint,deviceType,diagnose,patientSource) values " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            SQLiteStatement stat_insert = db.compileStatement(sql_insert);
            for (int i = 0; i < magnitude; i++) {
                stat_insert.bindString(1, UUID.randomUUID().toString());
                stat_insert.bindString(2, getName());
                stat_insert.bindString(3, "userShortName");
                stat_insert.bindString(4, "birthday");
                stat_insert.bindString(5, "1");
                stat_insert.bindString(6, "phone");
                stat_insert.bindString(7, "isDelete");
                stat_insert.bindString(8, "illHistory");
                stat_insert.bindString(9, "allergicHistory");
                stat_insert.bindString(10, "");
                stat_insert.bindString(11, "123");
                stat_insert.bindString(12, "1");
                stat_insert.bindString(13, "12554752");
                stat_insert.bindString(14, "1");
                stat_insert.bindString(15, "");
                stat_insert.bindString(16, "idCardNo");
                stat_insert.bindString(17, "agency");
                stat_insert.bindString(18, "folk");
                stat_insert.bindString(19, "validitytime");
                stat_insert.bindString(20, "fingerPrint");
                stat_insert.bindString(21, "deviceType");
                stat_insert.bindString(22, "diagnose");
                stat_insert.bindString(23, "patientSource");
                stat_insert.executeInsert();
                System.out.println("添加患者:"+i);
            }
//            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
//                db.endTransaction();
                PbPatientDBManager.getInstance().closeDatabase();
            }
        }
    }

    String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
            "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
            "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
            "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
    String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

    public String getName() {
        Random random = new Random();
        int index = random.nextInt(Surname.length - 1);
        //获得一个随机的姓氏
        String name = Surname[index];
        //可以根据这个数设置产生的男女比例
        int i = random.nextInt(3);
        if (i == 2) {
            int j = random.nextInt(girl.length() - 2);
            if (j % 2 == 0) {
                name = name + girl.substring(j, j + 2);
            } else {
                name = name + girl.substring(j, j + 1);
            }

        } else {
            int j = random.nextInt(girl.length() - 2);
            if (j % 2 == 0) {
                name = name + boy.substring(j, j + 2);
            } else {
                name = name + boy.substring(j, j + 1);
            }
        }
        return name;
    }
}
