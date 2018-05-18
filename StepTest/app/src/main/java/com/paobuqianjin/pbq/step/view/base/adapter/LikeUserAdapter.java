package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/31.
 */

public class LikeUserAdapter extends RecyclerView.Adapter<LikeUserAdapter.LikeUserViewHolder> {
    private final static String TAG = LikeUserAdapter.class.getSimpleName();
    private Context context;
    private final static int defaultValue = 7;
    List<?> mData;

    //TODO DATA
    public LikeUserAdapter(Context context, List<?> data) {
        super();
        this.context = context;
        mData = data;
    }

    public void notifyDataSetChanged(List<?> data) {
        mData = data;
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(LikeUserViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(LikeUserViewHolder holder, int position) {
        if (mData.get(position) instanceof DynamicLikeListResponse.DataBeanX.DataBean) {
            Presenter.getInstance(context).getPlaceErrorImage(holder.shareIcon, ((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (((DynamicLikeListResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }
        } else if (mData.get(position) instanceof UserFriendResponse.DataBeanX.DataBean) {
            Presenter.getInstance(context).getPlaceErrorImage(holder.shareIcon, ((UserFriendResponse.DataBeanX.DataBean) mData.get(position)).getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);
            if (((UserFriendResponse.DataBeanX.DataBean) mData.get(position)).getVip() == 1) {
                holder.vipFlg.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public LikeUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeUserViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.support_image_list, parent, false), viewType);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            int size = mData.size();
            if (size <= defaultValue) {
                return size;
            } else {
                return defaultValue;
            }

        } else {
            return 0;
        }
    }


    public class LikeUserViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.share_icon)
        CircleImageView shareIcon;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;

        public LikeUserViewHolder(View view, int viewType) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            shareIcon = (CircleImageView) view.findViewById(R.id.share_icon);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
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
            /*if (parent.getChildAdapterPosition(view) == defaultValue - 1) {
                outRect.right = 0;
                LocalLog.d(TAG, "getItemOffsets() last set");
            } else {
                outRect.right = mSpace;
            }*/
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
