package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/24.
 */

public class MyReleaseFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected String title() {
        return "我的发布";
    }

    @Override
    public Object right() {
        return "记录";
    }
}
