package com.yangyakun.androidtool.db.realmdomain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SysLabelMain extends RealmObject {
    @PrimaryKey
    private String id = "id";
    private String clinicId = "clinicId";
    private String name = "name";
    private String code = "code";
    private String parentId = "parentId";
    private String sort = "sort";
    private String status = "status";
    private String base_version = "base_version";
    private RealmList<SysLabelDetails> sysLabelDetailsRealmList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public RealmList<SysLabelDetails> getSysLabelDetailsRealmList() {
        return sysLabelDetailsRealmList;
    }

    public void setSysLabelDetailsRealmList(RealmList<SysLabelDetails> sysLabelDetailsRealmList) {
        this.sysLabelDetailsRealmList = sysLabelDetailsRealmList;
    }
}
