package com.lpf.oneminute.modules.login.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lpf.common.util.Base64Util;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.App;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.gen.LocalUserDao;
import com.lpf.oneminute.greendao.localBean.LocalUser;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.db.dao.UserDao;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.R.attr.id;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragmentRegister extends Fragment {

    Context mContext;

    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.confirm_pw)
    EditText confirmPw;
    @BindView(R.id.register_pw)
    EditText registerPw;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private boolean nameExists;

    public static SubFragmentRegister newInstance() {
        SubFragmentRegister registerFragment = new SubFragmentRegister();
        return registerFragment;
    }

    public SubFragmentRegister() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sub_f_register, container, false);

        mContext = getActivity();
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_register)
    public void onClick() {

        String userName = registerName.getText().toString();
        String passWord = registerPw.getText().toString();
        String confirmWord = confirmPw.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            ToastUtil.shortShow(mContext, "username is null");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            ToastUtil.shortShow(mContext, "password is null");
            return;
        }
        if (TextUtils.isEmpty(confirmWord)) {
            ToastUtil.shortShow(mContext, "confirm password is null");
            return;
        }

        if (!confirmWord.equals(passWord)) {
            ToastUtil.shortShow(mContext, "password is not right");
            return;
        } else {

            passWord = Base64Util.encode(passWord);

//            BmobUser user = new BmobUser();
//            user.setUsername(userName);
//            user.setPassword(passWord);
//            user.signUp(new SaveListener<Object>() {
//                @Override
//                public void done(Object o, BmobException e) {
//                    if(null == e){
//                        ToastUtil.shortShow(mContext,"register success");
//                        //todo jump to login
//                        ((MainActivity)getActivity()).switchToFragment(FragmentLoginOrRegister.getInstance());
//
//                    }else{
//                        int errorCode = e.getErrorCode();
//                        switch (errorCode){
//                            case 202:
//                                ToastUtil.shortShow(mContext,"username is already taken.");
//                                break;
//                            case 203:
//                                ToastUtil.shortShow(mContext,"email is already taken.");
//                                break;
//                        }
//                    }
//                }
//            });

            // add new user to local db
//            insertUser(userName,passWord,null);


            if(isNameExists(userName)){
                ToastUtil.shortShow(mContext,"already exist the username");
                return;
            }

            LocalUser localUser = new LocalUser();
            localUser.setUserId(System.currentTimeMillis());
            localUser.setName(userName);
            localUser.setPassWord(passWord);
            LocalUserDao mUserDao = App.getInstance().getDaoSession().getLocalUserDao();
            long resultId = mUserDao.insertOrReplace(localUser);
            ToastUtil.shortShow(mContext, resultId + "");
        }


    }

    private void insertUser(String userName, String passWord, String address) {
//        LocalUser localUser = new LocalUser(userName,passWord,"");
//        LocalUserDao mUserDao = App.getInstance().getDaoSession().getLocalUserDao();
//        mUserDao.insert(localUser);
//        if(returnCode !=0){
//            ToastUtil.shortShow(mContext,"register Success");
//            //jump to login
//            ((MainActivity)getActivity()).switchToFragment(FragmentLoginOrRegister.getInstance());
//        }
    }

    public boolean isNameExists(String userName) {
        boolean isUserExist = false;
        LocalUserDao localUserDao = App.getInstance().getDaoSession().getLocalUserDao();
        Query<LocalUser> query =
                localUserDao.queryBuilder().where(
                        LocalUserDao.Properties.Name.eq(userName)).build();
        List<LocalUser> localUsers = query.list();

        if (localUsers.size() > 0) {
            isUserExist = true;
        }
        return isUserExist;
    }
}
