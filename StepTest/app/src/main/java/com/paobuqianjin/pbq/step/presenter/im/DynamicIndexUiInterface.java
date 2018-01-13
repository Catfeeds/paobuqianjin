package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;

/**
 * Created by pbq on 2018/1/12.
 */

public interface DynamicIndexUiInterface extends CallBackInterface {
    public void response(DynamicAllIndexResponse dynamicAllIndexResponse);
}
