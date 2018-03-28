package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;

/**
 * Created by pbq on 2018/3/28.
 */

public interface FriendAddressInterface extends CallBackInterface {
    public void response(FriendAddResponse friendAddResponse);
}
