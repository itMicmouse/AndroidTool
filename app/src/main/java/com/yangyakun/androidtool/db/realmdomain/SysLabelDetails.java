package com.yangyakun.androidtool.db.realmdomain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SysLabelDetails extends RealmObject {
    @PrimaryKey
    private String id = "id";
    private String clinicId = "clinicId";
    private String labelId = "labelId";
    private String groupObjId = "groupObjId";
    private String sort = "sort";
    private String flag = "flag";
    private String status = "status";
    private String base_version = "base_version";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getGroupObjId() {
        return groupObjId;
    }

    public void setGroupObjId(String groupObjId) {
        this.groupObjId = groupObjId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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
}
