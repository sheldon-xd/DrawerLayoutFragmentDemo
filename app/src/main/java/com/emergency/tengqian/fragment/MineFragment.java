package com.emergency.tengqian.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.emergency.tengqian.R;
import com.emergency.tengqian.activity.MainActivity;
import com.emergency.tengqian.activity.RegisterActivity;
import com.emergency.tengqian.activity.UserLoginActivity;
import com.emergency.tengqian.util.ToastUtil;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private String mParam2;

    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RelativeLayout rl_quite;
    ImageView iv_AccountImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me, null);
        rl_quite=v.findViewById(R.id.rl_quite);
        iv_AccountImage=v.findViewById(R.id.iv_AccountImage);
        rl_quite.setOnClickListener(this);
        iv_AccountImage.setOnClickListener(this);
        return v;
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
        Log.i("xieduo", "MineFragment onDetach");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_quite:
                ToastUtil.showMessage(getActivity(),"退出登录");
                break;
            case R.id.iv_AccountImage:
                startActivity(new Intent(getActivity(),MainActivity.class));
                break;
        }
    }

    public void loginRegister() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setIcon(R.drawable.ic_warning_red_a700_24dp);
        builder.setMessage("请选择操作，点击其他区域取消");
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                ToastUtil.showMessage(getActivity(), "正在开发中...");
                startActivity(new Intent(getActivity(), UserLoginActivity.class));
            }
        });
        builder.setNegativeButton("注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                ToastUtil.showMessage(getActivity(), "正在开发中...");
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });
        builder.show();
    }
}
