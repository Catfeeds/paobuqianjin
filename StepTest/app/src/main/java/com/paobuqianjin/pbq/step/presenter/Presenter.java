package com.paobuqianjin.pbq.step.presenter;

import android.app.Service;
import android.content.Context;
import android.widget.ImageView;

import com.baidu.mapapi.cloud.CloudEvent;
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

    public boolean getLogFlg() {
        return engine.getLogFlag(mContext);
    }

    public void steLogFlg(boolean isLogin) {
        engine.setLogFlag(mContext, isLogin);
    }

    public int getId() {
        return engine.getId(mContext);
    }

    public void setId(int id) {
        engine.setId(mContext, id);
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

    public void getMsg(String phone) {
        engine.getMsg(phone);
    }

    public void getNearByPeople() {
        engine.getNearByPeople();
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

    public void getUserStep(int id) {
        engine.getUserStep(id);
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

    // 获取标签列表
    public void getCircleTag() {
        engine.getCircleTag();
    }

    public NetworkPolicy getNetworkPolicy() {
        return engine.getNetworkPolicy();
    }

    //TODO 三方登录
    public void PostThirdPartyLogin(ThirdPartyLoginParam thirdPartyLoginParam) {
        engine.PostThirdPartyLogin(thirdPartyLoginParam);
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
