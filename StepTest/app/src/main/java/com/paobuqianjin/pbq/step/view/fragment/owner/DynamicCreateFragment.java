package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/9.
 */

public class DynamicCreateFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected String title() {
        return "编辑动态";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dynamic_create_fg;
    }

    @Override
    public Object right() {
        return "发布";
    }
}
