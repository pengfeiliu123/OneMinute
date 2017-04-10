package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.listeners.OnRecyclerViewOnClickListener;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liupengfei on 17/3/5 09:04.
 */

public class FragmentHome extends Fragment implements HomeContract.View {

    View rootView;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.day_word)
    TextView dayWord;
    @BindView(R.id.btn_home_setting)
    ImageView btnHomeSetting;
    @BindView(R.id.btn_home_logout)
    ImageView btnHomeLogout;
    @BindView(R.id.user_name)
    TextView userName;
//    @BindView(R.id.home_setting_rv)
//    RecyclerView homeSettingRv;

    private Context mContext;
    private HomeContract.Presenter presenter;
    private HomeAdapter mAdapter;
    private List<HomeBean> mHomeBeanList;

//    public static FragmentHome newInstance() {
//        FragmentHome homeFragment = new FragmentHome();
//        return homeFragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.f_home, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        return rootView;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new HomePresenter(getActivity(),this);
        initViews(rootView);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

        homeRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        presenter.loadResults();

        presenter.loadUserName();
    }

    @Override
    public void showUserName() {
        userName.setText("Dear "+AccountUtil.getLoginName(mContext) + ", This is your world!");
    }

    @Override
    public void showResult(List<HomeBean> homeBeanList) {
        mHomeBeanList = homeBeanList;

        if (mAdapter == null) {
            mAdapter = new HomeAdapter(getActivity(), homeBeanList);
            mAdapter.setItemListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    presenter.jumpToDetail(position);
                }
            });
            homeRv.setAdapter(mAdapter);

        } else {
            homeRv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.btn_home_setting, R.id.btn_home_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_setting:
                presenter.showSetting();
                break;
            case R.id.btn_home_logout:
                //need to change
                AccountUtil.resetLoginUser(mContext);
                NavigatorUtil.switchToFragment(mContext, FragmentLoginOrRegister.newInstance());
                break;
        }
    }

    @Override
    public void showSetting() {
//        homeSettingRv.setVisibility(View.VISIBLE);
        homeRv.setVisibility(View.INVISIBLE);

//        Intent intent = new Intent(mContext,HomeSettingActivity.class);
//        startActivity(intent);

//        SubFragmentChangePw subFragmentChangePw = SubFragmentChangePw.newInstance();
        FragmentHomeSetting homeSetting = FragmentHomeSetting.newInstance();
        ((MainActivity) getActivity()).switchToFragment(homeSetting);

    }
}
