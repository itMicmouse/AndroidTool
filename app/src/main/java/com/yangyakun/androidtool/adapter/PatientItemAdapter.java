package com.yangyakun.androidtool.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.bean.DoctorsInterface;

import java.util.List;


/**
 * @author 92155
 * 患者信息
 */
public class PatientItemAdapter extends BaseQuickAdapter<DoctorsInterface.WaitPatientBean, BaseViewHolder> {

    private String regx = "\\[=.+]";

    public PatientItemAdapter(@LayoutRes int layout, @Nullable List<DoctorsInterface.WaitPatientBean> data) {
        super(layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorsInterface.WaitPatientBean item) {
        String removepy = removepy(item.getPatientName());
        helper.setText(R.id.tv_patient_name,removepy);
        helper.setText(R.id.tv_patient_number,item.getPatientNo()+"号");
    }

    @NonNull
    private String removepy(String patientName) {
        if(patientName==null){
            return "";
        }
        String[] split = patientName.split(regx);
        StringBuilder currenPatient = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            currenPatient.append(split[i]);
        }
        return currenPatient.toString();
    }
}
