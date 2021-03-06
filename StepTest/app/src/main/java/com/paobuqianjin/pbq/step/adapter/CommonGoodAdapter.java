package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CommonGoodResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.HomeGoodResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.view.PaoImageSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2018/12/12.
 */

public class CommonGoodAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    List<CommonGoodResponse.DataBeanX.DataBean> mData = new ArrayList<>();
    List<ExListResponse.DataBeanX.DataBean> data = new ArrayList<>();

    public CommonGoodAdapter(Context context, int maxSize) {
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setData(CommonGoodResponse.DataBeanX.DataBean bean) {
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setDatas(List<CommonGoodResponse.DataBeanX.DataBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(List<ExListResponse.DataBeanX.DataBean> list) {
        LocalLog.d("setData()", "setData()  enter");
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void cleanData(){
        data.clear();
        notifyDataSetChanged();
    }

    public List<CommonGoodResponse.DataBeanX.DataBean> getData() {
        return mData;
    }

    public List<ExListResponse.DataBeanX.DataBean> getExData() {
        return data;
    }

    @Override
    public int getCount() {
        if (mData.size() > 0) {
            return mData.size();
        } else if (data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodViewHolder holder;
        convertView = inflater.inflate(R.layout.common_good_grid_item, parent, false);
        holder = new GoodViewHolder(convertView);
        if (mData != null && mData.size() > 0 && mData.get(position) instanceof CommonGoodResponse.DataBeanX.DataBean) {
            LocalLog.d("getView", "CommonGoodResponse ");
            holder.goodsName.setText(mData.get(position).getGoods_name());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.goodPic, mData.get(position).getPic_url(), R.drawable.null_bitmap, R.drawable.null_bitmap);
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
            if (!TextUtils.isEmpty(mData.get(position).getShipping_fee())) {
                if ("0".equals(mData.get(position).getShipping_fee()) || "0.0".equals(mData.get(position).getShipping_fee()) ||
                        "0.00".equals(mData.get(position).getShipping_fee())) {
                    holder.triffPay.setText("包邮");
                } else {
                    holder.triffPay.setText("不包邮");
                }
            }
            if (Float.parseFloat(mData.get(position).getPromotion_price()) > 0) {
                holder.allBuBiTv.setVisibility(View.GONE);
                if (Float.parseFloat(mData.get(position).getPoint_exchange()) > 0) {
                    holder.promotionPrice.setVisibility(View.VISIBLE);
                    holder.pointExchange.setVisibility(View.GONE);
                    String showNowMoney = "¥" + mData.get(position).getPromotion_price() + " + " + " " + " " + mData.get(position).getPoint_exchange();
                    SpannableString showSpan = new SpannableString(showNowMoney);
                    showSpan.setSpan(imageSpan, ("¥" + mData.get(position).getPromotion_price() + " + ").length(), ("¥" + mData.get(position).getPromotion_price() + " + " + " ").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    holder.promotionPrice.setText(showSpan);
                    //特殊处理
                    holder.pointExchange.setVisibility(View.VISIBLE);
                    holder.pointExchange.setText(showSpan);
                    holder.promotionPrice.setVisibility(View.GONE);
                } else {

                    holder.promotionPrice.setVisibility(View.GONE);
                    holder.pointExchange.setText("¥" + mData.get(position).getPromotion_price());
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
        } else if (data != null && data.size() > 0 && data.get(position) instanceof ExListResponse.DataBeanX.DataBean) {
            LocalLog.d("getView", "ExListResponse ");
            holder.goodsName.setText(data.get(position).getName());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.goodPic, data.get(position).getImg_url(), R.drawable.null_bitmap, R.drawable.null_bitmap);
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.bubi);
            drawable.setBounds(0, 0, 30, 30);
            PaoImageSpan imageSpan = new PaoImageSpan(drawable);
            //
            if (Float.parseFloat(data.get(position).getOld_price()) > 0) {
                holder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.price.setText("原价" + data.get(position).getOld_price() + "元");
            } else {
                holder.price.setVisibility(View.GONE);
            }
            if (Float.parseFloat(data.get(position).getCredit()) > 0) {
                holder.allBuBiTv.setVisibility(View.GONE);
                if (Float.parseFloat(data.get(position).getCredit()) > 0) {
                    holder.promotionPrice.setVisibility(View.VISIBLE);
                    holder.pointExchange.setVisibility(View.GONE);
                    String showNowMoney = "  " + data.get(position).getCredit();
                    SpannableString showSpan = new SpannableString(showNowMoney);
                    showSpan.setSpan(imageSpan, 0, "  ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    holder.promotionPrice.setText(showSpan);
                    //特殊处理
                    holder.pointExchange.setVisibility(View.VISIBLE);
                    holder.pointExchange.setText(showSpan);
                    holder.promotionPrice.setVisibility(View.GONE);
                }
            }

            if (data.get(position).getExpress_status() == 1) {
                holder.triffPay.setText("不包邮");
            } else if (data.get(position).getExpress_status() == 2) {
                holder.triffPay.setText("包邮");
            } else {
                holder.triffPay.setText("待议");
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
        if (mData != null && mData.size() > 0) {
            return mData.get(position);
        } else if (data != null && data.size() > 0) {
            return data.get(position);
        } else {
            return null;
        }
    }

    public class GoodViewHolder {
        ImageView goodPic;
        TextView goodsName;
        TextView pointExchange;
        TextView price;
        TextView promotionPrice;
        TextView allBuBiTv;
        TextView triffPay;

        public GoodViewHolder(View view) {
            goodPic = (ImageView) view.findViewById(R.id.good_pic);
            goodsName = (TextView) view.findViewById(R.id.goods_name);
            pointExchange = (TextView) view.findViewById(R.id.point_exchange);
            price = (TextView) view.findViewById(R.id.price);
            promotionPrice = (TextView) view.findViewById(R.id.promotion_price);
            allBuBiTv = (TextView) view.findViewById(R.id.all_bubi);
            triffPay = (TextView) view.findViewById(R.id.triff_pay);
        }
    }
}
