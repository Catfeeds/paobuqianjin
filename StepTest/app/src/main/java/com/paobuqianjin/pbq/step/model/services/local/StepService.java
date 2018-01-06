package com.paobuqianjin.pbq.step.model.services.local;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.utils.DateUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


/*
@className :StepService
*@date 2017/11/30
*@author
*@description 计步服务
*/
public class StepService extends Service implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final static String TAG = StepService.class.getSimpleName();
    public final static String START_STEP_ACTION = "com.example.pbq.steptest.START_STEP_SERVICES";
    private SensorManager mSensorManager = null;
    private Sensor mSensorStepCount = null;
    private Sensor mSensorStepDetector = null;
    private StepTypeListener mStepCountListener, mStepDetectorListener;
    public final static int MSG_STEP_SERVER_TO_OTHER = 0;
    public final static int MSG_UPDATE_STEP_DETECTOR = 1;
    private Notification.Builder mBuilder = null;
    private NotificationManager mNotificationManager = null;
    private Intent mActivityIntent;
    private int mStepDetector;
    private int mTotalStep;
    private int mCurDayStep;
    //偏差步数
    private int mTempStep;
    private final static int TYPE_SENSOR_COUNT = 0;
    private final static int TYPE_SENSOR_DETECTOR = 1;
    private Messenger messenger = new Messenger(new MessengerHandler());
    private final static int MSG_SEND_DATA_TO_SETP_SERVICE = 0;
    private final static int MSG_SEND_DATA_TO_ENGINE = 1;
    //数据更新时间戳
    private String timeStamp;
    private String currentTime;
    private TickTimer tickTimer;
    private long FLUSH_RATE = 3000;

    public StepService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        LocalLog.d(TAG, "onBind() enter");
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LocalLog.d(TAG, "onCreate() enter");
        init();
        boolean isSuccess = registerStepSensor();
        LocalLog.d(TAG, "注册计步Sensor " + isSuccess);
        setForeGroundNotify();
        setStartServiceTime();
        startTick();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocalLog.d(TAG, "onStartCommand() enter");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        LocalLog.d(TAG, "onDestroy() enter ");
        super.onDestroy();
        unRegisterStepSensor();
        Toast.makeText(this, "onDestroy() Sensor 去注册", Toast.LENGTH_SHORT).show();
    }

    private void clean() {
        //TODO 资源释放
    }

    //TODO 服务重启
    private void restartService() {

    }

    private boolean registerStepSensor() {
        boolean isSuccess = false;
        if (mSensorManager != null && mSensorStepCount != null) {
            mStepCountListener = new StepTypeListener(TYPE_SENSOR_COUNT);
            isSuccess = mSensorManager.registerListener(mStepCountListener, mSensorStepCount, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorManager != null && mSensorStepDetector != null) {
            mStepDetectorListener = new StepTypeListener(TYPE_SENSOR_DETECTOR);
            isSuccess = mSensorManager.registerListener(mStepDetectorListener, mSensorStepDetector, SensorManager.SENSOR_DELAY_NORMAL);
        }
        return isSuccess;
    }

    private void unRegisterStepSensor() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(mStepCountListener);
            mSensorManager.unregisterListener(mStepDetectorListener);
        }
    }

    /*
    *@function  init() 获取资源
    *@param
    *@return 
    */
    private void init() {
        LocalLog.d(TAG, "init() enter");
        mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        initStepCountSensor();
    }

    /*@desc 获取STEP_COUNTER sensor
    *@function initStepCountSensor
    *@param
    *@return
    */
    private void initStepCountSensor() {
        if (mSensorManager != null) {
            mSensorStepCount = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
    }

    /*@desc  获取STEP_DETECTOR ,暂时不适用，更适合做Dead Reckoning
    *@function initStepDetectorSensor
    *@param
    *@return
    */
    private void initStepDetectorSensor() {
        if (mSensorManager != null) {
            mSensorStepDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        }
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG_SEND_DATA_TO_SETP_SERVICE:
                    LocalLog.d(TAG, "handleMessage() enter Engine -> send " + msg.getData().getString("Engine"));
                    Message message = Message.obtain();
                    message.what = MSG_SEND_DATA_TO_ENGINE;
                    Bundle bundle = new Bundle();
                    bundle.putString("StepService", "StepService -> Engine");
                    message.setData(bundle);
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        LocalLog.e(TAG, "failed!");
                    } finally {

                    }

                    //TODO
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    ;

    private void setForeGroundNotify() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(this.getApplicationContext());
        mActivityIntent = new Intent(this, MainActivity.class);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 0, mActivityIntent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round))
                .setContentTitle(StepService.this.getResources().getString(R.string.app_name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("运动总步数: " + mCurDayStep);
        Notification stepNotification = mBuilder.build();
        mNotificationManager.notify(110, stepNotification);
        startForeground(110, stepNotification);
    }


    private class StepTypeListener implements SensorEventListener {
        private int mSensorType = -1;

        StepTypeListener(int SensorType) {
            mSensorType = SensorType;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (mSensorType == TYPE_SENSOR_COUNT) {
                mTotalStep = (int) sensorEvent.values[0];
                mCurDayStep = mTotalStep - mTempStep;
                LocalLog.d(TAG, "mTotalStep =  " + mTotalStep);
            } else if (mSensorType == TYPE_SENSOR_DETECTOR) {
                //TODO  Now No USE
            } else {
                LocalLog.e(TAG, "onSensorChanged() unknown sensor event");
            }
        }
    }

    /*已经登陆，或者注册过则为TRUE*/
    private boolean getLoginFlag() {
        return Engine.getEngine(this).getLogFlag(this);
    }

    /*@desc  监测是否登录
    *@function onSharedPreferenceChanged
    *@param
    *@return
    */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

    //主动刷新,触发上报
    private void flush() {
        flushStepSensor(mStepCountListener);

    }

    /*@desc主动刷新Sensor数据
    *@function flushStepSensor
    *@param
    *@return
    */
    private boolean flushStepSensor(SensorEventListener sensorEventListener) {
        boolean isFlush = false;
        LocalLog.d(TAG, "flushStepSensor() enter");
        if (sensorEventListener == null) {
            LocalLog.e(TAG, "sensorEventListener() is null");
            return false;
        }

        isFlush = ((SensorManager) getSystemService(SENSOR_SERVICE)).flush(sensorEventListener);
        LocalLog.d(TAG, "flushStepSensor() sensor  op " + isFlush);
        return isFlush;
    }

    //default 5:30AM
    private void getEffectStartSportTime(String startTime) {

    }

    //default 21:30
    private void setEffectRecordEndTime(String endTime) {

    }

    private void setStartServiceTime() {
        currentTime = DateUtil.getCurDate();
        Engine.getEngine(this).setStartServiceTime(this, currentTime);
    }

    private boolean isNewDay() {
        boolean isNewDay = false;
        String startTime = Engine.getEngine(this).getStartSportTime(this);
        LocalLog.d(TAG, "startTime = " + startTime);
        String time = "00:00";
        if (time.equals(new SimpleDateFormat("HH:mm").format(new Date()))
                || !currentTime.equals(DateUtil.getCurDate())) {
            mCurDayStep = 0;
            isNewDay = true;
        }
        LocalLog.d(TAG, "isNewDay = " + isNewDay);
        return isNewDay;
    }


    /*@desc  生成当天数据,如果数据库中有了当天记录则不再生成，更新即可
    *@function createDayTrack
    *@param
    *@return 
    */
    private void createDayTrack() {

    }

    private void startTick() {
        tickTimer = new TickTimer(FLUSH_RATE, 1000);
        tickTimer.start();
    }

    private class TickTimer extends CountDownTimer {
        public TickTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            tickTimer.cancel();
            //
            flush();
            startTick();
            updateNotify();
        }
    }

    private void updateNotify() {
        mBuilder = new Notification.Builder(this.getApplicationContext());
        mActivityIntent = new Intent(this, MainActivity.class);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 0, mActivityIntent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round))
                .setContentTitle(StepService.this.getResources().getString(R.string.app_name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("今日步数: " + mCurDayStep + "步");
        Notification stepNotification = mBuilder.build();
        mNotificationManager.notify(110, stepNotification);
    }

    @Override

    public boolean onUnbind(Intent intent) {
        LocalLog.d(TAG, "onUnbind() enter");
        return super.onUnbind(intent);
    }
}
