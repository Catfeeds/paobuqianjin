package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RechargeDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.RechargeDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.IncomeAdater;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/3/10.
 */

public class RechargeDetailFragment extends BaseFragment implements RechargeDetailInterface {
    private final static String TAG = RechargeDetailFragment.class.getSimpleName();

    LinearLayoutManager layoutManager;
    @Bind(R.id.out_detail_recycler)
    SwipeMenuRecyclerView outDetailRecycler;
    @Bind(R.id.out_come_refresh)
    SwipeRefreshLayout outComeRefresh;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE_DEFAULT = 10;
    ArrayList<RechargeDetailResponse.DataBeanX.DataBean> myRechargeAllData = new ArrayList<>();
    IncomeAdater adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.outcome_detail_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        super.initView(viewRoot);
        outDetailRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.out_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        outDetailRecycler.setLayoutManager(layoutManager);
        outComeRefresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.out_come_refresh);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        // 自定义的核心就是DefineLoadMoreView类。
        CrashDetailFragment.DefineLoadMoreView loadMoreView = new CrashDetailFragment.DefineLoadMoreView(getContext());
        outDetailRecycler.addFooterView(loadMoreView); // 添加为Footer。
        outDetailRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        outDetailRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        adapter = new IncomeAdater(getContext(), null);
        outDetailRecycler.setAdapter(adapter);

        outComeRefresh.setOnRefreshListener(mRefreshListener);
        loadData(myRechargeAllData);
        Presenter.getInstance(getContext()).getRechargeRecord(pageIndex, PAGE_SIZE_DEFAULT);
    }


    /**
     * 这是这个类的主角，如何自定义LoadMoreView。
     */
    static final class DefineLoadMoreView extends LinearLayout implements SwipeMenuRecyclerView.LoadMoreView, View.OnClickListener {

        private LoadingView mLoadingView;
        private TextView mTvMessage;

        private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;

        public DefineLoadMoreView(Context context) {
            super(context);
            setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            setGravity(Gravity.CENTER);
            setVisibility(GONE);

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

            int minHeight = (int) (displayMetrics.density * 60 + 0.5);
            setMinimumHeight(minHeight);

            inflate(context, R.layout.layout_fotter_loadmore, this);
            mLoadingView = (LoadingView) findViewById(R.id.loading_view);
            mTvMessage = (TextView) findViewById(R.id.tv_message);

            int color1 = ContextCompat.getColor(getContext(), R.color.colorPrimary);
            int color2 = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);
            int color3 = ContextCompat.getColor(getContext(), R.color.colorAccent);

            mLoadingView.setCircleColors(color1, color2, color3);
            setOnClickListener(this);
        }

        /**
         * 马上开始回调加载更多了，这里应该显示进度条。
         */
        @Override
        public void onLoading() {
            setVisibility(VISIBLE);
            mLoadingView.setVisibility(VISIBLE);
            mTvMessage.setVisibility(VISIBLE);
            mTvMessage.setText("正在努力加载，请稍后");
        }

        /**
         * 加载更多完成了。
         *
         * @param dataEmpty 是否请求到空数据。
         * @param hasMore   是否还有更多数据等待请求。
         */
        @Override
        public void onLoadFinish(boolean dataEmpty, boolean hasMore) {
            if (!hasMore) {
                setVisibility(VISIBLE);

                if (dataEmpty) {
                    mLoadingView.setVisibility(GONE);
                    mTvMessage.setVisibility(VISIBLE);
                    mTvMessage.setText("暂时没有数据");
                } else {
                    mLoadingView.setVisibility(GONE);
                    mTvMessage.setVisibility(VISIBLE);
                    mTvMessage.setText("没有更多数据啦");
                }
            } else {
                setVisibility(INVISIBLE);
            }
        }

        /**
         * 调用了setAutoLoadMore(false)后，在需要加载更多的时候，这个方法会被调用，并传入加载更多的listener。
         */
        @Override
        public void onWaitToLoadMore(SwipeMenuRecyclerView.LoadMoreListener loadMoreListener) {
            this.mLoadMoreListener = loadMoreListener;

            setVisibility(VISIBLE);
            mLoadingView.setVisibility(GONE);
            mTvMessage.setVisibility(VISIBLE);
            mTvMessage.setText("点我加载更多");
        }

        /**
         * 加载出错啦，下面的错误码和错误信息二选一。
         *
         * @param errorCode    错误码。
         * @param errorMessage 错误信息。
         */
        @Override
        public void onLoadError(int errorCode, String errorMessage) {
            setVisibility(VISIBLE);
            mLoadingView.setVisibility(GONE);
            mTvMessage.setVisibility(VISIBLE);

            // 这里要不直接设置错误信息，要不根据errorCode动态设置错误数据。
            mTvMessage.setText(errorMessage);
        }

        /**
         * 非自动加载更多时mLoadMoreListener才不为空。
         */
        @Override
        public void onClick(View v) {
            if (mLoadMoreListener != null) mLoadMoreListener.onLoadMore();
        }
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            outDetailRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                            outDetailRecycler.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).getRechargeRecord(pageIndex, PAGE_SIZE_DEFAULT);

                }
            }, 1000);
        }
    };


    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            outDetailRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(myRechargeAllData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<RechargeDetailResponse.DataBeanX.DataBean> dataBeans) {

        adapter.notifyDataSetChanged(dataBeans);

        outComeRefresh.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            outDetailRecycler.loadMoreFinish(true, true);
        } else {
            outDetailRecycler.loadMoreFinish(false, true);
        }
    }


    private void loadMore(ArrayList<RechargeDetailResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        myRechargeAllData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(myRechargeAllData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        outDetailRecycler.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(RechargeDetailResponse rechargeDetailResponse) {
        LocalLog.d(TAG, "RechargeDetailResponse() enter  " + rechargeDetailResponse.toString());
        if (rechargeDetailResponse.getError() == 0) {
            //outDetailRecycler.setAdapter(new IncomeAdater(getContext(), rechargeDetailResponse.getData().getData()));
            LocalLog.d(TAG, rechargeDetailResponse.getMessage());
            if (notFoundData == null) {
                return;
            }
            notFoundData.setVisibility(View.GONE);
            outComeRefresh.setVisibility(View.VISIBLE);
            pageCount = rechargeDetailResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
            loadMore((ArrayList<RechargeDetailResponse.DataBeanX.DataBean>) rechargeDetailResponse.getData().getData());
            if (pageIndex == 1) {
                outDetailRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LocalLog.d(TAG, "滑动到顶端");
                        outDetailRecycler.scrollToPosition(0);
                    }
                }, 10);
            }
            pageIndex++;
        } else if (rechargeDetailResponse.getError() == 1) {
            notFoundData.setVisibility(View.VISIBLE);
            outComeRefresh.setVisibility(View.GONE);
        } else if (rechargeDetailResponse.getError() == -100) {
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
}
