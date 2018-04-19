package com.paobuqianjin.pbq.step.model.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;

public class StepLocationReciver extends BroadcastReceiver {
    private final static String TAG = StepLocationReciver.class.getSimpleName();
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";

    @Override

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            PaoBuApplication paoBuApplication = (PaoBuApplication) context.getApplicationContext();
            LocalLog.d(TAG, "onReceive() enter");
            if (intent.getAction() != null && LOCATION_ACTION.equals(intent.getAction())) {
                Presenter.getInstance(context).handBroadcast(intent);
            }
            if (!paoBuApplication.isForeground()) {
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainIntent);
            } else {

            }
        }
    }
}
