package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/29.
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {
    private final static String TAG = RankAdapter.class.getSimpleName();
    private final static int defaultValue = 7;
    private Context mContext;

    public RankAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RankViewHolder holder = new RankViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.love_rank_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RankViewHolder holder, int position) {
        updateLists(holder, position);
    }

    private void updateLists(RankViewHolder holder, int position) {
        holder.rankNum.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return defaultValue;
    }

    public class RankViewHolder extends RecyclerView.ViewHolder {
        TextView rankNum;
        CircleImageView circleLogoSearch;
        TextView searchCircleDesListName;
        TextView loveNumber;

        public RankViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            LocalLog.d(TAG, "initView() enter");
            rankNum = (TextView) view.findViewById(R.id.rank_num);
            circleLogoSearch = (CircleImageView) view.findViewById(R.id.circle_logo_search);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            loveNumber = (TextView) view.findViewById(R.id.love_number);
        }
    }
}
