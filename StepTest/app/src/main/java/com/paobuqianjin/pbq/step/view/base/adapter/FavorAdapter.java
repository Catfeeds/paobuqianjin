package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FavRitItemResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;


/**
 * Created by pbq on 2018/12/5.
 */

public class FavorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<?> data;
    private Context context;
    private final static String TAG = FavorAdapter.class.getSimpleName();

    public FavorAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position) instanceof FavRitItemResponse.DataBean.UatmTbkItemBean) {
            ((FavorViewHolder) holder).goodName.setText(((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getTitle());
            if (!TextUtils.isEmpty(((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getPict_url())) {
                Presenter.getInstance(context).getPlaceErrorImage(((FavorViewHolder) holder).goodIco, ((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getPict_url()
                        , R.drawable.bitmap_null, R.drawable.bitmap_null);
            }
            if (((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getUser_type() == 0) {
                ((FavorViewHolder) holder).tianmaoPrice.setText("淘宝原价: ¥" + ((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getZk_final_price());
            } else if (((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getUser_type() == 1) {
                ((FavorViewHolder) holder).tianmaoPrice.setText("天猫原价: ¥" + ((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getZk_final_price());
            } else {
                ((FavorViewHolder) holder).tianmaoPrice.setText("原价: ¥" + ((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getZk_final_price());
            }
            ((FavorViewHolder) holder).salesNum.setText("销量 " + ((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getVolume());
            String quanMoney = ((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getCoupon_info();
            try {
                if (!TextUtils.isEmpty(quanMoney)) {
                    ((FavorViewHolder) holder).quanLiner.setVisibility(View.VISIBLE);
                    String[] result = new String[2];
                    if (!TextUtils.isEmpty(quanMoney) && quanMoney.startsWith("满")) {
                        result = quanMoney.split("减");
                    }
                    LocalLog.d(TAG, "result[0]= " + result[0] + ",result[1] =" + result[1]);
                    result = result[1].split("元");
                    LocalLog.d(TAG, "result[0] =" + result[0]);
                    String quanDeStr = "¥" + result[0] + " 券";
                    SpannableString quanDeStrString = new SpannableString(quanDeStr);
                    quanDeStrString.setSpan(new AbsoluteSizeSpan(12, true), 0, ("¥" + result[0]).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ((FavorViewHolder) holder).taoquanTv.setText(quanDeStrString);
                    float afterPrice = Float.parseFloat(((FavRitItemResponse.DataBean.UatmTbkItemBean) data.get(position)).getZk_final_price())
                            - Float.parseFloat(result[0]);
                    String moneyFormat = String.format(context.getString(R.string.month_income), afterPrice);
                    SpannableString spannableString = new SpannableString(moneyFormat);
                    spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, "¥".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ((FavorViewHolder) holder).quanAfter.setText(spannableString);
                } else {
                    ((FavorViewHolder) holder).quanLiner.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavorViewHolder(LayoutInflater.from(context).inflate(R.layout.shop_search_grid_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class FavorViewHolder extends RecyclerView.ViewHolder {
        CircularImageView goodIco;
        TextView goodName;
        TextView tianmaoPrice;
        TextView quanAfter;
        TextView taoquanTv;
        LinearLayout quanLiner;
        TextView salesNum;

        public FavorViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            goodIco = (CircularImageView) view.findViewById(R.id.good_ico);
            goodName = (TextView) view.findViewById(R.id.good_name);
            tianmaoPrice = (TextView) view.findViewById(R.id.tianmao_price);
            quanAfter = (TextView) view.findViewById(R.id.quan_after);
            taoquanTv = (TextView) view.findViewById(R.id.taoquan_tv);
            quanLiner = (LinearLayout) view.findViewById(R.id.quan_liner);
            salesNum = (TextView) view.findViewById(R.id.sales_num);
        }
    }
}
