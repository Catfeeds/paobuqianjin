package com.paobuqianjin.pbq.step.view.fragment.record;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConSumSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedSendHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SendNearPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.ShopRedBagActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ConsumHisAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/10/18.
 */

public class DispatchConsumRecordFg extends BaseFragment {
    private final static String TAG = DispatchConsumRecordFg.class.getSimpleName();
    TextView hisPartA;
    TextView hisPartDesA;
    TextView hisPartB;
    TextView hisPartDesB;
    TextView hisPartC;
    TextView hisPartDesC;
    SwipeMenuRecyclerView redRecordRecycler;
    TextView notFoundData;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE_DEFAULT = 10;
    ArrayList<ConSumSendHisResponse.DataBeanX.SendListBean.DataBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ConsumHisAdapter redHisAdapter;
    private NormalDialog dialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.red_record_hs_fg;
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
        swipeLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.swipe_layout);
        swipeLayout.setOnRefreshListener(mRefreshListener);
        hisPartA = (TextView) viewRoot.findViewById(R.id.his_part_a);
        hisPartB = (TextView) viewRoot.findViewById(R.id.his_part_b);
        LinearLayout partB = (LinearLayout) viewRoot.findViewById(R.id.part_b);
        partB.setVisibility(View.GONE);
        hisPartC = (TextView) viewRoot.findViewById(R.id.his_part_c);
        hisPartDesA = (TextView) viewRoot.findViewById(R.id.his_part_des_a);
        hisPartDesC = (TextView) viewRoot.findViewById(R.id.his_part_des_c);
        hisPartDesA.setText("发布总个数");
        hisPartDesC.setText("领取总人数");

        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        notFoundData.setText("没有消费红包发布记录");
        redRecordRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.red_record_recycler);
        layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        redRecordRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        redRecordRecycler.setLayoutManager(layoutManager);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        redRecordRecycler.addFooterView(loadMoreView); // 添加为Footer。
        redRecordRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        redRecordRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        redRecordRecycler.setHasFixedSize(true);
        redRecordRecycler.setNestedScrollingEnabled(false);
        redHisAdapter = new ConsumHisAdapter(getContext(), arrayList, this);
        redRecordRecycler.setAdapter(redHisAdapter);
        redHisAdapter.setMyCustomClickLis(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                if (arrayList.get(position).getStatus() != 0) return;
                if (dialog == null) {
                    dialog = new NormalDialog(getActivity());
                    dialog.setMessage("确定要下架消费红包？");
                }
                dialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss();
                        cutDonwConsumptiveRedBag(position);
                    }
                });
                dialog.setNoOnclickListener(getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
        getConsumRedHistory();

    }

    private void cutDonwConsumptiveRedBag(final int position) {
        if (position >= arrayList.size()) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", String.valueOf(arrayList.get(position).getVoucherid()));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.lowerVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                arrayList.get(position).setStatus(1);
                redHisAdapter.notifyDataSetChanged();
                PaoToastUtils.showShortToast(getActivity(), "下架成功");
            }
        });

    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            swipeLayout.post(new Runnable() {
                @Override
                public void run() {
                    if (!isAdded()) {
                        return;
                    }
                    pageCount = 0;
                    pageIndex = 1;
                    arrayList.clear();
                    getConsumRedHistory();
                    if (swipeLayout != null) {
                        swipeLayout.setEnabled(false);
                        swipeLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (swipeLayout != null) {
                                    swipeLayout.setRefreshing(false);
                                    swipeLayout.setEnabled(true);
                                }
                            }
                        }, 2000);
                    }
                }
            }); // 延时模拟请求服务器。
        }
    };

    private void getConsumRedHistory() {
        Map<String, String> param = new HashMap<>();
        param.put("page", pageIndex + "");
        param.put("pagesize", Constants.PAGE_SIZE + "");
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.getMySendHis, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        ConSumSendHisResponse redSendHisResponse = new Gson().fromJson(s, ConSumSendHisResponse.class);
                        pageCount = redSendHisResponse.getData().getSend_list().getPagenation().getTotalPage();
                        if (pageCount == 0) {
                            notFoundData.setVisibility(View.VISIBLE);
                        } else {
                            notFoundData.setVisibility(View.GONE);
                        }
                        hisPartA.setText(redSendHisResponse.getData().getInfo().getSend_sum());
                        try {
                            int count_send = Integer.parseInt(redSendHisResponse.getData().getInfo().getSend_sum());
                            if (count_send > 10000) {
                                hisPartB.setText(Utils.zeroTow(count_send));
                            } else {
                                hisPartB.setText(redSendHisResponse.getData().getInfo().getSend_sum());
                            }
                            int count_rec = Integer.parseInt(redSendHisResponse.getData().getInfo().getReceive_sum());
                            if (count_rec > 10000) {
                                hisPartC.setText(Utils.zeroTow(count_rec));
                            } else {
                                hisPartC.setText(redSendHisResponse.getData().getInfo().getReceive_sum());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (pageIndex == 1 && redSendHisResponse.getData() != null) {
                            arrayList.clear();
                            if (redSendHisResponse.getData().getSend_list() != null && redSendHisResponse.getData().getSend_list().getData() != null) {
                                if (redSendHisResponse.getData().getSend_list().getData().size() > 0) {
                                    arrayList.addAll(redSendHisResponse.getData().getSend_list().getData());
                                    redHisAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            LocalLog.d(TAG, "加载更多");
                            if (redSendHisResponse.getData() != null) {
                                if (redSendHisResponse.getData().getSend_list() != null && redSendHisResponse.getData().getSend_list().getData() != null) {
                                    if (redSendHisResponse.getData().getSend_list().getData().size() > 0) {
                                        arrayList.addAll(redSendHisResponse.getData().getSend_list().getData());
                                        redHisAdapter.notifyItemRangeInserted(arrayList.size() - redSendHisResponse.getData().getSend_list().getData().size(), redSendHisResponse.getData().getSend_list().getData().size());
                                    }
                                }
                            }

                        }
                        redRecordRecycler.loadMoreFinish(false, true);
                    } catch (Exception e) {

                    }
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {
                    if (e != null) {

                    } else {
                        if (errorBean != null && "Not Found Data".equals(errorBean.getMessage())) {
                            hisPartA.setText("0");
                            hisPartB.setText("0");
                            hisPartC.setText("0");
                        }
                    }
                }
            }
        });
    }

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            redRecordRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (!isAdded()) {
                        return;
                    }
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            PaoToastUtils.showLongToast(getContext(), "没有更多内容");
                            redRecordRecycler.loadMoreFinish(false, true);
                            redRecordRecycler.setLoadMoreView(null);
                            redRecordRecycler.setLoadMoreListener(null);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    getConsumRedHistory();

                }
            }, 1000);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pageIndex = 1;
        pageCount = 0;
        arrayList.clear();
        getConsumRedHistory();
    }
}
