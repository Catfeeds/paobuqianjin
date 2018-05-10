package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.LimitLengthFilter;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.SoftKeyboardStateHelper;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SelectSettingAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    /*    @Bind(R.id.circle_style_text)
        TextView circleStyleText;*/
/*    @Bind(R.id.cir_cle_style)
    TextView cirCleStyle;*/
/*    @Bind(R.id.style_circle_pan)
    RelativeLayout styleCirclePan;*/
    @Bind(R.id.circle_name_text)
    TextView circleNameText;
    @Bind(R.id.cir_name_desc)
    EditText cirNameDesc;
    @Bind(R.id.name_circle_span)
    RelativeLayout nameCircleSpan;
    @Bind(R.id.circle_stand_text)
    TextView circleStandText;
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
    @Bind(R.id.logo_circle_pan)
    RelativeLayout logoCirclePan;
    @Bind(R.id.circle_pass_text_desc)
    TextView circlePassTextDesc;
    @Bind(R.id.pass_circle_span)
    RelativeLayout passCircleSpan;
    /*    @Bind(R.id.circle_theme_text)
        TextView circleThemeText;
        @Bind(R.id.circle_theme_pan)
        RelativeLayout circleThemePan;*/
    @Bind(R.id.circle_theme_phone_line)
    ImageView circleThemePhoneLine;
    @Bind(R.id.circle_desc_of_your)
    EditText circleDescOfYour;
    @Bind(R.id.bound_text)
    TextView boundText;
    @Bind(R.id.circle_theme_phone_line_2)
    ImageView circleThemePhoneLine2;
    @Bind(R.id.create_span)
    RelativeLayout createSpan;
    @Bind(R.id.container_create_circle)
    RelativeLayout containerCreateCircle;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;
    @Bind(R.id.create_circle_layout)
    RelativeLayout createCircleLayout;
    /*    @Bind(R.id.switch_style)
        ImageView switchStyle;*/
    @Bind(R.id.switch_stand)
    ImageView switchStand;
    @Bind(R.id.switch_circle_money_add_off)
    ImageView switchCircleMoneyAddOff;
    @Bind(R.id.logo_circle_pic)
    CircleImageView logoCirclePic;
    @Bind(R.id.password_circle_switch)
    ImageView passwordCircleSwitch;
    @Bind(R.id.create_circle_confim)
    Button createCircleConfim;
    @Bind(R.id.password_num_text)
    TextView passwordNumText;
    @Bind(R.id.password_num_editor)
    EditText passwordNumEditor;
    @Bind(R.id.password_pan)
    RelativeLayout passwordPan;
    /*    @Bind(R.id.flag_a0)
        TextView flagA0;
        @Bind(R.id.flag_a1)
        TextView flagA1;*/
    private ProgressDialog dialog;
    private Rationale mRationale;
    private PermissionSetting mSetting;

    private ArrayList<String> circleTypeList;
    private PopupWindow popupCircleTypeWindow;
    private View popupCircleTypeView;
    private TranslateAnimation animationCircleType;
    private Handler mHandler;
    private ArrayList<String> targetDefaults = new ArrayList<>();
    private boolean is_recharge = true;
    private boolean is_pwd = true;
    private String cachePath;
    private ArrayList<String> circleTypeDefaults = new ArrayList<>();
    private final static int REQUEST_CODE_TAG = 0;
    private CreateCircleBodyParam createCircleBodyParam = new CreateCircleBodyParam();
    private HashMap<String, String> selectA = new HashMap<>();
    private HashMap<String, String> selectB = new HashMap<>();
    private final static int CAMERA_PIC = 0;
    private QServiceCfg qServiceCfg;
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String QRCODE_ACTION = "android.intent.action.QRCODE";
    private final int REQUEST_CODE = 111;
    private String localAvatar;
    private LimitLengthFilter filter;

/*

    static {
        targetDefaults.add("6000");
        targetDefaults.add("7000");
        targetDefaults.add("8000");
        targetDefaults.add("9000");
        targetDefaults.add("10000");
    }

    static {
        circleTypeDefaults.add("个人圈子");
        circleTypeDefaults.add("企业圈子");
    }
*/

    @Override
    protected String title() {
        return "创建圈子";
    }

    @Override
    protected void onResume() {
        super.onResume();
        detectKeyBoardHide();
    }

    private void detectKeyBoardHide() {
        final SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(findViewById(R.id.create_circle_layout));
        softKeyboardStateHelper.addSoftKeyboardStateListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_circle_layout);
        ButterKnife.bind(this);
        Presenter.getInstance(this).attachUiInterface(uiCreateCircleInterface);
        //circleTypeList = intent.getStringArrayListExtra(getPackageName() + "circle_type");
        if (circleTypeList == null || circleTypeList.size() < 0) {
            //request
        } else {

        }
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
       /* Presenter.getInstance(this).getCirCleType();*/
        mHandler = new Handler(getMainLooper());
        Presenter.getInstance(this).getCircleTarget();
        qServiceCfg = QServiceCfg.instance(this);
        createCircleBodyParam.setIs_pwd(1);
        createCircleBodyParam.setIs_recharge(1);

        dialog = new ProgressDialog(this);

        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
        requestLocationPermission(Permission.Group.LOCATION);
        cachePath = getExternalCacheDir().getAbsolutePath();
        cirNameDesc.setSelection(cirNameDesc.getText().toString().length());
        circlePhoneNumEditor.setSelection(circlePhoneNumEditor.getText().toString().length());
        circleMoneyNumEditor.setSelection(circleMoneyNumEditor.getText().toString().length());
        circleMoneyNumEditor.addTextChangedListener(textWatcher);
        cirNameDesc.setSelection(cirNameDesc.getText().toString().length());
        circleReadPackageEditor.setSelection(circleReadPackageEditor.getText().toString().length());
        circleReadPackageEditor.addTextChangedListener(textWatcher);
        moneyPkgNumEditor.setSelection(moneyPkgNumEditor.getText().toString().length());
        passwordNumEditor.setSelection(passwordNumEditor.getText().toString().length());
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);


         filter = new LimitLengthFilter();
        cirNameDesc.setFilters(new InputFilter[]{filter});
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String temp = editable.toString();
            int posDot = temp.indexOf(".");
            if (posDot <= 0) return;
            if (temp.length() - posDot - 1 > 2) {
                editable.delete(posDot + 3, posDot + 4);
            }

        }
    };
/*    public void onStyleSelect(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.switch_style:
                    LocalLog.d(TAG, " 圈子类型选择");
                    selectType(circleTypeDefaults, cirCleStyle);
                    break;
                case R.id.switch_stand:
                    LocalLog.d(TAG, "设定目标距离");
                    selectType(targetDefaults, circleStandNum);
                    break;
            }
        }
    }*/


    private UiCreateCircleInterface uiCreateCircleInterface = new UiCreateCircleInterface() {
        @Override
        public void response(Object error) {

            ErrorCode errorCode = new Gson().fromJson(error.toString(), ErrorCode.class);
            Toast.makeText(CreateCircleActivity.this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                Presenter.getInstance(CreateCircleActivity.this).setId(-1);
                Presenter.getInstance(CreateCircleActivity.this).steLogFlg(false);
                Presenter.getInstance(CreateCircleActivity.this).setToken(CreateCircleActivity.this, "");
                CreateCircleActivity.this.finish();
                System.exit(0);
            }
        }

        @Override
        public void response(CircleTargetResponse targetResponse) {
            LocalLog.d(TAG, "CircleTargetResponse() enter");
            if (targetResponse != null) {
                int size = targetResponse.getData().size();
                LocalLog.d(TAG, "size = " + size);
                for (int i = 0; i < size; i++) {
                    selectB.put(String.valueOf(targetResponse.getData().get(i).getTarget()),
                            String.valueOf(targetResponse.getData().get(i).getId()));
                    targetDefaults.add(String.valueOf(targetResponse.getData().get(i).getTarget()));
                }
                if (size > 1) {
                    circleStandNum.setText(String.valueOf(targetResponse.getData().get(size - 1).getTarget()));
                    createCircleBodyParam.setTargetid(targetResponse.getData().get(size - 1).getId());
                }
            }
        }

        @Override
        public void response(CircleTagResponse circleTagResponse) {
            LocalLog.d(TAG, "CircleTagResponse() enter");

        }

        @Override
        public void response(CircleTypeResponse circleTypeResponse) {
            LocalLog.d(TAG, "CircleTypeResponse() enter");
            if (circleTypeResponse != null) {
                int size = circleTypeResponse.getData().size();
                LocalLog.d(TAG, "size = " + size);
                for (int i = 0; i < size; i++) {
                    selectA.put(circleTypeResponse.getData().get(i).getName(),
                            String.valueOf(circleTypeResponse.getData().get(i).getId()));
                    circleTypeDefaults.add(circleTypeResponse.getData().get(i).getName());
                }
                /*cirCleStyle.setText(circleTypeResponse.getData().get(0).getName());*/
            }

        }

        @Override
        public void responseLocation(String city, double latitude, double longitude) {
            createCircleBodyParam.setCity(city);
            createCircleBodyParam.setLongitude((float) longitude);
            createCircleBodyParam.setLatitude((float) latitude);
        }

        @Override
        public void response(CreateCircleResponse createCircleResponses) {
            LocalLog.d(TAG, "CreateCircleResponse() enter " + createCircleResponses.toString());
            Toast.makeText(CreateCircleActivity.this, createCircleResponses.getMessage(), Toast.LENGTH_SHORT).show();
            //finish();
            if (createCircleResponses.getError() == 0) {
                Bundle bundle = new Bundle();
                bundle.putString(CIRCLE_ID, createCircleResponses.getData().getId());
                bundle.putString(CIRCLE_NAME, createCircleResponses.getData().getName());
                bundle.putString(CIRCLE_LOGO, createCircleResponses.getData().getLogo());
                if (is_recharge) {
                    LocalLog.d(TAG, "创建成功,跳转支付");
                    bundle.putString(PAY_FOR_STYLE, "circle");
                    bundle.putString(CIRCLE_RECHARGE, circleMoneyNumEditor.getText().toString());
                    startActivity(PaoBuPayActivity.class, bundle, true, PAY_ACTION);
                } else {
                    startActivity(PaoBuPayActivity.class, bundle, true, QRCODE_ACTION);
                }
            }
        }
    };

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        LocalLog.d(TAG, "onSoftKeyboardOpened() 键盘弹出高度 ：" + keyboardHeightInPx);
        if (circleDescOfYour.hasFocus()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "键盘弹出滚动到底部!");
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utils.showSoftInputFromWindow(CreateCircleActivity.this, circleDescOfYour);
                        }
                    }, 500);
                }
            });
        }else if(passwordNumEditor.hasFocus()){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "键盘弹出滚动到底部!");
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Utils.showSoftInputFromWindow(CreateCircleActivity.this, passwordNumEditor);
                        }
                    });
                }
            });
        }

    }

    //返回值就是状态栏的高度,得到的值是像素'
    public float getStatusBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimension(resourceId);
        }
        return result;
    }

    //返回值就是导航栏的高度,得到的值是像素
    public float getNavigationBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimension(resourceId);
        }
        return result;
    }

    public void selectType(ArrayList<String> strings, final TextView desView) {
        final SelectSettingAdapter selectSettingAdapter = new SelectSettingAdapter(this, strings);

        popupCircleTypeView = View.inflate(this, R.layout.select_dialog_layout, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        popupCircleTypeWindow.setFocusable(true);

        popupCircleTypeWindow.setOutsideTouchable(true);
        popupCircleTypeWindow.setBackgroundDrawable(new BitmapDrawable());

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
                if (popupCircleTypeWindow.isShowing()) {
                    popupCircleTypeWindow.dismiss();
                    popupCircleTypeWindow = null;
                }
            }
        });

        popupCircleTypeView.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "onClick() 确定");
                String selectString = selectSettingAdapter.getSelectContent();
                desView.setText(selectString);
           /*     if (desView == cirCleStyle) {
                    createCircleBodyParam.setTaskid(Integer.parseInt(selectA.get(selectString)));
                } else */
                if (desView == circleStandNum) {
                    createCircleBodyParam.setTargetid(Integer.parseInt(selectB.get(selectString)));
                }
                LocalLog.d(TAG, "you choice is: " + selectString);

                if (popupCircleTypeWindow.isShowing()) {
                    popupCircleTypeWindow.dismiss();
                    popupCircleTypeWindow = null;
                }

            }
        });
        final RecyclerView recyclerView = (RecyclerView) popupCircleTypeView.findViewById(R.id.select_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(selectSettingAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                LocalLog.d(TAG, "OnItemClick() enter " + position);
                int lastSelectPosition = selectSettingAdapter.getSelectPosition();
                selectSettingAdapter.setSelectPosition(position);
                recyclerView.getAdapter().notifyItemChanged(position);
                recyclerView.getAdapter().notifyItemChanged(lastSelectPosition);
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                LocalLog.d(TAG, "OnItemLongClick() enter " + position);
            }
        }));

        popupCircleTypeWindow.showAtLocation(CreateCircleActivity.this.findViewById(R.id.create_circle_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(uiCreateCircleInterface);
    }

    @Override
    public void onSoftKeyboardClosed() {
        LocalLog.d(TAG, "键盘关闭");
    }

    public void disableMoneyEdit() {
        moneyPkgPan.setVisibility(View.GONE);
        readPackageMumPan.setVisibility(View.GONE);
        moneyMumPan.setVisibility(View.GONE);
    }

    public void enableMoneyEdit() {
        moneyPkgPan.setVisibility(View.VISIBLE);
        readPackageMumPan.setVisibility(View.VISIBLE);
        moneyMumPan.setVisibility(View.VISIBLE);
    }

    public void disablePassEdit() {
        passwordPan.setVisibility(View.GONE);
    }

    public void enablePassEdit() {
        passwordPan.setVisibility(View.VISIBLE);
    }

    @TargetApi(19)
    @OnClick({/*R.id.switch_style,*/
            R.id.logo_circle_pan,
            R.id.stand_circle_pan, R.id.switch_circle_money_add_off, R.id.logo_circle_pic, R.id.password_circle_switch,
            R.id.create_circle_confim,
            /*R.id.circle_theme_text*/})
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.switch_style:
                LocalLog.d(TAG, " 圈子类型选择");
                selectType(circleTypeDefaults, cirCleStyle);
                break;*/
            case R.id.stand_circle_pan:
                LocalLog.d(TAG, "设定目标距离");
                hideSoftInputView();
                selectType(targetDefaults, circleStandNum);
                break;
            case R.id.switch_circle_money_add_off:
                LocalLog.d(TAG, "是否充值!" + is_recharge);
                if (!is_recharge) {
                    switchCircleMoneyAddOff.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.switch_bar_a));
                    is_recharge = true;
                    enableMoneyEdit();
                    createCircleBodyParam.setIs_recharge(1);
                } else {
                    switchCircleMoneyAddOff.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.switch_bar_a_pass));
                    is_recharge = false;
                    disableMoneyEdit();
                    createCircleBodyParam.setIs_recharge(0);
                }
                break;
            case R.id.logo_circle_pic:
                break;
            case R.id.password_circle_switch:
                LocalLog.d(TAG, "是否有密码!" + is_pwd);
                if (!is_pwd) {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.switch_bar_a));
                    is_pwd = true;
                    enablePassEdit();
                    createCircleBodyParam.setIs_pwd(1);
                } else {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.switch_bar_a_pass));
                    is_pwd = false;
                    disablePassEdit();
                    createCircleBodyParam.setIs_pwd(0);
                }
                break;
            case R.id.create_circle_confim:
                LocalLog.d(TAG, "创建圈子");
                if (!checkcreateCircleBodyParam()) {
                    return;
                }
                if (TextUtils.isEmpty(localAvatar)) {
                    Toast.makeText(this, "请选择圈子头像", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (localAvatar != null) {
                    LogoUpTask logoUpTask = new LogoUpTask();
                    logoUpTask.execute(localAvatar);
                }
                break;
            case R.id.logo_circle_pan:
                LocalLog.d(TAG, "上传圈子logo");
                requestPermission(Permission.Group.STORAGE);
                break;
           /* case R.id.circle_theme_text:
                LocalLog.d(TAG, "添加标签!");
                Intent intent = new Intent();
                intent.setClass(this, CirCleTagActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TAG);
                break;*/
            default:
                break;
        }
    }


    /*权限适配*/

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        LocalLog.d(TAG, "获取权限成功");
                        selectPicture();
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(CreateCircleActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }


    private void selectPicture() {
        popupCircleTypeView = View.inflate(this, R.layout.select_camera_pic, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        popupCircleTypeWindow.setFocusable(true);
        popupCircleTypeWindow.setOutsideTouchable(true);
        popupCircleTypeWindow.setBackgroundDrawable(new BitmapDrawable());
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.select_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相机");
                new ImagePicker()
                        .pickType(ImagePickType.ONLY_CAMERA)//设置选取类型(拍照、单选、多选)
                        .maxNum(1)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .doCrop(1, 1, 0, 0)//裁剪功能需要调用这个方法，多选模式下无效
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(CreateCircleActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        ((RelativeLayout) popupCircleTypeView.findViewById(R.id.xiangche_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "相册");
                new ImagePicker()
                        .pickType(ImagePickType.MULTI)//设置选取类型(拍照、单选、多选)
                        .maxNum(1)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                        .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                        .cachePath(cachePath)//自定义缓存路径
                        .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                        .start(CreateCircleActivity.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(findViewById(R.id.create_circle_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }
        /*权限适配*/

    private void requestLocationPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        LocalLog.d(TAG, "发起定位");
                        Presenter.getInstance(CreateCircleActivity.this).startService(null, LocalBaiduService.class);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(CreateCircleActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    public boolean checkcreateCircleBodyParam() {
        createCircleBodyParam.setUserid(Presenter.getInstance(this).getId());
        //createCircleBodyParam.setCity("深圳福田");
        createCircleBodyParam.setName(cirNameDesc.getText().toString());
        if (cirNameDesc.getText() == null || cirNameDesc.getText().toString().equals("")) {
            Toast.makeText(this, "请输入圈子名称", Toast.LENGTH_SHORT).show();
            return false;
        }

        String targetTaskStepNumStr = cirNameDesc.getText().toString();
        if (TextUtils.isEmpty(targetTaskStepNumStr.trim()) || filter.calculateLength(targetTaskStepNumStr) < 4
                || filter.calculateLength(targetTaskStepNumStr) > 32) {
                final NormalDialog normalDialog = new NormalDialog(this);
                normalDialog.setMessage("请输入2-16位任务名称");
                normalDialog.setSingleBtn(true);
                normalDialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        normalDialog.dismiss();
                    }
                });
            normalDialog.show();
            return false;
        }
        createCircleBodyParam.setMobile(circlePhoneNumEditor.getText().toString());
        if (!Utils.isMobile(createCircleBodyParam.getMobile())) {

            Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (circlePhoneNumEditor.getText() == null || circlePhoneNumEditor.getText().toString().equals("")) {
            Toast.makeText(this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }

        LocalLog.d(TAG, "Is_recharge = " + createCircleBodyParam.isIs_recharge());
        if (createCircleBodyParam.isIs_recharge() == 0) {

        } else if (createCircleBodyParam.isIs_recharge() == 1) {
            if (moneyPkgNumEditor.getText().toString().equals("")
                    || circleReadPackageEditor.getText().toString().equals("")
                    || circleMoneyNumEditor.getText().toString().equals("")) {
                Toast.makeText(this, "请完善红包信息", Toast.LENGTH_SHORT).show();
                return false;
            } else {

            }
        }

        LocalLog.d(TAG, "Is_pwd = " + createCircleBodyParam.isIs_pwd());
        if ((createCircleBodyParam.isIs_pwd() == 1) && (passwordNumEditor.getText().toString().equals(""))) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (createCircleBodyParam.isIs_recharge() == 1) {
            if (moneyPkgNumEditor.getText().toString().startsWith("0")) {
                Toast.makeText(this, "红包个数非法", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (Float.parseFloat(circleReadPackageEditor.getText().toString()) >
                    Float.parseFloat(circleMoneyNumEditor.getText().toString())) {
                Toast.makeText(this, "充值金额不能小于每日红包金额", Toast.LENGTH_SHORT).show();
                return false;
            }
            createCircleBodyParam.setRed_packet(Integer.parseInt(moneyPkgNumEditor.getText().toString()));
            createCircleBodyParam.setRed_packet_amount(Float.parseFloat(circleReadPackageEditor.getText().toString()));
            createCircleBodyParam.setTotal_amount(Float.parseFloat(circleMoneyNumEditor.getText().toString()));

        }

        if (createCircleBodyParam.isIs_pwd() == 1) {
            createCircleBodyParam.setPassword(passwordNumEditor.getText().toString());
        }

        if (circleDescOfYour.getText() == null || circleDescOfYour.getText().toString().equals("")) {
            Toast.makeText(this, "圈子描述至少填写一个字符", Toast.LENGTH_SHORT).show();
        }
        createCircleBodyParam.setDescription(circleDescOfYour.getText().toString());
        createCircleBodyParam.setCoverid(1);
        return true;
    }

    public class LogoUpTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = null;
            for (String path : strings) {
                LocalLog.d(TAG, "path = " + path);
                ResultHelper result = null;
                PutObjectSample putObjectSample = new PutObjectSample(qServiceCfg);
                result = putObjectSample.start(path);
                //LocalLog.d(TAG, "result = " + result.cosXmlResult.printError());
                url = result.cosXmlResult.accessUrl;
                LocalLog.d(TAG, "url = " + url);

            }
            return url;
        }

        @Override
        protected void onPostExecute(String s) {
            LocalLog.d(TAG, "onPostExecute() enter");
            super.onPostExecute(s);
            if (s != null && !"".equals(s)) {
                createCircleBodyParam.setLogo(s);
            }
            if (checkcreateCircleBodyParam()) {
                Presenter.getInstance(CreateCircleActivity.this).createCircle(createCircleBodyParam);
            }
            //SocializeUtils.safeCloseDialog(dialog);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LocalLog.d(TAG, "onActivityResult() enter");
        LocalLog.d(TAG, "onActivityResult() enter");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            String content = "";
            for (ImageBean imageBean : resultList) {
                content = content + imageBean.toString() + "\n";
            }
            LocalLog.d(TAG, "content = " + content);
            if (resultList != null && resultList.size() == 1) {
                Presenter.getInstance(this).getImage(resultList.get(0).getImagePath(), logoCirclePic, resultList.get(0).getWidth() / 4
                        , resultList.get(0).getHeight() / 4);
                localAvatar = resultList.get(0).getImagePath();
            } else {
                LocalLog.d(TAG, "未知操作");
            }
            return;
        }

    }

    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) {
            LocalLog.d(TAG, "uri auth:" + uri.getAuthority());

            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("img".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("auto".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(this, contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            } else if (isXiaoMi(uri)) {
                LocalLog.d(TAG, "小米手机相册 enter()");
                LocalLog.d(TAG, uri.toString());
                if ("content".equalsIgnoreCase(uri.getScheme())) {
                    return uri.getLastPathSegment();
                }
                return uri.getPath();
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotos(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(this, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    public boolean isGooglePhotos(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public boolean isXiaoMi(Uri uri) {
        LocalLog.d(TAG, uri.getAuthority());
        return "com.miui.gallery.open".equals(uri.getAuthority());
    }

    private void saveImage(Bitmap bitmap, String sourcePath) throws FileNotFoundException {
        String path = this.getExternalCacheDir() + "/head_logo.png";
        LocalLog.d(TAG, "path = " + path);
        FileOutputStream fos = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

    }

}
