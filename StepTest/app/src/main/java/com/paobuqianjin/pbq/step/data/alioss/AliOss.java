package com.paobuqianjin.pbq.step.data.alioss;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;

/**
 * Created by pbq on 2018/7/24.
 */

public class AliOss {
    String AK = "LTAIpSQX9t1xK9ji";
    String SK = "oakQeBuJLUJV8znUY9Uw631HTMGLmf";
    private final static String endpoint = "";
    private String imgEndpoint = "";
    private String mRegion = "";

    public OssService initOSS(Context appContext) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(AK, SK);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(appContext, Config.endpoint, credentialProvider, conf);
        OSSLog.enableLog();
        return new OssService(oss, "pbqj-cdn", null, appContext);
    }

    public void initRegion(Context context) {
        if (TextUtils.isEmpty(Config.endpoint)) {
            return;
        }
        if (Config.endpoint.contains("oss-cn-hangzhou")) {
            mRegion = "杭州";
            imgEndpoint = getImgEndpoint(context);
        } else if (Config.endpoint.contains("oss-cn-qingdao")) {
            mRegion = "青岛";
            imgEndpoint = getImgEndpoint(context);
        } else if (Config.endpoint.contains("oss-cn-beijing")) {
            mRegion = "北京";
            imgEndpoint = getImgEndpoint(context);
        } else if (Config.endpoint.contains("oss-cn-shenzhen")) {
            mRegion = "深圳";
            imgEndpoint = getImgEndpoint(context);
        } else if (Config.endpoint.contains("oss-us-west-1")) {
            mRegion = "美国";
            imgEndpoint = getImgEndpoint(context);
        } else if (Config.endpoint.contains("oss-cn-shanghai")) {
            mRegion = "上海";
            imgEndpoint = getImgEndpoint(context);
        } else {
            Toast.makeText(context, "错误的区域", Toast.LENGTH_SHORT).show();
//            new AlertDialog.Builder(AuthTestActivity.this).setTitle("错误的区域").setMessage(mRegion).show();
        }
    }

    protected String getImgEndpoint(Context appContext) {
        String imgEndpoint = "";
        if (mRegion.equals("杭州")) {
            imgEndpoint = "http://img-cn-hangzhou.aliyuncs.com";
        } else if (mRegion.equals("青岛")) {
            imgEndpoint = "http://img-cn-qingdao.aliyuncs.com";
        } else if (mRegion.equals("北京")) {
            imgEndpoint = "http://img-cn-beijing.aliyuncs.com";
        } else if (mRegion.equals("深圳")) {
            imgEndpoint = "http://img-cn-shenzhen.aliyuncs.com";
        } else if (mRegion.equals("美国")) {
            imgEndpoint = "http://img-us-west-1.aliyuncs.com";
        } else if (mRegion.equals("上海")) {
            imgEndpoint = "http://img-cn-shanghai.aliyuncs.com";
        } else {
            new AlertDialog.Builder(appContext).setTitle("错误的区域").setMessage(mRegion).show();
            imgEndpoint = "";
        }
        return imgEndpoint;
    }
}
