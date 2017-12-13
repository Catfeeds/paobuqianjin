package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2017/12/12.
 */

public class MyHotCircleAdapter extends RecyclerView.Adapter<MyHotCircleAdapter.MyHotCircleViewHolder> {
    private final static String  TAG = MyHotCircleAdapter.class.getSimpleName();
    private Context myHotContext;
    private final static int defaultValue = 3;

    public MyHotCircleAdapter(Context context) {
        super();
        myHotContext = context;
    }

    @Override
    public MyHotCircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHotCircleViewHolder holder = new MyHotCircleViewHolder(
                LayoutInflater.from(myHotContext).inflate(R.layout.my_circle_list, parent, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return defaultValue;
    }

    @Override
    public void onBindViewHolder(MyHotCircleViewHolder holder, int position) {

    }

    class MyHotCircleViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item;

        public MyHotCircleViewHolder(View view) {
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
