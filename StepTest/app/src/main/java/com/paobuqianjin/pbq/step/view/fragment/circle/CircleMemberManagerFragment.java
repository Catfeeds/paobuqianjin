package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.adapter.MemberManagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2017/12/18.
 */

public class CircleMemberManagerFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = CircleMemberManagerFragment.class.getSimpleName();
    private RecyclerView adminRecyclerView, normalRecyclerView;
    private LinearLayoutManager adminManager, normalManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_member_manager_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

        MemberManagerAdapter adminAdapter = new MemberManagerAdapter(getContext());
        adminAdapter.setDefaultValue(2, 0);
        adminRecyclerView.setAdapter(adminAdapter);

        MemberManagerAdapter normalAdapter = new MemberManagerAdapter(getContext());
        normalAdapter.setDefaultValue(5, 1);
        normalRecyclerView.setAdapter(normalAdapter);
        
    }

    @Override
    protected String title() {
        return "成员管理";
    }


}
