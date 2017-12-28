package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateFragment extends BaseFragment {
    private final static String TAG = OwnerCreateFragment.class.getSimpleName();
    @Bind(R.id.owner_create_circle_lists)
    RecyclerView ownerCreateCircleLists;
    private LinearLayoutManager layoutManager;
    private ArrayList<MyHotCircleResponse.DataBeanX.DataBean> ownerCreateCircleData;

    public void setOwnerCreateCircleData(ArrayList<MyHotCircleResponse.DataBeanX.DataBean> ownerCreateCircleData) {
        this.ownerCreateCircleData = ownerCreateCircleData;
        if (ownerCreateCircleData == null) {
            //重新获取
            return;
        }
        LocalLog.d(TAG, "setOwnerCreateCircleData() enter" + ownerCreateCircleData.toString());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_create_cirlce_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        LocalLog.d(TAG, "initView() enter");
        ownerCreateCircleLists = (RecyclerView) viewRoot.findViewById(R.id.owner_create_circle_lists);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ownerCreateCircleLists.setLayoutManager(layoutManager);
        ownerCreateCircleLists.setAdapter(new OwnerCreateAdapter(getContext(), ownerCreateCircleData));

    }

    public String getTabLabel() {
        return "我创建的";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
