package com.paobuqianjin.pbq.step.view.fragment.login;

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
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutUserInfoParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.WheelPicker;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.widgets.WheelDatePicker;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

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
    UserInfoResponse.DataBean dataBean;

    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    @Bind(R.id.btn_confirm)
    Button confirm;
    @Bind(R.id.person_message_fg)
    RelativeLayout personMessageFg;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
    private View popBirthSelectView;
    private View popWeighSelectView;
    private View popHighSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private boolean isPhoneNumLogin = false;
    private String birthYear = null;
    private String birthMonth = null;
    private String birthDay = null;
    private String high;
    private String weight;
    private String userNameOldStr;
    private int userid = -1;
    ArrayList<String> weightList = new ArrayList<>();
    //50-250cm
    ArrayList<String> heightList = new ArrayList<>();
    PutUserInfoParam putUserInfoParam;
    private boolean[] selectSex = new boolean[2];
    private ImageView[] selectIcon = new ImageView[2];
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private QServiceCfg qServiceCfg;
    private String cachePath;
    private final int REQUEST_CODE = 111;
    private String localAvatar;

    @Override
    protected int getLayoutResId() {
        return R.layout.person_message_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private int getSelect() {
        for (int i = 0; i < selectSex.length; i++) {
            if (selectSex[i]) {
                return i + 1;
            }
        }
        LocalLog.d(TAG, "error:没有选择");
        return 0;
    }

    private void UpdateUnSelect(int i) {
        for (int j = 0; j < selectSex.length; j++) {
            if (j != i) {
                if (selectSex[j] = true) {
                    selectSex[j] = false;
                    selectIcon[j].setImageDrawable(null);
                }
            }
        }
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        userIcon = (CircleImageView) viewRoot.findViewById(R.id.user_icon);
        useName = (EditText) viewRoot.findViewById(R.id.use_name);
        useGenderManSelect = (ImageView) viewRoot.findViewById(R.id.use_gender_man_select);
        useGenderNvSelect = (ImageView) viewRoot.findViewById(R.id.use_gender_nv_select);
        selectIcon[0] = useGenderManSelect;
        selectIcon[1] = useGenderNvSelect;
        birthDaySpan = (RelativeLayout) viewRoot.findViewById(R.id.birth_day_span);
        buttonReturnBar = (RelativeLayout) viewRoot.findViewById(R.id.button_return_bar);
        settingBirth = (TextView) viewRoot.findViewById(R.id.setting_birth);
        settingHeight = (TextView) viewRoot.findViewById(R.id.setting_height);
        settingWeight = (TextView) viewRoot.findViewById(R.id.setting_weight);
        buttonReturnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (USER_FIT_ACTION_SETTING.equals(intent.getAction())) {
                dataBean = (UserInfoResponse.DataBean) intent.getSerializableExtra("userinfo");
                if (dataBean != null) {
                    String paramStr = new Gson().toJson(dataBean);
                    putUserInfoParam = new Gson().fromJson(paramStr, PutUserInfoParam.class);
                    LocalLog.d(TAG, "" + putUserInfoParam.toString() + ", " + putUserInfoParam.paramString());
                    userid = dataBean.getId();
                    update(dataBean);
                }
            }
        } else {
            //
        }
        initData();
        qServiceCfg = QServiceCfg.instance(getContext());
    }

    private void initData() {
        int high = 50;
        while (high < 250) {
            heightList.add(String.valueOf(high));
            high += 5;
        }

        float weight = 10.0f;
        while (weight < 250.0f) {
            weightList.add(String.valueOf(weight));
            weight += 0.5;
        }
        cachePath = getContext().getExternalCacheDir().getAbsolutePath();
    }


    private void selectPicture() {
        popupCircleTypeView = View.inflate(getContext(), R.layout.select_camera_pic, null);
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
                        .start(PersonInfoSettingFragment.this, REQUEST_CODE);
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
                        .start(PersonInfoSettingFragment.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.person_message_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    private void setBirthDay() {
        LocalLog.d(TAG, "setBirthDay() 生日选择框");
        popBirthSelectView = View.inflate(getContext(), R.layout.wheel_select_layout, null);
        popupSelectWindow = new PopupWindow(popBirthSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popRedPkgButton dismiss() ");
                popupSelectWindow = null;
            }
        });
        Button confirmBt = (Button) popBirthSelectView.findViewById(R.id.btn_confirm);
        Button cancelBt = (Button) popBirthSelectView.findViewById(R.id.cancel);
        final WheelDatePicker wheelDatePicker = (WheelDatePicker) popBirthSelectView.findViewById(R.id.date_picker);
        wheelDatePicker.setOnDateSelectedListener(new WheelDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(WheelDatePicker picker, Date date) {
                LocalLog.d(TAG, "Date = " + date);
                if (DateTimeUtil.gainCurrentDate().before(date)) {
                    LocalLog.d(TAG, "Uneffect date");
                    ToastUtils.showShortToast(getContext(), "请选择正确的生日");
                    return;
                }
                birthYear = DateTimeUtil.formatDateTime(date, "yyyy");
                birthMonth = DateTimeUtil.formatDateTime(date, "MM");
                birthDay = DateTimeUtil.formatDateTime(date, "dd");
            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                if (birthDay != null && birthMonth != null && birthYear != null) {
                    String newBorth = birthYear + "年" + birthMonth + "月" + birthDay + "日";
                    if (!newBorth.equals(settingBirth.getText().toString())) {
                        settingBirth.setText(newBorth);
                        putUserInfoParam.setBirthyear(birthYear).setBirthmonth(birthMonth).setBirthday(birthDay);
                    }

                }
                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消");
                birthYear = null;
                birthMonth = null;
                birthDay = null;
                //TODO  使用默认的生日
                popupSelectWindow.dismiss();
            }
        });
        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);
        popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.person_message_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popBirthSelectView.startAnimation(animationCircleType);
    }

    public void setWeight() {
        LocalLog.d(TAG, "setWeight() 选择框");
        popWeighSelectView = View.inflate(getContext(), R.layout.wheel_weight_select_layout, null);
        popupSelectWindow = new PopupWindow(popWeighSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupSelectWindow dismiss() ");
                popupSelectWindow = null;
            }
        });

        Button confirmBt = (Button) popWeighSelectView.findViewById(R.id.btn_confirm);
        Button cancelBt = (Button) popWeighSelectView.findViewById(R.id.cancel);
        final WheelPicker wheelWeigthPicker = (WheelPicker) popWeighSelectView.findViewById(R.id.weigth_picker);
        wheelWeigthPicker.setData(weightList);
        wheelWeigthPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                weight = (String) data;
            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                if (weight != null) {
                    if (!weight.equals(settingWeight.getText().toString())) {
                        settingWeight.setText(weight + "公斤");
                        putUserInfoParam.setWeight(weight);
                    }
                }
                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消");
                popupSelectWindow.dismiss();
            }
        });


        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);
        popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.person_message_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popWeighSelectView.startAnimation(animationCircleType);
    }

    public void setHigh() {
        LocalLog.d(TAG, "setWeight() 选择框");
        popHighSelectView = View.inflate(getContext(), R.layout.wheel_high_select_layout, null);
        popupSelectWindow = new PopupWindow(popHighSelectView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupSelectWindow dismiss() ");
                popupSelectWindow = null;
            }
        });

        Button confirmBt = (Button) popHighSelectView.findViewById(R.id.btn_confirm);
        Button cancelBt = (Button) popHighSelectView.findViewById(R.id.cancel);
        final WheelPicker wheelHighPicker = (WheelPicker) popHighSelectView.findViewById(R.id.high_picker);
        wheelHighPicker.setData(heightList);
        wheelHighPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                LocalLog.d(TAG, (String) data);
                high = (String) data;
            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                if (high != null) {
                    if (!high.equals(settingHeight.getText().toString())) {
                        settingHeight.setText(high + "厘米");
                        putUserInfoParam.setHeight(high);
                    }
                }
                popupSelectWindow.dismiss();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消");
                popupSelectWindow.dismiss();
            }
        });

        popupSelectWindow.setFocusable(true);
        popupSelectWindow.setOutsideTouchable(true);
        popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.person_message_fg), Gravity.CENTER_HORIZONTAL, 0, 0);
        popHighSelectView.startAnimation(animationCircleType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    private void update(UserInfoResponse.DataBean dataBean) {
        if (dataBean == null) {
            return;
        } else {
            if (userIcon == null) {
                LocalLog.d(TAG, "UI not live");
                return;
            }

            birthYear = putUserInfoParam.getBirthyear();
            birthMonth = putUserInfoParam.getBirthmonth();
            birthDay = putUserInfoParam.getBirthday();
            if (birthYear == null && birthMonth == null && birthYear == null) {
                putUserInfoParam.setBirthyear("1900").setBirthday("01").setBirthmonth("01");
            } else {
                settingBirth.setText(birthYear + "年" + birthMonth + "月" + birthDay + "日");
            }

            weight = putUserInfoParam.getWeight();
            if (weight == null) {
                putUserInfoParam.setWeight("10.0");
            } else {
                settingWeight.setText(weight + "公斤");
            }

            high = putUserInfoParam.getHeight();
            if (high == null) {
                putUserInfoParam.setHeight("50");
            } else {
                settingHeight.setText(high + "厘米");
            }
            Presenter.getInstance(getContext()).getPlaceErrorImage(userIcon, dataBean.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            userNameOldStr = dataBean.getNickname();
            useName.requestFocus();
            useName.setText(dataBean.getNickname());
            useName.setSelection(useName.getText().toString().trim().length());
            useName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    useName.setSelection(useName.getText().toString().trim().length());
                    putUserInfoParam.setNickname(useName.getText().toString());
                }
            });
            if (dataBean.getSex() == 1) {
                //
                useGenderManSelect.setImageResource(R.drawable.selected_icon);
                selectSex[0] = true;
                selectSex[1] = false;
            } else if (dataBean.getSex() == 2) {
                //
                useGenderNvSelect.setImageResource(R.drawable.selected_icon);
                selectSex[0] = false;
                selectSex[1] = true;
            }
        }
    }


    @Override
    public void response(UserInfoSetResponse userInfoSetResponse) {
        LocalLog.d(TAG, "UserInfoSetResponse() enter " + userInfoSetResponse.toString());
        if (userInfoSetResponse.getError() == 0) {
            if (isAdded() && getActivity() != null) {
                ToastUtils.showLongToast(getContext(), "资料填写成功");
                getActivity().onBackPressed();
            }
        } else if (userInfoSetResponse.getError() == -100) {
            exitTokenUnfect();
        } else {
            ToastUtils.showLongToast(getContext(), userInfoSetResponse.getMessage());
            getActivity().onBackPressed();
        }
    }


    protected void toast(@StringRes int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    @OnClick({R.id.user_icon, R.id.use_gender_man_select, R.id.use_gender_nv_select, R.id.birth_day_span, R.id.height_span, R.id.weight_span,
            R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                LocalLog.d(TAG, "更换头像");
                requestPermission(Permission.Group.STORAGE);
                break;
            case R.id.use_gender_man_select:
                LocalLog.d(TAG, "点击男");
                if (selectSex[0]) {
                    LocalLog.d(TAG, "取消微信支付");
                    selectSex[0] = false;
                    selectIcon[0].setImageDrawable(null);
                } else {
                    LocalLog.d(TAG, "选择微信,设置其他为未选中状态");
                    UpdateUnSelect(0);
                    selectIcon[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.selected_icon));
                    selectSex[0] = true;
                }
                putUserInfoParam.setSex(getSelect());
                break;
            case R.id.use_gender_nv_select:
                LocalLog.d(TAG, "点击女");
                if (selectSex[1]) {
                    selectSex[1] = false;
                    selectIcon[1].setImageDrawable(null);

                } else {
                    UpdateUnSelect(1);
                    selectIcon[1].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.selected_icon));
                    selectSex[1] = true;
                }
                putUserInfoParam.setSex(getSelect());
                break;
            case R.id.birth_day_span:
                LocalLog.d(TAG, "设置生日");
                setBirthDay();
                break;
            case R.id.height_span:
                LocalLog.d(TAG, "设置身高");
                setHigh();
                break;
            case R.id.weight_span:
                LocalLog.d(TAG, "设置体重");
                setWeight();
                break;
            case R.id.btn_confirm:
                LocalLog.d(TAG, "修改确认");
                if (userid != -1) {
                    if (useName.getText().toString() == null || "".equals(useName.getText().toString())) {
                        Toast.makeText(getContext(), "请填写昵称", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (getSelect() == 0) {
                        Toast.makeText(getContext(), "请选择性别", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (localAvatar != null) {
                        LogoUpTask logoUpTask = new LogoUpTask();
                        logoUpTask.execute(localAvatar);
                    } else {
                        if (TextUtils.isEmpty(putUserInfoParam.paramString())) {
                            ToastUtils.showLongToast(getContext(), "没有修改");
                            return;
                        }
                        Presenter.getInstance(getContext()).putUserInfo(userid, putUserInfoParam);
                    }
                }

                break;
        }
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
                result = putObjectSample.start(path,getActivity().getApplicationContext());
                //LocalLog.d(TAG, "result = " + result.cosXmlResult.printError());
                if (result != null && result.cosXmlResult != null) {
                    url = result.cosXmlResult.accessUrl;
                }
                LocalLog.d(TAG, "url = " + url);

            }
            return url;
        }

        @Override
        protected void onPostExecute(String s) {
            LocalLog.d(TAG, "onPostExecute() enter");
            super.onPostExecute(s);
            if (putUserInfoParam != null) {
                putUserInfoParam.setAvatar(s);
            }
            Presenter.getInstance(getContext()).putUserInfo(userid, putUserInfoParam);
            //SocializeUtils.safeCloseDialog(dialog);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            String content = "";
            for (ImageBean imageBean : resultList) {
                content = content + imageBean.toString() + "\n";
            }
            LocalLog.d(TAG, "content = " + content);
            if (resultList != null && resultList.size() == 1) {
                Presenter.getInstance(getContext()).getImage(resultList.get(0).getImagePath(), userIcon, resultList.get(0).getWidth() / 4
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
                return getDataColumn(getContext(), contentUri, null, null);
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
                return getDataColumn(getContext(), contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
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
            return getDataColumn(getContext(), uri, null, null);
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
        String path = getContext().getExternalCacheDir() + "/head_logo.png";
        LocalLog.d(TAG, "path = " + path);
        FileOutputStream fos = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            ToastUtils.showLongToast(getContext(), errorCode.getMessage());
        }
    }
}
