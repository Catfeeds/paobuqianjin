package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;

/**
 * Created by pbq on 2017/12/19.
 */

public interface UiCreateCircleInterface extends CallBackInterface {
    public void response(CircleTargetResponse targetResponse);

    public void response(CircleTypeResponse circleTypeResponse);

    public void response(CircleTagResponse circleTagResponse);

    public void response(CreateCircleResponse createCircleResponse);

    public void response(Object error);

    public void responseLocation(String city, double latitude, double longitude);
}
