package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserDanResponse;

/**
 * Created by pbq on 2018/2/24.
 */

public interface DanInterface extends CallBackInterface {
    public void response(DanListResponse danListResponse);

    public void response(UserDanResponse userDanResponse);

    public void response(ErrorCode errorCode);
}
