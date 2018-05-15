package com.paobuqianjin.pbq.step.customview;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.WheelPicker;

import java.util.List;

/**
 * Created by pbq on 2018/5/7.
 */

public class ChooseOneItemWheelPopWindow implements View.OnClickListener {
    WheelPicker provinceWheel;
    TextView dialogTitle;
    private Activity context;
    private View parentView;
    private PopupWindow popupWindow = null;
    private WindowManager.LayoutParams layoutParams = null;
    private LayoutInflater layoutInflater = null;

    private List<String> listData = null;

    private OnWheelItemConfirmListener itemConfirmListener = null;
    private Button mBtnCel;
    private Button mBtnSure;

    public ChooseOneItemWheelPopWindow(Activity context) {
        this.context = context;
        init();
    }

    public ChooseOneItemWheelPopWindow(Activity context, List<String> listData) {
        this.context = context;
        init();
        setListData(listData);
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
        parentView = layoutInflater.inflate(R.layout.pop_one_wheel, null);
        //ButterKnife.bind(this, parentView);
        provinceWheel = (WheelPicker) parentView.findViewById(R.id.province_wheel);
        dialogTitle = (TextView) parentView.findViewById(R.id.dialog_title);
        mBtnCel = (Button) parentView.findViewById(R.id.cancel_button);
        mBtnSure = (Button) parentView.findViewById(R.id.confirm_button);
        mBtnCel.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);

        provinceWheel.setIndicatorSize(5);
    }

    private void initPopupWindow() {
        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT, (int) (ProUtils.getScreenHeight(context) * 2.0 / 5));
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
        provinceWheel.setData(listData);
    }

    public void show(View v) {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }


    public void setCurrentSelectValue(String value) {
        try {
            provinceWheel.setSelectedItemPosition(listData.indexOf(value),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void show() {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }


    public void setTitle(String title) {
        dialogTitle.setText(title);
    }


    public void setListData(List<String> listData) {
        this.listData = listData;
        bindData();
    }

    //@OnClick(R.id.confirm_button)
    public void confirm() {
        if (itemConfirmListener != null) {
            int provinceIndex = provinceWheel.getCurrentItemPosition();

            String provinceName = null, cityName = null, areaName = null;

            provinceName = listData.get(provinceIndex);
            itemConfirmListener.onItemSelectLis(provinceName);
        }
        cancel();
    }

    //@OnClick(R.id.cancel_button)
    public void cancel() {
        popupWindow.dismiss();
    }

    public void setItemConfirmListener(OnWheelItemConfirmListener itemConfirmListener) {
        this.itemConfirmListener = itemConfirmListener;
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
    public interface OnWheelItemConfirmListener{
        void onItemSelectLis(String result);
    }
}
