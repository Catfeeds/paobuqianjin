package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.ChooseAddressWheel;
import com.paobuqianjin.pbq.step.customview.Utils;
import com.paobuqianjin.pbq.step.data.bean.AddressDtailsEntity;
import com.paobuqianjin.pbq.step.data.bean.AddressModel;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorGoodsPicLookActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorInfoCollectFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorInfoActivity extends BaseBarActivity implements ChooseAddressWheel.OnAddressChangeListener {
    private final static String TAG = SponsorInfoCollectFragment.class.getSimpleName();
    public final static int REQ_TIME = 1;
    public final static int RES_TIME = 2;
    private final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    private final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
    private final static String ACTION_WORK_TIME_ACTION = "com.paobuqianjin.pbq.setp.WORK_TIME_ACTION";
    @Bind(R.id.edit_sponsor_name)
    EditText editSponsorName;
    @Bind(R.id.sponsor_name_pan)
    RelativeLayout sponsorNamePan;
    @Bind(R.id.edit_sponsor_phone)
    EditText editSponsorPhone;
    @Bind(R.id.sponsor_phone_pan)
    RelativeLayout sponsorPhonePan;
    @Bind(R.id.edit_sponsor_time)
    TextView editSponsorTime;
    @Bind(R.id.sponsor_time_pan)
    RelativeLayout sponsorTimePan;
    @Bind(R.id.edit_sponsor_location_pan)
    TextView editSponsorLocationPan;
    @Bind(R.id.sponsor_location_pan)
    RelativeLayout sponsorLocationPan;
    @Bind(R.id.edit_sponsor_location_detail_pan)
    EditText editSponsorLocationDetailPan;
    @Bind(R.id.sponsor_location_detail_pan)
    RelativeLayout sponsorLocationDetailPan;
    @Bind(R.id.edit_sponsor_out_pics)
    TextView editSponsorOutPics;
    @Bind(R.id.sponsor_out_pics_pan)
    RelativeLayout sponsorOutPicsPan;
    @Bind(R.id.edit_sponsor_inner_pics)
    TextView editSponsorInnerPics;
    @Bind(R.id.sponsor_inner_pics_pan)
    RelativeLayout sponsorInnerPicsPan;
    @Bind(R.id.btn_confirm)
    Button confirm;

    private String sponsor_time;
    private String start_time;
    private String end_time;
    private ChooseAddressWheel chooseAddressWheel;
    private AddressDtailsEntity data;
    private String province;
    private String city;
    private String district;

    @Override
    protected String title() {
        return "商铺信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_info_collect_fg);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.sponsor_time_pan, R.id.sponsor_location_pan, R.id.sponsor_out_pics_pan,
            R.id.sponsor_inner_pics_pan, R.id.edit_sponsor_location_pan, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sponsor_time_pan:
                LocalLog.d(TAG, "设定营业时间");
                Intent intentTime = new Intent();
                intentTime.setAction(ACTION_WORK_TIME_ACTION);
                intentTime.setClass(this, SponsorTimeActivity.class);
                intentTime.putExtra("time", sponsor_time);
                intentTime.putExtra("startTime", start_time);
                intentTime.putExtra("endTime", end_time);
                startActivityForResult(intentTime, REQ_TIME);
                break;
            case R.id.sponsor_location_pan:
                LocalLog.d(TAG, "设定商铺地区");
                break;
            case R.id.sponsor_out_pics_pan:
                LocalLog.d(TAG, "门店照片");
                Intent intent = new Intent();
                intent.setAction(ACTION_INNER_PIC);
                intent.setClass(this, SponsorGoodsPicLookActivity.class);
                startActivity(intent);
                break;
            case R.id.sponsor_inner_pics_pan:
                LocalLog.d(TAG, "店内环境照片");
                Intent intentOut = new Intent();
                intentOut.setAction(ACTION_OUT_PIC);
                intentOut.setClass(this, SponsorGoodsPicLookActivity.class);
                startActivity(intentOut);
                break;
            case R.id.edit_sponsor_location_pan:
                if (chooseAddressWheel == null) {
                    initWheel();
                    String address = Utils.readAssert(this, "address.txt");
                    AddressModel model = new Gson().fromJson(address, AddressModel.class);
                    if (model != null) {
                        data = model.Result;
                        if (data == null) return;
                        if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                            chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                        }
                    }
                }
                setData();
                chooseAddressWheel.show(editSponsorLocationPan);
                break;
            case R.id.btn_confirm: {

            }
            break;
        }
    }

    private void initWheel() {
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setTitle("请选择地区");
        chooseAddressWheel.setOnAddressChangeListener(this);
    }

    private void setData() {
        if (data == null) return;
        if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
            if (province == null || province.length() == 0) {
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            } else {
                chooseAddressWheel.defaultValue(province, city, district);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RES_TIME: {
                editSponsorTime.setText("已设置");
                sponsor_time = data.getStringExtra("time");
                start_time = data.getStringExtra("startTime");
                end_time = data.getStringExtra("endTime");
                LocalLog.d("-------", sponsor_time + "====" + start_time + "===" + end_time);
            }
            break;
        }
    }

    @Override
    public void onAddressChange(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
        editSponsorLocationPan.setText(province + city + district);
        // isDifferent = true;
    }
}