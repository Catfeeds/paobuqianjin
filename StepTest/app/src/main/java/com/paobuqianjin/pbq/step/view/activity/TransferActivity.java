package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyWalletFragment;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends BaseBarActivity {

    @Override
    protected String title() {
        return "转出";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);

        getCurrentState();
    }

    private void getCurrentState() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.GET_PERSON_IDENTIFY_STATE, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String status = jsonObj.getString("status");
                    if (status.equals("0")) {
                        PaoToastUtils.showShortToast(TransferActivity.this, "当前用户还处于认证中，请稍后再试");
                        finish();
                        return;
                    } else if (status.equals("2")) {
                        PaoToastUtils.showShortToast(TransferActivity.this, "当前用户还处于认证中，请稍后再试");
                        finish();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showShortToast(TransferActivity.this, errorBean.getMessage());
                    finish();
                }
            }
        });
    }

    @OnClick({R.id.linear_wechat, R.id.linear_card})
    public void onClick(View view) {
        Intent intent = getIntent();
        switch (view.getId()) {
            case R.id.linear_wechat:
                intent.setClass(this, CrashActivity.class);
                startActivityForResult(intent, MyWalletFragment.REQUEST_CRASH);
                break;
            case R.id.linear_card:
                intent.setClass(this, TransferCardActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(resultCode);
        finish();
    }
}
