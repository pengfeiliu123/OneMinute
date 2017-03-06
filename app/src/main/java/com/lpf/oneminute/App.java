package com.lpf.oneminute;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.global.GlobalConfiguration;
import com.lpf.oneminute.greendao.db.MyOpenHelper;
import com.lpf.oneminute.greendao.gen.DaoMaster;
import com.lpf.oneminute.greendao.gen.DaoSession;
import com.nostra13.universalimageloader.utils.L;

import cn.bmob.v3.Bmob;

/**
 * Created by liupengfei on 17/2/24 16:55.
 */

public class App extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static App mInstance;               // 全局唯一实例

    public static App getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initBmob();

        appDebugConfig();

        initImageLoader();

        initDataBase();
    }

    /**
     * init DataBase
     * 默认DaoMaster.DevOpenHelper在数据库升级时，会删除所有的表，因此正式数据库应该做一层封装来实现数据库的安全升级。
     */
    private void initDataBase() {
        mHelper = new DaoMaster.DevOpenHelper(this,"oneMinuteDb",null);
        db = mHelper.getWritableDatabase();     //数据库连接属于DaoMaster,所以多个Session指的是相同的数据库连接
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession(){
        return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }


    //load image
    private void initImageLoader() {

    }

    /**
     * 打开或者关闭日志和Toast请求信息,true表示开启，false表示关闭
     */
    private void appDebugConfig() {
        ToastUtil.debug = true;
    }

    // init bmob
    private void initBmob() {
        //第一：默认初始化,第三个参数默认不传，就不会开启统计功能
        Bmob.initialize(this, GlobalConfiguration.BMOBID, "Bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }
}
