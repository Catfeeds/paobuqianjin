package com.paobuqianjin.pbq.step.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2018/6/21.
 */

public class RongYunChatUtils implements RongIMClient.OnReceiveMessageListener, RongIM.UserInfoProvider, RongIM.ConversationClickListener, RongIM.GroupInfoProvider {

    private static final String TAG = "RongYunChatUtils";
    private static RongYunChatUtils mRongUtils;

    private Context mContext;
    private boolean isRefreshToken;

    private RongYunChatUtils(Context context) {
        this.mContext = context;
        initLis();
        RongYunUserInfoManager.init(context);
    }

    public static RongYunChatUtils getInstance() {
        return mRongUtils;
    }


    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @param callback 连接回调。
     * @return RongIM  客户端核心类的实例。
     */
    public void connect(final Context context, final String token, final RongIMClient.ConnectCallback callback) {
        LocalLog.d(TAG, "chattoken : " + token);
        if (context.getApplicationInfo().packageName.equals("com.paobuqianjin.pbq.step")) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LocalLog.e("RongYunChatUtils", "--onTokenIncorrect Token 错误");
                    if (!isRefreshToken) {
                        isRefreshToken = true;
                        getChatTokenAndConnect(callback);
                    }

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LocalLog.d("RongYunChatUtils", "--onSuccess" + userid);
                    RongYunUserInfoManager.getInstance().getUserInfo(userid);
                    SharedPreferencesUtil.put(Constants.SP_RONGYUN_CHAT_TOKEN, token);
                    if(callback!=null) callback.onSuccess(userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LocalLog.e("RongYunChatUtils", "--onError" + errorCode.getMessage());
                    if(callback!=null) callback.onError(errorCode);
                }
            });
        }
    }

    /**
     * 获取新的token并且连接服务器
     * @param callback
     */
    public void getChatTokenAndConnect(final RongIMClient.ConnectCallback callback) {
        Presenter.getInstance(PaoBuApplication.getApplication()).getPaoBuSimple(NetApi.urlGetChatToken, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int error = jsonObject.getInt("error");
                    if (error == 0) {
                        String chat_token = jsonObject.getJSONObject("data").getString("chat_token");
                        if (!TextUtils.isEmpty(chat_token)) {
                            connect(PaoBuApplication.getApplication(),chat_token,callback);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
            }
        });
    }

    public void quit() {
        if (!SharedPreferencesUtil.get(Constants.SP_RONGYUN_CHAT_TOKEN, "").equals("")) {
            SharedPreferencesUtil.put(Constants.SP_RONGYUN_CHAT_TOKEN, "");
            RongIM.getInstance().logout();
            RongYunUserInfoManager.getInstance().closeDB();
            isRefreshToken = false;
        }
    }

    public static void init(Context context) {
        if (mRongUtils == null) {
            synchronized (RongYunChatUtils.class) {
                if (mRongUtils == null) {
                    mRongUtils = new RongYunChatUtils(context);
                }
            }
        }
    }

    private void initLis() {
        RongIM.getInstance().setOnReceiveMessageListener(this);
        RongIM.setUserInfoProvider(this, true);
        RongIM.setGroupInfoProvider(this, true);
        RongIM.getInstance().setConversationClickListener(this);
    }

    @Override
    public boolean onReceived(Message message, int i) {
        LocalLog.d(TAG,"onReceived : "+ message.toString());
        return false;
    }

    /**
     *
     * @param activity
     * @param conversationType 个人：ConversationType.PRIVATE 、小组：Conversation.ConversationType.GROUP
     * @param targetId id
     * @param targetTitle name
     */
    public void chatTo(Activity activity, Conversation.ConversationType conversationType,String targetId, String targetTitle) {
        /**
         * 启动单聊界面。
         *
         * @param context      应用上下文。
         * @param targetUserId 要与之聊天的用户 Id。
         * @param title        聊天的标题，开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
         *                     获取该值, 再手动设置为聊天界面的标题。
         */
//        RongIM.getInstance().startPrivateChat();


        RongIM.getInstance().startConversation(activity, conversationType, targetId, targetTitle);
    }

    /**
     * 只要根据s能返回相应的userinfo，他就不会再次调用了
     *
     * @param s
     * @return
     */
    @Override
    public UserInfo getUserInfo(String s) {
        LocalLog.d(TAG,"getUserInfo : "+s);
        return RongYunUserInfoManager.getInstance().getUserInfo(s);
    }

    @Override
    public Group getGroupInfo(String s) {
        LocalLog.d(TAG,"getGroupInfo : "+s);
        return RongYunUserInfoManager.getInstance().getGroupInfo(s);
    }

    /**
     * 当点击用户头像后执行。
     *
     * @param context          上下文。
     * @param conversationType 会话类型。
     * @param userInfo         被点击的用户的信息。
     * @param s         会话 id
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
     */
    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
        Intent intent = new Intent();
        intent.putExtra("userid", Integer.parseInt(userInfo.getUserId()));
//        intent.setClass(context, UserCenterActivity.class);
        intent.setClass(context, FriendDetailActivity.class);
        context.startActivity(intent);
        return true;
    }

    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
        return false;
    }

    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        return false;
    }

    @Override
    public boolean onMessageLinkClick(Context context, String s, Message message) {
        return false;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }

    public void removeConvertion(Conversation.ConversationType type, String id, RongIMClient.ResultCallback<Boolean> callback) {
        RongIM.getInstance().removeConversation(type,id,callback);
    }
}
