package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.OlderPassChangeActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/3/31.
 */

public class PassAccountManagerFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PassAccountManagerFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.address_mail)
    ImageView addressMail;
    @Bind(R.id.phone_address)
    TextView phoneAddress;
    @Bind(R.id.pass_word_layer)
    RelativeLayout passWordLayer;
    @Bind(R.id.address_weichat)
    ImageView addressWeichat;
    @Bind(R.id.weichat_address)
    TextView weichatAddress;
    @Bind(R.id.address_weichat_layer)
    RelativeLayout addressWeichatLayer;
    @Bind(R.id.address_qq)
    ImageView addressQq;
    @Bind(R.id.qq_address)
    TextView qqAddress;
    @Bind(R.id.qq_address_layer)
    RelativeLayout qqAddressLayer;

    @Override
    protected int getLayoutResId() {
        return R.layout.bind_account_fg;
    }

    @Override
    protected String title() {
        return "账号";
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

    @OnClick({R.id.pass_word_layer, R.id.address_weichat_layer, R.id.qq_address_layer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pass_word_layer:
                LocalLog.d(TAG, "修改密码");
                startActivity(OlderPassChangeActivity.class, null);
                break;
            case R.id.address_weichat_layer:
                LocalLog.d(TAG, "绑定或者解绑");

                break;
            case R.id.qq_address_layer:
                LocalLog.d(TAG, "绑定或解绑");
                break;
        }
    }
}
