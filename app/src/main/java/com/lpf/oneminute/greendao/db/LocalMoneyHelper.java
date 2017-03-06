package com.lpf.oneminute.greendao.db;

import com.lpf.oneminute.greendao.localBean.LocalMoney;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by liupengfei on 17/3/6 17:11.
 */

public class LocalMoneyHelper extends BaseDbHelper<LocalMoney,Long> {
    public LocalMoneyHelper(AbstractDao<LocalMoney, Long> mDao) {
        super(mDao);
    }
}
