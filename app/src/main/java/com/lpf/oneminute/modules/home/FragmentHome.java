package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalProtectionHelper;
import com.lpf.oneminute.greendao.gen.LocalProtectionDao;
import com.lpf.oneminute.greendao.localBean.LocalProtection;
import com.lpf.oneminute.listeners.OnRecyclerViewOnClickListener;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liupengfei on 17/3/5 09:04.
 */

public class FragmentHome extends Fragment implements HomeContract.View {

    View rootView;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.day_word)
    TextView dayWord;
    @BindView(R.id.btn_home_setting)
    ImageView btnHomeSetting;
    @BindView(R.id.btn_home_logout)
    ImageView btnHomeLogout;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.safe_image)
    ImageView safeImage;
    @BindView(R.id.adView)
    NativeExpressAdView mAdView;
//    @BindView(R.id.home_setting_rv)
//    RecyclerView homeSettingRv;

    private Context mContext;
    private HomeContract.Presenter presenter;
    private HomeAdapter mAdapter;
    private List<HomeBean> mHomeBeanList;

    private static final int INSTALLED_AD = 1;                  // add admob native ads
    private static final int CONTENT_AD = 2;
    private static int CURRENT_AD = 1;

    VideoController mVideoController;

//    public static FragmentHome newInstance() {
//        FragmentHome homeFragment = new FragmentHome();
//        return homeFragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.f_home, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        return rootView;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new HomePresenter(getActivity(), this);
        initViews(rootView);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

        NavigatorUtil.changeToolTitle(mContext, "OneMinute");

        homeRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        presenter.loadResults();

        presenter.loadUserName();

        initSafeImage();

        initAdmobExpressAd();

    }

    private void initAdmobExpressAd() {
// Locate the NativeExpressAdView.
        // Set its video options.
        mAdView.setVideoOptions(new VideoOptions.Builder()
                .setStartMuted(true)
                .build());

        // The VideoController can be used to get lifecycle events and info about an ad's video
        // asset. One will always be returned by getVideoController, even if the ad has no video
        // asset.
        mVideoController = mAdView.getVideoController();
        mVideoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            @Override
            public void onVideoEnd() {
                Log.d("lpftag", "Video playback is finished.");
                super.onVideoEnd();
            }
        });

        // Set an AdListener for the AdView, so the Activity can take action when an ad has finished
        // loading.
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mVideoController.hasVideoContent()) {
                    Log.d("lpftag", "Received an ad that contains a video asset.");
                } else {
                    Log.d("lpftag", "Received an ad that does not contain a video asset.");
                }
            }
        });

        mAdView.loadAd(new AdRequest.Builder().build());
    }


    boolean safeSmile = false;

    private void initSafeImage() {
        LocalProtectionHelper localProtectionHelper = DbUtil.getlocalProtectionHelper();
        List<LocalProtection> localProtections;

        String userId = AccountUtil.getLoginId(mContext);
        if (!TextUtils.isEmpty(userId)) {
            Query<LocalProtection> query =
                    localProtectionHelper.queryBuilder().where(
                            LocalProtectionDao.Properties.UserId.eq(userId)).build();
            localProtections = query.list();
            if (localProtections.size() > 0) {
                safeSmile = true;
                safeImage.setImageDrawable(getResources().getDrawable(R.mipmap.smile));
            } else {
                safeImage.setImageDrawable(getResources().getDrawable(R.mipmap.cry));
            }
        }

        safeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (safeSmile) {
                    ToastUtil.shortShow(mContext, "safe question is Ok!");
                } else {
                    ToastUtil.shortShow(mContext, "safe question is null");
                }
            }
        });
    }

    @Override
    public void showUserName() {
        userName.setText("Dear " + AccountUtil.getLoginName(mContext) + ", This is your world!");
    }

    @Override
    public void showResult(List<HomeBean> homeBeanList) {
        mHomeBeanList = homeBeanList;

        if (mAdapter == null) {
            mAdapter = new HomeAdapter(getActivity(), homeBeanList);
            mAdapter.setItemListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    presenter.jumpToDetail(position);
                }
            });
            homeRv.setAdapter(mAdapter);

        } else {
            homeRv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.btn_home_setting, R.id.btn_home_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_setting:
                presenter.showSetting();
                break;
            case R.id.btn_home_logout:
                //need to change
                AccountUtil.resetLoginUser(mContext);
                NavigatorUtil.switchToFragment(mContext, FragmentLoginOrRegister.newInstance());
                break;
        }
    }

    @Override
    public void showSetting() {
//        homeSettingRv.setVisibility(View.VISIBLE);
        homeRv.setVisibility(View.INVISIBLE);

//        Intent intent = new Intent(mContext,HomeSettingActivity.class);
//        startActivity(intent);

//        SubFragmentChangePw subFragmentChangePw = SubFragmentChangePw.newInstance();
        FragmentHomeSetting homeSetting = FragmentHomeSetting.newInstance();
        ((MainActivity) getActivity()).switchToFragment(homeSetting);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
