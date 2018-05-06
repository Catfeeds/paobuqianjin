package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;

/**
 * Created by pbq on 2017/12/1.
 */

public interface PayInterface extends CallBackInterface {
    //微信下单返回
    public void response(WxPayOrderResponse wxPayOrderResponse);

    //钱包支付
    public void response(WalletPayOrderResponse walletPayOrderResponse);
    //支付宝支付

    public  void response(ErrorCode errorCode);
}
