package com.lpf.oneminute.greendao.db;

import com.lpf.oneminute.greendao.localBean.LocalProtection;
import com.lpf.oneminute.greendao.localBean.LocalUser;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by liupengfei on 17/3/6 17:10.
 */

public class LocalProtectionHelper extends BaseDbHelper<LocalProtection,Long>{

    public LocalProtectionHelper(AbstractDao<LocalProtection, Long> mDao) {
        super(mDao);
    }
}
