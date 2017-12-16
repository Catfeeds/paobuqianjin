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
 * Created by pbq on 2017/12/13.
 */

public class CircleChooseGoodAdapter extends RecyclerView.Adapter<CircleChooseGoodAdapter.CircleChooseViewHolder> {
    private final static String TAG = CircleChooseGoodAdapter.class.getSimpleName();
    private Context mContext;
    private final static int defaultCount = 10;

    public CircleChooseGoodAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public CircleChooseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CircleChooseViewHolder holder = new CircleChooseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_choose_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(CircleChooseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    class CircleChooseViewHolder extends RecyclerView.ViewHolder {
        public CircleChooseViewHolder(View view) {
            super(view);
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else {
                outRect.bottom = mSpace;
            }
            if (parent.getChildAdapterPosition(view) == defaultCount - 1) {
                outRect.bottom = 0;
                LocalLog.d(TAG, "getItemOffsets() last set");
            } else {
                outRect.bottom = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
