package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExGoodDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.ExPayFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.PayFailedFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.PaySuccessFragment;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/28.
 */

public class ExpayActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final static String TAG = ExpayActivity.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private ExPayFragment exPayFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_pay_layout_activity);
        ButterKnife.bind(this);
        sharedPreferences = Presenter.getInstance(this).getSharePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        exPayFragment = new ExPayFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, exPayFragment)
                .show(exPayFragment)
                .commit();
    }


    public void showRePayFragment(BaseFragment hideFragment) {
        LocalLog.d(TAG, "重新支付");
        if (exPayFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(hideFragment)
                    .show(exPayFragment)
                    .commit();
        }
    }

    public void showPaySuccessWallet(WalletPayOrderResponse walletPayOrderResponse) {
        if (walletPayOrderResponse.getError() == 0) {
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            PaySuccessFragment paySuccessFragment = new PaySuccessFragment();
            paySuccessFragment.setPayNum(Float.parseFloat(walletPayOrderResponse.getData().getTotal_fee()));
            getSupportFragmentManager().beginTransaction()
                    .hide(exPayFragment)
                    .add(R.id.container, paySuccessFragment)
                    .show(paySuccessFragment)
                    .commit();
            setResult(RESULT_OK);
        } else {
            LocalLog.d(TAG, "error  ");
            PaoToastUtils.showLongToast(this, "支付未完成");
            PayFailedFragment payFailedFragment = new PayFailedFragment();
            getSupportFragmentManager().beginTransaction()
                    .hide(exPayFragment)
                    .add(R.id.pay_container, payFailedFragment)
                    .show(payFailedFragment)
                    .commit();
        }
    }

    @Override
    synchronized public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        LocalLog.d(TAG, "onSharedPreferenceChanged() enter");
        if ("errorCode".equals(s)) {
            if (Presenter.getInstance(this).getPayResultCode() != -3) {
                LocalLog.d(TAG, "errorCode 被设置");
                int resultCode = Presenter.getInstance(this).getPayResultCode();
                String outTradeNo = Presenter.getInstance(this).getOutTradeNo();
                if (resultCode == 0) {

                    LocalLog.d(TAG, "查询订单");
                    Map<String, String> param = new HashMap<>();
                    param.put("order_no", outTradeNo);
                    param.put("payment_type", "wx");
                    Presenter.getInstance(this).getPaoBuSimple(NetApi.urlPayResultOrderNo, param, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                WxPayResultResponse wxPayResultResponse = new Gson().fromJson(s, WxPayResultResponse.class);
                                PaoToastUtils.showLongToast(ExpayActivity.this, "支付成功");
                                PaySuccessFragment paySuccessFragment = new PaySuccessFragment();
                                paySuccessFragment.setPayNum(Float.parseFloat(wxPayResultResponse.getData().getTotal_fee()));
                                getSupportFragmentManager().beginTransaction()
                                        .hide(exPayFragment)
                                        .add(R.id.container, paySuccessFragment)
                                        .show(paySuccessFragment)
                                        .commit();
                                setResult(RESULT_OK);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                        }
                    });
                } else if (resultCode == -1) {

                } else if (resultCode == -2) {
                    LocalLog.d(TAG, "支付被取消!");
                }
                Presenter.getInstance(this).setPayResultCode(-3);
            }
        }
    }
}
