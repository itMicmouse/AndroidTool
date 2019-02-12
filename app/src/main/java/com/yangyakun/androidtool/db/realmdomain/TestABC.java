package com.yangyakun.androidtool.db.realmdomain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TestABC extends RealmObject {
    @PrimaryKey
    private String id = "id                 ";
    private String commodityCategory = "commodityCategory  ";
    private String commodityCode = "commodityCode      ";
    private String commodityName = "commodityName      ";
    private String commodityShortName = "commodityShortName ";
    private String commonNum = "commonNum	         ";
    private String simpleName = "simpleName         ";
    private String simpleShortName = "simpleShortName    ";
    private String typeId = "typeId             ";
    private String categoryId = "categoryId         ";

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
}
