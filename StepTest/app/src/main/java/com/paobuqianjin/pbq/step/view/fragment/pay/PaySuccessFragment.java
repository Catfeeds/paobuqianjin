package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
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
    private View popRedPkgView;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;

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
        String payAction = Presenter.getInstance(getContext()).getTradeStyle();
        if ("gvip".equals(payAction)) {
            goldenSponsorSuccess(viewRoot);
        }
    }

    private void goldenSponsorSuccess(View view) {
        if (!isAdded()) {
            return;
        }
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            return;
        }
        popRedPkgView = View.inflate(getContext(), R.layout.golden_sponsor_success, null);
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
            }
        });
        final TextView wxCode = popRedPkgView.findViewById(R.id.wx_code);
        LinearLayout linearLayout = (LinearLayout) popRedPkgView.findViewById(R.id.copy);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData textClipData = ClipData.newPlainText("Label", wxCode.getText().toString().trim());
                cmb.setPrimaryClip(textClipData);
                LocalLog.d(TAG, "  msg = " + cmb.getText());
                PaoToastUtils.showLongToast(getActivity(), "微信号复制成功");
            }
        });

        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        popRedPkgView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRedPkgWindow.dismiss();
            }
        });

        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupRedPkgWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.repay_button)
    public void onClick() {
        LocalLog.d(TAG, "已确认支付结果");
        String payAction = Presenter.getInstance(getContext()).getTradeStyle();
        LocalLog.d(TAG, "payAction =  " + payAction);
        if ("user".equals(payAction) || "task".equals(payAction) || "vip".equals(payAction) || "redpacket".equals(payAction)
                || "cvip".equals(payAction) || "gvip".equals(payAction) || "red_map".equals(payAction) || "comm_id".equals(payAction)) {
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        } else {
            if (getActivity() instanceof PaoBuPayActivity) {
                ((PaoBuPayActivity) getActivity()).showQrCodeFragment(this);
            }
        }

    }
}
