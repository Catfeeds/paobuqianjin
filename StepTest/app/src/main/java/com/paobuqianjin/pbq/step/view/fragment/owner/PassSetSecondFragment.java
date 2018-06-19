package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.PayPassWordActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/6/8.
 */

public class PassSetSecondFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PassSetSecondFragment.class.getSimpleName();
    private final static String ACTION_FIRST_CARD = "com.paobuqianjin.pbq.step.ACTION_FIRST_CARD";
    private final static String ACTION_RESET = "com.paobuqianjin.pbq.step.ACTION_RESET";
    private final static String ACTION_RESET_BANK = "com.paobuqianjin.pbq.step.ACTION_RESET_BANK";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.title_pay_first)
    TextView titlePayFirst;
    @Bind(R.id.first_page_des)
    TextView firstPageDes;
    @Bind(R.id.pswView)
    GridPasswordView pswView;
    @Bind(R.id.confirm_button)
    Button confirmButton;
    private String pswOlder = "";
    private String pswNew = "";
    private String srcPsw = "";
    private String action = "";

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pass_word_setting_fg;
    }

    @Override
    protected void initView(View viewRoot) {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            action = intent.getAction();
        }
        titlePayFirst = (TextView) viewRoot.findViewById(R.id.title_pay_first);
        firstPageDes = (TextView) viewRoot.findViewById(R.id.first_page_des);
        titlePayFirst.setText(getString(R.string.pay_pass_title_first));
        firstPageDes.setText(getString(R.string.pay_pass_try_again));
        pswView = (GridPasswordView) viewRoot.findViewById(R.id.pswView);
        confirmButton = (Button) viewRoot.findViewById(R.id.confirm_button);
        confirmButton.setEnabled(false);
        confirmButton.setVisibility(View.VISIBLE);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalLog.d(TAG, "设置密码!");
                if (pswNew.equals(pswOlder)) {
                    if (ACTION_FIRST_CARD.equals(action)) {
                        //第一次认证绑卡设置密码
                        Map<String, String> params = new HashMap<>();
                        params.put("paypw", Base64Util.makeUidToBase64(pswNew));
                        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlSettingPass, params, new PaoCallBack() {
                            @Override
                            protected void onSuc(String s) {
                                PaoToastUtils.showShortToast(getContext(), "密码设置成功");
                                getActivity().finish();
                            }

                            @Override
                            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                                if (errorBean != null) {
                                    if (errorBean.getError() == 100) {
                                        exitTokenUnfect();
                                    } else {
                                        PaoToastUtils.showShortToast(getContext(), errorBean.getMessage());
                                    }
                                }
                            }
                        });
                    } else if (ACTION_RESET_BANK.equals(action)) {
                        //通过银行卡重置
                        Map<String, String> params = new HashMap<>();
                        params.put("paypw", Base64Util.makeUidToBase64(pswNew));
                        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlPowerModify, params, new PaoCallBack() {
                            @Override
                            protected void onSuc(String s) {
                                PaoToastUtils.showShortToast(getContext(), "密码设置成功");
                                getActivity().finish();
                            }

                            @Override
                            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                                if (errorBean != null) {
                                    if (errorBean.getError() == 100) {
                                        exitTokenUnfect();
                                    } else {
                                        PaoToastUtils.showShortToast(getContext(), errorBean.getMessage());
                                    }
                                }
                            }
                        });

                    } else if (ACTION_RESET.equals(action)) {
                        //通过旧密码重置
                        LocalLog.d(TAG, "srcPsw = " + srcPsw);
                        if (!TextUtils.isEmpty(srcPsw)) {
                            Map<String, String> params = new HashMap<>();
                            params.put("paypw", Base64Util.makeUidToBase64(pswNew));
                            params.put("oldpw", Base64Util.makeUidToBase64(srcPsw));
                            Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlPayPassModify, params, new PaoCallBack() {
                                @Override
                                protected void onSuc(String s) {
                                    PaoToastUtils.showShortToast(getContext(), "密码设置成功");
                                    getActivity().finish();
                                }

                                @Override
                                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                                    if (errorBean.getError() == 100) {
                                        exitTokenUnfect();
                                    } else {
                                        PaoToastUtils.showShortToast(getContext(), errorBean.getMessage());
                                    }
                                }
                            });
                        }
                    }
                } else {
                    PaoToastUtils.showShortToast(getContext(), "两次输入的密码不一致");
                    pswView.clearPassword();
                }
            }
        });
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String pws) {

                pswNew = pws;
                if (pws.equals(pswOlder)) {
                    confirmButton.setBackground(getDrawableResource(R.drawable.rect_angle_background));
                    confirmButton.setEnabled(true);
                }
            }
        });
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                ((PayPassWordActivity) getActivity()).showFirstSetPassWord(srcPsw);
            }

            @Override
            public void clickRight() {

            }
        });
    }

    public void setPws(String pws, String psw) {
        this.pswOlder = pws;
        srcPsw = psw;
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
    }
}
