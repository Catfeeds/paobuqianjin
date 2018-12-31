package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExPubAdapter;
import com.paobuqianjin.pbq.step.view.fragment.exchange.ExPubFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExReleaseHisActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String TAG = ExReleaseHisActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    private ExPubFragment exPubFragment, exPubFragmentDown;

    @Override
    protected String title() {
        return "我发布的";
    }

    @Override
    public Object right() {
        return "已下架宝贝";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_release_his_activity_layout);
        ButterKnife.bind(this);
        barTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalLog.d(TAG, "我的发布");
                getSupportFragmentManager().beginTransaction()
                        .hide(exPubFragmentDown)
                        .show(exPubFragment)
                        .commitAllowingStateLoss();
            }
        });
        setToolBarListener(this);
        exPubFragment = new ExPubFragment();
        exPubFragment.setType(0);
        exPubFragmentDown = new ExPubFragment();
        exPubFragmentDown.setType(1);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, exPubFragment)
                .add(R.id.container, exPubFragmentDown)
                .hide(exPubFragmentDown)
                .show(exPubFragment)
                .commit();
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        LocalLog.d(TAG, "已下架");
        getSupportFragmentManager().beginTransaction()
                .hide(exPubFragment)
                .show(exPubFragmentDown)
                .commitAllowingStateLoss();
    }
}
