package com.paobuqianjin.pbq.step.view.base.view.wheelpicker.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.WheelPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 时间选择器
 * <p>
 */
public class WheelTimePicker extends WheelPicker {
    private long startTime, endTime;
    private String mSelectedTime;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
    List<String> data = new ArrayList<>();

    public WheelTimePicker(Context context) {
        this(context, null);
    }

    public WheelTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        startTime = calendar.getTimeInMillis();
        endTime = startTime + 1000 * 60 * 60 * 24;
        updateTimes();
        mSelectedTime = data.get(0);
        updateSelectedTime();
    }

    private void updateTimes() {
        data.clear();
        for (long i = startTime; i < endTime; i += 1000 * 60 * 30) {
            data.add(format.format(i));
        }
        data.add("23:59");
        super.setData(data);
    }

    private void updateSelectedTime() {
        LocalLog.d("---",mSelectedTime+"==="+data.get(1));
        for (int i = 0; i < data.size(); i++) {
            if (mSelectedTime.equals(data.get(i))) {
                setSelectedItemPosition(i,false);
            }
        }
    }

    @Override
    public void setData(List data) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelYearPicker");
    }

    public void setTimeFrame(long start, long end) {
        startTime = start;
        endTime = end;
        updateTimes();
        mSelectedTime = data.get(0);
        updateSelectedTime();
    }

    public String getTimeStart() {
        return format.format(startTime);
    }

    public void setTimeStart(long start) {
        startTime = start;
        updateTimes();
        mSelectedTime = getCurrentTime();
        updateSelectedTime();
    }

    public String getTimeEnd() {
        return format.format(endTime);
    }

    public void setTimeEnd(long end) {
        endTime = end;
        updateTimes();
    }

    public String getSelectedTime() {
        return mSelectedTime;
    }

    public void setSelectedTime(String time) {
        mSelectedTime = time;
        updateSelectedTime();
    }

    public String getCurrentTime() {
        return String.valueOf(getData().get(getCurrentItemPosition()));
    }
}