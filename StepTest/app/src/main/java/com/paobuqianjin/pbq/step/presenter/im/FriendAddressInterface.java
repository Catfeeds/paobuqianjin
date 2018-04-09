package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FriendAddResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteMessageResponse;

/**
 * Created by pbq on 2018/3/28.
 */

public interface FriendAddressInterface extends CallBackInterface {
    public void response(FriendAddResponse friendAddResponse);

    public void response(InviteMessageResponse inviteMessageResponse);

    public void response(ErrorCode errorCode);
}
