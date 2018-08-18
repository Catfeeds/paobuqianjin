package com.paobuqianjin.pbq.step.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/7/30.
 */

public class GetMoreMoneyActivity extends BaseBarActivity {
    @Bind(R.id.copy)
    TextView copy;

    @Override
    protected String title() {
        return "赚钱攻略";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_more_money_activity_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.copy)
    public void onClick() {
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData textClipData = ClipData.newPlainText("Label", "pbqj123456");
        cmb.setPrimaryClip(textClipData);
        PaoToastUtils.showLongToast(this, "微信号复制成功");
    }
}
