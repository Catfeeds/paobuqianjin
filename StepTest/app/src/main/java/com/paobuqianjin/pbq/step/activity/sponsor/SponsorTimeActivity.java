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
    private boolean[] data = new boolean[7];
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_time_fg);
        ButterKnife.bind(this);
        setToolBarListener(this);
        intent = getIntent();
        String sponsor_time = intent.getStringExtra("time");
        if (!TextUtils.isEmpty(sponsor_time)) {
            String[] times = sponsor_time.split(",");
            for (String time : times) {
                data[Integer.parseInt(time) - 1] = true;
            }
        }
        setDate(data);
        String startTime = intent.getStringExtra("startTime");
        if (!TextUtils.isEmpty(startTime)){
            viewStartTime.setSelectedTime(startTime);
        }
        String endTime = intent.getStringExtra("endTime");
        LocalLog.d("---",endTime);
        if (!TextUtils.isEmpty(endTime)){
            viewEndTime.setSelectedTime(endTime);
        }
    }

    @OnClick({R.id.time_pan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_pan: {
                Intent intent = new Intent();
                intent.setClass(this, SponsorDateActivity.class);
                intent.putExtra("data", data);
                startActivityForResult(intent, REQUEST_TIME);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        boolean[] intentData = intent.getBooleanArrayExtra("data");
        setDate(intentData);
    }

    private void setDate(boolean[] intentData) {
        String date = "";
        for (int i = 0; i < intentData.length; i++) {
            data[i] = intentData[i];
            if (intentData[i]) {
                if (!"".equals(date)) {
                    date += "，";
                }
                switch (i) {
                    case 0:
                        date += "周一";
                        break;
                    case 1:
                        date += "周二";
                        break;
                    case 2:
                        date += "周三";
                        break;
                    case 3:
                        date += "周四";
                        break;
                    case 4:
                        date += "周五";
                        break;
                    case 5:
                        date += "周六";
                        break;
                    case 6:
                        date += "周日";
                        break;
                }
            }
        }
        workTimeDes.setText(TextUtils.isEmpty(date) ? "请选择" : date);
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
        String date = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i]) {
                if (!"".equals(date)) {
                    date += ",";
                }
                date += i + 1 + "";
            }
        }
        intent.putExtra("time", date);
        setResult(SponsorInfoActivity.RES_TIME, intent);
        finish();
    }
}
