package com.paobuqianjin.pbq.step.utils;

/**
 * Created by pbq on 2017/12/19.
 */

public class NetApi {
    public final static String TAG = NetApi.class.getSimpleName();
    //圈子接口
    public final static String urlCircle = "http://www.paobu.com/v1/Circle";
    //圈子类型接口
    public final static String urlCircleType = "http://www.paobu.com/v1/CircleType";
    //圈子目标接口
    public final static String urlTarget = "http://www.paobu.com/v1/CircleTarget";
    //圈子成员接口
    public final static String urlCircleMember = "http://www.paobu.com/v1/CircleMember";
    //圈子订单类型接口
    public final static String urlCircleOrderType = "http://www.paobu.com/v1/CircleOrderType";
    //圈子标签接口
    public final static String urlCircleTags = "http://www.paobu.com/v1/CircleTags";
    //圈子封面接口
    public final static String url = "http://www.paobu.com/v1/CircleCover";


    //手机号登陆、微信登陆、微博登陆统一接口 请求方式post 地址：http://pb.com/v1/user/login 参数：mobile、password
    public final static String urlUserLogin = "http://192.168.2.130/v1/user/login";
    //发送验证码接口 请求方式 get,地址：http://pb.com/v1/user/sendmsg 参数：mobile 状态：0成功 -1失败
    public final static String urlSendMsg = "http://192.168.2.130/v1/user/sendMsg/?mobile=";
    //获取附近的人接口，请求方式：get 地址：http://pb.com/v1/user/?latitude=35.17000&longitude=86.26000，暂定10万米之内
    public final static String urlNearByPeople = "http://192.168.2.130/v1/user/?latitude=35.17000&longitude=86.26000";
    //手机号注册账号 请求方式post 地址：http://pb.com/v1/user 参数：mobile、password、code
    public final static String urlRegisterPhone = "http://192.168.2.130/v1/user/";
    //请求类型put 地址：http://pb.com/v1/user/18276810055 参数：code和password加在put中
    public final static String urlFindPassWord = "http://192.168.2.130/v1/user/";
    //获取用户信息 请求方式get 地址：http://pb.com/v1/user/用户id
    public final static String urlUser = "http://192.168.2.130/v1/user/";

}
