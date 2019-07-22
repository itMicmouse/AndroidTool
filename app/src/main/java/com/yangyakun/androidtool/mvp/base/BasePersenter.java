package com.yangyakun.androidtool.mvp.base;

import java.lang.ref.WeakReference;

//Presenter 基类
public abstract class BasePersenter<V extends BaseView,M extends BaseModel,CONTRACT> {

    protected M m;

    private WeakReference<V> vWeakReference;

    public BasePersenter() {
        m = getModel();
    }

    public void bindView(V v){
        vWeakReference = new WeakReference<>(v);
    }

    public void unBindView() {
        if(vWeakReference != null){
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }
    //子类具体契约 (Model 层和VIew层共同业务)
    public abstract CONTRACT getContract();

    public V getView(){
        if(vWeakReference != null){
            return vWeakReference.get();
        }
        return null;
    }

    public abstract M getModel();
}
