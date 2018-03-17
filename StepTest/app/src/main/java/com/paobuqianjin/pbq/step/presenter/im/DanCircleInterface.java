package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;

/**
 * Created by pbq on 2018/3/10.
 */
/*
@className :DanCircleInterface
*@date 2018/3/10
*@author
*@description 圈子榜单
*/
public interface DanCircleInterface extends CallBackInterface {
    public void response(MyHotCircleResponse myHotCircleResponse);

    public void response(CircleStepRankResponse circleStepRankResponse);
}
