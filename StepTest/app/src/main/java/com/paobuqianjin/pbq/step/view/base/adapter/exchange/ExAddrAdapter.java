package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExAddResponse;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/12/30.
 */

public class ExAddrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<?> data;

    public ExAddrAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExAddressViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_address_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position) instanceof ExAddResponse.DataBeanX.DataBean) {
            ((ExAddressViewHolder) holder).phoneName.setText(((ExAddResponse.DataBeanX.DataBean) data.get(position)).getConsigner());
            ((ExAddressViewHolder) holder).phone.setText(((ExAddResponse.DataBeanX.DataBean) data.get(position)).getMobile());
            ((ExAddressViewHolder) holder).addressTo.setText(((ExAddResponse.DataBeanX.DataBean) data.get(position)).getAddr() +
                    ((ExAddResponse.DataBeanX.DataBean) data.get(position)).getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public static class ExAddressViewHolder extends RecyclerView.ViewHolder {
        TextView phoneName;
        TextView phone;
        TextView addressTo;
        ImageView editAddr;

        public ExAddressViewHolder(View view) {
            super(view);
            phoneName = (TextView) view.findViewById(R.id.phone_name);
            phone = (TextView) view.findViewById(R.id.phone);
            addressTo = (TextView) view.findViewById(R.id.address_to);
        }
    }
}
