package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/28.
 */

public class EditCircleFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = EditCircleFragment.class.getSimpleName();
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
    private boolean is_recharge = false;
    private boolean is_pwd = false;

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
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            dataBean = (CircleDetailResponse.DataBean) intent.getSerializableExtra("circle_detail");
            if (dataBean != null) {
                cirNameDesc = (EditText) viewRoot.findViewById(R.id.cir_name_desc);
                cirNameDesc.setText(dataBean.getName());
                circleStandNum = (TextView) viewRoot.findViewById(R.id.circle_stand_num);
                circleStandNum.setText(String.valueOf(dataBean.getTarget()));
                circlePhoneNumEditor = (EditText) viewRoot.findViewById(R.id.circle_phone_num_editor);
                circlePhoneNumEditor.setText(dataBean.getMobile());
                moneyPkgPan = (RelativeLayout) viewRoot.findViewById(R.id.money_pkg_pan);
                readPackageMumPan = (RelativeLayout) viewRoot.findViewById(R.id.read_package_mum_pan);
                moneyMumPan = (RelativeLayout) viewRoot.findViewById(R.id.money_mum_pan);
                switchCircleMoneyAddOff = (ImageView)viewRoot.findViewById(R.id.switch_circle_money_add_off);
                if (1 == dataBean.getIs_recharge()) {
                    switchCircleMoneyAddOff.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a));
                    is_recharge = true;
                    enableMoneyEdit();
                } else {
                    switchCircleMoneyAddOff.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a_pass));
                    is_recharge = false;
                    disableMoneyEdit();
                }
                circleMoneyNumEditor = (EditText) viewRoot.findViewById(R.id.circle_money_num_editor);
                circleMoneyNumEditor.setText(String.valueOf(dataBean.getTotal_amount()));
                circleReadPackageEditor = (EditText) viewRoot.findViewById(R.id.circle_read_package_editor);
                circleReadPackageEditor.setText(String.valueOf(dataBean.getRed_packet()));
                moneyPkgNumEditor = (EditText) viewRoot.findViewById(R.id.money_pkg_num_editor);
                moneyPkgNumEditor.setText(String.valueOf(dataBean.getRed_packet_amount()));
                passwordPan = (RelativeLayout)viewRoot.findViewById(R.id.password_pan);
                passwordCircleSwitch =(ImageView)viewRoot.findViewById(R.id.password_circle_switch);
                if (dataBean.getIs_pwd() == 1) {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a));
                    is_pwd = true;
                    enablePassEdit();
                } else if (dataBean.getIs_pwd() == 0) {
                    passwordCircleSwitch.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_bar_a_pass));
                    is_pwd = false;
                    disablePassEdit();

                }

            }
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.edit_circle_confim)
    public void onClick() {
    }
}
