package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.customview.RedPkgAnimation;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;
import com.umeng.message.UmengNotifyClickActivity;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.android.agoo.common.AgooConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends UmengNotifyClickActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private static final String TAG = "SplashActivity";
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private ViewPager vp_first_income;
    private ImageView adImageView;
    private Button cancelAd;
    private int currentTimes = 3;
    Timer timer = new Timer();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        highApiEffects();
        if (isFirstOpen()) {
            return;
        }
        mContentView = findViewById(R.id.fullscreen_content);
        vp_first_income = (ViewPager) findViewById(R.id.vp_first_income);
        adImageView = (ImageView) findViewById(R.id.ad_pic);
        cancelAd = (Button) findViewById(R.id.cancel_ad);
        mHideHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstEnter = (boolean) SharedPreferencesUtil.get(Constants.SP_IS_FIRST_ENTER, true);
                if (isFirstEnter) {//首次进入
                    initViewpager();
                    SharedPreferencesUtil.put(Constants.SP_IS_FIRST_ENTER, false);
                } else {
                    loadBanner();
                    if (adImageView.getVisibility() == View.GONE) {
                        adImageView.setVisibility(View.VISIBLE);
                        mContentView.setVisibility(View.GONE);
                        cancelAd.setVisibility(View.VISIBLE);
                        String format = String.format(getString(R.string.cancel_ad), currentTimes);
                        cancelAd.setText(format);
                        RedPkgAnimation pkgAnimation = new RedPkgAnimation();
                        pkgAnimation.setHideAnimation(mContentView, 500);
                        pkgAnimation.setShowAnimation(adImageView, 500);
                        try {
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    LocalLog.d(TAG, "");
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            currentTimes--;
                                            String format = String.format(getString(R.string.cancel_ad), currentTimes);
                                            cancelAd.setText(format);
                                            if (currentTimes == 0) {
                                                timer.cancel();
                                                enterMainActivityOrLogin();
                                            }
                                        }
                                    });
                                }
                            }, 1000, 1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        cancelAd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "跳过广告!");
                                timer.cancel();
                                enterMainActivityOrLogin();
                            }
                        });
                    }
                }
            }
        }, AUTO_HIDE_DELAY_MILLIS);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏 @底部    这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    private void initViewpager() {
        vp_first_income.setVisibility(View.VISIBLE);
        final int[] guideImages = {R.mipmap.ic_guide1, R.mipmap.ic_guide2, R.mipmap.ic_guide3};

        final List<View> list = new ArrayList<>();
        for (int i = 0; i < guideImages.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.view_single_image, null);
            ImageView iv_page = (ImageView) view.findViewById(R.id.iv_page);
            iv_page.setImageResource(guideImages[i]);
            if (i == guideImages.length - 1) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterMainActivityOrLogin();
                    }
                });
            }
            list.add(view);
        }

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return guideImages.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = list.get(position);
                container.addView(view);
                return view;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }
        };
        vp_first_income.setAdapter(pagerAdapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        hide();

    }

    /*获取开屏幕广告图片*/
    private void loadBanner() {
        final String bannerUrl = NetApi.urlAd + "?position=cover";
        Presenter.getInstance(this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    ArrayList<AdObject> adList = new ArrayList<>();
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
                    LocalLog.d(TAG, "启动页广告只有一张");
                    String currentAdUrl = FlagPreference.getAdUrl(SplashActivity.this);
                    final String targetUrl = adList.get(0).getTarget_url();
                    if (adList.size() > 0) {
                        if (!TextUtils.isEmpty(adList.get(0).getImg_url()) && !adList.get(0).equals(currentAdUrl)) {
                            LocalLog.d(TAG, "更换广告图元");
                            Presenter.getInstance(SplashActivity.this).downLoadAdImage(adList.get(0).getImg_url(), adImageView, R.drawable.bitmap_null, R.drawable.bitmap_null);
                        } else {
                            Presenter.getInstance(SplashActivity.this).getAdImage(adImageView, currentAdUrl);
                            adImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(!TextUtils.isEmpty(targetUrl)) startActivity(new Intent(SplashActivity.this, SingleWebViewActivity.class).putExtra("url",targetUrl));
                                }
                            });
                        }
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "不需要显示上一个广告!");
                cancelAd.setVisibility(View.GONE);
                timer.cancel();
                enterMainActivityOrLogin();
                /*String currentAdUrl = FlagPreference.getAdUrl(SplashActivity.this);
                if (!TextUtils.isEmpty(currentAdUrl)) {
                    Presenter.getInstance(SplashActivity.this).getAdImage(adImageView, currentAdUrl);
                }*/
            }
        });
    }

    private void enterMainActivityOrLogin() {
        if (loginCheck()) {
            String chatToken = (String) SharedPreferencesUtil.get(Constants.SP_RONGYUN_CHAT_TOKEN, "");
//        if (!TextUtils.isEmpty(chatToken)) {
            RongYunChatUtils.getInstance().connect(PaoBuApplication.getApplication(), chatToken, null);
//        }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }

    private void hide() {
        // Hide UI first
        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }*/
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @Override
    public void onMessage(Intent intent) {
        // TODO: 推送处理动作
        if (intent != null) {
            super.onMessage(intent);
            String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            LocalLog.d(TAG, body);
        }
    }

    private boolean isFirstOpen() {
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {

                finish();
                return true;
            }
        }
        return false;
    }


    private boolean loginCheck() {
        return Presenter.getInstance(this).getLogFlg();
    }
}
