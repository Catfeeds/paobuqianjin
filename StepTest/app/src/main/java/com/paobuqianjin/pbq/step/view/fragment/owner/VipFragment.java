package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/4/25.
 */

public class VipFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.vip_fg;
    }

    @Override
    protected String title() {
        return "会员专享";
    }
}
