package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.SoftKeyboardStateHelper;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/14.
 */

public class CreateCircleActivity extends BaseBarActivity implements SoftKeyboardStateHelper.SoftKeyboardStateListener {
    private final static String TAG = CreateCircleActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.circle_style_text)
    TextView circleStyleText;
    @Bind(R.id.switch_style)
    ImageView switchStyle;
    @Bind(R.id.cir_cle_style)
    TextView cirCleStyle;
    @Bind(R.id.style_circle_pan)
    RelativeLayout styleCirclePan;
    @Bind(R.id.circle_name_text)
    TextView circleNameText;
    @Bind(R.id.cir_name_desc)
    EditText cirNameDesc;
    @Bind(R.id.name_circle_span)
    RelativeLayout nameCircleSpan;
    @Bind(R.id.circle_stand_text)
    TextView circleStandText;
    @Bind(R.id.switch_stand)
    ImageView switchStand;
    @Bind(R.id.circle_stand_num)
    TextView circleStandNum;
    @Bind(R.id.stand_circle_pan)
    RelativeLayout standCirclePan;
    @Bind(R.id.circle_owner_phone_line)
    ImageView circleOwnerPhoneLine;
    @Bind(R.id.phone_num_text)
    TextView phoneNumText;
    @Bind(R.id.circle_phone_num_editor)
    EditText circlePhoneNumEditor;
    @Bind(R.id.phone_circle_pan)
    RelativeLayout phoneCirclePan;
    @Bind(R.id.circle_money_start_text)
    TextView circleMoneyStartText;
    @Bind(R.id.switch_circle_money_add_off)
    ImageView switchCircleMoneyAddOff;
    @Bind(R.id.circle_start_money)
    RelativeLayout circleStartMoney;
    @Bind(R.id.money_num_text)
    TextView moneyNumText;
    @Bind(R.id.circle_money_num_editor)
    EditText circleMoneyNumEditor;
    @Bind(R.id.money_mum_pan)
    RelativeLayout moneyMumPan;
    @Bind(R.id.read_package_num_text)
    TextView readPackageNumText;
    @Bind(R.id.circle_read_package_editor)
    EditText circleReadPackageEditor;
    @Bind(R.id.read_package_mum_pan)
    RelativeLayout readPackageMumPan;
    @Bind(R.id.money_pkg_text)
    TextView moneyPkgText;
    @Bind(R.id.money_pkg_num_editor)
    EditText moneyPkgNumEditor;
    @Bind(R.id.money_pkg_pan)
    RelativeLayout moneyPkgPan;
    @Bind(R.id.money_use_desc_text)
    TextView moneyUseDescText;
    @Bind(R.id.money_use_desc)
    RelativeLayout moneyUseDesc;
    @Bind(R.id.circle_logo_text)
    TextView circleLogoText;
    @Bind(R.id.logo_circle_pic)
    ImageView logoCirclePic;
    @Bind(R.id.logo_circle_pan)
    RelativeLayout logoCirclePan;
    @Bind(R.id.circle_pass_text_desc)
    TextView circlePassTextDesc;
    @Bind(R.id.password_circle_switch)
    ImageView passwordCircleSwitch;
    @Bind(R.id.pass_circle_span)
    RelativeLayout passCircleSpan;
    @Bind(R.id.circle_theme_text)
    TextView circleThemeText;
    @Bind(R.id.circle_theme_pan)
    RelativeLayout circleThemePan;
    @Bind(R.id.circle_theme_phone_line)
    ImageView circleThemePhoneLine;
    @Bind(R.id.circle_desc_of_your)
    EditText circleDescOfYour;
    @Bind(R.id.bound_text)
    TextView boundText;
    @Bind(R.id.circle_theme_phone_line_2)
    ImageView circleThemePhoneLine2;
    @Bind(R.id.create_circle_confim)
    Button createCircleConfim;
    @Bind(R.id.create_span)
    RelativeLayout createSpan;
    @Bind(R.id.container_create_circle)
    RelativeLayout containerCreateCircle;

    private ArrayList<String> circleTypeList;
    CreateCircleBodyParam createCircleBodyParam = new CreateCircleBodyParam();
    private PopupWindow popupCircleTypeWindow;
    private View popupCircleTypeView;
    private TranslateAnimation animationCircleType;

    @Override
    protected String title() {
        return "创建圈子";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_circle_layout);
        ButterKnife.bind(this);
        initBarView();
        Intent intent = new Intent();

        circleTypeList = intent.getStringArrayListExtra(getPackageName() + "circle_type");
        if (circleTypeList == null || circleTypeList.size() < 0) {
            //request
        } else {

        }

    }

    public void onStyleSelect(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.switch_style:
                    List<String> strings = new LinkedList<String>();
                    strings.add("个人圈子");
                    strings.add("企业圈子");
                    break;
                case R.id.switch_stand:
                    LocalLog.d(TAG, "设定目标距离");
                    break;
            }
        }
    }


    private UiCreateCircleInterface uiCreateCircleInterface = new UiCreateCircleInterface() {
        @Override
        public void response(CircleTagResponse circleTagResponse) {

        }

        @Override
        public void response(CircleTypeResponse circleTypeResponse) {

        }

        @Override
        public void response(CircleTargetResponse circleTargetResponse) {

        }
    };

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        LocalLog.d(TAG, "onSoftKeyboardOpened() 键盘弹出高度 ：" + keyboardHeightInPx);

    }

    public void selectCircleType() {
        if (popupCircleTypeWindow == null) {
            popupCircleTypeView = View.inflate(this, R.layout.select_dialog_layout, null);
            popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            popupCircleTypeWindow.setBackgroundDrawable(new BitmapDrawable());
            popupCircleTypeWindow.setFocusable(true);

            popupCircleTypeWindow.setOutsideTouchable(true);

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                    , 0, Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);
            popupCircleTypeView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                    LocalLog.d(TAG, "onClick() 取消");
                }
            });

            popupCircleTypeView.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocalLog.d(TAG, "onClick() 确定");
                }
            });
            RecyclerView recyclerView = (RecyclerView) popupCircleTypeView.findViewById(R.id.select_recycler_view);
            recyclerView.setLayoutManager(linearLayoutManager);

        }

    }


    @Override
    public void onSoftKeyboardClosed() {
        LocalLog.d(TAG, "");
    }

    @OnClick(R.id.container_create_circle)
    public void onClick() {
    }
}
