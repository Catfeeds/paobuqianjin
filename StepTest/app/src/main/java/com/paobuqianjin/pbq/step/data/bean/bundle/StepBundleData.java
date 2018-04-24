package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/4/24.
 */

public class StepBundleData implements Parcelable {
    private ArrayList<StepRankResponse.DataBeanX.DataBean> stepRankData;

    public StepBundleData(ArrayList<StepRankResponse.DataBeanX.DataBean> data) {
        super();
        stepRankData = new ArrayList<>();
        stepRankData = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(stepRankData);
    }

    public static final Parcelable.Creator<StepBundleData> CREATOR =
            new Creator<StepBundleData>() {
                @Override
                public StepBundleData createFromParcel(Parcel parcel) {
                    return new StepBundleData(parcel.readArrayList(
                            StepRankResponse.DataBeanX.DataBean.class.getClassLoader()
                    ));
                }

                @Override
                public StepBundleData[] newArray(int i) {
                    return new StepBundleData[0];
                }
            };

    public ArrayList<StepRankResponse.DataBeanX.DataBean> getStepRankData() {
        return stepRankData;
    }
}
