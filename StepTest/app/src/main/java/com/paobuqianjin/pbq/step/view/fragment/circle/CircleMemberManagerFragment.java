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

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleMemberResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleMemberManagerInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.MemberManagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/18.
 */

public class CircleMemberManagerFragment extends BaseBarImageViewFragment {
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

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_member_manager_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(circleMemberManagerInterface);
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
        adminRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.circle_admin_span);
        normalRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.all_member_span);
        adminManager = new LinearLayoutManager(getContext());
        normalManager = new LinearLayoutManager(getContext());
        adminRecyclerView.setLayoutManager(adminManager);
        normalRecyclerView.setLayoutManager(normalManager);

    }

    @Override
    protected String title() {
        return "成员管理";
    }

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

    private CircleMemberManagerInterface circleMemberManagerInterface = new CircleMemberManagerInterface() {
        @Override
        public void response(CircleMemberResponse circleMemberResponse) {
            if (circleMemberResponse.getError() == 0) {
                LocalLog.d(TAG, "circleMemberResponse() enter" + circleMemberResponse.toString());
                ArrayList<CircleMemberResponse.DataBeanX.DataBean>[] data = getAdminList(circleMemberResponse);
                MemberManagerAdapter adminAdapter = new MemberManagerAdapter(getContext(), data[0], data[1]);

                adminRecyclerView.setAdapter(adminAdapter);

                MemberManagerAdapter normalAdapter = new MemberManagerAdapter(getContext(), data[2]);
                normalRecyclerView.setAdapter(normalAdapter);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(circleMemberManagerInterface);
    }
}
