package com.yangyakun.androidtool.db.realmdomain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Commondity extends RealmObject {
    @PrimaryKey
    private String id = "id";
    private String commodityCategory = "commodityCategory";
    private String commodityCode = "commodityCode";
    private String commodityName = "commodityName";
    private String commodityShortName = "commodityShortName";
    private String commonNum = "commonNum";
    private String simpleName = "simpleName";
    private String simpleShortName = "simpleShortName";
    private String typeId = "typeId";
    private String categoryId = "categoryId";
    private String dosageFormId = "dosageFormId";
    private String dosageUnitId = "dosageUnitId";
    private String dosage = "dosage";
    private String commonUnit = "commonUnit";
    private String minimumUnit = "minimumUnit";
    private String taboo = "taboo";
    private String direction = "direction";
    private String rate = "rate";
    private String quantum = "quantum";
    private String quantum_unit = "quantum_unit";
    private String specs = "specs";
    private String enterpriseName = "enterpriseName";
    private String conversionValue = "conversionValue";
    private String prescriptionPrice1 = "prescriptionPrice1";
    private String prescriptionPrice2 = "prescriptionPrice2";
    private String prescriptionNum1 = "prescriptionNum1";
    private String prescriptionNum2 = "prescriptionNum2";
    private String remark = "remark";
    private String medicinalCatagory = "medicinalCatagory";
    private String natureSmell = "natureSmell";
    private String medicinalEffectLocation = "medicinalEffectLoca";
    private String medicinalEffect = "medicinalEffect";
    private String indication = "indication";
    private String announcements = "announcements";
    private String defaultDosage = "defaultDosage";
    private String activeTime = "activeTime";
    private String approvalNumber = "approvalNumber";
    private String overedThreshold = "overedThreshold";
    private String showedRedPoint = "showedRedPoint";
    private String priceLastUpdated = "priceLastUpdated";
    private String commoditySource = "commoditySource";
    private String clinicId = "clinicId";
    private String flag = "flag";
    private String status = "status";
    private String base_version = "base_version";
    private String level = "level";
    private String threshold = "threshold";
    private String thresholdUnit = "thresholdUnit";
    private String defaultDosageUnit = "defaultDosageUnit";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityCategory() {
        return commodityCategory;
    }

    public void setCommodityCategory(String commodityCategory) {
        this.commodityCategory = commodityCategory;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityShortName() {
        return commodityShortName;
    }

    public void setCommodityShortName(String commodityShortName) {
        this.commodityShortName = commodityShortName;
    }

    public String getCommonNum() {
        return commonNum;
    }

    public void setCommonNum(String commonNum) {
        this.commonNum = commonNum;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getSimpleShortName() {
        return simpleShortName;
    }

    public void setSimpleShortName(String simpleShortName) {
        this.simpleShortName = simpleShortName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDosageFormId() {
        return dosageFormId;
    }

    public void setDosageFormId(String dosageFormId) {
        this.dosageFormId = dosageFormId;
    }

    public String getDosageUnitId() {
        return dosageUnitId;
    }

    public void setDosageUnitId(String dosageUnitId) {
        this.dosageUnitId = dosageUnitId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getCommonUnit() {
        return commonUnit;
    }

    public void setCommonUnit(String commonUnit) {
        this.commonUnit = commonUnit;
    }

    public String getMinimumUnit() {
        return minimumUnit;
    }

    public void setMinimumUnit(String minimumUnit) {
        this.minimumUnit = minimumUnit;
    }

    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQuantum() {
        return quantum;
    }

    public void setQuantum(String quantum) {
        this.quantum = quantum;
    }

    public String getQuantum_unit() {
        return quantum_unit;
    }

    public void setQuantum_unit(String quantum_unit) {
        this.quantum_unit = quantum_unit;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(String conversionValue) {
        this.conversionValue = conversionValue;
    }

    public String getPrescriptionPrice1() {
        return prescriptionPrice1;
    }

    public void setPrescriptionPrice1(String prescriptionPrice1) {
        this.prescriptionPrice1 = prescriptionPrice1;
    }

    public String getPrescriptionPrice2() {
        return prescriptionPrice2;
    }

    public void setPrescriptionPrice2(String prescriptionPrice2) {
        this.prescriptionPrice2 = prescriptionPrice2;
    }

    public String getPrescriptionNum1() {
        return prescriptionNum1;
    }

    public void setPrescriptionNum1(String prescriptionNum1) {
        this.prescriptionNum1 = prescriptionNum1;
    }

    public String getPrescriptionNum2() {
        return prescriptionNum2;
    }

    public void setPrescriptionNum2(String prescriptionNum2) {
        this.prescriptionNum2 = prescriptionNum2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMedicinalCatagory() {
        return medicinalCatagory;
    }

    public void setMedicinalCatagory(String medicinalCatagory) {
        this.medicinalCatagory = medicinalCatagory;
    }

    public String getNatureSmell() {
        return natureSmell;
    }

    public void setNatureSmell(String natureSmell) {
        this.natureSmell = natureSmell;
    }

    public String getMedicinalEffectLocation() {
        return medicinalEffectLocation;
    }

    public void setMedicinalEffectLocation(String medicinalEffectLocation) {
        this.medicinalEffectLocation = medicinalEffectLocation;
    }

    public String getMedicinalEffect() {
        return medicinalEffect;
    }

    public void setMedicinalEffect(String medicinalEffect) {
        this.medicinalEffect = medicinalEffect;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public String getDefaultDosage() {
        return defaultDosage;
    }

    public void setDefaultDosage(String defaultDosage) {
        this.defaultDosage = defaultDosage;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getOveredThreshold() {
        return overedThreshold;
    }

    public void setOveredThreshold(String overedThreshold) {
        this.overedThreshold = overedThreshold;
    }

    public String getShowedRedPoint() {
        return showedRedPoint;
    }

    public void setShowedRedPoint(String showedRedPoint) {
        this.showedRedPoint = showedRedPoint;
    }

    public String getPriceLastUpdated() {
        return priceLastUpdated;
    }

    public void setPriceLastUpdated(String priceLastUpdated) {
        this.priceLastUpdated = priceLastUpdated;
    }

    public String getCommoditySource() {
        return commoditySource;
    }

    public void setCommoditySource(String commoditySource) {
        this.commoditySource = commoditySource;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBase_version() {
        return base_version;
    }

    public void setBase_version(String base_version) {
        this.base_version = base_version;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getThresholdUnit() {
        return thresholdUnit;
    }

    public void setThresholdUnit(String thresholdUnit) {
        this.thresholdUnit = thresholdUnit;
    }

    public String getDefaultDosageUnit() {
        return defaultDosageUnit;
    }

    public void setDefaultDosageUnit(String defaultDosageUnit) {
        this.defaultDosageUnit = defaultDosageUnit;
    }
}
