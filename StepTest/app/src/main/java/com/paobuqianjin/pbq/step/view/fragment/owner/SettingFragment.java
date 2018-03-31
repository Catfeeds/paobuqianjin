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
import com.paobuqianjin.pbq.step.view.activity.UserInfoSettingActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/30.
 */

public class SettingFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = SettingFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.go_pic)
    ImageView goPic;
    @Bind(R.id.user_head_icon_change)
    RelativeLayout userHeadIconChange;
    @Bind(R.id.line_change_ico)
    ImageView lineChangeIco;
    @Bind(R.id.go_pic1)
    ImageView goPic1;
    @Bind(R.id.user_name_change)
    RelativeLayout userNameChange;
    @Bind(R.id.line_change_male)
    ImageView lineChangeMale;
    @Bind(R.id.go_pic2)
    ImageView goPic2;
    @Bind(R.id.change_male)
    RelativeLayout changeMale;
    @Bind(R.id.line_location_name)
    ImageView lineLocationName;
    @Bind(R.id.go_pic3)
    ImageView goPic3;
    @Bind(R.id.change_birth)
    RelativeLayout changeBirth;
    @Bind(R.id.exit)
    Button exit;
    @Bind(R.id.user_ico)
    CircleImageView userIco;
    private UserInfoResponse.DataBean userInfo;

    @Override
    protected int getLayoutResId() {
        return R.layout.setting_fg;
    }

    @Override
    protected String title() {
        return "设置";
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
        userIco = (CircleImageView) viewRoot.findViewById(R.id.user_ico);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            userInfo = (UserInfoResponse.DataBean) intent.getSerializableExtra("userinfo");
            if (userInfo != null) {
                Presenter.getInstance(getContext()).getImage(userIco, userInfo.getAvatar());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.user_head_icon_change, R.id.user_name_change, R.id.change_male, R.id.change_birth, R.id.exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_head_icon_change:
                LocalLog.d(TAG, "个人资料");
                if (userInfo != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfo);
                    userInfoIntent.setClass(getContext(), UserInfoSettingActivity.class);
                    startActivity(userInfoIntent);
                }

                break;
            case R.id.user_name_change:
                LocalLog.d(TAG, "账号设置");
                break;
            case R.id.change_male:
                LocalLog.d(TAG, "清除缓存");
                break;
            case R.id.change_birth:
                LocalLog.d(TAG, "关于我们");
                break;
            case R.id.exit:
                LocalLog.d(TAG, "退出APP");
                Presenter.getInstance(getContext()).setId(-1);
                Presenter.getInstance(getContext()).steLogFlg(false);
                getActivity().finish();
                System.exit(0);
                break;
        }
    }
}
