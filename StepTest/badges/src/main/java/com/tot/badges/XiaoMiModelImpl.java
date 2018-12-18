package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * https://dev.mi.com/console/doc/detail?pId=939
 * <p>
 * 必须发送通知
 */
public class XiaoMiModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "Xiaomi phones must send notification";

    @Override
    public Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception {
        if (notification == null) {
            throw new Exception(NOTIFICATION_ERROR);
        }
        Field field = notification.getClass().getDeclaredField("extraNotification");
        Object extraNotification = field.get(notification);
        Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
        method.invoke(extraNotification, count);
        return notification;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel createNotificationChannel() {
        String channelId = "test";
        NotificationChannel channel = null;
        channel = new NotificationChannel(channelId,
                "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.RED); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        return channel;
    }

    @Override
    public void setIconBadgeNum(@NonNull Application context, int count) throws Exception {
        Log.e("xiaomi", "必须要Notify");
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm == null) return;
        String notificationChannelId = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = createNotificationChannel();
            nm.createNotificationChannel(notificationChannel);
            notificationChannelId = notificationChannel.getId();
        }
        Notification notification = null;
        try {
            notification = new NotificationCompat.Builder(context, notificationChannelId)
                    .setSmallIcon(context.getApplicationInfo().icon)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("您未读消息")
                    .setContentText(count + "条")
                    .setTicker("ticker")
                    .setAutoCancel(true)
                    .setNumber(count)
                    .build();
            notification = setIconBadgeNum(context, notification, count);
            nm.notify(22222, notification);
            if (count == 0) {
                nm.cancel(22222);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
