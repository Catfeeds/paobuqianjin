package com.paobuqianjin.pbq.step.view.fragment.login;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.Display;
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

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutUserInfoParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;
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
    LoginResponse.DataBean phoneLoginDataBean;
    SignUserResponse.DataBean signUserDataBean;
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    @Bind(R.id.confirm)
    Button confirm;
    @Bind(R.id.person_message_fg)
    RelativeLayout personMessageFg;
    private View popBirthSelectView;
    private View popWeighSelectView;
    private View popHighSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;
    private boolean isPhoneNumLogin = false;
    private String birthYear = null;
    private String birthMonth = null;
    private String birthDay = null;
    private String high;
    private String weight;
    private int userid = -1;
    ArrayList<String> weightList = new ArrayList<>();
    //50-250cm
    ArrayList<String> heightList = new ArrayList<>();
    PutUserInfoParam putUserInfoParam = new PutUserInfoParam();
    private final static String LOGIN_SUCCESS_ACTION = "com.paobuqianjin.pbq.LOGIN_SUCCESS_ACTION";
    private boolean[] selectSex = new boolean[2];
    private ImageView[] selectIcon = new ImageView[2];
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private final static int CAMERA_PIC = 0;
    private QServiceCfg qServiceCfg;

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
                return i;
            }
        }
        LocalLog.d(TAG, "error:没有选择");
        return -1;
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
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (USER_FIT_ACTION_SETTING.equals(intent.getAction())) {
                LocalLog.d(TAG, "手机号登入第一次设置");
                signUserDataBean = (SignUserResponse.DataBean) intent.getSerializableExtra("signinfo");
                if (signUserDataBean != null) {
                    userid = signUserDataBean.getUserid();
                }
                phoneLoginDataBean = (LoginResponse.DataBean) intent.getSerializableExtra("userinfo");
                if (phoneLoginDataBean != null) {
                    userid = phoneLoginDataBean.getId();
                }
                isPhoneNumLogin = true;
            }
        } else {
            update(dataBean);
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
        Button confirmBt = (Button) popBirthSelectView.findViewById(R.id.confirm);
        Button cancelBt = (Button) popBirthSelectView.findViewById(R.id.cancel);
        final WheelDatePicker wheelDatePicker = (WheelDatePicker) popBirthSelectView.findViewById(R.id.date_picker);
        wheelDatePicker.setOnDateSelectedListener(new WheelDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(WheelDatePicker picker, Date date) {
                birthYear = DateTimeUtil.formatDateTime(date, "yyyy");
                birthMonth = DateTimeUtil.formatDateTime(date, "MM");
                birthDay = DateTimeUtil.formatDateTime(date, "dd");
            }
        });
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "确认");
                settingBirth.setText(birthYear + "年" + birthMonth + "月" + birthDay + "日");
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

        Button confirmBt = (Button) popWeighSelectView.findViewById(R.id.confirm);
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
                settingWeight.setText(weight + "公斤");
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

        Button confirmBt = (Button) popHighSelectView.findViewById(R.id.confirm);
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
                settingHeight.setText(high + "厘米");
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

    private void update(ThirdPartyLoginResponse.DataBean dataBean) {
        if (dataBean == null) {
            return;
        } else {
            if (userIcon == null) {
                LocalLog.d(TAG, "UI not live");
                return;
            }
            Presenter.getInstance(getContext()).getImage(userIcon, dataBean.getAvatar());
            useName.setText(dataBean.getNickname());
            if (dataBean.getSex() == 0) {
                //
                useGenderManSelect.setImageResource(R.drawable.selected_icon);
                selectSex[0] = true;
            } else if (dataBean.getSex() == 1) {
                //
                useGenderNvSelect.setImageResource(R.drawable.selected_icon);
                selectSex[0] = false;
            }
        }
    }

    public void setDataBen(ThirdPartyLoginResponse.DataBean dataBean) {
        this.dataBean = dataBean;
        if (dataBean != null) {
            userid = dataBean.getId();
            if (userid != -1) {
                update(dataBean);
            }
        }
    }

    @Override
    public void response(UserInfoSetResponse userInfoSetResponse) {
        LocalLog.d(TAG, "UserInfoSetResponse() enter " + userInfoSetResponse.toString());
        if (userInfoSetResponse.getError() == 0) {
            if (phoneLoginDataBean != null) {
                LocalLog.d(TAG, "手机号登陆成功");
                Presenter.getInstance(getContext()).steLogFlg(true);
                Presenter.getInstance(getContext()).setId(phoneLoginDataBean.getId());
                Presenter.getInstance(getContext()).setToken(getContext(), phoneLoginDataBean.getUser_token());
                Presenter.getInstance(getContext()).setMobile(getContext(), phoneLoginDataBean.getMobile());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }
            if (dataBean != null) {
                LocalLog.d(TAG, "三方登录成功");
                Presenter.getInstance(getContext()).steLogFlg(true);
                Presenter.getInstance(getContext()).setId(dataBean.getId());
                Presenter.getInstance(getContext()).setMobile(getContext(), dataBean.getMobile());
                Presenter.getInstance(getContext()).setToken(getContext(), phoneLoginDataBean.getUser_token());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }
            if (signUserDataBean != null) {
                LocalLog.d(TAG, "立即登登陆");
                Presenter.getInstance(getContext()).steLogFlg(true);
                Presenter.getInstance(getContext()).setId(signUserDataBean.getUserid());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }

        } else if (userInfoSetResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
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
                        toast(R.string.successfully);
                        LocalLog.d(TAG, "获取权限成功");
                        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picture, CAMERA_PIC);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                toast(R.string.failure);
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    @OnClick({R.id.user_icon, R.id.use_gender_man_select, R.id.use_gender_nv_select, R.id.birth_day_span, R.id.height_span, R.id.weight_span,
            R.id.confirm})
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
            case R.id.confirm:
                LocalLog.d(TAG, "修改确认");
                if (userid != -1) {
                    if (useName.getText().toString() == null || "".equals(useName.getText().toString())) {
                        Toast.makeText(getContext(), "请填写昵称", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (getSelect() == -1) {
                        Toast.makeText(getContext(), "请选择性别", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    putUserInfoParam.setNickname(useName.getText().toString());
                    if (birthYear != null) {
                        putUserInfoParam.setBirthyear(birthYear);
                    }
                    if (birthMonth != null) {
                        putUserInfoParam.setBirthmonth(birthMonth);
                    }

                    if (birthDay != null) {
                        putUserInfoParam.setBirthday(birthDay);
                    }
                    if (weight != null) {
                        putUserInfoParam.setWeight(weight);
                    }
                    if (high != null) {
                        putUserInfoParam.setHeight(high);
                    }
                    putUserInfoParam.setSex(getSelect());
                    if (signUserDataBean != null) {
                        Presenter.getInstance(getContext()).setToken(getContext(), signUserDataBean.getUser_token());
                    }
                    Presenter.getInstance(getContext()).putUserInfo(userid, putUserInfoParam);
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC) {
            LocalLog.d(TAG, "PICTURE OK");
            if (data != null) {

                //TODO 线程中上传保存*/
                Uri selectedImage = data.getData();

                final String pathResult = getPath(selectedImage);
                LocalLog.d(TAG, "pathResult = " + pathResult);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Bitmap docodeFile = BitmapFactory.decodeFile(pathResult, options);


                // 获取到这个图片的原始宽度和高度
                int picWidth = options.outWidth;
                int picHeight = options.outHeight;
                LocalLog.d(TAG, "options.outWidth = " + options.outWidth + "options.outHeight = " + options.outHeight);

                // 获取屏的宽度和高度
                WindowManager windowManager = getActivity().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                int screenWidth = display.getWidth();
                int screenHeight = display.getHeight();

                LocalLog.d(TAG, "screenWidth =  " + screenWidth + ",screenHeight = " + screenHeight);
                // isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
                options.inSampleSize = 1;
                // 根据屏的大小和图片大小计算出缩放比例
                if (picWidth > picHeight) {
                    if (picWidth > screenWidth)
                        options.inSampleSize = picWidth / screenWidth;
                } else {
                    if (picHeight > screenHeight)
                        options.inSampleSize = picHeight / screenHeight;
                }

                // 这次再真正地生成一个有像素的，经过缩放了的bitmap
                options.inJustDecodeBounds = false;
                //docodeFile = BitmapFactory.decodeFile(pathResult, options);
                Presenter.getInstance(getContext()).getImage(pathResult, userIcon);
                //logoCirclePic.setImageBitmap(docodeFile);
                LogoUpTask logoUpTask = new LogoUpTask();
                logoUpTask.execute(pathResult);
            }
/*        if (requestCode == REQUEST_CODE_TAG) {
                if (data != null) {
                    ArrayList<String> tags = data.getStringArrayListExtra("tag");
                    if (tags != null) {
                        if (tags.size() == 2) {
                            flagA1.setText(tags.get(0));
                            flagA0.setText(tags.get(1));
                            flagA1.setVisibility(View.VISIBLE);
                            flagA0.setVisibility(View.VISIBLE);
                        } else if (tags.size() == 1) {
                            flagA1.setText(tags.get(0));
                            flagA1.setVisibility(View.VISIBLE);
                        }
                    }
                    ArrayList<String> ids = data.getStringArrayListExtra("id");
                    if (ids != null) {
                        if (ids.size() == 2) {
                            LocalLog.d(TAG, ids.get(0));
                            LocalLog.d(TAG, ids.get(1));
                            createCircleBodyParam.setTags(ids.get(0) + "," + ids.get(1));
                        } else if (ids.size() == 1) {
                            LocalLog.d(TAG, ids.get(0));
                            createCircleBodyParam.setTags(ids.get(0));
                        }
                    }

                }
        }*/
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
            if (putUserInfoParam != null) {
                putUserInfoParam.setAvatar(s);
            }
            //SocializeUtils.safeCloseDialog(dialog);
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
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
