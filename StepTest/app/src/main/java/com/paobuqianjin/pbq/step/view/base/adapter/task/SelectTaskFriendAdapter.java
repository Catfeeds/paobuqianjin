package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/26.
 */

public class SelectTaskFriendAdapter extends RecyclerView.Adapter<SelectTaskFriendAdapter.SelectTaskFriendViewHolder> {
    private final static String TAG = SelectTaskFriendAdapter.class.getSimpleName();

    private Context context;
    private List<UserFriendResponse.DataBeanX.DataBean> mData;
    private List<UserFriendResponse.DataBeanX.DataBean> resultData;

    public List<UserFriendResponse.DataBeanX.DataBean> getResultData() {
        return resultData;
    }

    public SelectTaskFriendAdapter(Context context, List<UserFriendResponse.DataBeanX.DataBean> data, List<UserFriendResponse.DataBeanX.DataBean> sourceData) {
        super();
        this.context = context;
        mData = data;
        this.resultData = sourceData;
    }

    public void notifyDataSetChanged(List<UserFriendResponse.DataBeanX.DataBean> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SelectTaskFriendViewHolder holder, int position) {
        if (mData.get(position) instanceof UserFriendResponse.DataBeanX.DataBean) {
            LocalLog.d(TAG, " before mData.get(position) " + mData.get(position).toString());
            Presenter.getInstance(context).getPlaceErrorImage(holder.dearIcon, ((UserFriendResponse.DataBeanX.DataBean) mData.get(position)).getAvatar()
                    , R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.dearName.setText(((UserFriendResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.position = position;
            holder.is_distrubute = mData.get(position).getIs_distribute();
            if (mData.get(position).getIs_distribute() == 1) {
                LocalLog.d(TAG, "不可发布");
                holder.selectIcon.setImageDrawable(null);
                holder.selectIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_out_uncheck));
            } else {
                LocalLog.d(TAG, "可发布");
                holder.selectIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_outline));
                if (mData.get(position).isSelected()) {
                    holder.selectIcon.setImageResource(R.drawable.selected_icon);
                }
            }
            /*if (mData.get(position).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
            if (resultData != null) {
                for (int i = 0; i < resultData.size(); i++) {
                    if (mData.get(position).getId() == resultData.get(i).getId()) {
                        LocalLog.d(TAG, "id =" + mData.get(position).getId() + "被选中过");
                        holder.selectIcon.setImageResource(R.drawable.selected_icon);
                        mData.get(position).setSelected(true);
                        LocalLog.d(TAG, resultData.get(i).toString());
                        resultData.remove(i);
                        resultData.add(i, mData.get(position));
                    }
                }
            }
            LocalLog.d(TAG, "after mData.get(position) " + mData.get(position).toString());
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

    @Override
    public SelectTaskFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectTaskFriendViewHolder(LayoutInflater.from(context).inflate(R.layout.select_friend_list_item,
                parent, false));
    }

    public class SelectTaskFriendViewHolder extends RecyclerView.ViewHolder {
        //@Bind(R.id.select_icon)
        ImageView selectIcon;
        //@Bind(R.id.dear_icon)
        CircleImageView dearIcon;
        //@Bind(R.id.vip_flg)
        ImageView vipFlg;
        //@Bind(R.id.dear_name)
        TextView dearName;
        int position = -1;
        int is_distrubute = 0;

        public SelectTaskFriendViewHolder(View view) {
            super(view);
            initView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (is_distrubute == 1) {
                        return;
                    }
                    if (mData.get(position).isSelected()) {
                        LocalLog.d(TAG, "选中状态变为非选中");
                        selectIcon.setImageDrawable(null);
                        LocalLog.d(TAG, mData.get(position).toString());
                        if (resultData.contains(mData.get(position))) {
                            LocalLog.d(TAG, "删除!");
                            resultData.remove(mData.get(position));
                        }
                        mData.get(position).setSelected(false);
                    } else {
                        LocalLog.d(TAG, "非选中状态变为选中");
                        selectIcon.setImageResource(R.drawable.selected_icon);
                        mData.get(position).setSelected(true);
                        if (resultData == null) {
                            resultData = new ArrayList<>();
                        }

                        if (!resultData.contains(mData.get(position))) {
                            LocalLog.d(TAG, "添加");
                            resultData.add(mData.get(position));
                        }

                    }
                }
            });
        }

        private void initView(View view) {
            selectIcon = (ImageView) view.findViewById(R.id.select_icon);
            dearIcon = (CircleImageView) view.findViewById(R.id.dear_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }

    }
}
