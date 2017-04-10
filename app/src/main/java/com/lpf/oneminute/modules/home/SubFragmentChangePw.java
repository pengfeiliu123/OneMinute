package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpf.common.util.AnimationUtil;
import com.lpf.common.util.Base64Util;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class SubFragmentChangePw extends Fragment {


    @BindView(R.id.et_old_pw)
    EditText etOldPw;
    @BindView(R.id.tv_old_pw)
    TextView tvOldPw;
    @BindView(R.id.et_new_pw)
    EditText etNewPw;
    @BindView(R.id.tv_new_pw)
    TextView tvNewPw;
    @BindView(R.id.et_new_confirm_pw)
    EditText etNewConfirmPw;
    @BindView(R.id.tv_new_confirm_pw)
    TextView tvNewConfirmPw;
    @BindView(R.id.btn_change_ok)
    ImageView btnChangeOk;

    private Context mContext;
    private View rootView;


    public SubFragmentChangePw() {
        // Required empty public constructor
    }


    public static SubFragmentChangePw newInstance() {
        SubFragmentChangePw fragment = new SubFragmentChangePw();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sub_f_change_pw, container, false);
        mContext = getActivity();
        initViews();
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initViews() {


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.btn_change_ok)
    public void onClick() {
        String oldPassword = etOldPw.getText().toString();
        String newPassword = etNewPw.getText().toString();
        String confirmPassword = etNewConfirmPw.getText().toString();

        if (TextUtils.isEmpty(oldPassword)) {
            etOldPw.startAnimation(AnimationUtil.setSwingH(-5, 5));
            tvOldPw.setVisibility(View.VISIBLE);
            return;
        } else {
            tvOldPw.setVisibility(View.GONE);

        }

        if (TextUtils.isEmpty(newPassword)) {
            etNewPw.startAnimation(AnimationUtil.setSwingH(-5, 5));
            tvNewPw.setVisibility(View.VISIBLE);
            return;
        } else {
            tvNewPw.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            etNewConfirmPw.startAnimation(AnimationUtil.setSwingH(-5, 5));
            tvNewConfirmPw.setVisibility(View.VISIBLE);
            return;
        } else {
            tvNewConfirmPw.setVisibility(View.GONE);
        }

        LocalUser currentUser = AccountUtil.getLoginUser(mContext);

        if (currentUser.getPassWord().equals(Base64Util.encode(oldPassword))) {             // if old password equals current password
            tvOldPw.setVisibility(View.GONE);
            if (!newPassword.equals(confirmPassword)) {
                ToastUtil.shortShow(mContext, "please input the same password!");
            } else {
                currentUser.setPassWord(Base64Util.encode(newPassword));
                DbUtil.getlocalUserHelper().update(currentUser);
                ToastUtil.shortShow(mContext, "update success");
                AccountUtil.resetLoginUser(mContext);                                       //reset login user to null
                NavigatorUtil.switchToFragment(mContext,FragmentLoginOrRegister.newInstance());
            }
        } else {
            tvOldPw.setText("please input the right old password!");
            tvOldPw.setVisibility(View.VISIBLE);
        }
    }


}
