package com.paobuqianjin.pbq.step.data.alioss;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.ImagePersistRequest;
import com.alibaba.sdk.android.oss.model.ImagePersistResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.MultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.TriggerCallbackRequest;
import com.alibaba.sdk.android.oss.model.TriggerCallbackResult;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.paobuqianjin.pbq.step.utils.DateTimeUtil.getLocalTimeYMD;

/**
 * Created by mOss on 2015/12/7 0007.
 * 支持普通上传，普通下载
 */
public class OssService {
    private final static String TAG = OssService.class.getSimpleName();
    public OSS mOss;
    private String mBucket;
    private UIDisplayer mDisplayer;
    private String mCallbackAddress;
    private static String mResumableObjectKey = "resumableObject";
    private Context context;
    private String resultStr;

    public OssService(OSS oss, String bucket, UIDisplayer uiDisplayer, Context appContext) {
        this.mOss = oss;
        this.mBucket = bucket;
        this.mDisplayer = uiDisplayer;
        context = appContext;
    }

    public void setBucketName(String bucket) {
        this.mBucket = bucket;
    }

    public void initOss(OSS _oss) {
        this.mOss = _oss;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.mCallbackAddress = callbackAddress;
    }

    public void asyncGetImage(String object) {

        final long get_start = System.currentTimeMillis();
        if ((object == null) || object.equals("")) {
            Log.w("AsyncGetImage", "ObjectNull");
            return;
        }

        GetObjectRequest get = new GetObjectRequest(mBucket, object);
        get.setCRC64(OSSRequest.CRC64Config.YES);
        get.setProgressListener(new OSSProgressCallback<GetObjectRequest>() {
            @Override
            public void onProgress(GetObjectRequest request, long currentSize, long totalSize) {
                Log.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                if (mDisplayer != null) {
                    mDisplayer.updateProgress(progress);
                    mDisplayer.displayInfo("下载进度: " + String.valueOf(progress) + "%");
                }
            }
        });
        OSSAsyncTask task = mOss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                // 请求成功
                InputStream inputStream = result.getObjectContent();
                //Bitmap bm = BitmapFactory.decodeStream(inputStream);
                try {
                    //需要根据对应的View大小来自适应缩放
                    if (mDisplayer != null) {
                        Bitmap bm = mDisplayer.autoResizeFromStream(inputStream);
                        long get_end = System.currentTimeMillis();
                        OSSLog.logDebug("get cost: " + (get_end - get_start) / 1000f);
                        mDisplayer.downloadComplete(bm);
                        mDisplayer.displayInfo("Bucket: " + mBucket + "\nObject: " + request.getObjectKey() + "\nRequestId: " + result.getRequestId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                if (mDisplayer != null) {
                    mDisplayer.downloadFail(info);
                    mDisplayer.displayInfo(info);
                }
            }
        });
    }

    public String getRomoteFileName(Context context) {
        String index0 = String.valueOf(FlagPreference.getUid(context));
        String index1 = DateTimeUtil.getCurrentDateString();
        String index3 = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        return index0 + index1 + index3;
    }

    public String getUploadFileName(String localFile) {
        String path = null;
        String[] timeYYMMYY = getLocalTimeYMD();
        if (timeYYMMYY != null && timeYYMMYY.length == 3) {
            path = timeYYMMYY[0] + "/" + timeYYMMYY[1] + "/" + timeYYMMYY[2];
            int index = localFile.lastIndexOf(".");
            String fileAppend = localFile.substring(index, localFile.length());
            LocalLog.d(TAG, "fileAppend =  " + fileAppend);
            path = path + "/" + getRomoteFileName(context) + fileAppend;
        }
        return path;
    }

    public String asyncPutImageLocal(String localFile) {
        String result = null;
        String path = getUploadFileName(localFile);
        if (!TextUtils.isEmpty(localFile)) {
            result = syncPutImage(path, localFile);
            LocalLog.d(TAG, "result = " + result);
        }
        return result;
    }

    private File getDiskCacheDir(Context context) {
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
            LocalLog.d(TAG, "getExternalCachdir() = " + cachePath);
        } else {
            cachePath = context.getCacheDir();
            LocalLog.d(TAG, "getCacheDir() = " + cachePath);
        }
        return cachePath;
    }

    private String saveImage(String sourcePath, Context context) throws FileNotFoundException {
        if (context == null) {
            return null;
        }
        int index = sourcePath.lastIndexOf(".");
        String fileAppend = sourcePath.substring(index, sourcePath.length());
        String path = getDiskCacheDir(context) + "tmp" + fileAppend;
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
        if (baos.toByteArray().length / 1024 < 2048) {
            return sourcePath;
        }
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

    private String syncPutImage(final String object, String localFile) {
        LocalLog.d(TAG, "object = " + object);
        resultStr = null;
        if (object.equals("")) {
            Log.w("AsyncPutImage", "ObjectNull");
            return null;
        }
        String realUploadFile = null;
        try {
            realUploadFile = saveImage(localFile, context);
        } catch (Exception e) {
            e.printStackTrace();
            return resultStr;
        }
        File file = new File(realUploadFile);
        if (!file.exists()) {
            Log.w("AsyncPutImage", "FileNotExist");
            Log.w("LocalFile", realUploadFile);
            return null;
        }


        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(mBucket, object, localFile);
        put.setCRC64(OSSRequest.CRC64Config.YES);
        try {
            PutObjectResult resultPut = mOss.putObject(put);
            LocalLog.d(TAG, "result = " + resultPut.toString());
            if (resultPut.getStatusCode() == 200) {
                resultStr = Config.endPointUrl + "/" + object;
                if (!localFile.equals(realUploadFile)) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                    file = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    // Downloads the files with specified prefix in the asynchronous way.
    public void asyncListObjectsWithBucketName() {
        ListObjectsRequest listObjects = new ListObjectsRequest(mBucket);
        // Sets the prefix
        listObjects.setPrefix("android");
        listObjects.setDelimiter("/");
        // Sets the success and failure callback. calls the Async API
        OSSAsyncTask task = mOss.asyncListObjects(listObjects, new OSSCompletedCallback<ListObjectsRequest, ListObjectsResult>() {
            @Override
            public void onSuccess(ListObjectsRequest request, ListObjectsResult result) {
                String info = "";
                OSSLog.logDebug("AyncListObjects", "Success!");
                for (int i = 0; i < result.getObjectSummaries().size(); i++) {
                    info += "\n" + String.format("object: %s %s %s", result.getObjectSummaries().get(i).getKey(), result.getObjectSummaries().get(i).getETag(), result.getObjectSummaries().get(i).getLastModified().toString());
                    OSSLog.logDebug("AyncListObjects", info);
                }
                if (mDisplayer != null) {
                    mDisplayer.displayInfo(info);
                }
            }

            @Override
            public void onFailure(ListObjectsRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // request exception
                if (clientExcepion != null) {
                    // client side exception such as network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // service side exception.
                    OSSLog.logError("ErrorCode", serviceException.getErrorCode());
                    OSSLog.logError("RequestId", serviceException.getRequestId());
                    OSSLog.logError("HostId", serviceException.getHostId());
                    OSSLog.logError("RawMessage", serviceException.getRawMessage());
                }
                if (mDisplayer != null) {
                    mDisplayer.downloadFail("Failed!");
                    mDisplayer.displayInfo(serviceException.toString());
                }
            }
        });
    }

    // Gets file's metadata
    public void headObject(String objectKey) {
        // Creates a request to get the file's metadata
        HeadObjectRequest head = new HeadObjectRequest(mBucket, objectKey);

        OSSAsyncTask task = mOss.asyncHeadObject(head, new OSSCompletedCallback<HeadObjectRequest, HeadObjectResult>() {
            @Override
            public void onSuccess(HeadObjectRequest request, HeadObjectResult result) {
                OSSLog.logDebug("headObject", "object Size: " + result.getMetadata().getContentLength());
                OSSLog.logDebug("headObject", "object Content Type: " + result.getMetadata().getContentType());
                if (mDisplayer != null) {
                    mDisplayer.displayInfo(result.toString());
                }
            }

            @Override
            public void onFailure(HeadObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // request exception
                if (clientExcepion != null) {
                    // client side exception,  such as network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // service side exception
                    OSSLog.logError("ErrorCode", serviceException.getErrorCode());
                    OSSLog.logError("RequestId", serviceException.getRequestId());
                    OSSLog.logError("HostId", serviceException.getHostId());
                    OSSLog.logError("RawMessage", serviceException.getRawMessage());
                }
                if (mDisplayer != null) {
                    mDisplayer.downloadFail("Failed!");
                    mDisplayer.displayInfo(serviceException.toString());
                }
            }
        });
    }

    public void asyncMultipartUpload(String uploadKey, String uploadFilePath) {
        MultipartUploadRequest request = new MultipartUploadRequest(mBucket, uploadKey,
                uploadFilePath);
        request.setCRC64(OSSRequest.CRC64Config.YES);
        request.setProgressCallback(new OSSProgressCallback<MultipartUploadRequest>() {

            @Override
            public void onProgress(MultipartUploadRequest request, long currentSize, long totalSize) {
                OSSLog.logDebug("[testMultipartUpload] - " + currentSize + " " + totalSize, false);
            }
        });
        mOss.asyncMultipartUpload(request, new OSSCompletedCallback<MultipartUploadRequest, CompleteMultipartUploadResult>() {
            @Override
            public void onSuccess(MultipartUploadRequest request, CompleteMultipartUploadResult result) {
                if (mDisplayer != null) {
                    mDisplayer.uploadComplete();
                    mDisplayer.displayInfo(request.toString());
                }
            }

            @Override
            public void onFailure(MultipartUploadRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(clientException.toString());
                    }
                } else if (serviceException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(serviceException.toString());
                    }
                }

            }
        });
    }

    void asyncResumableUpload(String resumableFilePath) {
        ResumableUploadRequest request = new ResumableUploadRequest(mBucket, mResumableObjectKey, resumableFilePath);
        request.setProgressCallback(new OSSProgressCallback<ResumableUploadRequest>() {
            @Override
            public void onProgress(ResumableUploadRequest request, long currentSize, long totalSize) {
                Log.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                if (mDisplayer != null) {
                    mDisplayer.updateProgress(progress);
                    mDisplayer.displayInfo("上传进度: " + String.valueOf(progress) + "%");
                }
            }
        });
        OSSAsyncTask task = mOss.asyncResumableUpload(request, new OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult>() {
            @Override
            public void onSuccess(ResumableUploadRequest request, ResumableUploadResult result) {
                if (mDisplayer != null) {
                    mDisplayer.uploadComplete();
                    mDisplayer.displayInfo(request.toString());
                }
            }

            @Override
            public void onFailure(ResumableUploadRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(clientException.toString());
                    }
                } else if (serviceException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(serviceException.toString());
                    }
                }
            }
        });
    }

    // If the bucket is private, the signed URL is required for the access.
    // Expiration time is specified in the signed URL.
    public void presignConstrainedURL() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Gets the signed url, the expiration time is 5 minute
                    String url = mOss.presignConstrainedObjectURL(mBucket, "androidTest.jpeg", 5 * 60);
                    OSSLog.logDebug("signContrainedURL", "get url: " + url);
                    // 访问该url
                    Request request = new Request.Builder().url(url).build();
                    Response resp = null;

                    resp = new OkHttpClient().newCall(request).execute();

                    if (resp.code() == 200) {
                        OSSLog.logDebug("signContrainedURL", "object size: " + resp.body().contentLength());
                        if (mDisplayer != null) {
                            mDisplayer.displayInfo(resp.toString());
                        }
                    } else {
                        OSSLog.logDebug("signContrainedURL", "get object failed, error code: " + resp.code()
                                + "error message: " + resp.message());
                        if (mDisplayer != null) {
                            mDisplayer.displayInfo(resp.toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(e.toString());
                    }
                } catch (ClientException e) {
                    e.printStackTrace();
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(e.toString());
                    }
                }
            }
        }).start();
    }

    /**
     * Delete a non-empty bucket.
     * Create a bucket, and add files into it.
     * Try to delete the bucket and failure is expected.
     * Then delete file and then delete bucket
     */
    public void deleteNotEmptyBucket(final String bucket, final String filePath) {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        // 创建bucket
        try {
            mOss.createBucket(createBucketRequest);
        } catch (ClientException clientException) {
            clientException.printStackTrace();
            if (mDisplayer != null) {
                mDisplayer.displayInfo(clientException.toString());
            }
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
            if (mDisplayer != null) {
                mDisplayer.displayInfo(serviceException.toString());
            }
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, "test-file", filePath);
        try {
            mOss.putObject(putObjectRequest);
        } catch (ClientException clientException) {
            clientException.printStackTrace();
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
        }
        final DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucket);
        OSSAsyncTask deleteBucketTask = mOss.asyncDeleteBucket(deleteBucketRequest, new OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult>() {
            @Override
            public void onSuccess(DeleteBucketRequest request, DeleteBucketResult result) {
                OSSLog.logDebug("DeleteBucket", "Success!");
                if (mDisplayer != null) {
                    mDisplayer.displayInfo(result.toString());
                }
            }

            @Override
            public void onFailure(DeleteBucketRequest request, ClientException clientException, ServiceException serviceException) {
                // request exception
                if (clientException != null) {
                    // client side exception,  such as network exception
                    clientException.printStackTrace();
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(clientException.toString());
                    }
                }
                if (serviceException != null) {
                    // The bucket to delete is not empty.
                    if (serviceException.getStatusCode() == 409) {
                        // Delete files
                        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, "test-file");
                        try {
                            mOss.deleteObject(deleteObjectRequest);
                        } catch (ClientException clientexception) {
                            clientexception.printStackTrace();
                        } catch (ServiceException serviceexception) {
                            serviceexception.printStackTrace();
                        }
                        // Delete bucket again
                        DeleteBucketRequest deleteBucketRequest1 = new DeleteBucketRequest(bucket);
                        try {
                            mOss.deleteBucket(deleteBucketRequest1);
                        } catch (ClientException clientexception) {
                            clientexception.printStackTrace();
                            if (mDisplayer != null) {
                                mDisplayer.displayInfo(clientexception.toString());
                            }
                            return;
                        } catch (ServiceException serviceexception) {
                            serviceexception.printStackTrace();
                            if (mDisplayer != null) {
                                mDisplayer.displayInfo(serviceexception.toString());
                            }
                            return;
                        }
                        OSSLog.logDebug("DeleteBucket", "Success!");
                        if (mDisplayer != null) {
                            mDisplayer.displayInfo("The Operation of Deleting Bucket is successed!");
                        }
                    }
                }
            }
        });
    }

    public void customSign(Context ctx, String endpoint) {
        OSSCustomSignerCredentialProvider provider = new OSSCustomSignerCredentialProvider() {
            @Override
            public String signContent(String content) {

                // 此处本应该是客户端将contentString发送到自己的业务服务器,然后由业务服务器返回签名后的content。关于在业务服务器实现签名算法
                // 详情请查看http://help.aliyun.com/document_detail/oss/api-reference/access-control/signature-header.html。客户端
                // 的签名算法实现请参考OSSUtils.sign(accessKey,screctKey,content)

                String signedString = OSSUtils.sign("AK", "SK", content);
                return signedString;
            }
        };
        OSSClient tClient = new OSSClient(ctx, endpoint, provider);

        GetObjectRequest get = new GetObjectRequest(mBucket, "androidTest.jpeg");
        get.setCRC64(OSSRequest.CRC64Config.YES);
        get.setProgressListener(new OSSProgressCallback<GetObjectRequest>() {
            @Override
            public void onProgress(GetObjectRequest request, long currentSize, long totalSize) {
                Log.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                if (mDisplayer != null) {
                    mDisplayer.updateProgress(progress);
                    mDisplayer.displayInfo("下载进度: " + String.valueOf(progress) + "%");
                }
            }
        });
        OSSAsyncTask task = tClient.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                if (mDisplayer != null) {
                    mDisplayer.displayInfo("使用自签名获取网络对象成功！");
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(clientException.toString());
                    }
                } else if (serviceException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(serviceException.toString());
                    }
                }
            }
        });
    }

    public void triggerCallback(Context ctx, String endpoint) {
        OSSPlainTextAKSKCredentialProvider provider = new OSSPlainTextAKSKCredentialProvider("AK", "SK");
        OSSClient tClient = new OSSClient(ctx, endpoint, provider);

        Map<String, String> callbackParams = new HashMap<String, String>();
        callbackParams.put("callbackUrl", "callbackURL");
        callbackParams.put("callbackBody", "callbackBody");

        Map<String, String> callbackVars = new HashMap<String, String>();
        callbackVars.put("key1", "value1");
        callbackVars.put("key2", "value2");

        TriggerCallbackRequest request = new TriggerCallbackRequest("bucketName", "objectKey", callbackParams, callbackVars);

        OSSAsyncTask task = tClient.asyncTriggerCallback(request, new OSSCompletedCallback<TriggerCallbackRequest, TriggerCallbackResult>() {
            @Override
            public void onSuccess(TriggerCallbackRequest request, TriggerCallbackResult result) {
                if (mDisplayer != null) {
                    mDisplayer.displayInfo(result.getServerCallbackReturnBody());
                }
            }

            @Override
            public void onFailure(TriggerCallbackRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(clientException.toString());
                    }
                } else if (serviceException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(serviceException.toString());
                    }
                }
            }
        });

    }

    public void imagePersist(String fromBucket, String fromObjectKey, String toBucket, String toObjectkey, String action) {

        ImagePersistRequest request = new ImagePersistRequest(fromBucket, fromObjectKey, toBucket, toObjectkey, action);

        OSSAsyncTask task = mOss.asyncImagePersist(request, new OSSCompletedCallback<ImagePersistRequest, ImagePersistResult>() {
            @Override
            public void onSuccess(ImagePersistRequest request, ImagePersistResult result) {
//                mDisplayer.displayInfo(result.getServerCallbackReturnBody());
            }

            @Override
            public void onFailure(ImagePersistRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(clientException.toString());
                    }
                } else if (serviceException != null) {
                    if (mDisplayer != null) {
                        mDisplayer.displayInfo(serviceException.toString());
                    }
                }
            }
        });
    }
}
