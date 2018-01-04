package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;

/**
 * Created by pbq on 2017/12/26.
 */

public interface UiHotCircleInterface extends CallBackInterface {
    //圈子活动回调
    //精选圈子回调
    public void response(ChoiceCircleResponse choiceCircleResponse);

    //我创建的圈子
    public void response(MyCreateCircleResponse myCreateCircleResponse);

    //我加入的圈子
    public void response(MyJoinCircleResponse myJoinCircleResponse);
}
