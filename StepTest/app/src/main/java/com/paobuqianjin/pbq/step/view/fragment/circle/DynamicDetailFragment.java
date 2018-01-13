package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicCommentUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.LikeSupportActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.TopLevelContentAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/29.
 */

public class DynamicDetailFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = DynamicDetailFragment.class.getSimpleName();
    @Bind(R.id.dynamic_user_icon)
    CircleImageView dynamicUserIcon;
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
    @Bind(R.id.dynamic_location_city)
    TextView dynamicLocationCity;
    @Bind(R.id.content_numbers)
    TextView contentNumbers;
    @Bind(R.id.content_number_icon)
    ImageView contentNumberIcon;
    @Bind(R.id.content_supports)
    TextView contentSupports;
    @Bind(R.id.location_support_rel)
    RelativeLayout locationSupportRel;
    @Bind(R.id.line_content_list)
    ImageView lineContentList;
    @Bind(R.id.src_dynamic_content)
    RelativeLayout srcDynamicContent;
    @Bind(R.id.support_peoples)
    TextView supportPeoples;
    @Bind(R.id.support_icon_recycler)
    RecyclerView supportIcon;
    @Bind(R.id.share_icon)
    ImageView shareIcon;
    @Bind(R.id.support_pics)
    RelativeLayout supportPics;
    @Bind(R.id.support_rel)
    RelativeLayout supportRel;
    @Bind(R.id.content_details_list_item)
    RecyclerView contentDetailsListItem;
    @Bind(R.id.content_details)
    RelativeLayout contentDetails;

    private List<View> Mview = new ArrayList<>();
    private List<View> dots;
    private int oldPosition;
    private int currentItem;
    private View imageView0, imageView1, imageView2, imageView3;
    ImageViewPagerAdapter adapter;
    private LayoutInflater inflater;
    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManagerContent;

    public int getDynamicid() {
        return dynamicid;
    }

    public void setDynamicid(int dynamicid) {
        this.dynamicid = dynamicid;
    }

    private int dynamicid = -1;
    private int currentIndexPage = 1;

    @Override
    protected int getLayoutResId() {
        return R.layout.dynamic_detail_fg;
    }

    @Override
    protected String title() {
        return "动态详情";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        Presenter.getInstance(getContext()).attachUiInterface(dynamicCommentUiInterface);
        Presenter.getInstance(getContext()).getDynamicCommentList(dynamicid, currentIndexPage, 10);
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        dots = new ArrayList<View>();
        dot1 = viewRoot.findViewById(R.id.dot_1);
        dot2 = viewRoot.findViewById(R.id.dot_2);
        dot3 = viewRoot.findViewById(R.id.dot_3);
        dot4 = viewRoot.findViewById(R.id.dot_4);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);
        imageView0 = inflater.inflate(R.layout.image_view_pager, null);
        imageView1 = inflater.inflate(R.layout.image_view_pager, null);
        imageView2 = inflater.inflate(R.layout.image_view_pager, null);
        imageView3 = inflater.inflate(R.layout.image_view_pager, null);

        Mview.add(imageView0);
        Mview.add(imageView1);
        Mview.add(imageView2);
        Mview.add(imageView3);
        imageViewpager = (ViewPager) viewRoot.findViewById(R.id.image_viewpager);
        imageViewpager.addOnPageChangeListener(onPageChangeListener);
        dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);

        adapter = new ImageViewPagerAdapter(getContext(), Mview);
        imageViewpager.setAdapter(adapter);

        supportIcon = (RecyclerView) viewRoot.findViewById(R.id.support_icon_recycler);
        supportIcon.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        supportIcon.setLayoutManager(layoutManager);
        supportIcon.setAdapter(new LikeUserAdapter(getContext()));
        supportIcon.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(10));
        shareIcon = (ImageView) viewRoot.findViewById(R.id.share_icon);
        shareIcon.setOnClickListener(onClickListener);

        contentDetailsListItem = (RecyclerView) viewRoot.findViewById(R.id.content_details_list_item);
        layoutManagerContent = new LinearLayoutManager(getContext());
        contentDetailsListItem.setLayoutManager(layoutManagerContent);
        contentDetailsListItem.setAdapter(new TopLevelContentAdapter(getContext()));
    }

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


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalLog.d(TAG, "onClick() enter 查看点赞");
            switch (view.getId()) {
                case R.id.share_icon:
                    startActivity(LikeSupportActivity.class, null);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imageViewpager.removeOnPageChangeListener(onPageChangeListener);
        Presenter.getInstance(getContext()).dispatchUiInterface(dynamicCommentUiInterface);
        ButterKnife.unbind(this);
    }


    private DynamicCommentUiInterface dynamicCommentUiInterface = new DynamicCommentUiInterface() {
        @Override
        public void response(DynamicCommentResponse dynamicCommentResponse) {
            LocalLog.d(TAG, "DynamicCommentResponse() enter" + dynamicCommentResponse.toString());

        }
    };
}
