package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by pbq on 2018/5/25.
 */

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.BankCardViewHolder> {
    private Context mContext;
    private List<?> mData;

    public BankCardAdapter(Context context, List<?> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(BankCardViewHolder holder, int position) {

    }

    @Override
    public BankCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankCardViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.bank_card_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        /*return mData == null ? 0 : mData.size();*/
        return 7;
    }

    public class BankCardViewHolder extends RecyclerView.ViewHolder {
        public BankCardViewHolder(View view) {
            super(view);
        }
    }
}
