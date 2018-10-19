package com.paobuqianjin.pbq.step.view.fragment.record;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.GetConsumptiveRBResultActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyConsumptiveRedBagAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/10/18.
 */

public class RecConsumFragment extends BaseFragment {
    private final static String TAG = RecConsumFragment.class.getSimpleName();
    SwipeMenuRecyclerView redRecordRecycler;
    TextView notFoundData;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @Bind(R.id.data_span)
    LinearLayout dataSpan;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE_DEFAULT = 10;
    ArrayList<ShopSendedRedBagResponse.ShopSendedRedBagBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    MyConsumptiveRedBagAdapter redHisAdapter;
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
        swipeLayout.setEnabled(false);
        dataSpan = (LinearLayout) viewRoot.findViewById(R.id.data_span);
        dataSpan.setVisibility(View.GONE);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        notFoundData.setText("没有红包领取记录");
        redRecordRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.red_record_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        redRecordRecycler.setLayoutManager(layoutManager);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        redRecordRecycler.addFooterView(loadMoreView); // 添加为Footer。
        redRecordRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        redRecordRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        redRecordRecycler.setHasFixedSize(true);
        redRecordRecycler.setNestedScrollingEnabled(false);
        redRecordRecycler.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                LocalLog.d(TAG, "查看消费券详情！");
                if(position > arrayList.size()){
                    return;
                }
                Intent intent = new Intent(getActivity(), GetConsumptiveRBResultActivity.class);
                intent.putExtra(getContext().getPackageName() + "red_id", Integer.parseInt(arrayList.get(position).getVoucherid()));
                intent.putExtra(getContext().getPackageName() + "businessid", Integer.parseInt(arrayList.get(position).getBusinessid()));
                startActivityForResult(intent, 1);

            }
        });
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            redHisAdapter = new MyConsumptiveRedBagAdapter(getContext(), arrayList);
            redRecordRecycler.setAdapter(redHisAdapter);
            getConSumRecHistory();
        }

        redHisAdapter.setMyItemClickLis(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {
                if (arrayList.get(position).getStatus() == 0) {
                    if (dialog == null) {
                        dialog = new NormalDialog(getActivity());
                        dialog.setMessage("确定要使用消费红包吗?\n" +
                                "使用后则无法再次使用。");
                    }
                    dialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            dialog.dismiss();
                            useConsumptiveRedBag(position);
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
            }
        });
    }

    private void useConsumptiveRedBag(final int position) {
        Map<String, String> params = new HashMap<>();
        params.put("recordid", arrayList.get(position).getId());
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.useVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                arrayList.get(position).setStatus(3);
                redHisAdapter.notifyDataSetChanged();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    jsonObject = jsonObject.getJSONObject("data");
                    String vNo = jsonObject.getString("vno");
                    final NormalDialog codeDialog = new NormalDialog(getContext());
                    codeDialog.setMessage("消费红包码：" + vNo);
                    codeDialog.setSingleBtn(true);
                    codeDialog.setYesOnclickListener(getString(R.string.confirm), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            codeDialog.dismiss();
                        }
                    });
                    codeDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getConSumRecHistory() {
        String url = NetApi.getMyVoucher + "?page=" + String.valueOf(pageIndex) + "&pagesize=" + String.valueOf(PAGE_SIZE_DEFAULT);
        Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        ShopSendedRedBagResponse redRevHisResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                        pageCount = redRevHisResponse.getData().getPagenation().getTotalPage();
                        if (pageCount == 0) {
                            notFoundData.setVisibility(View.VISIBLE);
                        } else {
                            notFoundData.setVisibility(View.GONE);
                        }
                        if (pageIndex == 1 && redRevHisResponse.getData() != null) {
                            arrayList.clear();
                            if (redRevHisResponse.getData().getData() != null) {
                                if (redRevHisResponse.getData().getData().size() > 0) {
                                    arrayList.addAll(redRevHisResponse.getData().getData());
                                    redHisAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            LocalLog.d(TAG, "加载更多");
                            if (redRevHisResponse.getData() != null) {
                                if (redRevHisResponse.getData().getData() != null) {
                                    if (redRevHisResponse.getData().getData().size() > 0) {
                                        arrayList.addAll(redRevHisResponse.getData().getData());
                                        redHisAdapter.notifyItemRangeInserted(arrayList.size() - redRevHisResponse.getData().getData().size(),
                                                redRevHisResponse.getData().getData().size());
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
                    getConSumRecHistory();


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
