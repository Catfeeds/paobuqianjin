package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */

public class ShopSendedRdBagAdapter extends RecyclerView.Adapter<ShopSendedRdBagAdapter.ShopSendedRedBagHolder> {

    private Context context;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private RecyclerItemClickListener.OnItemClickListener myCustomClickLis;

    public ShopSendedRdBagAdapter(Context context, List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setMyCustomClickLis(RecyclerItemClickListener.OnItemClickListener myCustomClickLis) {
        this.myCustomClickLis = myCustomClickLis;
    }

    @Override
    public ShopSendedRedBagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopSendedRedBagHolder(LayoutInflater.from(context).inflate(R.layout.item_shop_sended_red_bag,null));
    }

    @Override
    public void onBindViewHolder(ShopSendedRedBagHolder holder, final int position) {
        ShopSendedRedBagResponse.ShopSendedRedBagBean itemBean = listData.get(position);
        Presenter.getInstance(context).getPlaceErrorImage(holder.iv_icon, itemBean.getBusinesslogo(), R.drawable.glide_default_picture, R.drawable.glide_default_picture);
        holder.tv_name.setText(itemBean.getVname());
        holder.tv_shop_name.setText(itemBean.getBusinessname());
        holder.tv_step.setText(context.getString(R.string.target_step_s) + itemBean.getStep());
        holder.tv_date.setText(context.getString(R.string.end_time_x, dateFormat.format(new Date(itemBean.getE_time()))));
        holder.tv_money.setText(context.getString(R.string.yuan_icon) + itemBean.getMoney());
        holder.tv_limite_money.setText(context.getString(R.string.use_by_x,itemBean.getCondition()));
        holder.tv_shop_name.setText(itemBean.getBusinessname());
        switch (itemBean.getStatus()) {//0正常|1已下架|2已过期|3已领完
            case 0:
                holder.tv_status.setText(R.string.cut_down);
                holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_6c71c4));
                holder.tv_status.setBackgroundResource(R.drawable.shape_14_dp_purple);
                holder.tv_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(myCustomClickLis!=null) myCustomClickLis.OnItemClick(view, position);
                    }
                });
                break;
            case 1:
                holder.tv_status.setText(R.string.already_cut_down);
                holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_9e9e9e));
                holder.tv_status.setBackgroundResource(R.drawable.shape_14_dp_gray);
                holder.tv_status.setOnClickListener(null);
                break;
            case 2:
                holder.tv_status.setText(R.string.already_over_date);
                holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_9e9e9e));
                holder.tv_status.setBackgroundResource(R.drawable.shape_14_dp_gray);
                holder.tv_status.setOnClickListener(null);
                break;
            case 3:
                holder.tv_status.setText(R.string.already_get_all);
                holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_9e9e9e));
                holder.tv_status.setBackgroundResource(R.drawable.shape_14_dp_gray);
                holder.tv_status.setOnClickListener(null);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ShopSendedRedBagHolder extends RecyclerView.ViewHolder {

        ImageView iv_icon;
        TextView tv_name;
        TextView tv_shop_name;
        TextView tv_step;
        TextView tv_date;
        TextView tv_money;
        TextView tv_limite_money;
        TextView tv_status;

        public ShopSendedRedBagHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_step = itemView.findViewById(R.id.tv_step);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_limite_money = itemView.findViewById(R.id.tv_limite_money);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }
}
