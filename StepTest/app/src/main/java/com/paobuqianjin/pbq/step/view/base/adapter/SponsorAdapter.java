package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {
    private final static String TAG = SponsorAdapter.class.getSimpleName();
    Activity context;
    private List<GetUserBusinessResponse.DataBeanX.DataBean> mData = new ArrayList<>();
    private final static int REQUEST_SPONSOR_INFO = 1;
    private Intent intent;

    public SponsorAdapter(Activity context, List<GetUserBusinessResponse.DataBeanX.DataBean> data, Intent intent) {
        this.context = context;
        mData = data;
        this.intent = intent;
    }

    public void notifyDataSetChanged(List<GetUserBusinessResponse.DataBeanX.DataBean> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SponsorViewHolder holder, int position) {
        final GetUserBusinessResponse.DataBeanX.DataBean dataBean = mData.get(position);
        holder.name.setText(dataBean.getName());
        holder.locationDes.setText(dataBean.getAddra() + dataBean.getAddress());
        holder.editSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                intent.setClass(context, SponsorInfoActivity.class);
                intent.putExtra("businessId", dataBean.getBusinessid());
                context.startActivityForResult(intent, REQUEST_SPONSOR_INFO);
            }
        });
        holder.editSponsorDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                intent.setClass(context, SponsorInfoActivity.class);
                intent.putExtra("businessId", dataBean.getBusinessid());
                context.startActivityForResult(intent, REQUEST_SPONSOR_INFO);
            }
        });
        holder.deleteSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance(context).deleteBusiness(dataBean.getBusinessid(), new InnerCallBack() {
                    @Override
                    public void innerCallBack(Object object) {
                        if (!(object instanceof ErrorCode)) {
                            ((SponsorManagerActivity) context).refresh();
                        }
                    }
                });
            }
        });
        holder.deleteSponsorDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance(context).deleteBusiness(dataBean.getBusinessid(), new InnerCallBack() {
                    @Override
                    public void innerCallBack(Object object) {
                        if (!(object instanceof ErrorCode)) {
                            ((SponsorManagerActivity) context).refresh();
                        }
                    }
                });
            }
        });

        holder.tvDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance(context).setDefaultBusiness(dataBean.getBusinessid(), new InnerCallBack() {
                    @Override
                    public void innerCallBack(Object object) {
                        if (!(object instanceof ErrorCode)) {
                            ((SponsorManagerActivity) context).refresh();
                        }
                    }
                });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("businessId", dataBean.getBusinessid());
                context.setResult(ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG, intent);
                context.finish();
            }
        });
    }

    @Override
    public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SponsorViewHolder(LayoutInflater.from(context).inflate(R.layout.sponsor_manager_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return mData.size();
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
        @Bind(R.id.tv_default)
        TextView tvDefault;


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
            tvDefault = (TextView) view.findViewById(R.id.tv_default);
        }
    }
}
