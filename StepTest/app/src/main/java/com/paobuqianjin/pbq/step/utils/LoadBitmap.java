package com.paobuqianjin.pbq.step.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Transformation;

import java.security.MessageDigest;

/**
 * Created by pbq on 2018/6/13.
 */

public class LoadBitmap {
    private static String TAG = LoadBitmap.class.getSimpleName();

    public static void glideLoad(Activity activity, ImageView imageView, String url,
                                 int placeHolder, int errorHolder) {
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeHolder)
                        .error(errorHolder)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void glideLoad(FragmentActivity activity, ImageView imageView, String url,
                                 int placeHolder, int errorHolder) {
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeHolder)
                        .error(errorHolder)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void glideLoad(Fragment activity, ImageView imageView, String url,
                                 int placeHolder, int errorHolder) {
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeHolder)
                        .error(errorHolder)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void glideLoad(Context activity, ImageView imageView, String url,
                                 int placeHolder, int errorHolder) {
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeHolder)
                        .error(errorHolder)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void glideLoad(Activity activity, ImageView imageView, String url) {
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void glideLoad(Context activity, ImageView imageView, String url) {
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }


    private static BitmapTransformation getBitmapTransformation(final ImageView view) {
        return  new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                int targetWidth = view.getWidth();
                LocalLog.d(TAG, "targetWidth  =" + targetWidth);
                //返回原图
                if (toTransform.getWidth() == 0 || toTransform.getWidth() < targetWidth) {
                    return toTransform;
                }

                //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                double aspectRatio = (double) toTransform.getHeight() / (double) toTransform.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                if (targetHeight == 0 || targetWidth == 0) {
                    return toTransform;
                }
                Bitmap result = Bitmap.createScaledBitmap(toTransform, targetWidth, targetHeight, false);
                if (result != toTransform) {
                    // Same bitmap is returned if sizes are the same
                    toTransform.recycle();
                }
                return result;
            }

            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {

            }
        };
    }

}
