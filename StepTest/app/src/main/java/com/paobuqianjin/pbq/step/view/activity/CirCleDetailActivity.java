package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleDetailAdminFragment;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleDetailNoAdminMainFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class CirCleDetailActivity extends BaseActivity implements CircleDetailInterface {
    private final static String TAG = CirCleDetailActivity.class.getSimpleName();
    /*    private CircleDetailNoAdminMainFragment circleDetailNoAdminMainFragment = new CircleDetailNoAdminMainFragment();*/
    private CircleDetailAdminFragment circleDetailAdminFragment = new CircleDetailAdminFragment();
    private int userIdCircleAdminMain = -1;
    private int circleId = -1;
    private int memberNum = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_detail_activity_layout);
        Presenter.getInstance(this).attachUiInterface(this);
        Intent intent = getIntent();
        if (intent != null) {
            circleId = intent.getIntExtra(getPackageName() + "circleid", -1);
            memberNum = intent.getIntExtra(getPackageName() + "membernum", -1);
            LocalLog.d(TAG, "circleId = " + circleId + " membernum = " + memberNum);
            Presenter.getInstance(this).getCircleDetail(circleId);
        }
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    public void response(CircleDetailResponse circleDetailResponse) {

        LocalLog.d(TAG, "CircleDetailResponse() ");
        userIdCircleAdminMain = circleDetailResponse.getData().getUserid();
        circleDetailAdminFragment.setCircleDetail(circleDetailResponse, memberNum);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.circle_detail_container, circleDetailAdminFragment)
                .show(circleDetailAdminFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
    }
}
