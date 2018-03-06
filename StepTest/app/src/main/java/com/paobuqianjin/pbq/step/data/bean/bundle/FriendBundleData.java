package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/3/6.
 */

public class FriendBundleData implements Parcelable {
    private ArrayList<UserFriendResponse.DataBeanX.DataBean> friendData;

    public FriendBundleData(ArrayList<UserFriendResponse.DataBeanX.DataBean> data) {
        super();
        friendData = new ArrayList<>();
        friendData = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(friendData);
    }

    public static final Parcelable.Creator<FriendBundleData> CREATOR = new Creator<FriendBundleData>() {
        @Override
        public FriendBundleData createFromParcel(Parcel parcel) {
            return new FriendBundleData(parcel.readArrayList(UserFriendResponse.DataBeanX.DataBean.class.getClassLoader()));
        }

        @Override
        public FriendBundleData[] newArray(int i) {
            return new FriendBundleData[i];
        }
    };

    public ArrayList<UserFriendResponse.DataBeanX.DataBean> getFriendData() {
        return friendData;
    }

}
