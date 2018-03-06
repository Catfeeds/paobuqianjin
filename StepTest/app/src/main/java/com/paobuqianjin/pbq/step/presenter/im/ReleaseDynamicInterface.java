package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseDynamicResponse;

/**
 * Created by pbq on 2018/3/6.
 */

public interface ReleaseDynamicInterface extends CallBackInterface {
    public void response(ReleaseDynamicResponse releaseDynamicResponse);
}
