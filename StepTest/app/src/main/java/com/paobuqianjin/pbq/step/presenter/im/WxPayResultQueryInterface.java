package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;

/**
 * Created by pbq on 2018/1/31.
 */

public interface WxPayResultQueryInterface extends CallBackInterface {
    public void response(WxPayResultResponse wxPayResultResponse);

    public void response(Object error);
}
