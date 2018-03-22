package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
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

    @Override
    public void onBindViewHolder(SponsorRedPkgViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(SponsorRedPkgViewHolder holder, int position) {
        if (mData.get(position) instanceof SponsorRedPkgResponse.DataBeanX.DataBean) {
            holder.redPkgFrom.setText(((SponsorRedPkgResponse.DataBeanX.DataBean) mData.get(position)).getName());
            holder.dataBean = ((SponsorRedPkgResponse.DataBeanX.DataBean) mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }

    public class SponsorRedPkgViewHolder extends RecyclerView.ViewHolder {

        //@Bind(R.id.red_pkg_from)
        TextView redPkgFrom;
        //@Bind(R.id.go_to)
        ImageView goTo;
        SponsorRedPkgResponse.DataBeanX.DataBean dataBean;

        public SponsorRedPkgViewHolder(View view) {
            super(view);
            initView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocalLog.d(TAG, "跳转到" + redPkgFrom.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra(context.getPackageName(), dataBean);
                    intent.setClass(context, SponsorDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        private void initView(View view) {
            redPkgFrom = (TextView) view.findViewById(R.id.red_pkg_from);
        }
    }
}
