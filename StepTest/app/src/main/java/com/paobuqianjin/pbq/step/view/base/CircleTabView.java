package com.paobuqianjin.pbq.step.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;


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
    private TextView mLabelTV;

    public CircleTabView(Context context) {
        super(context);
    }

    public CircleTabView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.circle_view_tab, this);
        mLabelTV = findViewById(R.id.circle_label_tv);
    }

    public void setData(String label) {
        mLabelTV.setText(label);
    }
}
