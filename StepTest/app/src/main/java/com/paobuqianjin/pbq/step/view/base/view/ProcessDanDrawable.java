package com.paobuqianjin.pbq.step.view.base.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2018/3/1.
 */

public class ProcessDanDrawable extends Drawable {
    private final static String TAG = ProcessDanDrawable.class.getSimpleName();
    private Paint mPaint;
    private final static int REDRAW = 0;
    private float processLength = 0;
    private float CurrentLength = 0;
    private float width;

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public void setColorFilter(int color, @NonNull PorterDuff.Mode mode) {
        super.setColorFilter(color, mode);
    }

    public ProcessDanDrawable setLength(float processLength, float width) {
        LocalLog.d(TAG, "processLength = " + processLength + ",width = " + width);
        this.processLength = processLength;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(width);
        // 设置风格为实线
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(0xffffc14c);
        this.width = width;
        handler.sendEmptyMessage(REDRAW);
        return this;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REDRAW:
                    CurrentLength += processLength / 500;
                    LocalLog.d(TAG, "CurrentLength = " + CurrentLength);
                    if (CurrentLength <= processLength) {
                        ProcessDanDrawable.this.invalidateSelf();
                        sendEmptyMessage(REDRAW);
                    }
                    break;
            }
        }
    };

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawLine(0 + width / 2, width / 2, CurrentLength, width / 2, mPaint);
    }
}
