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
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendSearchResponse;
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
    private final static int defaultCount = 7;
    private Context context;
    private List<UserFriendResponse.DataBeanX.DataBean> mData;
    private List<UserFriendResponse.DataBeanX.DataBean> resultData = new ArrayList<>();
    private List<UserFriendResponse.DataBeanX.DataBean> sourceData;

    public List<UserFriendResponse.DataBeanX.DataBean> getResultData() {
        return resultData;
    }

    public SelectTaskFriendAdapter(Context context, List<UserFriendResponse.DataBeanX.DataBean> data, List<UserFriendResponse.DataBeanX.DataBean> sourceData) {
        super();
        this.context = context;
        mData = data;
        this.sourceData = sourceData;
    }

    public void notifyDataSetChanged(List<UserFriendResponse.DataBeanX.DataBean> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SelectTaskFriendAdapter.SelectTaskFriendViewHolder holder, int position) {
        if (mData.get(position) instanceof UserFriendResponse.DataBeanX.DataBean) {
            Presenter.getInstance(context).getImage(holder.dearIcon, ((UserFriendResponse.DataBeanX.DataBean) mData.get(position)).getAvatar());
            holder.dearName.setText(((UserFriendResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.position = position;
            holder.is_distrubute = mData.get(position).getIs_distribute();
            if (holder.is_distrubute == 1) {
                holder.selectIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_out_uncheck));
            } else {
                holder.setOnClickListener();
            }
            if (sourceData != null) {
                for (int i = 0; i < sourceData.size(); i++) {
                    if (mData.get(position).getId() == sourceData.get(i).getId()) {
                        LocalLog.d(TAG, "id =" + mData.get(position).getId() + "被选中过");
                        holder.selectIcon.setImageResource(R.drawable.selected_icon);
                        holder.isSelected = true;
                        LocalLog.d(TAG, "非选中状态变为选中");
                        resultData.add(mData.get(position));
                    }
                }
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

    @Override
    public SelectTaskFriendAdapter.SelectTaskFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectTaskFriendViewHolder(LayoutInflater.from(context).inflate(R.layout.select_friend_list_item,
                parent, false));
    }

    public class SelectTaskFriendViewHolder extends RecyclerView.ViewHolder {
        //@Bind(R.id.select_icon)
        ImageView selectIcon;
        //@Bind(R.id.dear_icon)
        CircleImageView dearIcon;
        //@Bind(R.id.dear_name)
        TextView dearName;
        boolean isSelected = false;
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
                    if (isSelected) {
                        selectIcon.setImageDrawable(null);
                        isSelected = false;
                        LocalLog.d(TAG, "选中状态变为非选中");
                        resultData.remove(mData.get(position));
                    } else {
                        selectIcon.setImageResource(R.drawable.selected_icon);
                        isSelected = true;
                        LocalLog.d(TAG, "非选中状态变为选中");
                        resultData.add(mData.get(position));
                    }
                }
            });
        }

        private void initView(View view) {
            selectIcon = (ImageView) view.findViewById(R.id.select_icon);
            dearIcon = (CircleImageView) view.findViewById(R.id.dear_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
        }

        private void setOnClickListener() {
            selectIcon.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.select_icon:
                        if (isSelected) {
                            selectIcon.setImageDrawable(null);
                            isSelected = false;
                            LocalLog.d(TAG, "选中状态变为非选中");
                            resultData.remove(mData.get(position));
                        } else {
                            selectIcon.setImageResource(R.drawable.selected_icon);
                            isSelected = true;
                            LocalLog.d(TAG, "非选中状态变为选中");
                            resultData.add(mData.get(position));
                        }
                        break;
                }
            }
        };
    }
}
