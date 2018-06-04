package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OnIdentifyLis;
import com.paobuqianjin.pbq.step.utils.LocalLog;
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
                Presenter.getInstance(this).getIdentifyStatu(this, new OnIdentifyLis() {
                    @Override
                    public void onIdentifed() {
                        Intent intent = getIntent();
                        intent.setClass(TransferActivity.this, TransferCardActivity.class);
                        startActivityForResult(intent, MyWalletFragment.REQUEST_CRASH);
                    }

                    @Override
                    public void onUnidentify() {
                        Intent intent = getIntent();
                        intent.setClass(TransferActivity.this, IdentityAuth1Activity.class);
                        startActivityForResult(intent, 1);
                    }

                    @Override
                    public void onGetIdentifyStatusError() {
                    }
                });
                break;
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d("TransferActivity","requestCode:"+requestCode+" resultCode:"+resultCode);
        setResult(resultCode);
        finish();
    }*/
}
