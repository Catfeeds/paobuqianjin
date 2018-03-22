package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.RechargeDetailResponse;

/**
 * Created by pbq on 2018/3/22.
 */

public interface RechargeDetailInterface extends CallBackInterface {
    public void response(RechargeDetailResponse rechargeDetailResponse);
}
