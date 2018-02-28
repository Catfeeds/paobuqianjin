package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/2/27.
 */

public class InviteDanAdapter extends RecyclerView.Adapter<InviteDanAdapter.InviteDanViewHolder> {
    private final static String TAG = InviteDanAdapter.class.getSimpleName();
    Context context;
    List<?> mData;

    public InviteDanAdapter(Context context, List<InviteDanResponse.DataBeanX.DataBean> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(InviteDanViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(InviteDanViewHolder holder, int position) {
        if (mData.get(position) instanceof InviteDanResponse.DataBeanX.DataBean) {
            holder.dearName.setText(((InviteDanResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            String inviteStrFormat = context.getString(R.string.invite_dan_msg);
            String inviteRedPkgFormat = context.getString(R.string.invite_red_pkg);
            String inviteStr = String.format(inviteStrFormat, ((InviteDanResponse.DataBeanX.DataBean) mData.get(position)).getInviternum());
            String inviteRedPkgStr = String.format(inviteRedPkgFormat, ((InviteDanResponse.DataBeanX.DataBean) mData.get(position)).getSum_credit());
            String inviteDanStrFormat = context.getString(R.string.invite_dan_rank);
            String inviteDanStr = String.format(inviteDanStrFormat, ((InviteDanResponse.DataBeanX.DataBean) mData.get(position)).getInviterid());
            Presenter.getInstance(context).getImage(holder.userHeadIcon, ((InviteDanResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.incomeReadPkg.setText(inviteRedPkgStr);
            holder.inviteNumDes.setText(inviteStr);
            holder.inviteRank.setText(inviteDanStr);
        }
    }

    @Override
    public InviteDanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InviteDanViewHolder(LayoutInflater.from(context).inflate(R.layout.invite_rank_list, parent, false));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class InviteDanViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_head_icon)
        CircleImageView userHeadIcon;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.invite_num_des)
        TextView inviteNumDes;
        @Bind(R.id.income_read_pkg)
        TextView incomeReadPkg;
        @Bind(R.id.invite_rank)
        TextView inviteRank;

        public InviteDanViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            userHeadIcon = (CircleImageView) view.findViewById(R.id.user_head_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            inviteNumDes = (TextView) view.findViewById(R.id.invite_num_des);
            incomeReadPkg = (TextView) view.findViewById(R.id.income_read_pkg);
            inviteRank = (TextView) view.findViewById(R.id.invite_rank);
        }
    }
}