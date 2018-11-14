package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
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
        if (Integer.parseInt(mData.get(position).getPoint_exchange()) > 0) {
            String showStr = mData.get(position).getPoint_exchange() + "步币";
            SpannableString spannableString = new SpannableString(showStr);
            spannableString.setSpan(new TypefaceSpan("bold"), 0, mData.get(position).getPoint_exchange().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.pointExchange.setText(spannableString);
        }
        if (Float.parseFloat(mData.get(position).getMarket_price()) > 0) {
            holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            holder.price.setText("原价" + mData.get(position).getMarket_price() + "元");
        }
        if (Float.parseFloat(mData.get(position).getPromotion_price()) > 0) {
            holder.promotionPrice.setText("+" + mData.get(position).getPromotion_price());
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

        public GoodViewHolder(View view) {
            goodPic = (ImageView) view.findViewById(R.id.good_pic);
            goodsName = (TextView) view.findViewById(R.id.goods_name);
            pointExchange = (TextView) view.findViewById(R.id.point_exchange);
            price = (TextView) view.findViewById(R.id.price);
            promotionPrice = (TextView) view.findViewById(R.id.promotion_price);
        }
    }

}
