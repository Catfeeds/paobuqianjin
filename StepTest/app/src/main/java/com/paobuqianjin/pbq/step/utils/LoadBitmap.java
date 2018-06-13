package com.paobuqianjin.pbq.step.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by pbq on 2018/6/13.
 */

public class LoadBitmap {
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
}
