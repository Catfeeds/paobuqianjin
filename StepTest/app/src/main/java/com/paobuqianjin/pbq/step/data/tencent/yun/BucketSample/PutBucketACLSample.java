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
import com.tencent.cos.xml.model.bucket.PutBucketACLRequest;
import com.tencent.cos.xml.model.bucket.PutBucketACLResult;
import com.tencent.cos.xml.model.tag.ACLAccount;
import com.tencent.cos.xml.model.tag.ACLAccounts;


/**
 * Created by bradyxiao on 2017/6/1.
 * author bradyxiao
 *
 * Put Bucket ACL 接口用来写入 Bucket 的 ACL 表，您可以通过 Header："x-cos-acl"，
 * "x-cos-grant-read"，"x-cos-grant-write"，"x-cos-grant-full-control"
 * 传入 ACL 信息，或者通过 Body 以 XML 格式传入 ACL 信息。
 *
 */
public class PutBucketACLSample {
    PutBucketACLRequest putBucketACLRequest;
    QServiceCfg qServiceCfg;

    public PutBucketACLSample(QServiceCfg qServiceCfg){
        this.qServiceCfg = qServiceCfg;
    }

    public ResultHelper start(){
        ResultHelper resultHelper = new ResultHelper();
        String bucket = qServiceCfg.getBucketForBucketAPITest();
        if(bucket == null){
            qServiceCfg.toastShow("bucket 不存在，需要创建");
        }

        putBucketACLRequest = new PutBucketACLRequest(bucket);

        putBucketACLRequest.setXCOSACL("public-read");
        ACLAccounts readAccounts = new ACLAccounts();
        readAccounts.addACLAccount(new ACLAccount("1278687956", "1278687956"));
        putBucketACLRequest.setXCOSGrantRead(readAccounts);

        ACLAccounts writeAccounts = new ACLAccounts();
        writeAccounts.addACLAccount(new ACLAccount("1278687956", "1278687956"));
        putBucketACLRequest.setXCOSGrantWrite(writeAccounts);


        putBucketACLRequest.setSign(600,null,null);
        try {
            PutBucketACLResult putBucketACLResult =
                     qServiceCfg.cosXmlService.putBucketACL(putBucketACLRequest);
            Log.w("XIAO","success");
            resultHelper.cosXmlResult = putBucketACLResult;
            qServiceCfg.setBucketForBucketAPITest(bucket);
            return resultHelper;
        } catch (CosXmlClientException e) {
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

        putBucketACLRequest = new PutBucketACLRequest(bucket);
        putBucketACLRequest.setXCOSACL("public-read");
        ACLAccounts readAccounts = new ACLAccounts();
        readAccounts.addACLAccount(new ACLAccount("1278687956", "1278687956"));
        putBucketACLRequest.setXCOSGrantRead(readAccounts);

        ACLAccounts writeAccounts = new ACLAccounts();
        writeAccounts.addACLAccount(new ACLAccount("1278687956", "1278687956"));
        putBucketACLRequest.setXCOSGrantWrite(writeAccounts);

        putBucketACLRequest.setSign(600,null,null);
        qServiceCfg.cosXmlService.putBucketACLAsync(putBucketACLRequest, new CosXmlResultListener() {
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
