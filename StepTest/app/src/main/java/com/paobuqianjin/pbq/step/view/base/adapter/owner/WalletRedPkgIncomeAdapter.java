package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/1/18.
 */

public class WalletRedPkgIncomeAdapter extends RecyclerView.Adapter<WalletRedPkgIncomeAdapter.WalletRedPkgIncomeListViewHolder> {
    private final static String TAG = WalletRedPkgIncomeAdapter.class.getSimpleName();
    private final static int defaultCount = 8;
    private Context mContext;
    private List<?> mData;

    //TODO adapter data
    public WalletRedPkgIncomeAdapter(Context context, List<?> data) {
        super();
        mContext = context;
        mData = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(WalletRedPkgIncomeListViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(WalletRedPkgIncomeListViewHolder holder, int position) {
        LocalLog.d(TAG, "updateListItem() enter");

        //转换为毫秒级
        if (mData.get(position) instanceof IncomeResponse.DataBeanX.DataBean) {
            long createTime = ((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            LocalLog.d(TAG, "createTime = " + createTime);
            String dateStr = DateTimeUtil.formatDateTime(createTime * 1000, DateTimeUtil.DF_YYYY_MM_DD);
            LocalLog.d(TAG, "dateStr = " + dateStr);
            holder.date.setText(dateStr);
            holder.addIncome.setText(String.valueOf(((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getAmount()));
            holder.incomeFrom.setText(String.valueOf(((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getName()));
            if (((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getTypeid() == 1) {
                holder.incomeFrom.setText(String.valueOf(((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getCirclename()));
            } else if (((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getTypeid() == 6) {
                holder.incomeFrom.setText(String.valueOf(((IncomeResponse.DataBeanX.DataBean) mData.get(position)).getName()));
            }
        } else if (mData.get(position) instanceof AllIncomeResponse.DataBeanX.DataBean) {
            long createTime = ((AllIncomeResponse.DataBeanX.DataBean) mData.get(position)).getCreate_time();
            LocalLog.d(TAG, "createTime = " + createTime);
            String dateStr = DateTimeUtil.formatDateTime(createTime * 1000, DateTimeUtil.DF_YYYY_MM_DD);
            LocalLog.d(TAG, "dateStr = " + dateStr);
            holder.date.setText(dateStr);
            holder.addIncome.setText(String.valueOf(((AllIncomeResponse.DataBeanX.DataBean) mData.get(position)).getAmount()));
            if (((AllIncomeResponse.DataBeanX.DataBean) mData.get(position)).getTypeid() == 1) {
                holder.incomeFrom.setText(String.valueOf(((AllIncomeResponse.DataBeanX.DataBean) mData.get(position)).getCirclename()));
            } else if (((AllIncomeResponse.DataBeanX.DataBean) mData.get(position)).getTypeid() == 6) {
                holder.incomeFrom.setText(String.valueOf(((AllIncomeResponse.DataBeanX.DataBean) mData.get(position)).getName()));
            }
        }
    }

    @Override
    public void onBindViewHolder(WalletRedPkgIncomeListViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public WalletRedPkgIncomeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WalletRedPkgIncomeListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.wallet_income_list,
                parent, false));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class WalletRedPkgIncomeListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.income_from)
        TextView incomeFrom;
        @Bind(R.id.add_income)
        TextView addIncome;
        @Bind(R.id.income_list_item)
        RelativeLayout incomeListItem;

        public WalletRedPkgIncomeListViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            date = (TextView) view.findViewById(R.id.date);
            incomeFrom = (TextView) view.findViewById(R.id.income_from);
            addIncome = (TextView) view.findViewById(R.id.add_income);
        }
    }
}
