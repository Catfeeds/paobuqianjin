package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.SignUserResponse;

/**
 * Created by pbq on 2017/12/9.
 */
/*
@className :LoginSignCallbackInterface
*@date 2017/12/9
*@author
*@description  登陆注册接口
*/
public interface LoginSignCallbackInterface extends UiCallBackInterface {
    /*@desc获取验证码
    *@function requestPhoneSignCodeCallBack
    *@param
    *@return
    */
    public void requestPhoneSignCodeCallBack(int signCode);

    /*@desc 登陆请求
    *@function requestLogin
    *@param
    *@return
    */
    public void requestPhoneLoginCallback(LoginResponse loginResponse);

    /*@desc 三方登入
    *@function requestThirdLoginCallBack
    *@param
    *@return 
    */
    public void requestThirdLoginCallBack();

    public void registerByPhoneCallBack(SignUserResponse response);
}
