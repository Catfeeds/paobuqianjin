package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/4/28.
 */

public class GoodImageData implements Parcelable {
    private ArrayList<SponsorDetailResponse.DataBean.EnvironmentImgsBean> goodsImgsBeans;

    public GoodImageData(ArrayList<SponsorDetailResponse.DataBean.EnvironmentImgsBean> data) {
        super();
        goodsImgsBeans = new ArrayList<>();
        goodsImgsBeans = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(goodsImgsBeans);
    }

    public static final Parcelable.Creator<GoodImageData> CREATOR = new Creator<GoodImageData>() {
        @Override
        public GoodImageData createFromParcel(Parcel parcel) {
            return new GoodImageData(parcel.readArrayList(SponsorDetailResponse.DataBean.EnvironmentImgsBean.class.getClassLoader()));
        }

        @Override
        public GoodImageData[] newArray(int i) {
            return new GoodImageData[i];
        }
    };

    public ArrayList<SponsorDetailResponse.DataBean.EnvironmentImgsBean> getGoodsImgsBeans() {
        return goodsImgsBeans;
    }

}
