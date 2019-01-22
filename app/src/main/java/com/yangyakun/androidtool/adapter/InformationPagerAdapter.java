package com.yangyakun.androidtool.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.app.BaseApplication;
import com.yangyakun.androidtool.bean.DoctorsInterface;
import com.yangyakun.androidtool.utils.MultTextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GuiYanBing on 2018/6/19 16:28
 * E-Mail Address：guiyanbing@zhiyihealth.com.cn
 */
public class InformationPagerAdapter extends PagerAdapter {

    private List<DoctorsInterface> doctors;
    private Context context;
    private String regx = "\\[=.+]";

    private LinkedList<View> mViewCache = null;
    private LayoutInflater mLayoutInflater = null;


    public InformationPagerAdapter(Context context, List<DoctorsInterface> doctors) {
        this.doctors = doctors;
        this.context = context;

        this.mLayoutInflater = LayoutInflater.from(context) ;
        this.mViewCache = new LinkedList<>();
    }

    @Override
    public int getCount() {
        if (doctors != null) {
            return doctors.size();
        } else {
            return 2;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewHolder viewHolder = null;
        View convertView = null;
        if(mViewCache.size() == 0){
            convertView = this.mLayoutInflater.inflate(R.layout.center , null ,false);
            viewHolder = new ViewHolder();
            viewHolder.tvPatientName = convertView.findViewById(R.id.tv_patient_name);
            viewHolder.tvDoctorContent = convertView.findViewById(R.id.tv_doctor_content);
            viewHolder.tvDoctorName = convertView.findViewById(R.id.tv_doctor_name);
            viewHolder.tvDoctorNumber = convertView.findViewById(R.id.tv_doctorNumber);
            viewHolder.tvCurrentWait = convertView.findViewById(R.id.tv_current_wait);
            viewHolder.rvPatientList = convertView.findViewById(R.id.rv_patient_list);
            viewHolder.imgErcode = convertView.findViewById(R.id.img_ercode);
            convertView.setTag(viewHolder);
        }else {
            convertView = mViewCache.removeFirst();
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if (doctors != null) {
            DoctorsInterface doctorsInterface = doctors.get(position);
            StringBuilder removepy = removepy(doctorsInterface.getDoctorName());
            viewHolder.tvDoctorName.setText(removepy);
            viewHolder.tvDoctorContent.setText(doctorsInterface.getIntroduce());
            //当前就诊姓名
            String patientName = doctorsInterface.getCurrentPatient().getPatientName();
            if (!TextUtils.isEmpty(patientName)) {
                StringBuilder currenPatient = removepy(patientName);
                viewHolder.tvPatientName.setText(currenPatient);
            } else {
                viewHolder.tvDoctorNumber.setText("等待中  . . .");
            }
            //当前就诊号
            String patientNo = doctorsInterface.getCurrentPatient().getPatientNo();
            if (!TextUtils.isEmpty(patientNo)) {
                viewHolder.tvDoctorNumber.setText(patientNo + "号");
            } else {
                viewHolder.tvPatientName.setText("");
            }
            List<DoctorsInterface.WaitPatientBean> waitPatient = doctorsInterface.getWaitPatient();
            if(waitPatient.size()!=0){
                viewHolder.tvCurrentWait.setText(MultTextView.getStyleMoney(waitPatient.size()+""));
            }
            if(waitPatient.size()>3) {
                PatientItemAdapter patientItemAdapter;
                if(waitPatient.size()>6) {
                    patientItemAdapter = new PatientItemAdapter(R.layout.wairpatient_item_more, waitPatient.subList(0,6));
                }else {
                    patientItemAdapter = new PatientItemAdapter(R.layout.wairpatient_item_more, waitPatient);
                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
                viewHolder.rvPatientList.setLayoutManager(gridLayoutManager);
                viewHolder.rvPatientList.setAdapter(patientItemAdapter);
            }else {
                PatientItemAdapter patientItemAdapter = new PatientItemAdapter(R.layout.wairpatient_item_little, waitPatient);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                viewHolder.rvPatientList.setLayoutManager(linearLayoutManager);
                viewHolder.rvPatientList.setAdapter(patientItemAdapter);
                patientItemAdapter.setEmptyView(R.layout.layout_empty_patient,viewHolder.rvPatientList);
            }
            showViewPagerContent(viewHolder.imgErcode, doctorsInterface.getQrcodeUrl());
        }
        container.addView(convertView);
        return convertView;
    }

    @NonNull
    private StringBuilder removepy(String patientName) {
        if(patientName==null){
            return new StringBuilder();
        }
        String[] split = patientName.split(regx);
        StringBuilder currenPatient = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            currenPatient.append(split[i]);
        }
        return currenPatient;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = (View) object;
        container.removeView(contentView);
        this.mViewCache.add(contentView);
        container.removeView((View) object);
    }

    /**
     * 设置二维码
     */
    private void showViewPagerContent(ImageView view, String url) {
        Bitmap mBitmap = CodeUtils.createImage(url, 152, 152, BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(), R.mipmap.ic_launcher));
        view.setImageBitmap(mBitmap);
    }

    static final class ViewHolder{
        TextView tvPatientName;
        TextView tvDoctorContent;
        TextView tvDoctorName;
        TextView tvDoctorNumber;
        TextView tvCurrentWait;
        ImageView imgErcode;
        RecyclerView rvPatientList;
    }

}
