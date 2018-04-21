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
import com.paobuqianjin.pbq.step.view.activity.SponsorInfoCollectActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {
    private final static String TAG = SponsorAdapter.class.getSimpleName();
    Activity context;
    List<?> mData;
    private final static int REQUEST_SPONSOR_INFO = 1;

    public SponsorAdapter(Activity context, List<?> data) {
        this.context = context;
        mData = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SponsorViewHolder holder, int position) {

    }

    @Override
    public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SponsorViewHolder(LayoutInflater.from(context).inflate(R.layout.sponsor_manager_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class SponsorViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.location_des)
        TextView locationDes;
        @Bind(R.id.line)
        ImageView line;
        @Bind(R.id.select_circle)
        ImageView selectCircle;
        @Bind(R.id.delete_sponsor)
        ImageView deleteSponsor;
        @Bind(R.id.delete_sponsor_des)
        TextView deleteSponsorDes;
        @Bind(R.id.edit_sponsor)
        ImageView editSponsor;
        @Bind(R.id.edit_sponsor_des)
        TextView editSponsorDes;

        public SponsorViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            name = (TextView) view.findViewById(R.id.name);
            locationDes = (TextView) view.findViewById(R.id.location_des);
            selectCircle = (ImageView) view.findViewById(R.id.select_circle);
            deleteSponsor = (ImageView) view.findViewById(R.id.select_circle);
            deleteSponsorDes = (TextView) view.findViewById(R.id.delete_sponsor_des);
            editSponsor = (ImageView) view.findViewById(R.id.edit_sponsor);
            editSponsorDes = (TextView) view.findViewById(R.id.edit_sponsor_des);
            editSponsor.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.edit_sponsor:
                        Intent intent = new Intent();
                        intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                        intent.setClass(context, SponsorInfoCollectActivity.class);
                        context.startActivityForResult(intent, REQUEST_SPONSOR_INFO);
                        break;
                }
            }
        };
    }
}
