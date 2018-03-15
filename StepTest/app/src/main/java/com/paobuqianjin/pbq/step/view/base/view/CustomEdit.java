package com.paobuqianjin.pbq.step.view.base.view;


import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatEditText;

import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2018/3/15.
 */

public class CustomEdit extends AppCompatEditText {
    private final static String TAG = CustomEdit.class.getSimpleName();

    public String getTranslateEmotion() {
        return translateEmotion;
    }

    public void setTranslateEmotion(String translateEmotion) {
        LocalLog.d(TAG, "translateEmotion =  " + translateEmotion);
        this.translateEmotion = translateEmotion;
    }

    private String translateEmotion = "";

    public CustomEdit(Context context) {
        super(context);
    }

    public CustomEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
