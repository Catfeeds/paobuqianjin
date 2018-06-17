package com.paobuqianjin.pbq.step.view.base;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.l.okhttppaobu.okhttp.OkHttpUtils;

import java.io.InputStream;

/**
 * Created by pbq on 2018/6/17.
 */

public class MyOkHttpGlideModule extends AppGlideModule {
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpUrlLoader.Factory  factory = new OkHttpUrlLoader.Factory(OkHttpUtils.getInstance().getOkHttpClient());
        registry.replace(GlideUrl.class, InputStream.class,factory);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
