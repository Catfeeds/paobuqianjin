package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

/**
 * Created by pbq on 2018/12/28.
 */

public class ExchangeOutActivity extends BaseBarActivity {
    @Override
    protected String title() {
        return "卖出管理";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_out_manager_activity_layout);
    }
}
