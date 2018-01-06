package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;


import java.util.ArrayList;

/**
 * Created by pbq on 2017/12/28.
 */

public class MyCreateCircleBundleData implements Parcelable {
    private ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> myCreateCircleData;

    public MyCreateCircleBundleData(ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> data) {
        super();
        myCreateCircleData = new ArrayList<>();
        myCreateCircleData = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(myCreateCircleData);
    }

    public static final Parcelable.Creator<MyCreateCircleBundleData> CREATOR = new Creator<MyCreateCircleBundleData>() {
        @Override
        public MyCreateCircleBundleData createFromParcel(Parcel parcel) {
            return new MyCreateCircleBundleData(parcel.readArrayList(MyCreateCircleResponse
                    .DataBeanX
                    .DataBean
                    .class.getClassLoader()));
        }

        @Override
        public MyCreateCircleBundleData[] newArray(int i) {
            return new MyCreateCircleBundleData[i];
        }
    };

    public ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> getMyCreateCircleData() {
        return myCreateCircleData;
    }
}
