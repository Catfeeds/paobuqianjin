package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferResultActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    @Bind(R.id.iv_status)
    ImageView ivStatus;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    private boolean isSuccess;

    @Override
    protected String title() {
        return getIntent().getBooleanExtra("is_success", false)?getString(R.string.apply_suc):getString(R.string.apply_fal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_success);
        ButterKnife.bind(this);
        setToolBarListener(this);

        isSuccess = getIntent().getBooleanExtra("is_success", false);
        tvStatus.setText(isSuccess ? getString(R.string.apply_suc) : getString(R.string.apply_fal));
        ivStatus.setImageResource(isSuccess ? R.mipmap.ic_suc_check : R.mipmap.ic_fail_delect);
        if (isSuccess) {
            tvDesc.setText(R.string.transfer_do_by_3_day);
            tvDesc.setVisibility(View.VISIBLE);
        }else{
            tvDesc.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_confirm)
    public void onClick() {
        onBackPressed();
    }

    @Override
    public void clickLeft() {
        onBackPressed();
        return;
    }

    @Override
    public void clickRight() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MyWalletActivity.class);
        intent.putExtra("is_refresh", true);
        startActivity(intent);
    }
}
