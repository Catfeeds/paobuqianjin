package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/29.
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {
    private final static String TAG = RankAdapter.class.getSimpleName();
    private Context mContext;
    private List<?> mData;
    private final static int RECHARGE_RANK = 0;
    private final static int STEP_RANK = 1;

    public RankAdapter(Context context, List<?> data) {
        super();
        mContext = context;
        mData = data;
        LocalLog.d(TAG, "RankAdapter() enter");
    }

    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RankViewHolder holder = new RankViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.love_rank_list, parent, false), viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RankViewHolder holder, int position) {
        updateLists(holder, position);
    }

    private void updateLists(RankViewHolder holder, int position) {
        //holder.rankNum.setText(String.valueOf(position + 1));
        if (mData.get(position) instanceof ReChargeRankResponse.DataBeanX.DataBean) {
            holder.rankNum.setVisibility(View.VISIBLE);
            holder.rankNum.setText(String.valueOf(position + 1));
            ReChargeRankResponse.DataBeanX.DataBean dataBean = (ReChargeRankResponse.DataBeanX.DataBean) mData.get(position);
            Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, dataBean.getAvatar());
            holder.searchCircleDesListName.setText(dataBean.getNickname());
            holder.loveNumber.setText(dataBean.getTotal_fee() + "元");
            holder.userid = ((ReChargeRankResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            if (((ReChargeRankResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }

        } else if (mData.get(position) instanceof StepRankResponse.DataBeanX.DataBean) {
            StepRankResponse.DataBeanX.DataBean dataBean = (StepRankResponse.DataBeanX.DataBean) mData.get(position);
            Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, dataBean.getAvatar());
            holder.searchCircleDesListName.setText(dataBean.getNickname());
            holder.loveNumber.setText(dataBean.getStep_number() + "步");
            holder.userid = ((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            if (((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof ReChargeRankResponse) {
            LocalLog.d(TAG, "充值排行");
            return RECHARGE_RANK;
        } else if (mData.get(position) instanceof StepRankResponse) {
            LocalLog.d(TAG, "步数排行");
            return STEP_RANK;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class RankViewHolder extends RecyclerView.ViewHolder {
        int viewType;
        TextView rankNum;
        CircleImageView circleLogoSearch;
        ImageView vipFlg;
        TextView searchCircleDesListName;
        TextView loveNumber;
        int userid;

        public RankViewHolder(View view, int viewType) {
            super(view);
            initView(view, viewType);
        }

        private void initView(View view, int viewType) {
            this.viewType = viewType;
            LocalLog.d(TAG, "initView() enter");
            rankNum = (TextView) view.findViewById(R.id.rank_num);
            circleLogoSearch = (CircleImageView) view.findViewById(R.id.circle_logo_search);
            circleLogoSearch.setOnClickListener(onClickListener);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            loveNumber = (TextView) view.findViewById(R.id.love_number);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_logo_search:
                        LocalLog.d(TAG, "点击个人头像");
                        Intent intent = new Intent();
                        intent.putExtra("userid", userid);
                        intent.setClass(mContext, UserCenterActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        };
    }
}
