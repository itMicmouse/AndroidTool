package com.yangyakun.androidtool.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuiYanBing on 2018/4/13 16:06
 * E-Mail Address：guiyanbing@zhiyihealth.com.cn
 */

public class DoctorsInterface {
    /**
     * doctorId : d1
     * doctorName : 张1
     * introduce : 北京陆军总医院 主任医师
     * qrcodeUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523610955157&di=4190d4617fafe67dc39a038522fdbfe3&imgtype=0&src=http://www.asiadancing.com/images/aHR0cDovL3BpYy5iYWlrZS5zb3NvLmNvbS9wLzIwMTMxMjExLzIwMTMxMjExMDkxNzUyLTM5MzY2OTAzNy5qcGc=.jpg
     * currentPatient : {"patientName":"patient1","patientNo":"no1"}
     * waitPatient : [{"patientName":"patient2","patientNo":"no2"},{"patientName":"patient3","patientNo":"no3"},{"patientName":"patient4","patientNo":"no4"},{"patientName":"patient5","patientNo":"no5"}]
     */
    private String doctorId;
    private String doctorName;
    private String introduce;
    private String qrcodeUrl;
    private String aliasName;

    private int operationType;

    private CurrentPatientBean currentPatient;
    private List<WaitPatientBean> waitPatient = new ArrayList<>();

    public int getOperationType() {
        return operationType;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public CurrentPatientBean getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(CurrentPatientBean currentPatient) {
        this.currentPatient = currentPatient;
    }

    public List<WaitPatientBean> getWaitPatient() {
        return waitPatient;
    }

    public void setWaitPatient(List<WaitPatientBean> waitPatient) {
        this.waitPatient = waitPatient;
    }

    public static class CurrentPatientBean {
        /**
         * patientName : patient1
         * patientNo : no1
         */

        private String patientName;
        private String patientNo;
        private String subscribeId;

        public String getSubscribeId() {
            return subscribeId;
        }

        public void setSubscribeId(String subscribeId) {
            this.subscribeId = subscribeId;
        }

        public String getPatientName() {
            return patientName;

        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientNo() {
            return patientNo;
        }

        public void setPatientNo(String patientNo) {
            this.patientNo = patientNo;
        }
    }

    public static class WaitPatientBean {
        /**
         * patientName : patient2
         * patientNo : no2
         */

        private String patientName;
        private String patientNo;
        private String subscribeId;

        public String getSubscribeId() {
            return subscribeId;
        }

        public void setSubscribeId(String subscribeId) {
            this.subscribeId = subscribeId;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientNo() {
            return patientNo;
        }

        public void setPatientNo(String patientNo) {
            this.patientNo = patientNo;
        }
    }
}
