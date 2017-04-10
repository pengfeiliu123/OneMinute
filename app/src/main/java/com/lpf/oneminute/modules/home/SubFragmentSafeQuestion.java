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
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalProtectionHelper;
import com.lpf.oneminute.greendao.gen.LocalProtectionDao;
import com.lpf.oneminute.greendao.localBean.LocalProtection;
import com.lpf.oneminute.util.AccountUtil;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class SubFragmentSafeQuestion extends Fragment {


    @BindView(R.id.img_safe)
    ImageView imgSafe;
    @BindView(R.id.tv_safe)
    TextView tvSafe;
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

    private Context mContext;
    private View rootView;
    private long questionId = -1;

    LocalProtectionHelper localProtectionHelper = DbUtil.getlocalProtectionHelper();

    public SubFragmentSafeQuestion() {
        // Required empty public constructor
    }


    public static SubFragmentSafeQuestion newInstance() {
        SubFragmentSafeQuestion fragment = new SubFragmentSafeQuestion();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sub_f_safe_question, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);

        initViews();
        return rootView;
    }

    private void initViews() {

        String userId = AccountUtil.getLoginId(mContext);
        if (!TextUtils.isEmpty(userId)) {
            Query<LocalProtection> query =
                    localProtectionHelper.queryBuilder().where(
                            LocalProtectionDao.Properties.UserId.eq(userId)).build();
            List<LocalProtection> localProtections = query.list();
            if (localProtections.size() > 0) {
                setImageSmile();
                questionId = localProtections.get(0).getId();
            } else {
                setImageCry();
            }
        }
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

        String safeQuestion1 = etSafeQuestion1.getText().toString();
        String safeQuestion2 = etSafeQuestion2.getText().toString();

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

        // both question are not null

        LocalProtection localProtection = new LocalProtection();

        if(questionId == -1 ){
            questionId = System.currentTimeMillis();
        }
        try {
            localProtection.setId(questionId);    //这里还有一个问题，要保证id为唯一的
            localProtection.setUserId(AccountUtil.getLoginId(mContext));
            localProtection.setQuestion1(getString(R.string.safe_question_1));
            localProtection.setAnswer1(safeQuestion1);
            localProtection.setQuestion2(getString(R.string.safe_question_2));
            localProtection.setAnswer2(safeQuestion2);

            long resultCode = localProtectionHelper.insertOrUpdate(localProtection);
            if (resultCode > 0) {
                ToastUtil.shortShow(mContext, "success!");
                setImageSmile();
            } else {
                ToastUtil.shortShow(mContext, "add safe question failed!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //after add question success, change button text
    private void setImageSmile() {
        imgSafe.setImageDrawable(getResources().getDrawable(R.mipmap.smile));
        tvSafe.setText("Safe Question is Ok");
    }

    private void setImageCry(){
        imgSafe.setImageDrawable(getResources().getDrawable(R.mipmap.cry));
        tvSafe.setText("Safe Question is Null");
    }
}
