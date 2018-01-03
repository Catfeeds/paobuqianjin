package com.paobuqianjin.pbq.step.data.tencent.yun.BucketSample;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultActivity;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleResult;


/**
 * Created by bradyxiao on 2017/6/1.
 * author bradyxiao
 *
 * Get Bucket Lifecycle 用来查询 Bucket 的生命周期配置。如果该 Bucket 没有配置生命周期规则会返回 NoSuchLifecycle。
 *
 *
 */

public class GetBucketLifecycleSample {
    GetBucketLifecycleRequest getBucketLifecycleRequest;
    QServiceCfg qServiceCfg;

    public GetBucketLifecycleSample(QServiceCfg qServiceCfg){
        this.qServiceCfg = qServiceCfg;
    }

    public ResultHelper start(){
        ResultHelper resultHelper = new ResultHelper();
        String bucket = qServiceCfg.getBucketForBucketAPITest();
        if(bucket == null){
            qServiceCfg.toastShow("bucket 不存在，需要创建");
        }

        getBucketLifecycleRequest = new GetBucketLifecycleRequest(bucket);
        getBucketLifecycleRequest.setSign(600,null,null);
        try {
            GetBucketLifecycleResult getBucketLifecycleResult =
                    qServiceCfg.cosXmlService.getBucketLifecycle(getBucketLifecycleRequest);
            Log.w("XIAO","success");
            resultHelper.cosXmlResult = getBucketLifecycleResult;
            return resultHelper;
        }catch (CosXmlClientException e) {
            Log.w("XIAO","QCloudException =" + e.getMessage());
            resultHelper.qCloudException = e;
            return resultHelper;
        } catch (CosXmlServiceException e) {
            Log.w("XIAO","QCloudServiceException =" + e.toString());
            resultHelper.qCloudServiceException = e;
            return resultHelper;
        }
    }

    /**
     *
     * 采用异步回调操作
     *
     */
    public void startAsync(final Activity activity){
        String bucket = qServiceCfg.getBucketForBucketAPITest();
        if(bucket == null){
            qServiceCfg.toastShow("bucket 不存在，需要创建");
        }

        getBucketLifecycleRequest = new GetBucketLifecycleRequest(bucket);
        getBucketLifecycleRequest.setSign(600,null,null);
        qServiceCfg.cosXmlService.getBucketLifecycleAsync(getBucketLifecycleRequest, new CosXmlResultListener() {
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
                if(qcloudException != null){
                    stringBuilder.append(qcloudException.getMessage());
                }else {
                    stringBuilder.append(qcloudServiceException.toString());
                }
                Log.w("XIAO", "failed = " + stringBuilder.toString());
                show(activity, stringBuilder.toString());
            }
        });
    }

    private void show(Activity activity, String message){
        Intent intent = new Intent(activity, ResultActivity.class);
        intent.putExtra("RESULT", message);
        activity.startActivity(intent);
    }
}
