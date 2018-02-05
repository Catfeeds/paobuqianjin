package com.paobuqianjin.pbq.step.model.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

public class StepLocationReciver extends BroadcastReceiver {
    private final static String TAG = StepLocationReciver.class.getSimpleName();

    @Override

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            LocalLog.d(TAG, "onReceive() enter");
            Presenter.getInstance(context).handBroadcast(intent);
        }
    }
}
