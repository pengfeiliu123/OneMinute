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
import com.lpf.oneminute.greendao.db.LocalProtectionHelper;
import com.lpf.oneminute.greendao.localBean.LocalProtection;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;
import com.lpf.oneminute.util.ProtectionUtil;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class SubFragmentFindPw extends Fragment {

    @BindView(R.id.et_safe_question_1)
    EditText etSafeQuestion1;
    @BindView(R.id.tv_safe_question_1)
    TextView tvSafeQuestion1;
    @BindView(R.id.et_safe_question_2)
    EditText etSafeQuestion2;
    @BindView(R.id.tv_safe_question_2)
    TextView tvSafeQuestion2;
    @BindView(R.id.btn_safe_ok)
    ImageView btnSafeOk;
    @BindView(R.id.et_new_pw)
    EditText etNewPw;
    @BindView(R.id.et_new_pw_confirm)
    EditText etNewPwConfirm;
    @BindView(R.id.et_username)
    EditText etUsername;

    private Context mContext;
    private View rootView;

    public SubFragmentFindPw() {
        // Required empty public constructor
    }


    public static SubFragmentFindPw newInstance() {
        SubFragmentFindPw fragment = new SubFragmentFindPw();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sub_f_find_pw, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);

        initViews();
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


    @OnClick(R.id.btn_safe_ok)
    public void onClick() {

        String userName = etUsername.getText().toString();
        String safeQuestion1 = etSafeQuestion1.getText().toString();
        String safeQuestion2 = etSafeQuestion2.getText().toString();
        String newPwd = etNewPw.getText().toString();
        String newPwdConfirm = etNewPwConfirm.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            ToastUtil.shortShow(mContext, "userName cannot be empty!");
            return;
        }

        if (TextUtils.isEmpty(safeQuestion1)) {
            tvSafeQuestion1.setVisibility(View.VISIBLE);
            etSafeQuestion1.startAnimation(AnimationUtil.setSwingH(-5, 5));
            return;
        } else {
            tvSafeQuestion1.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(safeQuestion2)) {
            tvSafeQuestion2.setVisibility(View.VISIBLE);
            etSafeQuestion2.startAnimation(AnimationUtil.setSwingH(-5, 5));
            return;
        } else {
            tvSafeQuestion2.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(newPwd)) {
            ToastUtil.shortShow(mContext, "first password cannot be empty!");
            return;
        }
        if (TextUtils.isEmpty(newPwdConfirm)) {
            ToastUtil.shortShow(mContext, "second password cannot be empty!");
            return;
        }

        LocalUser localUser = AccountUtil.getLocalUser(userName);
        LocalProtection localProtection = ProtectionUtil.getLocalProtection(localUser.getUserId());

        if (localProtection == null) {
            ToastUtil.shortShow(mContext, "sorry, no protection answer!");
            return;
        } else {
            if (!localProtection.getAnswer1().equals(safeQuestion1)) {
                ToastUtil.shortShow(mContext, "first answer is wrong！");
                return;
            }

            if (!localProtection.getAnswer2().equals(safeQuestion2)) {
                ToastUtil.shortShow(mContext, "second answer is wrong！");
                return;
            }

            if (newPwd.equals(newPwdConfirm)) {             // if old password equals current password
                localUser.setPassWord(Base64Util.encode(newPwd));
                DbUtil.getlocalUserHelper().update(localUser);
                ToastUtil.shortShow(mContext, "update success");
                NavigatorUtil.switchToFragment(mContext, FragmentLoginOrRegister.newInstance());
            } else {
                ToastUtil.shortShow(mContext, "the two password is not the same!");
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
