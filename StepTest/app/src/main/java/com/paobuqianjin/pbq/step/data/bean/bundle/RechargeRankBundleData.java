package com.paobuqianjin.pbq.step.data.bean.bundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;

import java.util.ArrayList;

/**
 * Created by pbq on 2018/1/5.
 */

public class RechargeRankBundleData implements Parcelable {
    private ArrayList<ReChargeRankResponse.DataBeanX.DataBean> rechargeRankData;

    public RechargeRankBundleData(ArrayList<ReChargeRankResponse.DataBeanX.DataBean> data) {
        super();
        rechargeRankData = new ArrayList<>();
        rechargeRankData = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(rechargeRankData);
    }

    public static final Parcelable.Creator<RechargeRankBundleData> CREATOR =
            new Creator<RechargeRankBundleData>() {
                @Override
                public RechargeRankBundleData createFromParcel(Parcel parcel) {
                    return new RechargeRankBundleData(parcel.readArrayList(
                            ReChargeRankResponse.DataBeanX.DataBean.class.getClassLoader()
                    ));
                }

                @Override
                public RechargeRankBundleData[] newArray(int i) {
                    return new RechargeRankBundleData[0];
                }
            };

    public ArrayList<ReChargeRankResponse.DataBeanX.DataBean> getRechargeRankData() {
        return rechargeRankData;
    }
}
