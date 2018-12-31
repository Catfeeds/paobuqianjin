package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

/**
 * Created by pbq on 2018/12/28.
 */

public class OrderActivity extends BaseBarActivity {
    @Override
    protected String title() {
        return "订单详情";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_order_detail_activity_layout);
    }

}
