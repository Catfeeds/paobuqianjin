package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExInOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExOutOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TriResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExTrAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExTriffActivity extends BaseBarActivity {
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
    @Bind(R.id.status_str)
    TextView statusStr;
    @Bind(R.id.comm_no)
    TextView commNo;
    @Bind(R.id.compy_name)
    TextView compyName;
    @Bind(R.id.tri_no)
    TextView triNo;
    @Bind(R.id.copy)
    TextView copy;
    @Bind(R.id.recycler_msg)
    SwipeMenuRecyclerView recyclerMsg;
    private ExOutOrderResponse.DataBeanX.DataBean dataBean;
    private ExInOrderResponse.DataBeanX.DataBean inBean;
    private ExTrAdapter exTrAdapter;

    @Override
    protected String title() {
        return "查看物流";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_wuliu_layout_activity);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerMsg.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (ExOutOrderResponse.DataBeanX.DataBean) intent.getSerializableExtra("comm_order_no");
            inBean = (ExInOrderResponse.DataBeanX.DataBean) intent.getSerializableExtra("comm_order_no_in");
            if (dataBean != null && !TextUtils.isEmpty(dataBean.getComm_no())) {
                getWuLiu(dataBean.getComm_no());
            } else if (inBean != null && !TextUtils.isEmpty(inBean.getComm_no())) {
                getWuLiu(inBean.getComm_no());
            }
        }
    }

    private void getWuLiu(final String common_no) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_no", common_no);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlWuLiu, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    TriResponse triResponse = new Gson().fromJson(s, TriResponse.class);
                    if (triResponse.getData().getState() == 2) {
                        statusStr.setText("在途中");
                    } else if (triResponse.getData().getState() == 3) {
                        statusStr.setText("已签收");
                    } else if (triResponse.getData().getState() == 4) {
                        statusStr.setText("问题件");
                    }
                    commNo.setText("订单号:" + common_no);
                    compyName.setText("快递公司:" + triResponse.getData().getCompany_name());
                    triNo.setText("快递单号:" + triResponse.getData().getLogisticCode());
                    List<?> data = triResponse.getData().getTraces();
                    Collections.reverse(data);
                    exTrAdapter = new ExTrAdapter(ExTriffActivity.this, data);
                    recyclerMsg.setAdapter(exTrAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }
}
