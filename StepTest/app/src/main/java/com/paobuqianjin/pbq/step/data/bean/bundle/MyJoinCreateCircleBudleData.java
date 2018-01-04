package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/1/4.
 */

public class MyJoinCreateCircleBudleData implements Parcelable{
    private ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> myJoinCircleData;

    public MyJoinCreateCircleBudleData(ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> data) {
        super();
        myJoinCircleData = new ArrayList<>();
        myJoinCircleData = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(myJoinCircleData);
    }

    public static final Parcelable.Creator<MyJoinCreateCircleBudleData> CREATOR = new Parcelable.Creator<MyJoinCreateCircleBudleData>() {
        @Override
        public MyJoinCreateCircleBudleData createFromParcel(Parcel parcel) {
            return new MyJoinCreateCircleBudleData(parcel.readArrayList(MyJoinCircleResponse
                    .DataBeanX
                    .DataBean
                    .class.getClassLoader()));
        }

        @Override
        public MyJoinCreateCircleBudleData[] newArray(int i) {
            return new MyJoinCreateCircleBudleData[i];
        }
    };

    public ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> getMyJoinCircleData() {
        return myJoinCircleData;
    }
}
