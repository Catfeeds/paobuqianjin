package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteTopResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/10/25.
 */

public class InviteTopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<?> data;

    public InviteTopAdapter(Context context, List<?> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position) instanceof InviteTopResponse.DataBeanX.IlistBean.DataBean) {
            Presenter.getInstance(mContext).getPlaceErrorImage(((InviteViewHolder) holder).headIco, ((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            ((InviteViewHolder) holder).dearName.setText(((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getNickname());
            String time = DateTimeUtil.formatDateTime(((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getCreate_time() * 1000, DateTimeUtil.DF_YYYY_MM_DD);
            ((InviteViewHolder) holder).creatTime.setText(time);
            if (((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getSub_inviter_count() > 0) {
                ((InviteViewHolder) holder).inviteNum.setText("+" + String.valueOf(((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getSub_inviter_count()) + "人");
            }
            if (((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getGvip() == 1
                    || ((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getVip() == 1) {
                if (((InviteTopResponse.DataBeanX.IlistBean.DataBean) data.get(position)).getGvip() == 1) {
                    ((InviteViewHolder) holder).vipImg.setImageResource(R.drawable.golden_little);
                } else {
                    ((InviteViewHolder) holder).vipImg.setImageResource(R.drawable.vip_flg);
                }
                ((InviteViewHolder) holder).vipImg.setVisibility(View.VISIBLE);
            } else {
                ((InviteViewHolder) holder).vipImg.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InviteViewHolder(LayoutInflater.from(mContext).inflate(R.layout.invite_top_item, parent, false));
    }

    public class InviteViewHolder extends RecyclerView.ViewHolder {
        CircleImageView headIco;
        TextView dearName;
        TextView creatTime;
        TextView inviteNum;
        ImageView vipImg;

        public InviteViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            headIco = (CircleImageView) itemView.findViewById(R.id.head_ico);
            dearName = (TextView) itemView.findViewById(R.id.dear_name);
            creatTime = (TextView) itemView.findViewById(R.id.creat_time);
            inviteNum = (TextView) itemView.findViewById(R.id.invite_num);
            vipImg = (ImageView) itemView.findViewById(R.id.vip_fg);
        }
    }
}
