package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/26.
 */

public class SelectTaskFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = SelectTaskFriendAdapter.class.getSimpleName();
    private final static int defaultCount = 7;
    private Context context;

    public SelectTaskFriendAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectTaskFriendViewHolder(LayoutInflater.from(context).inflate(R.layout.select_friend_list_item,
                parent, false));
    }

    public class SelectTaskFriendViewHolder extends RecyclerView.ViewHolder {
        //@Bind(R.id.select_icon)
        ImageView selectIcon;
        //@Bind(R.id.dear_icon)
        CircleImageView dearIcon;
        //@Bind(R.id.dear_name)
        TextView dearName;

        public SelectTaskFriendViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
        }

    }
}
