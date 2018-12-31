package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

/**
 * Created by pbq on 2018/12/29.
 */
/*
@className :AddExTriffActivity
*@date 2018/12/29
*@author
*@description  录入快递单号
*/
public class AddExTriffActivity extends BaseBarActivity {
    @Override
    protected String title() {
        return "录入单号";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_add_order_activity_layout);
    }
}
