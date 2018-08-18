package com.paobuqianjin.pbq.step.data.netcallback;

import android.system.ErrnoException;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;

import java.net.SocketException;
import java.net.UnknownHostException;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/5/17.
 */
public abstract class PaoCallBack extends StringCallback {
    private final String TAG = this.getClass().getSimpleName();
    private String myUrl;

    @Override
    public void onError(Call call, Exception e, int i, Object o) {
        if (e instanceof UnknownHostException) {
            LocalLog.d(TAG, "没有网络");
            PaoToastUtils.showShortToastNoMore(PaoBuApplication.getApplication(),"网络连接异常，请检查手机网络是否已开启");
        }
        e.printStackTrace();


        if(o == null){
            return;
        }
        LocalLog.d(TAG, myUrl + "-----Http Code----> " + i + " \nError Object----> " +o.toString());
        try {
            ErrorCode code = new Gson().fromJson(o.toString(), ErrorCode.class);
            onFal(e, o.toString(), code);
        } catch (Exception e1) {
            e1.printStackTrace();
            onFal(e, o.toString(), null);
        }

    }

    @Override
    public void onResponse(String s, int i) {
        LocalLog.d(TAG, myUrl + "----> \n" + s);
        /*if (s != null) {
            if (s.contains("提交内容不符合相关规范")) {
                ErrorCode errorCode = new ErrorCode();
                errorCode.setError(-1);
                errorCode.setMessage("提交内容不符合相关规范");
                onFal(null, "提交内容不符合相关规范", errorCode);
                return;
            }
        }*/
        try {
            ErrorCode code = new Gson().fromJson(s, ErrorCode.class);
            if (code.getError() == 0) {
                onSuc(s);
            }else{
                onFal(null, s,code);
            }
        } catch (Exception e) {
            e.printStackTrace();
            onFal(e, null, null);
        }
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }

    protected abstract void onSuc(String s);

    protected abstract void onFal(Exception e, String errorStr, ErrorCode errorBean);
}
