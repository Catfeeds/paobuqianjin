package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.DynamicActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/3/2.
 */

public class UserDynamicRecordSecondAdapter extends RecyclerView.Adapter<UserDynamicRecordSecondAdapter.UserDynamicRecordSecondViewHolder> {
    private final static String TAG = UserDynamicRecordSecondAdapter.class.getSimpleName();
    Context context;
    List<?> mData;

    public UserDynamicRecordSecondAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public UserDynamicRecordSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDynamicRecordSecondViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamic_item_times, parent, false));
    }

    @Override
    public void onBindViewHolder(UserDynamicRecordSecondViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(UserDynamicRecordSecondViewHolder holder, int position) {
        LocalLog.d(TAG, "updateListItem() enter position =" + position);
        if (mData.get(position) instanceof DynamicPersonResponse.DataBeanX.DataBean) {
            holder.dynamicDes.setText(((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getDynamic());
            int imageSize = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().size();
            LocalLog.d(TAG, "imageSize = " + imageSize);
            if (((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getDynamic().equals("")) {
                LocalLog.d(TAG, "无内容");
                //holder.dynamicDes.setVisibility(View.GONE);
            } else {
                holder.dynamicDes.setText(((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getDynamic());
            }
            int likes = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getVote();
            int content = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getComment();
            LocalLog.d(TAG, "点赞数 = " + likes + ",评论数 = " + content);
            if (likes > 0) {
                holder.likeNumIcon.setImageResource(R.drawable.fabulous_s);
                holder.contentSupports.setText(String.valueOf(likes));
            } else {
                holder.contentSupports.setText(String.valueOf(0));
            }

            if (content > 0) {
                holder.contentNumbers.setText(String.valueOf(content));
                String firstContentDes = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getOne_comment().getNickname() + ":";
                String firstContentText = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getOne_comment().getContent();
                SpannableStringBuilder style = new SpannableStringBuilder(firstContentDes + firstContentText);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), 0, firstContentDes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff161727")), firstContentDes.length(), (firstContentDes + firstContentText).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                LocalLog.d(TAG, "没有任何评论");
                holder.contentNumbers.setText(String.valueOf(0));
            }

            if (imageSize >= 1) {
                if (!((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0).equals("")) {
                    holder.image.setVisibility(View.VISIBLE);
                    Presenter.getInstance(context).getImage(holder.image, ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getImages().get(0));
                }
            }

            holder.dynamicId = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getId();
            holder.userid = ((DynamicPersonResponse.DataBeanX.DataBean) mData.get(position)).getUserid();
        }

    }

    public class UserDynamicRecordSecondViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.dynamic_release_time)
        TextView dynamicReleaseTime;
        @Bind(R.id.dot)
        ImageView dot;
        @Bind(R.id.dynamic_des)
        TextView dynamicDes;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.pic_viewpager)
        RelativeLayout picViewpager;
        @Bind(R.id.content_numbers)
        TextView contentNumbers;
        @Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        @Bind(R.id.content_supports)
        TextView contentSupports;
        @Bind(R.id.like_num_icon)
        ImageView likeNumIcon;
        @Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;
        @Bind(R.id.item)
        RelativeLayout item;

        int dynamicId = -1;
        int userid = -1;

        public UserDynamicRecordSecondViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            dynamicReleaseTime = (TextView) view.findViewById(R.id.dynamic_release_time);
            dynamicDes = (TextView) view.findViewById(R.id.dynamic_des);
            dynamicDes.setOnClickListener(onClickListener);
            image = (ImageView) view.findViewById(R.id.image);
            image.setVisibility(View.GONE);
            image.setOnClickListener(onClickListener);
            contentNumbers = (TextView) view.findViewById(R.id.content_numbers);
            likeNumIcon = (ImageView)view.findViewById(R.id.like_num_icon);
            contentNumberIcon = (ImageView) view.findViewById(R.id.content_number_icon);
            contentSupports = (TextView) view.findViewById(R.id.content_supports);

        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.dynamic_des:
                    case R.id.image:
                        LocalLog.d(TAG, "dynamicId = " + dynamicId + ",userId = " + userid);
                        Intent intent = new Intent();
                        intent.putExtra(context.getPackageName() + "dynamicId", dynamicId);
                        intent.putExtra(context.getPackageName() + "userId", userid);
                        intent.setClass(context, DynamicActivity.class);
                        context.startActivity(intent);
                        break;

                }
            }
        };
    }


    //设置RecyclerView item间距
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else {
                //outRect.left = mSpace;
                outRect.top = mSpace;
            }
            /*if (parent.getChildAdapterPosition(view) == UserDynamicRecordAdapter.this.mData.size() - 1) {
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
