package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/25.
 */

public class ReleaseFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = ReleaseFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.target_step)
    TextView targetStep;
    @Bind(R.id.target_money)
    TextView targetMoney;
    @Bind(R.id.release_live_bg)
    RelativeLayout releaseLiveBg;
    @Bind(R.id.release_sample_des)
    TextView releaseSampleDes;
    @Bind(R.id.release_target)
    TextView releaseTarget;
    @Bind(R.id.release_live_money)
    TextView releaseLiveMoney;
    @Bind(R.id.release_live_days)
    TextView releaseLiveDays;
    @Bind(R.id.release_live_rules)
    TextView releaseLiveRules;
    @Bind(R.id.release_live_desc)
    TextView releaseLiveDesc;
    @Bind(R.id.flag_1)
    TextView flag1;
    @Bind(R.id.num_1)
    RelativeLayout num1;
    @Bind(R.id.flag_2)
    TextView flag2;
    @Bind(R.id.num_2)
    RelativeLayout num2;
    @Bind(R.id.flag_3)
    TextView flag3;
    @Bind(R.id.num_3)
    RelativeLayout num3;
    @Bind(R.id.get_release)
    TextView getRelease;
    @Bind(R.id.go_to)
    ImageView goTo;

    @Override
    protected int getLayoutResId() {
        return R.layout.release_detail_fg;
    }

    @Override
    protected String title() {
        return "任务详情";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
