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
import com.paobuqianjin.pbq.step.view.activity.GoldenSponsoractivity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/5/22.
 */

public class SponsorVipFragment extends BaseFragment {
    private final static String TAG = SponsorVipFragment.class.getSimpleName();
    private final static String ACTION_VIP_SPONSOR_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_SPONSOR_ACTION";
    /*private final static String ACTION_VIP_SPONSOR_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_SPONSOR_ACTION";*/
    private final static String ACTION_GOLDEN_VIP = "com.paobuqianjin.pbq.step.VIP_GOLDEN_ACTION";
    private final static int PAY_VIP_SPONSOR_SELF_RESULT = 501;
    private final static int PAY_VIP_SPONSOR_FRIEND_RESULT = 502;
    @Bind(R.id.vip_friend)
    Button vipFriend;
    @Bind(R.id.vip_self)
    Button vipSelf;
    private UserInfoResponse.DataBean currentUser;

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_vip_fg;
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
            if (currentUser.getGvip() == 1) {
                vipSelf.setText("已购买");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.vip_self})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vip_self:
                LocalLog.d(TAG, "pay for  self  sponsor vip");
                if (currentUser.getGvip() == 1) {
                    LocalLog.d(TAG, "已经是商家VIP");
                    return;
                }
                Intent intent = new Intent();
                intent.setAction(ACTION_GOLDEN_VIP);
                intent.setClass(getContext(), PaoBuPayActivity.class);
                startActivityForResult(intent, PAY_VIP_SPONSOR_SELF_RESULT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PAY_VIP_SPONSOR_SELF_RESULT) {
                LocalLog.d(TAG, "购买商家金牌会员成功！");
                vipSelf.setText("已购买");
                vipSelf.setEnabled(false);
            }
        }
    }
}
