package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/8/9.
 */

public class CircleGuideActivity extends Activity {
    private final static String TAG = CircleGuideActivity.class.getSimpleName();
    @Bind(R.id.circle_guide)
    ImageView circleGuide;
    @Bind(R.id.known_bts)
    Button knownBts;
    @Bind(R.id.go_bt)
    Button goBt;
    private final static String GUIDE_ACTION = "com.paobuqianjin.pbq.GUIDE_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
// 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
// 设置显示位置
        onWindowAttributesChanged(wl);
        setContentView(R.layout.circle_guide_activity_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.known_bts, R.id.go_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.known_bts:
                finish();
                SharedPreferencesUtil.put(Constants.CIRCLE_GUIDE, false);
                break;
            case R.id.go_bt:
                Intent intent = new Intent();
                intent.setClass(this, OwnerCircleActivity.class);
                intent.setAction(GUIDE_ACTION);
                startActivity(intent);
                finish();
                SharedPreferencesUtil.put(Constants.CIRCLE_GUIDE, false);
                break;
        }
    }
}
