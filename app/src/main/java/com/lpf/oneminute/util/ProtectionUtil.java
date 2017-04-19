package com.lpf.oneminute.util;

import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalMoneyDetailHelper;
import com.lpf.oneminute.greendao.db.LocalProtectionHelper;
import com.lpf.oneminute.greendao.gen.LocalMoneyDetailDao;
import com.lpf.oneminute.greendao.gen.LocalProtectionDao;
import com.lpf.oneminute.greendao.localBean.LocalMoneyDetail;
import com.lpf.oneminute.greendao.localBean.LocalProtection;
import com.lpf.oneminute.greendao.localBean.LocalUser;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 17/4/19 20:25.
 */

public class ProtectionUtil {

    public static LocalProtection getLocalProtection(long userId) {
        List<LocalProtection> localProtections = new ArrayList<>();

        LocalProtectionHelper localProtectionDao = DbUtil.getlocalProtectionHelper();
        Query<LocalProtection> query = localProtectionDao.queryBuilder().where(LocalProtectionDao.Properties.UserId.eq(userId)).build();
        localProtections = query.list();

        if (localProtections != null && localProtections.size() > 0) {
            return localProtections.get(0);
        }

        return null;
    }
}
