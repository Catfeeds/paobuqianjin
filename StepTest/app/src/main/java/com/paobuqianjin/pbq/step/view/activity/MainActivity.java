package com.paobuqianjin.pbq.step.view.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.ConversationListAdapterEx;
import com.paobuqianjin.pbq.step.customview.SponsorDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.view.DragPointView;
import com.paobuqianjin.pbq.step.view.fragment.chat.MyConversationListFragment;
import com.paobuqianjin.pbq.step.view.fragment.home.HomeFragment;
import com.paobuqianjin.pbq.step.view.fragment.home.PaoBuShopFragment;
import com.paobuqianjin.pbq.step.view.fragment.home.ShopLiveFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.OwnerFragment;
import com.tot.badges.IconBadgeNumManager;


import org.json.JSONObject;

import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


public class MainActivity extends BaseActivity implements IUnReadMessageObserver, DragPointView.OnDragListencer {
    private final static String TAG = MainActivity.class.getSimpleName();
    //Fragment页面索引
    private HomeFragment mHomePageFragment;
    private PaoBuShopFragment paoBuShopFragment;
    private OwnerFragment mOwnerFragment;
    private Fragment[] mFragments;
    private int mIndex = 0;
    private TextView[] mTabSelect;
    private TextView mBtn_home;
    private TextView mBtn_shangcheng;
    private TextView mBtn_shoplive;
    private TextView mBtn_conversion;
    private TextView mBtn_owner;
    private long firstBackClickTime = 0;
    private DragPointView mUnreadNumView;
    private int mCurrentIndex = 0;
    private int[][] icon = new int[][]{{R.drawable.home_n, R.drawable.home_s}
            , {R.drawable.shop_live_n, R.drawable.shop_live_s}
            , {R.drawable.shangcheng_n, R.drawable.shangcheng_s}
            , {R.drawable.circle_n, R.drawable.circle_s}
            , {R.drawable.me_n, R.drawable.me_s}};
    private final static String ACTION_SCAN_CIRCLE_ID = "com.paobuqianjin.pbq.step.SCAN_ACTION";
    private final static String RE_LOGIN_ACTION = "com.paobuqianjin.pbq.step.RE_LOGIN";
    private final static String GUIDE_ACTION = "com.paobuqianjin.pbq.GUIDE_ACTION";
    public static final int REQUEST_CODE = 0x0000c0de; // Only use bottom 16 bits
    private MyConversationListFragment mConversationListFragment;
    private ShopLiveFragment shopLiveFragment;
    private Conversation.ConversationType[] mConversationsTypes;
    private SponsorDialog sponsorDialog;

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
        } else {
            LocalLog.d(TAG, "已经登录！");
        }
    }

    @Override
    protected void initView() {
        initTab();
    }


    private void initTab() {
        mBtn_home = (TextView) findViewById(R.id.btn_home_page);
        mBtn_shangcheng = (TextView) findViewById(R.id.btn_shang_cheng);
        mBtn_shoplive = (TextView) findViewById(R.id.btn_shop_live);
        mBtn_conversion = (TextView) findViewById(R.id.btn_conversion);
        mBtn_owner = (TextView) findViewById(R.id.btn_owner);
        mUnreadNumView = findViewById(R.id.tv_unread);
        mTabSelect = new TextView[5];
        mTabSelect[0] = mBtn_home;
        mTabSelect[1] = mBtn_shoplive;
        mTabSelect[2] = mBtn_shangcheng;
        mTabSelect[3] = mBtn_conversion;
        mTabSelect[4] = mBtn_owner;
        mTabSelect[0].setSelected(true);
        initTextViewIcon();
        initFragment();
        mUnreadNumView.setDragListencer(this);
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, mConversationsTypes);
    }

    //更改图片大小
    private void initTextViewIcon() {
        Drawable home = getResources().getDrawable(R.drawable.home_s);
        Drawable shopLive = getResources().getDrawable(R.drawable.shop_live_n);
        Drawable shangcheng = getResources().getDrawable(R.drawable.shangcheng_n);
        Drawable conversion = getResources().getDrawable(R.drawable.circle_n);
        Drawable me = getResources().getDrawable(R.drawable.me_n);

        home.setBounds(0, 0, 54, 54);
        mBtn_home.setCompoundDrawables(null, home, null, null);
        shopLive.setBounds(0, 0, 54, 54);
        mBtn_shangcheng.setCompoundDrawables(null, shopLive, null, null);
        shangcheng.setBounds(0, 0, 54, 54);
        mBtn_shoplive.setCompoundDrawables(null, shangcheng, null, null);
        conversion.setBounds(0, 0, 54, 54);
        mBtn_conversion.setCompoundDrawables(null, conversion, null, null);
        me.setBounds(0, 0, 54, 54);
        mBtn_owner.setCompoundDrawables(null, me, null, null);

    }

    private void initFragment() {

        mHomePageFragment = new HomeFragment();
        initConversationList();
        shopLiveFragment = new ShopLiveFragment();
        paoBuShopFragment = new PaoBuShopFragment();
        mOwnerFragment = new OwnerFragment();
        mFragments = new Fragment[]{mHomePageFragment, shopLiveFragment, paoBuShopFragment, mConversationListFragment, mOwnerFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment)
                .add(R.id.fragment_container, shopLiveFragment)
                .add(R.id.fragment_container, paoBuShopFragment)
                .add(R.id.fragment_container, mConversationListFragment)
                .add(R.id.fragment_container, mOwnerFragment)
                .hide(shopLiveFragment)
                .hide(paoBuShopFragment)
                .hide(mConversationListFragment)
                .hide(mOwnerFragment)
                .show(mHomePageFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void onTabSelect(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_home_page:
                    mIndex = 0;
                    break;
                case R.id.btn_shop_live:
                    mIndex = 1;
                    break;
                case R.id.btn_shang_cheng:
                    mIndex = 2;
                    break;
                case R.id.btn_conversion:
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


    private void updateDrawableFalse(int index) {
        Drawable top = getResources().getDrawable(icon[index][0]);
        top.setBounds(0, 0, 54, 54);
        mTabSelect[index].setCompoundDrawables(null, top, null, null);
        mTabSelect[index].setTextColor(getResources().getColor(R.color.color_8a8a8a));
    }

    private void upDateDrawableTrue(int index) {
        Drawable top = getResources().getDrawable(icon[index][1]);
        top.setBounds(0, 0, 54, 54);
        mTabSelect[index].setCompoundDrawables(null, top, null, null);
        mTabSelect[index].setTextColor(getResources().getColor(R.color.color_6c71c4));
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
            trx.show(mFragments[fragmentIndex]).commitAllowingStateLoss();
        }
        mCurrentIndex = fragmentIndex;
/*        controlTransparentGuide(fragmentIndex);
        controllCircleChangeGuide(fragmentIndex);
        if (fragmentIndex == 3) {
            vipCheckTimeOut();
        }*/
    }

    private void controlTransparentGuide(int fragmentIndex) {
        String versionName = Utils.getVerName(this);
        //LocalLog.d(TAG, "fragmentIndex=" + fragmentIndex + "   versionName:" + versionName);
        if ((fragmentIndex == 1 || fragmentIndex == 3) && versionName.equals("4.0")) {
            boolean isFirstTime = (boolean) SharedPreferencesUtil.get(Constants.SP_TRANSPARENT_GUIDE + versionName + fragmentIndex, true);
            //LocalLog.d(TAG, "isFirstTime=" + isFirstTime);
            if (isFirstTime) {
                SharedPreferencesUtil.put(Constants.SP_TRANSPARENT_GUIDE + versionName + fragmentIndex, false);
                Intent intent = new Intent(this, TransparentGuideActivity.class);
                intent.putExtra("index", fragmentIndex);
                startActivity(intent);
            }
        }

    }

    private void vipCheckTimeOut() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlGvipTimeOut, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    int rday = jsonObj.getInt("rday");
                    Integer integer = (Integer) SharedPreferencesUtil.get("rday", 0);
                    LocalLog.d(TAG, "vip had days =" + rday + ",rday = " + integer.intValue());
                    if (integer.intValue() == rday) {
                        return;
                    } else {
                        SharedPreferencesUtil.put("rday", rday);
                    }
                    if (sponsorDialog == null && rday <= 3 && rday > 0) {
                        sponsorDialog = new SponsorDialog(MainActivity.this);
                        sponsorDialog.setTitle("会员到期提醒");
                        String format = getString(R.string.time_out);
                        String sFinalMember = String.format(format, String.valueOf(rday));
                        sponsorDialog.setMessage(sFinalMember);
                        sponsorDialog.setNoOnclickListener("取消", new SponsorDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                sponsorDialog.dismiss();

                            }
                        });

                        sponsorDialog.setYesOnclickListener("去开通", new SponsorDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                sponsorDialog.dismiss();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, GoldenSponsoractivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    if (sponsorDialog != null && !sponsorDialog.isShowing())
                        sponsorDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void controllCircleChangeGuide(int fragmentIndex) {
        String versionName = Utils.getVerName(this);
        if (fragmentIndex == 2 && versionName.equals("4.2")) {
            boolean isFirstTime = (boolean) SharedPreferencesUtil.get(Constants.CIRCLE_GUIDE, true);
            if (isFirstTime) {
                Intent intent = new Intent(this, CircleGuideActivity.class);
                startActivity(intent);
            }
        }
    }

    private boolean loginCheck() {
        return Presenter.getInstance(this).getLogFlg();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(this);
        clean();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LocalLog.d(TAG, "onNewIntent() enter");
        if (intent.getAction() != null) {
            if (intent.getAction().equals("login_other_phone")) {
                Intent intentLogin = new Intent(this, LoginActivity.class);
                intentLogin.setAction(RE_LOGIN_ACTION);
                startActivity(intentLogin);
                finish();
            } else if (GUIDE_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, intent.getAction());
                mIndex = 4;
                onTabIndex(4);
            }
        }

    }

    private void clean() {
        for (Fragment fragment : mFragments) {
            fragment = null;
        }
    }

    @Override
// 通过 onActivityResult的方法获取 扫描回来的 值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && resultCode == REQUEST_CODE) {
            String scanResult = data.getStringExtra(getPackageName() + "scanresult");
            if (scanResult == null) {
                Toast.makeText(this, "内容为空", Toast.LENGTH_LONG).show();
            } else {
                // ScanResult 为 获取到的字符串
                String ScanResult = scanResult;
                LocalLog.d(TAG, ScanResult);
                if (ScanResult.startsWith(NetApi.urlShareIc)) {
                    LocalLog.d(TAG, "扫描个人");
                    String userNo = ScanResult.substring(NetApi.urlShareIc.length(), ScanResult.length());
                    LocalLog.d(TAG, "userid = " + userNo);
                    try {
                        Intent intent = new Intent();
                        //TODO ACTION_SCAN_USERID
                        intent.putExtra("userno", userNo);
                        intent.setClass(this, FriendDetailActivity.class);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                } else if (ScanResult.startsWith(NetApi.urlShareCd)) {
                    LocalLog.d(TAG, "扫描圈子");
                    String circleid = ScanResult.substring(NetApi.urlShareCd.length(), ScanResult.length());
                    LocalLog.d(TAG, "circleid = " + circleid);
                    //TODO ACTION_SCAND_CIRCLE_ID
                    try {
                        Intent intent = new Intent();
                        intent.setClass(this, CirCleDetailActivity.class);
                        intent.putExtra(getPackageName() + "circleid", Integer.parseInt(circleid));
                        intent.setAction(ACTION_SCAN_CIRCLE_ID);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            MyConversationListFragment listFragment = new MyConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                    Conversation.ConversationType.GROUP,
                    Conversation.ConversationType.PUBLIC_SERVICE,
                    Conversation.ConversationType.APP_PUBLIC_SERVICE,
                    Conversation.ConversationType.SYSTEM
            };
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

    @Override
    public void onCountChanged(int count) {
        LocalLog.d(TAG, "count = " + count);
        if (count == 0) {
            mUnreadNumView.setVisibility(View.GONE);
        } else if (count > 0 && count < 100) {
            mUnreadNumView.setVisibility(View.VISIBLE);
            mUnreadNumView.setText(String.valueOf(count));
        } else {
            mUnreadNumView.setVisibility(View.VISIBLE);
            mUnreadNumView.setText(R.string.no_read_message);
        }
        try {
            new IconBadgeNumManager().setIconBadgeNum(getApplication(), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDragOut() {
        mUnreadNumView.setVisibility(View.GONE);
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    for (Conversation c : conversations) {
                        RongIM.getInstance().clearMessagesUnreadStatus(c.getConversationType(), c.getTargetId(), null);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, mConversationsTypes);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            PaoToastUtils.showShortToast(MainActivity.this, "再按一次退出程序");
            long currentTimeMillis = System.currentTimeMillis();
            if (firstBackClickTime != 0 && currentTimeMillis - firstBackClickTime < 2000) {
                System.exit(0);
                return true;
            }
            firstBackClickTime = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
