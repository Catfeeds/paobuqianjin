package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LogBindPhoneResponse;

/**
 * Created by pbq on 2018/3/23.
 */

public interface LoginBindPhoneInterface extends CallBackInterface {
    public void response(GetSignCodeResponse getSignCodeResponse);

    public void response(CheckSignCodeResponse checkSignCodeResponse);

    public void response(LogBindPhoneResponse logBindPhoneResponse);

    public void response(ErrorCode errorCode);
}
