package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendStepRankDayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendWeekResponse;

/**
 * Created by pbq on 2018/3/19.
 */

public interface FriendHonorDetailInterface extends CallBackInterface {
    public void response(FriendStepRankDayResponse friendStepRankDayResponse);

    public void response(ErrorCode errorCode);

    public void response(FriendWeekResponse friendWeekResponse);
}
