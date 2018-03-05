package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/3/1.
 */
/*
@className :DanAdapter
*@date 2018/3/1
*@author
*@description 段位
*/
public class DanAdapter extends RecyclerView.Adapter<DanAdapter.DanAdapterViewHolder> {
    private final static String TAG = DanAdapter.class.getSimpleName();
    Context context;
    List<?> mData;


    public DanAdapter(Context context, List<DanListResponse.DataBean> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public DanAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DanAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.dan_list_a, parent, false));
    }

    @Override
    public void onBindViewHolder(DanAdapterViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(DanAdapterViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    @TargetApi(19)
    private void updateListItem(DanAdapterViewHolder holder, int position) {
        if (mData.get(position) instanceof DanListResponse.DataBean) {
            if (((DanListResponse.DataBean) mData.get(position)).isFinished()) {
                holder.danIco1.setImageResource(R.drawable.dan_ico2);
                holder.distanceFinish.setImageResource(R.drawable.selected_icon);
                holder.distanceDan.setTextColor(ContextCompat.getColor(context,R.color.color_161727));
            }

            holder.danRank.setText(String.valueOf(((DanListResponse.DataBean) mData.get(position)).getId()));

            String processDanFormat = context.getString(R.string.steps_should_add_to);
            String processDanStr = String.format(processDanFormat, ((DanListResponse.DataBean) mData.get(position)).getTarget());
            holder.distanceDan.setText(processDanStr);

            String peopleNumFormat = context.getString(R.string.people_finished);
            String peopleNumStr = String.format(peopleNumFormat, ((DanListResponse.DataBean) mData.get(position)).getNumber());
            holder.danDes.setText(peopleNumStr);
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class DanAdapterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dan_ico1)
        ImageView danIco1;
        @Bind(R.id.dan_rank)
        TextView danRank;
        @Bind(R.id.distance_finish)
        ImageView distanceFinish;
        @Bind(R.id.distance_dan)
        TextView distanceDan;
        @Bind(R.id.dan_level_rel)
        RelativeLayout danLevelRel;
        @Bind(R.id.dan_des)
        TextView danDes;

        public DanAdapterViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            danIco1 = (ImageView) view.findViewById(R.id.dan_ico1);
            danRank = (TextView) view.findViewById(R.id.dan_rank);
            distanceFinish = (ImageView) view.findViewById(R.id.distance_finish);
            distanceDan = (TextView) view.findViewById(R.id.distance_dan);
            danDes = (TextView) view.findViewById(R.id.dan_des);
        }
    }

}
