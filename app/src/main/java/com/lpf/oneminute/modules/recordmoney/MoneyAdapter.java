package com.lpf.oneminute.modules.recordmoney;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.common.util.Base64Util;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.R;
//import com.lpf.oneminute.greendao.bean.Money;
import com.lpf.oneminute.greendao.localBean.LocalMoney;
import com.lpf.oneminute.greendao.localBean.LocalMoneyDetail;
import com.lpf.oneminute.util.MoneyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 17/2/25 14:12.
 */

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.MoneyViewHolder> {

    public List<LocalMoney> datas = new ArrayList<LocalMoney>();
    public Context mContext;

    public MoneyAdapter(Context context, List<LocalMoney> datas) {
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public MoneyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = View.inflate(mContext, R.layout.i_money_rv_left, null);
        } else {
            view = View.inflate(mContext, R.layout.i_money_rv_right, null);
        }
        MoneyViewHolder holder = new MoneyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(MoneyViewHolder holder, final int position) {
        LocalMoney money = datas.get(position);
        holder.title.setText(money.getTitle());
//        String content = Base64Util.decode(money.getContent());
        String content = "";

        List<LocalMoneyDetail> moneyDetails = MoneyUtil.getLocalMoneyDetails(money.getId());

        for (int i = 0; i < moneyDetails.size(); i++) {
            StringBuilder sb = new StringBuilder();
            LocalMoneyDetail moneyDetail = moneyDetails.get(i);
            int itemCost = moneyDetail.getCost();
            String itemContent = Base64Util.decode(moneyDetails.get(i).getContent());

            String left = mContext.getString(R.string.item_money_cost_left);
            String right = mContext.getString(R.string.item_money_cost_right);
            sb.append("* ")
                    .append(left)
                    .append(money.getMoneyUnit())
                    .append(":")
                    .append(itemCost)
                    .append(right)
                    .append("<br>")
                    .append("\t"+itemContent)
                    .append("<br>");

            content += sb.toString();
        }

//        SpannableString spanString = new SpannableString(content);
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
//
//        boolean hasItems = true;
//        String tempContent = content;
//        while(hasItems){
//            if(tempContent.contains("[")){
//                int begin = tempContent.indexOf("[");
//                int end = tempContent.indexOf("]");
//                tempContent = tempContent.substring(end+1,tempContent.length());
//                spanString.setSpan(colorSpan,begin,end+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            }else{
//                hasItems = false;
//            }
//        }


        holder.content.setText(Html.fromHtml(content));
        holder.time.setText(money.getTime());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.shortShow(mContext, "click item" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MoneyViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView title;
        public TextView content;
        public TextView time;

        public MoneyViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            title = (TextView) itemView.findViewById(R.id.item_money_title);
            content = (TextView) itemView.findViewById(R.id.item_money_content);
            time = (TextView) itemView.findViewById(R.id.item_money_time);
        }
    }
}
