package com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


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
    private final static long T2_M = 2048;

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


    private String saveImage(String sourcePath, String cosPath, Context context) throws FileNotFoundException {
        if (context == null) {
            return null;
        }
        String path = context.getExternalCacheDir() + cosPath;
        LocalLog.d(TAG, "path = " + path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(sourcePath, options);
        } catch (OutOfMemoryError o) {
            try {
                options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                bitmap = BitmapFactory.decodeFile(sourcePath, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bitmap == null) {
            return sourcePath;
        }
        int optionsLimit = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, optionsLimit, baos);
        while (baos.toByteArray().length / 1024 > 2048) {
            LocalLog.d(TAG, "图片大于2M需要压缩");
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, optionsLimit, baos);
            optionsLimit -= 10;
            if (optionsLimit <= 0) {
                break;
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return path;
    }

    /*压缩上传*/
    public ResultHelper start(String filePath, Context appContext) {
        LocalLog.d(TAG, "带压缩上传");
        ResultHelper resultHelper = new ResultHelper();
        String bucket = qServiceCfg.getBucketForObjectAPITest();
        //String cosPath = qServiceCfg.getUploadCosPath();
        /*压缩图片并缓存，上传成功或者失败都删除缓存*/

        String cosPath = "/" + getPicNameFromPath(filePath);
        String cacheBitmapPath = "";
        try {
            cacheBitmapPath = saveImage(filePath, cosPath, appContext);
            qServiceCfg.setUploadFileUrl(cacheBitmapPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String srcPath = qServiceCfg.getUploadFileUrl();
        LocalLog.d(TAG, "srcPath =  " + srcPath);
        LocalLog.d(TAG, "cosPath = " + cosPath);
        putObjectRequest = new PutObjectRequest(bucket, cosPath,
                cacheBitmapPath);

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
        } finally {
            if (!srcPath.equals(cacheBitmapPath)) {
                File bitmapFile = new File(cacheBitmapPath);
                if (!bitmapFile.isDirectory()) {
                    bitmapFile.delete();
                }
                bitmapFile = null;
            }
        }
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
