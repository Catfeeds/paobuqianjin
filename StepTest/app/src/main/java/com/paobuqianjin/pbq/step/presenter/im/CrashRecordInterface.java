package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashListDetailResponse;

/**
 * Created by pbq on 2018/3/10.
 */

public interface CrashRecordInterface extends CallBackInterface {
    public void response(CrashListDetailResponse crashListDetailResponse);
}
