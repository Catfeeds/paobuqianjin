package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleAdapter extends RecyclerView.Adapter<SearchCircleAdapter.SearchCirCleViewHolder> {
    private final static String TAG = SearchCircleAdapter.class.getSimpleName();
    private final static int defaultValue = 7;
    private Context mContext;
    public SearchCircleAdapter(Context context){
        mContext = context;
    }
    @Override
    public SearchCirCleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchCircleAdapter.SearchCirCleViewHolder holder = new SearchCircleAdapter.SearchCirCleViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.circle_kan_ban_list, parent, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return defaultValue;
    }

    @Override
    public void onBindViewHolder(SearchCirCleViewHolder holder, int position) {

    }

    class SearchCirCleViewHolder extends RecyclerView.ViewHolder {

        public SearchCirCleViewHolder(View view) {
            super(view);
        }
    }


}
