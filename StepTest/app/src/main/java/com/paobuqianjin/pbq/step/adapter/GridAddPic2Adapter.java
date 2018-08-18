package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GridAddPic2Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private Context mContext;
    private int mMaxSize = 12;
    private List<SelectPicBean> mData = new ArrayList<>();

    private OnDeleteListener listener;

    public GridAddPic2Adapter(Context context, int maxSize) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mMaxSize = maxSize;
    }

    public void setData(SelectPicBean bean) {
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setDatas(List<SelectPicBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public List<SelectPicBean> getData() {
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

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        convertView = inflater.inflate(R.layout.item_add_pic_grid2, parent, false);
        holder = new ViewHolder(convertView);
        if (position == mData.size()) {
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_min_add_one));
            holder.delete.setVisibility(View.GONE);
            if (position == mMaxSize) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            holder.image.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(mData.get(position).getFileUrl())) {
                Presenter.getInstance(mContext).getImage(holder.image, mData.get(position).getImageUrl(),
                        Utils.dip2px(mContext, 100), Utils.dip2px(mContext, 100));
            } else {
                Presenter.getInstance(mContext).getImage(mData.get(position).getFileUrl(), holder.image,
                        Utils.dip2px(mContext, 100), Utils.dip2px(mContext, 100));
            }
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    if (listener != null) {
                        listener.onDelete(position);
                    }
                    GridAddPic2Adapter.this.notifyDataSetChanged();
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
