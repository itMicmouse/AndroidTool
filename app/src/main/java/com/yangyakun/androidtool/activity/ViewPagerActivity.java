package com.yangyakun.androidtool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.adapter.InformationPagerAdapter;
import com.yangyakun.androidtool.bean.DoctorsInterface;
import com.yangyakun.androidtool.viewpager.AutoScrollViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {

    AutoScrollViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        pager = findViewById(R.id.scroll_pager);
        setViewPagerScroller();

        pager.setTime(5 * 1000);
        List<DoctorsInterface> doctors = new ArrayList<>();
        DoctorsInterface doctor ;
        for (int i = 0; i < 2; i++) {
            doctor = new DoctorsInterface();
            doctor.setDoctorId("ff80808163feebe70164214e481426a1");
            doctor.setDoctorName("杨亚坤");
            doctor.setIntroduce("要好好日日日腹股沟管刚刚好哈");
            doctor.setQrcodeUrl("http://clinicpe.zhiyijiankang.com/zyPublic/qrcode/readQrcode?id=ff80808163feebe70164214e481426a1&type=2&clinicId=ff808081628ba9d10162a422383e4798&k=d0cba01cc3a90fd63d0a3108c03bb573");
            doctor.setOperationType(0);
            DoctorsInterface.CurrentPatientBean currentPatientBean = new DoctorsInterface.CurrentPatientBean();
            currentPatientBean.setPatientName("李浩");
            currentPatientBean.setPatientNo("11");
            currentPatientBean.setSubscribeId("11");
            doctor.setCurrentPatient(currentPatientBean);
            for (int j = 0; j < 8; j++) {
                DoctorsInterface.WaitPatientBean waitPatientBean = new DoctorsInterface.WaitPatientBean();
                waitPatientBean.setPatientName("耿小敏");
                waitPatientBean.setPatientNo("12");
                doctor.getWaitPatient().add(waitPatientBean);
            }
            doctors.add(doctor);
        }
        InformationPagerAdapter informationPagerAdapter = new InformationPagerAdapter(this, doctors);
        pager.setAdapter(informationPagerAdapter);
        pager.startAutoScroll();
    }

    /**
     * 通过反射改viewpager 的滑动动画
     */
    private void setViewPagerScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            Scroller scroller = new Scroller(this, (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    // 这里是关键，将duration变长或变短
                    super.startScroll(startX, startY, dx, dy, duration * 7);
                }
            };
            scrollerField.set(pager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
