package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashListDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RechargeDetailResponse;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/3/10.
 */

public class IncomeAdater extends RecyclerView.Adapter<IncomeAdater.IncomeDetailViewHolder> {
    private final static String TAG = IncomeAdater.class.getSimpleName();
    private final static int defaultCount = 5;
    Context context;
    List<?> mData;

    public IncomeAdater(Context context, List<?> data) {
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

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(IncomeDetailViewHolder holder, int position) {
        upDateListItem(holder, position);
    }

    private void upDateListItem(IncomeDetailViewHolder holder, int position) {
        LocalLog.d(TAG, "upDateListItem() enter");
        if (mData.get(position) instanceof CrashListDetailResponse.DataBeanX.DataBean) {
            holder.incomeDetailDes.setText(((CrashListDetailResponse.DataBeanX.DataBean) mData.get(position)).getType_name());
            long create_time = ((CrashListDetailResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            String date = DateTimeUtil.formatDateTime(create_time * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM);
            String dateStr = date.replace("-", "/");
            holder.incomeDetailTime.setText(dateStr);
            if (((CrashListDetailResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 0) {
                LocalLog.d(TAG, "审核状态");
            } else if (((CrashListDetailResponse.DataBeanX.DataBean) mData.get(position)).getStatus() == 1) {

            }
            holder.data.setText(((CrashListDetailResponse.DataBeanX.DataBean) mData.get(position)).getActual_amount());
        } else if (mData.get(position) instanceof RechargeDetailResponse.DataBeanX.DataBean) {

            holder.incomeDetailDes.setText(((RechargeDetailResponse.DataBeanX.DataBean) mData.get(position)).getPay_name());
            long create_time = ((RechargeDetailResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            String date = DateTimeUtil.formatDateTime(create_time * 1000, DateTimeUtil.DF_YYYY_MM_DD_HH_MM);
            String dateStr = date.replace("-", "/");
            holder.incomeDetailTime.setText(dateStr);
            holder.data.setText(((RechargeDetailResponse.DataBeanX.DataBean) mData.get(position)).getTotal_fee());
        }
    }

    @Override
    public IncomeDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IncomeDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.in_out_come_list_item, parent, false));
    }

    public class IncomeDetailViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.income_detail_des)
        TextView incomeDetailDes;
        @Bind(R.id.income_detail_time)
        TextView incomeDetailTime;
        @Bind(R.id.data)
        TextView data;
        @Bind(R.id.span_time)
        RelativeLayout spanTime;

        public IncomeDetailViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            incomeDetailDes = (TextView) view.findViewById(R.id.income_detail_des);
            incomeDetailTime = (TextView) view.findViewById(R.id.income_detail_time);
            data = (TextView) view.findViewById(R.id.data);
        }

    }
}
