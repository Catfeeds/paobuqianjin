package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.UserInfo;

/**
 * Created by pbq on 2017/12/21.
 */

public interface UserInfoInterface extends InlineCallBackInterface {
    public void update(UserInfo userInfo);
}
