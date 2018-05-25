package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
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
    Activity context;
    List<?> mData;
    private final static String FOLLOW_OTO_ACTION = "com.paobuqianjin.pbq.action.OTO_ACTION";
    private final static String MY_FOLLOW_ACTION = "com.paobuqianjin.pbq.action.MY_FOLLOW_ACTION";
    private final static String FOLLOW_ME_ACTION = "com.paobuqianjin.pbq.action.FOLLOME_ACTION";
    private LocalBroadcastManager localBroadcastManager;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private TranslateAnimation animationCircleType;

    public FollowAdapter(Activity context, List<?> data) {
        this.context = context;
        mData = data;
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }


    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
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
        LocalLog.d(TAG, "updateListItem() enter");
        if (mData.get(position) instanceof FollowUserResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "关注我的");
            holder.dearName.setText(((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            Presenter.getInstance(context).getPlaceErrorImage(holder.userNearIcon, ((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
            holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_6c71c4));
            holder.stytle = 0;
            holder.btFollow.setText("关注");
            /*if (((FollowUserResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
        } else if (mData.get(position) instanceof UserIdFollowResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "我关注的");
            holder.dearName.setText(((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            Presenter.getInstance(context).getPlaceErrorImage(holder.userNearIcon, ((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.btFollow.setText("已关注");
            holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_not_fllow_nearby));
            holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_646464));
            holder.stytle = 1;
            /*if (((UserIdFollowResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
        } else if (mData.get(position) instanceof UserFollowOtOResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "互相关注");
            holder.dearName.setText(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            Presenter.getInstance(context).getPlaceErrorImage(holder.userNearIcon, ((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.btFollow.setText("互相关注");
            holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
            holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_6c71c4));
            holder.stytle = 2;
            /*if (((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
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
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int userid = -1;
        int stytle = -1;

        public FollowViewHolder(View view) {
            super(view);
            initView(view);
        }

        private InnerCallBack innerCallBack = new InnerCallBack() {
            @Override
            public void innerCallBack(Object object) {
                if (object instanceof AddDeleteFollowResponse) {
                    if (((AddDeleteFollowResponse) object).getMessage().equals("取消关注成功")) {
                        LocalLog.d(TAG, "取消关注");
                        switch (btFollow.getText().toString()) {
                            case "关注":
                                break;
                            case "已关注":
                                mData.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                break;
                            case "互相关注":
                                Intent intent = new Intent(FOLLOW_OTO_ACTION);
                                FollowUserResponse.DataBeanX.DataBean dataBean = new FollowUserResponse.DataBeanX.DataBean();
                                dataBean.setUserid(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getUserid());
                                dataBean.setId(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getUserid());
                                dataBean.setNickname(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getNickname());
                                dataBean.setAvatar(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getAvatar());
                                dataBean.setSex(((UserFollowOtOResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getSex());
                                intent.putExtra("friendinfo", dataBean);
                                mData.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                localBroadcastManager.sendBroadcast(intent);
                                break;
                        }
                    } else {
                        LocalLog.d(TAG, "关注");
                        switch (btFollow.getText().toString()) {
                            case "关注":
                                Intent intent = new Intent(FOLLOW_ME_ACTION);
                                UserFollowOtOResponse.DataBeanX.DataBean dataBean = new UserFollowOtOResponse.DataBeanX.DataBean();
                                dataBean.setUserid(((FollowUserResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getUserid());
                                dataBean.setNickname(((FollowUserResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getNickname());
                                dataBean.setAvatar(((FollowUserResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getAvatar());
                                dataBean.setSex(((FollowUserResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getSex());
                                dataBean.setFollowid(((FollowUserResponse.DataBeanX.DataBean) mData.get(getAdapterPosition())).getUserid());
                                intent.putExtra("friendinfo", dataBean);

                                mData.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                localBroadcastManager.sendBroadcast(intent);
                                break;
                            case "已关注":
                                mData.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                break;
                            case "互相关注":
                                mData.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                break;
                        }
                    }
                }
            }
        };
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
                        LocalLog.d(TAG, "style = " + stytle + ",content " + btFollow.getText());
                        if (btFollow.getText().toString().equals("互相关注")) {
                            popCancelPointConfirm("取消关注");
                        } else {
                            Presenter.getInstance(context).postAddUserFollow(innerCallBack, userid);
                        }
                        break;
                }
            }
        };

        private void popCancelPointConfirm(String title) {
            LocalLog.d(TAG, "popQuitConfirm() enter 退圈确认");
            popCircleOpBar = View.inflate(context, R.layout.quit_circle_confirm, null);
            TextView titleTV = (TextView) popCircleOpBar.findViewById(R.id.quit_title);
            titleTV.setText(title);
            popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    popupOpWindow = null;
                }
            });
            TextView cancelText = (TextView) popCircleOpBar.findViewById(R.id.cancel_quit_text);
            cancelText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocalLog.d(TAG, "取消退圈动作");
                    popupOpWindow.dismiss();
                }
            });
            TextView confirmText = (TextView) popCircleOpBar.findViewById(R.id.confirm_quit_text);
            confirmText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Presenter.getInstance(context).postAddUserFollow(innerCallBack, userid);
                    popupOpWindow.dismiss();
                }
            });


            popupOpWindow.setFocusable(true);
            popupOpWindow.setOutsideTouchable(true);
            popupOpWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new

                    AccelerateInterpolator());
            animationCircleType.setDuration(200);

            popupOpWindow.showAtLocation(context.findViewById(R.id.my_friend_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            popCircleOpBar.startAnimation(animationCircleType);
        }

        private void initView(View viewRoot) {
            userNearIcon = (CircleImageView) viewRoot.findViewById(R.id.user_near_icon);
            dearName = (TextView) viewRoot.findViewById(R.id.dear_name);
            btFollow = (Button) viewRoot.findViewById(R.id.bt_follow);
            btFollow.setOnClickListener(onClickListener);
            userNearIcon.setOnClickListener(onClickListener);
            vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        }
    }
}
