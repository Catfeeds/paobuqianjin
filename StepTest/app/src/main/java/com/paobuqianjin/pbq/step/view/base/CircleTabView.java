package com.paobuqianjin.pbq.step.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;


/**
 * Created by pbq on 2017/12/11.
 */
/*
@className :CircleTabView Tab 标签
*@date 2017/12/11
*@author
*@description
*/
public class CircleTabView extends FrameLayout {
    private final static String TAG = CircleTabView.class.getSimpleName();
    private TextView mLabelTV;

    public CircleTabView(Context context) {
        super(context);
        init(context);
    }

    public CircleTabView(Context context, AttributeSet attributes) {
        super(context, attributes);
        init(context);
    }


    private void init(Context context) {
        View.inflate(context, R.layout.circle_view_tab, this);
        mLabelTV = findViewById(R.id.circle_label_tv);
    }

    public void setData(String label) {
        LocalLog.d(TAG, "");
        mLabelTV.setText(label);
    }
}
