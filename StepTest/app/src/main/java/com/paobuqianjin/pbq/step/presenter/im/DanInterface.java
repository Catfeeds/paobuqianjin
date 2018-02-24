package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;

/**
 * Created by pbq on 2018/2/24.
 */

public interface DanInterface extends CallBackInterface {
    public void response(DanListResponse danListResponse);
}
