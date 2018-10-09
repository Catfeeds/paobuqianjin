package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/5/22.
 */

public class PersonVipFragment extends BaseFragment {
    private final static String TAG = PersonVipFragment.class.getSimpleName();
    private final static String ACTION_VIP_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_ACTION";
    private final static String ACTION_VIP_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_ACTION";
    private final static int PAY_VIP_SELF_RESULT = 401;
    private final static int PAY_VIP_FRIEND_RESULT = 402;
    @Bind(R.id.vip_head_ico)
    ImageView vipHeadIco;
    @Bind(R.id.vip_head_des)
    TextView vipHeadDes;
    @Bind(R.id.vip_head_pan)
    RelativeLayout vipHeadPan;
    @Bind(R.id.vip_pkg_ico)
    ImageView vipPkgIco;
    @Bind(R.id.vip_pkg_des)
    TextView vipPkgDes;
    @Bind(R.id.vip_pkg_pan)
    RelativeLayout vipPkgPan;
    @Bind(R.id.vip_time_ico)
    ImageView vipTimeIco;
    @Bind(R.id.vip_timer_des)
    TextView vipTimerDes;
    @Bind(R.id.vip_time_pan)
    RelativeLayout vipTimePan;
    @Bind(R.id.vip_data_ico)
    ImageView vipDataIco;
    @Bind(R.id.vip_data_des)
    TextView vipDataDes;
    @Bind(R.id.vip_data_pan)
    RelativeLayout vipDataPan;
    @Bind(R.id.vip_friend)
    Button vipFriend;
    @Bind(R.id.vip_self)
    Button vipSelf;
    private UserInfoResponse.DataBean currentUser;

    @Override
    protected int getLayoutResId() {
        return R.layout.person_vip_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        currentUser = Presenter.getInstance(getContext()).getCurrentUser();
        vipSelf = (Button) viewRoot.findViewById(R.id.vip_self);
        if (currentUser == null) {
            LocalLog.d(TAG, "未能获取UserInfo！");

        } else {
            if (currentUser.getVip() == 1) {
                vipSelf.setText("已购买");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.vip_friend, R.id.vip_self})
    public void onClick(View view) {
        Intent payIntent = new Intent();
        switch (view.getId()) {
            case R.id.vip_friend:
                LocalLog.d(TAG, "pay for friend person vip");
                payIntent.setAction(ACTION_VIP_FRIEND);
                payIntent.setClass(getContext(), PaoBuPayActivity.class);
                startActivityForResult(payIntent, PAY_VIP_FRIEND_RESULT);
                break;
            case R.id.vip_self:
                LocalLog.d(TAG, "pay for self person vip");
                if (currentUser != null && currentUser.getVip() == 1) {
                    LocalLog.d(TAG, "已经是个人VIP");
                    return;
                }
                payIntent.setAction(ACTION_VIP_SELF);
                payIntent.setClass(getContext(), PaoBuPayActivity.class);
                startActivityForResult(payIntent, PAY_VIP_SELF_RESULT);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PAY_VIP_SELF_RESULT) {
                LocalLog.d(TAG, "购买个人VIP成功！");
                vipSelf.setText("已购买");
                vipSelf.setEnabled(false);
            }
        }
    }
}