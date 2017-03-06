package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.content.Intent;

import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.modules.recordnote.FragmentRecordNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 17/3/5 09:35.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private Context mContext;

    String[] titles = new String[]{"Add Note", "Add Cost", "Notes", "Bill"};
    int[] resourceIds = new int[]{R.mipmap.ic_menu_note_add, R.mipmap.ic_menu_money_add, R.mipmap.ic_menu_note_show, R.mipmap.ic_menu_money_show};

    public HomePresenter(Context mContext,HomeContract.View view) {
        this.view = view;
        this.mContext = mContext;
        this.view.setPresenter(this);
    }

    @Override
    public void loadResults() {

        List<HomeBean> homeBeanList = new ArrayList<HomeBean>();

        for (int i = 0; i < titles.length; i++) {
            HomeBean bean = new HomeBean();
            bean.setTitle(titles[i]);
            bean.setResourceId(resourceIds[i]);
            homeBeanList.add(bean);
        }

        view.showResult(homeBeanList);
    }

    @Override
    public void jumpToDetail(int position) {
        switch (position){
            case 0:
                ((MainActivity) mContext).switchToFragment(FragmentRecordNote.getInstance());
                break;
            case 1:
                ((MainActivity) mContext).switchToFragment(FragmentRecordNote.getInstance());
                break;
            case 2:
                ((MainActivity) mContext).switchToFragment(FragmentRecordNote.getInstance());
                break;
            case 3:
                ((MainActivity) mContext).switchToFragment(FragmentRecordNote.getInstance());
                break;
        }
    }

    @Override
    public void start() {

    }
}
