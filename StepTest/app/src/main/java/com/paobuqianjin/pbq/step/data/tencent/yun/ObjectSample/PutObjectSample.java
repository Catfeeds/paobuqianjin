package com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultActivity;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
import com.tencent.cos.xml.model.object.PutObjectRequest;
import com.tencent.cos.xml.model.object.PutObjectResult;
import com.tencent.qcloud.core.network.QCloudProgressListener;


/**
 * Created by bradyxiao on 2017/6/1.
 * author bradyxiao
 * <p>
 * Put Object 接口请求可以将本地的文件（Object）上传至指定 Bucket 中。该操作需要请求者对 Bucket 有 WRITE 权限。
 */
public class PutObjectSample {
    private final static String TAG = PutObjectSample.class.getSimpleName();
    PutObjectRequest putObjectRequest;
    QServiceCfg qServiceCfg;


    public PutObjectSample(QServiceCfg qServiceCfg) {
        this.qServiceCfg = qServiceCfg;
    }

    public static String getPicNameFromPath(String picturePath) {
        String temp[] = picturePath.replace("\\\\", "/").split("/");
        String fileName = "";
        if (temp.length > 1) {
            fileName = temp[temp.length - 1];
        }
        return fileName;
    }

    public ResultHelper start(String filePath) {
        ResultHelper resultHelper = new ResultHelper();
        qServiceCfg.setUploadFileUrl(filePath);
        String bucket = qServiceCfg.getBucketForObjectAPITest();
        //String cosPath = qServiceCfg.getUploadCosPath();
        String srcPath = qServiceCfg.getUploadFileUrl();
        LocalLog.d(TAG, "srcPath =  " + srcPath);

        String cosPath = "/" + getPicNameFromPath(srcPath);
        LocalLog.d(TAG, "cosPath = " + cosPath);
        putObjectRequest = new PutObjectRequest(bucket, cosPath,
                srcPath);

        putObjectRequest.setProgressListener(new QCloudProgressListener() {
            @Override
            public void onProgress(long progress, long max) {
                float result = (float) (progress * 100.0 / max);
                Log.w("XIAO", "progress =" + (long) result + "%");
            }
        });
        putObjectRequest.setSign(600, null, null);


        try {
            final PutObjectResult putObjectResult =
                    qServiceCfg.cosXmlService.putObject(putObjectRequest);
            Log.w("XIAO", "success");
            resultHelper.cosXmlResult = putObjectResult;
            return resultHelper;
        } catch (CosXmlClientException e) {
            Log.w("XIAO", "QCloudException =" + e.getMessage());
            resultHelper.qCloudException = e;
            return resultHelper;
        } catch (CosXmlServiceException e) {
            Log.w("XIAO", "QCloudServiceException =" + e.toString());
            resultHelper.qCloudServiceException = e;
            return resultHelper;
        }
    }

    public ResultHelper start() {
        ResultHelper resultHelper = new ResultHelper();
        String bucket = qServiceCfg.getBucketForObjectAPITest();
        String cosPath = qServiceCfg.getUploadCosPath();
        String srcPath = qServiceCfg.getUploadFileUrl();

        putObjectRequest = new PutObjectRequest(bucket, cosPath,
                srcPath);

        putObjectRequest.setProgressListener(new QCloudProgressListener() {
            @Override
            public void onProgress(long progress, long max) {
                float result = (float) (progress * 100.0 / max);
                Log.w("XIAO", "progress =" + (long) result + "%");
            }
        });
        putObjectRequest.setSign(600, null, null);


        try {
            final PutObjectResult putObjectResult =
                    qServiceCfg.cosXmlService.putObject(putObjectRequest);
            Log.w("XIAO", "success");
            resultHelper.cosXmlResult = putObjectResult;
            return resultHelper;
        } catch (CosXmlClientException e) {
            Log.w("XIAO", "QCloudException =" + e.getMessage());
            resultHelper.qCloudException = e;
            return resultHelper;
        } catch (CosXmlServiceException e) {
            Log.w("XIAO", "QCloudServiceException =" + e.toString());
            resultHelper.qCloudServiceException = e;
            return resultHelper;
        }
    }

    /**
     * 采用异步回调操作
     */
    public void startAsync(final Activity activity) {
        String bucket = qServiceCfg.getBucketForObjectAPITest();
        String cosPath = qServiceCfg.getUploadCosPath();
        String srcPath = qServiceCfg.getUploadFileUrl();

        putObjectRequest = new PutObjectRequest(bucket, cosPath,
                srcPath);
        putObjectRequest.setProgressListener(new QCloudProgressListener() {
            @Override
            public void onProgress(long progress, long max) {
                float result = (float) (progress * 100.0 / max);
                Log.w("XIAO", "progress =" + (long) result + "%");
            }
        });
        putObjectRequest.setSign(600, null, null);
        qServiceCfg.cosXmlService.putObjectAsync(putObjectRequest, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(cosXmlResult.printHeaders())
                        .append(cosXmlResult.printBody());
                Log.w("XIAO", "success = " + stringBuilder.toString());
                show(activity, stringBuilder.toString());
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException qcloudException, CosXmlServiceException qcloudServiceException) {
                StringBuilder stringBuilder = new StringBuilder();
                if (qcloudException != null) {
                    stringBuilder.append(qcloudException.getMessage());
                } else {
                    stringBuilder.append(qcloudServiceException.toString());
                }
                Log.w("XIAO", "failed = " + stringBuilder.toString());
                show(activity, stringBuilder.toString());
            }
        });
    }

    private void show(Activity activity, String message) {
        Intent intent = new Intent(activity, ResultActivity.class);
        intent.putExtra("RESULT", message);
        activity.startActivity(intent);
    }
}
