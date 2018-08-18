package com.paobuqianjin.pbq.step.model.broadcast;

import android.content.Context;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by Administrator on 2018/6/22.
 */

public class RongYunMessageReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    /**
     * 第三方push状态回调
     *
     * @param pushType   push类型
     * @param action     当前的操作，连接或者获取token
     * @param resultCode 返回的错误码
     */
    @Override
    public void onThirdPartyPushState(String pushType, String action, long resultCode) {
        super.onThirdPartyPushState(pushType, action, resultCode);
        //拿到错误码用户可以在需要的 activity 调用 RongPushClient 的接口解决相应的错误：
    }
}
