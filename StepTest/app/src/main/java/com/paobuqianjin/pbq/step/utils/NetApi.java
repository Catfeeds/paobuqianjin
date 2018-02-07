package com.paobuqianjin.pbq.step.utils;

/**
 * Created by pbq on 2017/12/19.
 */

public class NetApi {
    //TODO 圈子接口
    /*TODO 获取我的圈子--地址：http://119.29.10.64/v1/Circle/?action=my&userid=1（userid用户真实id）
    获取精选圈子--地址：http://119.29.10.64/v1/Circle/?action=choice&userid=1
    获取我加入的圈子--地址：http://119.29.10.64/v1/Circle/?action=join&userid=1
    获取我创建的圈子--地址：http://119.29.10.64/v1/Circle/?action=create&userid=1*/
    public final static String urlCircle = "http://119.29.10.64/v1/Circle";
    public final static String urlCircleRank = "http://119.29.10.64/v1/Circle/getRank";
    //TODO 圈子类型接口
    public final static String urlCircleType = "http://119.29.10.64/v1/CircleType";
    //TODO 圈子目标接口
    public final static String urlTarget = "http://119.29.10.64/v1/CircleTarget";
    //TODO 获取所有圈子成员
    public final static String urlCircleMember = "http://119.29.10.64/v1/CircleMember";
    //TODO 圈子订单类型接口
    public final static String urlCircleOrderType = "http://119.29.10.64/v1/CircleOrderType";
    //TODO 圈子标签接口
    public final static String urlCircleTags = "http://119.29.10.64/v1/CircleTags";
    //TODO 圈子封面接口
    public final static String urlCircleCover = "http://119.29.10.64/v1/CircleCover";


    //TODO 手机号登陆、微信登陆、微博登陆统一接口 请求方式post 地址：http://pb.com/v1/user/login 参数：mobile、password
    public final static String urlUserLogin = "http://119.29.10.64/v1/user/login";
    //TODO 发送验证码接口 请求方式 get,地址：http://pb.com/v1/user/sendmsg 参数：mobile 状态：0成功 -1失败
    public final static String urlSendMsg = "http://119.29.10.64/v1/user/sendMsg/?mobile=";
    //TODO 获取附近的人接口，请求方式：get 地址：http://pb.com/v1/user/?latitude=35.17000&longitude=86.26000，暂定10万米之内
    public final static String urlNearByPeople = "http://119.29.10.64/v1/user/getNearbyPeople";
    //TODO 手机号注册账号 请求方式post 地址：http://pb.com/v1/user 参数：mobile、password、code
    public final static String urlRegisterPhone = "http://119.29.10.64/v1/user/";
    //TODO 请求类型put 地址：http://pb.com/v1/user/18276810055 参数：code和password加在put中
    public final static String urlFindPassWord = "http://119.29.10.64/v1/user/";
    //TODO 获取用户信息 请求方式get 地址：http://pb.com/v1/user/用户id
    public final static String urlUser = "http://119.29.10.64/v1/user/";


    /*TODO 用户登陆记录   http://pb.com/v1/userrecord*/
    //TODO 请求方式：get，http://pb.com/v1/userrecord/?id=2 参数用户id
    public final static String urlUserRecord = "http://119.29.10.64/v1/userrecord/?id=";
    //TODO 请求方式：post，地址：http://pb.com/v1/userrecord，参数：用户id、经度longitude、纬度latitude
    public final static String urlUserRecordPost = "http://119.29.10.64/v1/userrecord";

    //TODO 用户收益接口
    //请求方式：GET
    //请求示例：http://119.29.10.64/v1/income?userid=1&action=yesterday
    public final static String urlIncome = "http://119.29.10.64/v1/income";

    //TODO 用户身份认证接口
    //TODO 用户认证，请求方式：post，地址：http://api.runmoneyin.com/v1/userauthentication，参数：id（用户id）、idcard（身份证号）、realname（真实名字）
    public final static String urlAuthentication = "http://119.29.10.64/v1/userauthentication";
    //TODO 获取用户认证状态，请求方式：get，地址：http://api.runmoneyin.com/v1/userauthentication/5（用户id）
    public final static String getUrlAuthenticationState = "http://119.29.10.64/v1/userauthentication/";

    //TODO 用户收益额类型
    //TODO 用户收益类型相关接口，地址：http://api.runmoneyin.com/v1/incometype/?id=1
    public final static String urlIncomeType = "http://119.29.10.64/v1/incometype/?id=";

    //TODO 获取用户步币详细信息
    //TODO 获取用户步币详细信息，请求方式：get，地址：http://api.runmoneyin.com/v1/usercredit/?id=5
    public final static String urlCredit = "http://119.29.10.64/v1/usercredit/?id";

    //TODO  我的提现接口
    //TODO 获取用户提现信息记录，请求方式：get，地址：http://api.runmoneyin.com/v1/withdraw/2，参数：用户id
    public final static String urlWithDraw = "http://119.29.10.64/v1/withdraw/";
    //TODO 添加用户提现记录，请求方式：post，地址：http://api.runmoneyin.com/v1/withdraw，参数：type提现类型、amount提现金额、userid用户id


    //TODO 用户信息相关接口：邀请达人排行榜、获取我的邀请人数
    //TODO 邀请排行榜，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter
    public final static String urlUserInviter = "http://119.29.10.64/v1/userinviter";
    //TODO 获取我邀请的人数，请求方式：get，地址：http://api.runmoneyin.com/v1/userinviter/1

    //TODO 用户关注接口
    //TODO 我的关注：get http://api.runmoneyin.com/v1/UserFollow?userid=5
    public final static String urlUserFollow = "http://119.29.10.64/v1/UserFollow";
    //TODO 添加关注 post 参数userid ,fllowid

    //TODO GET index 获取动态列表http://119.29.10.64/v1/Dynamic?page=1&pagesize=1
    public final static String urlDynamic = "http://119.29.10.64/v1/Dynamic";

    //TODO 动态评论
    public final static String urlDynamicComment = "http://119.29.10.64/v1/DynamicComment";
    //TODO 动态点赞接口
    public final static String urlDynamicVote = "http://119.29.10.64/v1/DynamicVote";
    //TODO 用户反馈接口
    public final static String urlFeedBack = "http://119.29.10.64/v1/feedback";
    //TODO 关于我们类型
    //TODO 关于我们类型接口，请求方式：get，地址：http://119.29.10.64/v1/abouttype
    public final static String urlAboutType = "http://119.29.10.64/v1/abouttype";

    //TODO 用户消息接口
    public final static String urlMessage = "http://119.29.10.64/v1/messages";
    //TODO 获取消息详情，请求方式：get，地址：http://119.29.10.64/v1/messages/detail/?id=1，参数：消息id


    //TODO 三方登录
    public final static String urlThirdLogin = "http://119.29.10.64/v1/user/thirdPartyLogin";
    //TODO 通用支付接口
    public final static String urlPay = "http://119.29.10.64/v1/Pay";
    //TODO 创建充值订单
    public final static String urlPayOrder = "http://119.29.10.64/v1/Pay/createOrder";
    //TODO WX 订单查询
    public final static String urlPayResultOrderNo = "http://119.29.10.64/v1/Pay/orderQuery";

    //TODO 任务接口
    public final static String urlTask = "http://119.29.10.64/v1/Task";
    //TODO 我的任务列表
    public final static String urlTaskRecord = "http://119.29.10.64/v1/TaskRecord";
    //TODO 用户步数接口
    //TODO 获取用户当前步数信息 http://119.29.10.64/v1/userstep
    public final static String urlUserStep = "http://119.29.10.64/v1/userstep";
}
