package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2017/12/18.
 */

public class MemberManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = MemberManagerAdapter.class.getSimpleName();
    private Context mContext;
    private int defaultValue = 10;
    private int spanType = 0;


    //普通用户
    private final static int TYPE_NORMAL = 0;
    //管理员
    private final static int TYPE_OTHER_ADMIN = 1;
    //主管理员
    private final static int TYPE_MAIN_MEM = 2;

    private final static int TYPE_NORMAL_MEM_DEFAULT = 3;

    private ArrayList<CircleMemberResponse.DataBeanX.DataBean> mData;

    private ArrayList toOneList(ArrayList mainAdmin, ArrayList admin) {
        ArrayList adminList = new ArrayList();
        if (mainAdmin != null) {
            for (int i = 0; i < mainAdmin.size(); i++) {
                adminList.add(mainAdmin.get(i));
            }
        }
        if (admin != null) {
            for (int i = 0; i < admin.size(); i++) {
                adminList.add(admin.get(i));
            }
        }
        return adminList;
    }

    public MemberManagerAdapter(Context context, ArrayList mainAdmin, ArrayList admin) {
        mContext = context;
        mData = toOneList(mainAdmin, admin);

    }

    public MemberManagerAdapter(Context context, ArrayList memberNormal) {
        mContext = context;
        mData = memberNormal;
    }

/*    public void setDefaultValue(int defaultValue, int spanType) {
        this.defaultValue = defaultValue;
        this.spanType = spanType;
    }*/

    //数据与界面进行绑定
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_MAIN_MEM:
                LocalLog.d(TAG, "加载主管理员描述！");
                holder = new MemberMainAdminViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_a,
                        null, false), viewType);
                break;
            case TYPE_OTHER_ADMIN:
                LocalLog.d(TAG, "加载普通管理员描述");
                holder = new MemberOtherAdminViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_b,
                        null, false), viewType);
                break;
            case TYPE_NORMAL:
                LocalLog.d(TAG, "加载普通成员描述");
                holder = new MemberNoAdminViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_c,
                        null, false), viewType);
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
        int size = 0;
        if (mData != null) {
            size = mData.size();
            LocalLog.d(TAG, "size = " + size);
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        LocalLog.d(TAG, "getItemViewType() enter position " + position);
        //TODO 根据[postion]对应的数据流判定数据类型
        if (mData != null) {
            if (mData.get(position) != null) {
                if (mData.get(position).getIs_admin() == 2) {
                    LocalLog.d(TAG, "主管理员");
                    return TYPE_MAIN_MEM;
                } else if (mData.get(position).getIs_admin() == 1) {
                    LocalLog.d(TAG, "管理员");
                    return TYPE_OTHER_ADMIN;
                } else if (mData.get(position).getIs_admin() == 0) {
                    LocalLog.d(TAG, "普通成员");
                    return TYPE_NORMAL;
                }
            }
        }
        return -1;
    }

    class MemberMainAdminViewHolder extends RecyclerView.ViewHolder {
        private int viewType;

        public MemberMainAdminViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
        }
    }

    class MemberOtherAdminViewHolder extends RecyclerView.ViewHolder {
        private int viewType;

        public MemberOtherAdminViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
        }
    }

    class MemberNoAdminViewHolder extends RecyclerView.ViewHolder {
        private int viewType;

        public MemberNoAdminViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
        }
    }
}
