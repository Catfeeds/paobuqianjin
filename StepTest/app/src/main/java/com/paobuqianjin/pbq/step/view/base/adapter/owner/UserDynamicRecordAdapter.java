package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by pbq on 2018/3/2.
 */
/*
@className :UserDynamicRecordAdapter
*@date 2018/3/2
*@author
*@description 动态日记
*/
public class UserDynamicRecordAdapter extends RecyclerView.Adapter<UserDynamicRecordAdapter.UserDynamicRecordViewHolder> {
    private final static String TAG = UserDynamicRecordAdapter.class.getSimpleName();
    Context context;
    List<?> mData;
    Map<String, List> map;

    public UserDynamicRecordAdapter(Context context, Map<String, List> map) {
        this.context = context;
        this.map = map;
    }

    @Override
    public int getItemCount() {
        if (map != null) {
            return map.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(UserDynamicRecordViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(UserDynamicRecordViewHolder holder, int position) {
        for (String key : map.keySet()) {
            LocalLog.d(TAG, "key = " + key + ",当天记录条数 temp -> size = " + map.get(key).size());
            List<?> list = map.get(key);
            if (list.get(list.size() - 1) instanceof DynamicPersonResponse.DataBeanX.DataBean) {
                holder.date.setText(key);
                holder.dayDynamicRecycler.setAdapter(new UserDynamicRecordSecondAdapter(context, list));
            }
        }
    }

    @Override
    public UserDynamicRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDynamicRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamic_item_date, parent, false));
    }

    public class UserDynamicRecordViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.day_dynamic_recycler)
        RecyclerView dayDynamicRecycler;
        LinearLayoutManager layoutManager;

        public UserDynamicRecordViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            date = (TextView) view.findViewById(R.id.date);
            layoutManager = new LinearLayoutManager(context);
            dayDynamicRecycler = (RecyclerView) view.findViewById(R.id.day_dynamic_recycler);
            dayDynamicRecycler.setLayoutManager(layoutManager);
        }
    }
}
