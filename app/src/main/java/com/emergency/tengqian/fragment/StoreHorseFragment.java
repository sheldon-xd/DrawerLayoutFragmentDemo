package com.emergency.tengqian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.emergency.tengqian.R;

public class StoreHorseFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private String mParam2;

    public static StoreHorseFragment newInstance(String param1, String param2) {
        StoreHorseFragment fragment = new StoreHorseFragment();
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

    Button btn_AverageDataNear3;
    Button btn_AverageDataToday;
    Button btn_AverageDataNear7;
    Button btn_AverageDataNearToday3;
    Button btn_AverageDataNearToday7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_storehorse_manager, null);
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
        Log.i("xieduo", "evaFragment onDetach");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_AverageDataNear3:
                break;
        }
    }
}
