package com.lpf.oneminute.greendao.dbhelper;

import android.content.Context;

import com.lpf.oneminute.greendao.gen.DaoMaster;
import com.lpf.oneminute.greendao.gen.LocalMoneyDao;
import com.lpf.oneminute.greendao.gen.LocalProtectionDao;
import com.lpf.oneminute.greendao.gen.LocalUserDao;

import org.greenrobot.greendao.database.Database;

import static com.lpf.oneminute.greendao.gen.DaoMaster.dropAllTables;

/**
 * Created by liupengfei on 17/3/6 16:54.
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context mContext, String realDbName) {
        super(mContext,realDbName);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
//        MigrationHelper.getInstance().migrate(db,LocalUserDao.class);
    }
}
