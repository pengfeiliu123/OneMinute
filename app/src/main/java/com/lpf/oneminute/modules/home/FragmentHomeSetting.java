package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lpf.oneminute.R;
import com.lpf.oneminute.base.BaseFragment;
import com.lpf.oneminute.util.NavigatorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentHomeSetting extends BaseFragment {

    @BindView(R.id.btn_change_pwd)
    CardView btnChangePwd;
    @BindView(R.id.btn_safe_question)
    CardView btnSafeQuestion;
    //    @BindView(R.id.home_setting)
//    RecyclerView homeSettingRv;
    private Context mContext;
    //    private HomeSettingRvAdapter mAdapter;
    private View rootView;

    public static FragmentHomeSetting newInstance() {
        FragmentHomeSetting homeSetting = new FragmentHomeSetting();
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

    }

    @Override
    public boolean interceptBackPressed() {
        FragmentHome fragmentHome = new FragmentHome();
        NavigatorUtil.switchToFragment(getActivity(), fragmentHome);
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.btn_change_pwd, R.id.btn_safe_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_pwd:
                NavigatorUtil.switchToFragment(mContext, SubFragmentChangePw.newInstance());
                break;
            case R.id.btn_safe_question:
                NavigatorUtil.switchToFragment(mContext, SubFragmentSafeQuestion.newInstance());
                break;
        }
    }
}
