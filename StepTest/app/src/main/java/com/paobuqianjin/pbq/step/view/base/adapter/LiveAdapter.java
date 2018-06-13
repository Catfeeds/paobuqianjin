package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LiveResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.view.activity.LiveDetailActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/5/3.
 */

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.LiveViewHolder> {
    private final static String TAG = LiveAdapter.class.getSimpleName();
    private List<?> mData;
    private Activity context;

    public LiveAdapter(Activity context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        if (mData.get(position) instanceof LiveResponse.DataBeanX.DataBean) {
            /*Presenter.getInstance(context).getImage(holder.liveImag, ((LiveResponse.DataBeanX.DataBean) mData.get(position)).getConver());*/
            LoadBitmap.glideLoad(context,holder.liveImag,((LiveResponse.DataBeanX.DataBean) mData.get(position)).getConver());
            if (((LiveResponse.DataBeanX.DataBean) mData.get(position)).getIs_process() == 0) {
                holder.liveStates.setText("未开赛");
            } else if (((LiveResponse.DataBeanX.DataBean) mData.get(position)).getIs_process() == 2) {
                holder.liveStates.setText("已结束");
            } else {
                holder.liveStates.setText("进行中");
            }
            /*holder.liveDesc.setText(Html.fromHtml(((LiveResponse.DataBeanX.DataBean) mData.get(position)).getDeail()));*/
            holder.url = ((LiveResponse.DataBeanX.DataBean) mData.get(position)).getRemote_url();
            holder.isProcess = ((LiveResponse.DataBeanX.DataBean) mData.get(position)).getIs_process();
            holder.isReceive = ((LiveResponse.DataBeanX.DataBean) mData.get(position)).getIs_receive();
            holder.target = ((LiveResponse.DataBeanX.DataBean) mData.get(position)).getTarget();
            holder.actid = ((LiveResponse.DataBeanX.DataBean) mData.get(position)).getId();
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
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiveViewHolder(LayoutInflater.from(context).inflate(R.layout.live_list_item, parent, false));
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.live_imag)
        ImageView liveImag;
        @Bind(R.id.live_desc)
        TextView liveDesc;
        @Bind(R.id.live_states)
        TextView liveStates;
/*        @Bind(R.id.live_long_desc)
        TextView liveLongDesc;
        @Bind(R.id.join_mum)
        TextView joinMum;*/
        String url = "";
        public int isReceive;
        public int isProcess;
        public int target;
        public int actid;

        public LiveViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            liveImag = (ImageView) view.findViewById(R.id.live_imag);
            liveDesc = (TextView) view.findViewById(R.id.live_desc);
            liveStates = (TextView) view.findViewById(R.id.live_states);
      /*      liveLongDesc = (TextView) view.findViewById(R.id.live_long_desc);
            joinMum = (TextView) view.findViewById(R.id.join_mum);*/
            liveImag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(context.getPackageName(), url);
                    intent.putExtra("is_process", isProcess);
                    intent.putExtra("is_receive", isReceive);
                    intent.putExtra("target", target);
                    intent.putExtra("actid", actid);
                    intent.setClass(context, LiveDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
