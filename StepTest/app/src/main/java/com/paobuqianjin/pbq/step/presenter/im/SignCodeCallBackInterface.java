package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.SignCodeResponse;

/**
 * Created by pbq on 2017/12/21.
 */

public interface SignCodeCallBackInterface extends LoginCallBackInterface {
    public void signCodeCallBack(SignCodeResponse response);
}
