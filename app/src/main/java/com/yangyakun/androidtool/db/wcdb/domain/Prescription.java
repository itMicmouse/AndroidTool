package com.yangyakun.androidtool.db.wcdb.domain;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteStatement;

import com.yangyakun.androidtool.db.db.PrescriptionDetailsDTO2;
import com.yangyakun.androidtool.db.db.PrescriptionMainDTO;
import com.yangyakun.androidtool.db.wcdb.dbmanage.PbPrescriptionDBManager;

public class Prescription {
    public void doMain(int magnitude, int number) {
        SQLiteDatabase db = null;
        try {

            db = PbPrescriptionDBManager.getInstance().openDatabase();
//            db.beginTransaction();
            // 主表
            String sql_insert = " replace into pb_prescription_main (id ,patientId, diagnoseDate, height, temperature, weight, systolicPressure, diastolicPressure, complaint, illHistory, diagnose, " +
                    "taboo, allergicHistory, diagnosisStatus, medDisCost, otherCost, flag, isPrint, base_version, is_delete, clinicId ,regiFlag, outpatient_no, pay_time, prescriptionFlag, debtCost, " +
                    "paidCost, pulse, examOther, bloodGlucose, payType, age, patientName, sex, lastUpdated, busiOrderNo, onlinePayStatus, realName, homeAddress, phone,doctorMainId,invalidTime,invalidSysUser,invalidReason,is_delete) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            SQLiteStatement stat_insert = db.compileStatement(sql_insert);
            PrescriptionMainDTO prescriptionMainDTO;
            for (int i = 0; i < magnitude; i++) {
                prescriptionMainDTO = new PrescriptionMainDTO();
                stat_insert.bindString(1, prescriptionMainDTO.getId());
                stat_insert.bindString(2, prescriptionMainDTO.getPatientId() != null ? prescriptionMainDTO.getPatientId() : "");
                stat_insert.bindString(3, prescriptionMainDTO.getDiagnoseDate() != null ? prescriptionMainDTO.getDiagnoseDate() : "");
                stat_insert.bindString(4, prescriptionMainDTO.getHeight() != null ? prescriptionMainDTO.getHeight() : "");
                stat_insert.bindString(5, prescriptionMainDTO.getTemperature() != null ? prescriptionMainDTO.getTemperature() : "");
                stat_insert.bindString(6, prescriptionMainDTO.getWeight() != null ? prescriptionMainDTO.getWeight() : "");
                stat_insert.bindString(7, prescriptionMainDTO.getSystolicPressure() != null ? prescriptionMainDTO.getSystolicPressure() : "");
                stat_insert.bindString(8, prescriptionMainDTO.getDiastolicPressure() != null ? prescriptionMainDTO.getDiastolicPressure() : "");
                stat_insert.bindString(9, prescriptionMainDTO.getComplaint() != null ? prescriptionMainDTO.getComplaint() : "");
                stat_insert.bindString(10, prescriptionMainDTO.getIllHistory() != null ? prescriptionMainDTO.getIllHistory() : "");
                stat_insert.bindString(11, prescriptionMainDTO.getDiagnose() != null ? prescriptionMainDTO.getDiagnose() : "");
                stat_insert.bindString(12, prescriptionMainDTO.getTaboo() != null ? prescriptionMainDTO.getTaboo() : "");
                stat_insert.bindString(13, prescriptionMainDTO.getAllergicHistory() != null ? prescriptionMainDTO.getAllergicHistory() : "");
                stat_insert.bindString(14, prescriptionMainDTO.getStatus() != null ? prescriptionMainDTO.getStatus() : "");
                stat_insert.bindString(15, prescriptionMainDTO.getMedicineCost() != null ? prescriptionMainDTO.getMedicineCost() : "");
                stat_insert.bindString(16, prescriptionMainDTO.getOtherCost() != null ? prescriptionMainDTO.getOtherCost() : "");
                stat_insert.bindString(17, "1");
                stat_insert.bindString(18, prescriptionMainDTO.getIsPrint() != null ? prescriptionMainDTO.getIsPrint() : "");
                stat_insert.bindString(19, prescriptionMainDTO.getBase_version() != null ? prescriptionMainDTO.getBase_version() : "");
                //当处方状态为作废（3）时，默认为删除状态因为客户端不显示
                stat_insert.bindString(20, String.valueOf(prescriptionMainDTO.getIs_delete()));
                stat_insert.bindString(21, "clinicId");
                stat_insert.bindString(22, prescriptionMainDTO.getRegiFlag() != null ? prescriptionMainDTO.getRegiFlag() : "");
                stat_insert.bindString(23, prescriptionMainDTO.getOutpatientNo() != null ? prescriptionMainDTO.getOutpatientNo() : "");//门诊号 1.0.6--添加--
                stat_insert.bindString(24, prescriptionMainDTO.getPayTime() != null ? prescriptionMainDTO.getPayTime() : "");//处方时间 1.0.6--添加--
                stat_insert.bindString(25, prescriptionMainDTO.getPrescriptionFlag() != null ? prescriptionMainDTO.getPrescriptionFlag() : "");//处方类型（1或""是西药，2是中药）1.0.9--添加--
                stat_insert.bindString(26, prescriptionMainDTO.getDebtCost() != null ? prescriptionMainDTO.getDebtCost() : "");//欠款金额 1.0.9--添加--
                stat_insert.bindString(27, prescriptionMainDTO.getPaidCost() != null ? prescriptionMainDTO.getPaidCost() : "");//实收金额 1.0.9--添加--
                stat_insert.bindString(28, prescriptionMainDTO.getPulse() != null ? prescriptionMainDTO.getPulse() : "");//脉搏 1.1.0--添加--
                stat_insert.bindString(29, prescriptionMainDTO.getExamOther() != null ? prescriptionMainDTO.getExamOther() : "");//其它 1.1.0--添加--
                stat_insert.bindString(30, prescriptionMainDTO.getBloodGlucose() != null ? prescriptionMainDTO.getBloodGlucose() : "");//血糖 1.1.1--添加--
                stat_insert.bindString(31, prescriptionMainDTO.getPayType() != null ? prescriptionMainDTO.getPayType() : "");//支付方式 1.1.1--添加--
                stat_insert.bindString(32, prescriptionMainDTO.getAge() != null ? prescriptionMainDTO.getAge() : "");//年龄 1.1.4--添加--
                stat_insert.bindString(33, prescriptionMainDTO.getPatientName() != null ? prescriptionMainDTO.getPatientName() : "");//名称 1.1.4--添加--
                stat_insert.bindString(34, prescriptionMainDTO.getSex() != null ? prescriptionMainDTO.getSex() : "");//性别 1.1.4--添加--
                stat_insert.bindString(35, prescriptionMainDTO.getLastUpdated() != null ? prescriptionMainDTO.getLastUpdated() : "");//最后修改时间 1.1.5--添加--
                stat_insert.bindString(36, prescriptionMainDTO.getBusiOrderNo() != null ? prescriptionMainDTO.getBusiOrderNo() : "");//支付订单 1.1.7--添加--
                stat_insert.bindString(37, prescriptionMainDTO.getOnlinePayStatus() != null ? prescriptionMainDTO.getOnlinePayStatus() : "");//在线支付状态 1:待支付 2:支付成功 3:支付失败 3不可以修改处方 1.1.7--添加--
                stat_insert.bindString(38, prescriptionMainDTO.getRealName() != null ? prescriptionMainDTO.getRealName() : "");//医生名称 1.1.7--添加--
                stat_insert.bindString(39, prescriptionMainDTO.getHomeAddress() != null ? prescriptionMainDTO.getHomeAddress() : "");//地址 1.1.8--添加--
                stat_insert.bindString(40, prescriptionMainDTO.getPhone() != null ? prescriptionMainDTO.getPhone() : "");//电话 1.1.8--添加--
                stat_insert.bindString(41, prescriptionMainDTO.getDoctorMainId() != null ? prescriptionMainDTO.getDoctorMainId() : "");

                stat_insert.bindString(42, prescriptionMainDTO.getInvalidTime() != null ? prescriptionMainDTO.getInvalidTime() : "");//地址 1.1.8--添加--
                stat_insert.bindString(43, prescriptionMainDTO.getInvalidSysUser() != null ? prescriptionMainDTO.getInvalidSysUser() : "");//电话 1.1.8--添加--
                stat_insert.bindString(44, prescriptionMainDTO.getInvalidReason() != null ? prescriptionMainDTO.getInvalidReason() : "");
                stat_insert.bindString(45, String.valueOf(prescriptionMainDTO.getIs_delete()));
                stat_insert.executeInsert();
                System.out.println("添加处方:"+i);
                PrescriptionDetailsDTO2 prescriptionDetailsDTO2;
                for (int j = 0; j < number; j++) {
                    // 从表
                    String sql_insert2 = " replace into pb_prescription_details (id, commodityCategory, diagnosisId, commodityId, showCommodityName, groupId, useLevel, useLevelUnit, frequency, usage, "
                            + "batchId, priceUnit, totalNumber, receivablePrice, isBlank, orderNum, clinicId, specifcations, docterOrder, dosageFormId,dosage,dosageUnit,unitType) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                    SQLiteStatement stat_insert2 = db.compileStatement(sql_insert2);

                    String selection = " diagnosisId = ? ";
                    String[] selectionArgs;

                    prescriptionDetailsDTO2 = new PrescriptionDetailsDTO2();
                    //删除附表中本次处方的所有附表数据（药品）
                    selectionArgs = new String[]{prescriptionDetailsDTO2.getId()};
                    //添加或修改附表中本次处方的所有附表数据（药品）
                    stat_insert2.bindString(1, prescriptionDetailsDTO2.getId());
                    stat_insert2.bindString(2, prescriptionDetailsDTO2.getCommodityCategory());
                    stat_insert2.bindString(3, prescriptionMainDTO.getId());
                    stat_insert2.bindString(4, "commodityId");
                    stat_insert2.bindString(5, prescriptionDetailsDTO2.getShowCommodityName() != null ? prescriptionDetailsDTO2.getShowCommodityName() : "");
                    stat_insert2.bindString(6, prescriptionDetailsDTO2.getGroupId() != null ? prescriptionDetailsDTO2.getGroupId() : "");
                    stat_insert2.bindString(7, prescriptionDetailsDTO2.getUseLevel() != null ? prescriptionDetailsDTO2.getUseLevel() : "");
                    stat_insert2.bindString(8, prescriptionDetailsDTO2.getUseLevelUnit() != null ? prescriptionDetailsDTO2.getUseLevelUnit() : "");
                    stat_insert2.bindString(9, prescriptionDetailsDTO2.getFrequency() != null ? prescriptionDetailsDTO2.getFrequency() : "");
                    stat_insert2.bindString(10, prescriptionDetailsDTO2.getUsage() != null ? prescriptionDetailsDTO2.getUsage() : "");
                    stat_insert2.bindString(11, prescriptionDetailsDTO2.getBatchId() != null ? prescriptionDetailsDTO2.getBatchId() : "");
                    stat_insert2.bindString(12, prescriptionDetailsDTO2.getSaleUnit() != null ? prescriptionDetailsDTO2.getSaleUnit() : "");
                    stat_insert2.bindString(13, prescriptionDetailsDTO2.getTotalNumber() != null ? prescriptionDetailsDTO2.getTotalNumber() : "");
                    stat_insert2.bindString(14, prescriptionDetailsDTO2.getSaleUnitPrice() != null ? prescriptionDetailsDTO2.getSaleUnitPrice() : "");
                    stat_insert2.bindString(15, "0");
                    stat_insert2.bindString(16, String.valueOf(prescriptionDetailsDTO2.getOrderNum()));
                    stat_insert2.bindString(17, "clinicId");
                    stat_insert2.bindString(18, prescriptionDetailsDTO2.getSpecifcations() != null ? prescriptionDetailsDTO2.getSpecifcations() : "");
                    stat_insert2.bindString(19, prescriptionDetailsDTO2.getDocterOrder() != null ? prescriptionDetailsDTO2.getDocterOrder() : "");
                    stat_insert2.bindString(20, prescriptionDetailsDTO2.getDosageFormId() != null ? prescriptionDetailsDTO2.getDosageFormId() : "");
                    stat_insert2.bindString(21, prescriptionDetailsDTO2.getDosage() != null ? prescriptionDetailsDTO2.getDosage() : "");
                    stat_insert2.bindString(22, prescriptionDetailsDTO2.getDosageUnit() != null ? prescriptionDetailsDTO2.getDosageUnit() : "");
                    stat_insert2.bindString(23, prescriptionDetailsDTO2.getUnitType() != null ? prescriptionDetailsDTO2.getUnitType() : "");
                    System.out.println("添加处方附表:"+j);
                    stat_insert2.executeInsert();
                }
            }
//            db.setTransactionSuccessful();
        } catch (Exception e) {
            // 异常处理
        } finally {
            if (null != db) {
//                db.endTransaction();
                PbPrescriptionDBManager.getInstance().closeDatabase();
            }
        }
    }
}
