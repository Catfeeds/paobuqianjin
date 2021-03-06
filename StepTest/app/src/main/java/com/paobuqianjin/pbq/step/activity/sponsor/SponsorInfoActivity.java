package com.paobuqianjin.pbq.step.activity.sponsor;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.ChooseAddressWheel;
import com.paobuqianjin.pbq.step.data.bean.AddressDtailsEntity;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AddBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UpdateBusinessResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.param.Address2GeoParam;
import com.tencent.lbssearch.object.result.Address2GeoResultObject;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorInfoActivity extends BaseBarActivity implements ChooseAddressWheel.OnSelectWheelItemListener, InnerCallBack {
    private final static String TAG = SponsorInfoActivity.class.getSimpleName();
    public final static int REQ_TIME = 1;
    public final static int RES_TIME = 2;
    public final static int REQ_PIC = 3;
    public final static int REQ_PIC_IN = 4;
    public final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    public final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
    private final static String ACTION_WORK_TIME_ACTION = "com.paobuqianjin.pbq.setp.WORK_TIME_ACTION";
    private final static int REQ_POSITION = 101;
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
    @Bind(R.id.edit_sponsor_day)
    TextView editSponsorDay;
    @Bind(R.id.edit_sponsor_hour)
    TextView editSponsorHour;
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
    @Bind(R.id.sponsors)
    EditText sponsors;

    private String sponsor_time;
    private String start_time;
    private String end_time;
    private ChooseAddressWheel chooseAddressWheel;
    private AddressDtailsEntity data;
    private String province;
    private String city;
    private String district;
    private int businessId = -1;
    private String images;
    private String imagesIn;
    private Intent intent;
    private AddBusinessParam oldParam = null;
    private boolean isChangeImagesIn;//是否改变了店内环境
    private boolean isChangeImagesOut;//是否改变了门店logo
    //详细地址
    private String longitude = "0.0000";
    private String latitude = "0.0000";
    private PermissionSetting mSetting;
    private TencentSearch tencentSearch;
    private boolean isRetry = false;

    @Override
    protected String title() {
        return "商铺信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_info_collect_fg);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        intent = getIntent();
        businessId = intent.getIntExtra("businessId", -1);
//        businessId = 103;
        if (businessId != -1) {
            Presenter.getInstance(this).businessDetail(businessId, new InnerCallBack() {
                @Override
                public void innerCallBack(Object object) {
                    if (object instanceof SponsorDetailResponse) {
                        if (((SponsorDetailResponse) object).getError() == 0) {
                            SponsorDetailResponse.DataBean dataBean = ((SponsorDetailResponse) object).getData();
                            if (dataBean != null) {
                                editSponsorName.setText(dataBean.getName());
                                editSponsorPhone.setText(dataBean.getTel());
                                /*String workTimeStr = dataBean.getDo_day();
                                if (!TextUtils.isEmpty(workTimeStr)) {
                                    editSponsorTime.setVisibility(View.GONE);
                                    editSponsorDay.setVisibility(View.VISIBLE);
                                    editSponsorHour.setVisibility(View.VISIBLE);
                                    editSponsorDay.setText(workTimeStr);
                                    editSponsorHour.setText(dataBean.getS_do_time() + "-" + dataBean.getE_do_time());
                                    sponsor_time = workTimeStr;
                                    start_time = dataBean.getS_do_time();
                                    end_time = dataBean.getE_do_time();

                                }*/
                                if (!TextUtils.isEmpty(dataBean.getScope())) {
                                    sponsors.setText(dataBean.getScope());
                                }
                                if (!TextUtils.isEmpty(dataBean.getAddra()))
                                    editSponsorLocationPan.setText(dataBean.getAddra());
                                editSponsorLocationDetailPan.setText(dataBean.getAddress());
                                latitude = dataBean.getLatitude();
                                longitude = dataBean.getLongitude();
                                if (!TextUtils.isEmpty(dataBean.getLogo())) {
                                    images = "";
                                    editSponsorOutPics.setText("已上传1张");
                                    images = dataBean.getLogo();
                                } else {
                                    editSponsorOutPics.setText("请上传照片");
                                }

                                List<SponsorDetailResponse.DataBean.EnvironmentImgsBean> imagesBean = dataBean.getEnvironment_imgs();
                                if (imagesBean.size() == 0) {
                                    editSponsorInnerPics.setText("请上传照片");
                                } else {
                                    imagesIn = "";
                                    for (SponsorDetailResponse.DataBean.EnvironmentImgsBean image : imagesBean) {
                                        if (!TextUtils.isEmpty(imagesIn)) {
                                            imagesIn += ",";
                                        }
                                        imagesIn += image.getUrl();
                                    }
                                    editSponsorInnerPics.setText("已上传" + imagesBean.size() + "张");
                                }

                            }
                        }
                    }
                }
            });
        }
        mSetting = new PermissionSetting(this);
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
                intent.setAction(ACTION_OUT_PIC);
                intent.setClass(this, SponsorSelectPicActivity.class);
                intent.putExtra("images", images);
                startActivityForResult(intent, REQ_PIC);
                break;
            case R.id.sponsor_inner_pics_pan:
                LocalLog.d(TAG, "店内环境照片");
                Intent intentOut = new Intent();
                intentOut.setAction(ACTION_INNER_PIC);
                intentOut.setClass(this, SponsorSelectPicActivity.class);
                intentOut.putExtra("images", imagesIn);
                startActivityForResult(intentOut, REQ_PIC_IN);
                break;
            case R.id.edit_sponsor_location_pan:
                LocalLog.d(TAG, "位置选择");
                Intent intentLocation = new Intent();
                intentLocation.putExtra("lat", latitude);
                intentLocation.putExtra("lng", longitude);
                intentLocation.setClass(this, SponsorTMapActivity.class);
                startActivityForResult(intentLocation, REQ_POSITION);
/*                if (chooseAddressWheel == null) {
                    initWheel();
                    String address = ProUtils.readAssert(this, "address.txt");
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
                chooseAddressWheel.show(editSponsorLocationPan);*/
                break;
            case R.id.btn_confirm: {
                commit(latitude, longitude);
                if (confirm == null) {
                    confirm = (Button) findViewById(R.id.btn_confirm);
                    confirm.setEnabled(false);
                }
            }
            break;
        }
    }


    private void requestPermission(String... permissions) {
        DefaultRationale mRationale = new DefaultRationale();
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        getLocation();
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(SponsorInfoActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    public void getLocation() {
        if (tencentSearch == null) {
            tencentSearch = new TencentSearch(this);
        }

        Address2GeoParam param = new Address2GeoParam()
                .address(province + city + district + editSponsorName.getText().toString())
                .region(province);
        LocalLog.d(TAG, "province = " + province + ", " + editSponsorLocationDetailPan.getText().toString());
        tencentSearch.address2geo(param, new HttpResponseListener() {
            @Override
            public void onSuccess(int i, BaseObject baseObject) {
                Address2GeoResultObject object = (Address2GeoResultObject) baseObject;
                if (object.result != null) {
                    if (object.result.location != null) {
                        commit(String.valueOf(object.result.location.lat), String.valueOf(object.result.location.lng));
                    }
                }
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                LocalLog.d(TAG, "获取经纬度失败！");
                LocalLog.d(TAG, "i = " + i + ",s =" + s);
                if (!isRetry) {
                    Address2GeoParam param_retry = new Address2GeoParam()
                            .address(province + city + district)
                            .region(province);
                    tencentSearch.address2geo(param_retry, this);
                    isRetry = true;
                } else {
                    PaoToastUtils.showShortToast(getApplicationContext(), "没有相关地址哦！");
                }
                return;
            }
        });
    }

    private void commit(String latitude, String longitude) {
        String name = editSponsorName.getText().toString();
        if (TextUtils.isEmpty(name.trim())) {
            PaoToastUtils.showShortToast(this, "请输入店铺名称");
            return;
        }
        AddBusinessParam param = new AddBusinessParam();
        param.setName(name);
        if (!TextUtils.isEmpty(editSponsorPhone.getText().toString().trim())) {
            param.setTel(editSponsorPhone.getText().toString());
        }
 /*       if (!editSponsorTime.isShown()) {
            String[] workTimeStrList = sponsor_time.split(",");
            String date = "";
            for (String time : workTimeStrList) {
                if (!"".equals(date)) {
                    date += ",";
                }
                switch (time) {
                    case "周一": {
                        date += "1";
                    }
                    break;
                    case "周二": {
                        date += "2";
                    }
                    break;
                    case "周三": {
                        date += "3";
                    }
                    break;
                    case "周四": {
                        date += "4";
                    }
                    break;
                    case "周五": {
                        date += "5";
                    }
                    break;
                    case "周六": {
                        date += "6";
                    }
                    break;
                    case "周日": {
                        date += "7";
                    }
                    break;
                }
            }
            param.setDo_day(date).setS_do_time(start_time).setE_do_time(end_time);
        }*/
        if (!"_省_市_区".equals(editSponsorLocationPan.getText().toString())) {
            param.setAddra(editSponsorLocationPan.getText().toString())
                    .setAddress(editSponsorLocationDetailPan.getText().toString());
            if (latitude.startsWith("0.00") || longitude.startsWith("0.00")) {
                LocalLog.d(TAG, "位置未知!");
                String[] permissions = {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                requestPermission(permissions);
                return;
            } else {
                param.setLatitude(latitude);
                param.setLongitude(longitude);
            }
        }
        if (!TextUtils.isEmpty(images)) {
            param.setLogo(images);
            LocalLog.d(TAG, "out----" + images);
        }
        if (!TextUtils.isEmpty(imagesIn)) {
            param.setGoods_images(imagesIn);
            param.setEnvironment_images(imagesIn);
            LocalLog.d(TAG, "in----" + imagesIn);
        }

        if (!TextUtils.isEmpty(sponsors.getText().toString().trim())) {
            param.setScope(sponsors.getText().toString().trim());
        } else {
            param.setScope("");
        }

        if (businessId != -1) {
            param.setBusinessId(businessId);
            Presenter.getInstance(this).updateBusiness(param, this);
        } else {
            if (oldParam == null) {
                oldParam = param;
            } else {
                if (param.equals(oldParam)) {
                    PaoToastUtils.showShortToast(this, "不能重复创建!");
                    return;
                }
                oldParam = param;
            }
            Presenter.getInstance(this).AddBusiness(param, this);
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
        if (requestCode == REQ_PIC && resultCode > 0) {
            images = data.getStringExtra("images");
            int size = data.getIntExtra("size", 0);
            if (size == 0) {
                editSponsorOutPics.setText("请上传照片");
            } else {
                editSponsorOutPics.setText("已上传" + size + "张");
            }
            return;
        }
        if (requestCode == REQ_PIC_IN && resultCode > 0) {
            imagesIn = data.getStringExtra("images");
            int size = data.getIntExtra("size", 0);
            if (size == 0) {
                editSponsorInnerPics.setText("请上传照片");
            } else {
                editSponsorInnerPics.setText("已上传" + size + "张");
            }
            return;
        }
        if (requestCode == REQ_POSITION && data != null) {
            city = data.getStringExtra("city");
            latitude = String.valueOf(data.getDoubleExtra("latitude", 0));
            longitude = String.valueOf(data.getDoubleExtra("longitude", 0));
            if (TextUtils.isEmpty(data.getStringExtra("address"))) {
                editSponsorLocationPan.setText(city);
            } else {
                city = data.getStringExtra("address");
                editSponsorLocationPan.setText(city);
            }
        }
        switch (resultCode) {
            case RES_TIME: {
                sponsor_time = data.getStringExtra("time");
                start_time = data.getStringExtra("startTime");
                end_time = data.getStringExtra("endTime");
                if (!TextUtils.isEmpty(sponsor_time)) {
                    editSponsorTime.setVisibility(View.GONE);
                    editSponsorDay.setVisibility(View.VISIBLE);
                    editSponsorHour.setVisibility(View.VISIBLE);
                    editSponsorDay.setText(sponsor_time);
                    editSponsorHour.setText(start_time + "-" + end_time);
                }
            }
            break;
        }
    }

    @Override
    public void onAddressChange(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
        editSponsorLocationPan.setText(province + "/" + city + "/" + district);
        // isDifferent = true;
    }

    private void showSponsorError(String message) {
        String error = message;
        switch (message) {
            case "店铺LOGO必传":
            case "店铺名称必填":
            case "电话必填":
            case "省市区必填":
            case "详细地址必填":
            case "经营时间必填":
            case "店铺环境照片必传":
                error = "还有信息未填写";
                break;
        }
        PaoToastUtils.showLongToast(this, error);
    }

    @Override
    public void innerCallBack(Object object) {
        if (object instanceof ErrorCode) {
            PaoToastUtils.showShortToast(this, ((ErrorCode) object).getMessage());
        } else if (object instanceof AddBusinessResponse) {
            AddBusinessResponse response = (AddBusinessResponse) object;
            if (response.getError() == 0) {
                intent.putExtra("businessId", Integer.parseInt(response.getData().getBusinessid()));
                intent.putExtra("name", response.getData().getName());
                setResult(10, intent);
                finish();
            } else {
                oldParam = null;
                if (!TextUtils.isEmpty(((AddBusinessResponse) object).getMessage()))
                    showSponsorError(((AddBusinessResponse) object).getMessage());
            }
        } else if (object instanceof UpdateBusinessResponse) {
            if (((UpdateBusinessResponse) object).getError() == 0) {
                PaoToastUtils.showShortToast(this, "编辑成功");
                if (businessId != -1) {
                    intent.putExtra("businessId", businessId);
                    setResult(10, intent);
                }
                finish();
            } else {
                oldParam = null;
                if (!TextUtils.isEmpty(((AddBusinessResponse) object).getMessage()))
                    showSponsorError(((AddBusinessResponse) object).getMessage());
            }
        }
        if (confirm != null) {
            confirm.setEnabled(true);
        }
    }
}