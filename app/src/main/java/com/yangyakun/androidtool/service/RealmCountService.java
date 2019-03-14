package com.yangyakun.androidtool.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.yangyakun.androidtool.db.realmdomain.Patitent;
import com.yangyakun.androidtool.db.realmdomain.PrescriptionDetails;
import com.yangyakun.androidtool.db.realmdomain.PrescriptionMain;
import com.yangyakun.androidtool.db.realmdomain.SysLabelDetails;
import com.yangyakun.androidtool.db.realmdomain.SysLabelMain;
import com.yangyakun.androidtool.db.realmdomain.TestABC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class RealmCountService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("onBind.....");
        IBinder result = new ServiceBinder() ;
        Toast.makeText(this, "onBind",Toast.LENGTH_LONG).show();
        return result;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public void startInsertData(){
        //插入药品
        Realm realm = Realm.getDefaultInstance();
        List<TestABC> array = new ArrayList<>();
        TestABC commondity;
        for (int i = 0; i < 100000000; i++) {
            commondity = realm.createObject(TestABC.class);
            commondity.setId(UUID.randomUUID().toString());
            array.add(commondity);
        }
        long startTime = System.currentTimeMillis();
        realm.executeTransactionAsync(bgRealm -> {
            bgRealm.insert(array);
        }, () -> {
            System.out.println("成功药品"+(System.currentTimeMillis()-startTime));
        }, error -> {
            System.out.println("失败"+error);
            error.printStackTrace();
        });
        //插入患者
        realm.executeTransactionAsync(bgRealm -> {
            Patitent patitent;
            for (int i = 0; i < 1000000; i++) {
                patitent = new Patitent();
                patitent.setUserName(patitent.getName());
                patitent.setId(UUID.randomUUID().toString());
                bgRealm.insert(patitent);
            }
        }, () -> {
            System.out.println("成功患者"+(System.currentTimeMillis()-startTime));
        }, error -> {
            System.out.println("失败"+error);
            error.printStackTrace();
        });
        //插入处方和处方附表
        realm.executeTransactionAsync(bgRealm -> {
            for (int i = 0; i < 10000; i++) {
                PrescriptionMain main = new PrescriptionMain();
                String id = UUID.randomUUID().toString();
                main.setId(id);
                PrescriptionDetails details;
                RealmList<PrescriptionDetails> prescriptionDetailsRealmList = new RealmList<>();
                for (int j = 0; j < 10; j++) {
                    details = new PrescriptionDetails();
                    details.setId(UUID.randomUUID().toString());
                    details.setDiagnosisId(id);
                    prescriptionDetailsRealmList.add(details);
                }
                main.setPrescriptionDetailsRealmList(prescriptionDetailsRealmList);
                System.out.println("");
                bgRealm.insert(main);
            }
        }, () -> {
            System.out.println("成功处方"+(System.currentTimeMillis()-startTime));
        }, error -> {
            System.out.println("失败"+error);
            error.printStackTrace();
        });

        //插入标签和标签附表
        realm.executeTransactionAsync(bgRealm -> {
            for (int i = 0; i < 10000; i++) {
                SysLabelMain main = new SysLabelMain();
                String id = UUID.randomUUID().toString();
                main.setId(id);
                SysLabelDetails details;
                RealmList<SysLabelDetails> prescriptionDetailsRealmList = new RealmList<>();
                for (int j = 0; j < 10; j++) {
                    details = new SysLabelDetails();
                    details.setId(UUID.randomUUID().toString());
                    details.setLabelId(id);
                    prescriptionDetailsRealmList.add(details);
                }
                System.out.println("标签");
                main.setSysLabelDetailsRealmList(prescriptionDetailsRealmList);
                bgRealm.insert(main);
            }
        }, () -> {
            System.out.println("成功标签"+(System.currentTimeMillis()-startTime));
        }, error -> {
            System.out.println("失败"+error);
            error.printStackTrace();
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 此方法是为了可以在Acitity中获得服务的实例
     */
    public class ServiceBinder extends Binder {
        public RealmCountService getService() {
            return RealmCountService.this;
        }
    }
}