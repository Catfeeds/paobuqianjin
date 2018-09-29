package com.paobuqianjin.pbq.step.presenter;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.data.bean.gson.param.AddBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.BindCardPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CheckSignCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.FeedBackParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.GetUserBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostBindUnBindWqParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostCircleRedPkgParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostInviteCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassByOldParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassWordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostWxQqBindPhoneParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutDearNameParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutUserInfoParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.QueryFollowStateParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.RedPkgRecParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskSponsorParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.VipPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.NearByInterface;
import com.paobuqianjin.pbq.step.presenter.im.OnIdentifyLis;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.squareup.picasso.NetworkPolicy;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;


/**
 * Created by pbq on 2017/11/29.
 */

public final class Presenter {
    private final static String TAG = Presenter.class.getSimpleName();
    private static Presenter instance;
    private Engine engine;
    private static Context mContext;
    private int defaultPage = 1;
    private int defaultPageSize = 10;
    private final static String Style = "?x-oss-process=image/resize,m_mfit,";
    private UserInfoResponse.DataBean currentUser;
    private Handler mainHandler;

    private Presenter() {
        engine = Engine.getEngine(mContext);
    }

    public static synchronized Presenter getInstance(Context context) {
        if (context != null) {
            mContext = context.getApplicationContext();
            if (instance == null) {
                instance = new Presenter();
            }
            return instance;
        } else {
            if (mContext != null) {
                return instance;
            } else {
                return null;
            }
        }
    }

    public void startService(String action, Class<? extends Service> clazz) {
        engine.startService(action, clazz);
    }

    private Handler getMainHandler() {
        if (mainHandler == null) {
            return (mainHandler = new Handler(mContext.getMainLooper()));
        } else
            return mainHandler;
    }

    public void stopService(Class<? extends Service> clazz) {
        engine.stopService(clazz);
    }

    public boolean bindService(String action, Class<? extends Service> clazz) {
        return engine.bindService(action, clazz);
    }

    public void refreshStep() {
        engine.refreshStep();
    }

    public void unbindStepService() {
        engine.unbindStepService();
    }


    public SharedPreferences getSharePreferences() {
        return engine.getSharePreferences(mContext);
    }

    public boolean getLogFlg() {
        return engine.getLogFlag(mContext);
    }

    public void steLogFlg(boolean isLogin) {
        engine.setLogFlag(mContext, isLogin);
    }

    public void setToken(Context context, String user_token) {
        engine.setToken(context, user_token);
    }

    public void setReadCrashProtocol(Context context, boolean readFlag) {
        engine.setReadCrashProtocol(context, readFlag);
    }

    public boolean getReadCrashProtocol(Context context) {
        return engine.getReadCrashProtocol(context);
    }

    public String getToken(Context context) {
        return engine.getToken(context);
    }


    public void setPayResultCode(int errorCode) {
        engine.setPayResultCode(mContext, errorCode);
    }

    public int getPayResultCode() {
        return engine.getPayResultCode(mContext);
    }

    public void setOutTradeNo(String outTradeNo) {
        engine.setOutTradeNo(mContext, outTradeNo);
    }

    public void setTradeStyle(String tradeStyle) {
        engine.setTradeStyle(mContext, tradeStyle);
    }

    public String getTradeStyle() {
        return engine.getTradeStyle(mContext);
    }

    public String getOutTradeNo() {
        return engine.getOutTradeNo(mContext);
    }

    public int getId() {
        return engine.getId(mContext);
    }

    public void setId(int id) {
        engine.setId(mContext, id);
    }

    public String getNo() {
        return engine.getNo(mContext);
    }

    public void setNo(String no) {
        engine.setNo(mContext, no);
    }

    public String getAvatar(Context context) {
        return engine.getAvatar(context);
    }

    public void setAvatar(Context context, String avatar) {
        engine.setAvatar(context, avatar);
    }

    public String getNickName(Context context) {
        return engine.getNickName(context);
    }

    public void setNickName(Context context, String avatar) {
        engine.setNickName(context, avatar);
    }

    public void setTarget(Context context, int target) {
        engine.setTarget(context, target);
    }

    public int getTarget(Context context) {
        return engine.getTarget(context);
    }

    public void setMobile(Context context, String mobile) {
        engine.setMobile(context, mobile);
    }

    public String getMobile(Context context) {
        return engine.getMobile(context);
    }


    public Context getAppContext() {
        Context appContext = null;
        if (mContext != null) {
            appContext = mContext.getApplicationContext();
        }
        return appContext;
    }

    /*添加业务*/
    public void getCirCleType() {
        LocalLog.d(TAG, "getCirCleType() enter");
        engine.getCircleType();
    }

    public void userLoginByPhoneNumber(String[] userInfo) {
        engine.userLoginByPhoneOrNo(userInfo);
    }

    //TODO 获取验证码
    public void getSignCode(String phone) {
        engine.getSignCode(phone);
    }

    public boolean getSignCodeLoginBind(String phone) {
        return engine.getSignCodeLoginBind(phone);
    }

   /* public void checkLoginBindPhone(CheckSignCodeParam checkSignCodeParam) {
        engine.checkLoginBindPhone(checkSignCodeParam);
    }*/

    public void bindLoginPhone(PostWxQqBindPhoneParam postWxQqBindPhoneParam) {
        engine.bindLoginPhone(postWxQqBindPhoneParam);
    }

    public void checkSignCode(CheckSignCodeParam checkSignCodeParam) {
        engine.checkSignCode(checkSignCodeParam);
    }

    /*  public void checkSignCodePassWord(CheckSignCodeParam checkSignCodeParam) {
          engine.checkSignCodePassWord(checkSignCodeParam);
      }
  */
    public boolean getSignCodePassWord(String phone) {
        return engine.getSignCodePassWord(phone);
    }

    public void postPassByOlder(PostPassByOldParam postPassByOldParam) {
        engine.postPassByOlder(postPassByOldParam);
    }

    public void getLoginRecord(String userid) {
        engine.getLoginRecord(userid);
    }

    public void postNewPassWord(PostPassWordParam postPassWordParam) {
        engine.postNewPassWord(postPassWordParam);
    }

    public boolean getMsg(String phone) {
        return engine.getMsg(phone);
    }

    public void getNearByPeople(double latitude, double longitude, int page, int pagesize, final NearByInterface nearByInterface) {
        engine.getNearByPeople(latitude, longitude, page, pagesize, nearByInterface);
    }

    public void registerByPhoneNumber(String[] userInfo) {
        engine.registerByPhoneNumber(userInfo);
    }

    public void refreshPassWord() {
        engine.refreshPassWorld();
    }

    public void getUserInfo(int userId) {
        engine.getUserInfo(userId);
    }

    public void putUserInfo(int id, PutUserInfoParam putUserInfoParam) {
        engine.putUserInfo(id, putUserInfoParam);
    }

    public void createCircle(CreateCircleBodyParam createCircleBodyParam) {
        engine.createCircle(createCircleBodyParam);
    }

    public void putCircle(CreateCircleBodyParam createCircleBodyParam, int circleId) {
        engine.putCircle(createCircleBodyParam, circleId);
    }

    public void getUserRecoder(int userId) {
        engine.getUserRecord(userId);
    }

    public void getUserStep() {
        engine.getUserStep();
    }

    public void postUserStep(int step_num) {
        engine.postUserStep(step_num);
    }

    //TODO 获取收益
    public void getHomePageIncome(String action, int page, int pageSize) {
        engine.getHomePageIncome(action, page, pageSize);
    }

    public boolean getCurrentStep(final InnerCallBack innerCallBack) {
        return engine.getCurrentStep(innerCallBack);
    }

    public void getSponsorRedPkg() {
        engine.getSponsorRedPkg();
    }

    public void businessDetail(int businessid, final InnerCallBack innerCallBack) {
        engine.businessDetail(businessid, innerCallBack);
    }

    public void AddBusiness(AddBusinessParam addBusinessParam, final InnerCallBack innerCallBack) {
        engine.AddBusiness(addBusinessParam, innerCallBack);
    }

    public void getUserBusiness(GetUserBusinessParam param, final InnerCallBack innerCallBack) {
        engine.getUserBusiness(param, innerCallBack);
    }

    public void deleteBusiness(int businessId, final InnerCallBack innerCallBack) {
        engine.deleteBusiness(businessId, innerCallBack);
    }

    public void updateBusiness(AddBusinessParam addBusinessParam, final InnerCallBack innerCallBack) {
        engine.updateBusiness(addBusinessParam, innerCallBack);
    }

    public void setDefaultBusiness(int businessId, final InnerCallBack innerCallBack) {
        engine.setDefaultBusiness(businessId, innerCallBack);
    }

    //TODO 领取商家红包
    public void postRedPkgRec(RedPkgRecParam pkgRecParam, final InnerCallBack innerCallBack) {
        engine.postRedPkgRec(pkgRecParam, innerCallBack);
    }

    //TODO 获取收益
    public void getIncome(String action, int page, int pageSize) {
        engine.getIncome(action, page, pageSize);
    }

    //TODO 获取动态列表
    public void getDynamicIndex(int page, int pagesize) {
        engine.getDynamicIndex(page, pagesize);
    }

    //TODO  获取用户动态
    public void getUserHome(String userid, String userno, int page, int pagesize, InnerCallBack userCenterCallBack) {
        engine.getUserHome(userid, userno, page, pagesize, userCenterCallBack);
    }

    public void getMyDynamic(int pageIndex, int pageSize) {
        engine.getMyDynamic(pageIndex, pageSize);
    }

    public void getUserInfo(String userid) {
        engine.getUserInfo(userid);
    }

    //TODO 获取评论列表
    public void getDynamicCommentList(int dynamicid, int page, int pagesize) {
        engine.getDynamicCommentList(dynamicid, page, pagesize);
    }

    public void getDynamicDetail(int id) {
        engine.getDynamicDetail(id);
    }

    public void deleteDynamic(int dynamicId, final InnerCallBack innerCallBack) {
        engine.deleteDynamic(dynamicId, innerCallBack);
    }

    public void getDynamicVoteList(int id, int userid, int page, int pagesize) {
        engine.getDynamicVoteList(id, userid, page, pagesize);
    }

    public void postContent(PostDynamicContentParam postDynamicContentParam) {
        engine.postContent(postDynamicContentParam);
    }

    public void putVote(PutVoteParam putVoteParam, InnerCallBack innerCallBack) {
        engine.putVote(putVoteParam, innerCallBack);
    }

    public void putVote(PutVoteParam putVoteParam) {
        engine.putVote(putVoteParam);
    }

    //TODO
    public void postFeedBack(FeedBackParam feedBackParam) {
        engine.postFeedBack(feedBackParam);
    }

    //TODO 发布动态
    public void postDynamic(PostDynamicParam postDynamicParam) {
        engine.postDynamic(postDynamicParam);
    }

    //TODO 获取圈子目标
    public void getCircleTarget() {
        engine.getCircleTarget();
    }

    public void getCircleTargetEdit() {
        engine.getCircleTargetEdit();
    }

    //TODO  获取圈子详情
    public void getCircleDetail(int circleId) {
        engine.getCircleDetail(circleId);
    }

    public void getCircleDetailView(TextView myName, ImageView imageView, TextView stepNum, int circleId) {
        engine.getCircleDetailView(myName, imageView, stepNum, circleId);
    }

    public void postCircleRedPkg(PostCircleRedPkgParam postCircleRedPkgParam) {
        engine.postCircleRedPkg(postCircleRedPkgParam);
    }

    public void getCircleIsRedPackage(int circleId) {

    }

    public void deleteCircle(int circleId) {
        engine.deleteCircle(circleId);
    }

    /*热门界面*/
    //TODO 我创建的圈子
    public void getMyCreateCirlce(int pageIndex, int pagesize, String keyword) {
        engine.getMyCreateCirlce(pageIndex, pagesize, keyword);
    }

    public void reflashMyCircle(int pageIndex) {
        engine.reflashMyCreateCircle(pageIndex, 10);
    }

    //TODO 我的圈子
    public void getMyHotCircle(int page, int pagesize) {
        engine.getMyHotCircle(page, pagesize);
    }

    public void getAllMyCircle(int page, int pagesize) {
        engine.getAllMyCircle(page, pagesize);
    }

    public void getLiveList(final InnerCallBack innerCallBack, int page, int pagesize) {
        engine.getLiveList(innerCallBack, page, pagesize);
    }

    public void getMyCode(InnerCallBack innerCallBack) {
        engine.getMyCode(innerCallBack);
    }

    //TODO 我加入的圈子
    public void getMyJoinCircle(int page, int pagesize, String keyWord) {
        engine.getMyJoinCircle(page, pagesize, keyWord);
    }

    //获取精选圈子
    public void getCircleChoice(int page, int pageSize) {
        engine.getCircleChoice(page, pageSize);
    }

    public void getMoreCircle(int page, int pageSize, String keyword) {
        engine.getMoreCircle(page, pageSize, keyword);
    }

    private void getTest() {
        LocalLog.d(TAG, "getTest() enter  批量测试所有接口API");
    }

    public void getImage(final ImageView view, final String urlImg) {
        if ("".equals(urlImg)) {
            return;
        }
        if (view != null) {
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    engine.getImage(view, styleSizeImage(view, urlImg));
                }
            });
        }
    }

    private String styleSizeImage(View view, final String urlImg) {
        if (urlImg == null) {
            return null;
        }
        int w = view.getMeasuredWidth();
        int h = view.getMeasuredHeight();
        if (w == 0 || h == 0 || urlImg.contains("http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com")) {
            return urlImg;
        } else {
            String h_size = "h_" + String.valueOf(h);
            String w_size = "w_" + String.valueOf(w);
            String url = urlImg + Style + h_size + "," + w_size;
            LocalLog.d(TAG, "style imge" + url);
            return url;
        }
    }

    public void getAdImage(final ImageView imageView, final String keepUrl) {
        engine.getAdImage(imageView, keepUrl);
    }

    public void downLoadAdImage(String urlImage, ImageView view, int placeholderImageId, int errorId) {
        engine.downLoadAdImage(urlImage, view, placeholderImageId, errorId);
    }

    public void getPlaceErrorImage(final ImageView view, final String urlImage, final int placeholderImageId, final int errorId) {
        if (view != null) {
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    engine.getPlaceErrorImage(view, styleSizeImage(view, urlImage), placeholderImageId, errorId);
                }
            });
        }

    }

    public void getImage(final ImageView view, final String urlImg, final int targetWidth, final int targetHeight) {
        if ("".equals(urlImg)) {
            return;
        }
        if (view != null) {
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    engine.getImage(view, styleSizeImage(view, urlImg), targetWidth, targetHeight);
                }
            });
        }
    }

    public void getImage(String fileUrl, final ImageView imageView, int targetWidth, int targetHeight) {
        if (TextUtils.isEmpty(fileUrl)) {
            return;
        }
        if (targetHeight < 0 || targetWidth < 0) {
            LocalLog.e(TAG, "At least one dimension has to be positive number");
            return;
        }

        engine.getImage(fileUrl, imageView, targetWidth, targetHeight);
    }

    public void getImage(String fileUrl, final ImageView imageView) {
        if (fileUrl == null) {
            return;
        }
        engine.getImage(fileUrl, imageView);
    }

    public void getOriginImage(String fileUrl, final ImageView imageView) {
        if (fileUrl == null) {
            return;
        }
        engine.getOriginImage(fileUrl, imageView);
    }

    //TODO 加入圈子
    public void joinCircle(JoinCircleParam joinCircleParam) {
        engine.joinCircle(joinCircleParam);
    }

    //TODO 退出圈子
    public void loginOutCircle(LoginOutParam loginOutParam) {
        engine.loginOutCircle(loginOutParam);
    }

    public void getCircleStepRank(int circleId, int page, int pagesize) {
        engine.getCircleStepRank(circleId, page, pagesize);
    }

    public void getCircleRankMoreDetail(int circleId, int page, int pagesize, final InnerCallBack innerCallBack) {
        engine.getCircleRankMoreDetail(circleId, page, pagesize, innerCallBack);
    }

    public void getUserCircleRank(int circleId) {
        engine.getUserCircleRank(circleId);
    }

    public void getCircleDetailInCircleDan(int circleId) {
        engine.getCircleDetailInCircleDan(circleId);
    }

    public void getCircleStepRankDay(int circleId, int page, int pagesize) {
        engine.getCircleStepRankDay(circleId, page, pagesize);
    }

    public void getCircleStepRankWeek(int circleId, int page, int pagesize) {
        engine.getCircleStepRankWeek(circleId, page, pagesize);
    }

    public void getCircleRankNum(int circleId) {
        engine.getCircleRankNum(circleId);
    }

    public void getUserCircleRankDetail(int circleId) {
        engine.getUserCircleRankDetail(circleId);
    }

    public void getCircleRechargeRand(int circleId, int page, int pagesize) {
        engine.getCircleRechargeRank(circleId, page, pagesize);
    }

    public void joinCircle(int circleId, String password) {
        engine.joinCircle(circleId, password);
    }

    // TODO 获取标签列表
    public void getCircleTag() {
        engine.getCircleTag();
    }

    public NetworkPolicy getNetworkPolicy() {
        return engine.getNetworkPolicy();
    }

    //TODO 广播处理
    public void handBroadcast(Intent intent) {
        engine.handBroadcast(intent);
    }

    //TODO 获取圈子成员
    public void getCircleMemberAll(int circleId, int pageInex, int pageSize) {
        engine.getCircleMemberAll(circleId, pageInex, pageSize);
    }

    public void searchCircleMember(String circleid, int pageIndex, int pageSize, String keyWord, final InnerCallBack innerCallBack) {
        engine.searchCircleMember(circleid, pageIndex, pageSize, keyWord, innerCallBack);
    }

    public void deleteCircleMember(String idStr) {
        engine.deleteCircleMember(idStr);
    }

    public void modifyDearName(PutDearNameParam putDearNameParam) {
        engine.modifyDearName(putDearNameParam);
    }


    //TODO 圈子订单WX
    public void postCircleOrder(PayOrderParam wxPayOrderParam) {
        engine.postWxPayOrder(wxPayOrderParam);
    }

    public void postVipNo(VipPostParam vipPostParam, InnerCallBack innerCallBack) {
        engine.postVipNo(vipPostParam, innerCallBack);
    }

    public void postVipSponsorNo(VipPostParam vipPostParam, InnerCallBack innerCallBack) {
        engine.postVipSponsorNo(vipPostParam, innerCallBack);
    }

    //TODO 获取订单详情 WX
    public void getWxPayResultByOrderNo(String orderTradeNo, String payment_type) {
        engine.getWxPayResultByOrderNo(orderTradeNo, payment_type);
    }

    //TODO 云闪付订单查询
    public void postYsPayResultByOrderNo(String orderTraderNo, InnerCallBack innerCallBack) {
        engine.postYsPayResultByOrderNo(orderTraderNo, innerCallBack);
    }

    //TODO 三方登录
    public void postThirdPartyLogin(ThirdPartyLoginParam thirdPartyLoginParam) {
        engine.PostThirdPartyLogin(thirdPartyLoginParam);
    }

    //TODO 任务接口
    public void taskRelease(TaskReleaseParam taskReleaseParam) {
        engine.taskRelease(taskReleaseParam);
    }

    public void taskMyRelease(int page, int pagesize) {
        engine.taskMyRelease(page, pagesize);
    }

    public void getReleaseRecord(int page, int pagesize) {
        engine.getReleaseRecord(page, pagesize);
    }

    public void getTaskDetail(int taskId) {
        engine.getTaskDetail(taskId);
    }

    public void getTaskDetailRec(int taskId) {
        engine.getTaskDetailRec(taskId);
    }

    public void putTask(String action, int taskId, InnerCallBack innerCallBack) {
        engine.putTask(action, taskId, innerCallBack);
    }

    public void getAllMyRecTask(int pageIndex, int pagesize) {
        engine.getAllMyRecTask(pageIndex, pagesize);
    }

    //TODO 用户段位
    public void getDanList() {
        engine.getDanList();
    }

    public void getUserDan() {
        engine.getUserDan();
    }

    //TODO 用户关注接口
    public void getFollows(String action, int page, int pagesize, String keyWord, InnerCallBack innerCallBack) {
        engine.getFollows(action, page, pagesize, keyWord, innerCallBack);
    }

    public void postQueryFollowState(QueryFollowStateParam queryFollowStateParam) {
        engine.postQueryFollowState(queryFollowStateParam);
    }

    public void postAddUserFollow(QueryFollowStateParam queryFollowStateParam) {
        engine.postAddUserFollow(queryFollowStateParam);
    }

    public void postAddUserFollow(final InnerCallBack innerCallBack, final int followid) {
        engine.postAddUserFollow(innerCallBack, followid);
    }

    public void postUserStatus(Button button, int followid) {
        engine.postUserStatus(button, followid);
    }

    //TODO 用户好友接口
    public void getUserFiends(int pageIndex, int pagesize, String keyWord) {
        engine.getUserFriends(pageIndex, pagesize, keyWord);
    }

    public void getFriendHonor(int page, int pagesize) {
        engine.getFriendHonor(page, pagesize);
    }

    public void getFriendHonorDetail(int page, int pagesize) {
        engine.getFriendHonorDetail(page, pagesize);
    }

    public void getFriendWeekHonor(int page, int pagesize) {
        engine.getFriendWeekHonor(page, pagesize);
    }

    //TODO
    public void getUserBankCard() {
        engine.getUserBankCard();
    }

    //TODO 用户提现
    public void postCrashTo(CrashToParam crashToParam) {
        engine.postCrashTo(crashToParam);
    }

    public void getCrashRecord(int pageIndex, int pagesize) {
        engine.getCrashRecord(pageIndex, pagesize);
    }

    public void getRechargeRecord(int page, int pagesize) {
        engine.getRechargeRecord(page, pagesize);
    }

    //TODO 绑定提现账户
    public void bindCrashAccount(BindCardPostParam bindCardPostParam) {
        engine.bindCrashAccount(bindCardPostParam);
    }

    //TODO 邀请好友接口
    public void getInviteDan(int page, int pagesize) {
        engine.getInviteDan(page, pagesize);
    }

    public void getMyInviteMsg(final InnerCallBack innerCallBack, int page, int pagesize) {
        engine.getMyInviteMsg(innerCallBack, page, pagesize);
    }

    public void postInviteCode(PostInviteCodeParam postInviteCodeParam) {
        engine.postInviteCode(postInviteCodeParam);
    }

    //TODO 获取积分明细
    public void getUserCredit(int page, int pagesize) {
        engine.getUserCredit(page, pagesize);
    }

    public void getUserDollarStep() {
        engine.getUserDollarStep();
    }

    public void getUserPackageMoney() {
        engine.getUserPackageMoney();
    }

    //
    public void protocol(String action) {
        engine.protocol(action);
    }


    public double[] getLocation() {
        return engine.getLocation();
    }

    public void postBindWq(PostBindUnBindWqParam postBindUnBindWqParam) {
        engine.postBindWq(postBindUnBindWqParam);
    }

    public void postUnBind(String action, TextView view) {
        engine.postUnBind(action, view);
    }

    public void getBindStates() {
        engine.getBindStates();
    }

    public void postFollowStatus(final Button button, int followId) {
        engine.postFollowStatus(button, followId);
    }

    public void getWeather(double latitude, double longitude) {
        engine.getWeather(latitude, longitude);
    }

    public void getMessage(int typeid, int page, int pagesize) {
        engine.getMessage(typeid, page, pagesize);
    }

    public void postAddressBook(String addressBook) {
        engine.postAddressBook(addressBook);
    }

    public void inviteMsg(String phoneNum, Button button) {
        engine.inviteMsg(phoneNum, button);
    }

    public void postTaskSponsorRelease(TaskSponsorParam taskSponsorParam) {
        engine.postTaskSponsorRelease(taskSponsorParam);
    }

    public void setNetworkPolicy(NetworkPolicy networkPolicy) {
        engine.setNetworkPolicy(networkPolicy);
    }

    //call onResume
    public void attachUiInterface(CallBackInterface uiCallBackInterface) {
        LocalLog.d(TAG, "attachUiInterface() ");
        engine.attachUiInterface(uiCallBackInterface);

    }

    //call onDestroy
    public void dispatchUiInterface(CallBackInterface uiCallBackInterface) {
        LocalLog.d(TAG, "dispatchUiInterface() enter");
        engine.dispatchUiInterface(uiCallBackInterface);
    }

    public UserInfoResponse.DataBean getCurrentUser() {
        return currentUser;
    }

    public void getIdentifyStatu(final Activity activity, final OnIdentifyLis onIdentifyLis) {
        engine.getVerifyIdentify(activity, onIdentifyLis);
    }

    public void setCurrentUser(UserInfoResponse.DataBean currentUser) {
        this.currentUser = currentUser;
    }

    public void postPaoBuSimple(String url, Map<String, String> params, PaoCallBack callBack) {
        engine.post(url, params, callBack);
    }

    public void deletePaoBuSimple(String url, Map<String, String> params, PaoCallBack callBack) {
        engine.delete(url, params, callBack);
    }

    public void getPaoBuSimple(String url, Map<String, String> params, PaoCallBack callBack) {
        engine.get(url, params, callBack);
    }

    public String getPaoBuSimpleSync(String url, Map<String, String> params) throws IOException {
        return engine.getSync(url, params);
    }

    public void putPaoBuSimple(String url, Map<String, String> params, PaoCallBack callBack) {
        engine.put(url, params, callBack);
    }
}
