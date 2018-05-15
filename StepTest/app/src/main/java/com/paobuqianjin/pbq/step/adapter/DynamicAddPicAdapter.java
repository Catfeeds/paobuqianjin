package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

public class DynamicAddPicAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private int mMaxSize = 4;
    private List<ImageBean> mData = new ArrayList<>();

    private OnDeleteListener listener;

    public DynamicAddPicAdapter(Context context, int maxSize) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mMaxSize = maxSize;
    }

    public DynamicAddPicAdapter(Context context, int maxSize, List<ImageBean> mData) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mMaxSize = maxSize;
        this.mData = mData;
    }

    public void setData(ImageBean bean) {
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setDatas(List<ImageBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public List<ImageBean> getData() {
        return mData;
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.listener = listener;
    }


    public int getCount() {
        if (mData.size() >= mMaxSize) {
            return mMaxSize;
        }
        return (mData.size() + 1);
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        convertView = inflater.inflate(R.layout.item_add_pic_grid, parent, false);
        holder = new ViewHolder(convertView);
        if (position == mData.size()) {
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.add_pic));
            holder.delete.setVisibility(View.GONE);
            if (position == mMaxSize) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            holder.image.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mData.get(position).getImagePath()).into(holder.image);
//            Presenter.getInstance(mContext).getImage(mData.get(position).getImagePath(), holder.image);
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    if (listener != null) {
                        listener.onDelete(position);
                    }
                    DynamicAddPicAdapter.this.notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }


    public class ViewHolder {
        public ImageView image;
        public ImageView delete;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.item_grid_image);
            delete = (ImageView) view.findViewById(R.id.iv_delete);
        }
    }

    public interface OnDeleteListener {
        void onDelete(int position);
    }


}
