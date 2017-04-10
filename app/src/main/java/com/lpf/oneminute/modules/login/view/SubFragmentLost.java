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

import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.R;
import com.lpf.oneminute.listeners.OnProgressShowListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * find password page
 */
public class SubFragmentLost extends Fragment {

    View rootView;
    Context mContext;

    OnProgressShowListener listener;

    public static SubFragmentLost newInstance() {
        SubFragmentLost loginFragment = new SubFragmentLost();
        return loginFragment;
    }

    public SubFragmentLost() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (OnProgressShowListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement OnProgressShowListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sub_f_login, container, false);
        mContext = getActivity();
        initView();

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {

    }

//    @OnClick(R.id.btn_login)
//    public void onClick() {
//        String userName = loginName.getText().toString();
//        String passWord = loginPassword.getText().toString();
//        if (TextUtils.isEmpty(userName)) {
//            ToastUtil.shortShow(mContext, "user name is null");
//            return;
//        }
//        if (TextUtils.isEmpty(passWord)) {
//            ToastUtil.shortShow(mContext, "password is null");
//            return;
//        }
//
//        if(null!=listener){
//            listener.showProgress();
//        }
//
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
////                    ((MainActivity)getActivity()).switchToFragment(FragmentRecordNote.newInstance());
//                } else {
//                    int resultCode = e.getErrorCode();
//                    ToastUtil.dshortShow(mContext,resultCode+"");
//                    if (resultCode == 101) {
//                        ToastUtil.shortShow(mContext, "failed,username or password was wrong");
//                    }
//                }
//            }
//        });
//    }



}
