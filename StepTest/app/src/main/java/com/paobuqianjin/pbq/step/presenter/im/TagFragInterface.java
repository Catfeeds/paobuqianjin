package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;

/**
 * Created by pbq on 2018/1/8.
 */

public interface TagFragInterface extends CallBackInterface {
    //获取标签列表
    public void response(CircleTagResponse circleTagResponse);
}
