package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.adapter.task.SelectTaskFriendAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/26.
 */

public class SelectTaskFriendFragment extends BaseFragment {
    @Bind(R.id.bar_return_left)
    TextView barReturnLeft;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.search_icon)
    ImageView searchIcon;
    @Bind(R.id.search_cancel)
    ImageView searchCancel;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.friend_recycler)
    RecyclerView friendRecycler;

    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.select_task_friend_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        barReturnLeft = (TextView) viewRoot.findViewById(R.id.bar_return_left);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTvRight = (TextView) viewRoot.findViewById(R.id.bar_tv_right);
        barReturnLeft.setText("取消");
        barTitle.setText("选择好友");
        barTvRight.setText("确认");
        friendRecycler = (RecyclerView) viewRoot.findViewById(R.id.friend_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        friendRecycler.setLayoutManager(layoutManager);
        friendRecycler.setAdapter(new SelectTaskFriendAdapter(getContext()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
