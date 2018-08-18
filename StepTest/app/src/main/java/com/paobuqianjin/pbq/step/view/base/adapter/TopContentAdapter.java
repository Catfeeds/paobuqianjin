package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.paobuqianjin.pbq.step.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/8/8.
 */
/*
@className :TopContentAdapter
*@date 2018/8/8
*@author
*@description 商家评论
*/
public class TopContentAdapter extends RecyclerView.Adapter<TopContentAdapter.TopLevelViewHolder> {
    private final static String TAG = TopContentAdapter.class.getSimpleName();
    List<?> mData;
    private Context context;


    public TopContentAdapter(Context context, List<?> data) {
        super();
        this.context = context;
        mData = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        this.mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public TopContentAdapter.TopLevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopContentAdapter.TopLevelViewHolder(LayoutInflater.from(context).inflate(
                R.layout.content_first_support, parent, false));
    }

    @Override
    public void onBindViewHolder(TopContentAdapter.TopLevelViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(TopContentAdapter.TopLevelViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class TopLevelViewHolder extends RecyclerView.ViewHolder {
        private LinearLayoutManager layoutManager;
        @Bind(R.id.content_user_icon)
        CircleImageView contentUserIcon;
        @Bind(R.id.user_content_name)
        TextView userContentName;
        @Bind(R.id.user_content_ranka)
        TextView userContentRanka;
        @Bind(R.id.time_content_a)
        TextView timeContentA;
        @Bind(R.id.time_content_b)
        TextView timeContentB;
        @Bind(R.id.contend_all_recycler)
        RecyclerView contendAllRecycler;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        MiddleContentAdapter middleContentAdapter;

        public TopLevelViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            contentUserIcon = (CircleImageView) view.findViewById(R.id.content_user_icon);
            userContentName = (TextView) view.findViewById(R.id.user_content_name);
            userContentRanka = (TextView) view.findViewById(R.id.user_content_ranka);
            contendAllRecycler = (RecyclerView) view.findViewById(R.id.contend_all_recycler);
            timeContentA = (TextView) view.findViewById(R.id.time_content_a);
            timeContentB = (TextView) view.findViewById(R.id.time_content_b);
            layoutManager = new LinearLayoutManager(context);
            contendAllRecycler.setLayoutManager(layoutManager);
        }
    }
}
