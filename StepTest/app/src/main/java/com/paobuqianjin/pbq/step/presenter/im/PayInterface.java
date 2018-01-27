package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;

/**
 * Created by pbq on 2017/12/1.
 */

public interface PayInterface extends CallBackInterface {
    //微信下单返回
    public void response(WxPayOrderResponse wxPayOrderResponse);
}
