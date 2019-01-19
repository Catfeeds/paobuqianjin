package com.paobuqianjin.pbq.step.model.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.callback.BitmapCallback;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopRedResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.view.RedDataBean;
import com.paobuqianjin.pbq.step.view.fragment.home.HomeFragment;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by pbq on 2019/1/18.
 */

public class RedCheckService extends IntentService {
    private final static String TAG = RedCheckService.class.getSimpleName();
    private Vibrator mVibrator;
    public RedCheckService() {
        super("redcheck");
    }

    public RedCheckService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mVibrator =(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LocalLog.d(TAG,"RedCheckService enter");
        getNearShopAndRed();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel createNotificationChannel() {
        String channelId = "red";
        NotificationChannel channel = null;
        channel = new NotificationChannel(channelId,
                "Channel_red", NotificationManager.IMPORTANCE_DEFAULT);
        return channel;
    }

    private void getNearShopAndRed() {
        if (Presenter.getInstance(this).getLocation()[0] > 0.0d && Presenter.getInstance(this).getLocation()[1] > 0.0d) {
            Map<String, String> param = new HashMap<>();
            param.put("latitude", String.valueOf(Presenter.getInstance(this).getLocation()[0]));
            param.put("longitude", String.valueOf(Presenter.getInstance(this).getLocation()[1]));
            Presenter.getInstance(this).postPaoBuSimple(NetApi.urlShopNearRead, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {

                    try {
                        final ShopRedResponse shopRedResponse = new Gson().fromJson(s, ShopRedResponse.class);
                        int redSize = shopRedResponse.getData().getRedpacket().size();
                        if (redSize > 0) {
                            if(mVibrator != null){
                                mVibrator.vibrate(new long[]{100,10,100,1000},-1);
                            }
                            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            if (nm == null) return;
                            String notificationChannelId = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                NotificationChannel notificationChannel = createNotificationChannel();
                                nm.createNotificationChannel(notificationChannel);
                                notificationChannelId = notificationChannel.getId();
                            }
                            Notification notification = null;
                            notification = new NotificationCompat.Builder(RedCheckService.this, notificationChannelId)
                                    .setSmallIcon(getApplicationInfo().icon)
                                    .setWhen(System.currentTimeMillis())
                                    .setContentTitle("红包信息提示")
                                    .setContentText(shopRedResponse.getData().getTips())
                                    .setAutoCancel(true)
                                    .build();
                            nm.notify(12121, notification);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                }
            });
        }
    }

}
