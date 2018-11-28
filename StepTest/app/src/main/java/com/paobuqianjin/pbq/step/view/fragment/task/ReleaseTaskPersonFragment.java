package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskReleaseInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.ShopToolUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/26.
 */
/*
@className :ReleaseTaskPersonFragment
*@date 2018/1/26
*@author
*@description 发布任务界面
*/
public class ReleaseTaskPersonFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = ReleaseTaskPersonFragment.class.getSimpleName();

    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String TASK_NO = "taskno";
    private final static String CIRCLE_RECHARGE = "pay";
    private static final int SELECT_FRIENDS = 0;
    private static final int REQUEST_PAY_FRIEND_PKG = 1;
    ArrayList<UserFriendResponse.DataBeanX.DataBean> dataBeans = null;
    @Bind(R.id.target_step_des)
    TextView targetStepDes;
    @Bind(R.id.go_to_target_step)
    TextView goToTargetStep;
    @Bind(R.id.target_task_step_num)
    EditText targetTaskStepNum;
    @Bind(R.id.target_task_span)
    RelativeLayout targetTaskSpan;
    @Bind(R.id.target_money_des)
    TextView targetMoneyDes;
    @Bind(R.id.task_pay)
    TextView taskPay;
    @Bind(R.id.target_task_money_num)
    EditText targetTaskMoneyNum;
    @Bind(R.id.money_task_span)
    RelativeLayout moneyTaskSpan;
    @Bind(R.id.target_day_des)
    TextView targetDayDes;
    @Bind(R.id.task_day)
    TextView taskDay;
    @Bind(R.id.target_task_day_num)
    EditText targetTaskDayNum;
    @Bind(R.id.day_task_span)
    RelativeLayout dayTaskSpan;
    @Bind(R.id.task_recv_ico)
    ImageView taskRecvIco;
    @Bind(R.id.task_recv)
    TextView taskRecv;
    @Bind(R.id.add_friend_text)
    TextView addFriendText;
    @Bind(R.id.add_recv)
    ImageView addRecv;
    @Bind(R.id.add_task_friend)
    RelativeLayout addTaskFriend;
    @Bind(R.id.add_friend_des)
    TextView addFriendDes;
    @Bind(R.id.recv_recycler)
    RecyclerView recvRecycler;
    @Bind(R.id.add_ico)
    ImageView addIco;
    @Bind(R.id.target_people_money_num)
    TextView targetPeopleMoneyNum;
    @Bind(R.id.switch_doll)
    ImageView switchDoll;
    @Bind(R.id.crash_money)
    TextView crashMoney;
    @Bind(R.id.step_dolls)
    TextView stepDolls;
    @Bind(R.id.target_step_dollar_num)
    EditText targetStepDollarNum;
    @Bind(R.id.step_dollar_task_span)
    RelativeLayout stepDollarTaskSpan;
    @Bind(R.id.people_pay)
    TextView peoplePay;
    @Bind(R.id.people_money_des)
    TextView peopleMoneyDes;
    private TaskReleaseParam taskReleaseParam = new TaskReleaseParam();
    private String friends;
    LinearLayoutManager layoutManager;
    FriendBundleData friendBundleData = null;
    private final static String ACTION_TASK = "com.paobuqianjin.pbq.step.ACTION_TASK";
    private float totalMoney;
    private ChooseOneItemWheelPopWindow wheelPopWindow;
    private final int DEVALUE_STEP = 10000;//默认步数
    /* private String[] targetStepArr = null*//*{"3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"}*//*;*/
    private ArrayList<String> targetStepArr = new ArrayList<>();
    Banner banner;
    private int style = -1;
    //是否选择步币
    private boolean boolStepDoll = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_release_fg;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    @Override
    protected String title() {
        return "添加专享红包";
    }

    private void loadBanner() {
        final String bannerUrl = NetApi.urlAd + "?position=task_create" + Presenter.getInstance(getContext()).getLocationStrFormat();
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(getActivity()).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    final ArrayList<AdObject> adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
                                    adObject.setRid(Integer.parseInt(adresponse.getData().get(i).getRid()));
                                    adObject.setImg_url(adresponse.getData().get(i).getImgs().get(j).getImg_url());
                                    adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                                    adList.add(adObject);
                                }
                            }
                        }
                    }
                    banner.setImageLoader(new BannerImageLoader())
                            .setImages(adList)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .setBannerAnimation(Transformer.Default)
                            .isAutoPlay(true)
                            .setDelayTime(2000)
                            .setIndicatorGravity(BannerConfig.CENTER)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (adList.get(position).getRid() == 0) {
                                        LocalLog.d(TAG, "复制微信号");
                                        ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(getActivity(), "微信号复制成功");
                                    }
                                    String targetUrl = adList.get(position).getTarget_url();
                                    String result = ShopToolUtil.taoBaoString(targetUrl);
                                    if (!TextUtils.isEmpty(result)) {
                                        if (result.startsWith(ShopToolUtil.TaoBaoSchema)
                                                && Utils.checkPackage(getContext(), ShopToolUtil.TaoBao)) {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", targetUrl));
                                        }
                                    }

                                }
                            })
                            .start();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取失败!");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(taskReleaseInterface);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(taskReleaseInterface);
    }

    @Override
    protected void initView(View viewRoot) {
        banner = (Banner) viewRoot.findViewById(R.id.banner);
        targetTaskStepNum = (EditText) viewRoot.findViewById(R.id.target_task_step_num);
        targetTaskMoneyNum = (EditText) viewRoot.findViewById(R.id.target_task_money_num);
        targetTaskDayNum = (EditText) viewRoot.findViewById(R.id.target_task_day_num);
        recvRecycler = (RecyclerView) viewRoot.findViewById(R.id.recv_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recvRecycler.setNestedScrollingEnabled(false);
        recvRecycler.setHasFixedSize(true);
        recvRecycler.setLayoutManager(layoutManager);
        moneyTaskSpan = (RelativeLayout) viewRoot.findViewById(R.id.money_task_span);
        targetStepDollarNum = (EditText) viewRoot.findViewById(R.id.target_step_dollar_num);
        stepDollarTaskSpan = (RelativeLayout) viewRoot.findViewById(R.id.step_dollar_task_span);
        addFriendDes = (TextView) viewRoot.findViewById(R.id.add_friend_des);
        targetPeopleMoneyNum = (TextView) viewRoot.findViewById(R.id.target_people_money_num);
        recvRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(10));
        targetTaskDayNum.setSelection(targetTaskDayNum.getText().toString().length());
        targetTaskMoneyNum.setSelection(targetTaskMoneyNum.getText().toString().length());
        targetTaskStepNum.setSelection(targetTaskStepNum.getText().toString().length());
        targetTaskMoneyNum.addTextChangedListener(textWatcher);
        targetTaskDayNum.addTextChangedListener(textWatcher);
        targetStepDollarNum.addTextChangedListener(textWatcher);
        targetTaskStepNum.setText(DEVALUE_STEP + "");
        peoplePay = (TextView) viewRoot.findViewById(R.id.people_pay);
        peopleMoneyDes = (TextView) viewRoot.findViewById(R.id.people_money_des);

        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlTarget, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                LocalLog.d(TAG, "");
                try {
                    CircleTargetResponse circleTargetResponse = new Gson().fromJson(s, CircleTargetResponse.class);
                    if (circleTargetResponse.getData() != null && circleTargetResponse.getData().size() > 0) {
                        for (int i = 0; i < circleTargetResponse.getData().size(); i++) {
                            targetStepArr.add(String.valueOf(circleTargetResponse.getData().get(i).getTarget()));
                        }
                    }
                } catch (JsonSyntaxException j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                getActivity().onBackPressed();
            }

            @Override
            public void clickRight() {
                calculateResultMoney();
                confirm();
            }
        });
        loadBanner();
    }

    @Override
    public Object right() {
        return "确定";
    }

    private void confirm() {
        if (isAdded()) {
            if (checkReleaseParams()) {
                if (!boolStepDoll) {
                    taskReleaseParam
                            .setTask_days(Integer.parseInt(targetTaskDayNum.getText().toString()))
                            .setReward_amount(Float.parseFloat(targetTaskMoneyNum.getText().toString()))
                            .setTarget_step(Integer.parseInt(targetTaskStepNum.getText().toString()))
                            .setType(style)
                            .setTrade_way(1)
                            .setTo_userid(friends)
                            .setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).taskRelease(taskReleaseParam);
                } else {
                    taskReleaseParam
                            .setTask_days(Integer.parseInt(targetTaskDayNum.getText().toString()))
                            .setCredit(Integer.parseInt(targetStepDollarNum.getText().toString().trim()))
                            .setTarget_step(Integer.parseInt(targetTaskStepNum.getText().toString()))
                            .setType(style)
                            .setTrade_way(2)
                            .setTo_userid(friends)
                            .setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).taskRelease(taskReleaseParam);
                }
            }
        }
    }

    private TaskReleaseInterface taskReleaseInterface = new TaskReleaseInterface() {
        @Override
        public void response(TaskReleaseResponse taskReleaseResponse) {
            LocalLog.d(TAG, "TaskReleaseResponse() enter");
            if (!isAdded()) {
                return;
            }
            if (taskReleaseResponse.getError() == 0) {
                LocalLog.d(TAG, "任务生成，去充值");
                if (!boolStepDoll) {
                    //getActivity().finish();
                    Bundle bundle = new Bundle();
                    bundle.putString(PAY_FOR_STYLE, "task");
                    bundle.putString(TASK_NO, taskReleaseResponse.getData().getTask_no());
                    bundle.putString(CIRCLE_RECHARGE, String.format("%.2f", totalMoney));
                    Intent intent = new Intent();
                    intent.setClass(getContext(), PaoBuPayActivity.class);
                    intent.putExtra(getActivity().getPackageName(), bundle);
                    intent.setAction(PAY_ACTION);
                    startActivityForResult(intent, REQUEST_PAY_FRIEND_PKG);
                } else {
                    PaoToastUtils.showLongToast(getContext(),"发布成功");
                    getActivity().finish();
                }
            } else if (taskReleaseResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            } else {
                Toast.makeText(getContext(), taskReleaseResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
            } else {
                PaoToastUtils.showLongToast(getContext(), errorCode.getMessage());
            }
        }
    };

    private void check_swicth(String data) {
        if (!boolStepDoll) {
            switchDoll.setImageResource(R.drawable.sdoar_switch_a);
            LocalLog.d(TAG, "选择步币");
            targetTaskMoneyNum.setText(data);
            moneyTaskSpan.setVisibility(View.GONE);
            stepDollarTaskSpan.setVisibility(View.VISIBLE);
            boolStepDoll = !boolStepDoll;
            crashMoney.setTextColor(ContextCompat.getColor(getContext(), R.color.color_A6A9D9));
            stepDolls.setTextColor(ContextCompat.getColor(getContext(), R.color.color_6c71c4));
            peoplePay.setText("步币");
        } else {
            LocalLog.d(TAG, "选择现金");
            switchDoll.setImageResource(R.drawable.sdoar_switch_b);
            crashMoney.setTextColor(ContextCompat.getColor(getContext(), R.color.color_6c71c4));
            stepDolls.setTextColor(ContextCompat.getColor(getContext(), R.color.color_A6A9D9));
            targetStepDollarNum.setText(data);
            moneyTaskSpan.setVisibility(View.VISIBLE);
            stepDollarTaskSpan.setVisibility(View.GONE);
            boolStepDoll = !boolStepDoll;
            peoplePay.setText("元");
        }
    }

    @OnClick({R.id.switch_doll, R.id.target_task_span, R.id.target_task_step_num, R.id.add_task_friend, R.id.add_ico})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.switch_doll:
                check_swicth(null);
                break;
            case R.id.target_task_span:
            case R.id.target_task_step_num:
                LocalLog.d(TAG, "设置任务目标步数");
                if (wheelPopWindow == null && targetStepArr.size() > 0) {
                    wheelPopWindow = new ChooseOneItemWheelPopWindow(getActivity(), targetStepArr);
                    wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            targetTaskStepNum.setText(result);
                        }
                    });
                }
                if (wheelPopWindow.isShowing()) {
                    wheelPopWindow.cancel();
                    return;
                }
                wheelPopWindow.setCurrentSelectValue(targetTaskStepNum.getText().toString());
                wheelPopWindow.show();
                break;
            case R.id.add_task_friend:
                LocalLog.d(TAG, "添加任务好友");
                intent.putExtra(getActivity().getPackageName(), friendBundleData);
                intent.setAction(ACTION_TASK);
                intent.setClass(getActivity(), SelectFriendActivity.class);
                startActivityForResult(intent, SELECT_FRIENDS);
                break;
            case R.id.add_ico:
                LocalLog.d(TAG, "查看所有接入任务的好友");
                intent.putExtra(getActivity().getPackageName(), friendBundleData);
                intent.setClass(getActivity(), SelectFriendActivity.class);
                intent.setAction(ACTION_TASK);
                startActivityForResult(intent, SELECT_FRIENDS);
                break;
            default:
                break;
        }
    }


    private boolean checkReleaseParams() {

        if (targetTaskStepNum.getText() == null || targetTaskStepNum.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入目标步数", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!boolStepDoll) {
            LocalLog.d(TAG, "现金");
            if (TextUtils.isEmpty(targetTaskMoneyNum.getText().toString().trim())) {
                PaoToastUtils.showLongToast(getContext(), "请输入奖励金额");
                return false;
            }

        } else {
            LocalLog.d(TAG, "步币");
            if (TextUtils.isEmpty(targetStepDollarNum.getText().toString().trim())) {
                PaoToastUtils.showLongToast(getContext(), "请输入奖励步币数");
                return false;
            }
            UserInfoResponse.DataBean userInfoResponse = Presenter.getInstance(getContext()).getCurrentUser();
            try {
                if (Integer.parseInt(targetStepDollarNum.getText().toString().trim()) > userInfoResponse.getCredit()) {
                    PaoToastUtils.showLongToast(getContext(), "步币数量不足");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (targetTaskDayNum.getText() == null || targetTaskDayNum.getText().toString().equals("")) {
            PaoToastUtils.showLongToast(getContext(), "请输入任务天数");
            return false;
        }

        if (targetTaskDayNum.getText().toString().equals("0")) {
            PaoToastUtils.showLongToast(getContext(), "任务天数不能为0");
            return false;
        }
        if (TextUtils.isEmpty(friends)) {
            PaoToastUtils.showLongToast(getContext(), "请选择好友");
            return false;
        }
        if (TextUtils.isEmpty(targetPeopleMoneyNum.getText().toString().toString())) {
            return false;
        }
        if (!boolStepDoll) {
            float perMoney = Float.parseFloat(targetPeopleMoneyNum.getText().toString().toString());
            if (perMoney < 1.00f) {
                PaoToastUtils.showShortToast(getContext(), "单日单个人奖励不能低于1.00元");
                return false;
            }

            if (perMoney > 200.00f) {
                PaoToastUtils.showShortToast(getContext(), "单日单个人最高奖励不能高于200.00元");
                return false;
            }
        } else {

        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        switch (requestCode) {
            case SELECT_FRIENDS:
                LocalLog.d(TAG, "添加数据成功");
                if (data != null) {
                    friendBundleData = (FriendBundleData) data.getParcelableExtra(getActivity().getPackageName());
                    if (friendBundleData == null) {
                        return;
                    }
                    dataBeans = friendBundleData.getFriendData();
                    recvRecycler.setAdapter(new LikeUserAdapter(getContext(), dataBeans));
                    String friends = "";
                    String sFriendFormat = getContext().getResources().getString(R.string.add_friend);
                    String sFriendNum = String.format(sFriendFormat, dataBeans.size());
                    addFriendDes.setText(sFriendNum);
                    for (int i = 0; i < dataBeans.size(); i++) {
                        if (i == dataBeans.size() - 1) {
                            friends += String.valueOf(dataBeans.get(i).getId());
                        } else {
                            friends += String.valueOf(dataBeans.get(i).getId()) + ",";
                        }

                    }
                    this.friends = friends;
                    LocalLog.d(TAG, friends);
                    if (!boolStepDoll) {
                        if (!TextUtils.isEmpty(targetTaskMoneyNum.getText()) && !TextUtils.isEmpty(targetTaskDayNum.getText().toString())
                                && dataBeans != null && dataBeans.size() > 0) {
                            calculateResultMoney();
                        }
                    } else {
                        if (!TextUtils.isEmpty(targetStepDollarNum.getText()) && !TextUtils.isEmpty(targetTaskDayNum.getText().toString())
                                && dataBeans != null && dataBeans.size() > 0) {
                            calculateResultMoney();
                        }
                    }
                }
                break;
            case REQUEST_PAY_FRIEND_PKG:
                if (resultCode == RESULT_OK) {
                    LocalLog.d(TAG, "支付成功!");
                    getActivity().finish();
                } else {
                    LocalLog.d(TAG, "取消支付");
                }
                break;
        }
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!boolStepDoll) {
                if (!TextUtils.isEmpty(targetTaskMoneyNum.getText()) && !TextUtils.isEmpty(targetTaskDayNum.getText().toString())
                        && dataBeans != null && dataBeans.size() > 0) {
                    calculateResultMoney();
                }
            } else {
                if (!TextUtils.isEmpty(targetStepDollarNum.getText()) && !TextUtils.isEmpty(targetTaskDayNum.getText().toString())
                        && dataBeans != null && dataBeans.size() > 0) {
                    calculateResultMoney();
                }
            }
        }
    };

    /**
     * 计算红包总额
     */
    private void calculateResultMoney() {
        if (!boolStepDoll) {
            int dayNum = 0;
            float taskTotalMoney = 0f;
            int friendsNum = 0;
            if (TextUtils.isEmpty(targetTaskMoneyNum.getText()) || TextUtils.isEmpty(targetTaskDayNum.getText().toString())
                    || dataBeans == null || dataBeans.size() == 0) {
                LocalLog.d(TAG, "不计算");
                return;
            }
            if (TextUtils.isEmpty(targetTaskMoneyNum.getText())) {
                taskTotalMoney = 0f;
            } else {
                try {
                    taskTotalMoney = Float.parseFloat(targetTaskMoneyNum.getText().toString());
                } catch (NumberFormatException e) {
                    taskTotalMoney = 0f;
                }
            }

            if (TextUtils.isEmpty(targetTaskDayNum.getText())) {
                dayNum = 0;
            } else {
                try {
                    dayNum = Integer.parseInt(targetTaskDayNum.getText().toString());
                } catch (NumberFormatException e) {
                    dayNum = 0;
                }
            }

            if (TextUtils.isEmpty(friends) && dataBeans == null || dataBeans.size() == 0) {
//            Toast.makeText(getContext(), "请选择好友", Toast.LENGTH_SHORT).show();
                friendsNum = 0;
            } else {
                friendsNum = dataBeans.size();
            }

            totalMoney = taskTotalMoney;
            float perMoney = 0.0f;
            if (dayNum == 0 || friendsNum == 0) {
                perMoney = taskTotalMoney;
            } else {
                perMoney = taskTotalMoney / (dayNum * friendsNum);
            }
            LocalLog.d(TAG, "per one per day money " + perMoney);
            String dailyMoneyStr = String.format("%.2f", taskTotalMoney);
            String dayNumStr = dayNum + "";
            String personNumStr = friendsNum + "";
            String allMoneyStr = String.format("%.2f", perMoney);
            targetPeopleMoneyNum.setText(allMoneyStr);
        } else {
            int friendsNum;
            int dayNum = 0;
            if (TextUtils.isEmpty(friends) && dataBeans == null || dataBeans.size() == 0) {
                friendsNum = 0;
            } else {
                friendsNum = dataBeans.size();
            }

            if (TextUtils.isEmpty(targetTaskDayNum.getText())) {
                dayNum = 0;
            } else {
                try {
                    dayNum = Integer.parseInt(targetTaskDayNum.getText().toString());
                } catch (NumberFormatException e) {
                    dayNum = 0;
                }
            }
            int perStepDoll;
            if (dayNum == 0 || friendsNum == 0) {
                perStepDoll = Integer.parseInt(targetStepDollarNum.getText().toString().trim());
            } else {
                perStepDoll = Integer.parseInt(targetStepDollarNum.getText().toString().trim()) / (dayNum * friendsNum);
            }
            targetPeopleMoneyNum.setText(String.valueOf(perStepDoll));
        }
    }
}