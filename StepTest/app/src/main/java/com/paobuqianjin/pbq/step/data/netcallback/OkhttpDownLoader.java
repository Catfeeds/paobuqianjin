package com.paobuqianjin.pbq.step.data.netcallback;

import android.net.Uri;

import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.NetworkPolicy;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pbq on 2018/1/15.
 */

public class OkhttpDownLoader implements Downloader {
    private final static String TAG = OkhttpDownLoader.class.getSimpleName();
    OkHttpClient mClient = null;

    public OkhttpDownLoader(OkHttpClient client) {
        mClient = client;
    }

    @Override
    public Response load(Uri uri, int networkPolicy) throws IOException {
        CacheControl.Builder builder = new CacheControl.Builder();
        LocalLog.d(TAG, "load() enter networkPolicy = " + networkPolicy);
        if (networkPolicy != 0) {
            if (NetworkPolicy.isOfflineOnly(networkPolicy)) {
                LocalLog.d(TAG, "isOfflineOnly() enter");
                builder.onlyIfCached();
            } else {
                if (!NetworkPolicy.shouldReadFromDiskCache(networkPolicy)) {
                    LocalLog.d(TAG, "!shouldReadFromDiskCache() ");
                    builder.noCache();
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(networkPolicy)) {
                    LocalLog.d(TAG, "!shouldWriteToDiskCache() ");
                    builder.noStore();
                }
            }
        }

        Request request = new Request.Builder()
                .cacheControl(builder.build())
                .url(uri.toString())
                .build();
        okhttp3.Response response = mClient.newCall(request).execute();
        return new Response(response.body().byteStream(), false, response
                .body().contentLength());
    }

    @Override
    public void shutdown() {

    }
}
