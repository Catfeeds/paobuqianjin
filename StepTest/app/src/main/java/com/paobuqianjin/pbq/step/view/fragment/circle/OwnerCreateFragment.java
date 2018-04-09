package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyCreatCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashMyCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateFragment extends BaseFragment {
    private final static String TAG = OwnerCreateFragment.class.getSimpleName();
    @Bind(R.id.owner_create_circle_lists)
    SwipeMenuRecyclerView ownerCreateCircleLists;
    private LinearLayoutManager layoutManager;
    private ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> ownerCreateCircleData;
    private boolean isRefresh;
    private int currentPage = 1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(myCreatCircleInterface);
        Presenter.getInstance(getContext()).attachUiInterface(reflashMyCircleInterface);
        Presenter.getInstance(context).getMyCreateCirlce(1, 10, "");
    }

    public void searchKeyWord(String keyWord) {
        Presenter.getInstance(getContext()).getMyCreateCirlce(1, 10, keyWord);
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
        ownerCreateCircleLists = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.owner_create_circle_lists);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ownerCreateCircleLists.setLayoutManager(layoutManager);
    }


    private MyCreatCircleInterface myCreatCircleInterface = new MyCreatCircleInterface() {
        @Override
        public void response(MyCreateCircleResponse myCreateCircleResponse) {
            if (myCreateCircleResponse.getError() == 0) {
                ownerCreateCircleLists.setAdapter(new OwnerCreateAdapter(getContext(),
                        (ArrayList<MyCreateCircleResponse.DataBeanX.DataBean>) myCreateCircleResponse.getData().getData()));
            }else if(myCreateCircleResponse.getError() == -100){
                LocalLog.d(TAG, "Token 过期!");
                Presenter.getInstance(getContext()).setId(-1);
                Presenter.getInstance(getContext()).steLogFlg(false);
                Presenter.getInstance(getContext()).setToken(getContext(), "");
                getActivity().finish();
                System.exit(0);
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                Presenter.getInstance(getContext()).setId(-1);
                Presenter.getInstance(getContext()).steLogFlg(false);
                Presenter.getInstance(getContext()).setToken(getContext(), "");
                getActivity().finish();
                System.exit(0);
            }
        }
    };

    private ReflashMyCircleInterface reflashMyCircleInterface = new ReflashMyCircleInterface() {
        @Override
        public void response(MyCreateCircleResponse myCreateCircleResponse) {
            LocalLog.d(TAG, " Reflash MyCreateCircleResponse()");
            isRefresh = false;
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                Presenter.getInstance(getContext()).setId(-1);
                Presenter.getInstance(getContext()).steLogFlg(false);
                Presenter.getInstance(getContext()).setToken(getContext(), "");
                getActivity().finish();
                System.exit(0);
            }
        }
    };

    public String getTabLabel() {
        return "我创建的";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(getContext()).dispatchUiInterface(reflashMyCircleInterface);
        Presenter.getInstance(getContext()).dispatchUiInterface(myCreatCircleInterface);
    }
}
