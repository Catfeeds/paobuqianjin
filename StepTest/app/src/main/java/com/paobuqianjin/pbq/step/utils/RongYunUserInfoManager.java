package com.paobuqianjin.pbq.step.utils;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChatUserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.table.ChatGroupInfo;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChatGroupInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;
import com.paobuqianjin.pbq.step.data.dao.ChatGroupInfoDao;
import com.paobuqianjin.pbq.step.data.dao.ChatUserInfoDao;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2018/6/28.
 */
public class RongYunUserInfoManager {
    private static final String TAG = "RongYunUserInfoManager";
    private static RongYunUserInfoManager rInstance;
    private final Context mContext;
    private Handler workHandler;
    private HandlerThread workThread;
    private ChatGroupInfoDao groupInfoDao;
    private ChatUserInfoDao userInfoDao;

    private RongYunUserInfoManager(Context context) {
        mContext = context;
        openDB();
    }

    public static RongYunUserInfoManager getInstance() {
        return rInstance;
    }

    public static void init(Context context) {
        rInstance = new RongYunUserInfoManager(context);
    }

    public synchronized void openDB() {
        if (workThread == null) {
            workThread = new HandlerThread("rongYunUserInfoManager");
            workThread.start();
            workHandler = new Handler(workThread.getLooper());
            groupInfoDao = new ChatGroupInfoDao(mContext);
            userInfoDao = new ChatUserInfoDao(mContext);
        }
    }

    public void closeDB() {
        try {
            userInfoDao.clearTable();
            groupInfoDao.clearTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (workThread != null) {
//            workThread.quit();
//            workThread = null;
//            workHandler = null;
//            groupInfoDao = null;
//            DataBaseHelper.getUserDataHelper(mContext).close();


        }
    }

    public UserInfo getUserInfo(String id) {

        try {
            ChatUserInfo bean = userInfoDao.queryById(id);
            if (bean != null) return new UserInfo(bean.getId(), bean.getDisplayName(), bean.getAvatarUri());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServerUserInfoById(id);
        return null;
    }

    public void getServerUserInfoById(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", id);
        Presenter.getInstance(mContext).getPaoBuSimple(NetApi.urlGetUserInfo, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                ChatUserInfoResponse groupInfoResponse = new Gson().fromJson(s, ChatUserInfoResponse.class);
                List<ChatUserInfo> listBean = groupInfoResponse.getData();
                if (listBean != null && listBean.size() > 0) {
                    ChatUserInfo bean = listBean.get(0);
                    try {
                        refreshUserInfoDBAndCache(bean);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.e(TAG,"getGroupInfo.onFal "+errorStr);
            }
        });
    }

    public Group getGroupInfo(final String id) {

        //数据库取
        try {
            ChatGroupInfo chatGroupInfo = groupInfoDao.queryGroup(id);
            if(chatGroupInfo!=null)
                return new Group(chatGroupInfo.getId(), chatGroupInfo.getDisplayName(), chatGroupInfo.getLogoUri());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        getServerGroupInfo(id);
        return null;
    }

    /**
     * 获取服务器的群组信息
     * @param id
     */
    private void getServerGroupInfo(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "circle");
        params.put("groupid", id);
        Presenter.getInstance(mContext).getPaoBuSimple(NetApi.urlGetGroupInfo, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                ChatGroupInfoResponse groupInfoResponse = new Gson().fromJson(s, ChatGroupInfoResponse.class);
                List<ChatGroupInfo> listBean = groupInfoResponse.getData();
                if (listBean != null && listBean.size() > 0) {
                    ChatGroupInfo bean = listBean.get(0);
                    try {
                        refreshGroupInfoDBAndCache(bean);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.e(TAG,"getGroupInfo.onFal "+errorStr);
            }
        });
    }

    /**
     * 获取所有的群组信息并且覆盖数据库
     * @param isRefresh
     */
    public void getAllGroupInfo2DB(boolean isRefresh) {
        if(workThread == null) openDB();
        workHandler.post(new Runnable() {
            @Override
            public void run() {
                LocalLog.d(TAG, "Thread.currentThread().getName()： " + Thread.currentThread().getName());
                try {
                    Map<String, String> params = new HashMap<>();
                    params.put("action", "circle");
                    String result = Presenter.getInstance(mContext).getPaoBuSimpleSync(NetApi.urlGetGroupInfo, params);
                    ChatGroupInfoResponse groupInfoResponse = new Gson().fromJson(result, ChatGroupInfoResponse.class);
                    List<ChatGroupInfo> listData = groupInfoResponse.getData();
                    if (listData != null) {
                        for (ChatGroupInfo chatGroupInfo :
                                listData) {
                            groupInfoDao.updateGroup(chatGroupInfo);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 获取所有的好友信息并且覆盖数据库
     */
    public void getAllUserInfo2DB() {
        if(workThread == null) openDB();
        workHandler.post(new Runnable() {
            @Override
            public void run() {
                LocalLog.d(TAG,"Thread.currentThread().getName()： "+Thread.currentThread().getName());
                try {
                    String result = Presenter.getInstance(mContext).getPaoBuSimpleSync(NetApi.urlGetUserInfo, null);
                    ChatUserInfoResponse userInfoResponse = new Gson().fromJson(result, ChatUserInfoResponse.class);
                    List<ChatUserInfo> listData = userInfoResponse.getData();
                    userInfoDao.clearTable();
                    if (listData != null) {
                        for (ChatUserInfo chatUserInfo:
                                listData) {
                            userInfoDao.insert(chatUserInfo);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void refreshUserInfoDBAndCache(ChatUserInfo userInfo) throws SQLException {
        if(userInfo == null) return;
        userInfoDao.updateOrinsert(userInfo);
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(userInfo.getId(), userInfo.getDisplayName(), userInfo.getAvatarUri()));
    }

    public void refreshGroupInfoDBAndCache(ChatGroupInfo groupInfo) throws SQLException {
        if(groupInfo == null) return;
        groupInfoDao.updateGroup(groupInfo);
        RongIM.getInstance().refreshGroupInfoCache(new Group(groupInfo.getId(), groupInfo.getDisplayName(), groupInfo.getLogoUri()));
    }
}
