package com.lpf.oneminute.modules.home;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lpf.oneminute.R;
import com.lpf.oneminute.listeners.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 17/3/5 09:11.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<HomeBean> homeBeanList = new ArrayList<HomeBean>();

    private OnRecyclerViewOnClickListener listener;

    public HomeAdapter(Context mContext, List<HomeBean> homeBeanList) {
        this.mContext = mContext;
        this.homeBeanList = homeBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeViewHolder viewHolder = new HomeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.i_home, parent, false),listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HomeViewHolder homeHolder = (HomeViewHolder) holder;
        HomeBean bean = homeBeanList.get(position);

        homeHolder.title.setText(bean.getTitle());

        homeHolder.imageView.setImageResource(bean.getResourceId());
//        Uri uri = Uri.parse("android.resource://"+mContext.getPackageName()+"/"+bean.getResourceId());
//        Glide.with(mContext)
//                .load(uri)
//                .centerCrop()
//                .into(homeHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return homeBeanList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView title;
        OnRecyclerViewOnClickListener listener;

        public HomeViewHolder(View itemView,OnRecyclerViewOnClickListener listener) {
            super(itemView);
            this.listener = listener;
            imageView = (ImageView) itemView.findViewById(R.id.item_home_image);
            title = (TextView) itemView.findViewById(R.id.item_home_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getLayoutPosition());
            }
        }
    }

    public void setItemListener(OnRecyclerViewOnClickListener listener) {
        this.listener = listener;
    }
}
