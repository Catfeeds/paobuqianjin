package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/16.
 */

public class AboutActivity extends BaseBarActivity {
    private final static String USER_SERVICE_AGREEMENT_ACTION = "com.paobuqianjin.pbq.step.SERVICE_ACTION";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.app_logo)
    CircleImageView appLogo;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.version_des)
    RelativeLayout versionDes;
    @Bind(R.id.version_span)
    RelativeLayout versionSpan;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.introduce_span)
    RelativeLayout introduceSpan;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.protol_span)
    RelativeLayout protolSpan;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.copyright)
    TextView copyright;

    @Override
    protected String title() {
        return "关于我们";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        version = (TextView) findViewById(R.id.version);
        version.setText("当前版本 " + Utils.getVersionCode(this));
    }

    @OnClick(R.id.introduce_span)
    public void onClick() {
        startActivity(AgreementActivity.class, null, false, USER_SERVICE_AGREEMENT_ACTION);
    }
}
