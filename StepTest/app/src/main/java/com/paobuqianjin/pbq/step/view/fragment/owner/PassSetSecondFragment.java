package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
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
        titlePayFirst.setText(getString(R.string.pay_pass_title_first));
        firstPageDes.setText(getString(R.string.pay_pass_try_again));
        pswView = (GridPasswordView) viewRoot.findViewById(R.id.pswView);
        confirmButton = (Button) viewRoot.findViewById(R.id.confirm_button);
        confirmButton.setEnabled(false);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalLog.d(TAG, "设置密码!");
                if (pswNew.equals(pswOlder)) {
                    Map<String, String> params = new HashMap<>();
                    Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlSettingPass, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            ToastUtils.showShortToast(getContext(), "密码设置成功");
                            getActivity().finish();
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            if (errorBean != null) {
                                if (errorBean.getError() == 100) {
                                    exitTokenUnfect();
                                } else {
                                    ToastUtils.showShortToast(getContext(), errorBean.getMessage());
                                }
                            }
                        }
                    });
                } else {
                    ToastUtils.showShortToast(getContext(), "两次输入的密码不一致");
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
                LocalLog.d(TAG, "psw = " + pswOlder);
                pswNew = pws;
                if (!pws.equals(pswOlder)) {
                    confirmButton.setBackground(getDrawableResource(R.drawable.rect_angle_background));
                    confirmButton.setEnabled(true);
                }
            }
        });
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                ((PayPassWordActivity) getActivity()).showFirstSetPassWord();
            }

            @Override
            public void clickRight() {

            }
        });
    }

    public void setPws(String pws) {
        this.pswOlder = pws;
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
