package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecvNearPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedRevHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SendNearPkgResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.RedInfoActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorDetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/8/18.
 */

public class RedHisAdapter extends RecyclerView.Adapter<RedHisAdapter.RedHisViewHolder> {
    private final static String TAG = RedHisAdapter.class.getSimpleName();
    private Context mContext;
    private List<?> mData;
    private static String ROUND_RED = "com.paobuqianjin.pbq.ROUND_RED";
    private static String NEAR_READ = "com.paobuqianjin.pbq.NEAR_READ";

    public RedHisAdapter(Context context, List<?> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(RedHisViewHolder holder, int position) {
        if (mData.get(position) instanceof RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) {
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.headIcon, ((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.userName.setText(((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getUser_nickname());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.redImg0, ((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_img()
                    , R.drawable.bitmap_null, R.drawable.bitmap_null);
            holder.redDes.setText(((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_content());
            holder.likeNum.setText(((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getZan_count());
            holder.commentNum.setText(((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getComment_count());
            long recTime = ((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getReceive_time();
            String timeStr = DateTimeUtil.formatDateTime(recTime * 1000, DateTimeUtil.DF_MM_DD_HH_mm);
            holder.textTime.setText(timeStr);
            holder.userId = ((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getFrom_uid();
        } else if (mData.get(position) instanceof RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) {
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.headIcon, ((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.userName.setText(((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getUser_nickname());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.redImg0, ((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_img()
                    , R.drawable.bitmap_null, R.drawable.bitmap_null);
            holder.redDes.setText(((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_content());
            holder.likeNum.setText(((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getZan_count());
            holder.commentNum.setText(((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getComment_count());
            long recTime = ((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getCreate_time();
            String timeStr = DateTimeUtil.formatDateTime(recTime * 1000, DateTimeUtil.DF_MM_DD_HH_mm);
            holder.textTime.setText(timeStr);
        } else if (mData.get(position) instanceof RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) {
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.headIcon, ((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.userName.setText(((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getUser_nickname());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.redImg0, ((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getRed_img()
                    , R.drawable.bitmap_null, R.drawable.bitmap_null);
            holder.redDes.setText(((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getRed_content());
            holder.likeNum.setText(((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getZan_count());
            holder.commentNum.setText(((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getComment_count());
            long recTime = ((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getReceive_time();
            String timeStr = DateTimeUtil.formatDateTime(recTime * 1000, DateTimeUtil.DF_MM_DD_HH_mm);
            holder.textTime.setText(timeStr);
        } else if (mData.get(position) instanceof SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) {
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.headIcon, ((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.userName.setText(((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getUser_nickname());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.redImg0, ((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_img()
                    , R.drawable.bitmap_null, R.drawable.bitmap_null);
            holder.redDes.setText(((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getRed_content());
            holder.likeNum.setText(((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getZan_count());
            holder.commentNum.setText(((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getComment_count());
            long recTime = ((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getCreate_time();
            String timeStr = DateTimeUtil.formatDateTime(recTime * 1000, DateTimeUtil.DF_MM_DD_HH_mm);
            holder.textTime.setText(timeStr);
        }
    }

    @Override
    public RedHisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RedHisViewHolder(LayoutInflater.from(mContext).inflate(R.layout.red_record_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }

    public class RedHisViewHolder extends RecyclerView.ViewHolder {
        CircleImageView headIcon;
        TextView userName;
        ImageView redImg0;
        TextView redDes;
        TextView textTime;
        TextView likeNum;
        TextView commentNum;
        String userId;
        LinearLayout contentLinear;
        TextView repay;

        public RedHisViewHolder(View view) {
            super(view);
            initView(view);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.repay:
                        LocalLog.d(TAG, "再一次发该红包");

                        break;
                    case R.id.content_layout:
                        String redId = "";
                        Intent intent = new Intent();
                        if (mData.get(getAdapterPosition()) instanceof RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) {
                            redId = ((RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(getAdapterPosition())).getRed_id();
                            intent.setAction(ROUND_RED);
                            intent.putExtra(mContext.getPackageName() + "red_id", redId);
                            intent.setClass(mContext, RedInfoActivity.class);
                        } else if (mData.get(getAdapterPosition()) instanceof RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) {
                            redId = ((RedSendHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(getAdapterPosition())).getRed_id();
                            intent.setAction(ROUND_RED);
                            intent.putExtra(mContext.getPackageName() + "red_id", redId);
                            intent.setClass(mContext, RedInfoActivity.class);
                        } else if (mData.get(getAdapterPosition()) instanceof RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) {
                            redId = ((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(getAdapterPosition())).getRed_id();
                            String busid = String.valueOf(((RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(getAdapterPosition())).getBusinessid());
                            intent.putExtra(mContext.getPackageName() + "businessid", Integer.parseInt(busid));
                            intent.setAction(NEAR_READ);
                            intent.putExtra(mContext.getPackageName() + "red_id", Integer.parseInt(redId));
                            intent.putExtra(mContext.getPackageName() + "info", "info");
                            intent.setClass(mContext, SponsorDetailActivity.class);
                        } else if (mData.get(getAdapterPosition()) instanceof SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) {
                            redId = ((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(getAdapterPosition())).getRed_id();
                            String busid = String.valueOf(((SendNearPkgResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(getAdapterPosition())).getBusinessid());
                            intent.putExtra(mContext.getPackageName() + "businessid", Integer.parseInt(busid));
                            intent.putExtra(mContext.getPackageName() + "info", "info");
                            intent.putExtra(mContext.getPackageName() + "red_id", Integer.parseInt(redId));
                            intent.setClass(mContext, SponsorDetailActivity.class);
                            intent.setAction(NEAR_READ);
                        }
                        mContext.startActivity(intent);
                        break;
                    case R.id.head_icon:
                        if (!TextUtils.isEmpty(userId)) {
                            try {
                                Intent intentIco = new Intent();
                                //TODO ACTION_SCAN_USERID
                                intentIco.putExtra("userid", Integer.parseInt(userId));
                                intentIco.setClass(mContext, FriendDetailActivity.class);
                                mContext.startActivity(intentIco);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        };

        private void initView(View view) {
            headIcon = (CircleImageView) view.findViewById(R.id.head_icon);
            userName = (TextView) view.findViewById(R.id.user_name);
            redImg0 = (ImageView) view.findViewById(R.id.red_img0);
            redDes = (TextView) view.findViewById(R.id.red_des);
            textTime = (TextView) view.findViewById(R.id.text_time);
            likeNum = (TextView) view.findViewById(R.id.like_num);
            commentNum = (TextView) view.findViewById(R.id.comment_num);
            contentLinear = (LinearLayout) view.findViewById(R.id.content_layout);
            contentLinear.setOnClickListener(onClickListener);
            headIcon.setOnClickListener(onClickListener);
            repay = (TextView) view.findViewById(R.id.repay);
            repay.setOnClickListener(onClickListener);
        }
    }
}
