package com.lpf.oneminute.modules.login.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lpf.oneminute.modules.login.view.SubFragmentLogin;
import com.lpf.oneminute.modules.login.view.SubFragmentRegister;

/**
 * Created by liupengfei on 17/2/24 17:38.
 */

public class LoginPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;

    private String tabTitles[] = new String[]{"Login","Register"};

    private Context mContext;

    public LoginPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return SubFragmentLogin.newInstance();
        }else{
            return SubFragmentRegister.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
