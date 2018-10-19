package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConSumSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecvNearPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedRevHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SendNearPkgResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AddAroundRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.AddConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.RedInfoActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/10/18.
 */

public class ConsumHisAdapter extends RecyclerView.Adapter<ConsumHisAdapter.RedHisViewHolder> {
    private String TAG = ConsumHisAdapter.class.getSimpleName();
    private Context mContext;
    private List<?> mData;
    private Fragment fragment;
    private RecyclerItemClickListener.OnItemClickListener myCustomClickLis;

    public ConsumHisAdapter(Context context, List<?> data, Fragment fragment) {
        mContext = context;
        mData = data;
        this.fragment = fragment;
    }

    public void setMyCustomClickLis(RecyclerItemClickListener.OnItemClickListener myCustomClickLis) {
        this.myCustomClickLis = myCustomClickLis;
    }

    @Override
    public void onBindViewHolder(RedHisViewHolder holder, final int position) {
        if (mData.get(position) instanceof ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) {
            switch (((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getStatus()) {//0正常|1已下架|2已过期|3已领完
                case 0:
                    holder.op.setText(R.string.cut_down);
                    holder.op.setTextColor(mContext.getResources().getColor(R.color.color_6c71c4));
                    holder.op.setBackgroundResource(R.drawable.shape_14_dp_purple);
                    holder.op.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (myCustomClickLis != null)
                                LocalLog.d(TAG, "下架");
                            myCustomClickLis.OnItemClick(view, position);
                        }
                    });
                    break;
                case 1:
                    holder.op.setText(R.string.already_cut_down);
                    holder.op.setTextColor(mContext.getResources().getColor(R.color.color_9e9e9e));
                    holder.op.setBackgroundResource(R.drawable.shape_14_dp_gray);
                    holder.op.setOnClickListener(null);
                    break;
                case 2:
                    holder.op.setText(R.string.already_over_date);
                    holder.op.setTextColor(mContext.getResources().getColor(R.color.color_9e9e9e));
                    holder.op.setBackgroundResource(R.drawable.shape_14_dp_gray);
                    holder.op.setOnClickListener(null);
                    break;
                case 3:
                    holder.op.setText(R.string.already_get_all);
                    holder.op.setTextColor(mContext.getResources().getColor(R.color.color_9e9e9e));
                    holder.op.setBackgroundResource(R.drawable.shape_14_dp_gray);
                    holder.op.setOnClickListener(null);
                    break;
            }

            Presenter.getInstance(mContext).getPlaceErrorImage(holder.headIcon, ((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.userName.setText(((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getNickname());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.redImg0, ((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getVimg()
                    , R.drawable.bitmap_null, R.drawable.bitmap_null);
            holder.redDes.setText(((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getContent());
            holder.likeNum.setText(((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getZan_count());
            holder.commentNum.setText(((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getComment_count());
            long recTime = ((ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(position)).getCreate_time();
            String timeStr = DateTimeUtil.formatDateTime(recTime * 1000, DateTimeUtil.DF_MM_DD_HH_mm);
            holder.textTime.setText(timeStr);
        }
    }

    @Override
    public RedHisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RedHisViewHolder(LayoutInflater.from(mContext).inflate(R.layout.consum_record_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
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
        RelativeLayout editSpan;
        Button op;
        ImageView rePayRed;

        public RedHisViewHolder(View view) {
            super(view);
            initView(view);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.put_red:
                        LocalLog.d(TAG, "再次发红包");
                        if (mData.get(getAdapterPosition()) instanceof ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) {
                            Intent putRedIntent = new Intent();
                            putRedIntent.putExtra("tick_info", (ConSumSendHisResponse.DataBeanX.SendListBean.DataBean) mData.get(getAdapterPosition()));
                            putRedIntent.setClass(mContext, AddConsumptiveRedBagActivity.class);
                            fragment.startActivityForResult(putRedIntent, 1);
                        }
                        break;
                    case R.id.content_layout:
                        LocalLog.d(TAG,"查看历史记录");
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
            editSpan = (RelativeLayout) view.findViewById(R.id.edit_span);
            op = (Button) view.findViewById(R.id.op);
            rePayRed = (ImageView) view.findViewById(R.id.put_red);
            op.setOnClickListener(onClickListener);
            rePayRed.setOnClickListener(onClickListener);
        }
    }
}
