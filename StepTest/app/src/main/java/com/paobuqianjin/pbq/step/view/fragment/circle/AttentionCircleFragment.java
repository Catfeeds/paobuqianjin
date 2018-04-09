package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicIndexUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.AttentionCircleAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.SearchCircleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/11.
 */

public class AttentionCircleFragment extends BaseFragment {
    private final static String TAG = AttentionCircleFragment.class.getSimpleName();
    LinearLayoutManager layoutManager;
    @Bind(R.id.dynamic_recyclerView)
    SwipeMenuRecyclerView dynamicRecyclerView;
    @Bind(R.id.attention_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    int pageIndex = 1;
    int pageCount = 0;
    ArrayList<DynamicAllIndexResponse.DataBeanX.DataBean> dynamicAllData = new ArrayList<>();
    AttentionCircleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Presenter.getInstance(getActivity()).attachUiInterface(dynamicIndexUiInterface);
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.attention_circle_fragment;
    }

    public String getTabLabel() {
        return "关注";
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        Presenter.getInstance(getContext()).getDynamicIndex(pageIndex, Utils.PAGE_SIZE_DEFAULT);
        dynamicRecyclerView = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.dynamic_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dynamicRecyclerView.setLayoutManager(layoutManager);
        dynamicRecyclerView.setSwipeItemClickListener(mItemClickListener);
        adapter = new AttentionCircleAdapter(getContext(), null);

        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        dynamicRecyclerView.addFooterView(loadMoreView); // 添加为Footer。
        dynamicRecyclerView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        dynamicRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。


        dynamicRecyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.attention_swipe);
        swipeRefreshLayout.setOnRefreshListener(mRefreshListener);

        loadData(dynamicAllData);
    }


    private void loadMore(ArrayList<DynamicAllIndexResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        dynamicAllData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(dynamicAllData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        dynamicRecyclerView.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
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
     * Item点击监听。
     */
    private SwipeItemClickListener mItemClickListener = new SwipeItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            //Toast.makeText(getActivity(), "第" + position + "个", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            dynamicRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                            dynamicRecyclerView.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    Presenter.getInstance(getContext()).getDynamicIndex(pageIndex, Utils.PAGE_SIZE_DEFAULT);

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
            dynamicRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(dynamicAllData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<DynamicAllIndexResponse.DataBeanX.DataBean> dataBeans) {
        adapter.notifyDataSetChanged(dataBeans);

        swipeRefreshLayout.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            dynamicRecyclerView.loadMoreFinish(true, true);
        } else {
            dynamicRecyclerView.loadMoreFinish(false, true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(getContext()).dispatchUiInterface(dynamicIndexUiInterface);
        ButterKnife.unbind(this);
    }


    private DynamicIndexUiInterface dynamicIndexUiInterface = new DynamicIndexUiInterface() {
        @Override
        public void response(DynamicAllIndexResponse dynamicAllIndexResponse) {
            LocalLog.d(TAG, "DynamicAllIndexResponse() enter" + dynamicAllIndexResponse.toString());
            if (dynamicAllIndexResponse.getError() == 0) {
                LocalLog.d(TAG, dynamicAllIndexResponse.getMessage());
                pageCount = dynamicAllIndexResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);

                loadMore((ArrayList<DynamicAllIndexResponse.DataBeanX.DataBean>) dynamicAllIndexResponse.getData().getData());
                if (pageIndex == 1) {
                    dynamicRecyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LocalLog.d(TAG, "滑动到顶端");
                            dynamicRecyclerView.scrollToPosition(0);
                        }
                    }, 100);
                }

                pageIndex++;
            } else if (dynamicAllIndexResponse.getError() == -1) {

            } else if (dynamicAllIndexResponse.getError() == 1) {

            }else if(dynamicAllIndexResponse.getError() == -100){
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

    @OnClick({R.id.dynamic_recyclerView, R.id.attention_swipe})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dynamic_recyclerView:
                break;
            case R.id.attention_swipe:
                break;
        }
    }
}
