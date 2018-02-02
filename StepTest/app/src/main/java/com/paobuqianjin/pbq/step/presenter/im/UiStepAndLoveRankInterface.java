package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;

/**
 * Created by pbq on 2018/1/4.
 */

public interface UiStepAndLoveRankInterface extends CallBackInterface {

    public void response(ReChargeRankResponse reChargeRankResponse);

    public void response(StepRankResponse stepRankResponse);
}
