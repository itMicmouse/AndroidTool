package com.yangyakun.androidtool.mvp.login;

import com.yangyakun.androidtool.mvp.bean.BaseEntity;

public interface LoginContract {

    interface Model{
        void executeLogin(String name,String pwd ) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerResult(T t);
    }

    interface Presenter <T extends BaseEntity> {
        //登录请求（接受View层指令，可以自己做，也可以Model层来做）
        void requestLogin(String name,String pwd);

        // 结果响应 （接受Model层的结果，通知View层刷新）
        void responseResult(T t);
    }

}
