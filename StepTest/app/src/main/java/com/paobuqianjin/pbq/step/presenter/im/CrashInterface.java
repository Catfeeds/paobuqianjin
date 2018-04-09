package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/2/7.
 */

public interface CrashInterface extends CallBackInterface {
    public void response(BindCardListResponse bindCardListResponse);


    public void response(CrashResponse crashResponse);

    public void response(ErrorCode errorCode);
}
