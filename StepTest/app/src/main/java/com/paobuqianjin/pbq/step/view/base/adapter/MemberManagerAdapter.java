package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2017/12/18.
 */

public class MemberManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = MemberManagerAdapter.class.getSimpleName();
    private Context mContext;
    private int defaultValue = 10;
    private int spanType = 0;

    //普通用户
    private final static int TYPE_MAIN_ADMIN = 0;
    //管理员
    private final static int TYPE_OTHER_ADMIN = 1;
    //主管理员
    private final static int TYPE_NORMAL_MEM = 2;

    private final static int TYPE_NORMAL_MEM_DEFAULT = 3;

    public MemberManagerAdapter(Context context) {
        mContext = context;
    }

    public void setDefaultValue(int defaultValue, int spanType) {
        this.defaultValue = defaultValue;
        this.spanType = spanType;

    }

    //数据与界面进行绑定
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        LocalLog.d(TAG, "");
        switch (viewType) {
            case TYPE_MAIN_ADMIN:
                LocalLog.d(TAG, "加载主管理员描述！");
                holder = new MemberMainAdminViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_a,
                        null, false));
                break;
            case TYPE_OTHER_ADMIN:
                LocalLog.d(TAG, "加载普通管理员描述");
                holder = new MemberOtherAdminViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_b,
                        null, false));
                break;
            case TYPE_NORMAL_MEM:
                LocalLog.d(TAG, "加载普通成员描述");
                holder = new MemberNoAdminViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_c,
                        null, false));
                break;
            case TYPE_NORMAL_MEM_DEFAULT:
                break;
            default:
                holder = null;
        }
        return holder;
    }

    @Override
    public int getItemCount() {
        return defaultValue;
    }

    @Override
    public int getItemViewType(int position) {
        LocalLog.d(TAG, "getItemViewType() enter position " + position);
        //TODO 根据[postion]对应的数据流判定数据类型
        /*Data[position].getType() == ?
        * */
        if (spanType == 0) {
            if (position == 0) {
                return TYPE_MAIN_ADMIN;
            } else if (position == 1) {
                return TYPE_OTHER_ADMIN;
            }
            return TYPE_NORMAL_MEM;
        } else if (spanType == 1) {
            return TYPE_NORMAL_MEM;
        }
        return TYPE_NORMAL_MEM;
    }

    class MemberMainAdminViewHolder extends RecyclerView.ViewHolder {
        public MemberMainAdminViewHolder(View view) {
            super(view);
        }
    }

    class MemberOtherAdminViewHolder extends RecyclerView.ViewHolder {
        public MemberOtherAdminViewHolder(View view) {
            super(view);
        }
    }

    class MemberNoAdminViewHolder extends RecyclerView.ViewHolder {
        public MemberNoAdminViewHolder(View view) {
            super(view);
        }
    }
}
