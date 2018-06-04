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
    public final static String url = "https://api.runmoneyin.com/";
/*    public final static String url = "http://api-test1.runmoneyin.com/";*/
/*    public final static String url = "http://api-test.runmoneyin.com/";*/
    /*public final static String url = "http://119.29.10.64/";*/
    public final static String urlCircle = url + "v1/Circle";
    public final static String urlCircleRank = url + "v1/Circle/getRank";
    //TODO 圈子类型接口
    public final static String urlCircleType = url + "v1/CircleType";
    //TODO 圈子目标接口
    public final static String urlTarget = url + "v1/CircleTarget";
    //TODO 获取所有圈子成员
    public final static String urlCircleMember = url + "v1/CircleMember";
    public final static String urlMemberSearch = urlCircleMember + "?circleid=";
    //TODO 圈子订单类型接口
    public final static String urlCircleOrderType = url + "v1/CircleOrderType";
    //TODO 圈子标签接口
    public final static String urlCircleTags = url + "v1/CircleTags";
    //TODO 圈子封面接口
    public final static String urlCircleCover = url + "v1/CircleCover";


    //TODO 手机号登陆、微信登陆、微博登陆统一接口 请求方式post 地址：http://pb.com/v1/user/login 参数：mobile、password
    public final static String urlUserLogin = url + "v1/user/login";
    //TODO 发送验证码接口 请求方式 get,地址：http://pb.com/v1/user/sendmsg 参数：mobile 状态：0成功 -1失败
    public final static String urlSendMsg = url + "v1/ThirdParty/sendMsg/?mobile=";
    //TODO 获取附近的人接口，请求方式：get 地址：http://pb.com/v1/user/?latitude=35.17000&longitude=86.26000，暂定10万米之内
    public final static String urlNearByPeople = url + "v1/user/getNearbyPeople";
    //TODO 手机号注册账号 请求方式post 地址：http://pb.com/v1/user 参数：mobile、password、code
    public final static String urlRegisterPhone = url + "v1/user/";
    //TODO 请求类型put 地址：http://pb.com/v1/user/18276810055 参数：code和password加在put中
    public final static String urlFindPassWord = url + "v1/user/";
    //TODO 获取用户信息 请求方式get 地址：http://pb.com/v1/user/用户id
    public final static String urlUser = url + "v1/user/";


    /*TODO 用户登陆记录   http://pb.com/v1/userrecord*/
    //TODO 请求方式：get，http://pb.com/v1/userrecord/?id=2 参数用户id
    public final static String urlUserRecord = url + "v1/userrecord/?id=";
    //TODO 请求方式：post，地址：http://pb.com/v1/userrecord，参数：用户id、经度longitude、纬度latitude
    public final static String urlUserRecordPost = url + "v1/userrecord";

    //TODO 用户收益接口
    //请求方式：GET
    //请求示例：http://119.29.10.64/v1/income?userid=1&action=yesterday
    public final static String urlIncome = url + "v1/income";

    //TODO 用户身份认证接口
    //TODO 用户认证，请求方式：post，地址：http://api.runmoneyin.com/v1/userauthentication，参数：id（用户id）、idcard（身份证号）、realname（真实名字）
    public final static String urlAuthentication = url + "v1/userauthentication";
    //TODO 获取用户认证状态，请求方式：get，地址：http://api.runmoneyin.com/v1/userauthentication/5（用户id）
    public final static String getUrlAuthenticationState = url + "v1/userauthentication/";

    //TODO 用户收益额类型
    //TODO 用户收益类型相关接口，地址：http://api.runmoneyin.com/v1/incometype/?id=1
    public final static String urlIncomeType = url + "v1/incometype/?id=";

    //TODO 获取用户步币详细信息
    //TODO 获取用户步币详细信息，请求方式：get，地址：http://api.runmoneyin.com/v1/usercredit/?id=5
    public final static String urlCredit = url + "v1/usercredit";

    //TODO 添加用户提现记录，请求方式：post，地址：http://api.runmoneyin.com/v1/withdraw，参数：type提现类型、amount提现金额、userid用户id

    //TODO 用户关注接口
    public final static String urlUserFollow = url + "v1/UserFollow";

    //TODO GET index 获取动态列表http://119.29.10.64/v1/Dynamic?page=1&pagesize=1
    public final static String urlDynamic = url + "v1/Dynamic";

    //TODO 动态评论
    public final static String urlDynamicComment = url + "v1/DynamicComment";
    //TODO 动态点赞接口
    public final static String urlDynamicVote = url + "v1/DynamicVote";
    //TODO 用户反馈接口
    public final static String urlFeedBack = url + "v1/feedback";
    //TODO 关于我们类型
    //TODO 关于我们类型接口，请求方式：get，地址：http://119.29.10.64/v1/abouttype
    public final static String urlAboutType = url + "v1/abouttype";

    //TODO 获取消息详情，请求方式：get，地址：http://119.29.10.64/v1/messages/detail/?id=1，参数：消息id


    //TODO 三方登录
    public final static String urlThirdLogin = url + "v1/user/thirdPartyLogin";
    //TODO 通用支付接口
    public final static String urlPay = url + "v1/Pay";
    //TODO 创建充值订单
    public final static String urlPayOrder = url + "v1/Pay/createOrder";
    //TODO WX 订单查询
    public final static String urlPayResultOrderNo = url + "v1/Pay/orderQuery";
    //TODO yspay订单查询
    public final static String urlYsPayResultOrderNo = url + "v1/Pay/quickPayQuery";
    //TODO 发布任务接口
    public final static String urlTask = url + "v1/Task";
    //TODO 用户步数接口
    //TODO 获取用户当前步数信息 http://119.29.10.64/v1/userstep
    public final static String urlUserStep = url + "v1/userstep";
    //TODO 我的任务列表
    public final static String urlTaskRecord = url + "v1/TaskRecord";
    //TODO 提现账户接口
    public final static String urlUserBankCard = url + "v1/UserBankCard";
    //TODO 验证码发送校验
    public final static String urlSignCode = url + "v1/ThirdParty/sendMsg";
    //TODO 校验验证码
    public final static String urlSignCodeCheck = url + "v1/UserBankCard/checkCode";
    //TODO 提现接口
    public final static String urlCrashTo = url + "v1/withdraw";
    //TODO 充值记录
    public final static String urlRechargeRecord = urlCrashTo + "/rechargeList";
    //TODO 天气查询接口
    public final static String urlWeather = url + "v1/ThirdParty/getWeather";
    //TODO 用户好友接口
    public final static String urlUserFriends = url + "v1/UserFriends";
    //TODO 获取段位列表
    public final static String urlUserLevel = url + "v1/userlevel";
    //TODO 邀请好友接口
    public final static String urlInvite = url + "v1/userinviter";
    //TODO 用户在圈子中的排名 http://119.29.10.64/v1/Circle/getUserCircleRank?userid=30&circleid=100302
    public final static String urlUserCircleRank = url + "v1/Circle/getUserCircleRank?userid=";
    //TODO 获取附近广告商户
    public final static String urlRedpacket = url + "v1/Redpacket";
    //TODO 根据手机号码修改密码
    public final static String urlPassWord = url + "v1/user/modifyPasswordByMobile";
    //TODO 获取登录记录
    public final static String urlLoginRecord = url + "v1/userrecord?userid=";
    //TODO 三方绑定手机号
    public final static String urlBindPhone = url + "v1/user/bindMobile";
    //TODO 用户消息接口
    public final static String urlMessage = url + "v1/usermessages";
    //TODO 通讯录上传
    public final static String urlAddressBook = url + "v1/UserFollow/addressBook";
    //TODO 圈子榜单，排行http://119.29.10.64/v1/Circle/UserCircleWeekRank?circleid=100344&userid=30&page=1&pagesize=10
    public final static String urlCircleWeekRank = url + "v1/Circle/UserCircleWeekRank?circleid=";
    //TODO 周名次 http://119.29.10.64/v1/Circle/UserCircleWeekNum?circleid=100000&userid=1
    public final static String urlStepRankWeekNum = url + "v1/Circle/UserCircleWeekNum?circleid=";
    //TODO 短信邀请
    public final static String urlinviteMsg = url + "v1/userinviter/inviteMsg";
    //TODO 根据旧的密码更换密码
    public final static String urlByOldPassWord = url + "v1/user/modifyPasswordByOldPassword";
    //TODO 协议接口
    public final static String urlProtocol = url + "v1/Zfile/getFile";
    //TODO 绑定解绑三方借口
    public final static String urlBindThird = url + "v1/user/bindOpenid";
    public final static String urlGetBindStates = url + "v1/user/checkUserStatus";
    //TODO 退出APP接口
    public final static String urlLogOut = url + "v1/user/logOut";
    //TODO 查询关注关系
    public final static String urlFollowStatus = url + "v1/UserFollow/followStatus";
    //TODO 位置信息
    public final static String urlLocation = url + "v1/UserLocation/setLocation";
    //TODO share
    public final static String urlShare = "http://share.runmoneyin.com/in.html?";

    public final static String urlShareCircle = "http://share.runmoneyin.com/dd.html?";
    //TODO 个人码
    public final static String urlShareIc = urlShare + "ic=";
    //TODO 圈子码
    public final static String urlShareCd = urlShareCircle + "cd=";
    //TODO 商户发红包
    public final static String urlSendTaskRedBag = url + "v1/Redpacket";
    //TODO VIP
    public final static String urlVip = url + "v1/UserVip";
    //TODO 用户领取商家红包
    public final static String urlRevRedPkg = urlRedpacket + "/receiveRed";
    //TODO 商户详情接口
    public final static String urlBusiness = url + "v1/Business";
    //TODO 创建商户
    public final static String urlAddBusiness = urlBusiness + "/addBusiness";
    //TODO 获取用户商户列表
    public final static String urlGetUserBusiness = urlBusiness + "/getUserBusiness";
    //TODO 修改商户信息
    public final static String urlUpdateBusiness = urlBusiness + "/updateBusiness";
    //TODO 删除商户图片
    public final static String urlDeleteBusImg = urlBusiness + "/deleteBusImg";
    //TODO 删除商户图片
    public final static String urlSetDefaultBusiness = urlBusiness + "/setDefaultBusiness";
    //TODO 圈子活动
    public final static String urlLive = url + "v1/Activity/getActivityList";
    //TODO 领取活动奖励（红包）
    public final static String urlLiveGetRed = url + "v1/Activity/redActivity";
    //TODO 我的邀请码
    public final static String urlMyCode = url + "v1/userinviter/getMyCode";
    //TODO 用户当前步数
    public final static String urlCurrentStep = url + "v1/UserStep/";
    //TODO 解除绑定
    public final static String urlUnbindAccount = url + "v1/user/userUntie";
    //TODO 获取我的邀请数据和我的邀请人列表
    public final static String urlMyInviteData = url + "v1/userinviter/getMyInviter";
    //TODO 个人主页复合接口
    public final static String urlUserHome = url + "v1/Zcomposite/ouserData";
    //获取银行卡所属银行
    public final static String URL_GET_CARD_BANK = url + "v1/UserBankCard/getBankCardData";//http://apicloud.mob.com/appstore/bank/card/query?key=appkey&card=6228482898203884775
    //获取用户认证状态
    public final static String GET_PERSON_IDENTIFY_STATE = url + "v1/userauthentication/userAuthState";
    //绑定身份证信息
    public final static String BIND_CARD_PERSON_INFO = url + "v1/userinviter/getMyInviter";
    //检验验证码
    public final static String REAL_AUTH_ALL_INFO = url + "v1/userauthentication/realAuth";
    //获取银行卡列表
    public final static String GET_BANK_LIST = url + "v1/UserBankCard/getCardList";
    //合利宝提现
    public final static String TRANSFER_BY_HELIBAO = url + "v1/withdraw";
    //TODO 商户会员
    public final static String urlSponsorVip = url + "v1/UserVipCus";
}
