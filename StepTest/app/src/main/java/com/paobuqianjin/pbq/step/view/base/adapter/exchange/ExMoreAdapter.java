package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExGoodDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.view.PaoImageSpan;

import java.util.List;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<?> data;
    private Context context;
    private final static String TAG = ExMoreAdapter.class.getSimpleName();

    public ExMoreAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            if (data.get(position) instanceof ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) {
                ((ExMoreViewHolder) holder).goodsName.setText(((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getName());
                Presenter.getInstance(context).getPlaceErrorImage(((ExMoreViewHolder) holder).goodPic,
                        ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getImg_arr().get(0),
                        R.drawable.bitmap_null, R.drawable.bitmap_null);
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bubi);
                drawable.setBounds(0, 0, 30, 30);
                PaoImageSpan imageSpan = new PaoImageSpan(drawable);
                //
                if (Float.parseFloat(((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price()) > 0) {
                    ((ExMoreViewHolder) holder).price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    ((ExMoreViewHolder) holder).price.setText("原价" + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price() + "元");
                } else {
                    ((ExMoreViewHolder) holder).price.setVisibility(View.GONE);
                }

                if (Float.parseFloat(((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getExpress_price()) > 0) {
                    ((ExMoreViewHolder) holder).allBuBiTv.setVisibility(View.GONE);
                    if (Float.parseFloat(((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getExpress_price()) > 0) {
                        ((ExMoreViewHolder) holder).promotionPrice.setVisibility(View.VISIBLE);
                        ((ExMoreViewHolder) holder).pointExchange.setVisibility(View.GONE);
                        String showNowMoney = "¥" + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price() + " + " + " " + " "
                                + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price();
                        SpannableString showSpan = new SpannableString(showNowMoney);
                        showSpan.setSpan(imageSpan, ("¥" + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price()
                                + " + ").length(), ("¥" + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price() +
                                " + " + " ").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ((ExMoreViewHolder) holder).promotionPrice.setText(showSpan);
                        //特殊处理
                        ((ExMoreViewHolder) holder).pointExchange.setVisibility(View.VISIBLE);
                        ((ExMoreViewHolder) holder).pointExchange.setText(showSpan);
                        ((ExMoreViewHolder) holder).promotionPrice.setVisibility(View.GONE);
                    } else {

                        ((ExMoreViewHolder) holder).promotionPrice.setVisibility(View.GONE);
                        ((ExMoreViewHolder) holder).pointExchange.setText("¥" + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getOld_price());
                        ((ExMoreViewHolder) holder).pointExchange.setVisibility(View.VISIBLE);
                        //特殊处理
                    }
                } else {
                    ((ExMoreViewHolder) holder).promotionPrice.setVisibility(View.GONE);
                    ((ExMoreViewHolder) holder).pointExchange.setVisibility(View.GONE);
                    if (Float.parseFloat(((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getExpress_price()) > 0) {
                        ((ExMoreViewHolder) holder).allBuBiTv.setVisibility(View.VISIBLE);
                        String allBuBiStr = " " + " " + ((ExGoodDetailResponse.DataBean.UserInfoBean.OtherCommnuityBean) data.get(position)).getExpress_price();
                        SpannableString allBubiSpan = new SpannableString(allBuBiStr);
                        allBubiSpan.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ((ExMoreViewHolder) holder).allBuBiTv.setText(allBubiSpan);

                        //特殊处理
                        ((ExMoreViewHolder) holder).pointExchange.setVisibility(View.VISIBLE);
                        ((ExMoreViewHolder) holder).pointExchange.setText(allBubiSpan);
                        ((ExMoreViewHolder) holder).allBuBiTv.setVisibility(View.GONE);
                    } else {
                        ((ExMoreViewHolder) holder).allBuBiTv.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExMoreViewHolder(LayoutInflater.from(context).inflate(R.layout.common_good_grid_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ExMoreViewHolder extends RecyclerView.ViewHolder {
        ImageView goodPic;
        TextView goodsName;
        TextView pointExchange;
        TextView price;
        TextView promotionPrice;
        TextView allBuBiTv;
        TextView triffPay;

        public ExMoreViewHolder(View view) {
            super(view);
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
