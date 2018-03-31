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
    public final static String url = "http://119.29.10.64/";
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
    public final static String urlSendMsg = "http://119.29.10.64/v1/ThirdParty/sendMsg/?mobile=";
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
    public final static String urlCredit = "http://119.29.10.64/v1/usercredit";

    //TODO 添加用户提现记录，请求方式：post，地址：http://api.runmoneyin.com/v1/withdraw，参数：type提现类型、amount提现金额、userid用户id

    //TODO 用户关注接口
    public final static String urlUserFollow = "http://119.29.10.64/v1/UserFollow";

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

    //TODO 获取消息详情，请求方式：get，地址：http://119.29.10.64/v1/messages/detail/?id=1，参数：消息id


    //TODO 三方登录
    public final static String urlThirdLogin = "http://119.29.10.64/v1/user/thirdPartyLogin";
    //TODO 通用支付接口
    public final static String urlPay = "http://119.29.10.64/v1/Pay";
    //TODO 创建充值订单
    public final static String urlPayOrder = "http://119.29.10.64/v1/Pay/createOrder";
    //TODO WX 订单查询
    public final static String urlPayResultOrderNo = "http://119.29.10.64/v1/Pay/orderQuery";

    //TODO 发布任务接口
    public final static String urlTask = "http://119.29.10.64/v1/Task";
    //TODO 用户步数接口
    //TODO 获取用户当前步数信息 http://119.29.10.64/v1/userstep
    public final static String urlUserStep = "http://119.29.10.64/v1/userstep";
    //TODO 我的任务列表
    public final static String urlTaskRecord = "http://119.29.10.64/v1/TaskRecord";
    //TODO 提现账户接口
    public final static String urlUserBankCard = "http://119.29.10.64/v1/UserBankCard";
    //TODO 验证码发送校验
    public final static String urlSignCode = "http://119.29.10.64/v1/ThirdParty/sendMsg";
    //TODO 校验验证码
    public final static String urlSignCodeCheck = "http://119.29.10.64/v1/UserBankCard/checkCode";
    //TODO 提现接口
    public final static String urlCrashTo = "http://119.29.10.64/v1/withdraw";

    //TODO 天气查询接口
    public final static String urlWeather = "http://119.29.10.64/v1/ThirdParty/getWeather";
    //TODO 用户好友接口
    public final static String urlUserFriends = "http://119.29.10.64/v1/UserFriends";
    //TODO 获取段位列表
    public final static String urlUserLevel = "http://119.29.10.64/v1/userlevel";
    //TODO 邀请好友接口
    public final static String urlInvite = "http://119.29.10.64/v1/userinviter";
    //TODO 用户在圈子中的排名 http://119.29.10.64/v1/Circle/getUserCircleRank?userid=30&circleid=100302
    public final static String urlUserCircleRank = "http://119.29.10.64/v1/Circle/getUserCircleRank?userid=";
    //TODO 获取附近广告商户
    public final static String urlBusiness = "http://119.29.10.64/v1/Business";
    //TODO 根据手机号码修改密码
    public final static String urlPassWord = "http://119.29.10.64/v1/user/modifyPasswordByMobile";
    //TODO 获取登录记录
    public final static String urlLoginRecord = "http://119.29.10.64/v1/userrecord?userid=";
    //TODO 三方绑定手机号
    public final static String urlBindPhone = "http://119.29.10.64/v1/user/bindMobile";
    //TODO 用户消息接口
    public final static String urlMessage = "http://119.29.10.64/v1/usermessages";
    //TODO 通讯录上传
    public final static String urlAddressBook = " http://119.29.10.64/v1/UserFollow/addressBook";
    //TODO 圈子榜单，排行http://119.29.10.64/v1/Circle/UserCircleWeekRank?circleid=100344&userid=30&page=1&pagesize=10
    public final static String urlCircleWeekRank = "http://119.29.10.64/v1/Circle/UserCircleWeekRank?circleid=";
    //TODO 周名次 http://119.29.10.64/v1/Circle/UserCircleWeekNum?circleid=100000&userid=1
    public final static String urlStepRankWeekNum = "http://119.29.10.64/v1/Circle/UserCircleWeekNum?circleid=";
    //TODO 短信邀请
    public final static String urlinviteMsg = "http://119.29.10.64/v1/userinviter/inviteMsg";
}
