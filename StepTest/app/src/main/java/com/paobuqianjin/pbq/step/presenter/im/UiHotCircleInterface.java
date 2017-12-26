package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;

/**
 * Created by pbq on 2017/12/26.
 */

public interface UiHotCircleInterface extends CallBackInterface {
    //我的圈子回调
    public void response(MyHotCircleResponse myHotCircleResponse);
    //圈子活动回调
    //精选圈子回调
    public void response(ChoiceCircleResponse choiceCircleResponse);
}
