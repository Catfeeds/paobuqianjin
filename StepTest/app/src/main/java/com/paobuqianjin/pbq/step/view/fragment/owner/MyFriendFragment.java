package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.AddFriendAddressActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyFriendFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = MyFriendFragment.class.getSimpleName();

    String[] titles = {"好友", "已关注", "关注我的",};
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
    ViewPager friendRecyclerViewpager;
    @Bind(R.id.create_circle_swipe)
    SwipeRefreshLayout createCircleSwipe;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.my_friend_fg)
    RelativeLayout myFriendFg;
    private MyFollowFragment myFollowFragment;
    private FollowMeFragment followMeFragment;
    private FollowOtoFragment followOtoFragment;
    private int selectPage = 0;
    private String keyWord = "";
    private TextView viewNum;

    @Override

    protected int getLayoutResId() {
        return R.layout.my_friend_fg;
    }

    @Override
    protected String title() {
        return "关注";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void searchKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    protected void initView(View viewRoot) {
        setToolBarListener(toolBarListener);
        followTab = (TabLayout) viewRoot.findViewById(R.id.follow_tab);
        friendRecyclerViewpager = (ViewPager) viewRoot.findViewById(R.id.friend_recycler_viewpager);
        createCircleSwipe = (SwipeRefreshLayout) viewRoot.findViewById(R.id.create_circle_swipe);

        myFollowFragment = new MyFollowFragment();
        followMeFragment = new FollowMeFragment();
        followMeFragment.setUpFollowMeInterface(upFollowMeInterface);
        followOtoFragment = new FollowOtoFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(followOtoFragment);
        fragments.add(myFollowFragment);
        fragments.add(followMeFragment);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);
        friendRecyclerViewpager.setAdapter(tabAdapter);


        followTab.setupWithViewPager(friendRecyclerViewpager);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setHint("搜索昵称");
        searchIcon = (RelativeLayout) viewRoot.findViewById(R.id.search_icon);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    LocalLog.d(TAG, "onEditorAction() selectPage = " + selectPage);
                    if (selectPage == 0) {
                        followOtoFragment.searchKeyWord(searchCircleText.getText().toString());
                    } else if (selectPage == 1) {
                        myFollowFragment.searchKeyWord(searchCircleText.getText().toString());
                    } else if (selectPage == 2) {
                        followMeFragment.searchKeyWord(searchCircleText.getText().toString());
                    }
                    Utils.hideInput(getContext());
                }
                return false;
            }
        });
        searchCircleText.addTextChangedListener(textWatcher);

        cancelIcon = (RelativeLayout) viewRoot.findViewById(R.id.cancel_icon);
        cancelIcon.setOnClickListener(onClickListener);
        //
        createCircleSwipe.setOnRefreshListener(mRefreshListener);
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

        followTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LocalLog.d(TAG, "onTabSelected() enter" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        selectPage = 0;
                        LocalLog.d(TAG, "onTabSelected() selectPage = " + selectPage);
                        break;
                    case 1:
                        selectPage = 1;
                        LocalLog.d(TAG, "onTabSelected() selectPage = " + selectPage);
                        break;
                    case 2:
                        selectPage = 2;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private UpFollowMeInterface upFollowMeInterface = new UpFollowMeInterface() {
        @Override
        public void update(int data) {
            if (!isAdded() || viewNum == null) {
                return;
            }
            if (data > 0) {
                viewNum.setText(String.valueOf(data));
                viewNum.setVisibility(View.VISIBLE);
            } else {
                viewNum.setVisibility(View.GONE);
            }
        }
    };

    public interface UpFollowMeInterface {
        public void update(int data);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cancel_icon:
                    LocalLog.d(TAG, "取消搜索,显示原来的数据");
                    searchCircleText.setText(null);
                    keyWord = "";
                    break;
            }
        }
    };
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            if (!TextUtils.isEmpty(temp)) {
                LocalLog.d(TAG, "显示取消搜索界面");
                cancelIcon.setVisibility(View.VISIBLE);
                cancelIcon.setOnClickListener(onClickListener);
            } else {
                LocalLog.d(TAG, "隐藏搜索界面");
                cancelIcon.setVisibility(View.GONE);
                keyWord = "";
                LocalLog.d(TAG, "afterTextChanged() enter selectPage" + selectPage);
                if (selectPage == 0) {
                    followOtoFragment.searchKeyWord("");
                } else if (selectPage == 1) {
                    myFollowFragment.searchKeyWord("");
                } else {
                    followMeFragment.searchKeyWord("");
                }
            }
        }
    };
    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            LocalLog.d(TAG, "刷新当前页面! selectPage" + selectPage);
            if (selectPage == 0) {

            } else if (selectPage == 1) {

            } else if (selectPage == 2) {

            }
            if (createCircleSwipe != null) {
                createCircleSwipe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (createCircleSwipe != null) {
                            createCircleSwipe.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        }
    };


    public void finishUpdate() {
        if (isAdded() && createCircleSwipe != null) {
            createCircleSwipe.setRefreshing(false);
        }
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
            viewNum = (TextView) view.findViewById(R.id.att_num);
        }
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
/*        followOtoFragment.update();
        followMeFragment.update();*/
    }

    public void setIndicator(TabLayout tab, int leftDip, int rightDip) {
        if (tab == null) {
            return;
        }
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {
            getActivity().onBackPressed();
        }

        @Override
        public void clickRight() {
            Intent friendAddressIntent = new Intent();
            friendAddressIntent.setClass(getContext(), AddFriendAddressActivity.class);
            startActivity(friendAddressIntent);
        }
    };

    @Override
    public Object right() {
        return "添加关注";
    }

}
