package com.paobuqianjin.pbq.step.model.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.model.services.StepService;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;

/*
@className :BootStepReceiver
*@date 2017/11/29
*@author
*@description 开机操作
*/
public class BootStepReceiver extends BroadcastReceiver {
    private final static String TAG = BootStepReceiver.class.getSimpleName();
    private final static String BOOT_COMPLETE = "android.intent.action.BOOT_COMPLETED";
    private final static String SD_ACTION = "android.intent.action.MEDIA_MOUNTED";
    private final static String SD_ACTION_UN = "android.intent.action.MEDIA_UNMOUNTED";
    private final static String ACTIVITY_START_ACTION = "android.intent.action.APP_START";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {

            if (BOOT_COMPLETE.equals(intent.getAction())) {
                LocalLog.d(TAG, "onReceive() " + BOOT_COMPLETE);
                startStepService(context);
            } else if (SD_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "onReceive() " + SD_ACTION);
                startStepService(context);
            } else if (SD_ACTION_UN.equals(intent.getAction())) {
                LocalLog.d(TAG, "onReceive() " + SD_ACTION_UN);
                startStepService(context);
            }
        }
        Toast.makeText(context,"BootStepReceiver enter",Toast.LENGTH_SHORT).show();
        testStartApp(context);
    }

    private void testStartApp(Context context) {
        Intent activityIntent = new Intent();
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityIntent.setClass(context, MainActivity.class);
        activityIntent.setAction(ACTIVITY_START_ACTION);
        context.startActivity(activityIntent);
    }

    private void startStepService(Context context) {
        Intent stepIntent = new Intent();
        stepIntent.setAction(StepService.START_STEP_ACTION);
        stepIntent.setClass(context, StepService.class);
        context.startService(stepIntent);
    }
}
