package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserFollowInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AddFriendAddressActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.FollowAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.CustomViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyFriendFragment extends BaseBarStyleTextViewFragment implements UserFollowInterface {
    private final static String TAG = MyFriendFragment.class.getSimpleName();

    String[] titles = {"互相关注", "已关注", "关注我的"};
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.follow_tab)
    TabLayout followTab;
    @Bind(R.id.tab_span)
    RelativeLayout tabSpan;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.friend_recycler_viewpager)
    CustomViewPager friendRecyclerViewpager;
    private FollowOtoFragment followOtoFragment;
    private MyFollowFragment myFollowFragment;
    private FollowMeFragment followMeFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_friend_fg;
    }

    @Override
    protected String title() {
        return "我的关注";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).getFollows("mutual", 1, 10);
        Presenter.getInstance(getContext()).getFollows("my", 1, 10);
        Presenter.getInstance(getContext()).getFollows("me", 1, 10);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        setToolBarListener(toolBarListener);
        followTab = (TabLayout) viewRoot.findViewById(R.id.follow_tab);
        friendRecyclerViewpager = (CustomViewPager) viewRoot.findViewById(R.id.friend_recycler_viewpager);

        followOtoFragment = new FollowOtoFragment();
        myFollowFragment = new MyFollowFragment();
        followMeFragment = new FollowMeFragment();
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(followOtoFragment);
        fragments.add(myFollowFragment);
        fragments.add(followMeFragment);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);
        friendRecyclerViewpager.setAdapter(tabAdapter);


        followTab.setupWithViewPager(friendRecyclerViewpager);

        for (int i = 0; i < followTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            followTab.getTabAt(i).setCustomView(getTabView(i));
        }

        followTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(followTab, 10, 10);
            }
        });

    }

    private View getTabView(int position) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.text_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            textView.setText(titles[0]);
            view.setGravity(Gravity.LEFT);
        } else if (position == 1) {
            textView.setText(titles[1]);
            view.setGravity(Gravity.CENTER);
        } else if (position == 2) {
            textView.setText(titles[2]);
            view.setGravity(Gravity.RIGHT);
        }
        return view;
    }


    public void setIndicator(TabLayout tab, int leftDip, int rightDip) {
        Class<?> tabLayout = tab.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout IITab = null;
        try {
            IITab = (LinearLayout) tabStrip.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < IITab.getChildCount(); i++) {
            View child = IITab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "邀请好友");
            Intent friendAddressIntent = new Intent();
            friendAddressIntent.setClass(getContext(), AddFriendAddressActivity.class);
            startActivity(friendAddressIntent);
        }
    };

    @Override
    public Object right() {
        return "添加关注";
    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter");

    }

    @Override
    public void response(FollowUserResponse followUserResponse) {
        LocalLog.d(TAG, "FollowUserResponse() enter " + followUserResponse.toString());
        followMeFragment.setAdapter(new FollowAdapter(getContext(), followUserResponse.getData().getData()));
    }

    @Override
    public void response(UserIdFollowResponse userIdFollowResponse) {
        LocalLog.d(TAG, "UserIdFollowResponse() enter " + userIdFollowResponse.toString());
        if (userIdFollowResponse.getError() == 0) {
            if (userIdFollowResponse.getData() != null) {
                myFollowFragment.setAdapter(new FollowAdapter(getContext(), userIdFollowResponse.getData().getData()));
            }
        } else if (userIdFollowResponse.getError() == 1) {
            LocalLog.e(TAG, "没有数据");
        } else if (userIdFollowResponse.getError() == -1) {
            LocalLog.e(TAG, userIdFollowResponse.getMessage());
        }
    }

    @Override
    public void response(UserFollowOtOResponse userFollowOtOResponse) {
        LocalLog.d(TAG, "UserFollowOtOResponse() enter " + userFollowOtOResponse.toString());
        if (userFollowOtOResponse.getError() == 0) {
            if (userFollowOtOResponse.getData() != null) {
                followOtoFragment.setAdapter(new FollowAdapter(getContext(), userFollowOtOResponse.getData().getData()));
            }
        }

    }
}
