package com.paobuqianjin.pbq.step.view.fragment.circle;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


import com.google.zxing.integration.android.IntentIntegrator;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CreateDynamicActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.CirclePageAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2017/12/1.
 */

public final class FriendCircleFragment extends BaseFragment {
    private final static String TAG = FriendCircleFragment.class.getSimpleName();
    private android.support.design.widget.TabLayout mCircleTabLayout;
    private ViewPager mCirclePager;
    private BaseActivity mActivity;
    RelativeLayout scanMark;
    private ImageView iScanView/*, iCamemaView*/;
    private Context mContext;
    private int currentIndexFriend = 0;
    private int mIndexFriend = 0;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private TranslateAnimation animationCircleType;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
        mContext = context;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_circle_page;
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
    protected void initView(View rootView) {
        super.initView(rootView);
        mCircleTabLayout = (TabLayout) rootView.findViewById(R.id.circle_item_tab);
        mCirclePager = (ViewPager) rootView.findViewById(R.id.circle_item_page);
        LocalLog.d(TAG, "initView() enter");
        HotCircleFragment hotCircleFragment = new HotCircleFragment();
        AttentionCircleFragment attentionCircleFragment = new AttentionCircleFragment();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(hotCircleFragment);
        fragments.add(attentionCircleFragment);

        CirclePageAdapter pageAdapter = new CirclePageAdapter(mActivity, mActivity.getSupportFragmentManager(), fragments);
        mCirclePager.setAdapter(pageAdapter);
        mCircleTabLayout.setupWithViewPager(mCirclePager);
        for (int i = 0; i < mCircleTabLayout.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            mCircleTabLayout.getTabAt(i).setCustomView(pageAdapter.getTabView(i));
        }
        mCircleTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mCircleTabLayout, 30, 30);
            }
        });

        mCircleTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LocalLog.d(TAG, "onTabSelected() enter" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        /*if (iCamemaView.getVisibility() == View.VISIBLE) {
                            iCamemaView.setVisibility(View.GONE);
                        }
                        if (iScanView.getVisibility() == View.GONE) {
                            iScanView.setVisibility(View.VISIBLE);
                        }*/
                        break;
                    case 1:
                        /*if (iCamemaView.getVisibility() == View.GONE) {
                            iCamemaView.setVisibility(View.VISIBLE);
                        }
                        if (iScanView.getVisibility() == View.VISIBLE) {
                            iScanView.setVisibility(View.GONE);
                        }*/
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
        LocalLog.d(TAG, "initView() leave");
        scanMark = (RelativeLayout) rootView.findViewById(R.id.scan_mark);
        scanMark.setOnClickListener(onClickListener);
        iScanView = (ImageView) rootView.findViewById(R.id.add_scan_release_dynamic);
        /*iCamemaView = (ImageView) rootView.findViewById(R.id.camema);*/
    }

    private void popSelect() {
        popCircleOpBar = View.inflate(getContext(), R.layout.scan_release_dynamic_pop, null);
        popupOpWindow = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.scan_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.release_dynamic_text).setOnClickListener(onClickListener);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindow = null;
            }
        });

        popupOpWindow.setFocusable(true);
        popupOpWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindow.showAsDropDown(scanMark, 20, -10);
        popCircleOpBar.startAnimation(animationCircleType);

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.scan_mark:
/*                    if (iScanView.getVisibility() == View.VISIBLE) {
                        LocalLog.d(TAG, "扫描二维码!");
                        new IntentIntegrator(getActivity())
                                .setOrientationLocked(false)
                                .setCaptureActivity(QrCodeScanActivity.class)
                                .initiateScan();
                    }

                    if (iCamemaView.getVisibility() == View.VISIBLE) {
                        LocalLog.d(TAG, " 发布动态!");
                        Intent intent = new Intent();
                        intent.setClass(getContext(), CreateDynamicActivity.class);
                        startActivity(intent);
                    }*/
                    //popSelect();
                    Intent intent = new Intent();
                    intent.setClass(getContext(), CreateDynamicActivity.class);
                    startActivity(intent);
                    break;
                case R.id.scan_text:

                    break;
                case R.id.release_dynamic_text:

                    break;
            }
        }
    };
}

