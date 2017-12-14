package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.BaseFragment;

/**
 * Created by pbq on 2017/12/11.
 */

public class AttentionCircleFragment extends BaseFragment {
    private final static String TAG = AttentionCircleFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        LocalLog.d(TAG, "getLayoutResId() layout R " + R.layout.attention_circle_fragment);
        return R.layout.attention_circle_fragment;
    }

    public String getTabLabel() {
        return "关注";
    }
}
