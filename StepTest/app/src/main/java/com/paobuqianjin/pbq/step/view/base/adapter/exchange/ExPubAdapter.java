package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExPublistResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UpItemInterface;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExPubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<?> mData;
    private Context context;
    private UpItemInterface upItemInterface;

    public ExPubAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }


    public void setUpdateListener(UpItemInterface upItemInterface) {
        this.upItemInterface = upItemInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PubItemViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_pub_release_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            if (mData.get(position) instanceof ExPublistResponse.DataBeanX.DataBean) {
                if (((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 1) {
                    if (((PubItemViewHolder) holder).tvLeft.getVisibility() == View.VISIBLE) {
                        ((PubItemViewHolder) holder).tvLeft.setVisibility(View.GONE);
                    }
                    ((PubItemViewHolder) holder).tvMid.setText("下架");
                    ((PubItemViewHolder) holder).tvRight.setText("编辑");
                    ((PubItemViewHolder) holder).tvMid.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    ((PubItemViewHolder) holder).tvRight.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                } else if (((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 2) {
                    if (((PubItemViewHolder) holder).tvLeft.getVisibility() == View.GONE) {
                        ((PubItemViewHolder) holder).tvLeft.setVisibility(View.VISIBLE);
                    }
                    ((PubItemViewHolder) holder).tvLeft.setText("删除记录");
                    ((PubItemViewHolder) holder).tvMid.setText("重新上架");
                    ((PubItemViewHolder) holder).tvRight.setText("编辑");
                    ((PubItemViewHolder) holder).tvLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    ((PubItemViewHolder) holder).tvMid.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    ((PubItemViewHolder) holder).tvRight.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                } else if (((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 3) {
                    if (((PubItemViewHolder) holder).tvLeft.getVisibility() == View.GONE) {
                        ((PubItemViewHolder) holder).tvLeft.setVisibility(View.VISIBLE);
                    }
                    ((PubItemViewHolder) holder).tvLeft.setText("删除记录");
                    ((PubItemViewHolder) holder).tvMid.setText("重新上架");
                    ((PubItemViewHolder) holder).tvRight.setText("编辑");
                    ((PubItemViewHolder) holder).tvLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    ((PubItemViewHolder) holder).tvMid.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_gray));
                    ((PubItemViewHolder) holder).tvRight.setBackground(ContextCompat.getDrawable(context, R.drawable.ex_order_button_bg_red));
                }
                Presenter.getInstance(context).getPlaceErrorImage(((PubItemViewHolder) holder).goodPic, ((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getImg_arr().get(0),
                        R.drawable.bitmap_null, R.drawable.bitmap_null);
                ((PubItemViewHolder) holder).goodName.setText(((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getName());
                ((PubItemViewHolder) holder).goodDes.setText(((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getContent());
                String money_credit = "";
                if (Float.parseFloat(((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getExpress_price()) > 0.0f) {
                    money_credit += "￥" + ((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getExpress_price() + "+";
                }
                money_credit += ((ExPublistResponse.DataBeanX.DataBean) mData.get(position)).getCredit() + "步币";
                ((PubItemViewHolder) holder).priceDes.setText(money_credit);

                ((PubItemViewHolder) holder).tvLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (upItemInterface != null) {
                            upItemInterface.updateItem(position, ((TextView) v).getText().toString().trim());
                        }
                    }
                });

                ((PubItemViewHolder) holder).tvMid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (upItemInterface != null) {
                            upItemInterface.updateItem(position, ((TextView) v).getText().toString().trim());
                        }
                    }
                });

                ((PubItemViewHolder) holder).tvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (upItemInterface != null) {
                            upItemInterface.updateItem(position, ((TextView) v).getText().toString().trim());
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class PubItemViewHolder extends RecyclerView.ViewHolder {
        CircularImageView goodPic;
        TextView goodName;
        TextView goodDes;
        TextView priceDes;
        TextView tvLeft;
        TextView tvMid;
        TextView tvRight;

        public PubItemViewHolder(View view) {
            super(view);
            goodPic = (CircularImageView) view.findViewById(R.id.good_pic);
            goodName = (TextView) view.findViewById(R.id.good_name);
            goodDes = (TextView) view.findViewById(R.id.good_des);
            priceDes = (TextView) view.findViewById(R.id.price_des);
            tvLeft = (TextView) view.findViewById(R.id.tv_left);
            tvMid = (TextView) view.findViewById(R.id.tv_mid);
            tvRight = (TextView) view.findViewById(R.id.tv_right);
        }
    }
}
