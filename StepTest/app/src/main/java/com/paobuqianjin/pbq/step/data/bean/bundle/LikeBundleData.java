package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/3/5.
 */

public class LikeBundleData implements Parcelable {
    private ArrayList<DynamicLikeListResponse.DataBeanX.DataBean> likeData;

    public LikeBundleData(ArrayList<DynamicLikeListResponse.DataBeanX.DataBean> data) {
        super();
        likeData = new ArrayList<>();
        likeData = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(likeData);
    }

    public static final Parcelable.Creator<LikeBundleData> CREATOR = new Creator<LikeBundleData>() {
        @Override
        public LikeBundleData createFromParcel(Parcel parcel) {
            return new LikeBundleData(parcel.readArrayList(DynamicLikeListResponse.DataBeanX.DataBean.class.getClassLoader()));
        }

        @Override
        public LikeBundleData[] newArray(int i) {
            return new LikeBundleData[i];
        }
    };

    public ArrayList<DynamicLikeListResponse.DataBeanX.DataBean> getLikeData() {
        return likeData;
    }

}
