package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorDetailActivity;

import java.util.List;

/**
 * Created by pbq on 2018/3/22.
 */

public class SponsorRedPakAdapter extends RecyclerView.Adapter<SponsorRedPakAdapter.SponsorRedPkgViewHolder> {
    private final static String TAG = SponsorRedPakAdapter.class.getSimpleName();

    private Context context;
    private List<?> mData;

    public SponsorRedPakAdapter(Context context, List<?> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public SponsorRedPkgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SponsorRedPkgViewHolder(LayoutInflater.from(context).inflate(R.layout.red_pkg_list_item, parent, false));
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SponsorRedPkgViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(SponsorRedPkgViewHolder holder, int position) {
        String name = "";
        if (mData != null && position < mData.size()) {
            if (mData.get(position) instanceof SponsorRedPkgResponse.DataBean.CanredpacketBean) {
                if (!TextUtils.isEmpty(((SponsorRedPkgResponse.DataBean.CanredpacketBean) mData.get(position)).getName())) {
                    name = ((SponsorRedPkgResponse.DataBean.CanredpacketBean) mData.get(position)).getName();
                } else {
                    name = ((SponsorRedPkgResponse.DataBean.CanredpacketBean) mData.get(position)).getRed_name();
                }
                holder.redPkgFrom.setText(name);
                holder.canredpacketBean = ((SponsorRedPkgResponse.DataBean.CanredpacketBean) mData.get(position));
                holder.businessid = ((SponsorRedPkgResponse.DataBean.CanredpacketBean) mData.get(position)).getBusinessid();
                holder.imageView.setVisibility(View.VISIBLE);
            } else if (mData.get(position) instanceof SponsorRedPkgResponse.DataBean.LedredpacketBean) {
                if (!TextUtils.isEmpty(((SponsorRedPkgResponse.DataBean.LedredpacketBean) mData.get(position)).getName())) {
                    name = ((SponsorRedPkgResponse.DataBean.LedredpacketBean) mData.get(position)).getName();
                } else {
                    name = ((SponsorRedPkgResponse.DataBean.LedredpacketBean) mData.get(position)).getRed_name();
                }
                holder.redPkgFrom.setText(name);
                holder.ledredpacketBean = ((SponsorRedPkgResponse.DataBean.LedredpacketBean) mData.get(position));
                holder.businessid = ((SponsorRedPkgResponse.DataBean.LedredpacketBean) mData.get(position)).getBusinessid();
                holder.imageView.setVisibility(View.VISIBLE);
            } else if (mData.get(position) instanceof RecRedPkgResponse.DataBean.ResultBean) {
                if (!TextUtils.isEmpty(((RecRedPkgResponse.DataBean.ResultBean) mData.get(position)).getName())) {
                    name = ((RecRedPkgResponse.DataBean.ResultBean) mData.get(position)).getName();
                } else {
                    name = ((RecRedPkgResponse.DataBean.ResultBean) mData.get(position)).getRed_name();
                }
                holder.redPkgFrom.setText(name);
                holder.resultBean = ((RecRedPkgResponse.DataBean.ResultBean) mData.get(position));
                holder.businessid = ((RecRedPkgResponse.DataBean.ResultBean) mData.get(position)).getBusinessid();
                holder.imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            int size = mData.size();
            if (size == 0) {
                return 0;
            } else if (size < 4) {
                return 4;
            } else {
                return mData.size();
            }
        }
        return 0;
    }

    public class SponsorRedPkgViewHolder extends RecyclerView.ViewHolder {

        //@Bind(R.id.red_pkg_from)
        TextView redPkgFrom;
        SponsorRedPkgResponse.DataBean.CanredpacketBean canredpacketBean;
        SponsorRedPkgResponse.DataBean.LedredpacketBean ledredpacketBean;
        RecRedPkgResponse.DataBean.ResultBean resultBean;
        int businessid = -1;
        ImageView imageView;

        public SponsorRedPkgViewHolder(View view) {
            super(view);
            initView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocalLog.d(TAG, "跳转到" + redPkgFrom.getText().toString());
                    if (businessid != -1) {
                        Intent intent = new Intent();
                        intent.putExtra(context.getPackageName() + "businessid", businessid);
                        intent.setClass(context, SponsorDetailActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }

        private void initView(View view) {
            redPkgFrom = (TextView) view.findViewById(R.id.red_pkg_from);
            imageView = (ImageView) view.findViewById(R.id.go_to);
        }
    }
}
