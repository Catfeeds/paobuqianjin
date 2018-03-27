package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/3/27.
 */

public class MessageContentBundleData implements Parcelable {

    private ArrayList<MessageContentResponse.DataBeanX.DataBean> messageContentBundleData;

    public MessageContentBundleData(ArrayList<MessageContentResponse.DataBeanX.DataBean> data) {
        super();
        messageContentBundleData = new ArrayList<>();
        messageContentBundleData = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(messageContentBundleData);
    }

    public static final Parcelable.Creator<MessageContentBundleData> CREATOR = new Parcelable.Creator<MessageContentBundleData>() {
        @Override
        public MessageContentBundleData createFromParcel(Parcel parcel) {
            return new MessageContentBundleData(parcel.readArrayList(MessageContentResponse.DataBeanX.DataBean.class.getClassLoader()));
        }

        @Override
        public MessageContentBundleData[] newArray(int i) {
            return new MessageContentBundleData[i];
        }
    };

    public ArrayList<MessageContentResponse.DataBeanX.DataBean> getMessageContentBundleData() {
        return messageContentBundleData;
    }

}
