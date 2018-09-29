package com.paobuqianjin.pbq.step.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.view.base.view.PaoToastView;

/**
 * Created by Administrator on 2018/5/9.
 */

public class PaoToastUtils {
    private static PaoToastView paoToastView;
    private static long lastSecond;

    public PaoToastUtils() {
    }

    public static void showShortToast(Context context, String showMsg) {
        if (null != paoToastView) {
            paoToastView = null;
        }

        paoToastView = new PaoToastView(context);
        paoToastView.setShowText(showMsg);
        paoToastView.setDuration(Toast.LENGTH_SHORT);
        paoToastView.show();
    }

    public static synchronized void showShortToastNoMore(Context context, String showMsg) {
        if (null != paoToastView) {
            if (paoToastView.getText().equals(showMsg) && System.currentTimeMillis() - lastSecond < 5000) {
                return;
            } else {
                paoToastView = null;
            }
        }
        lastSecond = System.currentTimeMillis();
        paoToastView = new PaoToastView(context);
        paoToastView.setShowText(showMsg);
        paoToastView.setDuration(Toast.LENGTH_SHORT);
        paoToastView.show();
    }

    public static void showLongToast(Context context, String showMsg) {
        if (TextUtils.isEmpty(showMsg)) {
            return;
        }
        if (null != paoToastView) {
            paoToastView = null;
        }

        paoToastView = new PaoToastView(context);
        paoToastView.setShowText(showMsg);
        paoToastView.setDuration(Toast.LENGTH_LONG);
        paoToastView.show();
    }

    public static void showMomentToast(Activity activity, final Context context, final String showMsg) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (null == PaoToastUtils.paoToastView) {
                    PaoToastUtils.paoToastView = new PaoToastView(context);
                    PaoToastUtils.paoToastView.setShowText(showMsg);
                    PaoToastUtils.paoToastView.setDuration(Toast.LENGTH_SHORT);
                    PaoToastUtils.paoToastView.show();
                } else {
                    PaoToastUtils.paoToastView.setShowText(showMsg);
                    PaoToastUtils.paoToastView.show();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (null != PaoToastUtils.paoToastView) {
                            PaoToastUtils.paoToastView.cancel();
                        }
                    }
                }, 2000L);
            }
        });
    }
}