package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;

/**
 * Created by pbq on 2018/3/1.
 */

public interface UserFollowInterface extends CallBackInterface {
    public void response(UserFollowOtOResponse userFollowOtOResponse);

    public void response(UserIdFollowResponse userIdFollowResponse);

    public void response(FollowUserResponse followUserResponse);

    public void response(ErrorCode errorCode);
}
