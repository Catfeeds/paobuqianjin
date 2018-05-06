package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BaseCancelConfirmBarActivity;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorTimeSelectActivity;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.widgets.WheelTimePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorTimeActivity extends BaseCancelConfirmBarActivity implements BaseCancelConfirmBarActivity.ToolBarListener {

    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.work_time_des)
    TextView workTimeDes;
    @Bind(R.id.time_pan)
    RelativeLayout timePan;
    @Bind(R.id.view_start_time)
    WheelTimePicker viewStartTime;
    @Bind(R.id.view_end_time)
    WheelTimePicker viewEndTime;
    private final static int REQUEST_TIME = 0;
    private Intent intent;
    private String sponsor_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_time_fg);
        ButterKnife.bind(this);
        setToolBarListener(this);
        intent = getIntent();
        sponsor_time = intent.getStringExtra("time");
        workTimeDes.setText(TextUtils.isEmpty(sponsor_time) ? "请选择" : sponsor_time);
        String startTime = intent.getStringExtra("startTime");
        if (!TextUtils.isEmpty(startTime)) {
            viewStartTime.setSelectedTime(startTime);
        }
        String endTime = intent.getStringExtra("endTime");
        LocalLog.d("---", endTime);
        if (!TextUtils.isEmpty(endTime)) {
            viewEndTime.setSelectedTime(endTime);
        }
    }

    @OnClick({R.id.time_pan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_pan: {
                Intent intent = new Intent();
                intent.setClass(this, SponsorDateActivity.class);
                intent.putExtra("data", sponsor_time);
                startActivityForResult(intent, REQUEST_TIME);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode <= 0) {
            return;
        }
        sponsor_time = intent.getStringExtra("data");
        workTimeDes.setText(TextUtils.isEmpty(sponsor_time) ? "请选择" : sponsor_time);
    }


    @Override
    protected String title() {
        return "营业时间";
    }

    @Override
    public Object right() {
        return "确定";
    }

    @Override
    public Object left() {
        return "取消";
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        if ("请选择".equals(workTimeDes.getText().toString())) {
            onBackPressed();
            return;
        }
      /*  if (viewStartTime.getCurrentItemPosition() >= viewEndTime.getCurrentItemPosition()) {
            ToastUtils.showShortToast(this, "结束时间不能小于开始时间");
            return;
        }*/
        intent.putExtra("startTime", viewStartTime.getCurrentTime());
        intent.putExtra("endTime", viewEndTime.getCurrentTime());
        intent.putExtra("time", sponsor_time);
        setResult(SponsorInfoActivity.RES_TIME, intent);
        finish();
    }
}
