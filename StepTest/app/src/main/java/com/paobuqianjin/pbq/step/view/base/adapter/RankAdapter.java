package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleRedRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/29.
 */

public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = RankAdapter.class.getSimpleName();
    private Context mContext;
    private List<?> mData;
    private final static int RECHARGE_RANK = 0;
    private final static int STEP_RANK = 1;
    private final static int RED_RECORD = 2;

    public RankAdapter(Context context, List<?> data) {
        super();
        mContext = context;
        mData = data;
        LocalLog.d(TAG, "RankAdapter() enter");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == RECHARGE_RANK || viewType == STEP_RANK) {
            holder = new RankViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.love_rank_list, parent, false), viewType);
        } else if (viewType == RED_RECORD) {
            holder = new RedRecordViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.circle_red_record_item, parent, false));
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        updateLists(holder, position);
    }

    private void updateLists(RecyclerView.ViewHolder holder, int position) {
        //holder.rankNum.setText(String.valueOf(position + 1));
        if (mData.get(position) instanceof ReChargeRankResponse.DataBeanX.DataBean) {
            ((RankViewHolder) holder).rankNum.setVisibility(View.VISIBLE);
            ((RankViewHolder) holder).rankNum.setText(String.valueOf(position + 1));
            ReChargeRankResponse.DataBeanX.DataBean dataBean = (ReChargeRankResponse.DataBeanX.DataBean) mData.get(position);
            Presenter.getInstance(mContext).getPlaceErrorImage(((RankViewHolder) holder).circleLogoSearch, dataBean.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (!TextUtils.isEmpty(dataBean.getCirclenickname())) {
                ((RankViewHolder) holder).searchCircleDesListName.setText(dataBean.getCirclenickname());
            } else {
                ((RankViewHolder) holder).searchCircleDesListName.setText(dataBean.getNickname());
            }
            ((RankViewHolder) holder).loveNumber.setText(dataBean.getTotal_fee() + "元");
            ((RankViewHolder) holder).userid = ((ReChargeRankResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            /*if (((ReChargeRankResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/

        } else if (mData.get(position) instanceof StepRankResponse.DataBeanX.DataBean) {
            StepRankResponse.DataBeanX.DataBean dataBean = (StepRankResponse.DataBeanX.DataBean) mData.get(position);
            Presenter.getInstance(mContext).getPlaceErrorImage(((RankViewHolder) holder).circleLogoSearch, dataBean.getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (!TextUtils.isEmpty(dataBean.getCirclenickname())) {
                ((RankViewHolder) holder).searchCircleDesListName.setText(dataBean.getCirclenickname());
            } else {
                ((RankViewHolder) holder).searchCircleDesListName.setText(dataBean.getNickname());
            }
            ((RankViewHolder) holder).loveNumber.setText(dataBean.getStep_number() + "步");
            ((RankViewHolder) holder).userid = ((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            /*if (((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
        } else if (mData.get(position) instanceof CircleRedRecordResponse.DataBeanX.DataBean) {
            long redTime = ((CircleRedRecordResponse.DataBeanX.DataBean) mData.get(position)).getReceivetime();
            String date = DateTimeUtil.formatDateTime(redTime * 1000, DateTimeUtil.DF_YYYY_MM_DD);
            String dates[] = date.split("-");
            String dateStr = "";
            if (dates.length == 3) {
                dateStr = dates[0] + " 年 " + dates[1] + " 月 " + dates[2] + " 日";
            }
            ((RedRecordViewHolder) holder).dateT.setText(dateStr);

            String name = ((CircleRedRecordResponse.DataBeanX.DataBean) mData.get(position)).getNickname();
            if (!TextUtils.isEmpty(name)) {
                name = name.substring(0, 1) + "***";
            }
            ((RedRecordViewHolder) holder).uNameT.setText(name);
            ((RedRecordViewHolder) holder).uMoneyT.setText(((CircleRedRecordResponse.DataBeanX.DataBean) mData.get(position)).getAmount() + "元");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof ReChargeRankResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "充值排行");
            return RECHARGE_RANK;
        } else if (mData.get(position) instanceof StepRankResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, "步数排行");
            return STEP_RANK;
        } else if (mData.get(position) instanceof CircleRedRecordResponse.DataBeanX.DataBean) {
            return RED_RECORD;
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
                        intent.setClass(mContext, FriendDetailActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        };
    }

    public class RedRecordViewHolder extends RecyclerView.ViewHolder {
        TextView dateT;
        TextView uNameT;
        TextView uMoneyT;

        public RedRecordViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            dateT = (TextView) view.findViewById(R.id.date_red);
            uNameT = (TextView) view.findViewById(R.id.uname);
            uMoneyT = (TextView) view.findViewById(R.id.red_money);
        }
    }
}
