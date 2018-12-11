package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/12/6.
 */

public class GoodDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<?> data;

    public GoodDetailAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.good_small_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data.get(position) instanceof String) {
            LocalLog.d("GoodDetailAdapter",(String)data.get(position));
            Presenter.getInstance(context).getPlaceErrorImage(((ImageViewHolder) holder).goodSmallImg,
                    (String) data.get(position), R.drawable.null_bitmap, R.drawable.null_bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView goodSmallImg;

        public ImageViewHolder(View view) {
            super(view);
            goodSmallImg = (ImageView) view.findViewById(R.id.good_small_img);
        }
    }
}
