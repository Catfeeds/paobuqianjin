package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.FriendAddressActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/18.
 */

public class AddFriendFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = AddFriendFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.address_mail)
    ImageView addressMail;
    @Bind(R.id.phone_address)
    TextView phoneAddress;
    @Bind(R.id.mail_address_layer)
    RelativeLayout mailAddressLayer;
    @Bind(R.id.address_weichat)
    ImageView addressWeichat;
    @Bind(R.id.weichat_address)
    TextView weichatAddress;
    @Bind(R.id.address_weichat_layer)
    RelativeLayout addressWeichatLayer;
    @Bind(R.id.address_qq)
    ImageView addressQq;
    @Bind(R.id.qq_address)
    TextView qqAddress;
    @Bind(R.id.qq_address_layer)
    RelativeLayout qqAddressLayer;

    @Override
    protected String title() {
        return "添加关注";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_add_from_wx_phone_qq;
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
        mailAddressLayer = (RelativeLayout) viewRoot.findViewById(R.id.mail_address_layer);
        addressWeichatLayer = (RelativeLayout) viewRoot.findViewById(R.id.address_weichat_layer);
        qqAddressLayer = (RelativeLayout) viewRoot.findViewById(R.id.qq_address_layer);
        mailAddressLayer.setOnClickListener(onClickListener);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.mail_address_layer:
                    LocalLog.d(TAG, " 通讯录好友");
                    Intent friendAddressIntent = new Intent();
                    friendAddressIntent.setClass(getContext(), FriendAddressActivity.class);
                    startActivity(friendAddressIntent);
                    break;
                case R.id.address_weichat_layer:
                    break;
                case R.id.qq_address_layer:
                    break;
            }
        }
    };

}
