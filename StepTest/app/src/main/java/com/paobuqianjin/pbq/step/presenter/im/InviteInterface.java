package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;

/**
 * Created by pbq on 2018/2/27.
 */

public interface InviteInterface extends CallBackInterface {
    public void response(MyInviteResponse myInviteResponse);

    public void response(InviteDanResponse inviteDanResponse);

    public void response(ErrorCode errorCode);
}
