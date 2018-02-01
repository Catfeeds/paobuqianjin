package com.paobuqianjin.pbq.step.view.fragment.login;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/2/1.
 */

public class PersonInfoSettingFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = PersonInfoSettingFragment.class.getSimpleName();

    @Override
    protected String title() {
        return "完善信息";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.person_message_fg;
    }
}
