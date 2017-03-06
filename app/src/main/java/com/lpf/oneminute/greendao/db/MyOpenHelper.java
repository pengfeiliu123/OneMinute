package com.lpf.oneminute.greendao.db;

import android.content.Context;

import com.lpf.oneminute.greendao.gen.DaoMaster;
import com.lpf.oneminute.greendao.gen.LocalMoneyDao;
import com.lpf.oneminute.greendao.gen.LocalUserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by liupengfei on 17/3/6 16:54.
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context mContext, String realDbName) {
        super(mContext,realDbName);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        switch (oldVersion){
            case 1:
                break;

            case 4:
//                LocalMoneyDao.createTable(db,true);
                LocalUserDao.createTable(db,true);

                //add new column
                db.execSQL("alert table 'local_user' add 'gender' text");
                break;

        }
    }
}
