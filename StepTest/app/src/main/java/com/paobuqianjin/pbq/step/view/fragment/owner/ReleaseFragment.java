package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/25.
 */

public class ReleaseFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = ReleaseFragment.class.getSimpleName();

    @Override
    protected int getLayoutResId() {
        return R.layout.release_detail_fg;
    }

    @Override
    protected String title() {
        return "任务详情";
    }
}
