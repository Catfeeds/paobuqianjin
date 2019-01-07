package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TrSugResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2019/1/7.
 */

public class ExtrSuggestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<?> data;

    public ExtrSuggestAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExTrViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_tr_su_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position) instanceof TrSugResponse.DataBean) {
            ((ExTrViewHolder) holder).trName.setText(((TrSugResponse.DataBean) data.get(position)).getName());
            Presenter.getInstance(context).getPlaceErrorImage(((ExTrViewHolder) holder).ico,
                    ((TrSugResponse.DataBean) data.get(position)).getLogo(), R.drawable.null_bitmap, R.drawable.null_bitmap);
            ((ExTrViewHolder) holder).first.setText("首重:" + ((TrSugResponse.DataBean) data.get(position)).getFirst_weight() + "元"
                    + "(" + ((TrSugResponse.DataBean) data.get(position)).getArea() + "KG)内");
            ((ExTrViewHolder) holder).alter.setText("续重:" + ((TrSugResponse.DataBean) data.get(position)).getRenew_weight() + "元/KG");
        }
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public static class ExTrViewHolder extends RecyclerView.ViewHolder {
        ImageView ico;
        TextView trName;
        TextView first;
        TextView alter;

        public ExTrViewHolder(View view) {
            super(view);
            ico = (ImageView) view.findViewById(R.id.ico);
            trName = (TextView) view.findViewById(R.id.tr_name);
            first = (TextView) view.findViewById(R.id.first);
            alter = (TextView) view.findViewById(R.id.alter);
        }
    }
}
