package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedRevHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedSendHisResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/8/18.
 */

public class RedHisAdapter extends RecyclerView.Adapter<RedHisAdapter.RedHisViewHolder> {
    private final static String TAG = RedHisAdapter.class.getSimpleName();
    private Context mContext;
    private List<?> mData;

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

        public RedHisViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            headIcon = (CircleImageView) view.findViewById(R.id.head_icon);
            userName = (TextView) view.findViewById(R.id.user_name);
            redImg0 = (ImageView) view.findViewById(R.id.red_img0);
            redDes = (TextView) view.findViewById(R.id.red_des);
            textTime = (TextView) view.findViewById(R.id.text_time);
            likeNum = (TextView) view.findViewById(R.id.like_num);
            commentNum = (TextView) view.findViewById(R.id.comment_num);
        }
    }
}
