package com.paobuqianjin.pbq.step.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.model.services.local.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.FriendCircleFragment;
import com.paobuqianjin.pbq.step.view.fragment.home.HomePageFragment;
import com.paobuqianjin.pbq.step.view.fragment.honor.HonorFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.OwnerFragment;
import com.paobuqianjin.pbq.step.view.fragment.task.TaskFragment;


public class MainActivity extends BaseActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private boolean stepServiceBind = false;
    //Fragment页面索引
    private HomePageFragment mHomePageFragment;
    private HonorFragment mHonorFragment;
    private FriendCircleFragment mFriendCircleFragment;
    public TaskFragment taskFragment;
    private OwnerFragment mOwnerFragment;
    private Fragment[] mFragments;
    private int mIndex = 0;
    private TextView[] mTabSelect;
    private TextView mBtn_home;
    private TextView mBtn_friend;
    private TextView mBtn_task;
    private TextView mBtn_honor;
    private TextView mBtn_owner;
    private int mCurrentIndex = 0;
    private int[][] icon = new int[][]{{R.drawable.home_n, R.drawable.home_s}, {R.drawable.circle_n, R.drawable.circle_s},
            {R.drawable.task_n, R.drawable.task_s},
            {R.drawable.list_n, R.drawable.list_s}, {R.drawable.me_n, R.drawable.me_s}};
    private final static String ACTION_SCAN_CIRCLE_ID = "com.paobuqianjin.pbq.step.ACTION_SCAN_CIRCLE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float scale = getResources().getDisplayMetrics().density;
        LocalLog.d(TAG, "scale = " + scale);
        setContentView(R.layout.activity_main);
        if (!loginCheck()) {
            LocalLog.d(TAG, "启动登入注册界面！");
            startActivity(LoginActivity.class, null, false);
            finish();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        initTab();
    }


    private void initTab() {
        mBtn_home = (TextView) findViewById(R.id.btn_home_page);
        mBtn_friend = (TextView) findViewById(R.id.btn_friend_circle);
        mBtn_task = (TextView) findViewById(R.id.btn_task);
        mBtn_honor = (TextView) findViewById(R.id.btn_honor);
        mBtn_owner = (TextView) findViewById(R.id.btn_owner);
        mTabSelect = new TextView[5];
        mTabSelect[0] = mBtn_home;
        mTabSelect[1] = mBtn_friend;
        mTabSelect[2] = mBtn_task;
        mTabSelect[3] = mBtn_honor;
        mTabSelect[4] = mBtn_owner;
        mTabSelect[0].setSelected(true);
        initTextViewIcon();
        initFragment();
    }

    //更改图片大小
    private void initTextViewIcon() {
        Drawable home = getResources().getDrawable(R.drawable.home_s);
        Drawable circle = getResources().getDrawable(R.drawable.circle_n);
        Drawable task = getResources().getDrawable(R.drawable.task_n);
        Drawable honor = getResources().getDrawable(R.drawable.list_n);
        Drawable me = getResources().getDrawable(R.drawable.me_n);

        home.setBounds(0, 0, 54, 54);
        mBtn_home.setCompoundDrawables(null, home, null, null);
        circle.setBounds(0, 0, 54, 54);
        mBtn_friend.setCompoundDrawables(null, circle, null, null);
        task.setBounds(0, 0, 54, 54);
        mBtn_task.setCompoundDrawables(null, task, null, null);
        honor.setBounds(0, 0, 54, 54);
        mBtn_honor.setCompoundDrawables(null, honor, null, null);
        me.setBounds(0, 0, 54, 54);
        mBtn_owner.setCompoundDrawables(null, me, null, null);

    }

    private void initFragment() {
        mHomePageFragment = new HomePageFragment();
        mFriendCircleFragment = new FriendCircleFragment();
        taskFragment = new TaskFragment();
        mHonorFragment = new HonorFragment();
        mOwnerFragment = new OwnerFragment();
        mFragments = new Fragment[]{mHomePageFragment, mFriendCircleFragment, taskFragment, mHonorFragment, mOwnerFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment).
                add(R.id.fragment_container, mFriendCircleFragment)
                .add(R.id.fragment_container, taskFragment)
                .add(R.id.fragment_container, mHonorFragment)
                .add(R.id.fragment_container, mOwnerFragment)
                .hide(mFriendCircleFragment)
                .hide(taskFragment)
                .hide(mHonorFragment)
                .hide(mOwnerFragment)
                .show(mHomePageFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Presenter.getInstance(this).bindService(StepService.START_STEP_ACTION, StepService.class);
        stepServiceBind = true;
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
                case R.id.btn_task:
                    mIndex = 2;
                    break;
                case R.id.btn_honor:
                    mIndex = 3;
                    break;
                case R.id.btn_owner:
                    mIndex = 4;
                    break;
                default:
                    break;
            }
            onTabIndex(mIndex);
        }
    }

    public void tabToTask() {
        LocalLog.d(TAG, "tabToTask() enter");
        mIndex = 2;
        onTabIndex(mIndex);
    }

    private void updateDrawableFalse(int index) {
        Drawable top = getResources().getDrawable(icon[index][0]);
        top.setBounds(0, 0, 54, 54);
        mTabSelect[index].setCompoundDrawables(null, top, null, null);
    }

    private void upDateDrawableTrue(int index) {
        Drawable top = getResources().getDrawable(icon[index][1]);
        top.setBounds(0, 0, 54, 54);
        mTabSelect[index].setCompoundDrawables(null, top, null, null);
    }

    /*
    *@function onTabIndex() 切换到不同Fragment 界面
    *@param
    *@return
    */
    private void onTabIndex(int fragmentIndex) {
        LocalLog.d(TAG, "onTabIndex() enter mIndex " + fragmentIndex);
        mTabSelect[mCurrentIndex].setSelected(false);
        updateDrawableFalse(mCurrentIndex);
        mTabSelect[fragmentIndex].setSelected(true);
        upDateDrawableTrue(fragmentIndex);
        if (mCurrentIndex != fragmentIndex) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(mFragments[mCurrentIndex]);
            if (!mFragments[fragmentIndex].isAdded()) {
                trx.add(R.id.fragment_container, mFragments[fragmentIndex]);
            }
            trx.show(mFragments[fragmentIndex]).commit();
        }
        mCurrentIndex = fragmentIndex;
    }

    private boolean loginCheck() {
        return Presenter.getInstance(this).getLogFlg();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (stepServiceBind) {
            Presenter.getInstance(this).unbindStepService();
        }
    }


    @Override
// 通过 onActivityResult的方法获取 扫描回来的 值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "内容为空", Toast.LENGTH_LONG).show();
            } else {
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                Toast.makeText(this, "扫描成功:" + ScanResult, Toast.LENGTH_LONG).show();
                LocalLog.d(TAG, ScanResult);
                if (ScanResult.startsWith("userid:")) {
                    LocalLog.d(TAG, "扫描个人");
                    String userid = ScanResult.substring("userid:".length(), ScanResult.length());
                    LocalLog.d(TAG, "userid = " + userid);
                    //TODO ACTION_SCAN_USERID
                } else if (ScanResult.startsWith("circleid:")) {
                    LocalLog.d(TAG, "扫描圈子");
                    String circleid = ScanResult.substring("circleid:".length(), ScanResult.length());
                    LocalLog.d(TAG, "circleid = " + circleid);
                    //TODO ACTION_SCAND_CIRCLE_ID

                    Intent intent = new Intent();
                    intent.setClass(this, CirCleDetailActivity.class);
                    intent.putExtra(getPackageName() + "circleid", Integer.parseInt(circleid));
                    intent.setAction(ACTION_SCAN_CIRCLE_ID);
                    startActivity(intent);
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
