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
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExpayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/31.
 */

public class PayFailedFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PayFailedFragment.class.getSimpleName();
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
    @Bind(R.id.repay_button)
    Button repayButton;

    @Override
    protected String title() {
        return "支付结果";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pay_failed_fg;
    }

    @Override
    public Object right() {
        return "确定";
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {
            getActivity().onBackPressed();
        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "确定");
            getActivity().onBackPressed();
        }
    };

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
        setToolBarListener(toolBarListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.repay_button)
    public void onClick() {
        LocalLog.d(TAG, "重新支付!");
        try {
            if (getActivity() instanceof PaoBuPayActivity) {
                ((PaoBuPayActivity) getActivity()).showRePayFragment(PayFailedFragment.this);
            } else if (getActivity() instanceof ExpayActivity) {
                ((ExpayActivity) getActivity()).showRePayFragment(PayFailedFragment.this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
