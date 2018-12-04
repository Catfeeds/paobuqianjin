package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponListResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/12/3.
 */

public class ShopStyleAdapter extends RecyclerView.Adapter<ShopStyleAdapter.ShopStyleViewHolder> {

    private List<?> data;
    private Context context;

    public ShopStyleAdapter(Context context, List<?> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public ShopStyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopStyleViewHolder(LayoutInflater.from(context).inflate(R.layout.shop_style_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShopStyleViewHolder holder, int position) {
        if (data.get(position) instanceof CouponListResponse.DataBean.TbkCouponBean) {
            if (((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getPict_url() != null &&
                    !TextUtils.isEmpty(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getPict_url() )) {
                Presenter.getInstance(context).getPlaceErrorImage(holder.goodPic, ((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getPict_url() ,
                        R.drawable.bitmap_null, R.drawable.bitmap_null);
                if(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getUser_type() == 0){
                //插入淘宝图标
                }else if(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getUser_type() == 1){
                //插入天猫图标
                }
                holder.goodName.setText(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getTitle());
                holder.taobaoPrice.setText("淘宝价: ￥"+ String.valueOf(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getZk_final_price()));

                holder.salesNum.setText("销量 "+ String.valueOf(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getVolume()));

                holder.quanAfterPrice.setText("券后价  ￥" +String.valueOf(((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getCoupon_info()));

                holder.taoquanTv.setText("￥ " + ((CouponListResponse.DataBean.TbkCouponBean) data.get(position)).getCoupon_info());
            }
        }
    }

    public class ShopStyleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.good_pic)
        CircularImageView goodPic;
        @Bind(R.id.good_name)
        TextView goodName;
        @Bind(R.id.taobao_price)
        TextView taobaoPrice;
        @Bind(R.id.sales_num)
        TextView salesNum;
        @Bind(R.id.quan_after_price)
        TextView quanAfterPrice;
        @Bind(R.id.taoquan_tv)
        TextView taoquanTv;

        public ShopStyleViewHolder(View view) {
            super(view);
            init(view);
        }

        private void init(View viewRoot) {
            goodPic = (CircularImageView) viewRoot.findViewById(R.id.good_pic);
            goodName = (TextView) viewRoot.findViewById(R.id.good_name);
            taobaoPrice = (TextView) viewRoot.findViewById(R.id.sales_num);
            salesNum = (TextView) viewRoot.findViewById(R.id.sales_num);
            quanAfterPrice = (TextView) viewRoot.findViewById(R.id.quan_after_price);
            taoquanTv = (TextView) viewRoot.findViewById(R.id.taoquan_tv);
        }
    }
}
