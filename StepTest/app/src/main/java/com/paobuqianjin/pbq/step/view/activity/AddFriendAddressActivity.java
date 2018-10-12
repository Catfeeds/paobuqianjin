package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.AddFriendFragment;

/**
 * Created by pbq on 2018/1/18.
 */

public class AddFriendAddressActivity extends BaseActivity {
    private final static String TAG = AddFriendAddressActivity.class.getSimpleName();
    private AddFriendFragment addFriendFragment = new AddFriendFragment();
    public static final int REQUEST_CODE = 0x0000c0de; // Only use bottom 16 bits
    private final static String ACTION_SCAN_CIRCLE_ID = "com.paobuqianjin.pbq.step.SCAN_ACTION";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_wx_qq_phone_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.add_friend_container, addFriendFragment)
                .show(addFriendFragment)
                .commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && resultCode == REQUEST_CODE) {
            String scanResult = data.getStringExtra(getPackageName() + "scanresult");
            if (scanResult == null) {
                Toast.makeText(this, "内容为空", Toast.LENGTH_LONG).show();
            } else {
                // ScanResult 为 获取到的字符串
                String ScanResult = scanResult;
                LocalLog.d(TAG, ScanResult);
                if (ScanResult.startsWith(NetApi.urlShareIc)) {
                    LocalLog.d(TAG, "扫描个人");
                    String userNo = ScanResult.substring(NetApi.urlShareIc.length(), ScanResult.length());
                    LocalLog.d(TAG, "userid = " + userNo);
                    try {
                        Intent intent = new Intent();
                        //TODO ACTION_SCAN_USERID
                        intent.putExtra("userno", userNo);
                        intent.setClass(this, FriendDetailActivity.class);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                } else if (ScanResult.startsWith(NetApi.urlShareCd)) {
                    LocalLog.d(TAG, "扫描圈子");
                    String circleid = ScanResult.substring(NetApi.urlShareCd.length(), ScanResult.length());
                    LocalLog.d(TAG, "circleid = " + circleid);
                    //TODO ACTION_SCAND_CIRCLE_ID
                    try {
                        Intent intent = new Intent();
                        intent.setClass(this, CirCleDetailActivity.class);
                        intent.putExtra(getPackageName() + "circleid", Integer.parseInt(circleid));
                        intent.setAction(ACTION_SCAN_CIRCLE_ID);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
