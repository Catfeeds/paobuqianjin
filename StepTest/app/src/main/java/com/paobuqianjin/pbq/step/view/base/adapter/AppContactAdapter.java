package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;

import java.util.List;

/**
 * Created by pbq on 2018/3/28.
 */

public class AppContactAdapter extends RecyclerView.Adapter<AppContactAdapter.AppContactViewHolder> {
    private final static String TAG = AppContactAdapter.class.getSimpleName();
    private Context context;
    private List<?> mData;

    public AppContactAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(AppContactAdapter.AppContactViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    @Override
    public AppContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    private void updateListItem(AppContactAdapter.AppContactViewHolder holder, int position) {
        if (mData.get(position) instanceof FriendAddResponse.DataBean.InSystemBean) {

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

    public class AppContactViewHolder extends RecyclerView.ViewHolder {
        public AppContactViewHolder(View view) {
            super(view);
        }

        private void initView() {

        }
    }
}
