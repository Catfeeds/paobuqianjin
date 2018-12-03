package com.paobuqianjin.pbq.step.view.activity.shop;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GoodDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/11/30.
 */

public class TianMaoDetailActivity extends BaseBarActivity {
    @Override
    protected String title() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail_activity_layout);
    }

    @Override
    protected void initView() {

    }

    private void getGoodDeatil(final String num_iid) {
        Map<String, String> param = new HashMap<>();
        param.put("num_iid", num_iid);
        param.put("term_id", "1");

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlGoodsDetail, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    GoodDetailResponse goodDetailResponse = new Gson().fromJson(s, GoodDetailResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(TianMaoDetailActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(TianMaoDetailActivity.this, "开小差了，请稍后再试");
                }
            }
        });
    }
}
