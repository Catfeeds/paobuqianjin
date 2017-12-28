package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleAdapter extends RecyclerView.Adapter<SearchCircleAdapter.SearchCirCleViewHolder> {
    private final static String TAG = SearchCircleAdapter.class.getSimpleName();
    private final static int defaultValue = 15;
    private Context mContext;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data;
    private ChoiceCircleResponse.DataBeanX.DataBean tmpData;

    public SearchCircleAdapter(Context context, ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public SearchCirCleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchCirCleViewHolder holder = new SearchCirCleViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.circle_kan_ban_list, parent, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(SearchCirCleViewHolder holder, int position) {
        updateCircleList(holder, position);
    }

    private void updateCircleList(SearchCirCleViewHolder holder, int position) {
        LocalLog.d(TAG, "updateCircleList() enter" + data.get(position).toString());

        tmpData = data.get(position);
        LocalLog.d(TAG, "city = " + tmpData.getCity() +
                ", name =" + tmpData.getName() + "logo url = " + tmpData.getLogo() + " ,member_number ="
                + tmpData.getMember_number());
        Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, tmpData.getLogo());
        holder.locationDescSearchList.setText(tmpData.getCity());
        holder.searchCircleDesListName.setText(tmpData.getName());
        String sAgeFormat = mContext.getResources().getString(R.string.member_number);
        String sFinalMember = String.format(sAgeFormat, tmpData.getMember_number());
        holder.searchCircleDesListNum.setText(sFinalMember);
        if (tmpData.getIs_pwd() == 1) {
            holder.lock.setVisibility(View.VISIBLE);
        }
    }

    class SearchCirCleViewHolder extends RecyclerView.ViewHolder {

        ImageView circleLogoSearch;
        TextView searchCircleDesListName;
        ImageView lock;
        TextView searchCircleDesListNum;
        TextView locationDescSearchList;
        Button joinIn;

        public SearchCirCleViewHolder(View view) {
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
        }
    }


}
