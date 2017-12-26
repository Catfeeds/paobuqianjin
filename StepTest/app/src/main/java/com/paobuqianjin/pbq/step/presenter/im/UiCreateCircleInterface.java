package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;

/**
 * Created by pbq on 2017/12/19.
 */

public interface UiCreateCircleInterface extends CallBackInterface {

    public void response(CircleTypeResponse circleTypeResponse);

    public void response(CircleTargetResponse circleTargetResponse);

    public void response(CircleTagResponse circleTagResponse);
}
