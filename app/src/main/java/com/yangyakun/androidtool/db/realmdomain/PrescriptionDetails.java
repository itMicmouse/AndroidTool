package com.yangyakun.androidtool.db.realmdomain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PrescriptionDetails extends RealmObject {
    @PrimaryKey
    private String id="id";
    private String commodityCategor="commodityCategor";
    private String diagnosisId="diagnosisId";
    private String commodityId="commodityId";
    private String showCommodityNam="showCommodityNam";
    private String groupId="groupId";
    private String useLevel="useLevel";
    private String useLevelUnit="useLevelUnit";
    private String frequency="frequency";
    private String usage="usage";
    private String batchId="batchId";
    private String validityDate="validityDate";
    private String priceUnit="priceUnit";
    private String totalNumber="totalNumber";
    private String receivablePrice="receivablePrice";
    private String commonUnit="commonUnit";
    private String minimumUnit="minimumUnit";
    private String isBlank="isBlank";
    private String isSetMethod="isSetMethod";
    private String orderNum="orderNum";
    private String base_version="base_version";
    private String is_delete="is_delete";
    private String clinicId="clinicId";
    private String specifcations="specifcations";
    private String docterOrder="docterOrder";
    private String dosageFormId="dosageFormId";
    private String dosage="dosage";
    private String unitType="unitType";
    private String dosageUnit="dosageUnit";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityCategor() {
        return commodityCategor;
    }

    public void setCommodityCategor(String commodityCategor) {
        this.commodityCategor = commodityCategor;
    }

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getShowCommodityNam() {
        return showCommodityNam;
    }

    public void setShowCommodityNam(String showCommodityNam) {
        this.showCommodityNam = showCommodityNam;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUseLevel() {
        return useLevel;
    }

    public void setUseLevel(String useLevel) {
        this.useLevel = useLevel;
    }

    public String getUseLevelUnit() {
        return useLevelUnit;
    }

    public void setUseLevelUnit(String useLevelUnit) {
        this.useLevelUnit = useLevelUnit;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getReceivablePrice() {
        return receivablePrice;
    }

    public void setReceivablePrice(String receivablePrice) {
        this.receivablePrice = receivablePrice;
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

    public String getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(String isBlank) {
        this.isBlank = isBlank;
    }

    public String getIsSetMethod() {
        return isSetMethod;
    }

    public void setIsSetMethod(String isSetMethod) {
        this.isSetMethod = isSetMethod;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getBase_version() {
        return base_version;
    }

    public void setBase_version(String base_version) {
        this.base_version = base_version;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getSpecifcations() {
        return specifcations;
    }

    public void setSpecifcations(String specifcations) {
        this.specifcations = specifcations;
    }

    public String getDocterOrder() {
        return docterOrder;
    }

    public void setDocterOrder(String docterOrder) {
        this.docterOrder = docterOrder;
    }

    public String getDosageFormId() {
        return dosageFormId;
    }

    public void setDosageFormId(String dosageFormId) {
        this.dosageFormId = dosageFormId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }
}
