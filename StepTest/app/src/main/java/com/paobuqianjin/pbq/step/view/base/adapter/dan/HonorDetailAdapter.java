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
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.HisStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRandWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());

            /*if (((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
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
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getNickname());
            /*if (((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
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
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());
            /*if (((FriendWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
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
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
           /* if (((StepRankResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
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
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());
            /*if (((StepRandWeekResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
        } else if (mData.get(position) instanceof HisStepRankDayResponse.DataBean.MemberBean) {
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
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((HisStepRankDayResponse.DataBean.MemberBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((HisStepRankDayResponse.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((HisStepRankDayResponse.DataBean.MemberBean) mData.get(position)).getNickname());
            if (((HisStepRankDayResponse.DataBean.MemberBean) mData.get(position)).getIs_zan() == 1) {
                holder.likeIco.setImageResource(R.drawable.like_step_more);
                holder.is_vote = true;
            } else {
                holder.likeIco.setImageResource(R.drawable.like_step_zero);
                holder.is_vote = false;
            }
            holder.localVoteNum = ((HisStepRankDayResponse.DataBean.MemberBean) mData.get(position)).getZan_count();
            holder.likeNum.setText(String.valueOf(((HisStepRankDayResponse.DataBean.MemberBean) mData.get(position)).getZan_count()));
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
        ImageView rankIcon;
        CircleImageView headIconUser;
        TextView userNameRank;
        TextView stepNum;
        TextView rankNum;
        ImageView vipFlg;
        ImageView likeIco;
        TextView likeNum;
        boolean is_vote = false;
        int localVoteNum;

        /*点赞*/
        private void voteSponsor(final int status, final String step_record_id) {
            Map<String, String> param = new HashMap<>();
            param.put("status", String.valueOf(status));
            param.put("type", "4");
            param.put("step_record_id", step_record_id);
            Presenter.getInstance(context).postPaoBuSimple(NetApi.urlVoteSponsor, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (status == 1) {
                        LocalLog.d("voteSponsor", "点赞成功!");
                        is_vote = true;
                        likeIco.setImageResource(R.drawable.like_step_zero);
                        localVoteNum++;
                    } else if (status == 2) {
                        LocalLog.d("voteSponsor", "取消点赞!");
                        is_vote = false;
                        likeIco.setImageResource(R.drawable.like_step_more);
                        localVoteNum--;

                    }
                    likeNum.setText(String.valueOf(localVoteNum));
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                }
            });
        }

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
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
            likeIco = (ImageView) view.findViewById(R.id.like_ico);
            likeNum = (TextView) view.findViewById(R.id.like_num);
            likeIco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData.get(getAdapterPosition()) instanceof HisStepRankDayResponse.DataBean.MemberBean) {
                        if (is_vote) {
                            voteSponsor(2, ((HisStepRankDayResponse.DataBean.MemberBean) mData.get(getAdapterPosition())).getUser_step_id());
                        } else {
                            voteSponsor(1, ((HisStepRankDayResponse.DataBean.MemberBean) mData.get(getAdapterPosition())).getUser_step_id());
                        }
                    }
                }
            });
        }
    }
}
