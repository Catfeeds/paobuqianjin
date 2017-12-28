package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2017/12/28.
 */

public class MyCircleBundleData implements Parcelable {
    private ArrayList<MyHotCircleResponse.DataBeanX.DataBean> myCircleData;

    public MyCircleBundleData(ArrayList<MyHotCircleResponse.DataBeanX.DataBean> data) {
        super();
        myCircleData = new ArrayList<>();
        myCircleData = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(myCircleData);
    }

    public static final Parcelable.Creator<MyCircleBundleData> CREATOR = new Creator<MyCircleBundleData>() {
        @Override
        public MyCircleBundleData createFromParcel(Parcel parcel) {
            return new MyCircleBundleData(parcel.readArrayList(MyHotCircleResponse
                    .DataBeanX
                    .DataBean
                    .class.getClassLoader()));
        }

        @Override
        public MyCircleBundleData[] newArray(int i) {
            return new MyCircleBundleData[i];
        }
    };

    public ArrayList<MyHotCircleResponse.DataBeanX.DataBean> getMyCircleData() {
        return myCircleData;
    }
}
