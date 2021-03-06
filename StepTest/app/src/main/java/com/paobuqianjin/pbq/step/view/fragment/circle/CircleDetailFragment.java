package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CirclePassDialog;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DeleteCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostRevRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.table.ChatGroupInfo;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.view.activity.ChangeCircleBannerActivity;
import com.paobuqianjin.pbq.step.view.activity.EditCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.LoveRankActivity;
import com.paobuqianjin.pbq.step.view.activity.MemberManagerActivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.RechargeRankSimpleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.rong.imlib.model.Conversation;

import static android.app.Activity.RESULT_OK;

/**
 * 重写圈子详情UI
 * Created by pbq on 2018/11/1.
 */

public class CircleDetailFragment extends BaseBarImageViewFragment {
    private final static String TAG = CircleDetailFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.circle_cover)
    ImageView circleCover;
    @Bind(R.id.circle_obj_des)
    TextView circleObjDes;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.money_ret)
    TextView moneyRet;
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
    @Bind(R.id.re_charge_bt)
    Button reChargeBt;
    @Bind(R.id.rank_money)
    RelativeLayout rankMoney;
    @Bind(R.id.line_rank_step)
    ImageView lineRankStep;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.scan_more)
    TextView scanMore;
    @Bind(R.id.member_num_des)
    TextView memberNumDes;
    @Bind(R.id.step_recycler)
    SwipeMenuRecyclerView stepRecycler;
    @Bind(R.id.step_rank)
    RelativeLayout stepRank;
    @Bind(R.id.join_in)
    Button joinIn;
    @Bind(R.id.btn_chat)
    Button btnChat;
    @Bind(R.id.frame_bottom)
    FrameLayout frameBottom;

    private boolean is_password = false;
    private int circleId = -1;
    private LinearLayoutManager reChargeLayoutManager;
    private LinearLayoutManager stepLayoutManager;
    private RechargeRankBundleData rechargeRankBundleData;
    private String target;
    /**
     * 圈子余额
     */
    private float total_money;
    /**
     * 每日红包总金额
     */
    private float daily_red_pack_total;
    /**
     * 已领红包金额
     */
    private float red_pack_money;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private PopupWindow popupOpWindowTop;
    private PopupWindow popOpWindowRedButton;
    private PopupWindow popCircleRedPkg;
    private TranslateAnimation animationCircleType;
    private ImageView imageView;
    private TextView pop_money;
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
    private final static String ACTION_RED_RECORD = "com.paobuqianjin.pbq.step.RED_RECORD_ACTION";
    private final int REQUEST_MEMBER = 201;
    private final int REQUEST_EDIT = 202;
    private final int REQUEST_RECHARGE = 333;
    private final int REQUEST_CHANGE_BANNER = 334;
    String titleStr = "";
    private boolean is_join = false;
    private boolean isRecharge = false;
    private final static int PAGESIZE = 10;
    private int pageIndex, pageIndexStep, pageCount = 0;
    private int position = -1;
    private final static String QUIT_ACTION = "com.paobuqianjin.pbq.step.QUIT";
    private final static String DELETE_ACTION = "com.paobuqianjin.pbq.step.DELETE_CIRCLE";
    private final static String DELETE_MEMBER = "com.paobuqianjin.pbq.step.DELETE_MEMBER";
    private final static String CIRCLE_EDIT = "com.paobuqianjin.pbq.step.EDIT_CIRCLE";
    private final static String ADD_CIRCLE = "com.paobuqianjin.pbq.step.ADD_CIRCLE";
    private String changeName;
    private int memberNum = 0;

    TextView tvRedTipsMoney;
    private TextView pop_pkg_des;//领红包的界面底部红包解释
    private PopupWindow popOpWindowRedButtonHori;
    private TextView pop_message_red_a;
    private TextView pop_message_red_b;
    private RankAdapter rankAdapter;
    private ArrayList<StepRankResponse.DataBeanX.DataBean> stepData = new ArrayList<>();
    private CirclePassDialog circlePassDialog;
    EditText passEdit;
    TextView cancelText;
    TextView confirmText;
    private CircleDetailResponse circleDetailResponse = null;

    @Override
    protected String title() {
        return "";
    }

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.exit);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_detail_fg;
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
            //waitdelete
//            RongYunChatUtils.getInstance().chatTo(getActivity(), Conversation.ConversationType.GROUP,circleId+"",titleStr);
            if (circleDetailResponse.getData().getIs_admin() == 1) {
                LocalLog.d(TAG, "管理员界面");
                popAdminSelect();
            } else {
                popNoAdminSelect();
            }

        }
    };

    private void popAdminSelect() {
        popCircleOpBar = View.inflate(getContext(), R.layout.top_share_span_linear, null);
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = (LinearLayout) popCircleOpBar.findViewById(R.id.share_text);
        linearLayout.setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.editor_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.mananger_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.cancle_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.record_text).setOnClickListener(onClickListener);
        if (!isRecharge) {
            popCircleOpBar.findViewById(R.id.record_text).setVisibility(View.GONE);
            popCircleOpBar.findViewById(R.id.line_0).setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, 15, layoutParams.rightMargin, layoutParams.bottomMargin);
            linearLayout.setLayoutParams(layoutParams);
        }
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


        popupOpWindowTop.showAsDropDown(barTvRight, 20, -20);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    private void popNoAdminSelect() {
        LocalLog.d(TAG, "popNoAdminSelect() enter");
        popCircleOpBar = View.inflate(getContext(), R.layout.top_share_no_admin_linear, null);
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.exit_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.record_text).setOnClickListener(onClickListener);
        LinearLayout linearLayout = (LinearLayout) popCircleOpBar.findViewById(R.id.share_text);
        linearLayout.setOnClickListener(onClickListener);
        if (!isRecharge) {
            popCircleOpBar.findViewById(R.id.record_text).setVisibility(View.GONE);
            popCircleOpBar.findViewById(R.id.line_0).setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, 15, layoutParams.rightMargin, layoutParams.bottomMargin);
            linearLayout.setLayoutParams(layoutParams);
        }
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
                quitCircle(circleId);
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

    private void quitCircle(final int circleId) {
        Map<String, String> param = new HashMap<>();
        param.put("circleid", String.valueOf(circleId));
        param.put("userid", String.valueOf(Presenter.getInstance(getActivity()).getId()));
        Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlCircleMember + "/signOutCirscle", param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                PaoToastUtils.showLongToast(getContext(), "退出成功");
                //TODO 刷新上一层界面
                Intent intent = new Intent();
                intent.setAction(QUIT_ACTION);
                if (position != -1) {
                    intent.putExtra(getActivity().getPackageName() + "position", position);
                }
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.record_text:
                    LocalLog.d(TAG, "领取记录!");
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    Intent intentRed = new Intent();
                    intentRed.setAction(ACTION_RED_RECORD);
                    if (circleId != -1) {
                        intentRed.putExtra(getActivity().getPackageName() + "circle_detail", circleId);
                    }
                    intentRed.setClass(getContext(), LoveRankActivity.class);
                    startActivity(intentRed);
                    break;
                case R.id.image_button:
                    LocalLog.d(TAG, "进入爱心榜单行情");
                    final Intent intent = new Intent();
                    intent.putExtra(getActivity().getPackageName() + "circle_detail", rechargeRankBundleData);
                    intent.setAction(ACTION_LOVE_RANK);
                    intent.setClass(getContext(), LoveRankActivity.class);
                    startActivity(intent);
                    break;
                case R.id.share_text:
                    LocalLog.d(TAG, "分享");
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
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
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    if (circleDetailResponse != null) {
                        Intent intentEdit = new Intent();
                        intentEdit.setClass(getContext(), EditCircleActivity.class);
                        intentEdit.putExtra("circle_detail", circleDetailResponse.getData());
                        startActivityForResult(intentEdit, REQUEST_EDIT);
                    }
                    break;
                case R.id.mananger_text:
                    LocalLog.d(TAG, "成员管理");
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    Intent intentMember = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(CIRCLE_ID, String.valueOf(circleDetailResponse.getData().getId()));
                    intentMember.putExtra(getContext().getPackageName(), bundle);
                    intentMember.setClass(getContext(), MemberManagerActivity.class);
                    intentMember.setAction(MEMBER_MANANGER_ACTION);
                    startActivityForResult(intentMember, REQUEST_MEMBER);
                    break;
                case R.id.cancle_text:
                    LocalLog.d(TAG, "解散");
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    deleteCircle(circleId);
                    break;
                case R.id.exit_text:
                    LocalLog.d(TAG, "退出");
                    popQuitConfirm();
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    break;
                case R.id.join_in:
                    LocalLog.d(TAG, "点击加入圈子");
                    if (is_password) {
                        LocalLog.d(TAG, "需要密码");
                        popPassWordEdit();
                        return;
                    } else {
                        joinCircle(circleId, null);
                    }
                    break;
                case R.id.btn_chat:
                    LocalLog.d(TAG, "点击进入聊天");
                    RongYunChatUtils.getInstance().chatTo(getActivity(), Conversation.ConversationType.GROUP, circleId + "", circleDetailResponse.getData().getName());
                    break;
                case R.id.confirm_text:
                    LocalLog.d(TAG, "确定");
                    if (popupOpWindow != null) {
                        popupOpWindow.dismiss();
                    }
                    joinCircle(circleId, passEdit.getText().toString());
                    break;
                case R.id.cancel_text:
                    LocalLog.d(TAG, "取消");
                    if (popupOpWindow != null) {
                        popupOpWindow.dismiss();
                    }
                    break;
                case R.id.re_charge_bt:
                    LocalLog.d(TAG, "创建成功,跳转支付");
                    if (circleDetailResponse != null && circleDetailResponse.getData().getIs_join() == 0) {
                        PaoToastUtils.showShortToast(getActivity(), "请先加入社群");
                        return;
                    }
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
                    if (circleId != -1) {
                        intentStep.putExtra(getActivity().getPackageName() + "circle_detail", circleId);
                    }
                    intentStep.setClass(getContext(), LoveRankActivity.class);
                    startActivity(intentStep);
                    break;
                case R.id.circle_cover:
                    if (circleDetailResponse == null || circleId == -1 || circleDetailResponse.getData().getIs_admin() == 0)
                        return;
                    new AlertDialog.Builder(getActivity())
                            .setItems(new String[]{getString(R.string.change_circle_banner)}, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        Intent intentChangeBanner = new Intent(getActivity(), ChangeCircleBannerActivity.class);
                                        intentChangeBanner.putExtra("intentImgUrl", circleDetailResponse.getData().getChat_banner().get(0).getUrl());
                                        intentChangeBanner.putExtra("intentGroupId", circleId + "");
                                        startActivityForResult(intentChangeBanner, REQUEST_CHANGE_BANNER);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .create().show();

                    break;
                default:
                    break;
            }
        }
    };


    private void joinCircle(final int circleId, String psw) {
        Map<String, String> param = new HashMap<>();
        param.put("circleid", String.valueOf(circleId));
        if (!TextUtils.isEmpty(psw))
            param.put("password", psw);
        param.put("userid", String.valueOf(Presenter.getInstance(getContext()).getId()));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlCircleMember, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    joinIn.setVisibility(View.GONE);
                    btnChat.setVisibility(View.VISIBLE);
                    PaoToastUtils.showLongToast(getContext(), "加入成功");
                    is_join = true;
                    //TODO 通知源UI更新
                    if (circleId != -1) {
                        getCircleDetail(circleId);
                    }
                    Intent intent = new Intent();
                    if (position != -1) {
                        intent.putExtra(getActivity().getPackageName() + "position", position);
                    }
                    intent.setAction(ADD_CIRCLE);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getActivity(), errorBean.getMessage());
                }
            }
        });

    }

    private void deleteCircle(int circleId) {
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "&userid=" + String.valueOf(Presenter.getInstance(getActivity()).getId());
        Presenter.getInstance(getActivity()).deletePaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    DeleteCircleResponse deleteCircleResponse = new Gson().fromJson(s, DeleteCircleResponse.class);
                    PaoToastUtils.showLongToast(getActivity(), "解散成功");
                    Intent intent = new Intent();
                    intent.setAction(DELETE_ACTION);
                    if (position != -1) {
                        intent.putExtra(getActivity().getPackageName() + "position", position);
                    }
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {
                    if (errorBean != null) {
                        PaoToastUtils.showLongToast(getActivity(), errorBean.getMessage());
                    }
                }
            }
        });
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
        pop_money = (TextView) popCircleOpBar.findViewById(R.id.rec_money);
        pop_pkg_des = (TextView) popCircleOpBar.findViewById(R.id.pkg_des);
        pop_message_red_a = (TextView) popCircleOpBar.findViewById(R.id.message_red_a);
        pop_message_red_b = (TextView) popCircleOpBar.findViewById(R.id.message_red_b);
        pop_pkg_des.setText(titleStr + "的红包");
        ImageView close = (ImageView) popCircleOpBar.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popCircleRedPkg != null) popCircleRedPkg.dismiss();
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
                reCirclePkg(circleId);
            }
        });

        if (circleDetailResponse.getData().getRed_packet_status() != 1) {//红包不能正常领取
            imageView.clearAnimation();
            imageView.setVisibility(View.GONE);
            //0步数未达标|1有红包|2红包已领完|3用户已经领过红包|4圈子余额不足
            switch (circleDetailResponse.getData().getRed_packet_status()) {
                case 0:
                    pop_message_red_a.setVisibility(View.INVISIBLE);
                    pop_message_red_b.setText("步数未达标，还需继续努力！");
                    break;
                case 2:
                    pop_message_red_a.setVisibility(View.INVISIBLE);
                    pop_message_red_b.setText("手慢了，红包已被领完");
                    break;
                case 3:
                    pop_message_red_a.setVisibility(View.VISIBLE);
                    pop_message_red_b.setText("今日达标红包");
                    pop_money.setText("¥" + circleDetailResponse.getData().getRed_packet_money());
                    break;
                case 4:
                    pop_message_red_a.setVisibility(View.INVISIBLE);
                    pop_message_red_b.setText("社群余额不足");
                    break;
            }
        }


    /*    if (!TextUtils.isEmpty(circleDetailResponse.getData().getRed_packet_money())) {
            imageView.clearAnimation();
            imageView.setVisibility(View.GONE);
            pop_money.setText(String.valueOf("¥ " + circleDetailResponse.getData().getRed_packet_money()));
        }
*/
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popCircleRedPkg.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    //recv circle pkg
    private void reCirclePkg(final int circleId) {
        Map<String, String> param = new HashMap<>();
        param.put("userid", String.valueOf(Presenter.getInstance(getActivity()).getId()));
        param.put("circleid", String.valueOf(circleId));
        Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlCircle + "/sendRedBag", param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    PostRevRedPkgResponse postRevRedPkgResponse = new Gson().fromJson(s, PostRevRedPkgResponse.class);
                    if (imageView != null) {
                        imageView.clearAnimation();
                        imageView.setVisibility(View.GONE);
                        if (pop_money != null) {
                            if (postRevRedPkgResponse.getData() != null) {
                                pop_money.setText(String.valueOf("¥ " + postRevRedPkgResponse.getData().getAmount()));
                                getCircleDetail(circleId);
                            }
                        }
                        //popCircleRedPkg.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded() && errorBean != null) {
                    if (imageView != null) {
                        imageView.clearAnimation();
                        imageView.setVisibility(View.GONE);
                        pop_message_red_a.setVisibility(View.INVISIBLE);
                        pop_message_red_b.setText(errorBean.getMessage());
                        getCircleDetail(circleId);
                    }
                    PaoToastUtils.showShortToast(getActivity(), errorBean.getMessage());
                }
            }
        });
    }

    public void popPassWordEdit() {
        if (circlePassDialog == null) {
            circlePassDialog = new CirclePassDialog(getActivity());
            circlePassDialog.setNoOnclickListener(new CirclePassDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    circlePassDialog.dismiss();
                }
            });

            circlePassDialog.setYesOnclickListener(new CirclePassDialog.onYesOnclickListener() {
                @Override
                public void onYesClick(String passWord) {
                    circlePassDialog.dismiss();
                    if (TextUtils.isEmpty(passWord)) {
                        return;
                    }
                    joinCircle(circleId, passWord);
                }
            });
        }
        if (!circlePassDialog.isShowing())
            circlePassDialog.show();
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
        if (popupOpWindow != null) {
            popupOpWindow.dismiss();
            popupOpWindow = null;
        }
        if (popOpWindowRedButtonHori != null) {
            popOpWindowRedButtonHori.dismiss();
            popOpWindowRedButtonHori = null;
        }
    }

    @Override
    protected void initView(View viewRoot) {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            LocalLog.d(TAG, "intent.getAction():" + intent.getAction());
            if (ACTION_ENTER_CIRCLE.equals(intent.getAction())) {
                LocalLog.d(TAG, "logo 进入");
                circleId = intent.getIntExtra(getContext().getPackageName() + "circleid", -1);
                position = intent.getIntExtra(getContext().getPackageName() + "position", -1);

            } else if (ACTION_SCAN_CIRCLE_ID.equals(intent.getAction())) {
                LocalLog.d(TAG, "扫码 进入");
                circleId = intent.getIntExtra(getContext().getPackageName() + "circleid", -1);
            } else {
                circleId = intent.getIntExtra(getContext().getPackageName() + "circleid", -1);
            }
        }

        circleObjDes = (TextView) viewRoot.findViewById(R.id.circle_obj_des);
        rankRecycler = (RecyclerView) viewRoot.findViewById(R.id.rank_recycler);
        stepRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.step_recycler);
        reChargeLayoutManager = new LinearLayoutManager(getContext());
        reChargeLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        stepLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        stepRecycler.setLayoutManager(stepLayoutManager);
        rankRecycler.setLayoutManager(reChargeLayoutManager);
        rankRecycler.addItemDecoration(new RechargeRankSimpleAdapter.SpaceItemDecoration(30));
        stepRecycler.setFocusableInTouchMode(false); //设置不需要焦点
        stepRecycler.requestFocus(); //设置焦点不需要
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        stepRecycler.addFooterView(loadMoreView); // 添加为Footer。
        stepRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        stepRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        stepRecycler.setHasFixedSize(true);
        stepRecycler.setNestedScrollingEnabled(false);
        rankAdapter = new RankAdapter(getActivity(), stepData);
        stepRecycler.setAdapter(rankAdapter);
        ((SimpleItemAnimator) stepRecycler.getItemAnimator()).setSupportsChangeAnimations(false);
        memberNumDes = (TextView) viewRoot.findViewById(R.id.member_num_des);
        imageButton = (RelativeLayout) viewRoot.findViewById(R.id.image_button);
        imageButton.setOnClickListener(onClickListener);
        scanMore = (TextView) viewRoot.findViewById(R.id.scan_more);
        scanMore.setOnClickListener(onClickListener);
        setToolBarListener(toolBarListener);
        desc = (TextView) viewRoot.findViewById(R.id.desc);
        moneyRet = (TextView) viewRoot.findViewById(R.id.money_ret);
        circleCover = (ImageView) viewRoot.findViewById(R.id.circle_cover);
        circleCover.setOnClickListener(onClickListener);

        joinIn = (Button) viewRoot.findViewById(R.id.join_in);
        btnChat = (Button) viewRoot.findViewById(R.id.btn_chat);
        reChargeBt = (Button) viewRoot.findViewById(R.id.re_charge_bt);
        reChargeBt.setOnClickListener(onClickListener);
        rankMoney = (RelativeLayout) viewRoot.findViewById(R.id.rank_money);
        tvRedTipsMoney = (TextView) viewRoot.findViewById(R.id.tv_red_tips_money);
        btnChat.setOnClickListener(onClickListener);
        if (circleId > 0) {
            getCircleDetail(circleId);
        }
    }

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            if (!isAdded())
                return;
            pageIndexStep++;
            if (pageCount == 0) {
                LocalLog.d(TAG, "第一次刷新");
            } else {
                if (pageIndexStep > pageCount) {
                    PaoToastUtils.showLongToast(getContext(), "没有更多内容");
                    stepRecycler.loadMoreFinish(false, true);
                    stepRecycler.setLoadMoreView(null);
                    stepRecycler.setLoadMoreListener(null);
                    return;
                }
                getCircleStepRank(pageIndexStep);
            }
        }
    };

    private void getCircleDetail(final int circleId) {
        String url = NetApi.urlCircle + "/" + String.valueOf(circleId) + "?userid=" + String.valueOf(Presenter.getInstance(getContext()).getId());
        LocalLog.d(TAG, "url = " + url);
        Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    CircleDetailResponse circleDel = new Gson().fromJson(s, CircleDetailResponse.class);
                    circleDetailResponse = circleDel;

                    if (circleDel.getData().getDisband() == 1) {
                        RongYunChatUtils.getInstance().removeConvertion(Conversation.ConversationType.GROUP, circleId + "", null);
                        NormalDialog normalDialog = new NormalDialog(getActivity());
                        normalDialog.setYesOnclickListener(getString(R.string.i_know), new NormalDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                getActivity().finish();
                            }
                        });
                        normalDialog.setBackpressDismiss(false);
                        normalDialog.setSingleBtn(true);
                        normalDialog.setMessage("该社群已被解散");
                        normalDialog.show();
                        return;
                    }
                    if (circleDel.getData().getIs_recharge() == 1) {
                        isRecharge = true;
                    }
                    if (circleDel.getData().getIs_join() == 0) {
                        LocalLog.d(TAG, "还没有加入圈子");
                        joinIn.setVisibility(View.VISIBLE);
                        joinIn.setOnClickListener(onClickListener);
                        is_join = false;
                    } else if (circleDel.getData().getIs_join() == 1) {
                        is_join = true;
                        btnChat.setVisibility(View.VISIBLE);
                        try {
                            RongYunUserInfoManager.getInstance().refreshGroupInfoDBAndCache(new ChatGroupInfo(circleDel.getData().getId() + ""
                                    , circleDel.getData().getName()
                                    , circleDel.getData().getLogo()));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    total_money = Float.parseFloat(circleDel.getData().getTotal_amount());
                    daily_red_pack_total = Float.parseFloat(circleDel.getData().getRed_packet_amount());
                    red_pack_money = Float.parseFloat(circleDel.getData().getRed_packet_money());

                    String sMoneyFormat = getActivity().getResources().getString(R.string.circle_total);
                    String sMoney = String.format(sMoneyFormat, total_money);
                    if (circleDel != null) {
                        String targetFormat = getResources().getString(R.string.target_step);
                        target = String.format(targetFormat, circleDel.getData().getTarget());
                    }
                    circleObjDes.setText(target);
                    moneyRet.setText(sMoney);
                    if (circleDel.getData().getRed_packet_status() == 4) {
                        LocalLog.d(TAG, "余额不足当天的红包");
                        if (circleDel.getData().getIs_recharge() == 1) {
                            tvRedTipsMoney.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (circleDel.getData().getIs_recharge() == 1) {
                            tvRedTipsMoney.setVisibility(View.GONE);
                        }
                    }
                    if (circleDel.getData().getIs_pwd() == 1) {
                        is_password = true;
                    }
                    if (!TextUtils.isEmpty(titleStr) && !titleStr.equals(circleDel.getData().getName())) {
                        LocalLog.d(TAG, "圈子名称更改");
                        changeName = titleStr;
                        if (position != -1) {
                            Intent intent = new Intent();
                            if (!TextUtils.isEmpty(changeName)) {
                                intent.setAction(CIRCLE_EDIT);
                                intent.putExtra(getActivity().getPackageName() + "position", position);
                                intent.putExtra(getActivity().getPackageName() + "changeName", circleDel.getData().getName());
                                getActivity().setResult(RESULT_OK, intent);
                            }
                        }
                    }
                    titleStr = circleDel.getData().getName();
                    if (circleDel.getData().getIs_recharge() == 1) {
                        rankMoney.setVisibility(View.VISIBLE);
                        desc.setVisibility(View.VISIBLE);
                        moneyRet.setVisibility(View.VISIBLE);
                        if (circleDel.getData().getIs_join() == 1
                                && circleDel.getData().getRed_packet_status() != 4) {
                            LocalLog.d(TAG, "弹出红包,用户可以点击领取");
                            popRedPkgButton();
                        }
                    } else {
                        rankMoney.setVisibility(View.GONE);
                        desc.setVisibility(View.INVISIBLE);
                        moneyRet.setVisibility(View.INVISIBLE);
                        tvRedTipsMoney.setVisibility(View.GONE);
                    }
                    Log.d(TAG, "titleStr = " + titleStr);
                    setTitle(titleStr);
                    if (circleDel.getData().getChat_banner().size() > 0) {
                        Presenter.getInstance(getContext()).getImage(circleCover, circleDel.getData().getChat_banner().get(0).getUrl());
                    }
                    getCircleRechargeRand(1);
                    getCircleStepRank(1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getActivity(), errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(getActivity(), "开小差了，请稍后再试！");
                }
            }
        });
    }

    private void getCircleRechargeRand(int page) {
        if (!isAdded())
            return;
        pageIndex = page;
        Map<String, String> param = new HashMap<>();
        param.put("circleid", String.valueOf(circleId));
        param.put("action", "recharge");
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGESIZE));
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlCircleRank, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    ReChargeRankResponse reChargeRankResponse = new Gson().fromJson(s, ReChargeRankResponse.class);
                    rankRecycler.setAdapter(new RechargeRankSimpleAdapter(getContext(), reChargeRankResponse.getData().getData()));
                    rechargeRankBundleData = new RechargeRankBundleData(
                            (ArrayList<ReChargeRankResponse.DataBeanX.DataBean>)
                                    reChargeRankResponse.getData().getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void getCircleStepRank(final int page) {
        if (!isAdded())
            return;
        pageIndexStep = page;
        Map<String, String> param = new HashMap<>();
        param.put("circleid", String.valueOf(circleId));
        param.put("action", "step");
        param.put("page", String.valueOf(pageIndexStep));
        param.put("pagesize", String.valueOf(PAGESIZE));
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlCircleRank, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    StepRankResponse stepRankResponse = new Gson().fromJson(s, StepRankResponse.class);
                    pageCount = stepRankResponse.getData().getPagenation().getTotalPage();
                    if (page == 1) {
                        stepData.clear();
                        rankAdapter.notifyDataSetChanged();
                        stepRecycler.scrollToPosition(0);
                    }
                    stepRecycler.loadMoreFinish(false, true);
                    if (stepRankResponse.getData() != null && stepRankResponse.getData().getData() != null && stepRankResponse.getData().getData().size() > 0) {
                        stepData.addAll(stepRankResponse.getData().getData());
                        rankAdapter.notifyItemRangeInserted(stepData.size() - stepRankResponse.getData().getData().size(),
                                stepRankResponse.getData().getData().size());
                    }
                    String sAgeFormat = getActivity().getResources().getString(R.string.member_total);
                    String sFinalMember = String.format(sAgeFormat, stepRankResponse.getData().getPagenation().getTotalCount());
                    memberNumDes.setText(sFinalMember);
                    if (memberNum != 0 && stepRankResponse.getData().getPagenation().getTotalCount() != memberNum) {
                        LocalLog.d(TAG, "人数有变化");
                        Intent intent = new Intent();
                        intent.setAction(DELETE_MEMBER);
                        if (position != -1) {
                            intent.putExtra(getActivity().getPackageName() + "position", position);
                            intent.putExtra(getActivity().getPackageName() + "memberNum", stepRankResponse.getData().getPagenation().getTotalCount());
                        }
                        getActivity().setResult(RESULT_OK, intent);
                    }
                    memberNum = stepRankResponse.getData().getPagenation().getTotalCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    public void popRedPkgButton() {
        LocalLog.d(TAG, "popRedPkgButton() 弹出红包");
        if (popOpWindowRedButtonHori == null) {
            View popCircleOpBarHori = View.inflate(getContext(), R.layout.red_pkg_button_pop_window, null);
            popOpWindowRedButtonHori = new PopupWindow(popCircleOpBarHori,
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popCircleOpBarHori.findViewById(R.id.red_pkg_button).setOnClickListener(onClickListener);
            popOpWindowRedButtonHori.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popRedPkgButton dismiss() ");
//                popOpWindowRedButton = null;
                }
            });
/*            popOpWindowRedButtonHori.setTouchInterceptor(new View.OnTouchListener() {

                int orgX, orgY;
                int offsetX, offsetY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    LocalLog.d(TAG, "onTouch() enter");
                    boolean response = false;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            LocalLog.d(TAG, "ACTION_DOWN");
                            orgX = (int) event.getRawX();
                            orgY = (int) event.getRawX();
                            LocalLog.d(TAG, "org(" + orgX + "," + offsetY + ")");
                            response = false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            LocalLog.d(TAG, "ACTION_MOVE");
                            offsetX = (int) event.getRawX();
                            offsetY = (int) event.getRawY();
                            LocalLog.d(TAG, "offset(" + offsetX + "," + offsetY + ")");
                            if (Math.abs(offsetX - orgX) > 20 && Math.abs(offsetY - orgY) > 20) {
                                popOpWindowRedButtonHori.update(offsetX, offsetY, -1, -1, true);
                            }
                            response = true;
                            break;
                        case MotionEvent.ACTION_UP:
                            LocalLog.d(TAG, "ACTION_UP");
                            if (Math.abs(offsetX) > 20 && Math.abs(offsetY) > 20) {
                                response = true;
                            } else {
                                response = false;
                            }
                            break;
                    }
                    return response;

                }
            });*/
//        popOpWindowRedButton.setFocusable(true);
//        popOpWindowRedButton.setOutsideTouchable(true);
            popOpWindowRedButtonHori.setBackgroundDrawable(new BitmapDrawable());
            TranslateAnimation animationCircleTypeHori = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleTypeHori.setInterpolator(new AccelerateInterpolator());
            animationCircleTypeHori.setDuration(200);

            popOpWindowRedButtonHori.showAsDropDown(scanMore, 0, 40);
/*            popOpWindowRedButtonHori.showAtLocation(getActivity().findViewById(R.id.circle_detail_fg), Gravity.BOTTOM | Gravity.RIGHT, 0, 400);*/
            popCircleOpBarHori.startAnimation(animationCircleTypeHori);
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
                        getCircleDetail(circleId);
                    }
                } else if (requestCode == REQUEST_EDIT) {
                    LocalLog.d(TAG, "编辑过圈子");
                    if (circleId != -1) {
                        getCircleDetail(circleId);
                    }
                } else if (requestCode == REQUEST_RECHARGE) {
                    if (circleId != -1) {
                        getCircleDetail(circleId);
                    }
                } else if (requestCode == REQUEST_CHANGE_BANNER) {
                    if (circleId != -1) {
                        getCircleDetail(circleId);
                    }
                }
                break;
            default:
                break;
        }
    }

}
