package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExWantResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2019/1/7.
 */

public class ExWantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<?> data;

    public ExWantAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExWantViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_want_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position) instanceof ExWantResponse.DataBeanX.DataBean) {
            if (((ExWantResponse.DataBeanX.DataBean) data.get(position)).getIs_edit() == 1) {
                ((ExWantViewHolder) holder).itemSelect.setVisibility(View.VISIBLE);
            } else {
                ((ExWantViewHolder) holder).itemSelect.setVisibility(View.GONE);
            }

            Presenter.getInstance(context).getPlaceErrorImage(((ExWantViewHolder) holder).goodPic, ((ExWantResponse.DataBeanX.DataBean) data.get(position)).getImg_url(),
                    R.drawable.null_bitmap, R.drawable.null_bitmap);
            ((ExWantViewHolder) holder).goodName.setText(((ExWantResponse.DataBeanX.DataBean) data.get(position)).getName());
            ((ExWantViewHolder) holder).wantNumber.setText(((ExWantResponse.DataBeanX.DataBean) data.get(position)).getNumber() + " 人想要");
            try {
                if (Integer.parseInt(((ExWantResponse.DataBeanX.DataBean) data.get(position)).getCredit()) > 0)
                    ((ExWantViewHolder) holder).stepDollar.setText(((ExWantResponse.DataBeanX.DataBean) data.get(position)).getCredit() + "步币");
                if (Float.parseFloat(((ExWantResponse.DataBeanX.DataBean) data.get(position)).getOld_price()) > 0.0f)
                    ((ExWantViewHolder) holder).srcPrice.setText("原价 " + ((ExWantResponse.DataBeanX.DataBean) data.get(position)).getOld_price() + "元");
                ((ExWantViewHolder) holder).srcPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ExWantViewHolder extends RecyclerView.ViewHolder {
        ImageView itemSelect;
        CircularImageView goodPic;
        TextView goodName;
        TextView wantNumber;
        TextView stepDollar;
        TextView srcPrice;

        public ExWantViewHolder(View view) {
            super(view);
            itemSelect = (ImageView) view.findViewById(R.id.item_select);
            goodPic = (CircularImageView) view.findViewById(R.id.good_pic);
            goodName = (TextView) view.findViewById(R.id.good_name);
            wantNumber = (TextView) view.findViewById(R.id.want_number);
            stepDollar = (TextView) view.findViewById(R.id.step_dollar);
            srcPrice = (TextView) view.findViewById(R.id.src_price);
        }
    }
}
