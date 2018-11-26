package com.paobuqianjin.pbq.step.view.base.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.presenter.im.HomeRecInterface;

/**
 * Created by pbq on 2018/11/26.
 */

public class RedDataBean {
    public ImageView view;
    private String redId;

    public RedDataBean(ImageView view, final String redId, final HomeRecInterface homeRecInterface) {
        this.view = view;
        this.redId = redId;

        if (view != null && !TextUtils.isEmpty(redId) && homeRecInterface != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeRecInterface.recRedPkg(redId);
                }
            });
        }
    }
}
