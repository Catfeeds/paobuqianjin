package com.paobuqianjin.pbq.step.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;


/**
 * Created by pbq on 2018/6/5.
 */

public class WalletPassDialog extends Dialog {
    TextView title;
    GridPasswordView pswView;
    TextView forgetPass;
    private PassEditListener passEditListener;
    private ForgetPassOnclickListener forgetPassOnclickListener;

    public WalletPassDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_pass_word);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        initView();
        initEvent();
        pswView = (GridPasswordView)findViewById(R.id.pswView);
    }

    public void clearPassword() {
        if (pswView != null) {
            pswView.clearPassword();
        }
    }

    private void initEvent() {
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                if (passEditListener != null) {
                    passEditListener.onPassWord(psw);
                }
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forgetPassOnclickListener != null) {
                    forgetPassOnclickListener.onForgetPassClick();
                }
            }
        });
    }


    public void setPassEditListener(PassEditListener passEditListener) {
        this.passEditListener = passEditListener;
    }

    public void setForgetPassOnclickListener(ForgetPassOnclickListener forgetPassOnclickListener) {
        this.forgetPassOnclickListener = forgetPassOnclickListener;
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        pswView = (GridPasswordView) findViewById(R.id.pswView);
        forgetPass = (TextView) findViewById(R.id.forget_pass);
    }

    public interface PassEditListener {
        void onPassWord(String pass);
    }

    public interface ForgetPassOnclickListener {
        void onForgetPassClick();
    }
}
