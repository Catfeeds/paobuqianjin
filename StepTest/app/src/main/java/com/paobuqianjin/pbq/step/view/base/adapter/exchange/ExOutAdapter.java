package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/12/31.
 */

public class ExOutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    private List<?> data;

    public ExOutAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExOutViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_out_order_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public static class ExOutViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.order_numbm)
        TextView orderNumbm;
        @Bind(R.id.status_str)
        TextView statusStr;
        @Bind(R.id.good_pic)
        CircularImageView goodPic;
        @Bind(R.id.good_name)
        TextView goodName;
        @Bind(R.id.good_des)
        TextView goodDes;
        @Bind(R.id.good_price)
        TextView goodPrice;
        @Bind(R.id.good_nums)
        TextView goodNums;
        @Bind(R.id.good_jian)
        TextView goodJian;
        @Bind(R.id.price_total)
        TextView priceTotal;
        @Bind(R.id.left_tv)
        TextView leftTv;
        @Bind(R.id.right_tv)
        TextView rightTv;
        public ExOutViewHolder(View view) {
            super(view);
            orderNumbm =(TextView)view.findViewById(R.id.order_numbm);
            statusStr =(TextView)view.findViewById(R.id.status_str);
            goodPic =(CircularImageView)view.findViewById(R.id.good_pic);
            goodName = (TextView)view.findViewById(R.id.good_name);
        }
    }
}
