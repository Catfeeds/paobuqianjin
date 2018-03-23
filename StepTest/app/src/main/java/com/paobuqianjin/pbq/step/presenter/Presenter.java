package com.paobuqianjin.pbq.step.presenter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.data.bean.gson.param.BindCardPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CheckSignCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostCircleRedPkgParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostInviteCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassWordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostWxQqBindPhoneParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutDearNameParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.QueryFollowStateParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginOutResponse;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.squareup.picasso.NetworkPolicy;

import java.io.File;


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
            return null;
        }
    }

    public void startService(String action, Class<? extends Service> clazz) {
        engine.startService(action, clazz);
    }

    public void stopService(Class<? extends Service> clazz) {
        engine.stopService(clazz);
    }

    public void bindService(String action, Class<? extends Service> clazz) {
        engine.bindService(action, clazz);
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
        engine.userLoginByPhoneNumber(userInfo);
    }

    //TODO 获取验证码
    public void getSignCode(String phone) {
        engine.getSignCode(phone);
    }

    public void getSignCodeLoginBind(String phone) {
        engine.getSignCodeLoginBind(phone);
    }

    public void checkLoginBindPhone(CheckSignCodeParam checkSignCodeParam) {
        engine.checkLoginBindPhone(checkSignCodeParam);
    }

    public void bindLoginPhone(PostWxQqBindPhoneParam postWxQqBindPhoneParam) {
        engine.bindLoginPhone(postWxQqBindPhoneParam);
    }

    public void checkSignCode(CheckSignCodeParam checkSignCodeParam) {
        engine.checkSignCode(checkSignCodeParam);
    }

    public void checkSignCodePassWord(CheckSignCodeParam checkSignCodeParam) {
        engine.checkSignCodePassWord(checkSignCodeParam);
    }

    public void getSignCodePassWord(String phone) {
        engine.getSignCodePassWord(phone);
    }

    public void getLoginRecord(String userid) {
        engine.getLoginRecord(userid);
    }

    public void postNewPassWord(PostPassWordParam postPassWordParam) {
        engine.postNewPassWord(postPassWordParam);
    }

    public void getMsg(String phone) {
        engine.getMsg(phone);
    }

    public void getNearByPeople(double latitude, double longitude) {
        engine.getNearByPeople(latitude, longitude);
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

    public void createCircle(CreateCircleBodyParam createCircleBodyParam) {
        engine.createCircle(createCircleBodyParam);
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

    public void getSponsorRedPkg() {
        engine.getSponsorRedPkg();
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
    public void getUserDynamic(String userid) {
        engine.getUserDynamic(userid);
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

    public void getDynamicVoteList(int id, int userid, int page, int pagesize) {
        engine.getDynamicVoteList(id, userid, page, pagesize);
    }

    public void postContent(PostDynamicContentParam postDynamicContentParam) {
        engine.postContent(postDynamicContentParam);
    }

    public void putVote(PutVoteParam putVoteParam) {
        engine.putVote(putVoteParam);
    }

    //TODO 发布动态
    public void postDynamic(PostDynamicParam postDynamicParam) {
        engine.postDynamic(postDynamicParam);
    }

    //TODO 获取圈子目标
    public void getCircleTarget() {
        engine.getCircleTarget();
    }

    //TODO  获取圈子详情
    public void getCircleDetail(int circleId) {
        engine.getCircleDetail(circleId);
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

    public void getImage(ImageView view, String urlImg) {
        if ("".equals(urlImg)) {
            return;
        }
        engine.getImage(view, urlImg);
    }

    public void getImage(String fileUrl, final ImageView imageView) {
        if (fileUrl == null) {
            return;
        }
        engine.getImage(fileUrl, imageView);
    }

    //TODO 加入圈子
    public void joinCircle(JoinCircleParam joinCircleParam) {
        engine.joinCircle(joinCircleParam);
    }

    //TODO 退出圈子
    public void loginOutCircle(LoginOutParam loginOutParam) {
        engine.loginOutCircle(loginOutParam);
    }

    public void getCircleStepRank(int circleId) {
        engine.getCircleStepRank(circleId, 1, 10);
    }

    public void getUserCircleRank(int circleId) {
        engine.getUserCircleRank(circleId);
    }

    public void getUserCircleRankDetail(int circleId) {
        engine.getUserCircleRankDetail(circleId);
    }

    public void getCircleRechargeRand(int circleId) {
        engine.getCircleRechargeRank(circleId, 1, 10);
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

    //TODO 获取订单详情 WX
    public void getWxPayResultByOrderNo(String orderTradeNo, String payment_type) {
        engine.getWxPayResultByOrderNo(orderTradeNo, payment_type);
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

    public void putTask(String action, int taskId) {
        engine.putTask(action, taskId);
    }

    public void getAllMyRecTask() {
        engine.getAllMyRecTask();
    }

    //TODO 用户段位
    public void getDanList() {
        engine.getDanList();
    }

    public void getUserDan() {
        engine.getUserDan();
    }

    //TODO 用户关注接口
    public void getFollows(String action, int page, int pagesize) {
        engine.getFollows(action, page, pagesize);
    }

    public void postQueryFollowState(QueryFollowStateParam queryFollowStateParam) {
        engine.postQueryFollowState(queryFollowStateParam);
    }

    public void postAddUserFollow(QueryFollowStateParam queryFollowStateParam) {
        engine.postAddUserFollow(queryFollowStateParam);
    }

    //TODO 用户好友接口
    public void getUserFiends() {
        engine.getUserFriends();
    }

    public void getFriendHonor(int page, int pagesize) {
        engine.getFriendHonor(page, pagesize);
    }

    public void getFriendHonorDetail(int page, int pagesize) {
        engine.getFriendHonorDetail(page, pagesize);
    }

    //TODO
    public void getUserBankCard() {
        engine.getUserBankCard();
    }

    //TODO 用户提现
    public void postCrashTo(CrashToParam crashToParam) {
        engine.postCrashTo(crashToParam);
    }

    public void getCrashRecord() {
        engine.getCrashRecord();
    }

    public void getRechargeRecord() {
        engine.getRechargeRecord();
    }

    //TODO 绑定提现账户
    public void bindCrashAccount(BindCardPostParam bindCardPostParam) {
        engine.bindCrashAccount(bindCardPostParam);
    }

    //TODO 邀请好友接口
    public void getInviteDan(int page, int pagesize) {
        engine.getInviteDan(page, pagesize);
    }

    public void getMyInviteMsg() {
        engine.getMyInviteMsg();
    }

    public void postInviteCode(PostInviteCodeParam postInviteCodeParam) {
        engine.postInviteCode(postInviteCodeParam);
    }

    //TODO 获取步币明细
    public void getUserCredit() {
        engine.getUserCredit();
    }

    public void getUserDollarStep() {
        engine.getUserDollarStep();
    }

    public void getUserPackageMoney() {
        engine.getUserPackageMoney();
    }

    //
    public void getWeather(double latitude, double longitude) {
        engine.getWeather(latitude, longitude);
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
}
