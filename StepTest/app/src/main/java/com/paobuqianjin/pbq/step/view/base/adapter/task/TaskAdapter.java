package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.ReceiveTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.TaskDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/25.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final static String TAG = TaskAdapter.class.getSimpleName();
    private final static int defaultCount = 5;
    private Context context;
    private List<MyRecTaskRecordResponse.DataBeanX.DataBean> mData;
    private ReceiveTaskInterface receiveTaskInterface;
    private final static String REC_TASK_ACTION = "com.paobuqianjin.pbq.step.REC_TASK_ACTION";
    private final static String REC_GIFT_ACTION = "com.paobuqianjin.pbq.step.REC_GIFT_ACTION";
    private LocalBroadcastManager localBroadcastManager;


    public TaskAdapter(Context context) {
        super();
        this.context = context;
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public void notifyDataSetChanged(List<MyRecTaskRecordResponse.DataBeanX.DataBean> data) {
        mData = data;
        super.notifyDataSetChanged();
    }

    public void notifyAddData(MyRecTaskRecordResponse.DataBeanX.DataBean dataBean) {
        if (mData != null) {
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getId() != dataBean.getId()) {
                    mData.add(dataBean);
                } else {
                    mData.remove(i);
                    mData.add(dataBean);
                }
            }
        } else {
            mData = new ArrayList<>();
            mData.add(dataBean);
        }
        super.notifyItemRangeInserted(mData.size() - 1, 1);
    }

    public void setReceiveTaskInterface(ReceiveTaskInterface receiveTaskInterface) {
        this.receiveTaskInterface = receiveTaskInterface;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(TaskViewHolder holder, int position) {
        if (mData.get(position) instanceof MyRecTaskRecordResponse.DataBeanX.DataBean) {
            Presenter.getInstance(context).getImage(holder.headIcon, ((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.taskDesc.setText(((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getTask_name());
            holder.taskInvite.setText("派发人: " + ((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.taskGift.setText("奖金:" + String.valueOf(((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getReward_amount()));
            if (((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getIs_receive() == 0) {
                holder.releaseDetails.setText("领取任务");
            } else {
                if (((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getIs_finished() == 0) {
                    holder.releaseDetails.setText("进行中");
                } else {
                    holder.releaseDetails.setText("领取奖励");
                }
            }
            if (((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }

            holder.taskId = ((MyRecTaskRecordResponse.DataBeanX.DataBean) mData.get(position)).getId();
        }
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.head_icon)
        CircleImageView headIcon;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        @Bind(R.id.task_desc)
        TextView taskDesc;
        @Bind(R.id.task_invite)
        TextView taskInvite;
        @Bind(R.id.task_gift)
        TextView taskGift;
        @Bind(R.id.release_details)
        Button releaseDetails;
        @Bind(R.id.task_list_item)
        RelativeLayout taskListItem;
        int taskId = -1;
        ReflashInterface reflashInterface = new ReflashInterface() {
            @Override
            public void notifyReflash(Object object) {
                LocalLog.d(TAG, "ID = " + taskId + "领取成功");
            }
        };

        public TaskViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            headIcon = (CircleImageView) view.findViewById(R.id.head_icon);
            taskDesc = (TextView) view.findViewById(R.id.task_desc);
            taskInvite = (TextView) view.findViewById(R.id.task_invite);
            taskGift = (TextView) view.findViewById(R.id.task_gift);
            releaseDetails = (Button) view.findViewById(R.id.release_details);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
            releaseDetails.setOnClickListener(onClickListener);
            view.setOnClickListener(onClickListener);
        }

        private InnerCallBack innerCallBack = new InnerCallBack() {
            @Override
            public void innerCallBack(Object object) {
                if (object instanceof ErrorCode) {
                    LocalLog.d(TAG, "领取任务或者奖励出错");
                } else if (object instanceof ReceiveTaskResponse) {
                    LocalLog.d(TAG, "领取任务成功");
                    if (((ReceiveTaskResponse) object).getError() == 0) {
                        releaseDetails.setText("进行中");
                        Intent intent = new Intent();
                        intent.setAction(REC_TASK_ACTION);
                        intent.putExtra("taskid", taskId);
                        localBroadcastManager.sendBroadcast(intent);

                    }
                } else if (object instanceof RecPayResponse) {
                    LocalLog.d(TAG, "领取奖励成功");
                    if (((RecPayResponse) object).getError() == 0) {
                        Intent intent = new Intent();
                        intent.setAction(REC_GIFT_ACTION);
                        intent.putExtra("taskid", taskId);
                        localBroadcastManager.sendBroadcast(intent);
                    }
                }
            }
        };
        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.task_list_item:
                        LocalLog.d(TAG, "Item 被点击" + this.toString());
                        if (taskId != -1) {
                            Intent intent = new Intent();
                            intent.putExtra("taskid", taskId);
                            intent.setClass(context, TaskDetailActivity.class);
                            context.startActivity(intent);
                        }
                        break;
                    case R.id.release_details:
                        LocalLog.d(TAG, releaseDetails.getText().toString());
                        switch (releaseDetails.getText().toString()) {
                            case "领取任务":
                                /*if (receiveTaskInterface != null) {
                                    receiveTaskInterface.receiveTask(taskId, reflashInterface);
                                }*/
                                Presenter.getInstance(context).putTask("receive_task", taskId, innerCallBack);
                                break;
                            case "领取奖励":
                                LocalLog.d(TAG, "领取奖励");
                                Presenter.getInstance(context).putTask("receive_reward", taskId, innerCallBack);
                                break;
                            case "进行中":
                                LocalLog.d(TAG, "进行中");
                                Toast.makeText(context, "任务进行中...", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                break;
                        }
                        break;

                }
            }
        };
    }
}
