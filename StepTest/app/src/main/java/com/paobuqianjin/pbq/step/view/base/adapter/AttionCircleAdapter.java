package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/29.
 */

public class AttionCircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int defaultCount = 4;

    private Context mContext;

    public static enum ITEM_TYPE {
        ITEM_TYPE_NO_IMG,
        ITEM_TYPE_ONE_IMG,
        ITEM_TYPE_TWO_IMG,
        ITEM_TYPE_THREE_IMG
    }

    public AttionCircleAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal()) {
            return new OneOrZeroViewHodler(
                    LayoutInflater.from(mContext).inflate(R.layout.dynamic_style_one_pic_list, parent, false)
                    , 0);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_ONE_IMG.ordinal()) {
            return new OneOrZeroViewHodler(
                    LayoutInflater.from(mContext).inflate(R.layout.dynamic_style_one_pic_list, parent, false)
                    , 1);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TWO_IMG.ordinal()) {

        } else if (viewType == ITEM_TYPE.ITEM_TYPE_THREE_IMG.ordinal()) {

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    @Override
    public int getItemViewType(int position) {
        //根据图片数据条目个数判断类型
        if (position % defaultCount == 0) {
            //无图片
            return ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal();
        } else if (position % defaultCount == 1) {
            return ITEM_TYPE.ITEM_TYPE_ONE_IMG.ordinal();
        } else if (position % defaultCount == 2) {
            return ITEM_TYPE.ITEM_TYPE_TWO_IMG.ordinal();
        } else if (position % defaultCount == 3) {
            return ITEM_TYPE.ITEM_TYPE_THREE_IMG.ordinal();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public static class OneOrZeroViewHodler extends
            RecyclerView.ViewHolder {
        int viewType;
        //@Bind(R.id.dynamic_user_icon)
        CircleImageView dynamicUserIcon;
        //@Bind(R.id.dynamic_owner_rel)
        RelativeLayout dynamicOwnerRel;
        //@Bind(R.id.dynamic_content_text)
        TextView dynamicContentText;
        //@Bind(R.id.dynamic_pic_one)
        ImageView dynamicPicOne;
        //@Bind(R.id.pic_content_rel)
        RelativeLayout picContentRel;
        //@Bind(R.id.dynamic_location_city)
        TextView dynamicLocationCity;
        //@Bind(R.id.content_numbers)
        TextView contentNumbers;
        //@Bind(R.id.content_number_icon)
        ImageView contentNumberIcon;
        //@Bind(R.id.content_supports)
        TextView contentSupports;
        //@Bind(R.id.location_support_rel)
        RelativeLayout locationSupportRel;
        //@Bind(R.id.line_content)
        ImageView lineContent;
        //@Bind(R.id.first_content)
        TextView firstContent;
        //@Bind(R.id.content_des)
        RelativeLayout contentDes;
        //@Bind(R.id.line_content_list)
        ImageView lineContentList;

        public OneOrZeroViewHodler(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            initView(view, viewType);
        }

        private void initView(View view, int viewType) {
            dynamicPicOne = (ImageView) view.findViewById(R.id.dynamic_pic_one);
            switch (viewType) {
                case 0:
                    dynamicPicOne.setVisibility(View.GONE);
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    }


    public static class TwoPicViewHolder {

        public TwoPicViewHolder(View view, int viewType) {

        }

        private void initView(){
            
        }
    }
}
