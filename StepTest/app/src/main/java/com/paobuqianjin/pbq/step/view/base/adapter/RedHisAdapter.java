package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedHisResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.umeng.commonsdk.debug.D;

import java.util.List;
import java.util.logging.Handler;

import butterknife.Bind;
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
        if (mData.get(position) instanceof RedHisResponse.DataBeanX.RedpacketListBean.DataBean) {
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.headIcon, ((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.userName.setText(((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getUser_nickname());
            Presenter.getInstance(mContext).getPlaceErrorImage(holder.redImg0, ((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_img()
                    , R.drawable.bitmap_null, R.drawable.bitmap_null);
            holder.redDes.setText(((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getMap_content());
            holder.likeNum.setText(((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getZan_count());
            holder.commentNum.setText(((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getComment_count());
            long recTime = ((RedHisResponse.DataBeanX.RedpacketListBean.DataBean) mData.get(position)).getReceive_time();
            String timeStr = DateTimeUtil.formatDateTime(recTime, DateTimeUtil.DF_MM_DD_HH_mm);
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
