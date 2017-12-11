package com.paobuqianjin.pbq.step.model.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeReceiver extends BroadcastReceiver {
    private final static String ACTION_TIME_SET = "android.intent.action.TIME_SET";
    private final static String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
    private final static String ACTION_TIME_ZONE = "android.intent.action.TIMEZONE_CHANGED";
    private final static String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        if (context != null && intent != null) {

        }
    }

    private void timeSet() {

    }

    private void timeTick() {

    }

    private void dateChange() {

    }

    private void TimeZoneChanged() {

    }
}
