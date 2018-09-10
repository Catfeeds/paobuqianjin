package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearRedInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundRedInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;

import java.util.List;
import java.util.logging.Handler;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/8/21.
 */

public class RedRecListAdapter extends RecyclerView.Adapter<RedRecListAdapter.RedRecViewHolder> {
    @Bind(R.id.head_icon)
    CircleImageView headIcon;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.money)
    TextView money;
    private Context context;
    private List<?> mData;

    public RedRecListAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(RedRecViewHolder holder, int position) {
        if (mData.get(position) instanceof RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) {
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIcon, ((RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (!TextUtils.isEmpty(((RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getNickname())) {
                String name = ((RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getNickname().charAt(0) + "***";
                holder.name.setText(name);
            }
            long time = ((RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getReceive_time();
            String timeStr = DateTimeUtil.formatDateTime(time * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS);
            holder.time.setText(timeStr);
            holder.money.setText("***元");
            holder.userId = ((RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getReceive_uid();
        }else if(mData.get(position) instanceof NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean){
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIcon, ((NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (!TextUtils.isEmpty(((NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getNickname())) {
                String name = ((NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getNickname().charAt(0) + "***";
                holder.name.setText(name);
            }
            long time = ((NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getReceive_time();
            String timeStr = DateTimeUtil.formatDateTime(time * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS);
            holder.time.setText(timeStr);
            holder.money.setText("***元");
            holder.userId = ((NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean) mData.get(position)).getReceive_uid();
        }
    }


    @Override
    public RedRecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RedRecViewHolder(LayoutInflater.from(context).inflate(R.layout.round_red_info_item, parent
                , false));
    }

    public class RedRecViewHolder extends RecyclerView.ViewHolder {
        CircleImageView headIcon;
        TextView name;
        TextView time;
        TextView money;
        int userId = -1;

        RedRecViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            headIcon = (CircleImageView) view.findViewById(R.id.head_icon);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            money = (TextView) view.findViewById(R.id.money);
            headIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userId != -1 && userId != Presenter.getInstance(context).getId()) {
                        Intent intent = new Intent();
                        //TODO ACTION_SCAN_USERID
                        intent.putExtra("userid", userId);
                        intent.setClass(context, FriendDetailActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
