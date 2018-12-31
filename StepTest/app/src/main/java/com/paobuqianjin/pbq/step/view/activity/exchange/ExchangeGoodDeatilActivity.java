package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExGoodDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.exchange.ExgoodDetailFragment;
import com.paobuqianjin.pbq.step.view.fragment.exchange.SaleManFragment;
import com.umeng.commonsdk.debug.I;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imlib.model.Conversation;

/**
 * Created by pbq on 2018/12/26.
 */

public class ExchangeGoodDeatilActivity extends BaseActivity {
    private final static String TAG = ExchangeGoodDeatilActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.head_ico)
    CircleImageView headIco;
    @Bind(R.id.dear_name)
    TextView dearName;
    @Bind(R.id.time_arrive)
    TextView timeArrive;
    @Bind(R.id.step_dollor)
    TextView stepDollor;
    @Bind(R.id.src_price)
    TextView srcPrice;
    @Bind(R.id.tri_free)
    TextView triFree;
    @Bind(R.id.good_name)
    TextView goodName;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.fragment_container)
    LinearLayout fragmentContainer;
    @Bind(R.id.want_span)
    LinearLayout wantSpan;
    @Bind(R.id.talk_to)
    Button talkTo;
    @Bind(R.id.buy_buttone)
    Button buyButtone;
    @Bind(R.id.attion)
    TextView attion;
    private String[] titles = {"商品详情", "关于商家"};
    private Fragment[] fragments;
    private int mCurrentIndex = 0;
    private ExgoodDetailFragment exgoodDetailFragment;
    private SaleManFragment saleManFragment;
    private int userId;
    private String talkName;
    private ExGoodDetailResponse.DataBean dataBean;
    private final static int REQUEST_CONFIRM = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duihuan_detail_activity_layout);
        ButterKnife.bind(this);
        barTvRight.setImageResource(R.drawable.share_icon);
        SpannableString string = new SpannableString("注:个人闲置物品，不喜勿拍，拍下需自付邮费。");
        string.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.color_232433)), 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        attion.setText(string);
        Intent intent = getIntent();
        if (intent != null) {
            String ex_id = intent.getStringExtra("ex_id");
            if (!TextUtils.isEmpty(ex_id)) {
                getGoodDetail(ex_id);
            }
        }
    }

    private void intTabLayout(String desc, List<String> img, ExGoodDetailResponse.DataBean.UserInfoBean userInfo) {
        exgoodDetailFragment = new ExgoodDetailFragment();
        exgoodDetailFragment.setContent(desc, img);
        saleManFragment = new SaleManFragment();
        saleManFragment.setUserInfo(userInfo);
        fragments = new Fragment[]{exgoodDetailFragment, saleManFragment};
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                try {
                    return titles[position];
                } catch (Exception e) {
                    return null;
                }
            }
        });
        tablayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                TextView textView = new TextView(this);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setWidth(300);
                textView.setText(titles[i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(14);
                tab.setCustomView(textView);
                if (i == 0) {
                    textView.setTextColor(ContextCompat.getColor(this, R.color.color_161727));
                }
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                }
            }
        }

        tablayout.getTabAt(0).select();
        mCurrentIndex = 0;
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    LocalLog.d(TAG, "postion =" + tab.getPosition());
                    View customView = tab.getCustomView();
                    onTabIndex(tab.getPosition());
                    try {
                        if (customView != null && customView instanceof TextView) {
                            ((TextView) customView).setTextColor(ContextCompat.getColor(ExchangeGoodDeatilActivity.this, R.color.color_232433));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                try {
                    if (customView != null && customView instanceof TextView) {
                        ((TextView) customView).setTextColor(ContextCompat.getColor(ExchangeGoodDeatilActivity.this, R.color.color_646464));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, exgoodDetailFragment)
                .add(R.id.fragment_container, saleManFragment)
                .hide(saleManFragment)
                .show(exgoodDetailFragment)
                .commit();
    }

    /*
*@function onTabIndex() 切换到不同Fragment 界面
*@param
*@return
*/
    private void onTabIndex(int fragmentIndex) {
        LocalLog.d(TAG, "onTabIndex() enter mIndex " + fragmentIndex);
        if (mCurrentIndex != fragmentIndex) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[mCurrentIndex]);
            if (!fragments[fragmentIndex].isAdded()) {
                trx.add(R.id.fragment_container, fragments[fragmentIndex]);
            }
            trx.show(fragments[fragmentIndex]).commit();
        }
        mCurrentIndex = fragmentIndex;
    }


    private void getGoodDetail(final String id) {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlExDetail + id, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExGoodDetailResponse exGoodDetailResponse = new Gson().fromJson(s, ExGoodDetailResponse.class);
                    Presenter.getInstance(ExchangeGoodDeatilActivity.this).getPlaceErrorImage(headIco, exGoodDetailResponse.getData().getUser_info().getAvatar()
                            , R.drawable.default_head_ico, R.drawable.default_head_ico);
                    dearName.setText(exGoodDetailResponse.getData().getUser_info().getNickname());
                    SpannableString stepDollarSpan = new SpannableString(String.valueOf(exGoodDetailResponse.getData().getCredit()) + "步币");
                    stepDollarSpan.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(exGoodDetailResponse.getData().getCredit()).length(),
                            (String.valueOf(exGoodDetailResponse.getData().getCredit()) + "步币").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                    stepDollor.setText(stepDollarSpan);
                    talkName = exGoodDetailResponse.getData().getUser_info().getNickname();
                    userId = exGoodDetailResponse.getData().getUserid();
                    if (Float.parseFloat(exGoodDetailResponse.getData().getOld_price()) > 0.0f) {
                        srcPrice.setText("原价" + exGoodDetailResponse.getData().getOld_price() + "元");
                        srcPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                    }

                    if (Float.parseFloat(exGoodDetailResponse.getData().getExpress_price()) > 0.0f) {
                        triFree.setText("快递:" + exGoodDetailResponse.getData().getExpress_price());
                    }
                    goodName.setText(exGoodDetailResponse.getData().getName());
                    intTabLayout(exGoodDetailResponse.getData().getContent(),
                            exGoodDetailResponse.getData().getImgs_arr(), exGoodDetailResponse.getData().getUser_info());
                    dataBean = exGoodDetailResponse.getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @OnClick({R.id.want_span, R.id.talk_to, R.id.buy_buttone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.want_span:
                LocalLog.d(TAG, "");
                break;
            case R.id.talk_to:
                if (!TextUtils.isEmpty(talkName)) {
                    RongYunChatUtils.getInstance().chatTo(ExchangeGoodDeatilActivity.this
                            , Conversation.ConversationType.PRIVATE
                            , userId + ""
                            , talkName);
                }
                break;
            case R.id.buy_buttone:
                Intent intent = new Intent();
                intent.putExtra("good_detail", dataBean);
                intent.setClass(this, ConfirmOrderExActivity.class);
                startActivityForResult(intent,REQUEST_CONFIRM);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
