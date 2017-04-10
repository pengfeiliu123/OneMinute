package com.lpf.oneminute.modules.recordnote;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.App;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
//import com.lpf.oneminute.greendao.bean.Note;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalNoteHelper;
import com.lpf.oneminute.greendao.gen.LocalMoneyDao;
import com.lpf.oneminute.greendao.gen.LocalNoteDao;
import com.lpf.oneminute.greendao.gen.LocalUserDao;
import com.lpf.oneminute.greendao.localBean.LocalMoney;
import com.lpf.oneminute.greendao.localBean.LocalNote;
import com.lpf.oneminute.greendao.localBean.LocalUser;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecordNoteShow extends Fragment {

    @BindView(R.id.rv_note)
    RecyclerView rvNote;

    private Context mContext;
    private View rootView;
    private NoteAdapter mAdapter;
    private List<LocalNote> datas = new ArrayList<LocalNote>();

    public FragmentRecordNoteShow() {
        // Required empty public constructor
    }

    public static FragmentRecordNoteShow getInstance() {
        FragmentRecordNoteShow fragmentRecordShow = new FragmentRecordNoteShow();
        return fragmentRecordShow;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_record_note_show, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvNote.setLayoutManager(layoutManager);
        mAdapter = new NoteAdapter(mContext, datas);
        rvNote.setAdapter(mAdapter);

//        requestFromServer();

        if(!AccountUtil.isLogin(mContext)){
            NavigatorUtil.switchToFragment(mContext,FragmentLoginOrRegister.newInstance());
        }
        requestFromLocal();
    }

    // request from local
    private void requestFromLocal() {
//        LocalNoteDao noteDao = App.newInstance().getDaoSession().getLocalNoteDao();
        LocalNoteHelper noteDao = DbUtil.getlocalNoteHelper();
        List<LocalNote> localNoteList;

        String loginId = AccountUtil.getLoginId(mContext);                                  // get all money record desc by time
        Query<LocalNote> localNoteQuery =
                noteDao.queryBuilder()
                        .where(LocalNoteDao.Properties.UserId.eq(loginId))
                        .orderDesc(LocalNoteDao.Properties.Time)
                        .build();
        localNoteList = localNoteQuery.list();

        if (datas != null) {
            datas.clear();
        }
        datas.addAll(localNoteList);
        mAdapter.notifyDataSetChanged();
    }

    //todo 需要添加分页查询
//    private void requestFromServer() {
//        BmobUser user = BmobUser.getCurrentUser();
//        if (user == null) {
//            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
//        } else {
//            BmobQuery<Note> bmobQuery = new BmobQuery<Note>();
//            bmobQuery.addWhereEqualTo("userId", BmobUser.getCurrentUser().getObjectId());
//            bmobQuery.setLimit(50);
//            bmobQuery.findObjects(new FindListener<Note>() {
//                @Override
//                public void done(List<Note> list, BmobException e) {
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
