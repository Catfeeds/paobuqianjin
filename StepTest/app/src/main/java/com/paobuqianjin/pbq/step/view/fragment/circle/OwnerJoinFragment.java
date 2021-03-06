package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyJoinCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerJoinFragment extends BaseFragment {
    private final static String TAG = OwnerJoinFragment.class.getSimpleName();
    @Bind(R.id.owner_join_circle_lists)
    SwipeMenuRecyclerView ownerJoinCircleLists;
    @Bind(R.id.join_circle_swipe)
    SwipeRefreshLayout joinCircleSwipe;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private LinearLayoutManager layoutManager;
    private Context mContext;
    private int pageIndex = 1;
    private int pageCount = 0;
    ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> myJoinAllData = new ArrayList<>();
    OwnerCreateAdapter adapter;
    private final static int PAGE_SIZE_DEFAULT = 10;
    private String keyWord = "";
    ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> searchData;
    private boolean isSearch = false;
    private int mCurrentIndex = 1;
    private final int REQUEST_DETAIL = 100;
    private final static String QUIT_ACTION = "com.paobuqianjin.pbq.step.QUIT";

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_join_circle_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Presenter.getInstance(context).attachUiInterface(myJoinCircleInterface);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void searchKeyWord(String keyWord) {
        this.keyWord = keyWord;
        if (!isAdded()) {
            return;
        }
        if (TextUtils.isEmpty(keyWord)) {
            LocalLog.d(TAG, "显示旧数据");
            isSearch = false;
            notFoundData.setVisibility(View.GONE);
            joinCircleSwipe.setVisibility(View.VISIBLE);
            pageIndex = mCurrentIndex;
            loadData(myJoinAllData);
            pageCount = 0;
            return;
        }
        isSearch = true;
        searchData = new ArrayList<>();
        loadData(searchData);
        pageIndex = 1;
        Presenter.getInstance(getContext()).getMyJoinCircle(pageIndex, PAGE_SIZE_DEFAULT, keyWord);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        //TODO
        ownerJoinCircleLists = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.owner_join_circle_lists);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ownerJoinCircleLists.setLayoutManager(layoutManager);
        joinCircleSwipe = (SwipeRefreshLayout) viewRoot.findViewById(R.id.join_circle_swipe);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        ownerJoinCircleLists.setHasFixedSize(true);
        ownerJoinCircleLists.setNestedScrollingEnabled(false);
        // 自定义的核心就是DefineLoadMoreView类。
        AttentionCircleFragment.DefineLoadMoreView loadMoreView = new AttentionCircleFragment.DefineLoadMoreView(getContext());
        ownerJoinCircleLists.addFooterView(loadMoreView); // 添加为Footer。
        ownerJoinCircleLists.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        ownerJoinCircleLists.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        adapter = new OwnerCreateAdapter(getContext(), this, null);
        ownerJoinCircleLists.setAdapter(adapter);

        joinCircleSwipe.setOnRefreshListener(mRefreshListener);
        loadData(myJoinAllData);
        Presenter.getInstance(getContext()).getMyJoinCircle(pageIndex, PAGE_SIZE_DEFAULT, keyWord);
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            ownerJoinCircleLists.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            if (getContext() != null) {
                                /*Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();*/
                                ownerJoinCircleLists.loadMoreFinish(false, true);
                            }
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).getMyJoinCircle(pageIndex, PAGE_SIZE_DEFAULT, keyWord);

                }
            }, 1000);
        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> dataBeans) {

        adapter.notifyDataSetChanged(dataBeans);

        if (joinCircleSwipe == null) {
            return;
        }
        joinCircleSwipe.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            ownerJoinCircleLists.loadMoreFinish(true, true);
        } else {
            ownerJoinCircleLists.loadMoreFinish(false, true);
        }
    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            ownerJoinCircleLists.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(myJoinAllData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    private void searchMore(ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        searchData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(searchData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (ownerJoinCircleLists == null) {
            return;
        }
        ownerJoinCircleLists.loadMoreFinish(false, true);
    }

    private void loadMore(ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        myJoinAllData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(myJoinAllData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        ownerJoinCircleLists.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private MyJoinCircleInterface myJoinCircleInterface = new MyJoinCircleInterface() {
        @Override
        public void response(MyJoinCircleResponse myJoinCircleResponse) {
            if (!isAdded()) {
                return;
            }
            if (notFoundData == null) {
                return;
            }
            if (myJoinCircleResponse.getError() == 0) {
                LocalLog.d(TAG, myJoinCircleResponse.getMessage());
                notFoundData.setVisibility(View.GONE);
                joinCircleSwipe.setVisibility(View.VISIBLE);
                if (!isSearch) {
                    pageCount = myJoinCircleResponse.getData().getPagenation().getTotalPage();
                    LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    loadMore((ArrayList<MyJoinCircleResponse.DataBeanX.DataBean>) myJoinCircleResponse.getData().getData());
                    if (pageIndex == 1) {
                        ownerJoinCircleLists.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LocalLog.d(TAG, "滑动到顶端");
                                if (ownerJoinCircleLists != null) {
                                    ownerJoinCircleLists.scrollToPosition(0);
                                }
                            }
                        }, 10);
                    }
                    pageIndex++;
                    mCurrentIndex = pageIndex;
                } else {
                    pageCount = myJoinCircleResponse.getData().getPagenation().getTotalPage();
                    LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    searchMore((ArrayList<MyJoinCircleResponse.DataBeanX.DataBean>) myJoinCircleResponse.getData().getData());
                    if (pageIndex == 1) {
                        ownerJoinCircleLists.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LocalLog.d(TAG, "滑动到顶端");
                                ownerJoinCircleLists.scrollToPosition(0);
                            }
                        }, 10);
                    }
                    pageIndex++;
                }
            } else if (myJoinCircleResponse.getError() == 1) {
                if (pageIndex == 1) {
                    LocalLog.d(TAG, "显示无数据界面");
                    notFoundData.setVisibility(View.VISIBLE);
                    joinCircleSwipe.setVisibility(View.GONE);
                } else {
                    LocalLog.d(TAG, "其他页无数据!");
                }
            } else if (myJoinCircleResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (!isAdded()) {
                return;
            }
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
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
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_DETAIL) {
                    LocalLog.d(TAG, "执行过删除成员的操作2");
                    if (data != null) {
                        LocalLog.d(TAG, "action =  " + data.getAction());
                        int position = -1;
                        switch (data.getAction()) {
                            case QUIT_ACTION:
                                position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                                LocalLog.d(TAG, "退出");
                                adapter.notifyItemRemove(position);
                                break;
                            default:
                                break;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
