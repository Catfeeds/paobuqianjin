package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorGoodsPicLookActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorSelectActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/19.
 */

public class TargetPeopleFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = TargetPeopleFragment.class.getSimpleName();
    private final static String ACTION_RED_PACK_LOCATION = "com.paobuqianjin.pbq.step.RED_PKG_ACTION";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.sex)
    TextView sex;
    @Bind(R.id.nv_text)
    CheckBox nvText;
    @Bind(R.id.man_text)
    CheckBox manText;
    @Bind(R.id.sex_unselect_text)
    CheckBox sexUnselectText;
    @Bind(R.id.sex_pan)
    RelativeLayout sexPan;
    @Bind(R.id.age)
    TextView age;
    @Bind(R.id.age_quto)
    TextView ageQuto;
    @Bind(R.id.max_age)
    EditText maxAge;
    @Bind(R.id.to)
    TextView to;
    @Bind(R.id.age_quto_left)
    TextView ageQutoLeft;
    @Bind(R.id.age_pan)
    RelativeLayout agePan;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.location_select)
    TextView locationSelect;
    @Bind(R.id.location_pan)
    RelativeLayout locationPan;
    @Bind(R.id.target_pan)
    TextView targetPan;
    @Bind(R.id.go_to_pan)
    ImageView goToPan;
    @Bind(R.id.target_select)
    TextView targetSelect;
    @Bind(R.id.location_radius)
    RelativeLayout locationRadius;
    @Bind(R.id.min_age)
    EditText minAge;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    private String sexStr;
    private String minAgeStr;
    private String maxAgeStr;
    private String targetSelectStr;

    @Override
    protected int getLayoutResId() {
        return R.layout.target_people_fg;
    }

    @Override
    protected String title() {
        return "目标人群";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        ButterKnife.bind(this, viewRoot);
        nvText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && manText.isChecked()) {
                    manText.setChecked(false);
                }
            }
        });
        manText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && nvText.isChecked()) {
                    nvText.setChecked(false);
                }
            }
        });
        sexUnselectText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && sexUnselectText.isChecked()) {
                    sexUnselectText.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.location_pan, R.id.location_radius, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location_pan:
                LocalLog.d(TAG, "位置选择");
                Intent intent = new Intent();
                intent.setAction(ACTION_RED_PACK_LOCATION);
                intent.setClass(getContext(), SponsorGoodsPicLookActivity.class);
                startActivity(intent);
                break;
            case R.id.location_radius:
                break;
            case R.id.btn_confirm:

                sexStr = getSex();
                minAgeStr = minAge.getText().toString();
                maxAgeStr = maxAge.getText().toString();
                targetSelectStr = targetSelect.getText().toString();
                String longitudeStr = ((SponsorSelectActivity) getActivity()).longitudeStr;
                String latitudeStr = ((SponsorSelectActivity) getActivity()).latitudeStr;

                Intent intentResult = new Intent();
                intentResult.putExtra("sexStr", sexStr);
                intentResult.putExtra("minAgeStr", minAgeStr);
                intentResult.putExtra("maxAgeStr", maxAgeStr);
                intentResult.putExtra("targetSelectStr", targetSelectStr);
                if(!TextUtils.isEmpty(longitudeStr)) intentResult.putExtra("targetSelectStr", longitudeStr);
                if(!TextUtils.isEmpty(latitudeStr)) intentResult.putExtra("targetSelectStr", latitudeStr);
                getActivity().setResult(1, intentResult);
                getActivity().finish();
                break;
        }
    }

    private String getSex() {
        if (nvText.isChecked()) {
            return "2";
        }
        if (manText.isChecked()) {
            return "1";
        }
        return "0";
    }
}
