package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/3/2.
 */

public class UserDynamicRecordSecondAdapter extends RecyclerView.Adapter<UserDynamicRecordSecondAdapter.UserDynamicRecordSecondViewHolder> {
    private final static String TAG = UserDynamicRecordSecondAdapter.class.getSimpleName();
    Context context;
    List<?> mData;


    public UserDynamicRecordSecondAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public UserDynamicRecordSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDynamicRecordSecondViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamic_item_times, parent, false));
    }

    @Override
    public void onBindViewHolder(UserDynamicRecordSecondViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(UserDynamicRecordSecondViewHolder holder, int position) {
        LocalLog.d(TAG, "updateListItem() enter");

    }

    public class UserDynamicRecordSecondViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.dynamic_release_time)
        TextView dynamicReleaseTime;
        @Bind(R.id.dot)
        ImageView dot;
        @Bind(R.id.dynamic_des)
        TextView dynamicDes;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.pic_viewpager)
        RelativeLayout picViewpager;
        @Bind(R.id.content_numbers)
        TextView contentNumbers;
        @Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        @Bind(R.id.content_supports)
        TextView contentSupports;
        @Bind(R.id.like_num_icon)
        ImageView likeNumIcon;
        @Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;

        public UserDynamicRecordSecondViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            dynamicReleaseTime = (TextView) view.findViewById(R.id.dynamic_release_time);
            dynamicDes = (TextView) view.findViewById(R.id.dynamic_des);
            image = (ImageView)view.findViewById(R.id.image);
            contentNumbers = (TextView)view.findViewById(R.id.content_numbers);
        }
    }
}
