package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutDearNameParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteAdminResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AdminDeleteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MemberDeleteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.DearNameModifyActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.CircleMemberBarAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.MemberManagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/18.
 */

public class CircleMemberManagerFragment extends BaseBarImageViewFragment implements CircleMemberManagerInterface {
    private final static String TAG = CircleMemberManagerFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.admin_title_line)
    TextView adminTitleLine;
    @Bind(R.id.circle_admin_span)
    RecyclerView circleAdminSpan;
    @Bind(R.id.admin_line)
    ImageView adminLine;
    @Bind(R.id.all_member_span)
    RecyclerView allMemberSpan;
    @Bind(R.id.member_span)
    BounceScrollView memberSpan;
    @Bind(R.id.delete_member_confim)
    Button deleteMemberConfim;

    private RecyclerView adminRecyclerView, normalRecyclerView;
    private LinearLayoutManager adminManager, normalManager;
    private final static String MEMBER_MANANGER_ACTION = "android.intent.action.MAMBER_MANAGER_ACTION";
    private String id;
    private final static String CIRCLE_ID = "id";
    ArrayList<CircleMemberBarAdapter.AdapterCallInterface> adapterCallInterface;
    ArrayList<String> deleteArrList;
    private static final int DEAR_NAME_MODIFY = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_member_manager_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (MEMBER_MANANGER_ACTION.equals(intent.getAction())) {
                Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
                id = bundle.getString(CIRCLE_ID, "");
                LocalLog.d(TAG, "成员管理 circleid = " + id);
                Presenter.getInstance(context).getCircleMemberAll(Integer.parseInt(id), 1, 10);
            }
        }

    }

    @Override
    public Object right() {
        return getDrawableResource(R.drawable.delete_icon);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        setToolBarListener(toolBarListener);
        adminRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.circle_admin_span);
        normalRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.all_member_span);
        adminManager = new LinearLayoutManager(getContext());
        normalManager = new LinearLayoutManager(getContext());
        adminRecyclerView.setLayoutManager(adminManager);
        normalRecyclerView.setLayoutManager(normalManager);
        barTvRight = (ImageView) viewRoot.findViewById(R.id.bar_tv_right);
        deleteMemberConfim = (Button) viewRoot.findViewById(R.id.delete_member_confim);
        deleteMemberConfim.setOnClickListener(onClickListener);
        adapterCallInterface = new ArrayList<>();
        deleteArrList = new ArrayList<>();
    }

    @Override
    protected String title() {
        return "成员管理";
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.delete_member_confim:
                    LocalLog.d(TAG, "删除成员!");
                    String idStr = "";
                    for (int i = 0; i < deleteArrList.size(); i++) {
                        if (i == (deleteArrList.size() - 1)) {
                            idStr += deleteArrList.get(i);
                        } else {
                            idStr += deleteArrList.get(i) + ",";
                        }
                    }
                    Presenter.getInstance(getContext()).deleteCircleMember(idStr);
                    break;
            }
        }
    };

    private ToolBarListener toolBarListener = new ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "点击删除成员");
            deleteArrList.clear();
            for (int i = 0; i < adapterCallInterface.size(); i++) {
                adapterCallInterface.get(i).call();
            }
            if (deleteMemberConfim.getVisibility() == View.GONE) {
                deleteMemberConfim.setVisibility(View.VISIBLE);
            } else if (deleteMemberConfim.getVisibility() == View.VISIBLE) {
                deleteMemberConfim.setVisibility(View.GONE);
            }
        }
    };

    private ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] getAdminList(CircleMemberResponse circleMemberResponse) {
        LocalLog.d(TAG, "getAdminList() enter");
        ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] data = new ArrayList[3];
        data[0] = new ArrayList<>();
        data[1] = new ArrayList<>();
        data[2] = new ArrayList<>();
        for (int i = 0; i < circleMemberResponse.getData().getData().size(); i++) {
            if (circleMemberResponse.getData().getData().get(i).getIs_admin() == 2) {
                LocalLog.d(TAG, "主管理员");

                data[0].add(circleMemberResponse.getData().getData().get(i));
            } else if (circleMemberResponse.getData().getData().get(i).getIs_admin() == 1) {
                LocalLog.d(TAG, "普通管理员");

                data[1].add(circleMemberResponse.getData().getData().get(i));
            } else if (circleMemberResponse.getData().getData().get(i).getIs_admin() == 0) {
                LocalLog.d(TAG, "普通成员");
                data[2].add(circleMemberResponse.getData().getData().get(i));
            }
        }
        return data;
    }


    @Override
    public void response(CircleMemberResponse circleMemberResponse) {
        if (circleMemberResponse.getError() == 0) {
            LocalLog.d(TAG, "circleMemberResponse() enter" + circleMemberResponse.toString());
            ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] data = getAdminList(circleMemberResponse);
            MemberManagerAdapter adminAdapter = new MemberManagerAdapter(getContext(), data[0], data[1], opCallBackInterface);

            adminRecyclerView.setAdapter(adminAdapter);

            MemberManagerAdapter normalAdapter = new MemberManagerAdapter(getContext(), data[2], opCallBackInterface);
            normalRecyclerView.setAdapter(normalAdapter);
        }
    }

    public interface OpCallBackInterface {
        public void opMemberOutInto(int id);

        public void opModifyDearName(int id);

        public void addDeleteAdmin(int id);

        public void opMemberIntoOut(CircleMemberBarAdapter.AdapterCallInterface adapterCallInterface);

        public void onLongClick();
    }

    private OpCallBackInterface opCallBackInterface = new OpCallBackInterface() {
        @Override
        public void opMemberOutInto(int id) {
            LocalLog.d(TAG, "opMemberOutInto() enter userId" + id);
            if (deleteArrList.contains(String.valueOf(id))) {
                LocalLog.d(TAG, "移除删除队列");
                deleteArrList.remove(String.valueOf(id));
            } else {
                LocalLog.d(TAG, "加入删除队列");
                deleteArrList.add(String.valueOf(id));
            }
        }

        @Override
        public void opMemberIntoOut(CircleMemberBarAdapter.AdapterCallInterface adapterCallInterface) {
            LocalLog.d(TAG, "opMemberIntoOut() enter");
            setAdapterCallInterface(adapterCallInterface);
        }

        @Override
        public void onLongClick() {
            LocalLog.d(TAG, "onLongClick() enter");
        }

        @Override
        public void opModifyDearName(int id) {
            LocalLog.d(TAG, "修改昵称 id  = " + id);
            Intent intent = new Intent();
            intent.setClass(getContext(), DearNameModifyActivity.class);
            intent.putExtra(getContext().getPackageName(), id);
            startActivityForResult(intent, DEAR_NAME_MODIFY);
        }

        @Override
        public void addDeleteAdmin(int id) {
            PutDearNameParam putDearNameParam = new PutDearNameParam();
            putDearNameParam
                    .setId(id)
                    .setAction("admin");
            Presenter.getInstance(getContext()).modifyDearName(putDearNameParam);
        }
    };

    public void setAdapterCallInterface(CircleMemberBarAdapter.AdapterCallInterface adapterCallInterface) {
        if (!this.adapterCallInterface.contains(adapterCallInterface)) {
            LocalLog.d(TAG, "setAdapterCallInterface() add a callback");
            this.adapterCallInterface.add(adapterCallInterface);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        adapterCallInterface.clear();
        deleteArrList.clear();
    }

    @Override
    public void response(AddDeleteAdminResponse addDeleteAdminResponse) {
        LocalLog.d(TAG, "AddDeleteAdminResponse() enter " + addDeleteAdminResponse.toString());
        if (addDeleteAdminResponse.getError() == 0) {
            Toast.makeText(getContext(), addDeleteAdminResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Presenter.getInstance(getContext()).getCircleMemberAll(Integer.parseInt(id), 1, 10);
        }
    }

    @Override
    public void response(MemberDeleteResponse memberDeleteResponse) {
        LocalLog.d(TAG, "AddDeleteAdminResponse() enter" + memberDeleteResponse.toString());
        //TODO 更新本地UI
        if (memberDeleteResponse.getError() == 0) {
            deleteMemberConfim.setVisibility(View.GONE);
            Presenter.getInstance(getContext()).getCircleMemberAll(Integer.parseInt(id), 1, 10);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DEAR_NAME_MODIFY:
                LocalLog.d(TAG, "昵称修改成功!");
                Toast.makeText(getContext(), "昵称修改成功", Toast.LENGTH_SHORT).show();
                Presenter.getInstance(getContext()).getCircleMemberAll(Integer.parseInt(id), 1, 10);
                break;
        }
    }
}
