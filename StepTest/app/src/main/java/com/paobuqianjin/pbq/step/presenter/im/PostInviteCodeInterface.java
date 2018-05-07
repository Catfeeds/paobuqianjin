package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostInviteCodeResponse;

/**
 * Created by pbq on 2018/2/27.
 */

public interface PostInviteCodeInterface extends CallBackInterface {
    public void response(PostInviteCodeResponse postInviteCodeResponse);
}
