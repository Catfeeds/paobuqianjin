package com.paobuqianjin.pbq.step.view.base.adapter.dan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRandWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/19.
 */

public class HonorDetailAdapter extends RecyclerView.Adapter<HonorDetailAdapter.HonorAdapterViewHolder> {
    Context context;
    List<?> mData;


    public HonorDetailAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(HonorAdapterViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(HonorAdapterViewHolder holder, int position) {
        if (mData.get(position) instanceof FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) {
            if (position == 0) {
                holder.rankIcon.setImageResource(R.drawable.honor_master);
            } else if (position == 1) {
                holder.rankIcon.setImageResource(R.drawable.honor_second);
            } else if (position == 2) {
                holder.rankIcon.setImageResource(R.drawable.honor_third);
            } else {
                holder.rankNum.setVisibility(View.VISIBLE);
                holder.rankNum.setText(String.valueOf(position + 1));
                holder.rankIcon.setVisibility(View.INVISIBLE);
            }
            Presenter.getInstance(context).getImage(holder.headIconUser, ((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar());
            holder.stepNum.setText(String.valueOf(((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());
        } else if (mData.get(position) instanceof CircleStepRankResponse.DataBeanX.DataBean.CircleBean) {
            if (position == 0) {
                holder.rankIcon.setImageResource(R.drawable.honor_master);
            } else if (position == 1) {
                holder.rankIcon.setImageResource(R.drawable.honor_second);
            } else if (position == 2) {
                holder.rankIcon.setImageResource(R.drawable.honor_third);
            } else {
                holder.rankNum.setVisibility(View.VISIBLE);
                holder.rankNum.setText(String.valueOf(position + 1));
                holder.rankIcon.setVisibility(View.INVISIBLE);
            }
            Presenter.getInstance(context).getImage(holder.headIconUser, ((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getAvatar());
            holder.stepNum.setText(String.valueOf(((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getNickname());
        } else if (mData.get(position) instanceof FriendWeekResponse.DataBeanX.DataBean.MemberBean) {
            if (position == 0) {
                holder.rankIcon.setImageResource(R.drawable.honor_master);
            } else if (position == 1) {
                holder.rankIcon.setImageResource(R.drawable.honor_second);
            } else if (position == 2) {
                holder.rankIcon.setImageResource(R.drawable.honor_third);
            } else {
                holder.rankNum.setVisibility(View.VISIBLE);
                holder.rankNum.setText(String.valueOf(position + 1));
                holder.rankIcon.setVisibility(View.INVISIBLE);
            }
            Presenter.getInstance(context).getImage(holder.headIconUser, ((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar());
            holder.stepNum.setText(String.valueOf(((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());
        } else if (mData.get(position) instanceof StepRankResponse.DataBeanX.DataBean) {
            if (position == 0) {
                holder.rankIcon.setImageResource(R.drawable.honor_master);
            } else if (position == 1) {
                holder.rankIcon.setImageResource(R.drawable.honor_second);
            } else if (position == 2) {
                holder.rankIcon.setImageResource(R.drawable.honor_third);
            } else {
                holder.rankNum.setVisibility(View.VISIBLE);
                holder.rankNum.setText(String.valueOf(position + 1));
                holder.rankIcon.setVisibility(View.INVISIBLE);
            }
            Presenter.getInstance(context).getImage(holder.headIconUser, ((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.stepNum.setText(String.valueOf(((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
        } else if (mData.get(position) instanceof StepRandWeekResponse.DataBeanX.DataBean.MemberBean) {
            if (position == 0) {
                holder.rankIcon.setImageResource(R.drawable.honor_master);
            } else if (position == 1) {
                holder.rankIcon.setImageResource(R.drawable.honor_second);
            } else if (position == 2) {
                holder.rankIcon.setImageResource(R.drawable.honor_third);
            } else {
                holder.rankNum.setVisibility(View.VISIBLE);
                holder.rankNum.setText(String.valueOf(position + 1));
                holder.rankIcon.setVisibility(View.INVISIBLE);
            }
            Presenter.getInstance(context).getImage(holder.headIconUser, ((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar());
            holder.stepNum.setText(String.valueOf(((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());
        }
    }

    @Override
    public HonorAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HonorAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.dan_base_list, parent, false));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            int size = mData.size();
            return size;
        }
        return 0;
    }

    public class HonorAdapterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rank_icon)
        ImageView rankIcon;
        @Bind(R.id.head_icon_user)
        CircleImageView headIconUser;
        @Bind(R.id.user_name_rank)
        TextView userNameRank;
        @Bind(R.id.step_num)
        TextView stepNum;
        @Bind(R.id.rank_num)
        TextView rankNum;

        public HonorAdapterViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            rankIcon = (ImageView) view.findViewById(R.id.rank_icon);
            headIconUser = (CircleImageView) view.findViewById(R.id.head_icon_user);
            userNameRank = (TextView) view.findViewById(R.id.user_name_rank);
            stepNum = (TextView) view.findViewById(R.id.step_num);
            rankNum = (TextView) view.findViewById(R.id.rank_num);
        }
    }
}
