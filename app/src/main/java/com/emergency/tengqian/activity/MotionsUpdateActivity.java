package com.emergency.tengqian.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emergency.tengqian.R;
import com.emergency.tengqian.util.SharedPreferencesUtils;

public class MotionsUpdateActivity extends BaseActivity implements View.OnClickListener {
    private int bsI;
    private int bsR;
    private int fsI;
    private int fsR;
    private int lrI;
    private int lrR;
    private int lsI;
    private int lsR;
    private ImageView mIvBsIncrease;
    private ImageView mIvBsReduce;
    private ImageView mIvFsIncrease;
    private ImageView mIvFsReduce;
    private ImageView mIvLrIncrease;
    private ImageView mIvLrReduce;
    private ImageView mIvLsIncrease;
    private ImageView mIvLsReduce;
    private ImageView mIvRrIncrease;
    private ImageView mIvRrReduce;
    private ImageView mIvRsIncrease;
    private ImageView mIvRsReduce;
    private RelativeLayout rl_complete;
    private EditText mTvBsDegree;
    private EditText mTvFsDegree;
    private EditText mTvLrDegree;
    private EditText mTvLsDegree;
    private EditText mTvRrDegree;
    private EditText mTvRsDegree;
    private int rrI;
    private int rrR;
    private int rsI;
    private int rsR;
    private String strBsDegree;
    private String strFsDegree;
    private String strLrDegree;
    private String strLsDegree;
    private String strRrDegree;
    private String strRsDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_motion_analyse_data);
        View v = findViewById(R.id.ll_title);
        iv_back = v.findViewById(R.id.iv_tittle_back);
        tv_title = v.findViewById(R.id.tv_title_text);
        tv_title.setText("运动参数设置（单位度）");
        iv_back.setOnClickListener(this);
        initView();
        initData();
    }

    ImageView iv_back;
    TextView tv_title;

    private void initView() {
        mTvFsDegree = findViewById(R.id.tv_fsDegree);
        String content = mTvFsDegree.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            mTvFsDegree.setSelection(content.length());
        }
        mTvBsDegree = findViewById(R.id.tv_bsDegree);
        mTvLsDegree = findViewById(R.id.tv_lsDegree);
        mTvRsDegree = findViewById(R.id.tv_rsDegree);
        mTvLrDegree = findViewById(R.id.tv_lrDegree);
        mTvRrDegree = findViewById(R.id.tv_rrDegree);

        mIvFsReduce = (ImageView) findViewById(R.id.iv_fsReduce);
        subtactionListner(mIvFsReduce, mTvFsDegree);
        mIvFsIncrease = (ImageView) findViewById(R.id.iv_fsIncrease);
        countListner(mIvFsIncrease, mTvFsDegree);
        mIvBsReduce = (ImageView) findViewById(R.id.iv_bsReduce);
        subtactionListner(mIvBsReduce, mTvBsDegree);
        mIvBsIncrease = (ImageView) findViewById(R.id.iv_bsIncrease);
        countListner(mIvBsIncrease, mTvBsDegree);
        mIvLsReduce = (ImageView) findViewById(R.id.iv_lsReduce);
        subtactionListner(mIvLsReduce, mTvLsDegree);
        mIvLsIncrease = (ImageView) findViewById(R.id.iv_lsIncrease);
        countListner(mIvLsIncrease, mTvLsDegree);
        mIvRsReduce = (ImageView) findViewById(R.id.iv_rsReduce);
        subtactionListner(mIvRsReduce, mTvRsDegree);
        mIvRsIncrease = (ImageView) findViewById(R.id.iv_rsIncrease);
        countListner(mIvRsIncrease, mTvRsDegree);
        mIvLrReduce = (ImageView) findViewById(R.id.iv_lrReduce);
        subtactionListner(mIvLrReduce, mTvLrDegree);
        mIvLrIncrease = (ImageView) findViewById(R.id.iv_lrIncrease);
        countListner(mIvLrIncrease, mTvLrDegree);
        mIvRrReduce = (ImageView) findViewById(R.id.iv_rrReduce);
        subtactionListner(mIvRrReduce, mTvRrDegree);
        mIvRrIncrease = (ImageView) findViewById(R.id.iv_rrIncrease);
        countListner(mIvRrIncrease, mTvRrDegree);

        rl_complete = findViewById(R.id.rl_complete);
        rl_complete.setOnClickListener(this);
    }

    private void initData() {
        SharedPreferencesUtils.setContext(this);

        strFsDegree = SharedPreferencesUtils.getString("fsDegree", "");
        strBsDegree = SharedPreferencesUtils.getString("bsDegree", "");
        strLsDegree = SharedPreferencesUtils.getString("lsDegree", "");
        strRsDegree = SharedPreferencesUtils.getString("rsDegree", "");
        strLrDegree = SharedPreferencesUtils.getString("lrDegree", "");
        strRrDegree = SharedPreferencesUtils.getString("rrDegree", "");
        if (strFsDegree != "") {
            mTvFsDegree.setText(strFsDegree);
        }
        if (strBsDegree != "") {
            mTvBsDegree.setText(strBsDegree);
        }
        if (strLsDegree != "") {
            mTvLsDegree.setText(strLsDegree);
        }
        if (strRsDegree != "") {
            mTvRsDegree.setText(strRsDegree);
        }
        if (strLrDegree != "") {
            mTvLrDegree.setText(strLrDegree);
        }
        if (strRrDegree != "") {
            mTvRrDegree.setText(strRrDegree);
        }
    }

    int count;

    private void countListner(ImageView iv, final EditText et) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String counts = et.getText().toString();
                if (!TextUtils.isEmpty(counts)) {
                    count = Integer.parseInt(counts);
                    if (count < 80) {
                        count++;
                        et.setText(count + "");
                    }
                }
            }
        });
    }

    private void subtactionListner(ImageView iv, final EditText et) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String counts = et.getText().toString();
                if (!TextUtils.isEmpty(counts)) {
                    count = Integer.parseInt(counts);
                    if (count > 0) {
                        count--;
                        et.setText(count + "");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tittle_back:
                finish();
                break;
            case R.id.rl_complete:
                finish();
                break;
        }
    }

    //private void
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
