package com.paobuqianjin.pbq.step.view.base.adapter.dan;

import android.annotation.TargetApi;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/2/5.
 */

public class NearByAdapter extends RecyclerView.Adapter<NearByAdapter.NearByViewHolder> {
    private final static String TAG = NearByAdapter.class.getSimpleName();
    private Context context;
    List<?> mData;

    public NearByAdapter(Context context, List<NearByResponse.DataBeanX.DataBean> data) {
        this.context = context;
        mData = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public NearByViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NearByViewHolder(LayoutInflater.from(context).inflate(R.layout.near_by_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NearByViewHolder holder, int position) {
        upDateListItem(holder, position);
    }

    @TargetApi(19)
    private void upDateListItem(NearByViewHolder holder, int position) {
        LocalLog.d(TAG, "upDateListItem() enter");
        if (mData.get(position) instanceof NearByResponse.DataBeanX.DataBean) {
            Presenter.getInstance(context).getImage(holder.userNearIcon, ((NearByResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.dearName.setText(((NearByResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            String stepFormat = context.getResources().getString(R.string.near_by_step);
            String stepNum = String.format(stepFormat, ((NearByResponse.DataBeanX.DataBean) mData.get(position)).getStep_number());
            holder.stepDesc.setText(stepNum);

            String distanceFormat = context.getResources().getString(R.string.near_by_distance);
            String distanceNum = String.format(distanceFormat, ((NearByResponse.DataBeanX.DataBean) mData.get(position)).getDistance());
            holder.distance.setText(distanceNum);

            if (((NearByResponse.DataBeanX.DataBean) mData.get(position)).getIs_follow() == 0) {
                LocalLog.d(TAG, "未关注");
                holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_fllow_nearby));
                holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_6c71c4));
                holder.btFollow.setText("关注");
            } else {
                LocalLog.d(TAG, "已关注");
                holder.btFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.has_not_fllow_nearby));
                holder.btFollow.setTextColor(ContextCompat.getColor(context, R.color.color_646464));
                holder.btFollow.setText("已关注");
            }
            if (((NearByResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
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

    public class NearByViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_near_icon)
        CircleImageView userNearIcon;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.step_desc)
        TextView stepDesc;
        @Bind(R.id.distance)
        TextView distance;
        @Bind(R.id.bt_follow)
        Button btFollow;
        boolean followFlag;

        public NearByViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            userNearIcon = (CircleImageView) view.findViewById(R.id.user_near_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            stepDesc = (TextView) view.findViewById(R.id.step_desc);
            distance = (TextView) view.findViewById(R.id.distance);
            btFollow = (Button) view.findViewById(R.id.bt_follow);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }
    }
}
