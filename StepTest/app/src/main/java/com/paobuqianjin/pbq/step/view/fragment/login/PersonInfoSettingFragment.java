package com.paobuqianjin.pbq.step.view.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/2/1.
 */

public class PersonInfoSettingFragment extends BaseFragment implements UserInfoLoginSetInterface {
    private final static String TAG = PersonInfoSettingFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.user_icon)
    CircleImageView userIcon;
    @Bind(R.id.camema)
    ImageView camema;
    @Bind(R.id.head_ico)
    RelativeLayout headIco;
    @Bind(R.id.mofify_iocn)
    TextView mofifyIocn;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.dear_des)
    TextView dearDes;
    @Bind(R.id.use_name)
    EditText useName;
    @Bind(R.id.dear_name_span)
    RelativeLayout dearNameSpan;
    @Bind(R.id.gender_des)
    TextView genderDes;
    @Bind(R.id.use_gender_man_select)
    ImageView useGenderManSelect;
    @Bind(R.id.man)
    TextView man;
    @Bind(R.id.use_gender_nv_select)
    ImageView useGenderNvSelect;
    @Bind(R.id.nv)
    TextView nv;
    @Bind(R.id.gernder_span)
    RelativeLayout gernderSpan;
    @Bind(R.id.birth_des)
    TextView birthDes;
    @Bind(R.id.setting_birth)
    TextView settingBirth;
    @Bind(R.id.goto_birth)
    ImageView gotoBirth;
    @Bind(R.id.birth_day_span)
    RelativeLayout birthDaySpan;
    @Bind(R.id.height_des)
    TextView heightDes;
    @Bind(R.id.setting_height)
    TextView settingHeight;
    @Bind(R.id.goto_height)
    ImageView gotoHeight;
    @Bind(R.id.height_span)
    RelativeLayout heightSpan;
    @Bind(R.id.weight_des)
    TextView weightDes;
    @Bind(R.id.setting_weight)
    TextView settingWeight;
    @Bind(R.id.goto_weight)
    ImageView gotoWeight;
    @Bind(R.id.weight_span)
    RelativeLayout weightSpan;
    ThirdPartyLoginResponse.DataBean dataBean;
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    boolean[] select = {false, false};
    @Bind(R.id.confirm)
    Button confirm;
    private View popSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;

    @Override
    protected int getLayoutResId() {
        return R.layout.person_message_fg;
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
        userIcon = (CircleImageView) viewRoot.findViewById(R.id.user_icon);
        useName = (EditText) viewRoot.findViewById(R.id.use_name);
        useGenderManSelect = (ImageView) viewRoot.findViewById(R.id.use_gender_man_select);
        useGenderNvSelect = (ImageView) viewRoot.findViewById(R.id.use_gender_nv_select);
        confirm = (Button) viewRoot.findViewById(R.id.confirm);
        userIcon.setOnClickListener(onClickListener);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (USER_FIT_ACTION_SETTING.equals(intent.getAction())) {

            }
        } else {
            update(dataBean);
        }
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.user_icon:
                    LocalLog.d(TAG, "更换头像");
                    setBirthDay();
                    break;
                case R.id.use_gender_man_select:
                    LocalLog.d(TAG, "选择男");
                    break;
                case R.id.use_gender_nv_select:
                    LocalLog.d(TAG, "选女");

                    break;
            }
        }
    };


    private void setBirthDay() {
        LocalLog.d(TAG, "popRedPkgButton() 弹出红包");
        popSelectView = View.inflate(getContext(), R.layout.wheel_select_layout, null);
        popupSelectWindow = new PopupWindow(popSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popRedPkgButton dismiss() ");
                popupSelectWindow = null;
            }
        });

        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.person_message_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popSelectView.startAnimation(animationCircleType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void update(ThirdPartyLoginResponse.DataBean dataBean) {
        if (dataBean == null) {
            return;
        } else {
            Presenter.getInstance(getContext()).getImage(userIcon, dataBean.getAvatar());
            useName.setText(dataBean.getNickname());
            if (dataBean.getSex() == 0) {
                //
                useGenderManSelect.setImageResource(R.drawable.selected_icon);
            } else if (dataBean.getSex() == 1) {
                //
                useGenderNvSelect.setImageResource(R.drawable.selected_icon);
            }
        }
    }

    public void setDataBen(ThirdPartyLoginResponse.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    public void response(UserInfoSetResponse userInfoSetResponse) {
        LocalLog.d(TAG, "UserInfoSetResponse() enter " + userInfoSetResponse.toString());
        if (userInfoSetResponse.getError() == 0) {

        }
    }
}
