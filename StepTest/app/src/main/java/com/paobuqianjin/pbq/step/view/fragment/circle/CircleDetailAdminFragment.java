package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.LoveRankActivity;
import com.paobuqianjin.pbq.step.view.activity.MemberManagerActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.RechargeRankSimpleAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.SelectSettingAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/2.
 */

public class CircleDetailAdminFragment extends BaseBarImageViewFragment implements CircleDetailInterface {
    private final static String TAG = CircleDetailAdminFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
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
    @Bind(R.id.circle_cover)
    ImageView circleCover;
    @Bind(R.id.join_in)
    Button joinIn;

    private JoinCircleParam joinCircleParam;
    private Context mContext;
    private int circleId = -1;
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
    private final static String MEMBER_MANANGER_ACTION = "android.intent.action.MAMBER_MANAGER_ACTION";
    private final static String ACTION_ENTER_CIRCLE = "coma.paobuqian.pbq.step.ICON_ACTION";
    private final static String ACTION_SCAN_CIRCLE_ID = "com.paobuqianjin.pbq.step.SCAN_ACTION";

    String titleStr = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_detail_fg_no_admin;
    }

    @Override
    protected String title() {
        return titleStr;
    }

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.exit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Presenter.getInstance(context).attachUiInterface(this);
        Presenter.getInstance(getContext()).attachUiInterface(uiStepAndLoveRankInterface);
        Presenter.getInstance(getContext()).attachUiInterface(joinCircleInterface);
    }


    private ToolBarListener toolBarListener = new ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            if (circleDetailResponse == null) {
                return;
            }
            if (circleDetailResponse.getData().getIs_admin() == 1) {
                LocalLog.d(TAG, "管理员界面");
                popAdminSelect();
            } else {
                popNoAdminSelect();
            }

        }
    };

    private void popNoAdminSelect() {
        LocalLog.d(TAG, "popNoAdminSelect() enter");
        popCircleOpBar = View.inflate(getContext(), R.layout.top_share_no_admin, null);
        popupOpWindow = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.share_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.exit_text).setOnClickListener(onClickListener);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popWindow dismiss() ");
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


    public void popPassWordEdit() {
        LocalLog.d(TAG, "popPassWordEdit() enter 弹出密码输入框");
        popCircleOpBar = View.inflate(getContext(), R.layout.pass_word_layout, null);
        popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
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
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupOpWindow.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    private void popAdminSelect() {
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
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_ENTER_CIRCLE.equals(intent.getAction())) {
                LocalLog.d(TAG, "logo 进入");
                circleId = intent.getIntExtra(getContext().getPackageName() + "circleid", -1);

            } else if (ACTION_SCAN_CIRCLE_ID.equals(intent.getAction())) {
                LocalLog.d(TAG, "扫码 进入");
                circleId = intent.getIntExtra(getContext().getPackageName() + "circleid", -1);
            }
        }

        if (circleId != -1) {
            Presenter.getInstance(getContext()).getCircleDetail(circleId);
        }
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
        imageButton = (RelativeLayout) viewRoot.findViewById(R.id.image_button);
        imageButton.setOnClickListener(onClickListener);

        setToolBarListener(toolBarListener);
        moneyRet = (TextView) viewRoot.findViewById(R.id.money_ret);
        circleCover = (ImageView) viewRoot.findViewById(R.id.circle_cover);

        joinIn = (Button) viewRoot.findViewById(R.id.join_in);

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
                    LocalLog.d(TAG, "成员管理");
                    Bundle bundle = new Bundle();
                    bundle.putString(CIRCLE_ID, String.valueOf(circleDetailResponse.getData().getId()));
                    startActivity(MemberManagerActivity.class, bundle, false, MEMBER_MANANGER_ACTION);
                    break;
                case R.id.cancle_text:
                    LocalLog.d(TAG, "解散");
                    break;
                case R.id.exit_text:
                    LocalLog.d(TAG, "退出");

                    break;
                case R.id.join_in:
                    LocalLog.d(TAG, "点击加入圈子");
                    if (joinCircleParam == null) {
                        joinCircleParam = new JoinCircleParam();
                        joinCircleParam.setCircleid(circleId);
                    }
                    Presenter.getInstance(getContext()).joinCircle(joinCircleParam);
                    break;
                default:
                    break;
            }
        }
    };


    private JoinCircleInterface joinCircleInterface = new JoinCircleInterface() {
        @Override
        public void response(JoinCircleResponse joinCircleResponse) {
            if (joinCircleResponse.getError() == 0) {
                LocalLog.d(TAG, "加入成功");
                joinIn.setVisibility(View.GONE);
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getMessage().equals("请输入密码")) {
                popPassWordEdit();
            }
        }
    };


    private UiStepAndLoveRankInterface uiStepAndLoveRankInterface = new UiStepAndLoveRankInterface() {
        @Override
        public void response(ReChargeRankResponse reChargeRankResponse) {
            LocalLog.d(TAG, "ReChargeRankResponse() ");
            if (reChargeRankResponse.getError() == 0) {
                rankRecycler.setAdapter(new RechargeRankSimpleAdapter(getContext(), reChargeRankResponse.getData().getData()));
                rechargeRankBundleData = new RechargeRankBundleData(
                        (ArrayList<ReChargeRankResponse.DataBeanX.DataBean>)
                                reChargeRankResponse.getData().getData());

            }
        }

        @Override
        public void response(StepRankResponse stepRankResponse) {
            LocalLog.d(TAG, "StepRankResponse() ");
            if (stepRankResponse.getError() == 0) {
                stepRecycler.setAdapter(new RankAdapter(getContext(), stepRankResponse.getData().getData()));
                String sAgeFormat = mContext.getResources().getString(R.string.member_total);
                String sFinalMember = String.format(sAgeFormat, stepRankResponse.getData().getPagenation().getTotalCount());
                memberNumDes.setText(sFinalMember);
            }

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
        if (popupOpWindow != null) {
            popupOpWindow.dismiss();
            popupOpWindow = null;
        }
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(uiStepAndLoveRankInterface);
        Presenter.getInstance(getContext()).dispatchUiInterface(joinCircleInterface);
    }

    @OnClick(R.id.money_ret)
    public void onClick() {
    }

    @Override
    public void response(CircleDetailResponse circleDetailResponse) {
        if (circleDetailResponse.getError() == 0) {
            this.circleDetailResponse = circleDetailResponse;
            if (circleDetailResponse.getData().getIs_join() == 0) {
                LocalLog.d(TAG, "还没有加入圈子");
                joinIn.setVisibility(View.VISIBLE);
                joinIn.setOnClickListener(onClickListener);
            }
            total_money = Float.parseFloat(circleDetailResponse.getData().getTotal_amount());
            red_pack_money = Float.parseFloat(circleDetailResponse.getData().getRed_packet_amount());

            String sMoneyFormat = mContext.getResources().getString(R.string.circle_total);
            String sMoney = String.format(sMoneyFormat, total_money);
            if (circleDetailResponse != null) {
                String targetFormat = getResources().getString(R.string.target_step);
                target = String.format(targetFormat, circleDetailResponse.getData().getTarget());
            }
            circleObjDes.setText(target);
            moneyRet.setText(sMoney);
            if (red_pack_money > total_money) {
                LocalLog.d(TAG, "余额不足当天的红包");

            }
            titleStr = circleDetailResponse.getData().getName();
            Presenter.getInstance(getContext()).getImage(circleCover, circleDetailResponse.getData().getLogo());
            Presenter.getInstance(getContext()).getCircleRechargeRand(circleId);
            Presenter.getInstance(getContext()).getCircleStepRank(circleId);
        } else if (circleDetailResponse.getError() == -1) {
            Toast.makeText(getContext(), circleDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (circleDetailResponse.getError() == 1) {

        }
    }
}
