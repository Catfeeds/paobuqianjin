package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

public class MyBankCardActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    @Override
    protected String title() {
        return "银行卡";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bank_card);
    }

    @Override
    protected void setRightValue(int id, Object obj) {
        super.setRightValue(id, obj);
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        startActivity(BindCardActivity.class, null);
    }

    @Override
    public Object right() {
        return R.mipmap.ic_add_white;
    }
}
