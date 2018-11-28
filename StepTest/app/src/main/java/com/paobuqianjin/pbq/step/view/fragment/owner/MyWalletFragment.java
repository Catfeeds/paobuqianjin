package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserIncomInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MyBankCardActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.PayManagerActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.WalletRedPkgIncomeAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
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

public class MyWalletFragment extends BaseBarImageViewFragment implements UserIncomInterface {
    private final static String TAG = MyWalletFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.income_rel)
    RelativeLayout incomeRel;
    @Bind(R.id.total_income_des)
    TextView totalIncomeDes;
    @Bind(R.id.total_income_num)
    TextView totalIncomeNum;
    @Bind(R.id.line_total)
    ImageView lineTotal;
    @Bind(R.id.total_span)
    RelativeLayout totalSpan;
    @Bind(R.id.step_dollor_num_des)
    TextView stepDollorNumDes;
    @Bind(R.id.step_dollor_num)
    TextView stepDollorNum;
    @Bind(R.id.line_current)
    ImageView lineCurrent;
    @Bind(R.id.step_dollor_span)
    RelativeLayout yesterdaySpan;
    @Bind(R.id.income_outline)
    LinearLayout incomeOutline;
    @Bind(R.id.income_container)
    RelativeLayout incomeContainer;
    @Bind(R.id.charge_bar)
    Button chargeBar;

    private View popWalletBar;
    private PopupWindow popupWalletWindow;
    private TranslateAnimation animationCircleType;
    private int mIndex;//当前收入页面索引
    private Fragment[] fragments;
    private ImageView[] lines;
    private int mCurrentIndex = 0;
    private AllIncomeFragment allIncomeFragment = new AllIncomeFragment();
    private StepDollarDetailFragment stepDollarDetailFragment = new StepDollarDetailFragment();
    WalletRedPkgIncomeAdapter allAdapter;
    ArrayList<IncomeResponse.DataBeanX.DataBean> yesterData = new ArrayList<>();
    ArrayList<IncomeResponse.DataBeanX.DataBean> monthData = new ArrayList<>();
    ArrayList<AllIncomeResponse.DataBeanX.DataBean> allData = new ArrayList<>();
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_RECHARGE = "com.paobuqian.pbq.step.PAY_RECHARGE.ACTION";
    /*    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";*/
    private int pageIndexAll = 1;
    private int pageAllCount = 0;
    public final static int REQUEST_CRASH = 231;
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
        return getDrawableResource(R.drawable.exit);
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

    private ToolBarListener toolBarListener = new ToolBarListener() {
        @Override
        public void clickLeft() {
            getActivity().onBackPressed();
        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "查看明细");

            //startActivity(InoutcomDetailActivity.class, null);
            popWalletSelect();
        }
    };

    private void popWalletSelect() {
        LocalLog.d(TAG, "popNoAdminSelect() enter");
        popWalletBar = View.inflate(getContext(), R.layout.wallet_select_bar, null);
        popupWalletWindow = new PopupWindow(popWalletBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWalletWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popWindow dismiss() ");
                popupWalletWindow = null;
            }
        });
        TextView walletPay = (TextView) popWalletBar.findViewById(R.id.wallet_pay_manager);
        TextView walletBank = (TextView) popWalletBar.findViewById(R.id.wallet_my_bank);
        walletPay.setOnClickListener(onClickListener);
        walletBank.setOnClickListener(onClickListener);
        popupWalletWindow.setFocusable(true);
        popupWalletWindow.setOutsideTouchable(true);
        popupWalletWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupWalletWindow.showAsDropDown(barTvRight, 20, -10);
        popWalletBar.startAnimation(animationCircleType);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (popupWalletWindow != null) {
                popupWalletWindow.dismiss();
            }
            switch (v.getId()) {
                case R.id.wallet_pay_manager:
                    startActivity(PayManagerActivity.class, null);
                    break;
                case R.id.wallet_my_bank:
                    startActivity(MyBankCardActivity.class, null);
                    break;
            }
        }
    };

    @Override
    protected void initView(View viewRoot) {
        incomeRel = (RelativeLayout) viewRoot.findViewById(R.id.income_rel);
        setToolBarListener(toolBarListener);
        fragments = new Fragment[]{allIncomeFragment, stepDollarDetailFragment};
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.income_container, allIncomeFragment)
                .add(R.id.income_container, stepDollarDetailFragment)
                .hide(stepDollarDetailFragment)
                .show(allIncomeFragment)
                .commit();

        allAdapter = new WalletRedPkgIncomeAdapter(getContext(), null);
        totalIncomeNum = (TextView) viewRoot.findViewById(R.id.total_income_num);
        stepDollorNum = (TextView) viewRoot.findViewById(R.id.step_dollor_num);
        allIncomeFragment.setAdapter(allAdapter);
        allIncomeFragment.listen(mLoadMoreListener);
        lineCurrent = (ImageView) viewRoot.findViewById(R.id.line_current);
        lineTotal = (ImageView) viewRoot.findViewById(R.id.line_total);
        lines = new ImageView[]{lineTotal, lineCurrent};
        loadAllData(allData);
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

    @OnClick({R.id.charge_bar, R.id.total_span, R.id.step_dollor_span, R.id.step_span, R.id.money_span})
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
            case R.id.total_span:
            case R.id.money_span:
                LocalLog.d(TAG, "总收入");
                mIndex = 0;
                onTabIndex(mIndex);
                break;
            case R.id.step_dollor_span:
            case R.id.step_span:
                mIndex = 1;
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
            if (lines[mCurrentIndex].getVisibility() == View.VISIBLE) {
                lines[mCurrentIndex].setVisibility(View.INVISIBLE);
            }
            if (lines[fragmentIndex].getVisibility() != View.VISIBLE) {
                lines[fragmentIndex].setVisibility(View.VISIBLE);
            }
        }
        mCurrentIndex = fragmentIndex;
    }


    private void loadAllData(ArrayList<AllIncomeResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadAllData() enter");
        allAdapter.notifyDataSetChanged(dataBeans);


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
                if (pageAllCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexAll > pageAllCount) {
                        /*Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();*/
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
        if (!isAdded()) {
            return;
        }
        LocalLog.d(TAG, " 所有收益 responseAll() enter" + allIncomeResponse.toString());
        if (allIncomeResponse.getError() == 0) {
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
    public void response(UserInfoResponse userInfoResponse) {
        if (!isAdded()) {
            return;
        }
        if (userInfoResponse.getError() == 0) {

            totalIncomeNum.setText(userInfoResponse.getData().getBalance());
            stepDollorNum.setText(String.valueOf(userInfoResponse.getData().getCredit()));
            String moneyStr = String.valueOf(userInfoResponse.getData().getBalance()) + " 元";
            SpannableString moneyString = new SpannableString(moneyStr);
            moneyString.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(userInfoResponse.getData().getBalance()).length(), moneyStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            totalIncomeNum.setText(moneyString);
            String creditStr = String.valueOf(userInfoResponse.getData().getCredit()) + " 步币";
            SpannableString creditString = new SpannableString(creditStr);
            creditString.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(userInfoResponse.getData().getCredit()).length(), creditStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            stepDollorNum.setText(creditString);
        } else if (userInfoResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalLog.d(TAG, "充值成功或者提现成功");
        Presenter.getInstance(getContext()).getUserPackageMoney();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_RECHARGE || requestCode == REQUEST_CRASH) {
//                LocalLog.d(TAG, "充值成功或者提现成功");
//                Presenter.getInstance(getContext()).getUserPackageMoney();
            }
        }
    }

    @Override
    public void responseToday(IncomeResponse incomeResponse) {

    }

    @Override
    public void responseMonth(IncomeResponse incomeResponse) {

    }
}