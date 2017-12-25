package com.paobuqianjin.pbq.step.utils;

/**
 * Created by pbq on 2017/12/19.
 */

public class NetApi {
    public final static String TAG = NetApi.class.getSimpleName();
    //圈子接口
    public final static String urlCircle = "http://api.runmoneyin.com/v1/Circle";
    //圈子类型接口
    public final static String urlCircleType = "http://api.runmoneyin.com/v1/CircleType";
    //圈子目标接口
    public final static String urlTarget = "http://api.runmoneyin.com/v1/CircleTarget";
    //圈子成员接口
    public final static String urlCircleMember = "http://api.runmoneyin.com/v1/CircleMember";
    //圈子订单类型接口
    public final static String urlCircleOrderType = "http://api.runmoneyin.com/v1/CircleOrderType";
    //圈子标签接口
    public final static String urlCircleTags = "http://api.runmoneyin.com/v1/CircleTags";
    //圈子封面接口
    public final static String url = "http://api.runmoneyin.com/v1/CircleCover";


    //手机号登陆、微信登陆、微博登陆统一接口 请求方式post 地址：http://pb.com/v1/user/login 参数：mobile、password
    public final static String urlUserLogin = "http://api.runmoneyin.com/v1/user/login";
    //发送验证码接口 请求方式 get,地址：http://pb.com/v1/user/sendmsg 参数：mobile 状态：0成功 -1失败
    public final static String urlSendMsg = "http://api.runmoneyin.comv1/user/sendMsg/?mobile=";
    //获取附近的人接口，请求方式：get 地址：http://pb.com/v1/user/?latitude=35.17000&longitude=86.26000，暂定10万米之内
    public final static String urlNearByPeople = "http://api.runmoneyin.com/v1/user/?latitude=35.17000&longitude=86.26000";
    //手机号注册账号 请求方式post 地址：http://pb.com/v1/user 参数：mobile、password、code
    public final static String urlRegisterPhone = "http://api.runmoneyin.com/v1/user/";
    //请求类型put 地址：http://pb.com/v1/user/18276810055 参数：code和password加在put中
    public final static String urlFindPassWord = "http://api.runmoneyin.com/v1/user/";
    //获取用户信息 请求方式get 地址：http://pb.com/v1/user/用户id
    public final static String urlUser = "http://api.runmoneyin.com/v1/user/";


    /*用户登陆记录   http://pb.com/v1/userrecord*/
    //请求方式：get，http://pb.com/v1/userrecord/?id=2 参数用户id
    public final static String urlUserRecord = "http://api.runmoneyin.com/v1/userrecord/?id=";
    //请求方式：post，地址：http://pb.com/v1/userrecord，参数：用户id、经度longitude、纬度latitude
    public final static String urlUserRecordPost = "http://api.runmoneyin.com/v1/userrecord";

    /*用户步数*/
    //请求方式get/post，地址：http://pb.com/v1/userstep/1 参数：用户id
    public final static String urlUserStep = "http://api.runmoneyin.com/v1/userstep/";
    //用户收益接口
    //获取昨日收益，当月收益，总收益，请求方式：get，地址：http://pb.com/v1/income/?id=1&action=yesterday，参数：用户id、action=all（总收益）、action=month（当月收益）、action=yesterday（昨日收益）
    public final static String urlIncome = "http://api.runmoneyin.com/v1/income/?id=1&action=yesterday";
    //请求方式post，地址：http://pb.com/v1/income，参数：userid（用户id）、typeid（收益类型）、circleid（圈子id）、amount（收益金额）
    public final static String urlIncomePost = "http://api.runmoneyin.com/v1/income";

    //用户身份认证接口
    //用户认证，请求方式：post，地址：http://api.runmoneyin.com/v1/userauthentication，参数：id（用户id）、idcard（身份证号）、realname（真实名字）
    public final static String urlAuthentication = "http://api.runmoneyin.com/v1/userauthentication";
    //获取用户认证状态，请求方式：get，地址：http://api.runmoneyin.com/v1/userauthentication/5（用户id）
    public final static String getUrlAuthenticationState = "http://api.runmoneyin.com/v1/userauthentication/";

    //用户收益额类型
    //用户收益类型相关接口，地址：http://api.runmoneyin.com/v1/incometype/?id=1
    public final static String urlIncomeType = "http://api.runmoneyin.com/v1/incometype/?id=";

    //获取用户步币详细信息
    //获取用户步币详细信息，请求方式：get，地址：http://api.runmoneyin.com/v1/usercredit/?id=5
    public final static String urlCredit = "http://api.runmoneyin.com/v1/usercredit/?id";

    //我的提现接口
    //获取用户提现信息记录，请求方式：get，地址：http://api.runmoneyin.com/v1/withdraw/2，参数：用户id
    public final static String urlWithDraw = "http://api.runmoneyin.com/v1/withdraw/";
    //添加用户提现记录，请求方式：post，地址：http://api.runmoneyin.com/v1/withdraw，参数：type提现类型、amount提现金额、userid用户id


    //用户信息相关接口：邀请达人排行榜、获取我的邀请人数
    //邀请排行榜，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter
    public final static String urlUserInviter = "http://api.runmoneyin.com/v1/userinviter";
    //获取我邀请的人数，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter/1

    //用户关注接口
    //我的关注：get http://api.runmoneyin.com/v1/UserFollow?userid=5
    public final static String urlUserFollow = "http://api.runmoneyin.com/v1/UserFollow";
    //添加关注 post 参数userid ,fllowid


    //圈子订单类型接口http://119.29.10.64/v1/CircleOrderType
    public final static String urlOrderType = "http://119.29.10.64/v1/CircleOrderType";
    //
}
