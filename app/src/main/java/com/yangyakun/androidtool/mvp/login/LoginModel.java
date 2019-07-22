package com.yangyakun.androidtool.mvp.login;

import com.yangyakun.androidtool.mvp.base.BaseModel;

public class LoginModel extends BaseModel<LoginPresenter,LoginContract.Model> {

    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return null;
    }
}
