package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleMemberManagerFragment;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/19.
 */

public class CircleMemberBarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = CircleMemberBarAdapter.class.getSimpleName();

    private int DEFAULT_COUNT = 2;
    private CircleMemberResponse.DataBeanX.DataBean mData;
    private Activity context;
    private CircleMemberManagerFragment.OpCallBackInterface opCallBackInterface;
    private CircleMemberViewHolder circleMemberViewHolder;
    private CircleMemberRightViewHolder circleMemberRightViewHolder;

    public CircleMemberBarAdapter(Activity context, CircleMemberResponse.DataBeanX.DataBean data, CircleMemberManagerFragment.OpCallBackInterface opCallBackInterface) {
        mData = data;
        this.context = context;
        this.opCallBackInterface = opCallBackInterface;
    }


    public interface AdapterCallInterface {
        public void call();
    }

    private AdapterCallInterface adapterCallInterface = new AdapterCallInterface() {
        @Override
        public void call() {
            LocalLog.d(TAG, "call() enter");
            if (circleMemberViewHolder != null) {
                if (circleMemberViewHolder.selectUserIcon.getVisibility() == View.GONE && mData.getIs_admin() != 2) {
                    circleMemberViewHolder.selectUserIcon.setVisibility(View.VISIBLE);
                    circleMemberViewHolder.selectUserIcon.setOnClickListener(circleMemberViewHolder.onClickListener);
                } else if (circleMemberViewHolder.selectUserIcon.getVisibility() == View.VISIBLE && mData.getIs_admin() != 2) {
                    circleMemberViewHolder.selectUserIcon.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
           /* Presenter.getInstance(context).getPlaceErrorImage(((CircleMemberViewHolder) holder).memberHeadIconInCircle, mData.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);*/
            LoadBitmap.glideLoad(context, ((CircleMemberViewHolder) holder).memberHeadIconInCircle, mData.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            LocalLog.d(TAG, "" + mData.toString());

            if ("".equals(mData.getCirclenickname())) {
                ((CircleMemberViewHolder) holder).memberNameInCircle.setText(mData.getNickname());
            } else {
                ((CircleMemberViewHolder) holder).memberNameInCircle.setText(mData.getCirclenickname());
            }
            /*if (mData.getVip() == 1) {
                ((CircleMemberViewHolder) holder).vipFlg.setVisibility(View.VISIBLE);
            }*/
            if (mData.getIs_admin() == 2) {
                ((CircleMemberViewHolder) holder).adminHead.setText("主管理员");
                ((CircleMemberViewHolder) holder).adminHead.setBackgroundColor(ContextCompat.getColor(context, R.color.color_ffc400));
            } else if (mData.getIs_admin() == 1) {
                ((CircleMemberViewHolder) holder).adminHead.setText("管理员");
                ((CircleMemberViewHolder) holder).adminHead.setBackgroundColor(ContextCompat.getColor(context, R.color.color_6c71c4));
            } else if (mData.getIs_admin() == 0) {

            }
        } else if (position == 1) {
            if (mData.getIs_admin() == 2) {
                ((CircleMemberRightViewHolder) holder).btDeleteAdmin.setVisibility(View.GONE);
            } else if (mData.getIs_admin() == 1) {
                ((CircleMemberRightViewHolder) holder).btDeleteAdmin.setText("移除管理员权限");
            } else if (mData.getIs_admin() == 0) {
                ((CircleMemberRightViewHolder) holder).btDeleteAdmin.setText("设为管理员");
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 0) {
            circleMemberViewHolder = new CircleMemberViewHolder(LayoutInflater.from(context).inflate(R.layout.circle_member_message_item, parent, false), viewType);
            holder = circleMemberViewHolder;
        } else if (viewType == 1) {
            circleMemberRightViewHolder = new CircleMemberRightViewHolder(LayoutInflater.from(context).inflate(R.layout.right_bar_member_style_a, parent, false), viewType);
            holder = circleMemberRightViewHolder;
        }
        if (opCallBackInterface != null) {
            opCallBackInterface.opMemberIntoOut(adapterCallInterface);
        }
        return holder;
    }

    @Override
    public int getItemCount() {
        return DEFAULT_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        if (position == 0) {
            viewType = 0;
        } else if (position == 1) {
            viewType = 1;
        }
        return viewType;
    }

    public class CircleMemberViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.select_user_icon)
        ImageView selectUserIcon;
        @Bind(R.id.member_head_icon_in_circle)
        CircleImageView memberHeadIconInCircle;
        @Bind(R.id.member_name_in_circle)
        EditText memberNameInCircle;
        @Bind(R.id.admin_head)
        TextView adminHead;
        @Bind(R.id.circle_manager_list_id)
        RelativeLayout circleManagerListId;
        Boolean isSelect = false;
        int viewType;
        @Bind(R.id.vip_flg)
        ImageView vipFlg;


        public CircleMemberViewHolder(View view, int viewType) {
            super(view);
            initView(view);
            this.viewType = viewType;
        }

        private void initView(View view) {
            selectUserIcon = (ImageView) view.findViewById(R.id.select_user_icon);
            memberHeadIconInCircle = (CircleImageView) view.findViewById(R.id.member_head_icon_in_circle);
            memberNameInCircle = (EditText) view.findViewById(R.id.member_name_in_circle);
            circleManagerListId = (RelativeLayout) view.findViewById(R.id.circle_manager_list_id);
            //circleManagerListId.setOnLongClickListener(onLongClickListener);
            adminHead = (TextView) view.findViewById(R.id.admin_head);
            vipFlg = (ImageView) view.findViewById(R.id.vip_flg);
        }

        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_manager_list_id:
                        LocalLog.d(TAG, "长点击事件");
                        if (selectUserIcon.getVisibility() == View.GONE && mData.getIs_admin() != 2) {
                            selectUserIcon.setVisibility(View.VISIBLE);
                            selectUserIcon.setOnClickListener(onClickListener);
                        }
                        opCallBackInterface.onLongClick();
                        break;
                }
                return true;
            }
        };
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.select_user_icon:
                        if (!isSelect) {
                            selectUserIcon.setImageResource(R.drawable.selected_icon);
                            isSelect = true;
                            LocalLog.d(TAG, "删除");
                            opCallBackInterface.opMemberOutInto(mData.getId());
                        } else {
                            selectUserIcon.setImageDrawable(null);
                            isSelect = false;
                            LocalLog.d(TAG, "取消删除");
                            opCallBackInterface.opMemberOutInto(mData.getId());
                        }
                        break;
                }
            }
        };
    }

    public class CircleMemberRightViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bar_modify_dear)
        Button barModifyDear;
        @Bind(R.id.bt_delete_admin)
        Button btDeleteAdmin;
        int viewType;

        public CircleMemberRightViewHolder(View view, int viewType) {
            super(view);
            initView(view);
            this.viewType = viewType;
        }

        private void initView(View view) {
            barModifyDear = (Button) view.findViewById(R.id.bar_modify_dear);
            barModifyDear.setOnClickListener(onClickListener);
            btDeleteAdmin = (Button) view.findViewById(R.id.bt_delete_admin);
            btDeleteAdmin.setOnClickListener(onClickListener);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bar_modify_dear:
                        LocalLog.d(TAG, "修改昵称");
                        opCallBackInterface.opModifyDearName(mData.getId());
                        break;
                    case R.id.bt_delete_admin:
                        LocalLog.d(TAG, "设为管理员或者移除管理权限");
                        switch (btDeleteAdmin.getText().toString()) {
                            case "移除管理员权限":
                                LocalLog.d(TAG, "移除管理员权限");
                                opCallBackInterface.addDeleteAdmin(mData.getId());
                                break;
                            case "设为管理员":
                                opCallBackInterface.addDeleteAdmin(mData.getId());
                                LocalLog.d(TAG, "设为管理员");
                                break;
                        }
                        break;
                }
            }
        };
    }
}
