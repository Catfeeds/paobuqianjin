package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.AllMyPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/2/3.
 */
/*
@className :AllPayOrderInterface
*@date 2018/2/3
*@author
*@description  我的订单查询接口
*/
public interface AllPayOrderInterface extends CallBackInterface {
    public void response(AllMyPayResponse allMyPayResponse);

    public void response(ErrorCode errorCode);
}
