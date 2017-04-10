package com.lpf.oneminute.modules.home;

import com.lpf.oneminute.base.BasePresenter;
import com.lpf.oneminute.base.BaseView;

import java.util.List;

/**
 * Created by liupengfei on 17/3/5 09:09.
 */

public class HomeContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void showUserName();

        void showResult(List<HomeBean> homeBeanList);

        void showSetting();

    }

    interface Presenter extends BasePresenter{

        void loadResults();

        void jumpToDetail(int position);

        void loadUserName();

        void showSetting();
    }
}
