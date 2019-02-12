package com.yangyakun.androidtool.db.realmdomain;

import java.util.Random;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Patitent extends RealmObject {
    @PrimaryKey
    private String id = "id";
    private String userName = "userName";
    private String userShortName = "userShortName";
    private String birthday = "birthday";
    private String sex = "sex";
    private String phone = "phone";
    private String illHistory = "illHistory";
    private String allergicHistory = "allergicHistory";
    private String address = "address";
    private String totalArrears = "totalArrears";
    private String status = "status";
    private String clinicId = "clinicId";
    private String idCardNo = "idCardNo";
    private String agency = "agency";
    private String folk = "folk";
    private String fingerPrint = "fingerPrint";
    private String validitytime = "validitytime";
    private String diagnose = "diagnose";
    private String deviceType = "deviceType";
    private String isDelete = "isDelete";
    private String flag = "flag";
    private String patientSource = "patientSource";
    private String base_version = "base_version";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserShortName() {
        return userShortName;
    }

    public void setUserShortName(String userShortName) {
        this.userShortName = userShortName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIllHistory() {
        return illHistory;
    }

    public void setIllHistory(String illHistory) {
        this.illHistory = illHistory;
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalArrears() {
        return totalArrears;
    }

    public void setTotalArrears(String totalArrears) {
        this.totalArrears = totalArrears;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getValiditytime() {
        return validitytime;
    }

    public void setValiditytime(String validitytime) {
        this.validitytime = validitytime;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPatientSource() {
        return patientSource;
    }

    public void setPatientSource(String patientSource) {
        this.patientSource = patientSource;
    }

    public String getBase_version() {
        return base_version;
    }

    public void setBase_version(String base_version) {
        this.base_version = base_version;
    }

    @Ignore
    String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
            "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
            "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
            "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
    @Ignore
    String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
    @Ignore
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
