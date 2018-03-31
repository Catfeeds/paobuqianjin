package com.paobuqianjin.pbq.step.view.base.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.squareup.picasso.Transformation;

/**
 * Created by pbq on 2018/3/31.
 */

public class PicassoTransformation implements Transformation {
    int screenWidth;
    double targetWidth;

    public PicassoTransformation(Context context, int aver) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        targetWidth = screenWidth / aver;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        if (source.getWidth() == 0 || source.getHeight() == 0) {
            return source;
        }
        double ratio = (double) source.getWidth() / (double) source.getHeight();
        Bitmap bitmapResult = null;
        if (source != null) {
            bitmapResult = Bitmap.createScaledBitmap(source, (int) (targetWidth + 0.5), (int) ((targetWidth / ratio) + 0.5), false);
        }

        if (source != bitmapResult) {
            source.recycle();
        }

        return bitmapResult;
    }

    @Override
    public String key() {
        return "transformation" + screenWidth;
    }
}
