package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.UserCenterVoteData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserCenterResponse;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by pbq on 2018/3/2.
 */
/*
@className :UserDynamicRecordAdapter
*@date 2018/3/2
*@author
*@description 动态日记
*/
public class UserDynamicRecordAdapter extends RecyclerView.Adapter<UserDynamicRecordAdapter.UserDynamicRecordViewHolder> {
    private final static String TAG = UserDynamicRecordAdapter.class.getSimpleName();
    Activity context;
    List<List> mData;
    private Fragment fragment;

    public UserDynamicRecordAdapter(Activity context, List<List> map, Fragment fragment) {
        this.context = context;
        this.mData = map;
        this.fragment = fragment;
    }

    public void notifyItemChange(int topPosition, int position, int is_vote, int vote, int comment) {
        UserCenterVoteData userCenterVoteData = new UserCenterVoteData();
        if (position > -1) {
            userCenterVoteData.setPosition(position);
        }
        if (is_vote > -1) {
            userCenterVoteData.setIs_vote(is_vote);
        }
        if (vote > -1) {
            userCenterVoteData.setVote(vote);
        }
        if (comment > -1) {
            userCenterVoteData.setComment(comment);
        }
        super.notifyItemChanged(topPosition, userCenterVoteData);
    }

    @Override
    public void onBindViewHolder(UserDynamicRecordViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        int size = payloads.size();
        LocalLog.d(TAG, "size = " + payloads.size());
        if (size == 1) {
            UserCenterVoteData userCenterVoteData = (UserCenterVoteData) payloads.get(0);
            LocalLog.d(TAG, "userCenterVoteData = " + userCenterVoteData.toString());
            if (holder.userDynamicRecordSecondAdapter != null) {
                holder.userDynamicRecordSecondAdapter.notifyItemChanged(userCenterVoteData.getPosition(), userCenterVoteData);
            }else{
                LocalLog.d(TAG,"userDynamicRecordSecondAdapter  is null");
            }
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
    public void onBindViewHolder(UserDynamicRecordViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(UserDynamicRecordViewHolder holder, int position) {
        LocalLog.d(TAG, "position = " + position);
        int size = mData.get(position).size();
        LocalLog.d(TAG, "当天日子条数 size");
        if (size > 0) {
            if (mData.get(position).get(0) instanceof UserCenterResponse.DataBeanX.DynamicDataBean.DataBean) {
                long create_time = ((UserCenterResponse.DataBeanX.DynamicDataBean.DataBean) mData.get(position).get(0)).getCreate_time();
                String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
                holder.date.setText(create_timeStr);
            }
        }
        holder.dayDynamicRecycler.addItemDecoration(new UserDynamicRecordSecondAdapter.SpaceItemDecoration(30));
        if (holder.userDynamicRecordSecondAdapter == null) {
            holder.userDynamicRecordSecondAdapter = new UserDynamicRecordSecondAdapter(context, mData.get(position), fragment, position);
            holder.dayDynamicRecycler.setAdapter(holder.userDynamicRecordSecondAdapter);
        }

    }

    @Override
    public UserDynamicRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDynamicRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamic_item_date, parent, false));
    }

    public class UserDynamicRecordViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.day_dynamic_recycler)
        RecyclerView dayDynamicRecycler;
        LinearLayoutManager layoutManager;
        UserDynamicRecordSecondAdapter userDynamicRecordSecondAdapter;

        public UserDynamicRecordViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            date = (TextView) view.findViewById(R.id.date);
            layoutManager = new LinearLayoutManager(context);
            dayDynamicRecycler = (RecyclerView) view.findViewById(R.id.day_dynamic_recycler);
            dayDynamicRecycler.setLayoutManager(layoutManager);
        }
    }

    //设置RecyclerView item间距
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else {
                //outRect.left = mSpace;
                LocalLog.d(TAG, "mSpace = " + mSpace);
                outRect.top = mSpace;
            }
            /*if (parent.getChildAdapterPosition(view) == UserDynamicRecordAdapter.this.mData.size() - 1) {
                outRect.right = 0;
                LocalLog.d(TAG, "getItemOffsets() last set");
            } else {
                outRect.right = mSpace;
            }*/
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
