package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lpf.oneminute.R;
import com.lpf.oneminute.modules.recordmoney.MoneyAdapter;
import com.lpf.oneminute.util.NavigatorUtil;

/**
 * Created by liupengfei on 17/3/7 14:09.
 */
public class HomeSettingRvAdapter extends RecyclerView.Adapter<HomeSettingRvAdapter.SettingViewHolder> {


    private String[] itemList;

    private Context mContext;

    public HomeSettingRvAdapter(Context mContext) {
        this.mContext = mContext;
        itemList = mContext.getResources().getStringArray(R.array.home_setting);
    }

    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = View.inflate(mContext, R.layout.i_setting_rv, null);
        SettingViewHolder holder = new SettingViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SettingViewHolder holder, final int position) {
        holder.title.setText(itemList[position]);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        NavigatorUtil.switchToFragment(mContext,SubFragmentChangePw.newInstance());
                        break;
                    case 1:
                        NavigatorUtil.switchToFragment(mContext,SubFragmentSafeQuestion.newInstance());
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.length;
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView title;

        public SettingViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            title = (TextView) itemView.findViewById(R.id.item_setting);
        }
    }
}
