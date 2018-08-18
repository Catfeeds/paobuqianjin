package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.model.UserInfo;

/**
 * create by gavin 2018年8月8日16:12:39
 */
public class SetRemarkNameActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    @Bind(R.id.editText)
    EditText editText;
    private String userId;

    @Override
    protected String title() {
        return getString(R.string.set_remark);
    }

    @Override
    public Object right() {
        return getString(R.string.finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_remark_name);
        ButterKnife.bind(this);

        userId = getIntent().getStringExtra("userid");
        setToolBarListener(this);
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        final String remarkStr = editText.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("follow_id", userId);
        params.put("remark_name", remarkStr);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.setRemarkName, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showShortToast(SetRemarkNameActivity.this, "设置成功");
                setResult(RESULT_OK, getIntent().putExtra("intent_remark", remarkStr));
                finish();
            }
        });
    }
}
