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

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExToTrifResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/12/29.
 */
/*
@className :AddExTriffActivity
*@date 2018/12/29
*@author
*@description  录入快递单号
*/
public class AddExTriffActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String TAG = AddExAddrActivity.class.getSimpleName();
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
    @Bind(R.id.phone_name)
    TextView phoneName;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.select_addr)
    LinearLayout selectAddr;
    @Bind(R.id.trif_num)
    EditText trifNum;
    @Bind(R.id.scan_tif_num)
    ImageView scanTifNum;
    @Bind(R.id.trif_gs)
    EditText trifGs;
    @Bind(R.id.select_trif_gs)
    ImageView selectTrifGs;
    @Bind(R.id.trif_no)
    EditText trifNo;
    @Bind(R.id.remark_edit)
    EditText remarkEdit;
    @Bind(R.id.address)
    TextView address;
    private List<String> triNameList = new ArrayList<>();
    private Map<String, String> nameCode = new HashMap<>();
    private ChooseOneItemWheelPopWindow wheelPopWindow;
    private String comm_id;

    @Override
    protected String title() {
        return "录入单号";
    }

    @Override
    public Object right() {
        return "确定";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_add_order_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            comm_id = intent.getStringExtra("comm_order_id");
            if (!TextUtils.isEmpty(comm_id)) {
                getByErInfo(comm_id);
            } else {
                finish();
            }
        }
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        commit();
    }

    private void commit() {
        if (TextUtils.isEmpty(trifGs.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "快递公司名称必须填写");
            return;
        }

        if (TextUtils.isEmpty(trifNo.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "快递公司编号必须填");
            return;
        }
        if (TextUtils.isEmpty(trifNum.getText().toString().trim())) {
            PaoToastUtils.showLongToast(this, "快递单号");
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        param.put("company", trifGs.getText().toString().trim());
        param.put("company_code", trifNo.getText().toString().trim());
        param.put("express_no", trifNum.getText().toString().trim());
        if (!TextUtils.isEmpty(remarkEdit.getText().toString())) {
            param.put("remark", remarkEdit.getText().toString().trim());
        }

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExReleaseTr, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                setResult(Activity.RESULT_OK);
                PaoToastUtils.showLongToast(getApplicationContext(), "信息已更新");
                finish();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getApplicationContext(), errorBean.getMessage());
                }
            }
        });
    }

    private void getByErInfo(String comm_order_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_order_id);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExCommIdInfo, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExToTrifResponse exToTrifResponse = new Gson().fromJson(s, ExToTrifResponse.class);
                    phoneName.setText(exToTrifResponse.getData().getConsign_info().getBuyer_consigner());
                    phone.setText(exToTrifResponse.getData().getConsign_info().getBuyer_mobile());
                    address.setText(exToTrifResponse.getData().getConsign_info().getBuyer_addr() + exToTrifResponse
                            .getData().getConsign_info().getBuyer_address());
                    if (exToTrifResponse.getData().getExpress_list().size() > 0) {
                        for (int i = 0; i < exToTrifResponse.getData().getExpress_list().size(); i++) {
                            triNameList.add(exToTrifResponse.getData().getExpress_list().get(i).getName());
                            nameCode.put(exToTrifResponse.getData().getExpress_list().get(i).getName(),
                                    exToTrifResponse.getData().getExpress_list().get(i).getCode());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @OnClick(R.id.select_trif_gs)
    public void onClick() {
        LocalLog.d(TAG, "选择快递公司");
        if (wheelPopWindow == null && triNameList.size() > 0) {
            wheelPopWindow = new ChooseOneItemWheelPopWindow(this, triNameList);
            wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                @Override
                public void onItemSelectLis(String result) {
                    trifGs.setText(result);
                    trifNo.setText(nameCode.get(result));
                }
            });
        }
        if (wheelPopWindow.isShowing()) {
            wheelPopWindow.cancel();
            return;
        }
        wheelPopWindow.setCurrentSelectValue(trifGs.getText().toString());
        wheelPopWindow.show();
    }
}
