package com.lpf.oneminute.modules.recordmoney;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lpf.common.util.Base64Util;
import com.lpf.common.util.PreferenceUtil;
import com.lpf.common.util.TimeUtil;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalMoneyDetailHelper;
import com.lpf.oneminute.greendao.db.LocalMoneyHelper;
import com.lpf.oneminute.greendao.localBean.LocalMoney;
import com.lpf.oneminute.greendao.localBean.LocalMoneyDetail;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lpf.oneminute.global.GlobalConfiguration.MONEYUNIT;
import static com.lpf.oneminute.global.GlobalConfiguration.MONEYUNITPOSITION;

public class FragmentRecordMoney extends Fragment {

    //    @BindView(R.id.btnAdd)
//    Button btnAdd;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.money_scroller)
    ScrollView scroller;
    @BindView(R.id.item_money_count)
    TextView moneyCount;
    @BindView(R.id.item_money_title)
    EditText itemMoneyTitle;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;
    @BindView(R.id.btnOk)
    ImageView btnOk;
    @BindView(R.id.btn_money_setting)
    ImageView itemMoneySetting;
    private View rootView;
    private Context mContext;

    private Handler mHandler;
    long totalCost = 0;

    private int id = 1000;
    private List<CardView> cardViewList = new ArrayList<CardView>();
    private List<EditText> etContentList = new ArrayList<EditText>();
    private List<EditText> etMoneyList = new ArrayList<EditText>();
    private List<ImageView> imgDeleteList = new ArrayList<ImageView>();
    private List<LocalMoneyDetail> moneyDetails = new ArrayList<LocalMoneyDetail>();

    private int[] colors = new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6, R.color.color7};


    public static FragmentRecordMoney getInstance() {
        FragmentRecordMoney mInstance = new FragmentRecordMoney();
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_record_money, null, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {

//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if (null == bmobUser) {
//            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
//        }

        if (!AccountUtil.isLogin(mContext)) {
            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
        }
    }


    private void calculateMoney() {
        totalCost = 0;
        boolean isOk = true;
        if (etMoneyList != null) {
            for (EditText et : etMoneyList) {
                if (!TextUtils.isEmpty(et.getText().toString())) {
                    try {
                        totalCost += Long.parseLong(et.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        isOk = false;
                    }
                }
            }
            if (isOk) {
                moneyCount.setText(getString(R.string.costAll) + totalCost);
            } else {
                moneyCount.setText("");
            }
        }
    }

    @OnClick({R.id.btnAdd, R.id.btnOk, R.id.btn_money_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                addSubContent();
                break;
            case R.id.btnOk:
                saveContent();
                break;
            case R.id.btn_money_setting:
                showSettingDialog();
                break;
        }
    }

    // show setting dialog
    private void showSettingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(R.mipmap.btn_setting)
                .setTitle("Default Setting:");

        LinearLayout settingDialog = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.d_record_setting, null);
        builder.setView(settingDialog);

        Spinner spinner = (Spinner) settingDialog.findViewById(R.id.money_unit_spinner);
        final String[] moneyUnits = getResources().getStringArray(R.array.moneyUnit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, moneyUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                PreferenceUtil.putStringValue(mContext,MONEYUNIT,moneyUnits[position]);             //修改默认货币符号
//            }
//        });

        spinner.setSelection(PreferenceUtil.getIntValue(mContext, MONEYUNITPOSITION));               //获取货币默认位置
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PreferenceUtil.putStringValue(mContext, MONEYUNIT, moneyUnits[position]);             //修改默认货币符号
                PreferenceUtil.putIntValue(mContext, MONEYUNITPOSITION, position);             //修改默认货币符号
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void saveContent() {

        if (TextUtils.isEmpty(itemMoneyTitle.getText().toString())) {
            ToastUtil.shortShow(mContext, "title is null");
            return;
        }

        String content = "";
        if (etContentList.size() == 0) {
            ToastUtil.shortShow(mContext, "content is null");
            return;
        }

        boolean isReady = true;
        for (int i = 0; i < etContentList.size(); i++) {
            if (TextUtils.isEmpty(etContentList.get(i).getText().toString()) ||
                    TextUtils.isEmpty(etMoneyList.get(i).getText().toString())) {
                ToastUtil.shortShow(mContext, "Please make sure all items were filled");
                isReady = false;
                return;
            }
        }

        if (isReady) {
            StringBuilder sb = new StringBuilder();
            UnderlineSpan span = new UnderlineSpan();
            for (int i = 0; i < etContentList.size(); i++) {
                sb.append(i + 1).append(".")
                        .append("[Cost:" + etMoneyList.get(i).getText().toString() + "]:")
                        .append(etContentList.get(i).getText().toString()).append("\n");
            }
            content = Base64Util.encode(sb.toString());
        }


//        BmobUser user = BmobUser.getCurrentUser();
//        if (null != user) {
//            Money money = new Money();
//            money.setUserId(user.getObjectId());
//            money.setTime(TimeUtil.formatDate(new Date()));
//            money.setTitle(itemMoneyTitle.getText().toString());
//            money.setContent(content);
//            money.setTotalCost(totalCost + "");
//
//            money.save(new SaveListener<String>() {
//                @Override
//                public void done(String s, BmobException e) {
//                    if(e==null){
//                        ToastUtil.shortShow(mContext,"add Success");
//                    }else{
//                        ToastUtil.shortShow(mContext,"try it again later");
//                    }
//                }
//            });
//        } else {
//            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
//        }

        LocalUser user = AccountUtil.getLoginUser(mContext);
        if (user != null) {
            LocalMoney money = new LocalMoney();
            money.setId(System.currentTimeMillis());
            money.setUserId(user.getUserId());
            money.setTime(TimeUtil.formatDate(new Date()));
            money.setTitle(itemMoneyTitle.getText().toString());
            money.setContent(content);
            money.setMoneyUnit(PreferenceUtil.getStringValue(mContext, MONEYUNIT));
            money.setTotalCost(totalCost + "");

//            LocalMoneyDao moneyDao = App.newInstance().getDaoSession().getLocalMoneyDao();
            LocalMoneyHelper moneyDao = DbUtil.getlocalMoneyHelper();
            long resultCode = moneyDao.insert(money);
            if (resultCode > 0) {
                ToastUtil.shortShow(mContext, "add success");

//                LocalMoneyDetailDao moneyDetailDao = App.newInstance().getDaoSession().getLocalMoneyDetailDao();
                LocalMoneyDetailHelper moneyDetailDao = DbUtil.getlocalMoneyDetailHelper();
                for (int i = 0; i < etContentList.size(); i++) {
                    LocalMoneyDetail moneyDetail = new LocalMoneyDetail();
                    moneyDetail.setId(System.currentTimeMillis());
                    moneyDetail.setLocalMoneyId(resultCode);
                    String moneyDetailContent = Base64Util.encode(etContentList.get(i).getText().toString());
                    moneyDetail.setContent(moneyDetailContent);
                    //because money editText is number type
                    moneyDetail.setCost(Integer.parseInt(etMoneyList.get(i).getText().toString()));
                    moneyDetailDao.insertOrUpdate(moneyDetail);
                }

//                moneyDetailDao.insertOrReplaceInTx(moneyDetails);

                container.removeAllViews();
            } else {
                ToastUtil.shortShow(mContext, "add failed,try it later");
            }
        }
    }

    // add subContent
    public void addSubContent() {
        int color = colors[new Random().nextInt(colors.length)];

        final CardView cardView = new CardView(mContext);
        CardView.LayoutParams cardParams = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        cardParams.setMargins(10, 20, 10, 10);
        cardView.setLayoutParams(cardParams);
        cardView.setCardBackgroundColor(getResources().getColor(color));
        cardView.setRadius(30);

        final LinearLayout addLayout = new LinearLayout(mContext);     //vertical layout
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

        addLayout.setOrientation(LinearLayout.VERTICAL);
        addLayout.setLayoutParams(params);

        ImageView btnDelete = new ImageView(mContext);                 // delete image
        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnDelete.setBackground(getResources().getDrawable(R.mipmap.delete));
        btnDelete.setLayoutParams(deleteParams);
        deleteParams.gravity = Gravity.RIGHT | Gravity.TOP;
        deleteParams.setMargins(0, 0, 0, 0);
        imgDeleteList.add(btnDelete);

        for (int i = 0; i < imgDeleteList.size(); i++) {
            final int position = i;
            ImageView delete = imgDeleteList.get(i);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    container.removeView(cardViewList.get(position));
                    etMoneyList.remove(position);
                    etContentList.remove(position);
                    calculateMoney();
                }
            });
        }

        EditText etContent = new EditText(mContext);                   //edit for content
        etContent.setId(id++);
        etContent.setHint(R.string.content);
        etContent.setHintTextColor(getResources().getColor(R.color.white));
        etContent.setFocusable(true);
        etContent.setFocusableInTouchMode(true);
        etContent.requestFocus();
        etContentList.add(etContent);

        final EditText etMoney = new EditText(mContext);
        LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        etMoney.setLayoutParams(etParams);
        etMoney.setId(id++);
        etMoney.setInputType(InputType.TYPE_CLASS_NUMBER);
        etMoney.setHint("cost:");
        etMoney.setHintTextColor(getResources().getColor(R.color.white));
        etMoneyList.add(etMoney);
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateMoney();
            }
        });


        addLayout.addView(btnDelete);           //delete
        addLayout.addView(etContent);           //content
        addLayout.addView(etMoney);             //money

        cardView.addView(addLayout);
        cardViewList.add(cardView);             //add to cardview list


        container.addView(cardView);            //add cardview

        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                scroller.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }
}