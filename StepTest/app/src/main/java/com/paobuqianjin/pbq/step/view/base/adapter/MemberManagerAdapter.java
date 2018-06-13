package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleMemberManagerFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by pbq on 2017/12/18.
 */

public class MemberManagerAdapter extends RecyclerView.Adapter<MemberManagerAdapter.MemberViewHolder> {
    private final static String TAG = MemberManagerAdapter.class.getSimpleName();
    @Bind(R.id.member_list_recycler)
    RecyclerView memberListRecycler;
    private Activity mContext;
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
    private CircleMemberManagerFragment.OpCallBackInterface opCallBackInterface;

    public static ArrayList toOneList(ArrayList mainAdmin, ArrayList admin) {
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

    public MemberManagerAdapter(Activity context, ArrayList mainAdmin, ArrayList admin, CircleMemberManagerFragment.OpCallBackInterface opCallBackInterface) {
        mContext = context;
        mData = mainAdmin;
        this.opCallBackInterface = opCallBackInterface;
    }

    public MemberManagerAdapter(Activity context, ArrayList memberNormal, CircleMemberManagerFragment.OpCallBackInterface opCallBackInterface) {
        mContext = context;
        mData = memberNormal;
        this.opCallBackInterface = opCallBackInterface;
    }

/*    public void setDefaultValue(int defaultValue, int spanType) {
        this.defaultValue = defaultValue;
        this.spanType = spanType;
    }*/

    //数据与界面进行绑定
    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(MemberViewHolder holder, int position) {
        holder.memberListRecycler.setLayoutManager(holder.layoutManager);
/*        holder.layoutManager.scrollToPosition(1);
        holder.layoutManager.setStackFromEnd(true);*/
        holder.memberListRecycler.setAdapter(new CircleMemberBarAdapter(mContext, mData.get(position), opCallBackInterface));
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_member_manager_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mData != null) {
            size = mData.size();
        }
        return size;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.member_list_recycler)
        RecyclerView memberListRecycler;
        LinearLayoutManager layoutManager;

        public MemberViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            memberListRecycler = (RecyclerView) view.findViewById(R.id.member_list_recycler);
        }
    }

    /*@Override
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

        private void initView() {
            
        }
    }

    class MemberOtherAdminViewHolder extends RecyclerView.ViewHolder {
        private int viewType;

        public MemberOtherAdminViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
        }

        private void initView() {

        }
    }

    class MemberNoAdminViewHolder extends RecyclerView.ViewHolder {
        private int viewType;

        public MemberNoAdminViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
        }

        private void initView() {

        }
    }*/
}
