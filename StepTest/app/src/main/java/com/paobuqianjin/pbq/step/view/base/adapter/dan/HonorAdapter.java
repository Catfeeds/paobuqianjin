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
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/16.
 */

public class HonorAdapter extends RecyclerView.Adapter<HonorAdapter.HonorAdapterViewHolder> {
    private final static String TAG = HonorAdapter.class.getSimpleName();
    Context context;
    List mData;
    private static int DEFAULT_COUNT = 3;


    public HonorAdapter(Context context, List data) {
        this.context = context;
        mData = data;
        addDefault();
    }

    /**
     * 添加默认的排名
     */
    private void addDefault() {
        int emptyPerson = DEFAULT_COUNT - mData.size();

        try {
            for (int i = 0; i < emptyPerson; i++) {
                if (mData.get(0) instanceof FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) {
                    FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean bean = new FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean();
                    bean.setAvatar("");
                    bean.setNickname("--");
                    bean.setStep_number("--");
                    mData.add(bean);
                } else if (mData.get(0) instanceof CircleStepRankResponse.DataBeanX.DataBean.CircleBean) {
                    CircleStepRankResponse.DataBeanX.DataBean.CircleBean bean = new CircleStepRankResponse.DataBeanX.DataBean.CircleBean();
                    bean.setAvatar("");
                    bean.setNickname("--");
                    bean.setStep_number("--");
                    mData.add(bean);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            }
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getNickname());

           /* if (((FriendStepRankDayResponse.DataBeanX.DataBean.MemberBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
        } else if (mData.get(position) instanceof CircleStepRankResponse.DataBeanX.DataBean.CircleBean) {
            if (position == 0) {
                holder.rankIcon.setImageResource(R.drawable.honor_master);
            } else if (position == 1) {
                holder.rankIcon.setImageResource(R.drawable.honor_second);
            } else if (position == 2) {
                holder.rankIcon.setImageResource(R.drawable.honor_third);
            }
            Presenter.getInstance(context).getPlaceErrorImage(holder.headIconUser, ((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.stepNum.setText(String.valueOf(((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getStep_number()));
            holder.userNameRank.setText(((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getNickname());
           /* if (((CircleStepRankResponse.DataBeanX.DataBean.CircleBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
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
            if (size > DEFAULT_COUNT) {
                return DEFAULT_COUNT;
            } else {
                return size;
            }
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
        @Bind(R.id.vip_flg)
        ImageView vipFlg;

        public HonorAdapterViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            rankIcon = (ImageView) view.findViewById(R.id.rank_icon);
            headIconUser = (CircleImageView) view.findViewById(R.id.head_icon_user);
            userNameRank = (TextView) view.findViewById(R.id.user_name_rank);
            stepNum = (TextView) view.findViewById(R.id.step_num);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }
    }
}
