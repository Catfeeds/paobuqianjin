package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/2/27.
 */

public class MyReleaseTaskAdapter extends RecyclerView.Adapter<MyReleaseTaskAdapter.MyReleaseTaskViewHolder> {
    private final static String TAG = MyReleaseTaskAdapter.class.getSimpleName();
    Context context;
    List<?> mData;

    public MyReleaseTaskAdapter(Context context, List<TaskMyReleaseResponse.DataBeanX.DataBean> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(MyReleaseTaskViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(MyReleaseTaskViewHolder holder, int position) {
        if (mData.get(position) instanceof TaskMyReleaseResponse.DataBeanX.DataBean) {
            holder.releaseName.setText(((TaskMyReleaseResponse.DataBeanX.DataBean) mData.get(position)).getTask_name());
            holder.releaseFriend.setText("领取人: " + ((TaskMyReleaseResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            String daysFormat = context.getString(R.string.task_days);
            String daysStr = String.format(daysFormat, ((TaskMyReleaseResponse.DataBeanX.DataBean) mData.get(position)).getTask_days());
            holder.releaseDays.setText(daysStr);
            holder.taskId = ((TaskMyReleaseResponse.DataBeanX.DataBean) mData.get(position)).getId();
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

    @Override
    public MyReleaseTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyReleaseTaskViewHolder(LayoutInflater.from(context).inflate(R.layout.my_release_list, parent, false));
    }


    public class MyReleaseTaskViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.release_name)
        TextView releaseName;
        @Bind(R.id.release_friend)
        TextView releaseFriend;
        @Bind(R.id.release_days)
        TextView releaseDays;
        @Bind(R.id.release_details)
        Button releaseDetails;
        int taskId = -1;

        public MyReleaseTaskViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            releaseName = (TextView) view.findViewById(R.id.release_name);
            releaseFriend = (TextView) view.findViewById(R.id.release_friend);
            releaseDays = (TextView) view.findViewById(R.id.release_days);
            releaseDetails = (Button) view.findViewById(R.id.release_details);
            releaseDetails.setOnClickListener(onClickListener);

        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.release_details:
                        LocalLog.d(TAG, "查看任务详情 taskid = " + taskId);
                        if (taskId != -1) {
                            Intent intent = new Intent();
                            intent.putExtra("taskid", taskId);

                        }
                        break;
                }
            }
        };
    }
}
