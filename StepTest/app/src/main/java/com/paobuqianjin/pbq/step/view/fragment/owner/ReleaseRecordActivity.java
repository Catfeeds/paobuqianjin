package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/25.
 */
/*
@className :ReleaseRecordActivity
*@date 2018/1/25
*@author
*@description  任务发布记录
*/
public class ReleaseRecordActivity extends BaseBarActivity {
    private final static String TAG = ReleaseRecordActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.button_left)
    TextView buttonLeft;
    @Bind(R.id.button_right)
    TextView buttonRight;
    @Bind(R.id.record_tab)
    LinearLayout recordTab;
    @Bind(R.id.record_tab_container)
    LinearLayout recordTabContainer;
    private int mIndex = 0;
    private BaseFragment[] baseFragment;

    @Override
    protected String title() {
        return "发布记录";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_record_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        baseFragment = new BaseFragment[]{new ReleaseRecordFragment(), new ReleaseSponsorRecord()};
        buttonLeft = (TextView) findViewById(R.id.button_left);
        buttonRight = (TextView) findViewById(R.id.button_right);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.record_tab_container, baseFragment[0])
                .add(R.id.record_tab_container, baseFragment[1])
                .show(baseFragment[0])
                .commit();
        buttonRight.setTextColor(ContextCompat.getColor(ReleaseRecordActivity.this, R.color.color_6c71c4));
        buttonRight.setBackground(ContextCompat.getDrawable(ReleaseRecordActivity.this, R.drawable.rectangle_four_full_left_unselect));
        buttonLeft.setTextColor(ContextCompat.getColor(ReleaseRecordActivity.this, R.color.color_f8));
        buttonLeft.setBackground(ContextCompat.getDrawable(ReleaseRecordActivity.this, R.drawable.rectangle_four_full_r_selected));
    }

    private void updateSelect(int index) {
        LocalLog.d(TAG, "index =  " + index + ",mIndex =" + mIndex);
        if (index == 0) {
            buttonRight.setTextColor(ContextCompat.getColor(ReleaseRecordActivity.this, R.color.color_6c71c4));
            buttonRight.setBackground(ContextCompat.getDrawable(ReleaseRecordActivity.this, R.drawable.rectangle_four_full_left_unselect));
            buttonLeft.setTextColor(ContextCompat.getColor(ReleaseRecordActivity.this, R.color.color_f8));
            buttonLeft.setBackground(ContextCompat.getDrawable(ReleaseRecordActivity.this, R.drawable.rectangle_four_full_r_selected));
        } else if (index == 1) {
            buttonLeft.setTextColor(ContextCompat.getColor(ReleaseRecordActivity.this, R.color.color_6c71c4));
            buttonLeft.setBackground(ContextCompat.getDrawable(ReleaseRecordActivity.this, R.drawable.rectangele_four_full_r_unselected));
            buttonRight.setTextColor(ContextCompat.getColor(ReleaseRecordActivity.this, R.color.color_f8));
            buttonRight.setBackground(ContextCompat.getDrawable(ReleaseRecordActivity.this, R.drawable.rectangle_four_full_left_select));
        }
        if (index != mIndex) {
            getSupportFragmentManager().beginTransaction()
                    .hide(baseFragment[mIndex])
                    .show(baseFragment[index])
                    .commit();
        }
        mIndex = index;
    }

    @OnClick({R.id.button_left, R.id.button_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_left:
                updateSelect(0);
                break;
            case R.id.button_right:
                updateSelect(1);
                break;
        }
    }
}
