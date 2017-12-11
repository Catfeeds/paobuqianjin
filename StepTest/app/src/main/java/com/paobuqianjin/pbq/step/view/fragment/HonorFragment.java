package com.paobuqianjin.pbq.step.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.BaseFragment;

/**
 * Created by pbq on 2017/12/1.
 */

public final class HonorFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.honor_page;
    }
}
