package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lpf.oneminute.R;
import com.lpf.oneminute.listeners.OnProgressShowListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSettingActivity extends AppCompatActivity implements OnProgressShowListener{



    @BindView(R.id.home_setting_tablayout)
    TabLayout homeSettingTablayout;
    @BindView(R.id.home_setting_viewpager)
    ViewPager homeSettingViewpager;

    private Context mContext;
    private HomeSettingContract.Presenter presenter;
    private HomeSettingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_setting);
        ButterKnife.bind(this);

        mContext = this;
        initViews();
    }

    private void initViews() {

        mAdapter = new HomeSettingAdapter(getSupportFragmentManager(),this);
        homeSettingViewpager.setAdapter(mAdapter);
        homeSettingTablayout.setupWithViewPager(homeSettingViewpager);
        homeSettingTablayout.setTabMode(TabLayout.MODE_FIXED);


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDrawer() {

    }

    @Override
    public void hideDrawer() {

    }
}
