package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CollectSponsorResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;
import java.util.logging.Handler;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/8/6.
 */

public class SponsorCollectAdapter extends RecyclerView.Adapter<SponsorCollectAdapter.SponsorCollectViewHolder> {
    private List<?> data;
    private Context context;

    public SponsorCollectAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.data = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SponsorCollectViewHolder holder, int position) {
        if (data.get(position) instanceof CollectSponsorResponse.DataBeanX.DataBean) {
            if (((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getBusinessid() > 0) {
                Presenter.getInstance(context).getPlaceErrorImage(holder.sponsorLogo, ((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getBusinesslogo(),
                        R.drawable.default_head_ico, R.drawable.default_head_ico);
                if (!TextUtils.isEmpty(((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getBusinessname())) {
                    holder.nameSponsor.setText("会员名称:" + ((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getBusinessname());
                }
                String addA = "";
                if (!TextUtils.isEmpty(((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getAddra())) {
                    addA = ((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getAddra().replace("/", "");
                }
                String addB = "";
                if (!TextUtils.isEmpty(((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getAddress())) {
                    addB = ((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getAddress();
                }
                if (!TextUtils.isEmpty(((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getTel())) {
                    holder.tel.setText("电话:" + ((CollectSponsorResponse.DataBeanX.DataBean) data.get(position)).getTel());
                }
                if (!TextUtils.isEmpty(addA + addB)) {
                    holder.add.setText("地址:" + addA + addB);
                }
            }
        }
    }

    @Override
    public SponsorCollectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SponsorCollectViewHolder(LayoutInflater.from(context).
                inflate(R.layout.collect_sponsor_item, null, false));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class SponsorCollectViewHolder extends RecyclerView.ViewHolder {
        CircleImageView sponsorLogo;
        TextView nameSponsor;
        TextView tel;
        TextView add;

        SponsorCollectViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            sponsorLogo = (CircleImageView) view.findViewById(R.id.sponsor_logo);
            nameSponsor = (TextView) view.findViewById(R.id.name_sponsor);
            tel = (TextView) view.findViewById(R.id.tel);
            add = (TextView) view.findViewById(R.id.add);
        }
    }
}
