package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/31.
 */

public class PaySuccessFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PaySuccessFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.image_result)
    ImageView imageResult;
    @Bind(R.id.result_img)
    RelativeLayout resultImg;
    @Bind(R.id.pay_success_result)
    TextView paySuccessResult;
    @Bind(R.id.repay_button)
    Button repayButton;

    public float getPayNum() {
        return payNum;
    }

    public void setPayNum(float payNum) {
        this.payNum = payNum;
        if (paySuccessResult != null) {
            String payResult = String.format("你已成功付款%.2f元", payNum);
            paySuccessResult.setText(payResult);
        }
    }

    private float payNum;

    @Override
    protected String title() {
        return "支付结果";
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.pay_sucess_fg;
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
        super.initView(viewRoot);
        paySuccessResult = (TextView) viewRoot.findViewById(R.id.pay_success_result);
        String payResult = String.format("你已成功付款%.2f元", payNum);
        paySuccessResult.setText(payResult);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.repay_button)
    public void onClick() {
        LocalLog.d(TAG, "已确认支付结果");
        getActivity().finish();
    }
}
