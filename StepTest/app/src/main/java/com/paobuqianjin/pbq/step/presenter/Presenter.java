package com.paobuqianjin.pbq.step.presenter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.data.bean.gson.param.BindCardPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CheckSignCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.DynamicParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.squareup.picasso.NetworkPolicy;


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
        mContext = context.getApplicationContext();
        if (instance == null) {
            instance = new Presenter();
        }
        return instance;
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

    public void checkSignCode(CheckSignCodeParam checkSignCodeParam) {
        engine.checkSignCode(checkSignCodeParam);
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
    public void getIncome(String action, int page, int pageSize) {
        engine.getIncome(action, page, pageSize);
    }

    //TODO 获取动态列表
    public void getDynamicIndex(int page, int pagesize) {
        engine.getDynamicIndex(page, pagesize);
    }

    //TODO 获取评论列表
    public void getDynamicCommentList(int dynamicid, int page, int pagesize) {
        engine.getDynamicCommentList(dynamicid, page, pagesize);
    }

    //TODO 发布动态
    public void postDynamic(DynamicParam dynamicParam) {
        engine.postDynamic(dynamicParam);
    }

    //TODO 获取圈子目标
    public void getCircleTarget() {
        engine.getCircleTarget();
    }

    //TODO  获取圈子详情
    public void getCircleDetail(int circleId) {
        engine.getCircleDetail(circleId);
    }

    /*热门界面*/
    //TODO 我创建的圈子
    public void getMyCreateCirlce(int pageIndex) {
        engine.getMyCreateCirlce(pageIndex, 10);
    }

    public void reflashMyCircle(int pageIndex) {
        engine.reflashMyCreateCircle(pageIndex, 10);
    }

    //TODO 我的圈子
    public void getMyHotCircle(int page, int pagesize) {
        engine.getMyHotCircle(page, pagesize);
    }

    //TODO 我加入的圈子
    public void getMyJoinCircle() {
        engine.getMyJoinCircle(1, 10);
    }

    //获取精选圈子
    public void getCircleChoice() {
        engine.getCircleChoice(engine.getId(mContext), 1, 10);
    }

    private void getTest() {
        LocalLog.d(TAG, "getTest() enter  批量测试所有接口API");
    }

    public void getImage(ImageView view, String urlImg) {
        if (urlImg.equals("")) {
            return;
        }
        engine.getImage(view, urlImg);
    }

    //TODO 加入圈子
    public void joinCircle(int circleId) {
        engine.joinCircle(circleId);
    }

    public void getCircleStepRank(int circleId) {
        engine.getCircleStepRank(circleId, 1, 10);
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

    //TODO
    public void getUserBankCard() {
        engine.getUserBankCard();
    }

    //TODO 用户提现
    public void postCrashTo(CrashToParam crashToParam) {
        engine.postCrashTo(crashToParam);
    }

    //TODO 绑定提现账户
    public void bindCrashAccount(BindCardPostParam bindCardPostParam) {
        engine.bindCrashAccount(bindCardPostParam);
    }

    public void getWeather() {
        engine.getWeather();
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
