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
  /*  private boolean isCanScroll;

    public UnScrollViewPager(Context context) {
        super(context);
    }

    public UnScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void scrollTo(int x, int y) {
        LocalLog.d(TAG, "x =  " + x + " y = " + y);
        super.scrollTo(x, y);
    }

    public void setNoCanScroll(boolean noScroll) {
        isCanScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            LocalLog.d(TAG, "onTouchEvent() 不响应滑动 " + this.getTranslationX());
            return true;
        } else {
            return super.onTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (!isCanScroll) {
            LocalLog.d(TAG, "onInterceptHoverEvent() 不响应滑动");
            return false;
        } else {
            return super.onInterceptHoverEvent(event);
        }
    }*/


    private boolean isScroll;

    public UnScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnScrollViewPager(Context context) {
        super(context);
    }

    /**
     * 1.dispatchTouchEvent一般情况不做处理
     * ,如果修改了默认的返回值,子孩子都无法收到事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);   // return true;不行
    }

    /**
     * 是否拦截
     * 拦截:会走到自己的onTouchEvent方法里面来
     * 不拦截:事件传递给子孩子
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // return false;//可行,不拦截事件,
        // return true;//不行,孩子无法处理事件
        //return super.onInterceptTouchEvent(ev);//不行,会有细微移动
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    /**
     * 是否消费事件
     * 消费:事件就结束
     * 不消费:往父控件传
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return false;// 可行,不消费,传给父控件
        //return true;// 可行,消费,拦截事件
        //super.onTouchEvent(ev); //不行,
        //虽然onInterceptTouchEvent中拦截了,
        //但是如果viewpage里面子控件不是viewgroup,还是会调用这个方法.
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return true;// 可行,消费,拦截事件
        }
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
}
