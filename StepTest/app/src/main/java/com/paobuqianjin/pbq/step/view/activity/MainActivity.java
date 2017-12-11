package com.paobuqianjin.pbq.step.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.model.services.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.FriendCircleFragment;
import com.paobuqianjin.pbq.step.view.fragment.HomePageFragment;
import com.paobuqianjin.pbq.step.view.fragment.HonorFragment;
import com.paobuqianjin.pbq.step.view.fragment.OwnerFragment;


public class MainActivity extends BaseActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    //Fragment页面索引
    private HomePageFragment mHomePageFragment;
    private HonorFragment mHonorFragment;
    private FriendCircleFragment mFriendCircleFragment;
    private OwnerFragment mOwnerFragment;
    private Fragment[] mFragments;
    private int mIndex = 0;
    private TextView[] mTabSelect;
    private TextView mBtn_home;
    private TextView mBtn_friend;
    private TextView mBtn_honor;
    private TextView mBtn_owner;
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float scale = getResources().getDisplayMetrics().density;
        LocalLog.d(TAG,"scale = " +  scale);
        setContentView(R.layout.activity_main);
        if (!loginCheck()) {
            LocalLog.d(TAG, "启动登陆注册界面！");
            //startActivity(LoginActivity.class, null, false);
        }
        bindService(this);
    }

    @Override
    protected void initView() {
        super.initView();
        initTab();
    }


    private void initTab() {
        mBtn_home = (TextView) findViewById(R.id.btn_home_page);
        mBtn_friend = (TextView) findViewById(R.id.btn_friend_circle);
        mBtn_honor = (TextView) findViewById(R.id.btn_honor);
        mBtn_owner = (TextView) findViewById(R.id.btn_owner);
        mTabSelect = new TextView[4];
        mTabSelect[0] = mBtn_home;
        mTabSelect[1] = mBtn_friend;
        mTabSelect[2] = mBtn_honor;
        mTabSelect[3] = mBtn_owner;
        mTabSelect[0].setSelected(true);
        initFragment();
    }

    private void initFragment() {
        mHomePageFragment = new HomePageFragment();
        mFriendCircleFragment = new FriendCircleFragment();
        mHonorFragment = new HonorFragment();
        mOwnerFragment = new OwnerFragment();
        mFragments = new Fragment[]{mHomePageFragment, mFriendCircleFragment, mHonorFragment, mOwnerFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment).
                add(R.id.fragment_container, mFriendCircleFragment)
                .add(R.id.fragment_container, mHonorFragment)
                .add(R.id.fragment_container, mOwnerFragment)
                .hide(mFriendCircleFragment).hide(mHonorFragment)
                .hide(mOwnerFragment)
                .show(mHomePageFragment).commit();
    }

    private void startStepService(Context context) {
        Presenter.getInstance(context).startService(StepService.START_STEP_ACTION, StepService.class);
    }

    private void bindService(Context context) {
        Presenter.getInstance(context).bindService(StepService.START_STEP_ACTION, StepService.class);
    }

    public void onTabSelect(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_home_page:
                    mIndex = 0;
                    break;
                case R.id.btn_friend_circle:
                    mIndex = 1;
                    break;
                case R.id.btn_honor:
                    mIndex = 2;
                    break;
                case R.id.btn_owner:
                    mIndex = 3;
                    break;
                default:
                    break;
            }
            onTabIndex(mIndex);
        }
    }

    /*
    *@function onTabIndex() 切换到不同Fragment 界面
    *@param
    *@return
    */
    private void onTabIndex(int fragmentIndex) {
        LocalLog.d(TAG, "onTabIndex() enter mIndex " + mIndex);
        if (mCurrentIndex != mIndex) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(mFragments[mCurrentIndex]);
            if (!mFragments[mIndex].isAdded()) {
                trx.add(R.id.fragment_container, mFragments[mIndex]);
            }
            trx.show(mFragments[mIndex]).commit();
        }
        mTabSelect[mCurrentIndex].setSelected(false);
        mTabSelect[mIndex].setSelected(true);
        mCurrentIndex = mIndex;
    }

    private boolean loginCheck() {
        return Presenter.getInstance(this).getLogFlg();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).unbindStepService();
    }
}
