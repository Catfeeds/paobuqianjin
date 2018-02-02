package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AddFriendAddressActivity;
import com.paobuqianjin.pbq.step.view.activity.LoveRankActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.RechargeRankSimpleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/2.
 */

public class CircleDetailAdminFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = CircleDetailAdminFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.circle_obj_des)
    TextView circleObjDes;
    @Bind(R.id.circle_message)
    RelativeLayout circleMessage;
    @Bind(R.id.love_money_list)
    TextView loveMoneyList;
    @Bind(R.id.go_to_recharge_rank)
    ImageView goToRechargeRank;
    @Bind(R.id.image_button)
    RelativeLayout imageButton;
    @Bind(R.id.love_money_list_rel)
    RelativeLayout loveMoneyListRel;
    @Bind(R.id.rank_recycler)
    RecyclerView rankRecycler;
    @Bind(R.id.rank_money)
    RelativeLayout rankMoney;
    @Bind(R.id.line_rank_step)
    ImageView lineRankStep;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.member_num_des)
    TextView memberNumDes;
    @Bind(R.id.step_recycler)
    RecyclerView stepRecycler;
    @Bind(R.id.step_rank)
    RelativeLayout stepRank;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.money_ret)
    TextView moneyRet;
    private Context mContext;
    private int circleId;
    private int circleNum;
    private LinearLayoutManager reChargeLayoutManager;
    private LinearLayoutManager stepLayoutManager;
    private RechargeRankBundleData rechargeRankBundleData;
    private String target;
    private float total_money;
    private float red_pack_money;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private TranslateAnimation animationCircleType;
    private CircleDetailResponse circleDetailResponse = null;
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String QRCODE_ACTION = "android.intent.action.QRCODE";


    /*
        public void setCircleId(Context context, int circleId, int circleNum, String targetStr, float total_money, float red_pack_money) {
            this.circleId = circleId;
            target = targetStr;
            this.circleNum = circleNum;
            this.total_money = total_money;
            this.red_pack_money = red_pack_money;
            LocalLog.d(TAG, "红包余额: " + total_money + " 每日红包金额" + red_pack_money);
        }*/
    public void setCircleDetail(CircleDetailResponse circleDetailResponse, int circleNum) {
        this.circleDetailResponse = circleDetailResponse;
        circleId = circleDetailResponse.getData().getId();
        this.circleNum = circleNum;
        total_money = Float.parseFloat(circleDetailResponse.getData().getTotal_amount());
        red_pack_money = Float.parseFloat(circleDetailResponse.getData().getRed_packet_amount());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_detail_fg_no_admin;
    }

    @Override
    protected String title() {
        return "圈子";
    }

    @Override
    public Object right() {
        return "管理";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Presenter.getInstance(getContext()).attachUiInterface(uiStepAndLoveRankInterface);
        Presenter.getInstance(getContext()).getCircleRechargeRand(circleId);
        Presenter.getInstance(getContext()).getCircleStepRank(circleId);
    }


    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "管理员界面");
            popSelect();
        }
    };

    private void popSelect() {
        popCircleOpBar = View.inflate(getContext(), R.layout.top_share_span, null);
        popupOpWindow = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.share_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.editor_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.mananger_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.cancle_text).setOnClickListener(onClickListener);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindow = null;
            }
        });

        popupOpWindow.setFocusable(true);
        popupOpWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindow.showAsDropDown(barTvRight, 20, -10);
        popCircleOpBar.startAnimation(animationCircleType);

    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        circleObjDes = (TextView) viewRoot.findViewById(R.id.circle_obj_des);
        rankRecycler = (RecyclerView) viewRoot.findViewById(R.id.rank_recycler);
        stepRecycler = (RecyclerView) viewRoot.findViewById(R.id.step_recycler);
        reChargeLayoutManager = new LinearLayoutManager(getContext());
        reChargeLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        stepLayoutManager = new LinearLayoutManager(getContext());
        rankRecycler.setLayoutManager(reChargeLayoutManager);
        rankRecycler.addItemDecoration(new RechargeRankSimpleAdapter.SpaceItemDecoration(30));
        stepRecycler.setLayoutManager(stepLayoutManager);

        memberNumDes = (TextView) viewRoot.findViewById(R.id.member_num_des);
        String sAgeFormat = mContext.getResources().getString(R.string.member_total);
        String sFinalMember = String.format(sAgeFormat, circleNum);
        memberNumDes.setText(sFinalMember);
        imageButton = (RelativeLayout) viewRoot.findViewById(R.id.image_button);
        imageButton.setOnClickListener(onClickListener);

        setToolBarListener(toolBarListener);
        String sMoneyFormat = mContext.getResources().getString(R.string.circle_total);
        String sMoney = String.format(sMoneyFormat, total_money);
        moneyRet = (TextView) viewRoot.findViewById(R.id.money_ret);

        if (circleDetailResponse != null) {
            String targetFormat = getResources().getString(R.string.target_step);
            target = String.format(targetFormat, circleDetailResponse.getData().getTarget());
        }
        circleObjDes.setText(target);
        moneyRet.setText(sMoney);
        if (red_pack_money > total_money) {
            LocalLog.d(TAG, "余额不足当天的红包");

        }
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_button:
                    LocalLog.d(TAG, "进入爱心榜单行情");
                    Intent intent = new Intent();
                    intent.putExtra(getActivity().getPackageName() + "circle_detail", rechargeRankBundleData);
                    intent.setClass(getContext(), LoveRankActivity.class);
                    startActivity(intent);
                    break;
                case R.id.share_text:
                    LocalLog.d(TAG, "分享");
                    if (circleDetailResponse != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString(CIRCLE_ID, String.valueOf(circleDetailResponse.getData().getId()));
                        bundle.putString(CIRCLE_NAME, circleDetailResponse.getData().getName());
                        bundle.putString(CIRCLE_LOGO, circleDetailResponse.getData().getLogo());
                        startActivity(PaoBuPayActivity.class, bundle, false, QRCODE_ACTION);
                    }
                    break;
                case R.id.editor_text:
                    LocalLog.d(TAG, "编辑");
                    break;
                case R.id.mananger_text:
                    LocalLog.d(TAG, "管理");
                    break;
                case R.id.cancle_text:
                    LocalLog.d(TAG, "解散");
                    break;
                default:
                    break;
            }
        }
    };

    private UiStepAndLoveRankInterface uiStepAndLoveRankInterface = new UiStepAndLoveRankInterface() {
 /*       @Override
        public void response(CircleDetailResponse circleDetailResponse) {
            LocalLog.d(TAG, "CircleDetailResponse() ");
            String sAgeFormat = mContext.getResources().getString(R.string.target_step);
            String sFinalMember = String.format(sAgeFormat, circleDetailResponse.getData().getTarget());
            circleObjDes.setText(sFinalMember);
            userIdCircleAdminMain = circleDetailResponse.getData().getUserid();
            if (Presenter.getInstance(getContext()).getId() == userIdCircleAdminMain) {
                LocalLog.d(TAG, "当前用户为圈创建者");

            } else {
                LocalLog.d(TAG, "当前用户普通");
            }
        }*/

        @Override
        public void response(ReChargeRankResponse reChargeRankResponse) {
            LocalLog.d(TAG, "ReChargeRankResponse() ");
            rankRecycler.setAdapter(new RechargeRankSimpleAdapter(getContext(), reChargeRankResponse.getData().getData()));
            rechargeRankBundleData = new RechargeRankBundleData(
                    (ArrayList<ReChargeRankResponse.DataBeanX.DataBean>)
                            reChargeRankResponse.getData().getData());
        }

        @Override
        public void response(StepRankResponse stepRankResponse) {
            LocalLog.d(TAG, "StepRankResponse() ");
            stepRecycler.setAdapter(new RankAdapter(getContext(), stepRankResponse.getData().getData()));
        }
    };

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
    }

    @OnClick(R.id.money_ret)
    public void onClick() {
    }
}
