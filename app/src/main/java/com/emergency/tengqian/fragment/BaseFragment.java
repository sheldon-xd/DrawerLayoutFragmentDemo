package com.emergency.tengqian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emergency.tengqian.activity.MainActivity;


public class BaseFragment extends Fragment {
    protected boolean isViewCreated = false;
    protected MainActivity activity;
    protected FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (null == activity) {
            activity = (MainActivity) getActivity();
        }
        fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && null != activity) {
            lazyLoadData();
        }
    }

    protected void lazyLoadData() {
    }


}
