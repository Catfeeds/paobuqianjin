package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AddressPerson;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PhoneCheckResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SearchResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import org.json.JSONException;

import java.util.HashMap;
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
    Activity context;
    List<?> mData;
    private NormalDialog normalDialog;

    public LocalContactAdapter(Activity context, List<?> data) {
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

    private void updateListItem(final LocalContactViewHolder holder, int position) {
        if (mData.get(position) instanceof AddressPerson) {
            holder.dearName.setText(((AddressPerson) mData.get(position)).getName());
            holder.btFollow.setText("邀请");
            holder.phoneNum = ((AddressPerson) mData.get(position)).getPhone();
            holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
        } else if (mData.get(position) instanceof SearchResponse.DataBean) {
            Presenter.getInstance(context).getPlaceErrorImage(holder.userNearIcon, ((SearchResponse.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.dearName.setText(((SearchResponse.DataBean) mData.get(position)).getNickname());
            holder.userid = ((SearchResponse.DataBean) mData.get(position)).getUserid();
            if (holder.userid != Presenter.getInstance(context).getId()) {
                holder.btFollow.setVisibility(View.VISIBLE);
                if (((SearchResponse.DataBean) mData.get(position)).getFollow() == 1) {
                    holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_646464));
                    holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_not_fllow_nearby));
                    holder.btFollow.setText("已关注");
                } else if (((SearchResponse.DataBean) mData.get(position)).getFollow() == 0) {
                    holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_6c71c4));
                    holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
                    holder.btFollow.setText("关注");
                }
            } else {
                holder.btFollow.setText("");
                holder.btFollow.setVisibility(View.GONE);
            }

            holder.userNearIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocalLog.d(TAG, "头像被点击");
                    if (holder.userid != -1) {
                        Intent intent = new Intent();
                        intent.putExtra("userid", holder.userid);
                        intent.setClass(context, FriendDetailActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public LocalContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocalContactViewHolder(LayoutInflater.from(context).inflate(R.layout.local_constract_list_item, parent, false));
    }

    public void popFollowButton(final int userId) {
        LocalLog.d(TAG, "popFollowButton () enter");
        if (context == null) {
            return;
        }
        if (normalDialog == null) {
            normalDialog = new NormalDialog(context);
            normalDialog.setMessage("确定关注好友!");
            normalDialog.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    normalDialog.dismiss();
                }
            });

            normalDialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    normalDialog.dismiss();
                    Map<String, String> param = new HashMap<>();
                    param.put("userid", String.valueOf(Presenter.getInstance(context).getId()));
                    param.put("followid", String.valueOf(userId));
                    Presenter.getInstance(context).postPaoBuSimple(NetApi.urlUserFollow, param, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            PaoToastUtils.showLongToast(context, "关注成功!");
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                        }
                    });
                }
            });
        }
        if (!normalDialog.isShowing()) {
            normalDialog.show();
        }
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
                                Map<String, String> params = new HashMap<>();
                                params.put("phone", phoneNum);
                                Presenter.getInstance(context).postPaoBuSimple(NetApi.urlCheckFriend, params, new PaoCallBack() {
                                    @Override
                                    protected void onSuc(String s) {
                                        try {
                                            PhoneCheckResponse checkResponse = new Gson().fromJson(s, PhoneCheckResponse.class);
                                            if (checkResponse.getData().getStatus() == 0) {
                                                Utils.sendSMS(context, phoneNum, "我正在用今年最火爆的运动就能领红包的APP跑步钱进并领到红包，他们正在烧钱推广，下载就能领红包http://share.runmoneyin.com/in.html?" + Presenter.getInstance(context).getNo());
                                            } else {
                                                if (checkResponse.getData() != null && (checkResponse.getData().getFollow_type() == 0
                                                        || checkResponse.getData().getFollow_type() == 1)) {
                                                    LocalLog.d(TAG, "去关注");
                                                    popFollowButton(checkResponse.getData().getUserid());
                                                } else if (checkResponse.getData() != null && (checkResponse.getData().getFollow_type() == 2
                                                        || checkResponse.getData().getFollow_type() == 3)) {
                                                    LocalLog.d(TAG, "已关注");
                                                    PaoToastUtils.showLongToast(context, "已关注");
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            PaoToastUtils.showLongToast(context, "开小差了，请稍后再试");
                                        }
                                    }

                                    @Override
                                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                                        if (errorBean != null) {
                                            PaoToastUtils.showLongToast(context, errorBean.getMessage());
                                        }
                                    }
                                });
//                                Presenter.getInstance(context).inviteMsg(phoneNum, btFollow);

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
