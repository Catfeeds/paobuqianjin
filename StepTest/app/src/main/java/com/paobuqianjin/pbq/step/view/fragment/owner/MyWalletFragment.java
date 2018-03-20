package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserIncomInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CrashActivity;
import com.paobuqianjin.pbq.step.view.activity.InoutcomDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.MyWalletPayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    RelativeLayout incomeOutline;
    @Bind(R.id.income_rel)
    RelativeLayout incomeRel;
    @Bind(R.id.charge_bar)
    Button chargeBar;
    @Bind(R.id.crash)
    Button crash;
    private int mIndex;//当前收入页面索引
    private Fragment[] fragments;
    private int mCurrentIndex = 0;
    private YesterDayIncomeFragment yesterDayIncomeFragment = new YesterDayIncomeFragment();
    private MonthIncomeFragment monthIncomeFragment = new MonthIncomeFragment();
    private AllIncomeFragment allIncomeFragment = new AllIncomeFragment();

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
        Presenter.getInstance(context).getIncome("today", 1, 10);
        Presenter.getInstance(context).getIncome("yesterday", 1, 10);
        Presenter.getInstance(context).getIncome("month", 1, 10);
        Presenter.getInstance(context).getIncome("all", 1, 10);
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
                intent.setClass(getContext(), MyWalletPayActivity.class);
                startActivity(intent);
                break;
            case R.id.crash:
                LocalLog.d(TAG, "提现");
                intent.setClass(getContext(), CrashActivity.class);
                startActivity(intent);
                break;
            case R.id.yesterday_span:
                LocalLog.d(TAG, "查看昨日收益");
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

    @Override
    public void responseAll(AllIncomeResponse allIncomeResponse) {
        LocalLog.d(TAG, " 所有收益 responseAll() enter" + allIncomeResponse.toString());
        if (allIncomeResponse.getError() == 0) {
            String moneyFormat = getContext().getResources().getString(R.string.total_income);
            if (allIncomeResponse.getData().getPagenation() != null) {
                String moneyStr = String.format(moneyFormat, allIncomeResponse.getData().getTotal_amount());
                incomeMoney.setText(moneyStr);
            }
        }
    }

    @Override
    public void responseMonth(IncomeResponse incomeResponse) {
        LocalLog.d(TAG, " 月收益 responseMonth() enter" + incomeResponse.toString());
        if (incomeResponse.getError() == 0) {
            monthIncomeFragment.setData(incomeResponse);
        }

    }

    @Override
    public void responseToday(IncomeResponse incomeResponse) {
        LocalLog.d(TAG, "今天收益 responseToday() enter" + incomeResponse.toString());
        if (incomeResponse.getError() == 0) {
            incomeDes.setText(String.valueOf(incomeResponse.getData().getTotal_amount()));
        }

    }

    @Override
    public void responseYesterday(IncomeResponse yesterdayIncomeResponse) {
        LocalLog.d(TAG, "昨日收益 responseYesterday() enter" + yesterdayIncomeResponse.toString());
        if (yesterdayIncomeResponse.getError() == 0) {
            yesterDayIncomeFragment.setData(yesterdayIncomeResponse);
        }
    }
}
