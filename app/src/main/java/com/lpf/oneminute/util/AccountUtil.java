package com.lpf.oneminute.util;

import android.content.Context;
import android.text.TextUtils;

import com.lpf.common.util.PreferenceUtil;
import com.lpf.oneminute.App;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalUserHelper;
import com.lpf.oneminute.greendao.gen.LocalUserDao;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by liupengfei on 17/3/5 18:03.
 */

public class AccountUtil {

    // return true if logined
    public static boolean isLogin(Context context) {
        boolean isLogin = false;

        String loginUserId = PreferenceUtil.getStringValue(context, "userId");
        if (!TextUtils.isEmpty(loginUserId)) {
            isLogin = true;
        }
        return isLogin;
    }

    // return login name
    public static String getLoginName(Context context) {

        String loginUserName = PreferenceUtil.getStringValue(context, "userName");
        return loginUserName;
    }

    // return login user
    public static LocalUser getLoginUser(Context context) {

        String loginUserId = PreferenceUtil.getStringValue(context, "userId");
        if(!TextUtils.isEmpty(loginUserId)){
//            LocalUserDao userDao = App.newInstance().getDaoSession().getLocalUserDao();
            LocalUserHelper userDao = DbUtil.getlocalUserHelper();
            Query query = userDao.queryBuilder().where(LocalUserDao.Properties.UserId.eq(loginUserId)).build();
            List<LocalUser> list = query.list();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    public static String getLoginId(Context context){
        String loginUserId = PreferenceUtil.getStringValue(context, "userId");
        return loginUserId;
    }


    public static void resetLoginUser(Context context){
        PreferenceUtil.putStringValue(context, "userName",null);
        PreferenceUtil.putStringValue(context, "userId",null);
    }


}
