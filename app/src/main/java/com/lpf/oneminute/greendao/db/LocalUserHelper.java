package com.lpf.oneminute.greendao.db;

import com.lpf.oneminute.greendao.localBean.LocalUser;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by liupengfei on 17/3/6 17:10.
 */

public class LocalUserHelper extends BaseDbHelper<LocalUser,Long>{

    public LocalUserHelper(AbstractDao<LocalUser, Long> mDao) {
        super(mDao);
    }
}
