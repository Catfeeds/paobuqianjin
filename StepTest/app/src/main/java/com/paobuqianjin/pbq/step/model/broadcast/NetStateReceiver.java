package com.paobuqianjin.pbq.step.model.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.NetworkUtil;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetWorkUtil;
import com.squareup.picasso.NetworkPolicy;

public class NetStateReceiver extends BroadcastReceiver {
    private final static String TAG = NetStateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String workType = NetWorkUtil.GetNetWorkType(context);
        LocalLog.d(TAG, "网络类型");
        if (workType.equals("")) {
            LocalLog.d(TAG, "无网络");
            Presenter.getInstance(context).setNetworkPolicy(NetworkPolicy.OFFLINE);
        } else {
            if (workType.equals("WIFI")) {
                Presenter.getInstance(context).setNetworkPolicy(NetworkPolicy.NO_CACHE);
            }
        }
    }
}
