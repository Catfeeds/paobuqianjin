package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.DynamicActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyDynamicAdapter extends RecyclerView.Adapter<MyDynamicAdapter.MyDynamicViewHolder> {
    private final static String TAG = MyDynamicViewHolder.class.getSimpleName();
    private final static int defaultCount = 3;
    private Context mContext;

    public MyDynamicAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyDynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyDynamicViewHolder(LayoutInflater.from(mContext).inflate(R.layout.my_dynamic_one_pic_style, parent, false));
    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    @Override
    public void onBindViewHolder(MyDynamicViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(MyDynamicViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public class MyDynamicViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dynamic_user_icon)
        CircleImageView dynamicUserIcon;
        @Bind(R.id.dynamic_user_name)
        TextView dynamicUserName;
        @Bind(R.id.dynamic_owner_rel)
        RelativeLayout dynamicOwnerRel;
        @Bind(R.id.dynamic_content_text)
        TextView dynamicContentText;
        @Bind(R.id.pic_content_rel)
        RelativeLayout picContentRel;
        @Bind(R.id.image_viewpager)
        ViewPager imageViewpager;
        @Bind(R.id.dot_1)
        View dot1;
        @Bind(R.id.dot_2)
        View dot2;
        @Bind(R.id.dot_3)
        View dot3;
        @Bind(R.id.dot_4)
        View dot4;
        @Bind(R.id.dot_line)
        LinearLayout dotLine;
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
        @Bind(R.id.line_content)
        ImageView lineContent;
        @Bind(R.id.first_content)
        TextView firstContent;
        @Bind(R.id.scan_more)
        TextView scanMore;
        @Bind(R.id.content_des)
        RelativeLayout contentDes;
        @Bind(R.id.line_content_list)
        ImageView lineContentList;
        @Bind(R.id.delete_dynamic)
        ImageView deleteDynamic;

        private List<View> Mview = new ArrayList<>();
        private List<View> dots;
        private int oldPosition;
        private int currentItem;
        private View imageView0, imageView1, imageView2, imageView3;
        ImageViewPagerAdapter adapter;

        public MyDynamicViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View viewRoot) {
            dots = new ArrayList<View>();
            dot1 = viewRoot.findViewById(R.id.dot_1);
            dot2 = viewRoot.findViewById(R.id.dot_2);
            dot3 = viewRoot.findViewById(R.id.dot_3);
            dot4 = viewRoot.findViewById(R.id.dot_4);
            dots.add(dot1);
            dots.add(dot2);
            dots.add(dot3);
            dots.add(dot4);
            imageView0 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
            imageView1 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
            imageView2 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);
            imageView3 = LayoutInflater.from(mContext).inflate(R.layout.image_view_pager, null);

            Mview.add(imageView0);
            Mview.add(imageView1);
            Mview.add(imageView2);
            Mview.add(imageView3);
            imageViewpager = (ViewPager) viewRoot.findViewById(R.id.image_viewpager);
            imageViewpager.addOnPageChangeListener(onPageChangeListener);
            dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);

            adapter = new ImageViewPagerAdapter(mContext, Mview);
            imageViewpager.setAdapter(adapter);
            scanMore = (TextView) viewRoot.findViewById(R.id.scan_more);
            scanMore.setOnClickListener(onClickListener);
            deleteDynamic = (ImageView) viewRoot.findViewById(R.id.delete_dynamic);
            deleteDynamic.setOnClickListener(onClickListener);

        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.scan_more:
                        LocalLog.d(TAG, "点击查看更多评价");
                        Intent intent = new Intent();
                        intent.setClass(mContext, DynamicActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case R.id.delete_dynamic:
                        LocalLog.d(TAG,"删除动态");
                        break;
                    default:
                        break;
                }
            }
        };

        private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LocalLog.d(TAG, "");
                dots.get(oldPosition).setBackgroundResource(R.drawable.image_viewpager_dot_unselected);
                dots.get(position).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
