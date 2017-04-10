package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lpf.oneminute.R;
import com.lpf.oneminute.modules.login.view.SubFragmentLogin;
import com.lpf.oneminute.modules.login.view.SubFragmentRegister;

/**
 * Created by liupengfei on 17/3/7 14:09.
 */
public class HomeSettingAdapter extends FragmentPagerAdapter{


    private String tabTitles[];

    private Context mContext;

    public HomeSettingAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;

        tabTitles = mContext.getResources().getStringArray(R.array.home_setting_tablayout);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return SubFragmentChangePw.newInstance();
        }else{
            return SubFragmentSafeQuestion.newInstance();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
