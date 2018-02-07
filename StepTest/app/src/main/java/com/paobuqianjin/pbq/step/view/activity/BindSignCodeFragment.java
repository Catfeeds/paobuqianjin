package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/7.
 */

public class BindSignCodeFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = BindSignCodeFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.phone_text)
    TextView phoneText;
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.sign_code_text)
    TextView signCodeText;
    @Bind(R.id.sign_code)
    EditText signCode;
    @Bind(R.id.sign_code_get)
    TextView signCodeGet;
    @Bind(R.id.line2)
    ImageView line2;
    @Bind(R.id.confirm)
    Button confirm;
    @Bind(R.id.phone_nums)
    TextView phoneNums;

    @Override
    protected int getLayoutResId() {
        return R.layout.bind_wx_phone_sign_code_fg;
    }

    @Override
    protected String title() {
        return "绑定微信";
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
        LocalLog.d(TAG, "initView() enter ");
        phoneNums = (TextView) viewRoot.findViewById(R.id.phone_nums);
        phoneNums.setText(Presenter.getInstance(getContext()).getMobile(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.sign_code_get, R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_code_get:
                LocalLog.d(TAG, "获取验证码");
                break;
            case R.id.confirm:
                LocalLog.d(TAG, "下一步");
                break;
        }
    }
}
