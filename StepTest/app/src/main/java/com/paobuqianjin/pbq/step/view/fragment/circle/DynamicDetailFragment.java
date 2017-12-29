package com.paobuqianjin.pbq.step.view.fragment.circle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class DynamicDetailFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.dynamic_detail_fg;
    }

    @Override
    protected String title() {
        return "动态详情";
    }
}
