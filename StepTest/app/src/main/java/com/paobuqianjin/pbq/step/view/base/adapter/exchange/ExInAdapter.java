package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExInOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExOutOrderResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UpItemInterface;

import java.util.List;

/**
 * Created by hqp on 19-1-3.
 */

public class ExInAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    private List<?> data;
    private UpItemInterface upItemInterface;

    public void setUpdateListener(UpItemInterface upItemInterface) {
        this.upItemInterface = upItemInterface;
    }

    public ExInAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (data.get(position) instanceof ExInOrderResponse.DataBeanX.DataBean) {
            String orderNum = "交易单号" + ((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getComm_no();
            final ExInAdapter.ExInViewHolder exOutViewHolder = (ExInAdapter.ExInViewHolder) holder;
            exOutViewHolder.orderNumbm.setText(orderNum);
            Presenter.getInstance(context).getPlaceErrorImage(exOutViewHolder.goodPic, ((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getImg_arr().get(0),
                    R.drawable.bitmap_null, R.drawable.bitmap_null);
            exOutViewHolder.goodName.setText(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getName());
            String money_credit = "";
            if (Float.parseFloat(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getExpress_price()) > 0.0f) {
                money_credit += "￥" + ((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getExpress_price() + "+";
            }
            if (Integer.parseInt(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getCredit_total()) > 0) {
                money_credit += ((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getCredit_total() + "步币";
            }
            exOutViewHolder.goodPrice.setText(money_credit);
            exOutViewHolder.goodJian.setText(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getNumber());
            if (Float.parseFloat(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getExpress_price()) + Float.parseFloat(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getPrice_total()) > 0.0f) {
                String priceToStr = "合计 ￥" + String.valueOf(Float.parseFloat(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getExpress_price())
                        + Float.parseFloat(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getPrice_total()));
                SpannableString spannablePrice = new SpannableString(priceToStr);
                spannablePrice.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.color_161727)), "合计 ￥".length(),
                        priceToStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                exOutViewHolder.priceTotal.setText(spannablePrice);
            }
            exOutViewHolder.goodDes.setText(((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getContent());
            switch (((ExInOrderResponse.DataBeanX.DataBean) data.get(position)).getOrder_status()) {
                case -1:
                    exOutViewHolder.statusStr.setText("退款中");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.leftTv.setVisibility(View.GONE);
                    exOutViewHolder.rightTv.setVisibility(View.GONE);
                    break;
                case -2:
                    exOutViewHolder.statusStr.setText("已退款");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.leftTv.setVisibility(View.GONE);
                    exOutViewHolder.rightTv.setVisibility(View.GONE);
                    break;
                case 0:
                    exOutViewHolder.statusStr.setText("待付款");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.leftTv.setText("取消订单");
                    exOutViewHolder.rightTv.setText("立即支付");
                    exOutViewHolder.leftTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    exOutViewHolder.rightTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                    /*exOutViewHolder.rightTv.setBackground();*/
                    exOutViewHolder.leftTv.setVisibility(View.VISIBLE);
                    exOutViewHolder.rightTv.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    exOutViewHolder.statusStr.setText("待发货");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.rightTv.setText("退款");
                    exOutViewHolder.rightTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                    exOutViewHolder.leftTv.setVisibility(View.GONE);
                    exOutViewHolder.rightTv.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    exOutViewHolder.statusStr.setText("待收货");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.leftTv.setText("确认收货");
                    exOutViewHolder.rightTv.setText("查看物流");
                    exOutViewHolder.leftTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    exOutViewHolder.rightTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                    exOutViewHolder.leftTv.setVisibility(View.VISIBLE);
                    exOutViewHolder.rightTv.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    exOutViewHolder.statusStr.setText("待评价");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.leftTv.setText("查看物流");
                    exOutViewHolder.rightTv.setText("去评价");
                    exOutViewHolder.leftTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    exOutViewHolder.rightTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                    exOutViewHolder.leftTv.setVisibility(View.VISIBLE);
                    exOutViewHolder.rightTv.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    exOutViewHolder.statusStr.setText("交易成功");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_161727));
                    exOutViewHolder.rightTv.setText("查看物流");
                    exOutViewHolder.rightTv.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                    exOutViewHolder.leftTv.setVisibility(View.VISIBLE);
                    exOutViewHolder.rightTv.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    exOutViewHolder.statusStr.setText("已关闭");
                    exOutViewHolder.statusStr.setTextColor(ContextCompat.getColor(context, R.color.color_fffb2003));
                    exOutViewHolder.leftTv.setVisibility(View.GONE);
                    exOutViewHolder.rightTv.setVisibility(View.GONE);
                    break;
            }
            exOutViewHolder.leftTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upItemInterface.updateItem(position, exOutViewHolder.leftTv.getText().toString().trim());
                }
            });

            exOutViewHolder.rightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upItemInterface.updateItem(position, exOutViewHolder.rightTv.getText().toString().trim());
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExInViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_out_order_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public static class ExInViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumbm;
        TextView statusStr;
        CircularImageView goodPic;
        TextView goodName;
        TextView goodDes;
        TextView goodPrice;
        TextView goodNums;
        TextView goodJian;
        TextView priceTotal;
        TextView leftTv;
        TextView rightTv;

        public ExInViewHolder(View view) {
            super(view);
            orderNumbm = (TextView) view.findViewById(R.id.order_numbm);
            statusStr = (TextView) view.findViewById(R.id.status_str);
            goodPic = (CircularImageView) view.findViewById(R.id.good_pic);
            goodName = (TextView) view.findViewById(R.id.good_name);
            goodDes = (TextView) view.findViewById(R.id.good_des);
            goodPrice = (TextView) view.findViewById(R.id.good_price);
            goodNums = (TextView) view.findViewById(R.id.good_nums);
            goodJian = (TextView) view.findViewById(R.id.good_jian);
            priceTotal = (TextView) view.findViewById(R.id.price_total);
            leftTv = (TextView) view.findViewById(R.id.left_tv);
            rightTv = (TextView) view.findViewById(R.id.right_tv);
        }
    }
}
