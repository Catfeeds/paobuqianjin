package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyReleaseTaskDetailResponse;

/**
 * Created by pbq on 2018/2/28.
 */

public interface MyReleaseTaskDetailInterface extends CallBackInterface {
    public void response(MyReleaseTaskDetailResponse myReleaseTaskDetailResponse);

    public void response(ErrorCode errorCode);
}
