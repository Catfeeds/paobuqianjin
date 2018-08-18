package com.paobuqianjin.pbq.step.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2018/7/12.
 */

public class CirclePassDialog extends Dialog {
    EditText passEdit;
    TextView cancelText;
    TextView confirmText;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public CirclePassDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_word_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        initView();
        initEvent();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                passEdit.setText(null);
            }
        });
    }

    private void initView() {
        passEdit = (EditText) findViewById(R.id.pass_edit);
        cancelText = (TextView) findViewById(R.id.cancel_text);
        confirmText = (TextView) findViewById(R.id.confirm_text);
    }


    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    String passWord = passEdit.getText().toString();
                    yesOnclickListener.onYesClick(passWord);

                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick(String password);
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {
        this.yesOnclickListener = onYesOnclickListener;
    }
}
