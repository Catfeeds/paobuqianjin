package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;

/**
 * Created by pbq on 2018/2/2.
 */

public interface CircleMemberManagerInterface extends CallBackInterface {
    public void response(CircleMemberResponse circleMemberResponse);
}
