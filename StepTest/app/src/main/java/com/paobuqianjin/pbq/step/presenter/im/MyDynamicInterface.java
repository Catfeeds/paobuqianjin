package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;

/**
 * Created by pbq on 2018/2/24.
 */
/*
@className :MyDynamicInterface
*@date 2018/2/24
*@author
*@description 我的动态接口
*/
public interface MyDynamicInterface extends CallBackInterface {
    public void response(DynamicPersonResponse dynamicPersonResponse);
}
