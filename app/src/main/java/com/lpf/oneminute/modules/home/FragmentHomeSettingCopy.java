package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lpf.oneminute.R;
import com.lpf.oneminute.base.BaseFragment;
import com.lpf.oneminute.util.NavigatorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHomeSettingCopy extends BaseFragment {


    @BindView(R.id.home_setting_tablayout)
    TabLayout homeSettingTablayout;
    @BindView(R.id.home_setting_viewpager)
    ViewPager homeSettingViewpager;
    @BindView(R.id.activity_home_setting)
    RelativeLayout activityHomeSetting;
    private Context mContext;
    private HomeSettingAdapter mAdapter;
    private View rootView;

    public static FragmentHomeSettingCopy newInstance() {
        FragmentHomeSettingCopy homeSetting = new FragmentHomeSettingCopy();
        return homeSetting;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_home_setting, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        initViews();
        return rootView;
    }

    private void initViews() {

        NavigatorUtil.changeToolTitle(getActivity(), "Setting");

        mAdapter = new HomeSettingAdapter(getChildFragmentManager(), mContext);
        homeSettingViewpager.setAdapter(mAdapter);
        homeSettingTablayout.setupWithViewPager(homeSettingViewpager);
        homeSettingTablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean interceptBackPressed() {
        FragmentHome fragmentHome = new FragmentHome();
        NavigatorUtil.switchToFragment(getActivity(), fragmentHome);
        return true;
    }
}
