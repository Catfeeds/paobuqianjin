package com.paobuqianjin.pbq.step.view.fragment.exchange;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExInOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExOutOrderResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UpItemInterface;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.exchange.AddExTriffActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExTriffActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.OrderActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExInAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExOutAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExInFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, UpItemInterface {
    @Override
    protected int getLayoutResId() {
        return R.layout.ex_out_manager_fg;
    }
    private final static String TAG = ExOutFragment.class.getSimpleName();
    @Bind(R.id.recycler_out)
    SwipeMenuRecyclerView recyclerOut;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    @Bind(R.id.re_fresh)
    SwipeRefreshLayout reFresh;
    private String action;
    private int currentPage = 0;
    private ExInAdapter exOutAdapter;
    //发货
    private final int RELEASE_TR = 4;
    private final int ORDER_DETAIL = 5;

    public void setAction(String action) {
        switch (action) {
            case "全部":
                this.action = "all";
                break;
            case "待付款":
                this.action = "unpay";
                break;
            case "待发货":
                this.action = "unconsigner";
                break;
            case "待收货":
                this.action = "unreceive";
                break;
            case "待评价":
                this.action = "uncoment";
                break;
            case "退款":
                this.action = "unrefound";
                break;
            case "已完成":
                break;
        }
    }

    private List<ExInOrderResponse.DataBeanX.DataBean> listData = new ArrayList<>();

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
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExIn, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    ExInOrderResponse exOutOrderResponse = new Gson().fromJson(s, ExInOrderResponse.class);
                    notFoundData.setVisibility(View.GONE);
                    recyclerOut.setVisibility(View.VISIBLE);
                    if (page == 1) {
                        listData.clear();
                        exOutAdapter.notifyDataSetChanged();
                    }
                    if (exOutOrderResponse.getData().getData().size() > 0) {
                        listData.addAll(exOutOrderResponse.getData().getData());
                        recyclerOut.loadMoreFinish(false, true);
                    } else {
                        recyclerOut.loadMoreFinish(true, false);
                    }
                    reFresh.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {
                    recyclerOut.loadMoreFinish(true, false);
                    reFresh.setRefreshing(false);
                    notFoundData.setVisibility(View.VISIBLE);
                    recyclerOut.setVisibility(View.GONE);
                }
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
        recyclerOut.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                reFresh.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        recyclerOut.addFooterView(loadMoreView); // 添加为Footer。
        recyclerOut.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        recyclerOut.setLoadMoreListener(this);
        recyclerOut.setHasFixedSize(true);
        recyclerOut.setNestedScrollingEnabled(false);
        reFresh.setOnRefreshListener(this);
        recyclerOut.setLayoutManager(linearLayoutManager);
        recyclerOut.addItemDecoration(new ExOutFragment.SpaceItemDecoration(20));
        recyclerOut.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position < listData.size()) {
                    LocalLog.d(TAG, "查看详情");
                    Intent intent = new Intent();
                    intent.setClass(getContext(), OrderActivity.class);
                    intent.putExtra("comm_order_no", listData.get(position));
                    startActivityForResult(intent, ORDER_DETAIL);
                }
            }
        });
        if (!TextUtils.isEmpty(action)) {
            getExOrderByAction(1);
        }
        exOutAdapter = new ExInAdapter(getContext(), listData);
        exOutAdapter.setUpdateListener(this);
        recyclerOut.setAdapter(exOutAdapter);
    }

    @Override
    public void onLoadMore() {
        if (!TextUtils.isEmpty(action)) {
            getExOrderByAction(currentPage + 1);
        }
    }

    @Override
    public void onRefresh() {
        if (!TextUtils.isEmpty(action)) {
            getExOrderByAction(1);
        }
    }


    private void cancelOrder(String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        param.put("type", "sale");
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExCancel, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                getExOrderByAction(1);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                }
            }
        });
    }

    private void quitOrder(String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_order_id", comm_id);
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExQuitOrder, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                getExOrderByAction(1);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                }
            }
        });
    }


    @Override
    public void updateItem(int position, String opStr) {
        if (position < listData.size()) {
            switch (opStr) {
                case "取消订单":
                    cancelOrder(String.valueOf(listData.get(position).getId()));
                    break;
                case "退款":
                    quitOrder(String.valueOf(listData.get(position).getId()));
                    break;
                case "立即发货":
                    Intent intentRe = new Intent();
                    intentRe.setClass(getContext(), AddExTriffActivity.class);
                    intentRe.putExtra("comm_order_id", String.valueOf(listData.get(position).getId()));
                    startActivityForResult(intentRe, RELEASE_TR);
                    break;
                case "查看物流":
                    Intent intentTr = new Intent();
                    intentTr.setClass(getContext(), ExTriffActivity.class);
                    intentTr.putExtra("comm_order_no", listData.get(position));
                    startActivity(intentTr);
                    break;

            }
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildAdapterPosition(view) > 0) {
                outRect.top = space;
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RELEASE_TR && resultCode == Activity.RESULT_OK) {
            getExOrderByAction(1);
        } else if (requestCode == ORDER_DETAIL && requestCode == Activity.RESULT_OK) {
            getExOrderByAction(1);
        }
    }
}
