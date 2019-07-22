package com.yangyakun.androidtool.mvp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.mvp.base.BaseView;

public class LoginView extends BaseView<LoginPresenter,LoginContract.Model> {

    private EditText etName;
    private EditText etPWD;
    private Button btLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initListener() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPWD = findViewById(R.id.et_pwd);
        btLogin = findViewById(R.id.bt_login);
    }

    @Override
    public LoginContract.Model getContract() {
        return (name, pwd) -> {

        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    public void doLoginAction(View view) {

    }
}
