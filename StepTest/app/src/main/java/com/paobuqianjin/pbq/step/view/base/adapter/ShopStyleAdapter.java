package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pbq on 2018/12/3.
 */

public class ShopStyleAdapter extends RecyclerView.Adapter<ShopStyleAdapter.ShopStyleViewHolder> {
    public ShopStyleAdapter(Context context, List<?> data) {
        super();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public ShopStyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ShopStyleViewHolder holder, int position) {
        
    }

    public class ShopStyleViewHolder extends RecyclerView.ViewHolder {
        public ShopStyleViewHolder(View view) {
            super(view);
        }
    }
}
