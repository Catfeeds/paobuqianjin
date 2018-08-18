package com.paobuqianjin.pbq.step.view.base.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by Administrator on 2018/7/25.
 */

public class PaoBuProgressDialog extends ProgressDialog {
    private AnimationDrawable mAnimation;
    private Context mContext;
    // 显示的图片
    private ImageView mImageView;
    // 提示的文字
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int mResid;

    /**
     * loading对话框构造方法
     *
     * @param context
     *            上下文
     * @param content
     *            提示信息
     * @param resId
     *            资源
     */
    public PaoBuProgressDialog(Context context, String content, int resId) {
        super(context);
        this.mContext = context;
        this.mLoadingTip = content;
        this.mResid = resId;
//        this.showType = type;
        setCanceledOnTouchOutside(true);
    }
    public PaoBuProgressDialog(Context context) {
        super(context,R.style.MyDialog);
//        super(context);
        this.mContext = context;
        this.mLoadingTip = "正在加载中...";
        this.mResid = R.drawable.loading_icon;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        initView();
        initData();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        mLoadingTv = (TextView) findViewById(R.id.load_tetv);
        mImageView = (ImageView) findViewById(R.id.load_imgv);
    }

    /**
     * 数据初始化
     */
    private void initData() {
        mImageView.setBackgroundResource(mResid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
        mLoadingTv.setText(mLoadingTip);
    }

    /**
     * 设置提示信息内容
     *
     * @param str
     */
    public void setContent(String str) {
        mLoadingTv.setText(str);
    }
}
