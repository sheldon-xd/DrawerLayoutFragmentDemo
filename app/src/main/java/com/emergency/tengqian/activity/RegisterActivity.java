package com.emergency.tengqian.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.emergency.tengqian.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnLoginSubmit;
    private EditText mEtLoginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initView();
        initData();
    }

    ImageView iv_back;
    TextView tv_title;

    private void initView() {
        View v = findViewById(R.id.ll_title);
        iv_back = v.findViewById(R.id.iv_tittle_back);
        tv_title = v.findViewById(R.id.tv_title_text);
        iv_back.setOnClickListener(this);

        mEtLoginEmail = findViewById(R.id.et_RegEmail);
        mBtnLoginSubmit = findViewById(R.id.btn_RegCheckEmailAvailable);
    }

    private void initData() {
        String title = getIntent().getStringExtra("data");
        //来自找回密码
        if (!TextUtils.isEmpty(title) && title.equals("forget")) {
            tv_title.setText("密码找回");
            mBtnLoginSubmit.setText("获取密码保护问题");
        } else {
            //正常注册流程
            tv_title.setText("注册");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tittle_back:
                finish();
                break;
            case R.id.btn_RegCheckEmailAvailable:
                break;
        }
    }
}
