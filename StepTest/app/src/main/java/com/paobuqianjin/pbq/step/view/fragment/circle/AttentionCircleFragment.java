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
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicIndexUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.AttentionCircleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/11.
 */

public class AttentionCircleFragment extends BaseFragment {
    private final static String TAG = AttentionCircleFragment.class.getSimpleName();
    RecyclerView dynamicRecyclerView;
    LinearLayoutManager layoutManager;
    private int currentIndex = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Presenter.getInstance(getActivity()).attachUiInterface(dynamicIndexUiInterface);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.attention_circle_fragment;
    }

    public String getTabLabel() {
        return "关注";
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        Presenter.getInstance(getContext()).getDynamicIndex(currentIndex, 10);
        dynamicRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.dynamic_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dynamicRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(getContext()).dispatchUiInterface(dynamicIndexUiInterface);
    }

    private DynamicIndexUiInterface dynamicIndexUiInterface = new DynamicIndexUiInterface() {
        @Override
        public void response(DynamicAllIndexResponse dynamicAllIndexResponse) {
            LocalLog.d(TAG, "DynamicAllIndexResponse() enter" + dynamicAllIndexResponse.toString());
            if (dynamicAllIndexResponse.getError() == 1) {
                LocalLog.d(TAG, dynamicAllIndexResponse.getMessage());
            } else if (dynamicAllIndexResponse.getError() == -1) {
                
            } else if (dynamicAllIndexResponse.getError() == 0) {
                dynamicRecyclerView.setAdapter(new AttentionCircleAdapter(getContext(), dynamicAllIndexResponse.getData().getData()));
            }

        }
    };
}
