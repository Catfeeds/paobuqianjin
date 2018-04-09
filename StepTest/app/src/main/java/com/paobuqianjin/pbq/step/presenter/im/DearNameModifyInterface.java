package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DearNameResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/3/22.
 */

public interface DearNameModifyInterface extends CallBackInterface {
    public void response(DearNameResponse dearNameResponse);

    public void response(ErrorCode errorCode);
}
