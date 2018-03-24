package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;

/**
 * Created by pbq on 2018/3/24.
 */
/*
@className :UserInfoLoginSetInterface
*@date 2018/3/24
*@author
*@description 用户第一次登陆时时设置资料
*/
public interface UserInfoLoginSetInterface extends CallBackInterface {
    public void response(UserInfoSetResponse userInfoSetResponse);
}
