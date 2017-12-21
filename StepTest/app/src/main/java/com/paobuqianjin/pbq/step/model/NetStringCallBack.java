package com.paobuqianjin.pbq.step.model;

import com.l.okhttppaobu.okhttp.callback.Callback;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pbq on 2017/12/20.
 */

public class NetStringCallBack extends StringCallback {
    private final static String TAG = NetStringCallBack.class.getSimpleName();

    @Override
    public void onError(Call call, Exception e, int i) {

    }

    @Override
    public void onResponse(String s, int i) {
        LocalLog.d(TAG, "onResponse() enter body " +  s);
    }
}
