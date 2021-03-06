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
import android.widget.ImageView;
import android.widget.TextView;

import com.lpf.common.util.Base64Util;
import com.lpf.common.util.PreferenceUtil;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalUserHelper;
import com.lpf.oneminute.greendao.gen.LocalUserDao;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.listeners.OnProgressShowListener;
import com.lpf.oneminute.modules.home.FragmentHome;
import com.lpf.oneminute.modules.home.HomePresenter;
import com.lpf.oneminute.modules.home.SubFragmentFindPw;
import com.lpf.oneminute.util.NavigatorUtil;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragmentLogin extends Fragment {

    View rootView;
    Context mContext;
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.btn_login)
    ImageView btnLogin;

    OnProgressShowListener listener;
    @BindView(R.id.forget_password)
    TextView forgetPassword;

    public static SubFragmentLogin newInstance() {
        SubFragmentLogin loginFragment = new SubFragmentLogin();
        return loginFragment;
    }

    public SubFragmentLogin() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnProgressShowListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnProgressShowListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_f_login, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);

        initViews();

        return rootView;
    }

    private void initViews() {
//        NavigatorUtil.changeToolTitle(mContext,"Login");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.login_password)
    public void onViewClicked() {
    }

    @OnClick({R.id.btn_login, R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.forget_password:
                forget();
                break;
        }
    }

    private void forget() {
        NavigatorUtil.switchToFragment(mContext, SubFragmentFindPw.newInstance());
    }

    private void login() {
        String userName = loginName.getText().toString();
        String passWord = loginPassword.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.shortShow(mContext, "user name is null");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            ToastUtil.shortShow(mContext, "password is null");
            return;
        }

        if (null != listener) {
            listener.showProgress();
        }

        passWord = Base64Util.encode(passWord);

//        BmobUser user = new BmobUser();
//        user.setUsername(userName);
//        user.setPassword(passWord);
//        user.login(new SaveListener<Object>() {
//            @Override
//            public void done(Object o, BmobException e) {
//                if(null!=listener){
//                    listener.hideProgress();
//                }
//                if (null == e) {
//                    ToastUtil.shortShow(mContext, "login Success");
//                    //todo jump to mainpage
//                    FragmentHome homeFragment = new FragmentHome();
//                    new HomePresenter(getActivity(),homeFragment);
//                    ((MainActivity)getActivity()).switchToFragment(homeFragment);
//                } else {
//                    int resultCode = e.getErrorCode();
//                    ToastUtil.dshortShow(mContext,resultCode+"");
//                    if (resultCode == 101) {
//                        ToastUtil.shortShow(mContext, "failed,username or password was wrong");
//                    }
//                }
//            }
//        });

        LocalUser localUser = new LocalUser();
        localUser.setName(userName);
        localUser.setPassWord(passWord);

//        LocalUserDao localUserDao = App.newInstance().getDaoSession().getLocalUserDao();
        LocalUserHelper localUserDao = DbUtil.getlocalUserHelper();

        boolean isUserExist = false;
        Query<LocalUser> query =
                localUserDao.queryBuilder().where(
                        LocalUserDao.Properties.Name.eq(userName),
                        LocalUserDao.Properties.PassWord.eq(passWord)).build();
        List<LocalUser> localUsers = query.list();

        Query<LocalUser> queryName =
                localUserDao.queryBuilder().where(
                        LocalUserDao.Properties.Name.eq(userName)).build();
        List<LocalUser> localUserName = queryName.list();


        if (localUsers.size() > 0) {
            isUserExist = true;
        } else if (localUsers.size() == 0) {
            if (localUserName.size() > 0) {
                ToastUtil.shortShow(mContext, "the password is wrong!");
            } else {
                ToastUtil.shortShow(mContext, "the userName is invalid!");
            }
        }
        if (null != listener) {
            listener.hideProgress();
        }

        if (isUserExist) {
            PreferenceUtil.putStringValue(mContext, "userName", userName);
            PreferenceUtil.putStringValue(mContext, "userId", localUsers.get(0).getUserId() + "");
            FragmentHome homeFragment = new FragmentHome();
            new HomePresenter(getActivity(), homeFragment);
            ((MainActivity) getActivity()).switchToFragment(homeFragment);
        }
    }
}
