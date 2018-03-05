package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyReleaseTaskDetailResponse;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/2/28.
 */

public class MyTaskDetailStateAdapter extends RecyclerView.Adapter<MyTaskDetailStateAdapter.MyTaskDetailViewHolder> {
    private final static String TAG = MyTaskDetailStateAdapter.class.getSimpleName();
    List<?> mData;
    Context context;

    public MyTaskDetailStateAdapter(Context context, List<MyReleaseTaskDetailResponse.DataBean.TaskRecordBean> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(MyTaskDetailViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    @TargetApi(19)
    private void updateListItem(MyTaskDetailViewHolder holder, int position) {
        LocalLog.d(TAG, "updateListItem() enter");
        if (mData.get(position) instanceof MyReleaseTaskDetailResponse.DataBean.TaskRecordBean) {
            long time = ((MyReleaseTaskDetailResponse.DataBean.TaskRecordBean) mData.get(position)).getActivity_start_time();
            String date = DateTimeUtil.formatDateTime(time * 1000, DateTimeUtil.DF_YYYY_MM_DD);
            String dates[] = date.split("-");
            String dateStr = "";
            if (dates.length == 3) {
                dateStr = dates[0] + " 年 " + dates[1] + " 月 " + dates[2] + " 日";
            }
            holder.timeStmap.setText(dateStr);
            if (((MyReleaseTaskDetailResponse.DataBean.TaskRecordBean) mData.get(position)).getIs_receive() == 0) {
                holder.states.setText("未领取");
                holder.states.setTextColor(ContextCompat.getColor(context,R.color.color_161727));
            } else if (((MyReleaseTaskDetailResponse.DataBean.TaskRecordBean) mData.get(position)).getIs_receive() == 1) {
                holder.states.setText("已领取");
                holder.states.setTextColor(ContextCompat.getColor(context,R.color.color_e4393c));
            }
        }
    }

    @Override
    public MyTaskDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyTaskDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.my_release_task_detail_state_item, parent, false));
    }

    public class MyTaskDetailViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.time_stmap)
        TextView timeStmap;
        @Bind(R.id.states)
        TextView states;

        public MyTaskDetailViewHolder(View view) {
            super(view);
            initView(view);

        }

        private void initView(View viewRoot) {
            timeStmap = (TextView) viewRoot.findViewById(R.id.time_stmap);
            states = (TextView) viewRoot.findViewById(R.id.states);
        }
    }
}
