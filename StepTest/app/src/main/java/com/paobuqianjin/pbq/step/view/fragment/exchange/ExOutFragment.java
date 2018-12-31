package com.paobuqianjin.pbq.step.view.fragment.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UpItemInterface;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/28.
 */

public class ExOutFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, UpItemInterface {
    private final static String TAG = ExOutFragment.class.getSimpleName();
    @Bind(R.id.recycler_out)
    SwipeMenuRecyclerView recyclerOut;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    @Bind(R.id.re_fresh)
    SwipeRefreshLayout reFresh;
    private String action;
    private int currentPage = 0;

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.ex_out_manager_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void getExOrderByAction(final int page) {
        currentPage = page;
        Map<String, String> param = new HashMap<>();
        param.put("action", action);
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExOrderOut, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @Override
    protected void initView(View viewRoot) {
        recyclerOut = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.recycler_out);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }

            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        };
        reFresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.re_fresh);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        recyclerOut.addFooterView(loadMoreView); // 添加为Footer。
        recyclerOut.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        recyclerOut.setLoadMoreListener(this);
        recyclerOut.setHasFixedSize(true);
        recyclerOut.setNestedScrollingEnabled(false);
        reFresh.setOnRefreshListener(this);
        recyclerOut.setLayoutManager(linearLayoutManager);

        recyclerOut.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

            }
        });
        if (!TextUtils.isEmpty(action)) {
            getExOrderByAction(1);
        }

    }

    @Override
    public void onLoadMore() {
        if (!TextUtils.isEmpty(action)) {
            getExOrderByAction(currentPage + 1);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void updateItem(int position, String opStr) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
