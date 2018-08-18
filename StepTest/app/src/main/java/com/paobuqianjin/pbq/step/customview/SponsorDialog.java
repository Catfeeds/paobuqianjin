package com.paobuqianjin.pbq.step.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2018/8/9.
 */

public class SponsorDialog extends Dialog {
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    private SpannableString messageSpann;


    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private TextView mMessageTv;
    private View divide;
    private boolean isSignle;
    private int gravity = -1;
    private int paddingLeft;
    private boolean isBackpressDismiss = true;//是否点击返回消失

    public SponsorDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
        if (no != null) no.setText(noStr);
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
        if (yes != null) yes.setText(yesStr);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return isBackpressDismiss ? super.onKeyDown(keyCode, event) : false;
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }
        if (messageSpann != null) {
            mMessageTv.setText(messageSpann);
        }

        if (messageStr != null) {
            mMessageTv.setText(messageStr);
        }
        if (gravity != -1) {
            mMessageTv.setGravity(gravity);
            mMessageTv.setPadding(paddingLeft, 0, 0, 0);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        divide = findViewById(R.id.divide);
        titleTv = (TextView) findViewById(R.id.title);
        mMessageTv = (TextView) findViewById(R.id.message);
        if (isSignle) {
            no.setVisibility(View.GONE);
            divide.setVisibility(View.GONE);
        }
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
        if (titleTv != null) titleTv.setText(titleStr);
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
        messageSpann = null;
        if (mMessageTv != null) mMessageTv.setText(messageStr);
    }

    public void setMessage(String message, int gravity, int paddingLeft) {
        messageStr = message;
        this.gravity = gravity;
        this.paddingLeft = paddingLeft;
        if (mMessageTv != null) {
            mMessageTv.setText(messageStr);
            mMessageTv.setGravity(gravity);
            mMessageTv.setPadding(paddingLeft, 0, 0, 0);
        }

    }

    public void setMessageSpann(SpannableString messageSpann) {
        this.messageSpann = messageSpann;
        messageStr = null;
    }


    public void setSingleBtn(boolean isSignle) {
        this.isSignle = isSignle;
    }

    public void setBackpressDismiss(boolean backpressDismiss) {
        isBackpressDismiss = backpressDismiss;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }
}
