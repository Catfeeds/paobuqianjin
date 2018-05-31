package com.paobuqianjin.pbq.step.view.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;

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
    private int viewParentWidth = 0, viewParentHeight = 0, acrWidth = 0, arcHeight = 0, left = 0, top = 0;
    private int footWidth = 0;
    private final static int ADD_STEP_MSG = 1;
    private float addAngle = 0.0f;

    public StepProcessDrawable setmAngle(float mAngle) {
        this.mAngle = mAngle;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(widthStrokeWidth);
        // 设置风格为实线
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(0xffffc14c);
        handler.sendEmptyMessage(REDRAW);
        return this;
    }

    private float mAngle = 0.0f;
    private float widthStrokeWidth = 20.0f;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REDRAW:
                    if (mAngle <= 360.0f) {
                        mCurrentAngle += mAngle / 25;
                        if (mCurrentAngle <= mAngle) {
                            StepProcessDrawable.this.invalidateSelf();
                            sendEmptyMessageDelayed(REDRAW, 40);
                        }
                    } else if (mAngle > 360.0f) {
                        if (mCurrentAngle <= 360.0f) {
                            mCurrentAngle += mAngle / 25;
                            if (mCurrentAngle <= mAngle) {
                                StepProcessDrawable.this.invalidateSelf();
                                sendEmptyMessageDelayed(REDRAW, 40);
                            }
                        } else {
                            mCurrentAngle += 2;
                            StepProcessDrawable.this.invalidateSelf();
                            sendEmptyMessageDelayed(REDRAW, 40);
                        }
                    }
                    break;
                case ADD_STEP_MSG:
                    LocalLog.d(TAG,"ADD_STEP_MSG ...");
                    mCurrentAngle += addAngle;
                    StepProcessDrawable.this.invalidateSelf();
                    removeMessages(ADD_STEP_MSG);
                    break;
            }
        }
    };

    public void add(float addAngle) {
        this.addAngle = addAngle;
        handler.sendEmptyMessageDelayed(ADD_STEP_MSG, 5000);
    }

    @TargetApi(19)
    public StepProcessDrawable(Context context, int left, int top, int width, int height, int parentWidth, int parentHeight) {
        LocalLog.d(TAG, "left = " + left + ", top = " + top + ",width = " + width + ",height = " + height + ",parentWidth =  " + parentWidth + ",parentHeight =  " + parentHeight);
        this.left = left;
        this.top = top;
        viewParentWidth = width;
        viewParentHeight = height;
        acrWidth = parentWidth;
        arcHeight = parentHeight;
//        widthStrokeWidth = Utils.dp2px(context, 10);
        LocalLog.d(TAG, "widthStrokeWidth : " + widthStrokeWidth);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.foot_ico, options);
        footWidth = Utils.dip2px(context, 20);
        LocalLog.d(TAG, "width = " + bitmap.getWidth() + ", height = " + bitmap.getHeight());
        options.inSampleSize = bitmap.getWidth() / footWidth;
        widthStrokeWidth = Utils.dip2px(context, 10);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.foot_ico, options);
        LocalLog.d(TAG, "width = " + bitmap.getWidth() + ", height = " + bitmap.getHeight());
        oval = new RectF((width - parentWidth) / 2 + widthStrokeWidth / 2, (height - parentHeight) / 2 + widthStrokeWidth / 2,
                parentWidth + (width - parentWidth) / 2 - widthStrokeWidth / 2,
                parentHeight + (height - parentHeight) / 2 - widthStrokeWidth / 2);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mCurrentAngle > 0) {
            canvas.drawArc(oval, 90, mCurrentAngle, false, mPaint);
        }
        canvas.save();
        canvas.rotate(mCurrentAngle, viewParentHeight / 2, viewParentHeight / 2);
        canvas.drawBitmap(bitmap, (viewParentWidth - bitmap.getWidth()) / 2, viewParentHeight - bitmap.getHeight() - widthStrokeWidth / 2, null);
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
