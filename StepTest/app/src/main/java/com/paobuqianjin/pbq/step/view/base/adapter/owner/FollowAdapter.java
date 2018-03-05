package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/1.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FollowViewHolder> {
    private final static String TAG = FollowAdapter.class.getSimpleName();
    Context context;
    List<?> mData;

    public FollowAdapter(Context context, List<?> data) {
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
    public void onBindViewHolder(FollowViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    @TargetApi(19)
    private void updateListItem(FollowViewHolder holder, int position) {
        LocalLog.d(TAG,"updateListItem() enter");
        if (mData.get(position) instanceof FollowUserResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "关注我的");
            holder.dearName.setText(((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            Presenter.getInstance(context).getImage(holder.userNearIcon, ((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.btFollow.setBackground(ContextCompat.getDrawable(context,R.drawable.has_fllow_nearby));
            holder.btFollow.setTextColor(ContextCompat.getColor(context,R.color.color_6c71c4));
            holder.stytle = 0;
            holder.btFollow.setText("关注");
        } else if (mData.get(position) instanceof UserIdFollowResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "我关注的");
            holder.dearName.setText(((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            Presenter.getInstance(context).getImage(holder.userNearIcon, ((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.btFollow.setText("已关注");
            holder.btFollow.setBackground(ContextCompat.getDrawable(context,R.drawable.has_not_fllow_nearby));
            holder.btFollow.setTextColor(ContextCompat.getColor(context,R.color.color_646464));
            holder.stytle = 1;
        } else if (mData.get(position) instanceof UserFollowOtOResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "互相关注");
            holder.dearName.setText(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            Presenter.getInstance(context).getImage(holder.userNearIcon, ((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.btFollow.setText("互相关注");
            holder.btFollow.setBackground(ContextCompat.getDrawable(context,R.drawable.has_fllow_nearby));
            holder.btFollow.setTextColor(ContextCompat.getColor(context,R.color.color_6c71c4));
            holder.stytle = 2;
        } else {
            LocalLog.d(TAG, "not match");
        }
    }

    @Override
    public FollowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FollowViewHolder(LayoutInflater.from(context).inflate(R.layout.follow_list_item, parent, false));
    }

    public class FollowViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_near_icon)
        CircleImageView userNearIcon;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.bt_follow)
        Button btFollow;
        int userid = -1;
        int stytle = -1;

        public FollowViewHolder(View view) {
            super(view);
            initView(view);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.user_near_icon:
                        LocalLog.d(TAG, "头像被点击");
                        Intent intent = new Intent();
                        intent.putExtra("userid", userid);
                        intent.setClass(context, UserCenterActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.bt_follow:
                        LocalLog.d(TAG, "style = " + stytle + ",content "+btFollow.getText());
                        break;
                }
            }
        };

        private void initView(View viewRoot) {
            userNearIcon = (CircleImageView) viewRoot.findViewById(R.id.user_near_icon);
            dearName = (TextView) viewRoot.findViewById(R.id.dear_name);
            btFollow = (Button) viewRoot.findViewById(R.id.bt_follow);
            btFollow.setOnClickListener(onClickListener);
            userNearIcon.setOnClickListener(onClickListener);
        }
    }
}
