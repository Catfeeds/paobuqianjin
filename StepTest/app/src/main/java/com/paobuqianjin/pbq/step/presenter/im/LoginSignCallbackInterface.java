package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;

/**
 * Created by pbq on 2017/12/9.
 */
/*
@className :LoginSignCallbackInterface
*@date 2017/12/9
*@author
*@description  登陆注册接口
*/
public interface LoginSignCallbackInterface extends LoginCallBackInterface {
    /*@desc获取验证码
    *@function response
    *@param
    *@return
    */
    public void response(GetSignCodeResponse getSignCodeResponse);

    /*@desc 登陆请求
    *@function requestLogin
    *@param
    *@return
    */
    public void response(LoginResponse loginResponse);

    /*@desc 三方登入
    *@function response
    *@param
    *@return 
    */
    public void response(ThirdPartyLoginResponse thirdPartyLoginResponse);

    public void response(SignUserResponse response);

    public void response(LoginRecordResponse loginRecordResponse);

    public void response(ErrorCode errorCode);
}
