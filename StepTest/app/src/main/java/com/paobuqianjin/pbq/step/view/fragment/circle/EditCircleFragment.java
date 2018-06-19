package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.EditCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.tencent.yun.ObjectSample.PutObjectSample;
import com.paobuqianjin.pbq.step.data.tencent.yun.activity.ResultHelper;
import com.paobuqianjin.pbq.step.data.tencent.yun.common.QServiceCfg;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.EditCircleInterface;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.SoftKeyboardStateHelper;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SelectSettingAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
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

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/3/28.
 */

public class EditCircleFragment extends BaseBarStyleTextViewFragment implements EditCircleInterface, SoftKeyboardStateHelper.SoftKeyboardStateListener {
    private final static String TAG = EditCircleFragment.class.getSimpleName();
    private static final String DEFAULT_EMPTY_PWD = "***/**";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
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
    CircleImageView logoCirclePic;
    @Bind(R.id.logo_circle_pan)
    RelativeLayout logoCirclePan;
    @Bind(R.id.circle_pass_text_desc)
    TextView circlePassTextDesc;
    @Bind(R.id.password_circle_switch)
    ImageView passwordCircleSwitch;
    @Bind(R.id.pass_circle_span)
    RelativeLayout passCircleSpan;
    @Bind(R.id.password_num_text)
    TextView passwordNumText;
    @Bind(R.id.password_num_editor)
    EditText passwordNumEditor;
    @Bind(R.id.password_pan)
    RelativeLayout passwordPan;
    @Bind(R.id.circle_theme_phone_line)
    ImageView circleThemePhoneLine;
    @Bind(R.id.circle_desc_of_your)
    EditText circleDescOfYour;
    @Bind(R.id.bound_text)
    TextView boundText;
    @Bind(R.id.edit_circle_desc)
    RelativeLayout editCircleDesc;
    @Bind(R.id.container_create_circle)
    RelativeLayout containerCreateCircle;
    @Bind(R.id.scroll_view)
    BounceScrollView scrollView;
    @Bind(R.id.circle_theme_phone_line_2)
    ImageView circleThemePhoneLine2;
    @Bind(R.id.edit_circle_confim)
    Button editCircleConfim;
    @Bind(R.id.create_span)
    RelativeLayout createSpan;
    CircleDetailResponse.DataBean dataBean;
    @Bind(R.id.edit_circle)
    RelativeLayout editCircle;
    private boolean is_recharge = false;
    private boolean is_pwd = false;
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private QServiceCfg qServiceCfg;
    private PopupWindow popupCircleTypeWindow;
    private View popupCircleTypeView;
    private TranslateAnimation animationCircleType;
    private ArrayList<String> targetDefaults = new ArrayList<>();
    private final static int CAMERA_PIC = 0;
    private HashMap<String, String> selectB = new HashMap<>();
    private CreateCircleBodyParam createCircleBodyParam = new CreateCircleBodyParam();
    private Handler mHandler;
    private String cachePath;
    private final int REQUEST_CODE = 111;
    private String localAvatar;

    @Override
    protected int getLayoutResId() {
        return R.layout.edit_circle_fg;
    }

    @Override
    protected String title() {
        return "编辑圈子";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        detectKeyBoardHide();
    }

    private void detectKeyBoardHide() {
        final SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(getActivity().findViewById(R.id.edit_circle));
        softKeyboardStateHelper.addSoftKeyboardStateListener(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        Intent intent = getActivity().getIntent();
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        Presenter.getInstance(getContext()).getCircleTargetEdit();
        qServiceCfg = QServiceCfg.instance(getContext());
        cachePath = getContext().getExternalCacheDir().getAbsolutePath();
        if (intent != null) {
            dataBean = (CircleDetailResponse.DataBean) intent.getSerializableExtra("circle_detail");
            if (dataBean != null) {
                String paramStr = new Gson().toJson(dataBean);
                createCircleBodyParam = new Gson().fromJson(paramStr, CreateCircleBodyParam.class);

                cirNameDesc = (EditText) viewRoot.findViewById(R.id.cir_name_desc);
                cirNameDesc.setText(dataBean.getName());
                circleStandNum = (TextView) viewRoot.findViewById(R.id.circle_stand_num);
                circleStandNum.setText(String.valueOf(dataBean.getTarget()));
                createCircleBodyParam.setTargetid(dataBean.getTarget());
                circlePhoneNumEditor = (EditText) viewRoot.findViewById(R.id.circle_phone_num_editor);
                passwordNumEditor = (EditText) viewRoot.findViewById(R.id.password_num_editor);
                circlePhoneNumEditor.setText(dataBean.getMobile());
                moneyPkgPan = (RelativeLayout) viewRoot.findViewById(R.id.money_pkg_pan);
                readPackageMumPan = (RelativeLayout) viewRoot.findViewById(R.id.read_package_mum_pan);
                moneyMumPan = (RelativeLayout) viewRoot.findViewById(R.id.money_mum_pan);
                switchCircleMoneyAddOff = (ImageView) viewRoot.findViewById(R.id.switch_circle_money_add_off);
                logoCirclePic = (CircleImageView) viewRoot.findViewById(R.id.logo_circle_pic);
                /*Presenter.getInstance(getContext()).getImage(logoCirclePic, dataBean.getLogo());*/
                LoadBitmap.glideLoad(getActivity(), logoCirclePic, dataBean.getLogo());
                if (1 == dataBean.getIs_recharge()) {
                    switchCircleMoneyAddOff.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a));
                    switchCircleMoneyAddOff.setAlpha(0.5f);
                    is_recharge = true;
                    enableMoneyEdit();
                } else {
                    switchCircleMoneyAddOff.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a_pass));
                    switchCircleMoneyAddOff.setAlpha(0.5f);
                    is_recharge = false;
                    disableMoneyEdit();
                }
                circleMoneyNumEditor = (EditText) viewRoot.findViewById(R.id.circle_money_num_editor);
                circleMoneyNumEditor.setText(String.valueOf(dataBean.getTotal_amount()));
                circleReadPackageEditor = (EditText) viewRoot.findViewById(R.id.circle_read_package_editor);
                circleReadPackageEditor.setText(String.valueOf(dataBean.getRed_packet_amount()));
                moneyPkgNumEditor = (EditText) viewRoot.findViewById(R.id.money_pkg_num_editor);
                moneyPkgNumEditor.setText(String.valueOf(dataBean.getRed_packetInt()));
                passwordPan = (RelativeLayout) viewRoot.findViewById(R.id.password_pan);
                passwordCircleSwitch = (ImageView) viewRoot.findViewById(R.id.password_circle_switch);
                circleDescOfYour = (EditText) viewRoot.findViewById(R.id.circle_desc_of_your);
                if (dataBean.getIs_pwd() == 1) {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a));
                    is_pwd = true;
                    enablePassEdit();
                    changePwdEditTextLogic();
                } else if (dataBean.getIs_pwd() == 0) {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a_pass));
                    is_pwd = false;
                    disablePassEdit();
                }
                circleDescOfYour.setText(dataBean.getDescription());
                mHandler = new Handler(getActivity().getMainLooper());
            }
        }
    }

    private void changePwdEditTextLogic() {
        passwordNumEditor.setText(DEFAULT_EMPTY_PWD);
        passwordNumEditor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    passwordNumEditor.setText("");
                    passwordNumEditor.setOnKeyListener(null);
                    return true;
                }
                return false;
            }
        });
    }


    public void disablePassEdit() {
        passwordPan.setVisibility(View.GONE);
    }

    public void enablePassEdit() {
        passwordPan.setVisibility(View.VISIBLE);
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

/*
    @TargetApi(19)
    @OnClick({*//*R.id.switch_style,*//*
            R.id.logo_circle_pan,
            R.id.stand_circle_pan, R.id.switch_circle_money_add_off, R.id.logo_circle_pic,
            R.id.create_circle_confim,
            *//*R.id.circle_theme_text*//*})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stand_circle_pan:
                LocalLog.d(TAG, "设定目标距离");
                selectType(targetDefaults, circleStandNum);
                break;
            case R.id.logo_circle_pic:
                break;
            case R.id.edit_circle_confim:
                LocalLog.d(TAG, "保存编辑");
                break;
            case R.id.logo_circle_pan:
                LocalLog.d(TAG, "上传圈子logo");
                requestPermission(Permission.Group.STORAGE);
                break;
           *//* case R.id.circle_theme_text:
                LocalLog.d(TAG, "添加标签!");
                Intent intent = new Intent();
                intent.setClass(this, CirCleTagActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TAG);
                break;*//*
            default:
                break;
        }
    }*/

    public void selectType(ArrayList<String> strings, final TextView desView) {
        final SelectSettingAdapter selectSettingAdapter = new SelectSettingAdapter(getContext(), strings);

        popupCircleTypeView = View.inflate(getContext(), R.layout.select_dialog_layout, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
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
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
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

        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.edit_circle), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
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

    private void selectPicture() {
        popupCircleTypeView = View.inflate(getActivity(), R.layout.select_camera_pic, null);
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
                        .start(EditCircleFragment.this, REQUEST_CODE);
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
                        .start(EditCircleFragment.this, REQUEST_CODE);
                popupCircleTypeWindow.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.edit_circle), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    protected void toast(@StringRes int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    @Override
    public void response(ErrorCode errorCode) {

    }

    @Override
    public void response(EditCircleResponse editCircleResponse) {
        if (!isAdded()) {
            return;
        }
        if (editCircleResponse.getError() == 0) {
            LocalLog.d(TAG, "编辑成功，通知详情界面更新");
            Toast.makeText(getContext(), "编辑成功", Toast.LENGTH_SHORT).show();
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        }
    }

    @Override
    public void response(CircleTargetResponse circleTargetResponse) {
        LocalLog.d(TAG, "CircleTargetResponse() enter");
        if (!isAdded()) {
            return;
        }
        if (circleTargetResponse.getError() == 0) {
            int size = circleTargetResponse.getData().size();
            LocalLog.d(TAG, "size = " + size);
            for (int i = 0; i < size; i++) {
                selectB.put(String.valueOf(circleTargetResponse.getData().get(i).getTarget()),
                        String.valueOf(circleTargetResponse.getData().get(i).getId()));
                targetDefaults.add(String.valueOf(circleTargetResponse.getData().get(i).getTarget()));
                if (dataBean != null) {
                    if (dataBean.getTarget() == circleTargetResponse.getData().get(i).getTarget()) {
                        LocalLog.d(TAG, "circle target");
                        createCircleBodyParam.setTargetid(circleTargetResponse.getData().get(i).getId());
                    }
                }
            }

        } else if (circleTargetResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
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
/*                Presenter.getInstance(getContext()).getImage(resultList.get(0).getImagePath(), logoCirclePic, resultList.get(0).getWidth() / 4
                        , resultList.get(0).getHeight() / 4);*/
                LoadBitmap.glideLoad(getActivity(), logoCirclePic, resultList.get(0).getImagePath());
                localAvatar = resultList.get(0).getImagePath();
            } else {
                LocalLog.d(TAG, "未知操作");
            }
            return;
        }
    }

    @OnClick({R.id.stand_circle_pan, R.id.logo_circle_pan, R.id.edit_circle_confim,R.id.password_circle_switch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stand_circle_pan:
                LocalLog.d(TAG, "设定目标距离");
                selectType(targetDefaults, circleStandNum);
                break;
            case R.id.logo_circle_pan:
                LocalLog.d(TAG, "上传圈子logo");
                requestPermission(Permission.Group.STORAGE);
                break;
            case R.id.password_circle_switch:
                LocalLog.d(TAG, "修改圈子");
                if (!is_pwd) {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.switch_bar_a));
                    is_pwd = true;
                    enablePassEdit();
                    createCircleBodyParam.setIs_pwd(1);
                } else {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.switch_bar_a_pass));
                    is_pwd = false;
                    disablePassEdit();
                    createCircleBodyParam.setIs_pwd(0);
                }
                break;
            case R.id.edit_circle_confim:
                LocalLog.d(TAG, "保存");
                if (checkcreateCircleBodyParam()) {
                    if (!TextUtils.isEmpty(localAvatar)) {
                        LogoUpTask logoUpTask = new LogoUpTask();
                        logoUpTask.execute(localAvatar);
                    } else {
                        Presenter.getInstance(getContext()).putCircle(createCircleBodyParam, dataBean.getId());
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
                result = putObjectSample.start(path,getContext().getApplicationContext());
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
            createCircleBodyParam.setLogo(s);
            Presenter.getInstance(getContext()).putCircle(createCircleBodyParam, dataBean.getId());
            //SocializeUtils.safeCloseDialog(dialog);
        }
    }

    public boolean checkcreateCircleBodyParam() {
        createCircleBodyParam.setUserid(Presenter.getInstance(getContext()).getId());
        //createCircleBodyParam.setCity("深圳福田");
        createCircleBodyParam.setName(cirNameDesc.getText().toString());
        if (cirNameDesc.getText() == null || cirNameDesc.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入圈子名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        createCircleBodyParam.setMobile(circlePhoneNumEditor.getText().toString());
        if (circlePhoneNumEditor.getText() == null || circlePhoneNumEditor.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入正确手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }

        LocalLog.d(TAG, "Is_recharge = " + createCircleBodyParam.isIs_recharge());
        if (createCircleBodyParam.isIs_recharge() == 0) {

        } else if (createCircleBodyParam.isIs_recharge() == 1) {
            if (moneyPkgNumEditor.getText().toString().equals("")
                    || circleReadPackageEditor.getText().toString().equals("")
                    || circleMoneyNumEditor.getText().toString().equals("")) {
                Toast.makeText(getContext(), "请完善红包信息", Toast.LENGTH_SHORT).show();
                return false;
            } else {

            }
        }

        LocalLog.d(TAG, "Is_pwd = " + createCircleBodyParam.isIs_pwd());
        if ((createCircleBodyParam.isIs_pwd() == 1) && (passwordNumEditor.getText().toString().equals(""))) {
            Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (createCircleBodyParam.isIs_recharge() == 1) {
            createCircleBodyParam.setRed_packet(Integer.parseInt(moneyPkgNumEditor.getText().toString()));
            createCircleBodyParam.setRed_packet_amount(Float.parseFloat(circleReadPackageEditor.getText().toString()));
            createCircleBodyParam.setTotal_amount(Float.parseFloat(circleMoneyNumEditor.getText().toString()));
        }

        if (createCircleBodyParam.isIs_pwd() == 1) {
            createCircleBodyParam.setPassword(passwordNumEditor.getText().toString());
            if (passwordNumEditor.getText().toString().equals(DEFAULT_EMPTY_PWD)) {//如果为默认的就不提交
                createCircleBodyParam.setPassword(null);
            }
        }

        if (circleDescOfYour.getText() == null || circleDescOfYour.getText().toString().equals("")) {
            Toast.makeText(getContext(), "圈子描述至少填写一个字符", Toast.LENGTH_SHORT).show();
        }
        createCircleBodyParam.setDescription(circleDescOfYour.getText().toString());

        return true;
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
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        LocalLog.d(TAG, "onSoftKeyboardOpened() 键盘弹出高度 ：" + keyboardHeightInPx);
        if (circleDescOfYour.hasFocus()) {
            //boundText.setVisibility(View.GONE);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "键盘弹出滚动到底部!");
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            boundText.setVisibility(View.GONE);
                        }
                    }, 500);
                }
            });
        }

    }

    @Override
    public void onSoftKeyboardClosed() {
        LocalLog.d(TAG, "键盘关闭");
    }
}
