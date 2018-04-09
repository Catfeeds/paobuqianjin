package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashListDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/3/10.
 */

public interface CrashRecordInterface extends CallBackInterface {
    public void response(CrashListDetailResponse crashListDetailResponse);

    public void response(ErrorCode errorCode);
}
