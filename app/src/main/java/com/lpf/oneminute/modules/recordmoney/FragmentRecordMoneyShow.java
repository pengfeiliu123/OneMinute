package com.lpf.oneminute.modules.recordmoney;


import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpf.common.util.AssetUtil;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.App;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.bean.Money;
import com.lpf.oneminute.greendao.gen.LocalMoneyDao;
import com.lpf.oneminute.greendao.gen.LocalUserDao;
import com.lpf.oneminute.greendao.localBean.LocalMoney;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecordMoneyShow extends Fragment {


    @BindView(R.id.rv_money)
    RecyclerView rvMoney;
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvMoney.setLayoutManager(layoutManager);
        mAdapter = new MoneyAdapter(mContext, datas);
        rvMoney.setAdapter(mAdapter);

//        requestFromServer();

        if(!AccountUtil.isLogin(mContext)){
            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.getInstance());
        }
        requestFromLocal();
    }

    private void requestFromLocal() {
        LocalMoneyDao moneyDao = App.getInstance().getDaoSession().getLocalMoneyDao();
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
            ToastUtil.shortShow(mContext, "load data failed");
        }
    }

    //todo 需要添加分页查询
//    private void requestFromServer() {
//        BmobUser user = BmobUser.getCurrentUser();
//        if (user == null) {
//            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.getInstance());
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
