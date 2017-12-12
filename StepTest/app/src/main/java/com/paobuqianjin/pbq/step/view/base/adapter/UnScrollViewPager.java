package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2017/12/12.
 */
/*
@className :UnScrollViewPager
*@date 2017/12/12
*@author
*@description  禁止滑动的ViewPager
*/
public class UnScrollViewPager extends ViewPager {
    private final static String TAG = UnScrollViewPager.class.getSimpleName();
    private boolean isCanScroll = true;

    public UnScrollViewPager(Context context) {
        super(context);
    }

    public UnScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setNoCanScroll(boolean noScroll) {
        isCanScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            LocalLog.d(TAG, "onTouchEvent() 不响应滑动");
            return false;
        } else {
            return super.onTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (isCanScroll) {
            LocalLog.d(TAG, "onTouchEvent() 不响应滑动");
            return false;
        } else {
            return super.onInterceptHoverEvent(event);
        }
    }
}
