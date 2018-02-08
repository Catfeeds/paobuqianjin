package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.BindAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;

/**
 * Created by pbq on 2018/2/7.
 */
/*
@className :SignCodeInterface
*@date 2018/2/7
*@author
*@description 验证码发送，校验接口回调
*/
public interface SignCodeInterface extends CallBackInterface {
    public void response(GetSignCodeResponse getSignCodeResponse);

    public void response(CheckSignCodeResponse checkSignCodeResponse);

    public void response(BindAccountResponse bindAccoutResponse);
}
