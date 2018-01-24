package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/23.
 */

public class ContentEmptyFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = ContentEmptyFragment.class.getSimpleName();


    @Override
    protected int getLayoutResId() {
        return R.layout.content_empty_fg;
    }

    @Override
    protected String title() {
        return "评论";
    }
}
