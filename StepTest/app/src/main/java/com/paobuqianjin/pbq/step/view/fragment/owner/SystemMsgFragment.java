package com.paobuqianjin.pbq.step.view.fragment.owner;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/23.
 */

public class SystemMsgFragment extends BaseBarStyleTextViewFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.system_msg_fg;
    }

    @Override
    protected String title() {
        return "系统消息";
    }
}
