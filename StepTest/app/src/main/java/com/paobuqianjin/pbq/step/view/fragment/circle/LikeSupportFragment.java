package com.paobuqianjin.pbq.step.view.fragment.circle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2017/12/30.
 */

public class LikeSupportFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = LikeSupportFragment.class.getSimpleName();

    @Override
    protected int getLayoutResId() {
        return R.layout.like_support_fg;
    }

    @Override
    protected String title() {
        return "点赞";
    }
}
