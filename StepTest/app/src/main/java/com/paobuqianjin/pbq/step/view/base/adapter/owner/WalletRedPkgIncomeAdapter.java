package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;

import java.util.List;

/**
 * Created by pbq on 2018/1/18.
 */

public class WalletRedPkgIncomeAdapter extends RecyclerView.Adapter<WalletRedPkgIncomeAdapter.WalletRedPkgIncomeListViewHolder> {
    private final static String TAG = WalletRedPkgIncomeAdapter.class.getSimpleName();
    private final static int defaultCount = 8;
    private Context mContext;

    //TODO adapter data
    public WalletRedPkgIncomeAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(WalletRedPkgIncomeListViewHolder holder, int position) {

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
        return defaultCount;
    }

    public class WalletRedPkgIncomeListViewHolder extends RecyclerView.ViewHolder {
        public WalletRedPkgIncomeListViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {

        }
    }
}
