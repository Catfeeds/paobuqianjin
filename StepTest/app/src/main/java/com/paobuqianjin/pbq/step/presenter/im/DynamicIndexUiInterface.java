package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/1/12.
 */

public interface DynamicIndexUiInterface extends CallBackInterface {
    public void response(DynamicAllIndexResponse dynamicAllIndexResponse);

    public void response(ErrorCode errorCode);
}
