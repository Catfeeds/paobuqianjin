package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.OldPassChangeResponse;

/**
 * Created by pbq on 2018/4/2.
 */

public interface OlderPassInterface extends CallBackInterface {
    public void response(OldPassChangeResponse oldPassChangeResponse);

    public void response(ErrorCode errorCode);
}
