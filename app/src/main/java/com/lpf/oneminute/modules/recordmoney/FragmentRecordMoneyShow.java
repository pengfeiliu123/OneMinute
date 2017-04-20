package com.lpf.oneminute.modules.recordmoney;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalMoneyHelper;
import com.lpf.oneminute.greendao.gen.LocalMoneyDao;
import com.lpf.oneminute.greendao.localBean.LocalMoney;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecordMoneyShow extends Fragment {


    @BindView(R.id.rv_money)
    RecyclerView rvMoney;
    @BindView(R.id.btn_jump_record)
    Button btnJumpRecord;
    private Context mContext;
    private View rootView;
    private MoneyAdapter mAdapter;
    private List<LocalMoney> datas = new ArrayList<LocalMoney>();

    public FragmentRecordMoneyShow() {
        // Required empty public constructor
    }

    public static FragmentRecordMoneyShow getInstance() {
        FragmentRecordMoneyShow recordShowFragment = new FragmentRecordMoneyShow();
        return recordShowFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_record_money_show, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

        NavigatorUtil.changeToolTitle(mContext,"Bill History");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvMoney.setLayoutManager(layoutManager);
        mAdapter = new MoneyAdapter(mContext, datas);
        rvMoney.setAdapter(mAdapter);

//        requestFromServer();

        if (!AccountUtil.isLogin(mContext)) {
            NavigatorUtil.switchToFragment(mContext, FragmentLoginOrRegister.newInstance());
        }
        requestFromLocal();
    }

    private void requestFromLocal() {
//        LocalMoneyDao moneyDao = App.newInstance().getDaoSession().getLocalMoneyDao();
        LocalMoneyHelper moneyDao = DbUtil.getlocalMoneyHelper();
//        List<LocalMoney> localMoneyList = moneyDao.loadAll();

        String loginId = AccountUtil.getLoginId(mContext);                                  // get all money record desc by time
        Query<LocalMoney> localMoneyQuery =
                moneyDao.queryBuilder()
                        .where(LocalMoneyDao.Properties.UserId.eq(loginId))
                        .orderDesc(LocalMoneyDao.Properties.Time)
                        .build();
        List<LocalMoney> localMoneyList = localMoneyQuery.list();

        if (datas != null) {
            datas.clear();
        }
        if (localMoneyList != null && localMoneyList.size() > 0) {
            datas.addAll(localMoneyList);
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.shortShow(mContext, "no record");
            btnJumpRecord.setVisibility(View.VISIBLE);
            btnJumpRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavigatorUtil.switchToFragment(mContext,FragmentRecordMoney.getInstance());
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //todo 需要添加分页查询
//    private void requestFromServer() {
//        BmobUser user = BmobUser.getCurrentUser();
//        if (user == null) {
//            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
//        } else {
//            BmobQuery<Money> bmobQuery = new BmobQuery<Money>();
//            bmobQuery.addWhereEqualTo("userId", BmobUser.getCurrentUser().getObjectId());
//            bmobQuery.setLimit(50);
//            bmobQuery.findObjects(new FindListener<Money>() {
//                @Override
//                public void done(List<Money> list, BmobException e) {
//                    if (e == null) {
//                        datas.addAll(list);
//                        mAdapter.notifyDataSetChanged();
//                    } else {
//                        ToastUtil.shortShow(mContext, "request failed");
//                    }
//                }
//            });
//        }
//    }

}
