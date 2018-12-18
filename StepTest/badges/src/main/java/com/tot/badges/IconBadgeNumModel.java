package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.support.annotation.NonNull;

public interface IconBadgeNumModel {
    Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception;

    void setIconBadgeNum(@NonNull Application context, int count) throws Exception;
}
