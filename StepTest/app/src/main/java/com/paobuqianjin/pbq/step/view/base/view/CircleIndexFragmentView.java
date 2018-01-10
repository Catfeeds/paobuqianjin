package com.paobuqianjin.pbq.step.view.base.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2018/1/10.
 */

public class CircleIndexFragmentView extends View {
    private final static String TAG = CircleIndexFragmentView.class.getSimpleName();
    private Context context;

    public CircleIndexFragmentView(Context context) {
        super(context);
        this.context = context;
    }

    public View getView() {
        return LayoutInflater.from(context).inflate(R.layout.circle_honor_fg, null, false);
    }
}
