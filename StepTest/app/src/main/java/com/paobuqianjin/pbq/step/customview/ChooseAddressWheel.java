package com.paobuqianjin.pbq.step.customview;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.AddressDtailsEntity;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.WheelPicker;

import java.util.ArrayList;
import java.util.List;


public class ChooseAddressWheel implements View.OnClickListener {

    //@Bind(R.id.province_wheel)
    WheelPicker provinceWheel;
    //@Bind(R.id.city_wheel)
    WheelPicker cityWheel;
    //@Bind(R.id.district_wheel)
    WheelPicker districtWheel;
    //@Bind(R.id.dialog_title)
    TextView dialogTitle;

    private Activity context;
    private View parentView;
    private PopupWindow popupWindow = null;
    private WindowManager.LayoutParams layoutParams = null;
    private LayoutInflater layoutInflater = null;

    private List<AddressDtailsEntity.ProvinceEntity> province = null;

    private OnAddressChangeListener onAddressChangeListener = null;
    private Button mBtnCel;
    private Button mBtnSure;

    public ChooseAddressWheel(Activity context) {
        this.context = context;
        init();
    }

    private void init() {
        layoutParams = context.getWindow().getAttributes();
        layoutInflater = context.getLayoutInflater();
        initView();
        initPopupWindow();
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    private void initView() {
        parentView = layoutInflater.inflate(R.layout.choose_city_layout, null);
        //ButterKnife.bind(this, parentView);
        provinceWheel = (WheelPicker) parentView.findViewById(R.id.province_wheel);
        cityWheel = (WheelPicker) parentView.findViewById(R.id.city_wheel);
        districtWheel = (WheelPicker) parentView.findViewById(R.id.district_wheel);
        dialogTitle = (TextView) parentView.findViewById(R.id.dialog_title);
        mBtnCel = (Button) parentView.findViewById(R.id.cancel_button);
        mBtnSure = (Button) parentView.findViewById(R.id.confirm_button);
        mBtnCel.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);

        provinceWheel.setIndicatorSize(5);
        cityWheel.setIndicatorSize(5);
        districtWheel.setIndicatorSize(5);

        provinceWheel.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {

            }

            @Override
            public void onWheelSelected(int position) {
                updateCitiy();//省份改变后城市和地区联动
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
            }
        });
        cityWheel.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {

            }

            @Override
            public void onWheelSelected(int position) {
                updateDistrict();//城市改变后地区联动
            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });
    }

    private void initPopupWindow() {
        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT, (int)(ProUtils.getScreenHeight(context)*2.0/5));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.anim_push_bottom);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                layoutParams.alpha = 1.0f;
                context.getWindow().setAttributes(layoutParams);
                popupWindow.dismiss();
            }
        });
    }

    private void bindData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < province.size(); i++) {
            data.add(province.get(i).Name);
        }
        provinceWheel.setData(data);
        updateCitiy();
        updateDistrict();
    }


    private void updateCitiy() {
        int index = provinceWheel.getCurrentItemPosition();
        List<AddressDtailsEntity.ProvinceEntity.CityEntity> citys = province.get(index).City;
        if (citys != null && citys.size() > 0) {
            List<String> data = new ArrayList<>();
            for (int i = 0; i < citys.size(); i++) {
                data.add(citys.get(i).Name);
            }
            cityWheel.setData(data);
            cityWheel.setSelectedItemPosition(0, false);
            updateDistrict();
        }
    }

    private void updateDistrict() {
        int provinceIndex = provinceWheel.getCurrentItemPosition();
        List<AddressDtailsEntity.ProvinceEntity.CityEntity> citys = province.get(provinceIndex).City;
        int cityIndex = cityWheel.getCurrentItemPosition();
        List<AddressDtailsEntity.ProvinceEntity.AreaEntity> districts = citys.get(cityIndex).Area;
        if (districts != null && districts.size() > 0) {
            List<String> data = new ArrayList<>();
            for (int i = 0; i < districts.size(); i++) {
                data.add(districts.get(i).Name);
            }
            districtWheel.setData(data);
            districtWheel.setSelectedItemPosition(0, false);
        }

    }

    public void show(View v) {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void setTitle(String title) {
        dialogTitle.setText(title);
    }


    public void setProvince(List<AddressDtailsEntity.ProvinceEntity> province) {
        this.province = province;
        bindData();
    }

    public void defaultValue(String provinceStr, String city, String arae) {
        if (TextUtils.isEmpty(provinceStr)) return;
        for (int i = 0; i < province.size(); i++) {
            AddressDtailsEntity.ProvinceEntity provinces = province.get(i);
            if (provinces != null && provinces.Name.equalsIgnoreCase(provinceStr)) {
                provinceWheel.setSelectedItemPosition(i, false);
                if (TextUtils.isEmpty(city)) return;
                List<AddressDtailsEntity.ProvinceEntity.CityEntity> citys = provinces.City;
                for (int j = 0; j < citys.size(); j++) {
                    AddressDtailsEntity.ProvinceEntity.CityEntity cityEntity = citys.get(j);
                    if (cityEntity != null && cityEntity.Name.equalsIgnoreCase(city)) {
                        List<String> data = new ArrayList<>();
                        for (int k = 0; k < citys.size(); k++) {
                            data.add(citys.get(k).Name);
                        }
                        cityWheel.setData(data);
                        cityWheel.setSelectedItemPosition(j, false);
                        if (TextUtils.isEmpty(arae)) return;
                        List<AddressDtailsEntity.ProvinceEntity.AreaEntity> areas = cityEntity.Area;
                        for (int k = 0; k < areas.size(); k++) {
                            AddressDtailsEntity.ProvinceEntity.AreaEntity areaEntity = areas.get(k);
                            if (areaEntity != null && areaEntity.Name.equalsIgnoreCase(arae)) {
                                List<String> datas = new ArrayList<>();
                                for (int l = 0; l < areas.size(); l++) {
                                    datas.add(areas.get(l).Name);
                                }
                                districtWheel.setData(datas);
                                districtWheel.setSelectedItemPosition(k, false);
                            }
                        }
                    }
                }
            }
        }
    }

    //@OnClick(R.id.confirm_button)
    public void confirm() {
        if (onAddressChangeListener != null) {
            int provinceIndex = provinceWheel.getCurrentItemPosition();
            int cityIndex = cityWheel.getCurrentItemPosition();
            int areaIndex = districtWheel.getCurrentItemPosition();

            String provinceName = null, cityName = null, areaName = null;

            List<AddressDtailsEntity.ProvinceEntity.CityEntity> citys = null;
            if (province != null && province.size() > provinceIndex) {
                AddressDtailsEntity.ProvinceEntity provinceEntity = province.get(provinceIndex);
                citys = provinceEntity.City;
                provinceName = provinceEntity.Name;
            }
            List<AddressDtailsEntity.ProvinceEntity.AreaEntity> districts = null;
            if (citys != null && citys.size() > cityIndex) {
                AddressDtailsEntity.ProvinceEntity.CityEntity cityEntity = citys.get(cityIndex);
                districts = cityEntity.Area;
                cityName = cityEntity.Name;
            }

            if (districts != null && districts.size() > areaIndex) {
                AddressDtailsEntity.ProvinceEntity.AreaEntity areaEntity = districts.get(areaIndex);
                areaName = areaEntity.Name;
            }

            onAddressChangeListener.onAddressChange(provinceName, cityName, areaName);
        }
        cancel();
    }

    //@OnClick(R.id.cancel_button)
    public void cancel() {
        popupWindow.dismiss();
    }

    public void setOnAddressChangeListener(OnAddressChangeListener onAddressChangeListener) {
        this.onAddressChangeListener = onAddressChangeListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                cancel();
                break;
            case R.id.confirm_button:
                confirm();
                break;
            default:
                break;
        }

    }

    public interface OnAddressChangeListener {
        void onAddressChange(String province, String city, String district);
    }
}