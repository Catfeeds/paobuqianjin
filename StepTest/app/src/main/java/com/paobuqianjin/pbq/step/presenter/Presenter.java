package com.paobuqianjin.pbq.step.presenter;

import android.app.Service;
import android.content.Context;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginCallBackInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;


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

    public static Presenter getInstance(Context context) {
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

    /*热门界面*/
    //获取我的圈子
    public void getCircleMy() {
        engine.getCircleMy(engine.getId(mContext),
                1, 10);
    }

    //获取精选圈子
    public void getCircleChoice() {
        engine.getCircleChoice(engine.getId(mContext), 1, 10);
    }

    public void getImage(ImageView view, String urlImg) {
        engine.getImage(view, urlImg);
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
