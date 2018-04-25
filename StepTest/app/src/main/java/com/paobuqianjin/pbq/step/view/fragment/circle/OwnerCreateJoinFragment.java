package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
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
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateJoinFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = OwnerCreateJoinFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.create_or_join)
    TabLayout createOrJoin;
    @Bind(R.id.line_view)
    ImageView lineView;
    @Bind(R.id.owner_create_join_pager)
    ViewPager ownerCreateJoinPager;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    private String keyWord = "";

    private OwnerCreateFragment ownerCreateFragment = new OwnerCreateFragment();
    private OwnerJoinFragment ownerJoinFragment = new OwnerJoinFragment();
    private int selectPage = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_circle_fg;
    }

    @Override
    protected String title() {
        return "我的圈子";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        String[] title = {"我创建的", "我加入的"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ownerCreateFragment);
        fragments.add(ownerJoinFragment);
        createOrJoin = (TabLayout) viewRoot.findViewById(R.id.create_or_join);
        ownerCreateJoinPager = (ViewPager) viewRoot.findViewById(R.id.owner_create_join_pager);
        TabAdapter pageAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, title);
        ownerCreateJoinPager.setAdapter(pageAdapter);
        createOrJoin.setupWithViewPager(ownerCreateJoinPager);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchIcon = (RelativeLayout) viewRoot.findViewById(R.id.search_icon);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (selectPage == 0) {
                        ownerCreateFragment.searchKeyWord(searchCircleText.getText().toString());
                    } else if (selectPage == 1) {
                        ownerJoinFragment.searchKeyWord(searchCircleText.getText().toString());
                    }
                    Utils.hideInput(getContext());
                }
                return false;
            }
        });
        searchCircleText.addTextChangedListener(textWatcher);
        cancelIcon = (RelativeLayout) viewRoot.findViewById(R.id.cancel_icon);
        cancelIcon.setOnClickListener(onClickListener);
        for (int i = 0; i < createOrJoin.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            //createOrJoin.getTabAt(i).setCustomView(pageAdapter.getTabView(i));
        }
        createOrJoin.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(createOrJoin, 50, 50);
            }
        });


        createOrJoin.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LocalLog.d(TAG, "onTabSelected() enter" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        selectPage = 0;
                        break;
                    case 1:
                        selectPage = 1;
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
                if (selectPage == 0) {
                    ownerCreateFragment.searchKeyWord("");
                } else if (selectPage == 1) {
                    ownerJoinFragment.searchKeyWord("");
                }
            }
        }
    };

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
