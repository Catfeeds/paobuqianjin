package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageLikeBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageLikeResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

/**
 * Created by pbq on 2018/3/27.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = MessageAdapter.class.getSimpleName();
    private Context context;
    private List<?> mData;

    public MessageAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            LocalLog.d(TAG, "MessageContentResponse ");
            return new MessageContentViewHolder(LayoutInflater.from(context).inflate(R.layout.content_list, parent, false));
        } else if (viewType == 1) {
            LocalLog.d(TAG, "MessageLikeResponse");
            return new MessageLikeViewHolder(LayoutInflater.from(context).inflate(R.layout.like_list, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null) {
            if (mData.get(position) instanceof MessageContentResponse.DataBeanX.DataBean) {
                return 0;
            } else if (mData.get(position) instanceof MessageLikeResponse.DataBeanX.DataBean) {
                return 1;
            }
        }
        return -1;
    }

    public class MessageContentViewHolder extends RecyclerView.ViewHolder {
        public MessageContentViewHolder(View view) {
            super(view);
        }
    }

    public class MessageLikeViewHolder extends RecyclerView.ViewHolder {
        public MessageLikeViewHolder(View view) {
            super(view);
        }
    }
}
