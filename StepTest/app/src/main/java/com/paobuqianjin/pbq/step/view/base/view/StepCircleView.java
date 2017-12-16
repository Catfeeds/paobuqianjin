package com.paobuqianjin.pbq.step.view.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2017/12/2.
 */

public final class StepCircleView extends View {
    private final static String TAG = StepCircleView.class.getSimpleName();
    private float mAngele = 0;
    private Paint mPaint;
    private float mRadius;

    public StepCircleView(Context context) {
        super(context);
        initParam();
    }

    public StepCircleView(Context context, AttributeSet attr) {
        super(context, attr);
        initParam();
    }

    public StepCircleView(Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
        initParam();
    }

    @TargetApi(21)
    public StepCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParam();
    }

    private void initParam() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.color_ffc14c));
        mPaint.setStrokeWidth(12.0f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        mRadius = widthSize / 2;
/*        LocalLog.d(TAG, "width =  " + width + ", widthSize = " + widthSize + " ,height  =  " + height
                + ", heightSize =  " + heightSize + " radius = " + mRadius);*/
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /*
    *@function 设置旋转弧度
    *@param
    *@return 
    */
    private void setAngle(float angele) {
        mAngele = angele;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mRadius, mRadius, mRadius - 6.0f, mPaint);
    }
}
