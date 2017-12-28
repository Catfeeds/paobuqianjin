package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2017/12/28.
 */

public class ChoiceBundleData implements Parcelable {
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> choiceCircleData;

    public ChoiceBundleData(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        super();
        choiceCircleData = new ArrayList<>();
        choiceCircleData = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(choiceCircleData);
    }

    public static final Parcelable.Creator<ChoiceBundleData> CREATOR = new Creator<ChoiceBundleData>() {
        @Override
        public ChoiceBundleData createFromParcel(Parcel parcel) {
            return new ChoiceBundleData(parcel.readArrayList(ChoiceCircleResponse.DataBeanX.DataBean.class.getClassLoader()));
        }

        @Override
        public ChoiceBundleData[] newArray(int i) {
            return new ChoiceBundleData[i];
        }
    };

    public ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> getChoiceCircleData() {
        return choiceCircleData;
    }
}
