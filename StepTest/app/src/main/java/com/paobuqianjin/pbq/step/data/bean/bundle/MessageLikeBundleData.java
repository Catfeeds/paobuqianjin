package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageLikeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/3/27.
 */

public class MessageLikeBundleData implements Parcelable {
    private ArrayList<MessageLikeResponse.DataBeanX.DataBean> messageLikeBundleData;

    public MessageLikeBundleData(ArrayList<MessageLikeResponse.DataBeanX.DataBean> data) {
        super();
        messageLikeBundleData = new ArrayList<>();
        messageLikeBundleData = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(messageLikeBundleData);
    }

    public static final Parcelable.Creator<MessageLikeBundleData> CREATOR = new Parcelable.Creator<MessageLikeBundleData>() {
        @Override
        public MessageLikeBundleData createFromParcel(Parcel parcel) {
            return new MessageLikeBundleData(parcel.readArrayList(MessageLikeResponse.DataBeanX.DataBean.class.getClassLoader()));
        }

        @Override
        public MessageLikeBundleData[] newArray(int i) {
            return new MessageLikeBundleData[i];
        }
    };

    public ArrayList<MessageLikeResponse.DataBeanX.DataBean> getMessageLikeBundleData() {
        return messageLikeBundleData;
    }

}
