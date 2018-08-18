package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MultAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/7/31.
 */

public class MultAccountAdapter extends RecyclerView.Adapter<MultAccountAdapter.MultAccountViewHolder> {
    private final static String TAG = MultAccountAdapter.class.getSimpleName();
    private Context context;
    private List<?> mData;

    public MultAccountAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(MultAccountViewHolder holder, int position) {
        LocalLog.d(TAG, "onBindViewHolder() enter");
        if (mData.get(position) instanceof UserInfoResponse.DataBean) {
            Presenter.getInstance(context).getPlaceErrorImage(holder.accountIcon, ((UserInfoResponse.DataBean) mData.get(position)).getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.dearName.setText(((UserInfoResponse.DataBean) mData.get(position)).getNickname());
            holder.pbNo.setText("跑步钱进号:" + ((UserInfoResponse.DataBean) mData.get(position)).getNo());
            if (((UserInfoResponse.DataBean) mData.get(position)).getNo() != null
                    && ((UserInfoResponse.DataBean) mData.get(position)).getNo().equals(Presenter.getInstance(context).getNo())) {
                holder.checkOut.setVisibility(View.VISIBLE);
            } else {
                holder.checkOut.setVisibility(View.GONE);
            }
        } else if (mData.get(position) instanceof MultAccountResponse.DataBean) {
            LocalLog.d(TAG, "icon =" + ((MultAccountResponse.DataBean) mData.get(position)).getAvatar());
            Presenter.getInstance(context).getPlaceErrorImage(holder.accountIcon, ((MultAccountResponse.DataBean) mData.get(position)).getAvatar(),
                    R.drawable.default_head_ico, R.drawable.default_head_ico);
            holder.dearName.setText(((MultAccountResponse.DataBean) mData.get(position)).getNickname());
            holder.pbNo.setText("跑步钱进号:" + ((MultAccountResponse.DataBean) mData.get(position)).getNo());
            if (((MultAccountResponse.DataBean) mData.get(position)).getNo() != null
                    && ((MultAccountResponse.DataBean) mData.get(position)).getNo().equals(Presenter.getInstance(context).getNo())) {
                holder.checkOut.setVisibility(View.VISIBLE);
            } else {
                holder.checkOut.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public MultAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultAccountViewHolder(LayoutInflater.from(context).inflate(R.layout.mult_account_item, parent, false));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class MultAccountViewHolder extends RecyclerView.ViewHolder {
        CircleImageView accountIcon;
        TextView dearName;
        TextView pbNo;
        ImageView checkOut;

        MultAccountViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            accountIcon = (CircleImageView) view.findViewById(R.id.account_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            pbNo = (TextView) view.findViewById(R.id.pb_no);
            checkOut = (ImageView) view.findViewById(R.id.check_out);
        }
    }
}
