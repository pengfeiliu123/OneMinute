package com.lpf.oneminute.util;

import com.lpf.oneminute.App;
import com.lpf.oneminute.greendao.gen.LocalMoneyDetailDao;
import com.lpf.oneminute.greendao.gen.LocalUserDao;
import com.lpf.oneminute.greendao.localBean.LocalMoneyDetail;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 17/3/5 23:18.
 */

public class MoneyUtil {

    public static List<LocalMoneyDetail> getLocalMoneyDetails(long moneyId) {
        List<LocalMoneyDetail> localMoneyDetails = new ArrayList<>();

        LocalMoneyDetailDao moneyDetailDao = App.getInstance().getDaoSession().getLocalMoneyDetailDao();
        Query<LocalMoneyDetail> query = moneyDetailDao.queryBuilder().where(LocalMoneyDetailDao.Properties.LocalMoneyId.eq(moneyId)).build();
        localMoneyDetails = query.list();

        return localMoneyDetails;
    }
}
