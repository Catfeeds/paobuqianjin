package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ProtocolResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/4/2.
 */

public interface ProtocolInterface extends CallBackInterface {
    public void response(ProtocolResponse crashProtocolResponse);

    public void response(ErrorCode errorCode);
}
