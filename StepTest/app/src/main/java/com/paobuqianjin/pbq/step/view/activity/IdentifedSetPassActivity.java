package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/6/8.
 */

public class IdentifedSetPassActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.real_name)
    EditText realName;
    @Bind(R.id.id_number)
    EditText idNumber;
    @Bind(R.id.pass_word)
    EditText passWord;
    @Bind(R.id.pass_word_again)
    EditText passWordAgain;
    @Bind(R.id.button_confirm)
    Button buttonConfirm;
    private NormalDialog normalDialog;

    @Override
    protected String title() {
        return "设置密码";
    }

    @Override
    public void onBackPressed() {
        if (normalDialog == null) {
            normalDialog = new NormalDialog(this);
            normalDialog.setMessage("提现和支付需要钱包密码，确定要取消密码设置？");
            normalDialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    normalDialog.dismiss();
                    finish();
                }
            });
            normalDialog.setNoOnclickListener(getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    normalDialog.dismiss();
                }
            });
        }
        if (!normalDialog.isShowing() && !isFinishing()) {
            normalDialog.show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_ed_setpass_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        passWordAgain = (EditText) findViewById(R.id.pass_word_again);
        passWordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (realName.getText().toString().trim().length() >= 2
                        && idNumber.getText().toString().trim().length() >= 15
                        && idNumber.getText().toString().trim().length() <= 18
                        && passWord.getText().toString().length() == 6
                        && passWordAgain.getText().toString().length() == 6) {
                    buttonConfirm.setEnabled(true);
                } else {
                    buttonConfirm.setEnabled(false);
                }
            }
        });
    }

    @OnClick(R.id.button_confirm)
    public void onClick() {
        if (realName.getText().toString().trim().length() <= 1) {
            PaoToastUtils.showShortToast(getApplicationContext(), "请填写正确的姓名");
            return;
        }
        if (!(idNumber.getText().toString().trim().length() >= 15
                && idNumber.getText().toString().trim().length() <= 18)) {
            PaoToastUtils.showShortToast(getApplicationContext(), "请输入正确身份证号码");
            return;
        }
        if (!passWord.getText().toString().equals(passWordAgain.getText().toString())) {
            PaoToastUtils.showShortToast(getApplicationContext(), "两次输入的密码不一致");
            return;
        }
        String psw = passWordAgain.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("paypw", Base64Util.makeUidToBase64(psw));
        params.put("realname", realName.getText().toString().trim());
        params.put("idcard", idNumber.getText().toString().trim());
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlAddPassIdentify, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(getApplicationContext(), "密码设置成功!");
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getApplicationContext(), errorBean.getMessage());
                }
            }
        });
    }
}
