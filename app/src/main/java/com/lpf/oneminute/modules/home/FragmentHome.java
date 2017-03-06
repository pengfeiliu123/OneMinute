package com.lpf.oneminute.modules.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpf.oneminute.R;
import com.lpf.oneminute.listeners.OnRecyclerViewOnClickListener;
import com.lpf.oneminute.modules.login.view.SubFragmentLogin;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liupengfei on 17/3/5 09:04.
 */

public class FragmentHome extends Fragment implements HomeContract.View {

    View rootView;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;

    private HomeContract.Presenter presenter;
    private HomeAdapter mAdapter;
    private List<HomeBean> mHomeBeanList;

//    public static FragmentHome newInstance() {
//        FragmentHome homeFragment = new FragmentHome();
//        return homeFragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.f_home, container, false);
        ButterKnife.bind(this, rootView);

        initViews(rootView);

        return rootView;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

        homeRv.setLayoutManager(new GridLayoutManager(getActivity(),2));

        presenter.loadResults();
    }

    @Override
    public void showResult(List<HomeBean> homeBeanList) {
        mHomeBeanList = homeBeanList;

        if(mAdapter==null){
            mAdapter = new HomeAdapter(getActivity(),homeBeanList);
            mAdapter.setItemListener(new OnRecyclerViewOnClickListener(){
                @Override
                public void onItemClick(View v, int position) {
                    presenter.jumpToDetail(position);
                }
            });
            homeRv.setAdapter(mAdapter);

        }else{
            mAdapter.notifyDataSetChanged();
        }
    }
}
