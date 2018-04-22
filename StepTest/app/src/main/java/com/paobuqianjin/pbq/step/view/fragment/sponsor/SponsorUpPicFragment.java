package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Intent;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorUpPicFragment extends BaseBarStyleTextViewFragment {
    private final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    private final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_upload_fg;
    }

    @Override
    protected String title() {
        Intent intent = getActivity().getIntent();
        if (ACTION_INNER_PIC.equals(intent.getAction())) {
            return "门店照片";
        } else if (ACTION_OUT_PIC.equals(intent.getAction())) {
            return "店内环境";
        } else {
            return null;
        }
    }
}
