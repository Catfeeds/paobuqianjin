package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;

import java.util.ArrayList;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateAdapter extends RecyclerView.Adapter<OwnerCreateAdapter.OwnerCreateViewHolder> {
    private final static String TAG = OwnerCreateAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<?> data;

    private MyCreateCircleResponse.DataBeanX.DataBean tmpData;
    private MyJoinCircleResponse.DataBeanX.DataBean tmpData1;

    public OwnerCreateAdapter(Context context, ArrayList<?> data) {
        super();
        this.data = data;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public OwnerCreateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OwnerCreateViewHolder holder = new OwnerCreateViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.circle_kan_ban_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(OwnerCreateViewHolder holder, int position) {

        updateMyCreateList(holder, position);
    }

    private void updateMyCreateList(OwnerCreateViewHolder holder, int position) {
        LocalLog.d(TAG, "updateCircleList() enter" + data.get(position).toString());
        if (data.get(position) instanceof MyCreateCircleResponse.DataBeanX.DataBean) {
            tmpData = (MyCreateCircleResponse.DataBeanX.DataBean) data.get(position);
            LocalLog.d(TAG, "city = " + tmpData.getCity() +
                    ", name =" + tmpData.getName() + "logo url = " + tmpData.getLogo() + " ,member_number ="
                    + tmpData.getMember_number());
            Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, tmpData.getLogo());
            holder.locationDescSearchList.setText(tmpData.getCity());
            holder.searchCircleDesListName.setText(tmpData.getName());
            String sAgeFormat = mContext.getResources().getString(R.string.member_number);
            String sFinalMember = String.format(sAgeFormat, tmpData.getMember_number());
            holder.searchCircleDesListNum.setText(sFinalMember);
            if (tmpData.getIs_recharge() == 1) {
                holder.joinIn.setText("管理");
                holder.is_recharge = true;

            } else if (tmpData.getIs_recharge() == 0) {
                holder.joinIn.setText("充值");
                holder.is_recharge = false;
            }
            holder.setCircleid(tmpData.getId());
            holder.setCircle_member_num(tmpData.getMember_number());
        } else if (data.get(position) instanceof MyJoinCircleResponse.DataBeanX.DataBean) {
            tmpData1 = (MyJoinCircleResponse.DataBeanX.DataBean) data.get(position);
            LocalLog.d(TAG, "city = " + tmpData1.getCity() +
                    ", name =" + tmpData1.getName() + "logo url = " + tmpData1.getLogo() + " ,member_number ="
                    + tmpData1.getMember_number());
            Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, tmpData1.getLogo());
            holder.locationDescSearchList.setText(tmpData1.getCity());
            holder.searchCircleDesListName.setText(tmpData1.getName());
            String sAgeFormat = mContext.getResources().getString(R.string.member_number);
            String sFinalMember = String.format(sAgeFormat, tmpData1.getMember_number());
            holder.searchCircleDesListNum.setText(sFinalMember);
            holder.joinIn.setVisibility(View.GONE);
            holder.setCircle_member_num(tmpData1.getMember_number());
            holder.setCircleid(tmpData1.getId());
        }
    }

    class OwnerCreateViewHolder extends RecyclerView.ViewHolder {
        boolean needPass;

        public boolean getIs_recharge() {
            return is_recharge;
        }

        public void setIs_recharge(boolean is_recharge) {
            this.is_recharge = is_recharge;
        }

        public int getCircleid() {
            return circleid;
        }

        public void setCircleid(int id) {
            circleid = id;
        }

        int circleid;

        public int getCircle_member_num() {
            return circle_member_num;
        }

        public void setCircle_member_num(int circle_member_num) {
            this.circle_member_num = circle_member_num;
        }

        int circle_member_num;
        boolean is_recharge;
        ImageView circleLogoSearch;
        TextView searchCircleDesListName;
        ImageView lock;
        TextView searchCircleDesListNum;
        TextView locationDescSearchList;
        Button joinIn;

        public OwnerCreateViewHolder(View view) {
            super(view);
            init(view);
        }

        private void init(View view) {
            circleLogoSearch = (ImageView) view.findViewById(R.id.circle_logo_search);
            circleLogoSearch.setOnClickListener(onClickListener);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            lock = (ImageView) view.findViewById(R.id.lock);
            searchCircleDesListNum = (TextView) view.findViewById(R.id.search_circle_des_list_num);
            locationDescSearchList = (TextView) view.findViewById(R.id.location_desc_search_list);
            joinIn = (Button) view.findViewById(R.id.join_in);
            joinIn.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.join_in:
                        if (is_recharge) {
                            LocalLog.d(TAG, "管理");
                        } else {
                            LocalLog.d(TAG, "充值");
                        }
                        break;
                    case R.id.circle_logo_search:
                        LocalLog.d(TAG, " 点击圈子头像进入圈子");
                        Intent intent = new Intent();
                        intent.setClass(mContext, CirCleDetailActivity.class);
                        intent.putExtra(mContext.getPackageName() + "circleid", getCircleid());
                        intent.putExtra(mContext.getPackageName() + "membernum", getCircle_member_num());
                        mContext.startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
