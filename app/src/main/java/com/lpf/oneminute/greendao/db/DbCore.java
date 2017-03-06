package com.lpf.oneminute.greendao.db;

import android.content.Context;

import com.lpf.oneminute.greendao.gen.DaoMaster;
import com.lpf.oneminute.greendao.gen.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by liupengfei on 17/3/6 16:45.
 */

public class DbCore {

    private static final String DEFAULT_DB_NAME = "greendao3.db";

    private static DaoMaster daoMaster;

    private static DaoSession daoSession;


    private static Context mContext;

    private static String REAL_DB_NAME;

    // init

    public static void init(Context context){
        init(context,DEFAULT_DB_NAME);
    }

    public static void init(Context context,String dbName){
        if(context == null){
            throw new IllegalArgumentException("context cannot be null");
        }

        mContext = context;
        REAL_DB_NAME = dbName;
    }


    public static DaoMaster getDaoMaster(){
        if(daoMaster == null){

            DaoMaster.OpenHelper helper = new MyOpenHelper(mContext,REAL_DB_NAME);
            daoMaster = new DaoMaster(helper.getEncryptedReadableDb("password"));
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(){
        if(daoSession== null){
            if(daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }


}
