package com.paobuqianjin.pbq.step.view.base.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by Administrator on 2018/5/9.
 */
public class PaoToastView extends Toast {
    private Toast toast;
    private Context context;
    private TextView noticeText;

    public PaoToastView(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.view_pao_toast, (ViewGroup) null);
        this.noticeText = (TextView) layout.findViewById(R.id.noticeText);
//        View layout = inflater.inflate(android.R.layout.transient_notification, (ViewGroup) null);
//        this.noticeText = (TextView) layout.findViewById(android.R.id.message);

        this.toast = new Toast(context);
        this.toast.setGravity(17, 0, 0);
        this.toast.setDuration(0);
        this.toast.setView(layout);
    }

    public void setShowText(String dialogNotice) {
        this.noticeText.setText(dialogNotice);
    }

    public void show() {
        this.toast.show();
    }
}