package com.paobuqianjin.pbq.step.view.fragment.circle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;


/**
 * Created by pbq on 2017/12/29.
 */

public class CircleDetailFragment extends BaseBarImageViewFragment {
    @Override
    protected String title() {
        return "圈子";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_detail_fg;
    }

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.home_s);
    }
}
