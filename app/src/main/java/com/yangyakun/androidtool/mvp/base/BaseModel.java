package com.yangyakun.androidtool.mvp.base;

//接受到P层交给他的请求
public abstract class BaseModel<P extends BasePersenter,CONTRACT> {

    public P p;

    public BaseModel(P p) {
        this.p = p;
    }

    public abstract CONTRACT getContract();

}
