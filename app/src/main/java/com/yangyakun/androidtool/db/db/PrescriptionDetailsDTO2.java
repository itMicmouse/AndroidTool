package com.yangyakun.androidtool.db.db;

import java.io.Serializable;
import java.util.UUID;

/**
 * @ClassName: PrescriptionDetailsDTO
 * @Description: TODO
 * @author yyz
 * @date 2015-11-11 下午12:50:31
 * 
 */
public class PrescriptionDetailsDTO2 implements Serializable {

	public PrescriptionDetailsDTO2() {
		this.id = UUID.randomUUID().toString();
		this.medDisId = "medDisId";
		this.clinicCommodityChineseId = "clinicCommodityChineseId";
		this.groupId = "groupId";
		this.showCommodityName = "showCommodityName";
		this.useLevel =" useLevel";
		this.useLevelUnit = "useLevelUnit";
		this.frequency = "frequency";
		this.usage = "usage";
		this.batchId = "batchId";
		this.totalNumber = "totalNumber";
		this.saleUnit =" saleUnit";
		this.orderNum =12;
		this.commodityCode =" commodityCode";
		this.price = "price";
		this.saleUnitPrice = "saleUnitPrice";
		this.commodityCategory = "commodityCategory";
		this.isBlank = 1;
		this.specifcations = "specifcations";
		this.docterOrder =" docterOrder";
		this.dosageFormId = "dosageFormId";
		this.dosage = "dosage";
		this.dosageUnit = "dosageUnit";
		this.unitType = "unitType";
		this.diagnosisId = "diagnosisId";
	}

	private String id = "";

	// 西药
	private String medDisId = "";

	// 中药
	private String clinicCommodityChineseId = "";

	// 分组
	private String groupId = "";

	// 是否显示药品名字
	private String showCommodityName = "";

	// 用量字典项 int
	private String useLevel = "";

	// 用量单位字典项
	private String useLevelUnit = "";

	// 频度字典项
	private String frequency = "";

	// 用法字典项
	private String usage = "";

	// 批次Id
	private String batchId = "";

	// 总量 int
	private String totalNumber = "";

	// 销售单位 common;min
	private String saleUnit = "";

	// 排序号
	private int orderNum = 0;

	// 诊所服务Id
	private String commodityCode = "";

	// 诊所服务价格
	private String price = "";

	private String saleUnitPrice = "";

	private String commodityCategory;

	private int isBlank;
	
	private String specifcations;

	private String docterOrder = "";

	private String dosageFormId="";

	private String dosage  = "";

	private String dosageUnit  = "";

	private String unitType;


	public int getIsBlank() {
		return isBlank;
	}

	public void setIsBlank(int isBlank) {
		this.isBlank = isBlank;
	}

	public String getCommodityCategory() {
		return commodityCategory;
	}

	public void setCommodityCategory(String commodityCategory) {
		this.commodityCategory = commodityCategory;
	}

	// 处方Id
	public String diagnosisId = "";

	public String getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getShowCommodityName() {
		return showCommodityName;
	}

	public void setShowCommodityName(String showCommodityName) {
		this.showCommodityName = showCommodityName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClinicCommodityChineseId() {
		return clinicCommodityChineseId;
	}

	public void setClinicCommodityChineseId(String clinicCommodityChineseId) {
		this.clinicCommodityChineseId = clinicCommodityChineseId;
	}

	public String getMedDisId() {
		return medDisId;
	}

	public void setMedDisId(String medDisId) {
		this.medDisId = medDisId;
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

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSaleUnitPrice() {
		return saleUnitPrice;
	}

	public void setSaleUnitPrice(String saleUnitPrice) {
		this.saleUnitPrice = saleUnitPrice;
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

	public String getDosageUnit() {
		return dosageUnit;
	}

	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
}
