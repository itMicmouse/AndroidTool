package com.yangyakun.androidtool.db.db;

import java.io.Serializable;
import java.util.UUID;

/**
 * 保存到服务器的处方主表
 *
 * @author yyz
 * @ClassName: PrescriptionMainDTO
 * @Description: TODO
 * @date 2015-11-24 下午8:35:54
 */
public class PrescriptionMainDTO implements Serializable {
    public PrescriptionMainDTO() {
        this.id = UUID.randomUUID().toString();
        this.doctorMainId = "doctorMainId";
        this.patientId = "patientId";
        this.diagnoseDate = "diagnoseDate";
        this.height = "height";
        this.temperature = "temperature";
        this.weight = "weight";
        this.systolicPressure = "systolicPressure";
        this.diastolicPressure = "diastolicPressure";
        this.age = "age";
        this.patientName = "patientName";
        this.sex = "sex";
        this.complaint = "complaint";
        this.illHistory = "illHistory";
        this.diagnose = "diagnose";
        this.taboo = "taboo";
        this.allergicHistory = "allergicHistory";
        this.status = "status";
        this.isPrint = "isPrint";
        this.medicineCost = "medicineCost";
        this.otherCost = "otherCost";
        this.flag = "flag";
        this.base_version = "base_version";
        this.regiFlag = "regiFlag";
        this.outpatientNo = "outpatientNo";
        this.payTime = "payTime";
        this.prescriptionFlag = "prescriptionFlag";
        this.debtCost = "debtCost";
        this.paidCost = "paidCost";
        this.pulse = "pulse";
        this.examOther = "examOther";
        this.bloodGlucose = "bloodGlucose";
        this.payType = "payType";
        this.is_delete = 1;
        this.lastUpdated = "lastUpdated";
        this.busiOrderNo = "busiOrderNo";
        this.onlinePayStatus =" onlinePayStatus";
        this.realName = "realName";
        this.homeAddress = "homeAddress";
        this.phone = "phone";
        this.invalidTime = "invalidTime";
        this.invalidSysUser = "invalidSysUser";
        this.invalidReason = "invalidReason";
    }

    private String id;
    private String doctorMainId;
    private String patientId;
    private String diagnoseDate;//诊断日期
    private String height;//身高
    private String temperature;//体温
    private String weight;//体重
    private String systolicPressure;//高血压
    private String diastolicPressure;//低血压
    private String age;//年龄	1.14--添加--
    private String patientName;//姓名 1.14--添加--
    private String sex;//性别 1.14--添加--

    private String complaint;//主诉
    private String illHistory;//病史
    private String diagnose;//诊断
    private String taboo;//商品禁忌
    private String allergicHistory;//过敏史
    private String status;//处方状态
    private String isPrint;//打印状态0:'未打印',1:'已打印'
    private String medicineCost;
    private String otherCost;//其它金额
    private String flag;//是否已经上传 0 没上传 1 上传了
    private String base_version;
    private String regiFlag;//是否挂号 1挂号
    private String outpatientNo;//门诊号
    private String payTime;//结算时间
    private String prescriptionFlag;//处方类型（1或""是西药，2是中药）1.0.9--添加--
    private String debtCost;//欠款金额1.0.9--添加--
    private String paidCost;//实收金额1.0.9--添加--
    private String pulse;//脉搏1.1.0--添加--
    private String examOther;//其它1.1.0--添加--
    private String bloodGlucose;//血糖1.1.1--添加--
    private String payType;//支付方式 1.1.1--添加--+  0:'现金',1:'医保卡',2:'微信',3:'支付宝',4:'银行卡',5:'其它'
    private int is_delete;
    private String lastUpdated;//最后修改时间
    private String busiOrderNo;//支付订单
    private String onlinePayStatus;//在线支付状态 1:待支付 2:支付成功 3:支付失败 3不可以修改处方
    private String realName;//医生真实姓名 1.1.7--添加--
    private String homeAddress;//电话 1.1.8--添加--
    private String phone;//地址 1.1.8--添加--
    private String invalidTime; //作废时间
    private String invalidSysUser; //操作人
    private String invalidReason; //作废原因

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(String diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(String systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public String getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(String diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getIllHistory() {
        return illHistory;
    }

    public void setIllHistory(String illHistory) {
        this.illHistory = illHistory;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public String getMedicineCost() {
        return medicineCost;
    }

    public void setMedicineCost(String medicineCost) {
        this.medicineCost = medicineCost;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getBase_version() {
        return base_version;
    }

    public void setBase_version(String base_version) {
        this.base_version = base_version;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getRegiFlag() {
        return regiFlag;
    }

    public void setRegiFlag(String regiFlag) {
        this.regiFlag = regiFlag;
    }

    public String getOutpatientNo() {
        return outpatientNo;
    }

    public void setOutpatientNo(String outpatientNo) {
        this.outpatientNo = outpatientNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPaidCost() {
        return paidCost;
    }

    public void setPaidCost(String paidCost) {
        this.paidCost = paidCost;
    }

    public String getDebtCost() {
        return debtCost;
    }

    public void setDebtCost(String debtCost) {
        this.debtCost = debtCost;
    }

    public String getPrescriptionFlag() {
        return prescriptionFlag;
    }

    public void setPrescriptionFlag(String prescriptionFlag) {
        this.prescriptionFlag = prescriptionFlag;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getExamOther() {
        return examOther;
    }

    public void setExamOther(String examOther) {
        this.examOther = examOther;
    }

    public String getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(String bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getBusiOrderNo() {
        return busiOrderNo;
    }

    public void setBusiOrderNo(String busiOrderNo) {
        this.busiOrderNo = busiOrderNo;
    }

    public String getOnlinePayStatus() {
        return onlinePayStatus;
    }

    public void setOnlinePayStatus(String onlinePayStatus) {
        this.onlinePayStatus = onlinePayStatus;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctorMainId() {
        return doctorMainId == null ? "" : doctorMainId;
    }

    public void setDoctorMainId(String doctorMainId) {
        this.doctorMainId = doctorMainId;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getInvalidSysUser() {
        return invalidSysUser;
    }

    public void setInvalidSysUser(String invalidSysUser) {
        this.invalidSysUser = invalidSysUser;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }
}
