package com.emergency.tengqian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emergency.tengqian.R;

public class UserLoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBtnLoginSubmit;
    private EditText mEtLoginEmail;
    private EditText mEtLoginPassword;
    private EditText mEtLoginUserName;
    private TextView mTvForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initView();
        initData();
    }

    ImageView iv_back;
    TextView tv_title;
    RelativeLayout rl_login;

    private void initView() {
        View v = findViewById(R.id.ll_title);
        iv_back = v.findViewById(R.id.iv_tittle_back);
        tv_title = v.findViewById(R.id.tv_title_text);
        tv_title.setText("登录");
        iv_back.setOnClickListener(this);

        rl_login = findViewById(R.id.rl_login);
        rl_login.setOnClickListener(this);
        mEtLoginEmail = findViewById(R.id.et_LoginEmail);
        mEtLoginPassword = findViewById(R.id.et_LoginPassword);
        mBtnLoginSubmit = findViewById(R.id.btn_LoginSubmit);
        mTvForgetPassword = findViewById(R.id.tv_LoginForgetPassword);
        mTvForgetPassword.setOnClickListener(this);
    }

    private void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tittle_back:
                finish();
                break;
            case R.id.btn_LoginSubmit:
                finish();
                break;
            case R.id.rl_login:
                break;
            case R.id.tv_LoginForgetPassword:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("data", "forget"));
                break;
        }
    }
}
