package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorTMapActivity;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/12/28.
 */

public class AddExAddrActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.your_name)
    EditText yourName;
    @Bind(R.id.your_phone)
    EditText yourPhone;
    @Bind(R.id.detail_add)
    EditText detailAdd;
    @Bind(R.id.map_select)
    LinearLayout mapSelect;
    public static final int REQ_POSITION = 3;
    @Bind(R.id.target_address)
    TextView targetAddress;
    @Bind(R.id.code)
    EditText code;
    private String city;
    private String address;

    @Override
    protected String title() {
        return "添加地址";
    }

    @Override
    public Object right() {
        return "添加";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_add_address_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(this);
    }

    @Override
    public void clickRight() {
        addAddress();
    }

    private void addAddress() {
        if (TextUtils.isEmpty(yourName.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "收货人必填");
            return;
        }

        if (TextUtils.isEmpty(yourPhone.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "联系电话必填");
            return;
        }
        if (TextUtils.isEmpty(targetAddress.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "请选择所在地区");
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("consigner", yourName.getText().toString().trim());
        param.put("mobile", yourPhone.getText().toString().trim());
        param.put("addr", targetAddress.getText().toString().trim());
        if (!TextUtils.isEmpty(code.getText().toString().trim())) {
            param.put("zip_code", code.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(detailAdd.getText().toString().trim())) {
            param.put("address", detailAdd.getText().toString().trim());
        }
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlAddExAddress, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(AddExAddrActivity.this, "地址添加成功");
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(AddExAddrActivity.this, errorBean.getMessage());
                }
            }
        });
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @OnClick(R.id.map_select)
    public void onClick() {
        double latitude = Presenter.getInstance(this).getLocation()[0];
        double longitude = Presenter.getInstance(this).getLocation()[1];
        Intent intent = new Intent();
        intent.putExtra("lat", latitude);
        intent.putExtra("lng", longitude);
        intent.setClass(this, SponsorTMapActivity.class);
        startActivityForResult(intent, REQ_POSITION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_POSITION && data != null) {
            city = data.getStringExtra("city");
            if (TextUtils.isEmpty(data.getStringExtra("address"))) {
                targetAddress.setText(city);
            } else {
                address = data.getStringExtra("address");
                targetAddress.setText(address);
            }
        }
    }
}
