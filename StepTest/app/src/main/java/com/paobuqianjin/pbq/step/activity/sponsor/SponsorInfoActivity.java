package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.ChooseAddressWheel;
import com.paobuqianjin.pbq.step.customview.ProUtils;
import com.paobuqianjin.pbq.step.data.bean.AddressDtailsEntity;
import com.paobuqianjin.pbq.step.data.bean.AddressModel;
import com.paobuqianjin.pbq.step.data.bean.gson.param.AddBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UpdateBusinessResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorInfoCollectFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorInfoActivity extends BaseBarActivity implements ChooseAddressWheel.OnSelectWheelItemListener, InnerCallBack {
    private final static String TAG = SponsorInfoCollectFragment.class.getSimpleName();
    public final static int REQ_TIME = 1;
    public final static int RES_TIME = 2;
    public final static int REQ_PIC = 3;
    public final static int REQ_PIC_IN = 4;
    public final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    public final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
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
                                String workTimeStr = dataBean.getDo_day();
                                if (!TextUtils.isEmpty(workTimeStr)) {
                                    editSponsorTime.setVisibility(View.GONE);
                                    editSponsorDay.setVisibility(View.VISIBLE);
                                    editSponsorHour.setVisibility(View.VISIBLE);
                                    editSponsorDay.setText(workTimeStr);
                                    editSponsorHour.setText(dataBean.getS_do_time() + "-" + dataBean.getE_do_time());
                                    sponsor_time = workTimeStr;
                                    start_time = dataBean.getS_do_time();
                                    end_time = dataBean.getE_do_time();

                                }
                                if (!TextUtils.isEmpty(dataBean.getAddra()))
                                    editSponsorLocationPan.setText(dataBean.getAddra());
                                editSponsorLocationDetailPan.setText(dataBean.getAddress());
                                List<SponsorDetailResponse.DataBean.EnvironmentImgsBean> imagesBean = dataBean.getEnvironment_imgs();
                                if (imagesBean.size() == 0) {
                                    editSponsorOutPics.setText("请上传照片");
                                } else {
                                    images = "";
                                    for (SponsorDetailResponse.DataBean.EnvironmentImgsBean image : imagesBean) {
                                        if (!TextUtils.isEmpty(images)) {
                                            images += ",";
                                        }
                                        images += image.getUrl();
                                    }
                                    editSponsorOutPics.setText("已上传" + imagesBean.size() + "张");
                                }
                                List<SponsorDetailResponse.DataBean.GoodsImgsBean> goodsImgsBean = dataBean.getGoods_imgs();
                                if (goodsImgsBean.size() == 0) {
                                    editSponsorInnerPics.setText("请上传照片");
                                } else {
                                    imagesIn = "";
                                    for (SponsorDetailResponse.DataBean.GoodsImgsBean image : goodsImgsBean) {
                                        if (!TextUtils.isEmpty(imagesIn)) {
                                            imagesIn += ",";
                                        }
                                        imagesIn += image.getUrl();
                                    }
                                    editSponsorInnerPics.setText("已上传" + goodsImgsBean.size() + "张");
                                }

                            }
                        }
                    }
                }
            });
        }
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
                if (chooseAddressWheel == null) {
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
                chooseAddressWheel.show(editSponsorLocationPan);
                break;
            case R.id.btn_confirm: {
                commit();
            }
            break;
        }
    }

    private void commit() {
        String name = editSponsorName.getText().toString();
        if (TextUtils.isEmpty(name.trim())) {
            ToastUtils.showShortToast(this, "请输入店铺名称");
            return;
        }
        AddBusinessParam param = new AddBusinessParam();
        param.setName(name);
        if (!TextUtils.isEmpty(editSponsorPhone.getText().toString().trim())) {
            param.setTel(editSponsorPhone.getText().toString());
        }
        if (!editSponsorTime.isShown()) {
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
        }
        if (!"_省_市_区".equals(editSponsorLocationPan.getText().toString())) {
            param.setAddra(editSponsorLocationPan.getText().toString())
                    .setAddress(editSponsorLocationDetailPan.getText().toString());
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
        if (businessId != -1) {
            param.setBusinessId(businessId);
            Presenter.getInstance(this).updateBusiness(param, this);
        } else {
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
        editSponsorLocationPan.setText(province + city + district);
        // isDifferent = true;
    }

    @Override
    public void innerCallBack(Object object) {
        if (object instanceof ErrorCode) {
            ToastUtils.showShortToast(this, ((ErrorCode) object).getMessage());
        } else if (object instanceof AddBusinessResponse) {
            AddBusinessResponse response = (AddBusinessResponse) object;
            if (response.getError() == 0) {
                intent.putExtra("businessId", Integer.parseInt(response.getData().getBusinessid()));
                intent.putExtra("name", response.getData().getName());
                setResult(10, intent);
                finish();
            }
        } else if (object instanceof UpdateBusinessResponse) {
            if (((UpdateBusinessResponse) object).getError() == 0) {
                ToastUtils.showShortToast(this, "编辑成功");
                if (businessId != -1) {
                    intent.putExtra("businessId", businessId);
                    setResult(10, intent);
                }
                finish();
            }
        }
    }
}