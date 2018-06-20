package com.paobuqianjin.pbq.step.view.base.okhttp3;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.io.InputStream;

/**
 * Created by pbq on 2018/6/19.
 */
@GlideModule
public class OkHttpGlideModule extends AppGlideModule {
    private final static String TAG = OkHttpGlideModule.class.getSimpleName();

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        LocalLog.d(TAG, "registerComponents() enter");
        registry.append(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpUtils.getInstance().getOkHttpClient()));
    }
}
