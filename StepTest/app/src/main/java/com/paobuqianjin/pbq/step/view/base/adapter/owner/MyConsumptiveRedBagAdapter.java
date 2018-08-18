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
import com.paobuqianjin.pbq.step.view.activity.MyConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/17.
 */

public class MyConsumptiveRedBagAdapter extends RecyclerView.Adapter<MyConsumptiveRedBagAdapter.MyConsumptiveRedBagHolder> {

    private final List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData;
    private Context context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private SwipeItemClickListener myItemClickLis;

    public MyConsumptiveRedBagAdapter(Context context, List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setMyItemClickLis(SwipeItemClickListener myItemClickLis) {
        this.myItemClickLis = myItemClickLis;
    }

    @Override
    public MyConsumptiveRedBagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyConsumptiveRedBagHolder(LayoutInflater.from(context).inflate(R.layout.item_my_consumptive_red_bag,null));
    }

    @Override
    public void onBindViewHolder(MyConsumptiveRedBagHolder holder, final int position) {
        ShopSendedRedBagResponse.ShopSendedRedBagBean itemBean = listData.get(position);
        holder.tv_name.setText(context.getString(R.string.vip_name_x,itemBean.getBusinessname()));
        holder.tv_enable_date.setText(context.getString(R.string.end_time_x, dateFormat.format(new Date(itemBean.getE_time()))));
        holder.tv_money.setText(itemBean.getMoney());
        holder.tv_money_limit.setText(context.getString(R.string.use_by_x,itemBean.getCondition()));
        switch (itemBean.getStatus()) {//0正常|1已下架|2已过期|3已使用
            case 0:
                holder.tv_status.setVisibility(View.VISIBLE);
                holder.iv_status.setVisibility(View.GONE);
                holder.tv_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(myItemClickLis!= null) myItemClickLis.onItemClick(view, position);
                    }
                });
                break;
            case 1:
            case 2:
                holder.tv_status.setOnClickListener(null);
                holder.tv_status.setVisibility(View.GONE);
                holder.iv_status.setVisibility(View.VISIBLE);
                holder.iv_status.setImageResource(R.mipmap.ic_already_overdate);
                break;
            /*case 3:
                holder.tv_status.setOnClickListener(null);
                holder.tv_status.setVisibility(View.GONE);
                holder.iv_status.setVisibility(View.VISIBLE);
                holder.iv_status.setImageResource(R.mipmap.ic_already_used);
                break;*/
            default:
                holder.tv_status.setOnClickListener(null);
                holder.tv_status.setVisibility(View.GONE);
                holder.iv_status.setVisibility(View.VISIBLE);
                holder.iv_status.setImageResource(R.mipmap.ic_already_used);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    class MyConsumptiveRedBagHolder extends RecyclerView.ViewHolder{

        TextView tv_money;
        TextView tv_money_limit;
        TextView tv_name;
        TextView tv_enable_date;
        ImageView iv_status;
        TextView tv_status;

        public MyConsumptiveRedBagHolder(View itemView) {
            super(itemView);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_money_limit = itemView.findViewById(R.id.tv_money_limit);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_enable_date = itemView.findViewById(R.id.tv_enable_date);
            iv_status = itemView.findViewById(R.id.iv_status);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }
}
