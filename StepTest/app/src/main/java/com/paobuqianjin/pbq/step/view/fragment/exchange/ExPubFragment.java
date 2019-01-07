package com.paobuqianjin.pbq.step.view.fragment.exchange;

import android.app.Activity;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExPublistResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.UpItemInterface;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExchangeGoodDeatilActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.TwoHandReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExPubAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
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
/*
@className :ExPubFragment
*@date 2018/12/29
*@author
*@description 我发布的和已下架的交换物品
*/
public class ExPubFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, UpItemInterface {
    private final static String TAG = ExPubFragment.class.getSimpleName();
    @Bind(R.id.recycler_out)
    SwipeMenuRecyclerView recyclerOut;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    @Bind(R.id.re_fresh)
    SwipeRefreshLayout reFresh;
    /*1表示已下架*/
    private int type = 0;
    private int currentPage = 0;
    private List<ExPublistResponse.DataBeanX.DataBean> listData = new ArrayList<>();
    private ExPubAdapter exPubAdapter;
    private final static int REQUEST_EDIT = 4;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.ex_out_manager_fg;
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
        exPubAdapter = new ExPubAdapter(getContext(), listData);
        exPubAdapter.setUpdateListener(this);
        recyclerOut.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                LocalLog.d(TAG, "查看商品详情");
                if (position < listData.size()) {
                    Intent intent = new Intent();
                    intent.putExtra("ex_id", String.valueOf(listData.get(position).getId()));
                    intent.setClass(getContext(), ExchangeGoodDeatilActivity.class);
                    startActivity(intent);
                }
            }
        });
        recyclerOut.setAdapter(exPubAdapter);
        getExPub(1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onLoadMore() {
        if (isAdded()) {
            getExPub(currentPage + 1);
        }
    }

    @Override
    public void onRefresh() {
        if (isAdded()) {
            getExPub(1);
        }
    }

    private void delete(int position) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_id", String.valueOf(listData.get(position).getId()));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExDelete, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                getExPub(1);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null && isAdded()) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                } else {

                }
            }
        });
    }

    private void reUp(int position) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_id", String.valueOf(listData.get(position).getId()));
        param.put("action", String.valueOf(2));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExUpDown, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                getExPub(1);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null && isAdded()) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                } else {

                }
            }
        });
    }

    private void down(int position) {
        Map<String, String> param = new HashMap<>();
        param.put("comm_id", String.valueOf(listData.get(position).getId()));
        param.put("action", String.valueOf(1));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExUpDown, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                getExPub(1);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null && isAdded()) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                } else {

                }
            }
        });
    }

    @Override
    public void updateItem(int position, String opStr) {
        LocalLog.d(TAG, "updateItem = " + position + ",op = " + opStr);
        if (position < listData.size()) {
            switch (opStr) {
                case "删除记录":
                    delete(position);
                    break;
                case "重新上架":
                    reUp(position);
                    break;
                case "下架":
                    down(position);
                    break;
                case "编辑":
                    LocalLog.d(TAG, "进入编辑界面!");
                    Intent intentEdit = new Intent();
                    intentEdit.setClass(getContext(), TwoHandReleaseActivity.class);
                    intentEdit.putExtra("data", listData.get(position));
                    startActivityForResult(intentEdit, REQUEST_EDIT);
                    break;
            }
        }
    }

    private void getExPub(final int page) {
        Map<String, String> param = new HashMap<>();
        param.put("type", String.valueOf(type));
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));

        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.ulrExchangePub, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        ExPublistResponse exPublistResponse = new Gson().fromJson(s, ExPublistResponse.class);
                        if (page == 1) {
                            listData.clear();
                            recyclerOut.loadMoreFinish(false, true);
                            exPubAdapter.notifyDataSetChanged();
                        }

                        if (exPublistResponse.getData().getData().size() > 0) {
                            listData.addAll(exPublistResponse.getData().getData());
                            exPubAdapter.notifyDataSetChanged();
                        } else {
                            recyclerOut.loadMoreFinish(true, false);
                        }
                        reFresh.setRefreshing(false);
                        if (recyclerOut.getVisibility() == View.GONE) {
                            recyclerOut.setVisibility(View.VISIBLE);
                            notFoundData.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {
                    recyclerOut.setVisibility(View.GONE);
                    notFoundData.setVisibility(View.VISIBLE);
                    recyclerOut.loadMoreFinish(false, false);
                    reFresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT && resultCode == Activity.RESULT_OK) {
            getExPub(1);
        }
    }
}
