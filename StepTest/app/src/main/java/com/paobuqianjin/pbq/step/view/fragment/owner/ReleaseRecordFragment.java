package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/25.
 */

public class ReleaseRecordFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.release_record_fg;
    }

    @Override
    protected String title() {
        return "发布记录";
    }
}
