package com.paobuqianjin.pbq.step.view.fragment.circle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2017/12/20.
 */

public class CircleFlagFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = CircleFlagFragment.class.getSimpleName();


    public CircleFlagFragment() {
        super();
    }

    @Override
    protected String title() {
        return "添加标签";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_flag_fragment;
    }

    @Override
    public Object right() {
        return "完成";
    }
}
