package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private ViewPager vp_first_income;
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

        mContentView = findViewById(R.id.fullscreen_content);
        vp_first_income = (ViewPager) findViewById(R.id.vp_first_income);

        mHideHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstEnter = (boolean) SharedPreferencesUtil.get(Constants.SP_IS_FIRST_ENTER, true);
                if (isFirstEnter) {//首次进入
                    initViewpager();
                    SharedPreferencesUtil.put(Constants.SP_IS_FIRST_ENTER, false);
                }else{
                    enterMainActivity();
                }
            }
        }, AUTO_HIDE_DELAY_MILLIS);
    }

    private void initViewpager() {
        vp_first_income.setVisibility(View.VISIBLE);
        final int[] guideImages = {R.mipmap.ic_guide1, R.mipmap.ic_guide2, R.mipmap.ic_guide3};

        final List<View> list = new ArrayList<>();
        for (int i = 0; i < guideImages.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.view_single_image, null);
            view.setBackgroundResource(guideImages[i]);
            if (i == guideImages.length - 1) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterMainActivity();
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

    private void enterMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mHideHandler.postDelayed(mHidePart2Runnable,UI_ANIMATION_DELAY);
    }

}
