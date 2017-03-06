package com.lpf.oneminute.greendao.db;

import com.lpf.oneminute.greendao.localBean.LocalMoneyDetail;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by liupengfei on 17/3/6 17:12.
 */

public class LocalMoneyDetailHelper extends BaseDbHelper<LocalMoneyDetail,Long> {
    public LocalMoneyDetailHelper(AbstractDao<LocalMoneyDetail, Long> mDao) {
        super(mDao);
    }
}
