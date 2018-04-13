package com.paobuqianjin.pbq.step.view.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2018/1/19.
 */

public class StepProcessDrawable extends Drawable {
    private final static String TAG = StepProcessDrawable.class.getSimpleName();
    private Paint mPaint;
    private final static int REDRAW = 0;
    private float mCurrentAngle = 0.0f;
    private Bitmap bitmap;
    private RectF oval;
    private long animationTime = 200;

    public StepProcessDrawable setmAngle(float mAngle) {
        this.mAngle = mAngle;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(8.0F);
        // 设置风格为实线
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(0xffffc14c);
        handler.sendEmptyMessage(REDRAW);
        return this;
    }

    private float mAngle = 0.0f;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REDRAW:
                    mCurrentAngle += mAngle / 200;
                    LocalLog.d(TAG, "mCurrentAngle = " + mCurrentAngle);
                    if (mCurrentAngle <= mAngle) {
                        StepProcessDrawable.this.invalidateSelf();
                        sendEmptyMessage(REDRAW);
                    }
                    break;
            }
        }
    };

    @TargetApi(19)
    public StepProcessDrawable(Context context) {
        bitmap = ((BitmapDrawable) ContextCompat.getDrawable(context,R.drawable.foot)).getBitmap();
        oval = new RectF(30, 30, 390, 390);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        LocalLog.d(TAG, "draw() enter");
        if (mCurrentAngle > 15) {
            canvas.drawArc(oval, 90, mCurrentAngle - 15, false, mPaint);
        }
        canvas.save();
        canvas.rotate(mCurrentAngle, 210, 210);
        //canvas.drawCircle(184, 358, 8.0f, mPaint);
        canvas.drawBitmap(bitmap, 159, 324, null);
        canvas.restore();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
