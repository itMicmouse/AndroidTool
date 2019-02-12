package com.yangyakun.androidtool.db.realmdomain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PrescriptionMain extends RealmObject {
    @PrimaryKey
    private String id = "id";
    private String patientId = "patientId";
    private String regiId = "regiId";
    private String doctorMainId = "doctorMainId";
    private String diagnoseDate = "diagnoseDate";
    private String height = "height";
    private String temperature = "temperature";
    private String weight = "weight";
    private String systolicPressure = "systolicPressure";
    private String diastolicPressure = "diastolicPressure";
    private String complaint = "complaint";
    private String illHistory = "illHistory";
    private String diagnose = "diagnose";
    private String taboo = "taboo";
    private String allergicHistory = "allergicHistory";
    private String diagnosisStatus = "diagnosisStatus";
    private String medDisCost = "medDisCost";
    private String otherCost = "otherCost";
    private String isPrint = "isPrint";
    private String regiFlag = "regiFlag		 	";
    private String clinicId = "clinicId";
    private String outpatient_no = "outpatient_no";
    private String pay_time = "pay_time";
    private String prescriptionFlag = "prescriptionFlag";
    private String debtCost = "debtCost";
    private String paidCost = "paidCost";
    private String pulse = "pulse";
    private String examOther = "examOther";
    private String bloodGlucose = "bloodGlucose";
    private String payType = "payType";
    private String patientName = "patientName";
    private String sex = "sex";
    private String age = "age";
    private String homeAddress = "homeAddress";
    private String phone = "phone";
    private String lastUpdated = "lastUpdated";
    private String busiOrderNo = "busiOrderNo";
    private String onlinePayStatus = "onlinePayStatus";
    private String realName = "realName";
    private String flag = "flag";
    private String is_delete = "is_delete";
    private String base_version = "base_version";
    private String invalidTime = "invalidTime";
    private String invalidSysUser = "invalidSysUser";
    private String invalidReason = "invalidReason";

    private RealmList<PrescriptionDetails> prescriptionDetailsRealmList;

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

    public String getRegiId() {
        return regiId;
    }

    public void setRegiId(String regiId) {
        this.regiId = regiId;
    }

    public String getDoctorMainId() {
        return doctorMainId;
    }

    public void setDoctorMainId(String doctorMainId) {
        this.doctorMainId = doctorMainId;
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

    public String getDiagnosisStatus() {
        return diagnosisStatus;
    }

    public void setDiagnosisStatus(String diagnosisStatus) {
        this.diagnosisStatus = diagnosisStatus;
    }

    public String getMedDisCost() {
        return medDisCost;
    }

    public void setMedDisCost(String medDisCost) {
        this.medDisCost = medDisCost;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public String getRegiFlag() {
        return regiFlag;
    }

    public void setRegiFlag(String regiFlag) {
        this.regiFlag = regiFlag;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getOutpatient_no() {
        return outpatient_no;
    }

    public void setOutpatient_no(String outpatient_no) {
        this.outpatient_no = outpatient_no;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getPrescriptionFlag() {
        return prescriptionFlag;
    }

    public void setPrescriptionFlag(String prescriptionFlag) {
        this.prescriptionFlag = prescriptionFlag;
    }

    public String getDebtCost() {
        return debtCost;
    }

    public void setDebtCost(String debtCost) {
        this.debtCost = debtCost;
    }

    public String getPaidCost() {
        return paidCost;
    }

    public void setPaidCost(String paidCost) {
        this.paidCost = paidCost;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getBase_version() {
        return base_version;
    }

    public void setBase_version(String base_version) {
        this.base_version = base_version;
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

    public RealmList<PrescriptionDetails> getPrescriptionDetailsRealmList() {
        return prescriptionDetailsRealmList;
    }

    public void setPrescriptionDetailsRealmList(RealmList<PrescriptionDetails> prescriptionDetailsRealmList) {
        this.prescriptionDetailsRealmList = prescriptionDetailsRealmList;
    }
}
