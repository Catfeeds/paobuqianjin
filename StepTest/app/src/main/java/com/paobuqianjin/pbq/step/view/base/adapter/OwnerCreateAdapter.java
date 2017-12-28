package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.ArrayList;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateAdapter extends RecyclerView.Adapter<OwnerCreateAdapter.OwnerCreateViewHolder> {
    private final static String TAG = OwnerCreateAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<MyHotCircleResponse.DataBeanX.DataBean> data;
    private MyHotCircleResponse.DataBeanX.DataBean tmpData;

    public OwnerCreateAdapter(Context context, ArrayList<MyHotCircleResponse.DataBeanX.DataBean> data) {
        super();
        this.data = data;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public OwnerCreateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OwnerCreateViewHolder holder = new OwnerCreateViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.circle_kan_ban_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(OwnerCreateViewHolder holder, int position) {

        updateMyCreateList(holder, position);
    }

    private void updateMyCreateList(OwnerCreateViewHolder holder, int position) {
        LocalLog.d(TAG, "updateCircleList() enter" + data.get(0).toString());

        tmpData = data.get(0);
        LocalLog.d(TAG, "city = " + tmpData.getCity() +
                ", name =" + tmpData.getName() + "logo url = " + tmpData.getLogo() + " ,member_number ="
                + tmpData.getMember_number());
        Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, tmpData.getLogo());
        holder.locationDescSearchList.setText(tmpData.getCity());
        holder.searchCircleDesListName.setText(tmpData.getName());
        String sAgeFormat = mContext.getResources().getString(R.string.member_number);
        String sFinalMember = String.format(sAgeFormat, tmpData.getMember_number());
        holder.searchCircleDesListNum.setText(sFinalMember);
    }

    class OwnerCreateViewHolder extends RecyclerView.ViewHolder {
        boolean needPass;
        ImageView circleLogoSearch;
        TextView searchCircleDesListName;
        ImageView lock;
        TextView searchCircleDesListNum;
        TextView locationDescSearchList;
        Button joinIn;

        public OwnerCreateViewHolder(View view) {
            super(view);
            init(view);
        }

        private void init(View view) {
            circleLogoSearch = (ImageView) view.findViewById(R.id.circle_logo_search);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            lock = (ImageView) view.findViewById(R.id.lock);
            searchCircleDesListNum = (TextView) view.findViewById(R.id.search_circle_des_list_num);
            locationDescSearchList = (TextView) view.findViewById(R.id.location_desc_search_list);
            joinIn = (Button) view.findViewById(R.id.join_in);
            joinIn.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.join_in:
                        LocalLog.d(TAG, "  点击加入");
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
