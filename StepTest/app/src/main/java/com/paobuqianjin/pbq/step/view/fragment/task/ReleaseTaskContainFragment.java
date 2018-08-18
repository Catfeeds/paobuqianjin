package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ConfirmResult;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorRedDetailActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/19.
 */

public class ReleaseTaskContainFragment extends BaseFragment implements ConfirmResult {
    private final static String TAG = ReleaseTaskContainFragment.class.getSimpleName();
    @Bind(R.id.release_choice_tab)
    TabLayout releaseChoiceTab;
    @Bind(R.id.bar)
    RelativeLayout bar;
    @Bind(R.id.release_choice_pager)
    ViewPager releaseChoicePager;
    @Bind(R.id.buttone_left_bar)
    RelativeLayout buttoneLeftBar;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    private ReleaseTaskSponsorFragment releaseTaskSponsorFragment;
    private ReleaseTaskPersonFragment releaseTaskPersonFragment;
    Banner banner;
    private int selectPage = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_release_container_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        releaseTaskPersonFragment = new ReleaseTaskPersonFragment();
        releaseTaskSponsorFragment = new ReleaseTaskSponsorFragment();
        String[] titles = {"个人", "商家"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(releaseTaskPersonFragment);
        fragments.add(releaseTaskSponsorFragment);
        releaseChoiceTab = (TabLayout) viewRoot.findViewById(R.id.release_choice_tab);
        releaseChoicePager = (ViewPager) viewRoot.findViewById(R.id.release_choice_pager);
        btnConfirm = (Button) viewRoot.findViewById(R.id.btn_confirm);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);

        releaseChoicePager.setAdapter(tabAdapter);
        releaseChoiceTab.setupWithViewPager(releaseChoicePager);
        for (int i = 0; i < releaseChoiceTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);

        }
        releaseChoiceTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(releaseChoiceTab, 60, 60);
            }
        });
        releaseChoiceTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab != null) {
                    switch (tab.getPosition()) {
                        case 0:
                            selectPage = 0;
                            break;
                        case 1:
                            selectPage = 1;
                            break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        banner = (Banner) viewRoot.findViewById(R.id.banner);
        loadBanner();
    }

    private void confirmEnable(boolean enable) {
        LocalLog.d(TAG, "confirmEnable() enter");
        btnConfirm.setEnabled(enable);
        if (enable) {
            btnConfirm.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rect_no_corner));
        } else {
            btnConfirm.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rect_diss_no_corner));
        }
    }

    @Override
    public void result(boolean enable) {
        confirmEnable(enable);
    }

    private void loadBanner() {
        final String bannerUrl = NetApi.urlAd + "?position=task_list";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(getActivity()).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    final ArrayList<AdObject> adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
                                    adObject.setImg_url(adresponse.getData().get(i).getImgs().get(j).getImg_url());
                                    adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                                    adList.add(adObject);
                                }
                            }
                        }
                    }
                    banner.setImageLoader(new BannerImageLoader())
                            .setImages(adList)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .setBannerAnimation(Transformer.Default)
                            .isAutoPlay(true)
                            .setDelayTime(2000)
                            .setIndicatorGravity(BannerConfig.CENTER)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    String targetUrl = adList.get(position).getTarget_url();
                                    if(!TextUtils.isEmpty(targetUrl)) startActivity(new Intent(getActivity(), SingleWebViewActivity.class).putExtra("url",targetUrl));
                                }
                            })
                            .start();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取失败!");
            }
        });
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

    @OnClick({R.id.buttone_left_bar, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttone_left_bar:
                getActivity().finish();
                break;
            case R.id.btn_confirm:
                if (selectPage == 0) {
                    releaseTaskPersonFragment.confirm(this);
                } else if (selectPage == 1) {
                    releaseTaskSponsorFragment.confirm(this);
                }
                break;
        }
    }
}
