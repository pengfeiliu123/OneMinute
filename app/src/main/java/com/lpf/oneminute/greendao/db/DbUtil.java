package com.lpf.oneminute.greendao.db;

import com.lpf.oneminute.greendao.dbhelper.DbCore;

/**
 * Created by liupengfei on 17/3/6 17:09.
 * Db 操作集合
 */

public class DbUtil {

    private static LocalNoteHelper mLocalNoteHelper;

    private static LocalMoneyHelper mLocalMoneyHelper;

    private static LocalMoneyDetailHelper mLocalMoneyDetailHelper;

    private static LocalUserHelper mLocalUserHelper;

    private static LocalProtectionHelper mLocalProtectionHelper;

    public static LocalUserHelper getlocalUserHelper() {
        if (mLocalUserHelper == null) {
            mLocalUserHelper = new LocalUserHelper(DbCore.getDaoSession().getLocalUserDao());
        }
        return mLocalUserHelper;
    }

    public static LocalNoteHelper getlocalNoteHelper() {
        if (mLocalNoteHelper == null) {
            mLocalNoteHelper = new LocalNoteHelper(DbCore.getDaoSession().getLocalNoteDao());
        }
        return mLocalNoteHelper;
    }

    public static LocalMoneyHelper getlocalMoneyHelper() {
        if (mLocalMoneyHelper == null) {
            mLocalMoneyHelper = new LocalMoneyHelper(DbCore.getDaoSession().getLocalMoneyDao());
        }
        return mLocalMoneyHelper;
    }

    public static LocalMoneyDetailHelper getlocalMoneyDetailHelper() {
        if (mLocalMoneyDetailHelper == null) {
            mLocalMoneyDetailHelper = new LocalMoneyDetailHelper(DbCore.getDaoSession().getLocalMoneyDetailDao());
        }
        return mLocalMoneyDetailHelper;
    }

    public static LocalProtectionHelper getlocalProtectionHelper() {
        if (mLocalProtectionHelper == null) {
            mLocalProtectionHelper = new LocalProtectionHelper(DbCore.getDaoSession().getLocalProtectionDao());
        }
        return mLocalProtectionHelper;
    }

}
