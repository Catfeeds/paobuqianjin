package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostBindResponse;

/**
 * Created by pbq on 2018/4/2.
 */

public interface BindThirdAccoutInterface extends CallBackInterface {
    public void response(PostBindResponse postBindResponse);

    public void response(ErrorCode errorCode);
}
