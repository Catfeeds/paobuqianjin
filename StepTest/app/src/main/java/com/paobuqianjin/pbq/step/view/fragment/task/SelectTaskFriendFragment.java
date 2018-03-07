package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
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
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendSearchResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.SelectUserFriendInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.task.SelectTaskFriendAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/26.
 */

public class SelectTaskFriendFragment extends BaseFragment implements SelectUserFriendInterface {
    private final static String TAG = SelectTaskFriendFragment.class.getSimpleName();
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
    SelectTaskFriendAdapter selectTaskFriendAdapter = null;
    private LinearLayoutManager layoutManager;
    private static final int SELECT_FRIENDS = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.select_task_friend_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).getUserFiends();
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
        barReturnLeft.setOnClickListener(onClickListener);
        barTvRight.setOnClickListener(onClickListener);
        friendRecycler = (RecyclerView) viewRoot.findViewById(R.id.friend_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        friendRecycler.setLayoutManager(layoutManager);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bar_return_left:
                    LocalLog.d(TAG, "取消");
                    getActivity().finish();
                    break;
                case R.id.bar_tv_right:
                    LocalLog.d(TAG, "确定");
                    if (selectTaskFriendAdapter != null) {
                        //反馈选中结果到上一个Activity
                        FriendBundleData friendBundleData = new FriendBundleData((ArrayList<UserFriendResponse.DataBeanX.DataBean>) selectTaskFriendAdapter.getResultData());
                        LocalLog.d(TAG, selectTaskFriendAdapter.getResultData().toString());
                        Intent intent = new Intent();
                        intent.putExtra(getActivity().getPackageName(), friendBundleData);
                        getActivity().setResult(SELECT_FRIENDS, intent);
                        getActivity().finish();
                    }
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(UserFriendResponse userFriendResponse) {
        LocalLog.d(TAG, "UserFriendResponse() enter " + userFriendResponse.toString());
        if (userFriendResponse.getError() == 0) {
            selectTaskFriendAdapter = new SelectTaskFriendAdapter(getContext(), userFriendResponse.getData().getData());
            friendRecycler.setAdapter(selectTaskFriendAdapter);
        }
    }

    @Override
    public void response(UserFriendSearchResponse userFriendSearchResponse) {
        LocalLog.d(TAG, "UserFriendSearchResponse() enter");
    }
}
