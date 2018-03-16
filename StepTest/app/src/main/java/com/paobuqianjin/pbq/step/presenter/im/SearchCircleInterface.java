package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;


/**
 * Created by pbq on 2018/3/13.
 */

public interface SearchCircleInterface extends CallBackInterface {
    public void response(ChoiceCircleResponse searchCircleResponse);
    public void response(ErrorCode errorCode);
}
