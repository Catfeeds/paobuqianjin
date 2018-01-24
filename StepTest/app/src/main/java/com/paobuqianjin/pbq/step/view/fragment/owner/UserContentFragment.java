package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/23.
 */

public class UserContentFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected String title() {
        return "评论";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.content_fg;
    }
}
