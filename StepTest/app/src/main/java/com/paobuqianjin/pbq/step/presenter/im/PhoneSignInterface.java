package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;

/**
 * Created by pbq on 2018/6/30.
 */

public interface PhoneSignInterface extends CallBackInterface {
    public void response(SignUserResponse response);
}
