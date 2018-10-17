package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

/**
 * Created by pbq on 2018/10/16.
 */

public class ConsumRedFragment extends BaseFragment {
    private String title = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.consum_red_fg;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
