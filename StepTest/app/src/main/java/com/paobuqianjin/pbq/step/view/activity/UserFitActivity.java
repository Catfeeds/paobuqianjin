package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.login.BindPhoneFragment;
import com.paobuqianjin.pbq.step.view.fragment.login.PersonInfoSettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/3/23.
 */
/*
@className :UserFitActivity
*@date 2018/3/23
*@author
*@description 完善用户信息
*/
public class UserFitActivity extends BaseActivity {
    private final static String TAG = UserFitActivity.class.getSimpleName();
    private final static String USER_FIT_ACTION_BIND = "com.paobuqianjin.pbq.USER_FIT_ACTION_BIND";
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    private BindPhoneFragment bindPhoneFragment = new BindPhoneFragment();
    private PersonInfoSettingFragment personInfoSettingFragment = new PersonInfoSettingFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fit_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (USER_FIT_ACTION_BIND.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.user_fit_container, bindPhoneFragment)
                        .show(bindPhoneFragment).commit();
            } else if (USER_FIT_ACTION_SETTING.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.user_fit_container, personInfoSettingFragment)
                        .show(personInfoSettingFragment).commit();
            }
        } else {
            finish();
        }
    }

    public void showPersonInfo(ThirdPartyLoginResponse.DataBean dataBean) {
        LocalLog.d(TAG, "showPersonInfo() enter 跳转到个人资料修改页");
        getSupportFragmentManager().beginTransaction()
                .hide(bindPhoneFragment)
                .add(R.id.pay_container, personInfoSettingFragment)
                .show(personInfoSettingFragment)
                .commit();
        personInfoSettingFragment.setDataBen(dataBean);
    }
}
