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

import java.util.Arrays;


public class ChooseTargetWheel implements View.OnClickListener {

    WheelPicker targetWheel;
    TextView dialogTitle;

    private Activity context;
    private View parentView;
    private PopupWindow popupWindow = null;
    private WindowManager.LayoutParams layoutParams = null;
    private LayoutInflater layoutInflater = null;

    private OnTargetChangeListener onTargetChangeListener = null;
    private Button mBtnCel;
    private Button mBtnSure;
    private String[] contents = new String[]{"附近", "1km", "3km", "5km", "10km"};
    private String[] distances = new String[]{"500", "1000", "3000", "5000", "10000"};


    public ChooseTargetWheel(Activity context) {
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
        parentView = layoutInflater.inflate(R.layout.choose_target_layout, null);
        targetWheel = (WheelPicker) parentView.findViewById(R.id.target_wheel);
        dialogTitle = (TextView) parentView.findViewById(R.id.dialog_title);
        mBtnCel = (Button) parentView.findViewById(R.id.cancel_button);
        mBtnSure = (Button) parentView.findViewById(R.id.confirm_button);
        mBtnCel.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        targetWheel.setIndicatorSize(5);
        targetWheel.setData(Arrays.asList(contents));
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

    public void show(View v) {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void setTitle(String title) {
        dialogTitle.setText(title);
    }

    public void setSelectByDistance(String distance) {
        targetWheel.setSelectedItemPosition(0);
        for (int i = 0; i < distances.length; i++) {
            if (distances[i].equals(distance)) {
                targetWheel.setSelectedItemPosition(i);
            }
        }
    }

    public String getContentByDistance(String distance){
        for (int i = 0; i < distances.length; i++) {
            if (distances[i].equals(distance)) {
                return contents[i];
            }
        }
        return contents[0];
    }

    public void confirm() {
        if (onTargetChangeListener != null) {
            int index = targetWheel.getCurrentItemPosition();
            onTargetChangeListener.onTargetChange(contents[index], distances[index]);
        }
        cancel();
    }

    public void cancel() {
        popupWindow.dismiss();
    }

    public void setOnTargetChangeListener(OnTargetChangeListener onTargetChangeListener) {
        this.onTargetChangeListener = onTargetChangeListener;
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

    public interface OnTargetChangeListener {
        void onTargetChange(String content, String distance);
    }
}