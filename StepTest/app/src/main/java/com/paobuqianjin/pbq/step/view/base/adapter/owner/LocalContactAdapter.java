package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/2.
 */
/*
@className :LocalContactAdapter
*@date 2018/3/2
*@author
*@description 本地联系人
*/
public class LocalContactAdapter extends RecyclerView.Adapter<LocalContactAdapter.LocalContactViewHolder> {
    private final static String TAG = LocalContactAdapter.class.getSimpleName();
    Context context;
    List<?> mData;


    public LocalContactAdapter(Context context, List<?> data) {
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
    public void onBindViewHolder(LocalContactViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(LocalContactViewHolder holder, int position) {
        if (mData.get(position) instanceof FriendAddResponse.DataBean.InSystemBean) {
            Presenter.getInstance(context).getPlaceErrorImage(holder.userNearIcon, ((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.dearName.setText(((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getName());
            if (((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getFollow_type() == 2) {
                holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_646464));
                holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_not_fllow_nearby));
                holder.btFollow.setText("已关注");
            } else if (((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getFollow_type() == 3) {
                holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_6c71c4));
                holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
                holder.btFollow.setText("互相关注");
            } else if (((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getFollow_type() == 0) {
                holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_6c71c4));
                holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
                holder.btFollow.setText("关注");
            }
            holder.phoneNum = ((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getPhone();
           /* if (((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
            holder.userid = ((FriendAddResponse.DataBean.InSystemBean) mData.get(position)).getUserid();
        } else if (mData.get(position) instanceof FriendAddResponse.DataBean.OutSystemBean) {
            holder.dearName.setText(((FriendAddResponse.DataBean.OutSystemBean) mData.get(position)).getName());
            holder.btFollow.setText("邀请");
            holder.phoneNum = ((FriendAddResponse.DataBean.OutSystemBean) mData.get(position)).getPhone();
            holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
        }
    }

    @Override
    public LocalContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocalContactViewHolder(LayoutInflater.from(context).inflate(R.layout.local_constract_list_item, parent, false));
    }

    public class LocalContactViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_near_icon)
        CircleImageView userNearIcon;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.bt_follow)
        Button btFollow;
        String phoneNum;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int userid = -1;

        public LocalContactViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            userNearIcon = (CircleImageView) view.findViewById(R.id.user_near_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            btFollow = (Button) view.findViewById(R.id.bt_follow);
            btFollow.setOnClickListener(onClickListener);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_follow:
                        switch (btFollow.getText().toString()) {
                            case "邀请":
                                //TODO 发送邀请
                                LocalLog.d(TAG, "邀请好友");
                                Presenter.getInstance(context).inviteMsg(phoneNum, btFollow);
                                break;
                            case "已关注":
                                LocalLog.d(TAG, "取消关注");
                                if (userid != -1) {
                                    Presenter.getInstance(context).postUserStatus(btFollow, userid);
                                }
                                break;
                            case "关注":
                                LocalLog.d(TAG, "取消关注");
                                if (userid != -1) {
                                    Presenter.getInstance(context).postUserStatus(btFollow, userid);
                                }
                                break;
                            case "互相关注":
                                LocalLog.d(TAG, "取消关注");
                                if (userid != -1) {
                                    Presenter.getInstance(context).postUserStatus(btFollow, userid);
                                }
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
