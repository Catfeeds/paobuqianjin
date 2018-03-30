package com.paobuqianjin.pbq.step.view.activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.UserInfoSettingFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by pbq on 2018/1/5.
 */

public class UserInfoSettingActivity extends BaseActivity {
    private final static String TAG = UserInfoSettingActivity.class.getSimpleName();
    private UserInfoSettingFragment userInfoSettingFragment = new UserInfoSettingFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_setting_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_info_setting_container, userInfoSettingFragment)
                .show(userInfoSettingFragment)
                .commit();
    }

}
