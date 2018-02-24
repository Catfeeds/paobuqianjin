package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;

/**
 * Created by pbq on 2018/2/24.
 */
/*
@className :UserHomeInterface
*@date 2018/2/24
*@author
*@description 用户主页接口
*/
public interface UserHomeInterface extends CallBackInterface {
    public void response(UserInfoResponse userInfoResponse);

    public void response(DynamicPersonResponse dynamicPersonResponse);
}
