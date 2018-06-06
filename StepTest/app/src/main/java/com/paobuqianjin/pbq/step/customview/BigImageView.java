package com.paobuqianjin.pbq.step.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;

import com.lwkandroid.imagepicker.widget.photoview.PhotoView;

/**
 * Created by pbq on 2018/6/6.
 */

public class BigImageView extends PhotoView {
    public BigImageView(Context context) {
        super(context, null);
    }

    public BigImageView(Context context, AttributeSet attr) {
        super(context, attr, 0);
    }

    public BigImageView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @TargetApi(21)
    public BigImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        /*setImageDrawable(null);*/
    }
}
