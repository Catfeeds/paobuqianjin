package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CrashActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/15.
 */

public class MyWalletFragment extends BaseBarStyleTextViewFragment {
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
    @Bind(R.id.income_recycler)
    RecyclerView incomeRecycler;
    @Bind(R.id.charge_bar)
    Button chargeBar;
    @Bind(R.id.crash)
    Button crash;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_wallet_fg;
    }

    @Override
    protected String title() {
        return "我的钱包";
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
        crash = (Button) viewRoot.findViewById(R.id.crash);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.charge_bar, R.id.crash})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.charge_bar:
                break;
            case R.id.crash:
                intent.setClass(getContext(), CrashActivity.class);
                startActivity(intent);
                LocalLog.d(TAG, "提现");
                break;
        }
    }
}
