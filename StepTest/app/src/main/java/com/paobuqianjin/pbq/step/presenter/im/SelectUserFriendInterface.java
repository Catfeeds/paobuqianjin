package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendSearchResponse;

/**
 * Created by pbq on 2018/2/23.
 */

public interface SelectUserFriendInterface extends CallBackInterface {
    public void response(UserFriendResponse userFriendResponse);

    public void response(UserFriendSearchResponse userFriendSearchResponse);
}
