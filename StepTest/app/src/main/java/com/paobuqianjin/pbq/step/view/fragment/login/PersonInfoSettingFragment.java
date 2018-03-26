package com.paobuqianjin.pbq.step.view.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoSetResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoLoginSetInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.WheelPicker;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.widgets.WheelDatePicker;

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

    @Override
    protected int getLayoutResId() {
        return R.layout.person_message_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        if (dataBean != null) {
            userid = dataBean.getId();
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
                Presenter.getInstance(getContext()).setMobile(getContext(), phoneLoginDataBean.getMobile());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }
            if (dataBean != null) {
                LocalLog.d(TAG, "三方登录成功");
                Presenter.getInstance(getContext()).steLogFlg(true);
                Presenter.getInstance(getContext()).setId(dataBean.getId());
                Presenter.getInstance(getContext()).setMobile(getContext(), dataBean.getMobile());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }

        }
    }


    @OnClick({R.id.user_icon, R.id.use_gender_man_select, R.id.use_gender_nv_select, R.id.birth_day_span, R.id.height_span, R.id.weight_span,
            R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                LocalLog.d(TAG, "更换头像");
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
                    Presenter.getInstance(getContext()).putUserInfo(userid, putUserInfoParam);
                }

                break;
        }
    }
}
