package com.paobuqianjin.pbq.step.view.fragment.login;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/25.
 */

public class ForgetPassFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.log_forget_password;
    }

    @Override
    protected String title() {
        return "忘记密码";
    }
}
