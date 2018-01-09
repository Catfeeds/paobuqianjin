package com.paobuqianjin.pbq.step.view.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by pbq on 2018/1/9.
 */

public class ListenScrollView extends ScrollView {
    private final static String TAG = ListenScrollView.class.getSimpleName();

    public ListenScrollView(Context context) {
        super(context);
    }

    public ListenScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ListenScrollView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
