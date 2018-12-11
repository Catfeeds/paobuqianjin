package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DrawMoneyListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.AgreementActivity;
import com.paobuqianjin.pbq.step.view.activity.CrashActivity;
import com.paobuqianjin.pbq.step.view.activity.ForgetPayWordActivity;
import com.paobuqianjin.pbq.step.view.activity.IdentifedSetPassActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.GridMoneyAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/17.
 */

public class CrashFragment extends BaseBarStyleTextViewFragment implements CrashInterface {
    private final static String TAG = CrashFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.wechat_pay_icon)
    ImageView wechatPayIcon;
    @Bind(R.id.wx_dear_name)
    TextView wxDearName;
    @Bind(R.id.go_to_wx)
    ImageView goToWx;
    @Bind(R.id.wechat_pay)
    RelativeLayout wechatPay;
    @Bind(R.id.protocl_text)
    TextView protoclText;
    @Bind(R.id.protocl_pay)
    RelativeLayout protoclPay;
    @Bind(R.id.confirm_crash)
    Button confirmCrash;
    @Bind(R.id.select_icon)
    ImageView selectIcon;
    @Bind(R.id.wallet_money)
    TextView walletMoney;
    @Bind(R.id.crash_des)
    TextView crashDes;
    @Bind(R.id.grid_view)
    RongGridView gridView;
    @Bind(R.id.des_more)
    TextView desMore;
    private float canCrashNum;
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private final static int CRASH_PROTOCAL = 206;
    private boolean isauthWx;
    private ProgressDialog dialog;
    private String action = "";
    CrashToParam crashToParam;
    private NormalDialog passWordSetDialog;
    private WalletPassDialog walletPassDialog;
    GridMoneyAdapter gridMoneyAdapter;
    List<DrawMoneyListResponse.DataBean> listData = new ArrayList<>();
    private String crashMoney;//选择提现的金额
    List<String> rules = new ArrayList<>();

    @Override
    protected String title() {
        return "提现到微信";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.crash_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
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
        protoclText = (TextView) viewRoot.findViewById(R.id.protocl_text);
        confirmCrash = (Button) viewRoot.findViewById(R.id.confirm_crash);
        selectIcon = (ImageView) viewRoot.findViewById(R.id.select_icon);
        dialog = new ProgressDialog(getContext());
        wxDearName = (TextView) viewRoot.findViewById(R.id.wx_dear_name);
        walletMoney = (TextView) viewRoot.findViewById(R.id.wallet_money);
        gridView = (RongGridView) viewRoot.findViewById(R.id.grid_view);
        desMore = (TextView) viewRoot.findViewById(R.id.des_more);
        if (!Presenter.getInstance(getActivity()).getReadCrashProtocol(getActivity())) {
            LocalLog.d(TAG, "未阅读过提现协议");
            confirmCrash.setEnabled(false);
            confirmCrash.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rect_out_gray_shape));
            selectIcon.setImageDrawable(null);
        } else {
            LocalLog.d(TAG, "已阅读");
            confirmCrash.setEnabled(true);
            selectIcon.setImageResource(R.drawable.selected_icon);
        }
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            canCrashNum = Float.parseFloat(intent.getStringExtra("total"));
/*            String canCrashStrFormat = getActivity().getString(R.string.can_crash);
            String canCrashStr = String.format(canCrashStrFormat, canCrashNum);*/
            walletMoney.setText("钱包余额: " + String.valueOf(canCrashNum));
        }
        String part1 = "我已认真阅读", part2 = "《提现协议》";
        String protoclStr = part1 + part2;
        SpannableString spannableString = new SpannableString(protoclStr);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.color_007aff));
        spannableString.setSpan(colorSpan, part1.length(), protoclStr.length()
                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        protoclText.setText(spannableString);

        isauthWx = UMShareAPI.get(getContext()).isAuthorize(getActivity(), SHARE_MEDIA.WEIXIN);
        if (isauthWx) {
            UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
        }
        initGrid();
    }

    private void initGrid() {
        Map<String, String> param = new HashMap<>();
        param.put("userid", String.valueOf(Presenter.getInstance(getActivity()).getId()));
        Presenter.getInstance(getActivity()).getPaoBuSimple(NetApi.urlWithDrawList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) return;
                try {
                    DrawMoneyListResponse drawMoneyListResponse = new Gson().fromJson(s, DrawMoneyListResponse.class);
                    if (drawMoneyListResponse.getData() != null && drawMoneyListResponse.getData().size() > 0) {
                        for (int i = 0; i < drawMoneyListResponse.getData().size(); i++) {
                            if (i == 0) {
                                rules.addAll(drawMoneyListResponse.getData().get(0).getWithdraw_tips());
                            }
                            if (drawMoneyListResponse.getData().get(i).getIs_disable() == 0) {
                                crashMoney = drawMoneyListResponse.getData().get(i).getMoney();
                                drawMoneyListResponse.getData().get(i).setIs_select(true);
                                break;
                            }
                        }
                        listData.addAll(drawMoneyListResponse.getData());
                        gridMoneyAdapter = new GridMoneyAdapter(getActivity(), listData);
                        gridView.setAdapter(gridMoneyAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (!isAdded()) return;
                                if (position >= listData.size()) {
                                    return;
                                }
                                if (listData.get(position).getIs_disable() == 1) {
                                    LocalLog.d(TAG, "不可选中");
                                    return;
                                }
                                if (listData.get(position).isIs_select()) {
                                    listData.get(position).setIs_select(false);
                                    crashMoney = null;
                                    gridMoneyAdapter.notifyDataSetChanged();
                                } else {
                                    listData.get(position).setIs_select(true);
                                    crashMoney = listData.get(position).getMoney();
                                    for (int j = 0; j < listData.size(); j++) {
                                        if (j != position && listData.get(j).isIs_select()) {
                                            listData.get(j).setIs_select(false);
                                        }
                                    }
                                    gridMoneyAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                    if (rules.size() > 0) {
                        String ruleStr = "";
                        for (int line = 0; line < rules.size(); line++) {
                            ruleStr += String.valueOf(line + 1) + ". " + rules.get(line) + "\n";
                        }
                        desMore.setText(ruleStr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        UMShareAPI.get(getContext()).release();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    private void popPayPass() {
        if (walletPassDialog == null) {
            walletPassDialog = new WalletPassDialog(getActivity());
            walletPassDialog.setPassEditListener(new WalletPassDialog.PassEditListener() {
                @Override
                public void onPassWord(String pass) {
                    LocalLog.d(TAG, "pass =" + pass);
                    walletPassDialog.dismiss();
                    String base64Pass = Base64Util.makeUidToBase64(pass);
                    Map<String, String> params = new HashMap<>();
                    params.put("paypw", base64Pass);
                    Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlPayPass, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(s, ErrorCode.class);
                                if ("密码正确".equals(errorCode.getMessage())) {
                                    Presenter.getInstance(getContext()).postCrashTo(crashToParam);
                                } else {

                                }
                            } catch (JsonSyntaxException j) {
                                LocalLog.d(TAG, "error data format!");
                            }
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            if (errorBean.getError() != 100) {
                                PaoToastUtils.showShortToast(getContext(), errorBean.getMessage());
                            }
                        }
                    });
                }
            });

            walletPassDialog.setForgetPassOnclickListener(new WalletPassDialog.ForgetPassOnclickListener() {
                @Override
                public void onForgetPassClick() {
                    LocalLog.d(TAG, "忘记支付密码");
                    startActivity(ForgetPayWordActivity.class, null);
                }
            });
        }
        if (!walletPassDialog.isShowing() && !getActivity().isFinishing()) {
            walletPassDialog.clearPassword();
            walletPassDialog.show();
        }
    }

    @OnClick({R.id.wechat_pay, R.id.confirm_crash, R.id.protocl_pay, R.id.select_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat_pay:
                LocalLog.d(TAG, "绑定微信或者更换绑定的微信");
                if (!isauthWx) {
                    LocalLog.d(TAG, "授权");
                    UMShareAPI.get(getActivity()).doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                }
                break;
            case R.id.confirm_crash:
                LocalLog.d(TAG, "确认转出");
                action = "wx";
                if ("wx".equals(action)) {
                    if (crashToParam == null || TextUtils.isEmpty(crashToParam.getWx_openid())) {
                        PaoToastUtils.showLongToast(getContext(), "选择提现的微信");
                        return;
                    }
                }
                if (TextUtils.isEmpty(crashMoney)) {
                    LocalLog.d(TAG, "请选择提现金额!");
                    return;
                }
                try {
                    if (Float.parseFloat(crashMoney) > canCrashNum) {
                        PaoToastUtils.showLongToast(getActivity(), "余额不足！");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                crashToParam.setAmount(crashMoney);
                //TODO 判断是否设置过密码
                Presenter.getInstance(getActivity()).getPaoBuSimple(NetApi.urlPassCheck, null, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            jsonObj = jsonObj.getJSONObject("data");
                            String status = jsonObj.getString("setpw");
                            if (status.equals("1")) {
                                popPayPass();
                            } else if (status.equals("0")) {
                                if (passWordSetDialog == null) {
                                    passWordSetDialog = new NormalDialog(getActivity());
                                }
                                passWordSetDialog.setMessage("您还未设置支付密码，去上设置支付密码?");
                                passWordSetDialog.setYesOnclickListener("去设置", new NormalDialog.onYesOnclickListener() {
                                    @Override
                                    public void onYesClick() {
                                        startActivity(IdentifedSetPassActivity.class, null);
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                passWordSetDialog.setNoOnclickListener("不设置", new NormalDialog.onNoOnclickListener() {
                                    @Override
                                    public void onNoClick() {
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                if (!passWordSetDialog.isShowing())
                                    passWordSetDialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                    }
                });

                break;
            case R.id.protocl_pay:
                Intent intent = new Intent();
                intent.setClass(getActivity(), AgreementActivity.class);
                intent.setAction(CRASH_ACTION);
                startActivityForResult(intent, CRASH_PROTOCAL);
                break;
            case R.id.select_icon:
                boolean isCurrentProtocalPayState = Presenter.getInstance(getActivity()).getReadCrashProtocol(getActivity());
                isCurrentProtocalPayState = !isCurrentProtocalPayState;
                Presenter.getInstance(getActivity()).setReadCrashProtocol(getActivity(), isCurrentProtocalPayState);

                if (!isCurrentProtocalPayState) {
//                    LocalLog.d(TAG, "未阅读过提现协议");
                    confirmCrash.setEnabled(false);
                    confirmCrash.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rect_out_gray_shape));
                    selectIcon.setImageDrawable(null);
                } else {
//                    LocalLog.d(TAG, "已阅读");
                    confirmCrash.setEnabled(true);
                    confirmCrash.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rect_out_blue_shape));
                    selectIcon.setImageResource(R.drawable.selected_icon);
                }
                break;
        }
    }

    @Override
    public void response(BindCardListResponse bindCardListResponse) {
        LocalLog.d(TAG, "BindCardListResponse() enter " + bindCardListResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (bindCardListResponse.getError() == 0) {
        } else if (bindCardListResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), bindCardListResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void response(CrashResponse crashResponse) {
        LocalLog.d(TAG, "CrashResponse()  enter  " + crashResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (crashResponse.getError() == 0) {
            ((CrashActivity) getActivity()).showCrashResult(crashResponse);
        } else if (crashResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            PaoToastUtils.showLongToast(getActivity(), crashResponse.getMessage());
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {

        }
    }

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, "授权开始callback:" + share_media.toString());
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
            String temp = "";
            if (crashToParam == null) {
                crashToParam = new CrashToParam();
            }
            if (share_media.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {
                if (!isauthWx) {
                    UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, this);
                    isauthWx = true;
                    return;
                }
                for (String key : map.keySet()) {
                    temp = temp + key + ":" + map.get(key) + "\n";
                    switch (key) {
                        case "openid":
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_openid(map.get(key));
                            break;
                        case "screen_name":
                            wxDearName.setText(map.get(key));
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_nickname(map.get(key));
                            continue;
                        case "iconurl":
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_avatar(map.get(key));
                            continue;
                        case "province":
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_province(map.get(key));
                            continue;
                        case "city":
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_city(map.get(key));
                            continue;
                        case "gender":
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_sex(map.get(key));
                            continue;
                        case "unionid":
                            if (!TextUtils.isEmpty(map.get(key)))
                                crashToParam.setWx_unionid(map.get(key));
                            break;
                        default:
                            continue;
                    }
                }
                LocalLog.d(TAG, "tmp =" + temp);
                SocializeUtils.safeCloseDialog(dialog);
            }

        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(getContext()).onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CRASH_PROTOCAL) {
//                confirmCrash.setEnabled(true);
//                confirmCrash.setBackgroundColor(getResources().getColor(R.color.color_6c71c4));
//                selectIcon.setImageResource(R.drawable.selected_icon);
            }
        }
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

}
