package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jungly.gridpasswordview.GridPasswordView;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.PayPassWordActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/6/9.
 */

public class PassSetZeroFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PassSetZeroFragment.class.getSimpleName();
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
        titlePayFirst = (TextView) viewRoot.findViewById(R.id.title_pay_first);
        firstPageDes = (TextView) viewRoot.findViewById(R.id.first_page_des);
        titlePayFirst.setText(getString(R.string.pay_pass_reset_title));
        firstPageDes.setText(getString(R.string.pay_pass_reset_idtify));
        pswView = (GridPasswordView) viewRoot.findViewById(R.id.pswView);
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(final String psw) {
                LocalLog.d(TAG, "psw = " + psw);
                String base64Pass = Base64Util.makeUidToBase64(psw);
                Map<String, String> params = new HashMap<>();
                params.put("paypw", base64Pass);
                Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlPayPass, params, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            ErrorCode errorCode = new Gson().fromJson(s, ErrorCode.class);
                            if ("密码正确".equals(errorCode.getMessage())) {
                                ((PayPassWordActivity) getActivity()).showFirstSetPassWord(psw);
                            } else {

                            }
                        } catch (JsonSyntaxException j) {
                            LocalLog.d(TAG, "error data format!");
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                        if (errorBean != null && errorBean.getError() != 100) {
                            ToastUtils.showShortToast(getContext(), errorBean.getMessage());
                        }
                    }
                });
            }
        });
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
