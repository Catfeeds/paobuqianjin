package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2017/12/31.
 */

public class LikeUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG =  LikeUserAdapter.class.getSimpleName();
    private Context context;
    private final static int defaultValue = 5;
    //TODO DATA
    public LikeUserAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeUserViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.support_image_list, parent, false),viewType);
    }

    @Override
    public int getItemCount() {
        return defaultValue;
    }


    public class LikeUserViewHolder extends RecyclerView.ViewHolder {
        public LikeUserViewHolder(View view, int viewType) {
            super(view);
        }
    }

    //设置RecyclerView item间距
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0;
            } else {
                outRect.left = mSpace;
            }
            if (parent.getChildAdapterPosition(view) == defaultValue - 1) {
                outRect.right = 0;
                LocalLog.d(TAG,"getItemOffsets() last set");
            } else {
                outRect.right = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
