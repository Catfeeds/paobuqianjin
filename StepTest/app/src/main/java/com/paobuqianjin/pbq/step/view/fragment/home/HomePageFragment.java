package com.paobuqianjin.pbq.step.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.StepProcessDrawable;
import com.paobuqianjin.pbq.step.view.base.view.WaveView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/1.
 */

public final class HomePageFragment extends BaseFragment {
    @Bind(R.id.bg_home)
    ImageView bgHome;
    @Bind(R.id.process_step_now)
    View processStepNow;
    @Bind(R.id.toay_step)
    TextView toayStep;
    @Bind(R.id.mu_biao_text)
    TextView muBiaoText;
    @Bind(R.id.process_step)
    FrameLayout processStep;
    @Bind(R.id.wave_view)
    WaveView waveView;
    @Bind(R.id.wave_view_span)
    RelativeLayout waveViewSpan;
    @Bind(R.id.major_bg)
    ImageView majorBg;
    @Bind(R.id.major_span)
    RelativeLayout majorSpan;
    @Bind(R.id.home_title)
    TextView homeTitle;
    @Bind(R.id.home_page)
    RelativeLayout homePage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        processStepNow = (View) viewRoot.findViewById(R.id.process_step_now);
        processStepNow.setBackground(new StepProcessDrawable(getContext()).setmAngle(90));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
