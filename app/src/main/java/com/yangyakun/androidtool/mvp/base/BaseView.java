package com.yangyakun.androidtool.mvp.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseView<P extends BasePersenter,CONTRACT> extends Activity {

    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        p = getPresenter();
        //绑定
        p.bindView(this);
    }

    //让P层做什么
    public abstract CONTRACT getContract();

    //从子类中获取契约
    public abstract P getPresenter();

    // 如果Presenter 出现异常 需要告知View
    public void error(Exception e){};

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unBindView();
    }
}
