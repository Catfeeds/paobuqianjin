package com.paobuqianjin.pbq.step.view.activity.shop;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponCateListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TaoHomeTopAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/4.
 */

public class TianMaoActivity extends BaseBarActivity implements SwipeMenuRecyclerView.LoadMoreListener {
    private final static String TAG = TianMaoActivity.class.getSimpleName();
    @Bind(R.id.page_recycler)
    SwipeMenuRecyclerView pageRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    View viewCate;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.bg_img)
    ImageView bgImg;
    private int scrollY = 0;
    RelativeLayout barNull;
    LinearLayout barItem;
    private int selectPosition = 0;
    TaoHomeTopAdapter taoHomeTopAdapter;

    @Override
    protected String title() {
        return "天猫";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("天猫");
        setContentView(R.layout.tao_tian_activity_layout);
        ButterKnife.bind(this);
        barNull = (RelativeLayout) findViewById(R.id.bar_null);
        bgImg = (ImageView) barNull.findViewById(R.id.bg_img);
        bgImg.setVisibility(View.VISIBLE);
        barItem = (LinearLayout) findViewById(R.id.item_shop);
        barItem.setVisibility(View.GONE);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pageRecycler.setLayoutManager(linearLayoutManager);
        pageRecycler.setHasFixedSize(true);
        pageRecycler.setNestedScrollingEnabled(false);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        pageRecycler.addFooterView(loadMoreView); // 添加为Footer。
        pageRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        pageRecycler.setLoadMoreListener(this);
        barTitle = barNull.findViewById(R.id.bar_title);
        taoHomeTopAdapter = new TaoHomeTopAdapter(this, getSupportFragmentManager());
        taoHomeTopAdapter.setLoadMoreInterface(loadDataInterface);
        pageRecycler.setAdapter(taoHomeTopAdapter);
        bgImg.setVisibility(View.GONE);
        pageRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    scrollY += dy;
                    LocalLog.d(TAG, "dy = " + dy + ",scrollY = " + scrollY);
                    viewCate = linearLayoutManager.findViewByPosition(1);
                    if (viewCate != null) {
                        int[] location = new int[2];
                        LocalLog.d(TAG, "找到view  1");
                        viewCate.getLocationOnScreen(location);

                        LocalLog.d(TAG, "location[0] =" + location[0] + ",location[1]= " + location[1]);
                        if (location[1] <= Utils.dp2px(getApplicationContext(), 64)
                                && viewCate.getVisibility() == View.VISIBLE && dy > 0) {
                            bgImg.setVisibility(View.VISIBLE);
                            viewCate.setVisibility(View.GONE);
                            barItem.setVisibility(View.VISIBLE);
                            selectPosition = taoHomeTopAdapter.getSelectTab();
                            tablayout.getTabAt(selectPosition).select();
                            LocalLog.d(TAG, "selectPosition =  " + selectPosition);
                        } else if (location[1] > Utils.dp2px(getApplicationContext(), 64)
                                && viewCate.getVisibility() == View.GONE && dy < 0) {
                            barItem.setVisibility(View.GONE);
                            viewCate.setVisibility(View.VISIBLE);
                            bgImg.setVisibility(View.GONE);
                            TabLayout tabLayout = (TabLayout) viewCate.findViewById(R.id.tablayout);
                            if (tabLayout != null) {
                                tabLayout.getTabAt(selectPosition).select();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        getCouponCateList();
    }


    @Override
    public void onLoadMore() {
        LocalLog.d(TAG, "onLoadMore() enter");
        if (taoHomeTopAdapter != null) {
            taoHomeTopAdapter.loadMore();
        }
    }

    public interface LoadDataInterface {
        public void canLoadMore();

        public void canNoLoadMore();
    }

    private LoadDataInterface loadDataInterface = new LoadDataInterface() {
        @Override
        public void canLoadMore() {
            pageRecycler.loadMoreFinish(false, true);
        }

        @Override
        public void canNoLoadMore() {
            pageRecycler.loadMoreFinish(false, false);
        }
    };

    @TargetApi(23)
    private void getCouponCateList() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlCouponCateStyleS, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    CouponCateListResponse couponCateListResponse = new Gson().fromJson(s, CouponCateListResponse.class);
                    if (couponCateListResponse.getData() != null) {
                        int size = couponCateListResponse.getData().size();

                        if (size == 0) {
                            return;
                        }
                        final List<CouponCateListResponse.DataBean> strings = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            strings.add(couponCateListResponse.getData().get(i));
                        }
                        ViewPager viewPager = new ViewPager(TianMaoActivity.this);
                        viewPager.setAdapter(new FragmentPagerAdapter(TianMaoActivity.this.getSupportFragmentManager()) {
                            @Override
                            public Fragment getItem(int position) {
                                return null;
                            }

                            @Override
                            public int getCount() {
                                return strings.size();
                            }

                            @Override
                            public CharSequence getPageTitle(int position) {
                                try {
                                    LocalLog.d(TAG, "postion =" + position + ",name = " + strings.get(position).getCate_name());
                                    return (String) strings.get(position).getCate_name();
                                } catch (Exception e) {
                                    return null;
                                }
                            }
                        });
                        tablayout.setupWithViewPager(viewPager);
                        for (int i = 0; i < tablayout.getTabCount(); i++) {
                            TabLayout.Tab tab = tablayout.getTabAt(i);
                            if (tab != null) {
                                TextView textView = new TextView(TianMaoActivity.this);
                                textView.setMinWidth(30);
                                textView.setTypeface(Typeface.DEFAULT_BOLD);
                                textView.setText(strings.get(i).getCate_name());
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(14);
                                tab.setCustomView(textView);
                                if (tab.getCustomView() != null) {
                                    View tabView = (View) tab.getCustomView().getParent();
                                    tabView.setTag(i);
                                }
                            }
                        }
                        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                try {
                                    LocalLog.d(TAG, "postion =" + tab.getPosition());
                                    selectPosition = tab.getPosition();
                                    taoHomeTopAdapter.setSelectTab(selectPosition);

                                    //更新相应数据
                                    //
                                    View customView = tab.getCustomView();
                                    try {
                                        if (customView != null && customView instanceof TextView) {
                                            ((TextView) customView).setTextColor(getColor(R.color.color_FF3E7E));
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
                                        ((TextView) customView).setTextColor(getColor(R.color.color_646464));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(TianMaoActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(TianMaoActivity.this, "开小差了，请稍后再试");
                }
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
            LocalLog.d(TAG, "查看更多!");
        }
    };
}
