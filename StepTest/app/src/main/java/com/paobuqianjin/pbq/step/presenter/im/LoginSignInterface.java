package com.paobuqianjin.pbq.step.presenter.im;

/**
 * Created by pbq on 2017/12/9.
 */
/*
@className :LoginSignInterface
*@date 2017/12/9
*@author
*@description  登陆注册接口
*/
public interface LoginSignInterface {
    /*@desc获取验证码
    *@function requestPhoneSignCode
    *@param
    *@return
    */
    public void requestPhoneSignCode();

    /*@desc 登陆请求
    *@function requestLogin
    *@param
    *@return
    */
    public void requestPhoneLogin(String userName, String phoneSignCode, String password);

    /*@desc 三方登入
    *@function requestThirdLogin
    *@param
    *@return 
    */
    public void requestThirdLogin();
}
