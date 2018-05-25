package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserIncomInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CrashActivity;
import com.paobuqianjin.pbq.step.view.activity.InoutcomDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.WalletRedPkgIncomeAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.paobuqianjin.pbq.step.utils.Utils.PAGE_SIZE_DEFAULT;

/**
 * Created by pbq on 2018/1/15.
 */

public class MyWalletFragment extends BaseBarStyleTextViewFragment implements UserIncomInterface {
    private final static String TAG = MyWalletFragment.class.getSimpleName();

    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.all_income)
    TextView allIncome;
    @Bind(R.id.income_des)
    TextView incomeDes;
    @Bind(R.id.income_money)
    TextView incomeMoney;
    @Bind(R.id.month_income_des)
    TextView monthIncomeDes;
    @Bind(R.id.month_income_num)
    TextView monthIncomeNum;
    @Bind(R.id.month_span)
    RelativeLayout monthSpan;
    @Bind(R.id.yesterday_income_des)
    TextView yesterdayIncomeDes;
    @Bind(R.id.yesterday_income_num)
    TextView yesterdayIncomeNum;
    @Bind(R.id.yesterday_span)
    RelativeLayout yesterdaySpan;
    @Bind(R.id.total_income_des)
    TextView totalIncomeDes;
    @Bind(R.id.total_income_num)
    TextView totalIncomeNum;
    @Bind(R.id.total_span)
    RelativeLayout totalSpan;
    @Bind(R.id.income_outline)
    LinearLayout incomeOutline;
    @Bind(R.id.income_rel)
    RelativeLayout incomeRel;
    @Bind(R.id.charge_bar)
    Button chargeBar;
    @Bind(R.id.crash)
    Button crash;
    @Bind(R.id.income_container)
    RelativeLayout incomeContainer;
    /*    @Bind(R.id.scroll_view_income)
        BounceScrollView scrollViewIncome;*/
    @Bind(R.id.wallet_refresh)
    SwipeRefreshLayout walletRefresh;
    private int mIndex;//当前收入页面索引
    private Fragment[] fragments;
    private int mCurrentIndex = 0;
    private ToDayIncomeFragment yesterDayIncomeFragment = new ToDayIncomeFragment();
    private MonthIncomeFragment monthIncomeFragment = new MonthIncomeFragment();
    private AllIncomeFragment allIncomeFragment = new AllIncomeFragment();
    WalletRedPkgIncomeAdapter yesterDayAdapter, monthAdapter, allAdapter;
    ArrayList<IncomeResponse.DataBeanX.DataBean> yesterData = new ArrayList<>();
    ArrayList<IncomeResponse.DataBeanX.DataBean> monthData = new ArrayList<>();
    ArrayList<AllIncomeResponse.DataBeanX.DataBean> allData = new ArrayList<>();
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_RECHARGE = "com.paobuqian.pbq.step.PAY_RECHARGE.ACTION";
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private int pageIndexYD = 1, pageIndexMonth = 1, pageIndexAll = 1;
    private int pageYDCount = 0, pageMonthCount = 0, pageAllCount = 0;
    private final static int REQUEST_CRASH = 231;
    private float totalMoney = 0.0f;
    private final int REQUEST_RECHARGE = 223;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_wallet_fg;
    }

    @Override
    protected String title() {
        return "我的钱包";
    }

    @Override
    public Object right() {
        return "明细";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
        Presenter.getInstance(context).getUserPackageMoney();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {
            getActivity().onBackPressed();
        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "查看明细");
            startActivity(InoutcomDetailActivity.class, null);
        }
    };

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        setToolBarListener(toolBarListener);
        crash = (Button) viewRoot.findViewById(R.id.crash);
        fragments = new Fragment[]{yesterDayIncomeFragment, monthIncomeFragment, allIncomeFragment};
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.income_container, yesterDayIncomeFragment)
                .add(R.id.income_container, monthIncomeFragment)
                .hide(monthIncomeFragment)
                .add(R.id.income_container, allIncomeFragment)
                .hide(allIncomeFragment)
                .show(yesterDayIncomeFragment)
                .commit();

        walletRefresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.wallet_refresh);
        yesterDayAdapter = new WalletRedPkgIncomeAdapter(getContext(), null);
        monthAdapter = new WalletRedPkgIncomeAdapter(getContext(), null);
        allAdapter = new WalletRedPkgIncomeAdapter(getContext(), null);
        yesterDayIncomeFragment.setAdapter(yesterDayAdapter);
        yesterDayIncomeFragment.listen(mLoadMoreListener);
        allIncomeFragment.setAdapter(allAdapter);
        allIncomeFragment.listen(mLoadMoreListener);
        monthIncomeFragment.setAdapter(monthAdapter);
        monthIncomeFragment.listen(mLoadMoreListener);

        loadYesterData(yesterData);
        loadMonthData(monthData);
        loadAllData(allData);
        walletRefresh.setOnRefreshListener(mRefreshListener);
        Presenter.getInstance(getContext()).getIncome("today", pageIndexYD, PAGE_SIZE_DEFAULT);
        Presenter.getInstance(getContext()).getIncome("month", pageIndexMonth, PAGE_SIZE_DEFAULT);
        Presenter.getInstance(getContext()).getIncome("all", pageIndexAll, PAGE_SIZE_DEFAULT);
        mIndex = 0;
        mCurrentIndex = 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.charge_bar, R.id.crash, R.id.yesterday_span, R.id.month_span, R.id.total_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.charge_bar:
                LocalLog.d(TAG, "向钱包充值");
                Bundle bundle = new Bundle();
                bundle.putString(PAY_FOR_STYLE, "user");
                intent.putExtra(getActivity().getPackageName(), bundle);
                intent.setClass(getContext(), PaoBuPayActivity.class);
                intent.setAction(PAY_RECHARGE);
                startActivityForResult(intent, REQUEST_RECHARGE);
                break;
            case R.id.crash:
                LocalLog.d(TAG, "提现");
                intent.setAction(CRASH_ACTION);
                intent.putExtra("total", totalMoney);
                intent.setClass(getContext(), CrashActivity.class);
                startActivityForResult(intent, REQUEST_CRASH);
                break;
            case R.id.yesterday_span:
                LocalLog.d(TAG, "查看当前收益");
                mIndex = 0;
                onTabIndex(mIndex);
                break;
            case R.id.month_span:
                LocalLog.d(TAG, "查看月收入");
                mIndex = 1;
                onTabIndex(mIndex);
                break;
            case R.id.total_span:
                LocalLog.d(TAG, "总收入");
                mIndex = 2;
                onTabIndex(mIndex);
                break;
            default:
                break;
        }
    }

    /*
   *@function onTabIndex() 切换到不同Fragment 界面
   *@param
   *@return
   */
    private void onTabIndex(int fragmentIndex) {
        LocalLog.d(TAG, "onTabIndex() enter mIndex " + fragmentIndex);
        if (mCurrentIndex != fragmentIndex) {
            FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[mCurrentIndex]);
            if (!fragments[fragmentIndex].isAdded()) {
                trx.add(R.id.fragment_container, fragments[fragmentIndex]);
            }
            trx.show(fragments[fragmentIndex]).commit();
        }
        mCurrentIndex = fragmentIndex;
    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            LocalLog.d(TAG, "刷新当前页面!");
            walletRefresh.setRefreshing(false);
            if (mCurrentIndex == 0) {
                //loadYesterData(yesterData);
            } else if (mCurrentIndex == 1) {
                //loadMonthData(monthData);
            } else if (mCurrentIndex == 2) {
                //loadAllData(allData);
            }
        }
    };


    /**
     * 第一次加载数据。
     */
    private void loadYesterData(ArrayList<IncomeResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadYesterData() enter");
        yesterDayAdapter.notifyDataSetChanged(dataBeans);

        walletRefresh.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            yesterDayIncomeFragment.loadMoreFinish(true, true);
        } else {
            yesterDayIncomeFragment.loadMoreFinish(false, true);
        }
    }

    private void loadYesterDayMore(ArrayList<IncomeResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadYesterDayMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        yesterData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        yesterDayAdapter.notifyItemRangeInserted(yesterData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        yesterDayIncomeFragment.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private void loadMonthData(ArrayList<IncomeResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadMonthData() enter");
        monthAdapter.notifyDataSetChanged(dataBeans);

        walletRefresh.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            monthIncomeFragment.loadMoreFinish(true, true);
        } else {
            monthIncomeFragment.loadMoreFinish(false, true);
        }
    }

    private void loadMonthMore(ArrayList<IncomeResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadMonthMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        monthData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        monthAdapter.notifyItemRangeInserted(monthData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        monthIncomeFragment.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private void loadAllData(ArrayList<AllIncomeResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadAllData() enter");
        allAdapter.notifyDataSetChanged(dataBeans);

        walletRefresh.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            allIncomeFragment.loadMoreFinish(true, true);
        } else {
            allIncomeFragment.loadMoreFinish(false, true);
        }
    }

    private void loadAllMore(ArrayList<AllIncomeResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadAllMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        allData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        allAdapter.notifyItemRangeInserted(allData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        allIncomeFragment.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            LocalLog.d(TAG, "加载更多!");
            if (mCurrentIndex == 0) {
                if (pageYDCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexYD > pageYDCount) {
                        Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                        yesterDayIncomeFragment.loadMoreFinish(false, true);
                        return;
                    }
                }

                Presenter.getInstance(getContext()).getIncome("today", pageIndexYD, PAGE_SIZE_DEFAULT);
            } else if (mCurrentIndex == 1) {
                if (pageMonthCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexMonth > pageMonthCount) {
                        Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                        monthIncomeFragment.loadMoreFinish(false, true);
                        return;
                    }
                }

                Presenter.getInstance(getContext()).getIncome("month", pageIndexMonth, PAGE_SIZE_DEFAULT);

            } else if (mCurrentIndex == 2) {
                if (pageAllCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexAll > pageAllCount) {
                        Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                        allIncomeFragment.loadMoreFinish(false, true);
                        return;
                    }
                }
                Presenter.getInstance(getContext()).getIncome("all", pageIndexAll, PAGE_SIZE_DEFAULT);
            }
        }
    };

    @Override
    public void responseAll(AllIncomeResponse allIncomeResponse) {
        LocalLog.d(TAG, " 所有收益 responseAll() enter" + allIncomeResponse.toString());
        if (allIncomeResponse.getError() == 0) {
            if (allIncomeResponse.getData() != null) {
                if (totalIncomeNum == null) {
                    return;
                }
                totalIncomeNum.setText(String.valueOf(allIncomeResponse.getData().getTotal_amount()));
            }
            pageAllCount = allIncomeResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndexAll = " + pageIndexAll + " ,pageAllCount = " + pageAllCount);
            loadAllMore((ArrayList<AllIncomeResponse.DataBeanX.DataBean>) allIncomeResponse.getData().getData());
            if (pageIndexAll == 1) {
                allIncomeFragment.scrollTop();
            }
            pageIndexAll++;
        } else if (allIncomeResponse.getError() == 1) {
            if (pageIndexAll == 1) {
                allIncomeFragment.nullDataVisibleSet(View.VISIBLE);
            }
        } else if (allIncomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void responseMonth(IncomeResponse incomeResponse) {
        LocalLog.d(TAG, " 月收益 responseMonth() enter" + incomeResponse.toString());
        if (incomeResponse.getError() == 0) {
            monthIncomeFragment.nullDataVisibleSet(View.GONE);
            if (incomeResponse.getData() != null) {
                if (monthIncomeNum == null) {
                    return;
                }
                monthIncomeNum.setText(String.valueOf(incomeResponse.getData().getTotal_amount()));
            }
            pageMonthCount = incomeResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndexAll = " + pageIndexMonth + " ,pageAllCount = " + pageMonthCount);
            loadMonthMore((ArrayList<IncomeResponse.DataBeanX.DataBean>) incomeResponse.getData().getData());
            if (pageIndexMonth == 1) {
                monthIncomeFragment.scrollTop();
            }
            pageIndexMonth++;
        } else if (incomeResponse.getError() == 1) {
            if (pageIndexMonth == 1) {
                monthIncomeFragment.nullDataVisibleSet(View.VISIBLE);
            }
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }

    }

    @Override
    public void responseToday(IncomeResponse incomeResponse) {
        LocalLog.d(TAG, "今天收益 responseToday() enter" + incomeResponse.toString());
        if (incomeResponse.getError() == 0) {
            yesterDayIncomeFragment.nullDataVisibleSet(View.GONE);
            if (incomeDes == null) {
                return;
            }
            incomeDes.setText(String.valueOf(incomeResponse.getData().getTotal_amount()));
            yesterdayIncomeNum.setText(String.valueOf(incomeResponse.getData().getTotal_amount()));
            pageYDCount = incomeResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndexAll = " + pageIndexYD + " ,pageAllCount = " + pageYDCount);
            loadYesterDayMore((ArrayList<IncomeResponse.DataBeanX.DataBean>) incomeResponse.getData().getData());
            if (pageIndexYD == 1) {
                yesterDayIncomeFragment.scrollTop();
            }
            pageIndexYD++;
        } else if (incomeResponse.getError() == 1) {
            if (pageIndexYD == 1) {
                yesterDayIncomeFragment.nullDataVisibleSet(View.VISIBLE);
            }
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }

    }

/* 改为显示今日收益
    @Override
    public void responseYesterday(IncomeResponse yesterdayIncomeResponse) {
        LocalLog.d(TAG, "昨日收益 responseYesterday() enter" + yesterdayIncomeResponse.toString());
        if (yesterdayIncomeResponse.getError() == 0) {
            if (yesterdayIncomeResponse.getData() != null) {
                yesterdayIncomeNum.setText(String.valueOf(yesterdayIncomeResponse.getData().getTotal_amount()));
            }

            pageYDCount = yesterdayIncomeResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndexAll = " + pageIndexYD + " ,pageAllCount = " + pageYDCount);
            loadYesterDayMore((ArrayList<IncomeResponse.DataBeanX.DataBean>) yesterdayIncomeResponse.getData().getData());
            if (pageIndexYD == 1) {
                yesterDayIncomeFragment.scrollTop();
            }
            pageIndexYD++;
        } else if (yesterdayIncomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }*/

    @Override
    public void response(UserInfoResponse userInfoResponse) {

        if (userInfoResponse.getError() == 0) {
            if (incomeMoney == null) {
                return;
            }
            incomeMoney.setText("总金额:" + userInfoResponse.getData().getBalance());
            totalMoney = Float.parseFloat(userInfoResponse.getData().getBalance());
        } else if (userInfoResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_RECHARGE || requestCode == REQUEST_CRASH) {
                LocalLog.d(TAG, "充值成功或者提现成功");
                Presenter.getInstance(getContext()).getUserPackageMoney();
            }
        }
    }
}