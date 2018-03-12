package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/4.
 */

public class RechargeRankSimpleAdapter extends RecyclerView.Adapter<RechargeRankSimpleAdapter.RechargeRankSimpleViewHolder> {
    private final static String TAG = RechargeRankSimpleAdapter.class.getSimpleName();
    Context context;
    private List<ReChargeRankResponse.DataBeanX.DataBean> mData;

    public RechargeRankSimpleAdapter(Context context, List<ReChargeRankResponse.DataBeanX.DataBean> data) {
        super();
        this.context = context;
        mData = data;
        LocalLog.d(TAG, "RechargeRankSimpleAdapter() enter");
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (mData.size() > 0) {
            if (mData.size() < 7) {
                return mData.size();
            } else {
                return 7;
            }
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(RechargeRankSimpleViewHolder holder, int position) {
        updateList(holder, position);
    }

    private void updateList(RechargeRankSimpleViewHolder holder, int position) {
        holder.moneyNumDes.setText(mData.get(position).getTotal_fee() + "元");
        holder.userid = mData.get(position).getUserid();
        Presenter.getInstance(context).getImage(holder.userIconMoney, mData.get(position).getAvatar());
    }

    @Override
    public RechargeRankSimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RechargeRankSimpleViewHolder(
                LayoutInflater.from(context).inflate(R.layout.money_rank_list, parent, false)
        );
    }

    public class RechargeRankSimpleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_icon_money)
        CircleImageView userIconMoney;
        @Bind(R.id.money_num_des)
        TextView moneyNumDes;
        int userid;

        public RechargeRankSimpleViewHolder(View view) {
            super(view);
            initView(view);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.user_icon_money:
                        LocalLog.d(TAG, "点击个人用户头像");
                        Intent intent = new Intent();
                        intent.putExtra("userid", userid);
                        intent.setClass(context, UserCenterActivity.class);
                        context.startActivity(intent);
                        break;
                }
            }
        };

        private void initView(View view) {
            moneyNumDes = (TextView) view.findViewById(R.id.money_num_des);
            userIconMoney = (CircleImageView) view.findViewById(R.id.user_icon_money);
            userIconMoney.setOnClickListener(onClickListener);
        }
    }


    //设置RecyclerView item间距
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0;
            } else {
                outRect.left = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
