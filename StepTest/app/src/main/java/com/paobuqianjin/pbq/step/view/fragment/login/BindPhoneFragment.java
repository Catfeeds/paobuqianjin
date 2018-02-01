package com.paobuqianjin.pbq.step.view.fragment.login;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/31.
 */

public class BindPhoneFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = BindPhoneFragment.class.getSimpleName();

    @Override
    protected int getLayoutResId() {
        return R.layout.login_bind_phone_fg;
    }

    @Override
    protected String title() {
        return "绑定手机号";
    }
}
