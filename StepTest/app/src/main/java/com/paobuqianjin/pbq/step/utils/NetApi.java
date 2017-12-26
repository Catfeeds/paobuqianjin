package com.paobuqianjin.pbq.step.utils;

/**
 * Created by pbq on 2017/12/19.
 */

public class NetApi {
    //圈子接口
    public final static String urlCircle = "http://119.29.10.64/v1/Circle";
    //圈子类型接口
    public final static String urlCircleType = "http://119.29.10.64/v1/CircleType";
    //圈子目标接口
    public final static String urlTarget = "http://119.29.10.64/v1/CircleTarget";
    //圈子成员接口
    public final static String urlCircleMember = "http://119.29.10.64/v1/CircleMember";
    //圈子订单类型接口
    public final static String urlCircleOrderType = "http://119.29.10.64/v1/CircleOrderType";
    //圈子标签接口
    public final static String urlCircleTags = "http://119.29.10.64/v1/CircleTags";
    //圈子封面接口
    public final static String urlCircleCover = "http://119.29.10.64/v1/CircleCover";


    //手机号登陆、微信登陆、微博登陆统一接口 请求方式post 地址：http://pb.com/v1/user/login 参数：mobile、password
    public final static String urlUserLogin = "http://119.29.10.64/v1/user/login";
    //发送验证码接口 请求方式 get,地址：http://pb.com/v1/user/sendmsg 参数：mobile 状态：0成功 -1失败
    public final static String urlSendMsg = "http://119.29.10.64/v1/user/sendMsg/?mobile=";
    //获取附近的人接口，请求方式：get 地址：http://pb.com/v1/user/?latitude=35.17000&longitude=86.26000，暂定10万米之内
    public final static String urlNearByPeople = "http://119.29.10.64/v1/user/?latitude=35.17000&longitude=86.26000";
    //手机号注册账号 请求方式post 地址：http://pb.com/v1/user 参数：mobile、password、code
    public final static String urlRegisterPhone = "http://119.29.10.64/v1/user/";
    //请求类型put 地址：http://pb.com/v1/user/18276810055 参数：code和password加在put中
    public final static String urlFindPassWord = "http://119.29.10.64/v1/user/";
    //获取用户信息 请求方式get 地址：http://pb.com/v1/user/用户id
    public final static String urlUser = "http://119.29.10.64/v1/user/";


    /*用户登陆记录   http://pb.com/v1/userrecord*/
    //请求方式：get，http://pb.com/v1/userrecord/?id=2 参数用户id
    public final static String urlUserRecord = "http://119.29.10.64/v1/userrecord/?id=";
    //请求方式：post，地址：http://pb.com/v1/userrecord，参数：用户id、经度longitude、纬度latitude
    public final static String urlUserRecordPost = "http://119.29.10.64/v1/userrecord";

    /*用户步数*/
    //请求方式get/post，地址：http://pb.com/v1/userstep/1 参数：用户id
    public final static String urlUserStep = "http://119.29.10.64/v1/userstep/";
    //用户收益接口
    //获取昨日收益，当月收益，总收益，请求方式：get，地址：http://pb.com/v1/income/?id=1&action=yesterday，参数：用户id、action=all（总收益）、action=month（当月收益）、action=yesterday（昨日收益）
    public final static String urlIncome = "http://119.29.10.64/v1/income/?id=1&action=yesterday";
    //请求方式post，地址：http://pb.com/v1/income，参数：userid（用户id）、typeid（收益类型）、circleid（圈子id）、amount（收益金额）
    public final static String urlIncomePost = "http://119.29.10.64/v1/income";

    //用户身份认证接口
    //用户认证，请求方式：post，地址：http://api.runmoneyin.com/v1/userauthentication，参数：id（用户id）、idcard（身份证号）、realname（真实名字）
    public final static String urlAuthentication = "http://119.29.10.64/v1/userauthentication";
    //获取用户认证状态，请求方式：get，地址：http://api.runmoneyin.com/v1/userauthentication/5（用户id）
    public final static String getUrlAuthenticationState = "http://119.29.10.64/v1/userauthentication/";

    //用户收益额类型
    //用户收益类型相关接口，地址：http://api.runmoneyin.com/v1/incometype/?id=1
    public final static String urlIncomeType = "http://119.29.10.64/v1/incometype/?id=";

    //获取用户步币详细信息
    //获取用户步币详细信息，请求方式：get，地址：http://api.runmoneyin.com/v1/usercredit/?id=5
    public final static String urlCredit = "http://119.29.10.64/v1/usercredit/?id";

    //我的提现接口
    //获取用户提现信息记录，请求方式：get，地址：http://api.runmoneyin.com/v1/withdraw/2，参数：用户id
    public final static String urlWithDraw = "http://119.29.10.64/v1/withdraw/";
    //添加用户提现记录，请求方式：post，地址：http://api.runmoneyin.com/v1/withdraw，参数：type提现类型、amount提现金额、userid用户id


    //用户信息相关接口：邀请达人排行榜、获取我的邀请人数
    //邀请排行榜，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter
    public final static String urlUserInviter = "http://119.29.10.64/v1/userinviter";
    //获取我邀请的人数，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter/1

    //用户关注接口
    //我的关注：get http://api.runmoneyin.com/v1/UserFollow?userid=5
    public final static String urlUserFollow = "http://119.29.10.64/v1/UserFollow";
    //添加关注 post 参数userid ,fllowid

    //GET index 获取动态列表http://119.29.10.64/v1/Dynamic?page=1&pagesize=1
    public final static String urlDynamic = "http://119.29.10.64/v1/Dynamic";

    //动态评论
    public final static String urlDynamicComment = "http://119.29.10.64/v1/DynamicComment";
    //动态点赞接口
    public final static String urlDynamicVote = "http://119.29.10.64/v1/DynamicVote";
    //用户反馈接口
    public final static String urlFeedBack = "http://119.29.10.64/v1/feedback";
    //关于我们类型
    //关于我们类型接口，请求方式：get，地址：http://119.29.10.64/v1/abouttype
    public final static String urlAboutType = "http://119.29.10.64/v1/abouttype";

    //用户消息接口
    public final static String urlMessage = "http://119.29.10.64/v1/messages";
    //获取消息详情，请求方式：get，地址：http://119.29.10.64/v1/messages/detail/?id=1，参数：消息id
}
