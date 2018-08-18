package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */

public class ConsumptiveRdBagBeGetAdapter extends RecyclerView.Adapter<ConsumptiveRdBagBeGetAdapter.ShopSendedRedBagHolder> {

    private Context context;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private RecyclerItemClickListener.OnItemClickListener myCustomClickLis;

    public ConsumptiveRdBagBeGetAdapter(Context context, List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setMyCustomClickLis(RecyclerItemClickListener.OnItemClickListener myCustomClickLis) {
        this.myCustomClickLis = myCustomClickLis;
    }

    @Override
    public ShopSendedRedBagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopSendedRedBagHolder(LayoutInflater.from(context).inflate(R.layout.item_consumptive_red_bag_be_get,null));
    }

    @Override
    public void onBindViewHolder(ShopSendedRedBagHolder holder, final int position) {
        ShopSendedRedBagResponse.ShopSendedRedBagBean itemBean = listData.get(position);
        holder.tv_coupon_name.setText(itemBean.getVname());
        holder.tv_name.setText(itemBean.getNickname());
        holder.tv_date.setText(dateFormat.format(new Date(itemBean.getE_time())));
        holder.tv_limite_money.setText(context.getString(R.string.use_by_x,itemBean.getCondition()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ShopSendedRedBagHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_coupon_name;
        TextView tv_date;
        TextView tv_limite_money;

        public ShopSendedRedBagHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_coupon_name = itemView.findViewById(R.id.tv_coupon_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_limite_money = itemView.findViewById(R.id.tv_limite_money);
        }
    }
}
