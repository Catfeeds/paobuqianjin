package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerJoinFragment extends BaseFragment {
    private final static String TAG = OwnerJoinFragment.class.getSimpleName();
    RecyclerView ownerJoinCircleLists;
    private LinearLayoutManager layoutManager;
    private Context mContext;
    private ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> ownerCircleData;

    public void setOwnerCircleData(ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> ownerCircleData) {
        this.ownerCircleData = ownerCircleData;
        if (ownerCircleData == null) {
            //重新获取
            return;
        }
        LocalLog.d(TAG, "");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_join_circle_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
        //TODO
        ownerJoinCircleLists = (RecyclerView) viewRoot.findViewById(R.id.owner_join_circle_lists);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ownerJoinCircleLists.setLayoutManager(layoutManager);
        ownerJoinCircleLists.setAdapter(new OwnerCreateAdapter(getContext(), ownerCircleData));

    }

    public String getTabLabel() {
        return "我加入的";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
