package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/31.
 */

public class LikeSupportDetailAdapter extends RecyclerView.Adapter<LikeSupportDetailAdapter.LikeSupportViewHolder> {
    private final static String TAG = LikeSupportDetailAdapter.class.getSimpleName();
    private final static int defaultCount = 8;
    private Context context;
    List<?> mData;

    public LikeSupportDetailAdapter(Context context, List<?> data) {
        super();
        this.context = context;
        mData = data;
    }

    @Override
    public LikeSupportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeSupportViewHolder(LayoutInflater.from(context).inflate(R.layout.live_support_list, parent,
                false));
    }

    @Override
    public void onBindViewHolder(LikeSupportViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(LikeSupportViewHolder holder, int position) {
        if (mData.get(position) instanceof DynamicLikeListResponse.DataBeanX.DataBean) {
            /*Presenter.getInstance(context).getPlaceErrorImage(holder.circleLogoSearch, ((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);*/
            LoadBitmap.glideLoad(context, holder.circleLogoSearch, ((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.searchCircleDesListName.setText(((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getNickname());
            holder.userid = ((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
            /*查询我与该用户的关注状态*/
            if (holder.userid == Presenter.getInstance(context).getId()) {
                holder.loveNumber.setVisibility(View.GONE);
            } else {
                Presenter.getInstance(context).postFollowStatus(holder.loveNumber, ((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getUserid());
            }

            /*if (((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }*/
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class LikeSupportViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.circle_logo_search)
        CircleImageView circleLogoSearch;
        @Bind(R.id.search_circle_des_list_name)
        TextView searchCircleDesListName;
        @Bind(R.id.love_number)
        Button loveNumber;
        @Bind(R.id.like_support_list_rel)
        RelativeLayout likeSupportListRel;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;
        int userid = -1;

        public LikeSupportViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            circleLogoSearch = (CircleImageView) view.findViewById(R.id.circle_logo_search);
            circleLogoSearch.setOnClickListener(onClickListener);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            loveNumber = (Button) view.findViewById(R.id.love_number);
            loveNumber.setOnClickListener(onClickListener);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_logo_search:
                        LocalLog.d(TAG, "点击头像");
                        Intent intent = new Intent();
                        //TODO ACTION_SCAN_USERID
                        intent.putExtra("userid", userid);
                        intent.setClass(context, UserCenterActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.love_number:
                        Presenter.getInstance(context).postUserStatus(loveNumber, userid);
                        break;
                }
            }
        };

    }
}
