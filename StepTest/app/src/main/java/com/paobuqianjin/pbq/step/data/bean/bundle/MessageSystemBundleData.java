package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MessageSystemResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/3/27.
 */

public class MessageSystemBundleData implements Parcelable {
    private ArrayList<MessageSystemResponse.DataBeanX.DataBean> messageSystemData;

    public MessageSystemBundleData(ArrayList<MessageSystemResponse.DataBeanX.DataBean> data) {
        super();
        messageSystemData = new ArrayList<>();
        messageSystemData = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(messageSystemData);
    }

    public static final Parcelable.Creator<MessageSystemBundleData> CREATOR = new Parcelable.Creator<MessageSystemBundleData>() {
        @Override
        public MessageSystemBundleData createFromParcel(Parcel parcel) {
            return new MessageSystemBundleData(parcel.readArrayList(MessageSystemResponse.DataBeanX.DataBean.class.getClassLoader()));
        }

        @Override
        public MessageSystemBundleData[] newArray(int i) {
            return new MessageSystemBundleData[i];
        }
    };

    public ArrayList<MessageSystemResponse.DataBeanX.DataBean> getMessageSystemData() {
        return messageSystemData;
    }

}
