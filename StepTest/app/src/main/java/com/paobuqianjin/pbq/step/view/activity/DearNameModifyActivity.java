package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutDearNameParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DearNameResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DearNameModifyInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/3/22.
 */

public class DearNameModifyActivity extends BaseActivity implements DearNameModifyInterface {
    private final static String TAG = DearNameModifyActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.dear_name)
    EditText dearName;
    private int id = -1;
    private final static int DEAR_NAME_MODIFY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_dear_name_layout);
        ButterKnife.bind(this);
        Presenter.getInstance(this).attachUiInterface(this);
    }

    @Override
    protected void initView() {
        super.initView();
        barTitle = (TextView) findViewById(R.id.bar_title);
        barTitle.setText("修改昵称");
        barTvRight = (TextView) findViewById(R.id.bar_tv_right);
        barTvRight.setText("保存");
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra(getPackageName(), -1);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
    }

    @OnClick({R.id.button_return_bar, R.id.bar_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_return_bar:
                LocalLog.d(TAG, "返回上一级");
                finish();
                break;
            case R.id.bar_tv_right:
                LocalLog.d(TAG, "修改昵称");
                PutDearNameParam putDearNameParam = new PutDearNameParam();
                if (id != -1 && !TextUtils.isEmpty(dearName.getText())) {
                    putDearNameParam
                            .setId(id)
                            .setAction("remark")
                            .setNickname(dearName.getText().toString());
                    Presenter.getInstance(this).modifyDearName(putDearNameParam);
                }
                break;
        }
    }

    @Override
    public void response(DearNameResponse dearNameResponse) {
        LocalLog.d(TAG, "dearNameResponse() enter " + dearNameResponse.toString());
        if (dearNameResponse.getError() == 0) {
            Intent intent = new Intent();
            setResult(DEAR_NAME_MODIFY, intent);
            finish();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(DearNameModifyActivity.this).setId(-1);
            Presenter.getInstance(DearNameModifyActivity.this).steLogFlg(false);
            Presenter.getInstance(DearNameModifyActivity.this).setToken(this, "");
            DearNameModifyActivity.this.finish();
            System.exit(0);
        }
    }
}
