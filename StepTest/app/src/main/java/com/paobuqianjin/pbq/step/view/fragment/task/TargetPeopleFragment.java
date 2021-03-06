package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorRedLocationActivity;
import com.paobuqianjin.pbq.step.customview.ChooseTargetWheel;
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

public class TargetPeopleFragment extends BaseBarStyleTextViewFragment implements ChooseTargetWheel.OnTargetChangeListener {
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
    RadioButton nvText;
    @Bind(R.id.man_text)
    RadioButton manText;
    @Bind(R.id.sex_unselect_text)
    RadioButton sexUnselectText;
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
    private String targetSelectStr = "0";
    private ChooseTargetWheel chooseTargetWheel;
    public static final int REQ_POSITION = 3;
    public static final int RES_POSITION = 4;

    private String city;
    private double latitude, longitude;

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
                intent.setClass(getContext(), SponsorRedLocationActivity.class);
                startActivityForResult(intent, REQ_POSITION);
                break;
            case R.id.location_radius:
                if (chooseTargetWheel == null) {
                    chooseTargetWheel = new ChooseTargetWheel(getActivity());
                    chooseTargetWheel.setOnTargetChangeListener(this);
                }
                chooseTargetWheel.setSelectByDistance(targetSelectStr);
                chooseTargetWheel.show(locationRadius);
                break;
            case R.id.btn_confirm:

                sexStr = getSex();
                String min = minAge.getText().toString();
                String max = maxAge.getText().toString();
                minAgeStr = TextUtils.isEmpty(min.trim()) ? "0" : min;
                maxAgeStr = TextUtils.isEmpty(max.trim()) ? "0" : max;
                Intent intentResult = new Intent();
                intentResult.putExtra("sexStr", sexStr);
                intentResult.putExtra("minAgeStr", minAgeStr);
                intentResult.putExtra("maxAgeStr", maxAgeStr);
                intentResult.putExtra("targetSelectStr", targetSelectStr);
                if (!TextUtils.isEmpty(city))
                    intentResult.putExtra("city", city);
                if (0 != longitude)
                    intentResult.putExtra("longitudeStr", longitude);
                if (0 != latitude)
                    intentResult.putExtra("latitudeStr", latitude);
                getActivity().setResult(1, intentResult);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_POSITION && resultCode == RES_POSITION) {
            city = data.getStringExtra("city");
            latitude = data.getDoubleExtra("latitude", 0);
            longitude = data.getDoubleExtra("longitude", 0);
            locationSelect.setText(city);
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

    @Override
    public void onTargetChange(String content, String distance) {
        targetSelect.setText(content);
        targetSelectStr = distance;
    }
}
