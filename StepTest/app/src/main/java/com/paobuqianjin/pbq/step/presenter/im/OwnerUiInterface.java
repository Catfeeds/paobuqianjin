package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;

/**
 * Created by pbq on 2018/1/24.
 */

public interface OwnerUiInterface extends CallBackInterface {
    public void response(UserInfoResponse userInfoResponse);
}
