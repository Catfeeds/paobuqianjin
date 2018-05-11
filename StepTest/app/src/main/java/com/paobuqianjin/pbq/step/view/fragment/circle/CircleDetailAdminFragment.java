package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.StepBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostCircleRedPkgParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DeleteCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginOutResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostRevRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiStepAndLoveRankInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.EditCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.LoveRankActivity;
import com.paobuqianjin.pbq.step.view.activity.MemberManagerActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.RechargeRankSimpleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

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

    EditText passEdit;
    ImageView lineMid;
    TextView cancelText;
    TextView confirmText;
    RelativeLayout partTwo;
    @Bind(R.id.re_charge_bt)
    Button reChargeBt;
    @Bind(R.id.circle_detail_fg)
    RelativeLayout circleDetailFg;
    @Bind(R.id.scan_more)
    TextView scanMore;

    private boolean is_password = false;

    private JoinCircleParam joinCircleParam;
    private Context mContext;
    private int circleId = -1;
    private LinearLayoutManager reChargeLayoutManager;
    private LinearLayoutManager stepLayoutManager;
    private RechargeRankBundleData rechargeRankBundleData;
    private StepBundleData stepBundleData;
    private String target;
    private float total_money;
    private float red_pack_money;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private PopupWindow popupOpWindowTop;
    private PopupWindow popOpWindowRedButton;
    private PopupWindow popCircleRedPkg;
    private TranslateAnimation animationCircleType;
    private ImageView imageView;
    private TextView money;
    private CircleDetailResponse circleDetailResponse = null;
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String QRCODE_ACTION = "android.intent.action.QRCODE";
    private final static String MEMBER_MANANGER_ACTION = "android.intent.action.MAMBER_MANAGER_ACTION";
    private final static String ACTION_ENTER_CIRCLE = "coma.paobuqian.pbq.step.ICON_ACTION";
    private final static String ACTION_SCAN_CIRCLE_ID = "com.paobuqianjin.pbq.step.SCAN_ACTION";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String PAY_RECHARGE = "com.paobuqian.pbq.step.PAY_RECHARGE.ACTION";
    private final static String ACTION_STEP_RANK = "com.paobuqian.pbq.step.STEP_ACTION";
    private final static String ACTION_LOVE_RANK = "com.paobuqian.pbq.step.LOVE_ACTION";
    private final int REQUEST_MEMBER = 201;
    private final int REQUEST_EDIT = 202;
    private final int REQUEST_RECHARGE = 333;
    String titleStr = "";
    private boolean is_join = false;
    private int pageIndex = 1, PAGESIZE = 200;
    private int position = -1;
    private final static String QUIT_ACTION = "com.paobuqianjin.pbq.step.QUIT";
    private final static String DELETE_ACTION = "com.paobuqianjin.pbq.step.DELETE_CIRCLE";
    private final static String DELETE_MEMBER = "com.paobuqianjin.pbq.step.DELETE_MEMBER";
    private final static String CIRCLE_EDIT = "com.paobuqianjin.pbq.step.EDIT_CIRCLE";
    private String changeName;
    private int memberNum = 0;

    TextView tvRedTipsMoney;

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
            if (!is_join) {
                LocalLog.d(TAG, "没有加入圈子，无法操作");
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
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.share_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.exit_text).setOnClickListener(onClickListener);
        popupOpWindowTop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popWindow dismiss() ");
                popupOpWindowTop = null;
            }
        });

        popupOpWindowTop.setFocusable(true);
        popupOpWindowTop.setOutsideTouchable(true);
        popupOpWindowTop.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindowTop.showAsDropDown(barTvRight, 20, -10);
        popCircleOpBar.startAnimation(animationCircleType);
    }


    public void popPassWordEdit() {
        if (popupOpWindow != null) {
            popupOpWindow = null;
        }
        LocalLog.d(TAG, "popPassWordEdit() enter 弹出密码输入框");
        popCircleOpBar = View.inflate(getContext(), R.layout.pass_word_layout, null);
        popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindow = null;
            }
        });

        RelativeLayout relativeLayout = (RelativeLayout) popCircleOpBar.findViewById(R.id.pass_word_pop);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  LocalLog.d(TAG, "点击外围");
                                              }
                                          }
        );

        passEdit = (EditText) popCircleOpBar.findViewById(R.id.pass_edit);
        passEdit.setOnClickListener(onClickListener);
        cancelText = (TextView) popCircleOpBar.findViewById(R.id.cancel_text);
        cancelText.setOnClickListener(onClickListener);
        confirmText = (TextView) popCircleOpBar.findViewById(R.id.confirm_text);
        confirmText.setOnClickListener(onClickListener);


        popupOpWindow.setFocusable(true);
        popupOpWindow.setOutsideTouchable(true);
        popupOpWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupOpWindow.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
    }


    private void popRedPkgPick() {
        LocalLog.d(TAG, "popRedPkgPick() enter");
        popCircleOpBar = View.inflate(getContext(), R.layout.circle_red_pkg_window, null);
        popCircleRedPkg = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleRedPkg.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popCircleRedPkg = null;
            }
        });
        money = (TextView) popCircleOpBar.findViewById(R.id.rec_money);
        ImageView close = (ImageView) popCircleOpBar.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popCircleRedPkg.dismiss();
            }
        });
        popCircleRedPkg.setFocusable(true);
        popCircleRedPkg.setOutsideTouchable(true);
        popCircleRedPkg.setBackgroundDrawable(new BitmapDrawable());
        imageView = (ImageView) popCircleOpBar.findViewById(R.id.open_red_pkg);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "领取红包");
                int[] location = new int[2];
                LocalLog.d(TAG, " x = " + view.getWidth() + ",y = " + view.getHeight());
                final Rotate3dAnimation animation = new Rotate3dAnimation(0, 359, view.getWidth() / 2f, view.getHeight() / 2f, 30, true);
                animation.setDuration(500);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setFillAfter(true);
                view.setAnimation(animation);
                view.startAnimation(animation);
                PostCircleRedPkgParam postCircleRedPkgParam = new PostCircleRedPkgParam();
                postCircleRedPkgParam.setCircleid(circleId);
                Presenter.getInstance(getContext()).postCircleRedPkg(postCircleRedPkgParam);
            }
        });


        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popCircleRedPkg.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    private void popQuitConfirm() {
        LocalLog.d(TAG, "popQuitConfirm() enter 退圈确认");
        popCircleOpBar = View.inflate(getContext(), R.layout.quit_circle_confirm, null);
        popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindow = null;
            }
        });

        cancelText = (TextView) popCircleOpBar.findViewById(R.id.cancel_quit_text);
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消退圈动作");
                popupOpWindow.dismiss();
            }
        });
        confirmText = (TextView) popCircleOpBar.findViewById(R.id.confirm_quit_text);
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginOutParam loginOutParam = new LoginOutParam();
                loginOutParam.setCircleid(circleId);
                Presenter.getInstance(getContext()).loginOutCircle(loginOutParam);
            }
        });


        popupOpWindow.setFocusable(true);
        popupOpWindow.setOutsideTouchable(true);
        popupOpWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupOpWindow.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    public void popRedPkgButton() {
        LocalLog.d(TAG, "popRedPkgButton() 弹出红包");
        View popCircleOpBarHori = View.inflate(getContext(), R.layout.red_pkg_button_pop_window, null);
        PopupWindow popOpWindowRedButton = new PopupWindow(popCircleOpBarHori,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBarHori.findViewById(R.id.red_pkg_button).setOnClickListener(onClickListener);
        popOpWindowRedButton.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popRedPkgButton dismiss() ");
//                popOpWindowRedButton = null;
            }
        });

//        popOpWindowRedButton.setFocusable(true);
//        popOpWindowRedButton.setOutsideTouchable(true);
        popOpWindowRedButton.setBackgroundDrawable(new BitmapDrawable());
        TranslateAnimation animationCircleTypeHori = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleTypeHori.setInterpolator(new AccelerateInterpolator());
        animationCircleTypeHori.setDuration(200);


        popOpWindowRedButton.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.RIGHT, 0, 25);
        popCircleOpBarHori.startAnimation(animationCircleTypeHori);
    }

    private void popAdminSelect() {
        popCircleOpBar = View.inflate(getContext(), R.layout.top_share_span, null);
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.share_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.editor_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.mananger_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.cancle_text).setOnClickListener(onClickListener);
        popupOpWindowTop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindowTop = null;
            }
        });

        popupOpWindowTop.setFocusable(true);
        popupOpWindowTop.setOutsideTouchable(true);
        popupOpWindowTop.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindowTop.showAsDropDown(barTvRight, 20, -10);
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
                position = intent.getIntExtra(getContext().getPackageName() + "position", -1);

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
        stepRecycler.setHasFixedSize(true);
        stepRecycler.setNestedScrollingEnabled(false);

        memberNumDes = (TextView) viewRoot.findViewById(R.id.member_num_des);
        imageButton = (RelativeLayout) viewRoot.findViewById(R.id.image_button);
        imageButton.setOnClickListener(onClickListener);
        scanMore = (TextView) viewRoot.findViewById(R.id.scan_more);
        scanMore.setOnClickListener(onClickListener);
        setToolBarListener(toolBarListener);
        desc = (TextView) viewRoot.findViewById(R.id.desc);
        moneyRet = (TextView) viewRoot.findViewById(R.id.money_ret);
        circleCover = (ImageView) viewRoot.findViewById(R.id.circle_cover);

        joinIn = (Button) viewRoot.findViewById(R.id.join_in);
        reChargeBt = (Button) viewRoot.findViewById(R.id.re_charge_bt);
        reChargeBt.setOnClickListener(onClickListener);
        rankMoney = (RelativeLayout) viewRoot.findViewById(R.id.rank_money);
        tvRedTipsMoney = (TextView) viewRoot.findViewById(R.id.tv_red_tips_money);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_button:
                    LocalLog.d(TAG, "进入爱心榜单行情");
                    Intent intent = new Intent();
                    intent.putExtra(getActivity().getPackageName() + "circle_detail", rechargeRankBundleData);
                    intent.setAction(ACTION_LOVE_RANK);
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
                        popupOpWindowTop.dismiss();
                    }
                    break;
                case R.id.editor_text:
                    LocalLog.d(TAG, "编辑");
                    if (circleDetailResponse != null) {
                        Intent intentEdit = new Intent();
                        intentEdit.setClass(getContext(), EditCircleActivity.class);
                        intentEdit.putExtra("circle_detail", circleDetailResponse.getData());
                        startActivityForResult(intentEdit, REQUEST_EDIT);
                        popupOpWindowTop.dismiss();
                    }
                    break;
                case R.id.mananger_text:
                    LocalLog.d(TAG, "成员管理");
                    Intent intentMember = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(CIRCLE_ID, String.valueOf(circleDetailResponse.getData().getId()));
                    intentMember.putExtra(getContext().getPackageName(), bundle);
                    intentMember.setClass(getContext(), MemberManagerActivity.class);
                    intentMember.setAction(MEMBER_MANANGER_ACTION);
                    startActivityForResult(intentMember, REQUEST_MEMBER);
                    popupOpWindowTop.dismiss();
                    break;
                case R.id.cancle_text:
                    LocalLog.d(TAG, "解散");
                    Presenter.getInstance(getContext()).deleteCircle(circleId);
                    popupOpWindowTop.dismiss();
                    break;
                case R.id.exit_text:
                    LocalLog.d(TAG, "退出");
                    popQuitConfirm();
                    popupOpWindowTop.dismiss();
                    break;
                case R.id.join_in:
                    LocalLog.d(TAG, "点击加入圈子");
                    if (joinCircleParam == null) {
                        joinCircleParam = new JoinCircleParam();

                    }
                    joinCircleParam.setCircleid(circleId);
                    if (is_password) {
                        LocalLog.d(TAG, "需要密码");
                        popPassWordEdit();
                        return;
                    }
                    Presenter.getInstance(getContext()).joinCircle(joinCircleParam);
                    break;
                case R.id.confirm_text:
                    LocalLog.d(TAG, "确定");
                    if (popupOpWindow != null) {
                        joinCircleParam.setPassword(passEdit.getText().toString());
                        popupOpWindow.dismiss();
                    }
                    Presenter.getInstance(getContext()).joinCircle(joinCircleParam);
                    break;
                case R.id.cancel_text:
                    LocalLog.d(TAG, "取消");
                    if (popupOpWindow != null) {
                        popupOpWindow.dismiss();
                    }
                    break;
                case R.id.re_charge_bt:
                    LocalLog.d(TAG, "创建成功,跳转支付");
                    if (circleId != -1) {
                        Bundle bundlePay = new Bundle();
                        bundlePay.putString(CIRCLE_ID, String.valueOf(circleId));
                        bundlePay.putString(PAY_FOR_STYLE, "circle");
                        bundlePay.putString(CIRCLE_NAME, circleDetailResponse.getData().getName());
                        bundlePay.putString(CIRCLE_LOGO, circleDetailResponse.getData().getLogo());
                        Intent intentPay = new Intent();
                        intentPay.setAction(PAY_RECHARGE);
                        intentPay.setClass(getContext(), PaoBuPayActivity.class);
                        intentPay.putExtra(getActivity().getPackageName(), bundlePay);
                        startActivityForResult(intentPay, REQUEST_RECHARGE);
                    }
                    break;
                case R.id.red_pkg_button:
                    LocalLog.d(TAG, "点击领取红包按钮");
//                    popOpWindowRedButton.dismiss();
                    popRedPkgPick();
                    break;
                case R.id.scan_more:
                    LocalLog.d(TAG, "查看更多");
                    Intent intentStep = new Intent();
                    intentStep.setAction(ACTION_STEP_RANK);
                    intentStep.putExtra(getActivity().getPackageName() + "circle_detail", stepBundleData);
                    intentStep.setClass(getContext(), LoveRankActivity.class);
                    startActivity(intentStep);
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
                is_join = true;
                //TODO 通知源UI更新
                if (circleId != -1) {
                    Presenter.getInstance(getContext()).getCircleStepRank(circleId, pageIndex, PAGESIZE);
                }
            } else if (joinCircleResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getMessage().equals("请输入密码")) {
                popPassWordEdit();
            } else {
                ToastUtils.showShortToast(getContext(), errorCode.getMessage());
            }
        }
    };


    private UiStepAndLoveRankInterface uiStepAndLoveRankInterface = new UiStepAndLoveRankInterface() {
        @Override
        public void response(ReChargeRankResponse reChargeRankResponse) {
            LocalLog.d(TAG, "ReChargeRankResponse() ");
            if (reChargeRankResponse.getError() == 0) {
                if (rankRecycler == null) {
                    return;
                }
                rankRecycler.setAdapter(new RechargeRankSimpleAdapter(getContext(), reChargeRankResponse.getData().getData()));
                rechargeRankBundleData = new RechargeRankBundleData(
                        (ArrayList<ReChargeRankResponse.DataBeanX.DataBean>)
                                reChargeRankResponse.getData().getData());

            } else if (reChargeRankResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(StepRankResponse stepRankResponse) {
            LocalLog.d(TAG, "StepRankResponse() ");
            if (stepRankResponse.getError() == 0) {
                if (stepRecycler == null) {
                    return;
                }
                stepRecycler.setAdapter(new RankAdapter(getContext(), stepRankResponse.getData().getData()));
                String sAgeFormat = mContext.getResources().getString(R.string.member_total);
                String sFinalMember = String.format(sAgeFormat, stepRankResponse.getData().getPagenation().getTotalCount());
                memberNumDes.setText(sFinalMember);
                if (memberNum != 0 && stepRankResponse.getData().getPagenation().getTotalCount() != memberNum) {
                    LocalLog.d(TAG, "人数有变化");
                    Intent intent = new Intent();
                    intent.setAction(DELETE_MEMBER);
                    if (position != -1) {
                        intent.putExtra(mContext.getPackageName() + "position", position);
                        intent.putExtra(mContext.getPackageName() + "memberNum", stepRankResponse.getData().getPagenation().getTotalCount());
                    }
                    getActivity().setResult(RESULT_OK, intent);
                }
                memberNum = stepRankResponse.getData().getPagenation().getTotalCount();
                stepBundleData = new StepBundleData(
                        (ArrayList<StepRankResponse.DataBeanX.DataBean>)
                                stepRankResponse.getData().getData());
            } else if (stepRankResponse.getError() == -100) {
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
                is_join = false;
            } else if (circleDetailResponse.getData().getIs_join() == 1) {
                is_join = true;
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
                tvRedTipsMoney.setVisibility(View.VISIBLE);
            }
            if (circleDetailResponse.getData().getIs_pwd() == 1) {
                is_password = true;
            }
            if (!TextUtils.isEmpty(titleStr) && !titleStr.equals(circleDetailResponse.getData().getName())) {
                LocalLog.d(TAG, "圈子名称更改");
                changeName = titleStr;
                if (position != -1) {
                    Intent intent = new Intent();
                    if (!TextUtils.isEmpty(changeName)) {
                        intent.setAction(CIRCLE_EDIT);
                        intent.putExtra(mContext.getPackageName() + "position", position);
                        intent.putExtra(mContext.getPackageName() + "changeName", circleDetailResponse.getData().getName());
                        getActivity().setResult(RESULT_OK, intent);
                    }
                }
            }
            titleStr = circleDetailResponse.getData().getName();
            if (circleDetailResponse.getData().getIs_recharge() == 1) {
                rankMoney.setVisibility(View.VISIBLE);
            }
            Log.d(TAG, "titleStr = " + titleStr);
            setTitle(titleStr);
            /*Presenter.getInstance(getContext()).getImage(circleCover, circleDetailResponse.getData().getLogo());*/
            Presenter.getInstance(getContext()).getCircleRechargeRand(circleId, pageIndex, PAGESIZE);
            Presenter.getInstance(getContext()).getCircleStepRank(circleId, pageIndex, PAGESIZE);

            if (circleDetailResponse.getData().getIs_red_packet() == 1) {
                LocalLog.d(TAG, "弹出红包,用户可以点击领取");
                popRedPkgButton();
                desc.setVisibility(View.VISIBLE);
                moneyRet.setVisibility(View.VISIBLE);
            } else {
                desc.setVisibility(View.INVISIBLE);
                moneyRet.setVisibility(View.INVISIBLE);
            }
        } else if (circleDetailResponse.getError() == -1) {
            Toast.makeText(getContext(), circleDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (circleDetailResponse.getError() == 1) {

        } else if (circleDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }


    @Override
    public void response(LoginOutResponse loginOutResponse) {
        LocalLog.d(TAG, "LoginOutResponse()  enter");
        if (loginOutResponse.getError() == 0) {
            //TODO 刷新上一层界面
            Intent intent = new Intent();
            intent.setAction(QUIT_ACTION);
            if (position != -1) {
                intent.putExtra(mContext.getPackageName() + "position", position);
            }
            getActivity().setResult(RESULT_OK, intent);
            getActivity().finish();
        }
    }

    @Override
    public void response(PostRevRedPkgResponse postRevRedPkgResponse) {
        LocalLog.d(TAG, "PostRevRedPkgResponse() enter " + postRevRedPkgResponse.toString());

        if (postRevRedPkgResponse.getError() == 0) {
            if (imageView != null) {
                imageView.clearAnimation();
                imageView.setVisibility(View.GONE);
                if (money != null) {
                    if (postRevRedPkgResponse.getData() != null) {
                        money.setText(String.valueOf("￥ " + postRevRedPkgResponse.getData().getAmount()));
                    }
                }
                //popCircleRedPkg.dismiss();
            }
        }
    }

    @Override
    public void response(DeleteCircleResponse deleteCircleResponse) {
        LocalLog.d(TAG, "DeleteCircleResponse() enter" + deleteCircleResponse.toString());
        if (deleteCircleResponse.getError() == 0) {
            Toast.makeText(getContext(), deleteCircleResponse.getMessage(), Toast.LENGTH_SHORT).show();
            //TODO 通知上一层UI更新
            Intent intent = new Intent();
            intent.setAction(DELETE_ACTION);
            if (position != -1) {
                intent.putExtra(mContext.getPackageName() + "position", position);
            }
            getActivity().setResult(RESULT_OK, intent);
            getActivity().finish();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_MEMBER) {
                    LocalLog.d(TAG, "执行过成员删除操作!");
                    if (circleId != -1) {
                        Presenter.getInstance(getContext()).getCircleStepRank(circleId, pageIndex, PAGESIZE);
                    }
                } else if (requestCode == REQUEST_EDIT) {
                    LocalLog.d(TAG, "编辑过圈子");
                    if (circleId != -1) {
                        Presenter.getInstance(getContext()).getCircleDetail(circleId);
                    }
                } else if (requestCode == REQUEST_RECHARGE) {
                    if (circleId != -1) {
                        Presenter.getInstance(getContext()).getCircleDetail(circleId);
                    }
                }
                break;
            default:
                break;
        }
    }
}
