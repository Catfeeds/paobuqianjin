package com.paobuqianjin.pbq.step.view.base.adapter.dan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/2/5.
 */

public class NearByAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = NearByAdapter.class.getSimpleName();
    private Context context;
    List<NearByResponse.DataBean> mData;

    public NearByAdapter(Context context, List<NearByResponse.DataBean> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NearByViewHolder(LayoutInflater.from(context).inflate(R.layout.near_by_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    private class NearByViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_near_icon)
        public CircleImageView userNearIcon;
        @Bind(R.id.dear_name)
        public TextView dearName;
        @Bind(R.id.step_desc)
        public TextView stepDesc;
        @Bind(R.id.distance)
        public TextView distance;
        @Bind(R.id.bt_follow)
        public Button btFollow;

        public NearByViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            userNearIcon = (CircleImageView) view.findViewById(R.id.user_near_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            stepDesc = (TextView) view.findViewById(R.id.step_desc);
            distance = (TextView) view.findViewById(R.id.distance);
            btFollow = (Button) view.findViewById(R.id.bt_follow);
        }
    }
}
