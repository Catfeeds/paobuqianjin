package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/3/31.
 */

public class PassAccountManagerFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.bind_account_fg;
    }

    @Override
    protected String title() {
        return "账号";
    }
}
