package com.lpf.oneminute.modules.login.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.home.FragmentHome;
import com.lpf.oneminute.modules.home.HomePresenter;
import com.lpf.oneminute.modules.login.adapter.LoginPagerAdapter;
import com.lpf.oneminute.util.AccountUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

import static com.lpf.oneminute.R.id.toolbar;

/**
 * Login and Register Page
 */
public class FragmentLoginOrRegister extends Fragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.register_login_layout)
    ViewPager registerLoginLayout;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.logout_layout)
    LinearLayout logoutLayout;


    LoginPagerAdapter mAdapter;
    View rootView;
    Context mContext;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    public static FragmentLoginOrRegister newInstance() {
        FragmentLoginOrRegister mInstance = new FragmentLoginOrRegister();
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_login_register, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {

//        toolbar.setNavigationIcon(R.mipmap.menu);

        // show login or btn_logout
//        BmobUser user = BmobUser.getCurrentUser();

        LocalUser user = AccountUtil.getLoginUser(mContext);
        if (user != null) {
//            toolbar.setTitle("personal details:");
//            tablayout.setVisibility(View.GONE);
//            registerLoginLayout.setVisibility(View.GONE);
//            logoutLayout.setVisibility(View.VISIBLE);
//
//            userName.setText(user.getName());
//            btnLogout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    BmobUser.logOut();
//                    ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
//                }
//            });

            FragmentHome homeFragment = new FragmentHome();
            new HomePresenter(getActivity(), homeFragment);
            ((MainActivity) getActivity()).switchToFragment(homeFragment);
        } else {
            initUI();
        }
    }

    private void initUI() {
        mAdapter = new LoginPagerAdapter(getChildFragmentManager(), mContext);
        registerLoginLayout.setAdapter(mAdapter);
        tablayout.setupWithViewPager(registerLoginLayout);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
//        toolbar.setTitle("Login/Register");
    }


}
