package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRandWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;

/**
 * Created by pbq on 2018/3/19.
 */

public interface CircleStepDetailDanInterface extends CallBackInterface {
    public void response(CircleStepRankResponse circleStepRankResponse);

    public void response(StepRankResponse stepRankResponse);

    public void response(ErrorCode errorCode);

    public void response(CircleDetailResponse circleDetailResponse);

    public void response(StepRandWeekResponse stepRandWeekResponse);

    public void response(CircleStepRankWeekResponse circleStepRankWeekResponse);
}
