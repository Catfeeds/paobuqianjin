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
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/25.
 */

public class VipFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = VipFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.vip_banner)
    ImageView vipBanner;
    @Bind(R.id.vip_des_title)
    TextView vipDesTitle;
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
    private final static String ACTION_VIP_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_ACTION";
    private final static String ACTION_VIP_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_ACTION";
    private final static int PAY_VIP_SELF_RESULT = 1;
    private final static int PAY_VIP_FRIEND_RESULT = 2;
    private int vip;

    @Override
    protected int getLayoutResId() {
        return R.layout.vip_fg;
    }

    @Override
    protected String title() {
        return "会员专享";
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        vipSelf = (Button) viewRoot.findViewById(R.id.vip_self);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            vip = intent.getIntExtra("vip", 0);
            if (vip == 1) {
                vipSelf.setText("已购买");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
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
                payIntent.setAction(ACTION_VIP_FRIEND);
                payIntent.setClass(getContext(), PaoBuPayActivity.class);
                startActivityForResult(payIntent, PAY_VIP_FRIEND_RESULT);
                break;
            case R.id.vip_self:
                if (vip == 1) {
                    LocalLog.d(TAG, "已经是VIP");
                    return;
                }
                payIntent.setAction(ACTION_VIP_SELF);
                payIntent.setClass(getContext(), PaoBuPayActivity.class);
                startActivityForResult(payIntent, PAY_VIP_SELF_RESULT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PAY_VIP_SELF_RESULT:
                LocalLog.d(TAG, "自充VIP");
                break;
        }
    }
}
