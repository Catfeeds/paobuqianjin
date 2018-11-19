package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.HomeGoodResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.view.PaoImageSpan;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/11/14.
 */

public class GridGoodAdpter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private int mMaxSize = 12;
    List<HomeGoodResponse.DataBean.GoodsListBean> mData = new ArrayList<>();

    public GridGoodAdpter(Context context, int maxSize) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mMaxSize = maxSize;
    }

    public void setData(HomeGoodResponse.DataBean.GoodsListBean bean) {
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setDatas(List<HomeGoodResponse.DataBean.GoodsListBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public List<HomeGoodResponse.DataBean.GoodsListBean> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        if (mData.size() >= mMaxSize) {
            return mMaxSize;
        }
        return mData.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodViewHolder holder;
        convertView = inflater.inflate(R.layout.good_grid_item, parent, false);
        holder = new GoodViewHolder(convertView);
        holder.goodsName.setText(mData.get(position).getGoods_name());
        Presenter.getInstance(mContext).getPlaceErrorImage(holder.goodPic, mData.get(position).getPic_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.bubi);
        drawable.setBounds(0, 0, 30, 30);
        PaoImageSpan imageSpan = new PaoImageSpan(drawable);
        //
        if (Float.parseFloat(mData.get(position).getMarket_price()) > 0) {
            holder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.price.setText("原价" + mData.get(position).getMarket_price() + "元");
        } else {
            holder.price.setVisibility(View.GONE);
        }
        if (Float.parseFloat(mData.get(position).getPromotion_price()) > 0) {
            holder.allBuBiTv.setVisibility(View.GONE);
            if (Float.parseFloat(mData.get(position).getPoint_exchange()) > 0) {
                holder.promotionPrice.setVisibility(View.VISIBLE);
                holder.pointExchange.setVisibility(View.GONE);
                String showNowMoney = "￥" + mData.get(position).getPromotion_price() + " + " + " " + " " + mData.get(position).getPoint_exchange();
                SpannableString showSpan = new SpannableString(showNowMoney);
                showSpan.setSpan(imageSpan, ("￥" + mData.get(position).getPromotion_price() + " + ").length(), ("￥" + mData.get(position).getPromotion_price() + " + " + " ").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.promotionPrice.setText(showSpan);
                //特殊处理
                holder.pointExchange.setVisibility(View.VISIBLE);
                holder.pointExchange.setText(showSpan);
                holder.promotionPrice.setVisibility(View.GONE);
            } else {

                holder.promotionPrice.setVisibility(View.GONE);
                holder.pointExchange.setText("￥" + mData.get(position).getPromotion_price());
                holder.pointExchange.setVisibility(View.VISIBLE);
                //特殊处理
            }
        } else {
            holder.promotionPrice.setVisibility(View.GONE);
            holder.pointExchange.setVisibility(View.GONE);
            if (Float.parseFloat(mData.get(position).getPoint_exchange()) > 0) {
                holder.allBuBiTv.setVisibility(View.VISIBLE);
                String allBuBiStr = " " + " " + mData.get(position).getPoint_exchange();
                SpannableString allBubiSpan = new SpannableString(allBuBiStr);
                allBubiSpan.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.allBuBiTv.setText(allBubiSpan);

                //特殊处理
                holder.pointExchange.setVisibility(View.VISIBLE);
                holder.pointExchange.setText(allBubiSpan);
                holder.allBuBiTv.setVisibility(View.GONE);
            } else {
                holder.allBuBiTv.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    public class GoodViewHolder {
        ImageView goodPic;
        TextView goodsName;
        TextView pointExchange;
        TextView price;
        TextView promotionPrice;
        TextView allBuBiTv;

        public GoodViewHolder(View view) {
            goodPic = (ImageView) view.findViewById(R.id.good_pic);
            goodsName = (TextView) view.findViewById(R.id.goods_name);
            pointExchange = (TextView) view.findViewById(R.id.point_exchange);
            price = (TextView) view.findViewById(R.id.price);
            promotionPrice = (TextView) view.findViewById(R.id.promotion_price);
            allBuBiTv = (TextView) view.findViewById(R.id.all_bubi);
        }
    }

}
