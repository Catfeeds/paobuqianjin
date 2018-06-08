package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PayPassWordActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/6/8.
 */

public class PassSetFirstFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PassSetFirstFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.pswView)
    GridPasswordView pswView;
    @Bind(R.id.title_pay_first)
    TextView titlePayFirst;
    @Bind(R.id.first_page_des)
    TextView firstPageDes;
    private NormalDialog normalDialog;

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
        firstPageDes.setText(getString(R.string.pay_des_first));
        pswView = (GridPasswordView) viewRoot.findViewById(R.id.pswView);
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                LocalLog.d(TAG, "psw = " + psw);
                ((PayPassWordActivity) getActivity()).showSenCondPassWord(psw);
            }
        });
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                if (normalDialog == null) {
                    normalDialog = new NormalDialog(getActivity());
                    normalDialog.setMessage("提现和支付需要钱包密码，确定要取消密码设置？");
                    normalDialog.setYesOnclickListener(getContext().getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            normalDialog.dismiss();
                            getActivity().finish();
                        }
                    });
                    normalDialog.setNoOnclickListener(getContext().getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            normalDialog.dismiss();
                        }
                    });
                }
                if (!normalDialog.isShowing() && isAdded()) {
                    normalDialog.show();
                }
            }

            @Override
            public void clickRight() {

            }
        });

    }

    public void cleanPassWord() {
        if (pswView != null) {
            pswView.clearPassword();
        }
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
