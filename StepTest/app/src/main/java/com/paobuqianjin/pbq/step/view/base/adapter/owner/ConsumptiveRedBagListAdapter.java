package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AddConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.ConsumptiveRedBag2Activity;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */

public class ConsumptiveRedBagListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = ConsumptiveRedBagListAdapter.class.getSimpleName();
    private Context context;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private SwipeItemClickListener myCustomClickLis;
    private final static int EMPTY_VIEW_TYPE = 1;
    private final static int NO_EMPTY_TYPE = 0;

    public ConsumptiveRedBagListAdapter(Context context, List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setMyCustomClickLis(SwipeItemClickListener myCustomClickLis) {
        this.myCustomClickLis = myCustomClickLis;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NO_EMPTY_TYPE) {
            return new ShopSendedRedBagHolder(LayoutInflater.from(context).inflate(R.layout.item_shop_sended_red_bag, null));
        } else {
            return new ShopSendRedEmptyHolder(LayoutInflater.from(context).inflate(R.layout.item_shop_empty_red_bag, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (!TextUtils.isEmpty(listData.get(position).getVoucherid())) {
            ShopSendedRedBagResponse.ShopSendedRedBagBean itemBean = listData.get(position);

            Presenter.getInstance(context).getPlaceErrorImage(((ShopSendedRedBagHolder) holder).iv_icon, itemBean.getBusinesslogo(), R.drawable.glide_default_picture, R.drawable.glide_default_picture);
//        Presenter.getInstance(context).getImage(holder.iv_icon, itemBean.getBusinesslogo());
            ((ShopSendedRedBagHolder) holder).tv_name.setText(itemBean.getVname());
            ((ShopSendedRedBagHolder) holder).tv_step.setText(context.getString(R.string.target_step_s) + "  " + itemBean.getStep());
            ((ShopSendedRedBagHolder) holder).tv_date.setText(context.getString(R.string.end_time_x, dateFormat.format(new Date(itemBean.getE_time()))));
            String moneyStr = context.getString(R.string.yuan_icon) + itemBean.getMoney();
            SpannableString spannableString = new SpannableString(moneyStr);
            spannableString.setSpan(new AbsoluteSizeSpan(30, true), context.getString(R.string.yuan_icon).length(),
                    moneyStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ((ShopSendedRedBagHolder) holder).tv_money.setText(spannableString);
            ((ShopSendedRedBagHolder) holder).tv_limite_money.setText(context.getString(R.string.use_by_x, itemBean.getCondition()));
            try {
                int distanceInt = Integer.parseInt(itemBean.getDistance());
                String showResult = "";
                if (distanceInt >= 0f) {
                    ((ShopSendedRedBagHolder) holder).im_loc.setVisibility(View.VISIBLE);
                    if (distanceInt > 1000) {
                        String distance = String.format(context.getString(R.string.distance_format), distanceInt / 1000f);
                        showResult = distance + "km";
                    } else {
                        showResult = String.valueOf(distanceInt) + "m";
                    }
                    ((ShopSendedRedBagHolder) holder).tv_distance.setText(showResult);
                } else {
                    ((ShopSendedRedBagHolder) holder).im_loc.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                ((ShopSendedRedBagHolder) holder).im_loc.setVisibility(View.GONE);
            }
            switch (itemBean.getStatus()) {//0可领取|1条件不符|2已领过|3已领完
                case 0:
                    ((ShopSendedRedBagHolder) holder).tv_status.setText(R.string.pull_down_now);
                    /*((ShopSendedRedBagHolder) holder).tv_status.setTextColor(context.getResources().getColor(R.color.color_6c71c4));*/
                    /*((ShopSendedRedBagHolder) holder).tv_status.setBackgroundResource(R.drawable.shape_14_dp_purple);*/
                    ((ShopSendedRedBagHolder) holder).tv_status.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (myCustomClickLis != null)
                                LocalLog.d(TAG, "立即领取");
                            myCustomClickLis.onItemClick(view, position);
                        }
                    });
                    break;
                case 1:
                    ((ShopSendedRedBagHolder) holder).tv_status.setText(R.string.condition_unavaliable);
                   /* ((ShopSendedRedBagHolder) holder).tv_status.setTextColor(context.getResources().getColor(R.color.color_9e9e9e));*/
                    /*((ShopSendedRedBagHolder) holder).tv_status.setBackgroundResource(R.drawable.shape_14_dp_gray);*/
                    ((ShopSendedRedBagHolder) holder).tv_status.setOnClickListener(null);
                    break;
                case 2:
                    ((ShopSendedRedBagHolder) holder).tv_status.setText(R.string.already_pull_down);
                /*    ((ShopSendedRedBagHolder) holder).tv_status.setTextColor(context.getResources().getColor(R.color.color_9e9e9e));*/
                    /*((ShopSendedRedBagHolder) holder).tv_status.setBackgroundResource(R.drawable.shape_14_dp_gray);*/
                    ((ShopSendedRedBagHolder) holder).tv_status.setOnClickListener(null);
                    break;
                case 3:
                    ((ShopSendedRedBagHolder) holder).tv_status.setText(R.string.already_pull_down_over);
                /*    ((ShopSendedRedBagHolder) holder).tv_status.setTextColor(context.getResources().getColor(R.color.color_9e9e9e));*/
                    /*((ShopSendedRedBagHolder) holder).tv_status.setBackgroundResource(R.drawable.shape_14_dp_gray);*/
                    ((ShopSendedRedBagHolder) holder).tv_status.setOnClickListener(null);
                    break;
            }
        } else {

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(listData.get(position).getVoucherid())) {
            return EMPTY_VIEW_TYPE;
        } else {
            return NO_EMPTY_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ShopSendRedEmptyHolder extends RecyclerView.ViewHolder {
        TextView content;

        public ShopSendRedEmptyHolder(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.content_ad);
            SpannableString spannableString = new SpannableString("消费红包的功能是商家向50km内的客户推送消费劵，增加你的客流量，扩展你的产品知名度。点击发红包 >");
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.color_6c71c4)),
                    "消费红包的功能是商家向50km内的客户推送消费劵，增加你的客流量，扩展你的产品知名度。".length(),
                    "消费红包的功能是商家向50km内的客户推送消费劵，增加你的客流量，扩展你的产品知名度。点击发红包 >".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            content.setText(spannableString);
        }
    }

    public class ShopSendedRedBagHolder extends RecyclerView.ViewHolder {

        ImageView iv_icon;
        TextView tv_name;
        ImageView im_loc;
        TextView tv_distance;
        TextView tv_step;
        TextView tv_date;
        TextView tv_money;
        TextView tv_limite_money;
        TextView tv_status;

        public ShopSendedRedBagHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            im_loc = itemView.findViewById(R.id.im_loc);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_step = itemView.findViewById(R.id.tv_step);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_limite_money = itemView.findViewById(R.id.tv_limite_money);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }
}
