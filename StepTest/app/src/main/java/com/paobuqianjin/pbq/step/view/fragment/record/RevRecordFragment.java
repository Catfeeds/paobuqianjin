package com.paobuqianjin.pbq.step.view.fragment.record;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecvNearPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedRevHisResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.adapter.RedHisAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/8/15.
 */

public class RevRecordFragment extends BaseFragment {
    private final static String TAG = RevRecordFragment.class.getSimpleName();
    private final static String ROUND_ACTION = "com.paobuqianjin.pbq.ROUND_PKG.ACTION";
    private final static String NEAR_ACTION = "com.paobuqianjin.pbq.NEAR_PKG.ACTION";
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
    ArrayList<RedRevHisResponse.DataBeanX.RedpacketListBean.DataBean> arrayList = new ArrayList<>();
    ArrayList<RecvNearPkgResponse.DataBeanX.RedpacketListBean.DataBean> barrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    RedHisAdapter redHisAdapter;
    private String currentAction = null;

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
        swipeLayout.setEnabled(false);
        hisPartA = (TextView) viewRoot.findViewById(R.id.his_part_a);
        hisPartB = (TextView) viewRoot.findViewById(R.id.his_part_b);
        hisPartC = (TextView) viewRoot.findViewById(R.id.his_part_c);
        hisPartDesA = (TextView) viewRoot.findViewById(R.id.his_part_des_a);
        hisPartDesB = (TextView) viewRoot.findViewById(R.id.his_part_des_b);
        hisPartDesC = (TextView) viewRoot.findViewById(R.id.his_part_des_c);
        hisPartDesA.setText(getString(R.string.red_his_reca));
        hisPartDesB.setText(getString(R.string.red_his_recb));
        hisPartDesC.setText(getString(R.string.red_his_recc));
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        notFoundData.setText("没有红包领取记录");
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
        redRecordRecycler.setLayoutManager(layoutManager);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        redRecordRecycler.addFooterView(loadMoreView); // 添加为Footer。
        redRecordRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        redRecordRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        redRecordRecycler.setHasFixedSize(true);
        redRecordRecycler.setNestedScrollingEnabled(false);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ROUND_ACTION.equals(intent.getAction())) {
                currentAction = ROUND_ACTION;
                redHisAdapter = new RedHisAdapter(getContext(), arrayList);
                redRecordRecycler.setAdapter(redHisAdapter);
                getRecHistory();
            } else if (NEAR_ACTION.equals(intent.getAction())) {
                //附近红包
                currentAction = NEAR_ACTION;
                redHisAdapter = new RedHisAdapter(getContext(), barrayList);
                redRecordRecycler.setAdapter(redHisAdapter);
                getNearByPkgHistory();
            }
        }
    }

    private void getNearByPkgHistory() {
        Map<String, String> param = new HashMap<>();
        param.put("action", "receive");
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGE_SIZE_DEFAULT));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlNearByRedHis, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        RecvNearPkgResponse redRevHisResponse = new Gson().fromJson(s, RecvNearPkgResponse.class);
                        pageCount = redRevHisResponse.getData().getRedpacket_list().getPagenation().getTotalPage();
                        if (pageCount == 0) {
                            notFoundData.setVisibility(View.VISIBLE);
                        } else {
                            notFoundData.setVisibility(View.GONE);
                        }
                        hisPartA.setText(redRevHisResponse.getData().getCount_info().getReceive_total());
                        hisPartB.setText(redRevHisResponse.getData().getCount_info().getReceive_count());
                        hisPartC.setText(redRevHisResponse.getData().getCount_info().getMax_money());
                        if (pageIndex == 1 && redRevHisResponse.getData() != null) {
                            barrayList.clear();
                            if (redRevHisResponse.getData().getRedpacket_list() != null && redRevHisResponse.getData().getRedpacket_list().getData() != null) {
                                if (redRevHisResponse.getData().getRedpacket_list().getData().size() > 0) {
                                    barrayList.addAll(redRevHisResponse.getData().getRedpacket_list().getData());
                                    redHisAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            LocalLog.d(TAG, "加载更多");
                            if (redRevHisResponse.getData() != null) {
                                if (redRevHisResponse.getData().getRedpacket_list() != null && redRevHisResponse.getData().getRedpacket_list().getData() != null) {
                                    if (redRevHisResponse.getData().getRedpacket_list().getData().size() > 0) {
                                        barrayList.addAll(redRevHisResponse.getData().getRedpacket_list().getData());
                                        redHisAdapter.notifyItemRangeInserted(barrayList.size() - redRevHisResponse.getData().getRedpacket_list().getData().size(),
                                                redRevHisResponse.getData().getRedpacket_list().getData().size());
                                    }
                                }
                            }

                        }
                        redRecordRecycler.loadMoreFinish(false, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {
                    if (e != null) {

                    } else {

                    }
                }
            }
        });
    }

    private void getRecHistory() {
        Map<String, String> param = new HashMap<>();
        param.put("action", "receive");
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGE_SIZE_DEFAULT));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlRedHis, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        RedRevHisResponse redRevHisResponse = new Gson().fromJson(s, RedRevHisResponse.class);
                        pageCount = redRevHisResponse.getData().getRedpacket_list().getPagenation().getTotalPage();
                        if (pageCount == 0) {
                            notFoundData.setVisibility(View.VISIBLE);
                        } else {
                            notFoundData.setVisibility(View.GONE);
                        }
                        hisPartA.setText(redRevHisResponse.getData().getCount_info().getReceive_total());
                        hisPartB.setText(redRevHisResponse.getData().getCount_info().getReceive_count());
                        hisPartC.setText(redRevHisResponse.getData().getCount_info().getMax_money());
                        if (pageIndex == 1 && redRevHisResponse.getData() != null) {
                            arrayList.clear();
                            if (redRevHisResponse.getData().getRedpacket_list() != null && redRevHisResponse.getData().getRedpacket_list().getData() != null) {
                                if (redRevHisResponse.getData().getRedpacket_list().getData().size() > 0) {
                                    arrayList.addAll(redRevHisResponse.getData().getRedpacket_list().getData());
                                    redHisAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            LocalLog.d(TAG, "加载更多");
                            if (redRevHisResponse.getData() != null) {
                                if (redRevHisResponse.getData().getRedpacket_list() != null && redRevHisResponse.getData().getRedpacket_list().getData() != null) {
                                    if (redRevHisResponse.getData().getRedpacket_list().getData().size() > 0) {
                                        arrayList.addAll(redRevHisResponse.getData().getRedpacket_list().getData());
                                        redHisAdapter.notifyItemRangeInserted(arrayList.size() - redRevHisResponse.getData().getRedpacket_list().getData().size(),
                                                redRevHisResponse.getData().getRedpacket_list().getData().size());
                                    }
                                }
                            }

                        }
                        redRecordRecycler.loadMoreFinish(false, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {
                    if (e != null) {

                    } else {

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
                    if (ROUND_ACTION.equals(currentAction)) {
                        getRecHistory();
                    } else if (NEAR_ACTION.equals(currentAction)) {
                        getNearByPkgHistory();
                    }

                }
            }, 1000);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
