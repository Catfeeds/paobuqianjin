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
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyJoinCircleInterface;
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

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_join_circle_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Presenter.getInstance(context).attachUiInterface(myJoinCircleInterface);
        Presenter.getInstance(context).getMyJoinCircle(1, 10, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    public void searchKeyWord(String keyWord) {
        Presenter.getInstance(getContext()).getMyJoinCircle(1, 10, keyWord);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        //TODO
        ownerJoinCircleLists = (RecyclerView) viewRoot.findViewById(R.id.owner_join_circle_lists);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ownerJoinCircleLists.setLayoutManager(layoutManager);
    }

    private MyJoinCircleInterface myJoinCircleInterface = new MyJoinCircleInterface() {
        @Override
        public void response(MyJoinCircleResponse myJoinCircleResponse) {
            if (myJoinCircleResponse.getError() == 0) {
                ownerJoinCircleLists.setAdapter(new OwnerCreateAdapter(getContext(),
                        (ArrayList<MyJoinCircleResponse.DataBeanX.DataBean>) myJoinCircleResponse.getData().getData()));
            }
        }
    };


    public String getTabLabel() {
        return "我加入的";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(getContext()).dispatchUiInterface(myJoinCircleInterface);
    }
}
