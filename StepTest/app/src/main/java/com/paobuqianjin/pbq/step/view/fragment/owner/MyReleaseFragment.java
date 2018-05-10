package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.MyReleaseActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyReleaseTaskAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.fragment.circle.AttentionCircleFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/24.
 */

public class MyReleaseFragment extends BaseBarStyleTextViewFragment implements MyReleaseTaskInterface {
    private final static String TAG = MyReleaseFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.my_release_recycler)
    SwipeMenuRecyclerView myReleaseRecycler;
    LinearLayoutManager layoutManager;
    @Bind(R.id.go_to_release)
    TextView goToRelease;
    @Bind(R.id.no_release_record)
    RelativeLayout noReleaseRecord;
    @Bind(R.id.my_release_scroll)
    SwipeRefreshLayout myReleaseScroll;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE = 10;
    MyReleaseTaskAdapter adapter;
    ArrayList<TaskMyReleaseResponse.DataBeanX.DataBean> myReleaseData = new ArrayList<>();

    @Override

    protected int getLayoutResId() {
        return R.layout.my_release_fg;
    }

    @Override
    protected String title() {
        return "我的发布";
    }

    @Override
    public Object right() {
        return "记录";
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {
            getActivity().onBackPressed();
        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "点击查看记录");
            startActivity(ReleaseRecordActivity.class, null);
        }
    };

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
        Presenter.getInstance(getContext()).taskMyRelease(pageIndex, PAGE_SIZE);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        myReleaseScroll = (SwipeRefreshLayout) viewRoot.findViewById(R.id.my_release_scroll);
        setToolBarListener(toolBarListener);
        layoutManager = new LinearLayoutManager(getContext());
        myReleaseRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.my_release_recycler);
        myReleaseRecycler.setLayoutManager(layoutManager);

        noReleaseRecord = (RelativeLayout) viewRoot.findViewById(R.id.no_release_record);
        goToRelease = (TextView) viewRoot.findViewById(R.id.go_to_release);

        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        myReleaseRecycler.addFooterView(loadMoreView); // 添加为Footer。
        myReleaseRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        myReleaseRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new MyReleaseTaskAdapter(getContext(), null);
        myReleaseRecycler.setAdapter(adapter);

        myReleaseScroll.setOnRefreshListener(mRefreshListener);
        loadData(myReleaseData);
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            myReleaseRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            if (getContext() == null) {
                                return;
                            }
                            Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                            myReleaseRecycler.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).taskMyRelease(pageIndex, PAGE_SIZE);
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
            myReleaseRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(myReleaseData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<TaskMyReleaseResponse.DataBeanX.DataBean> dataBeans) {

        adapter.notifyDataSetChanged(dataBeans);

        if (myReleaseScroll == null) {
            return;
        }
        myReleaseScroll.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            myReleaseRecycler.loadMoreFinish(true, true);
        } else {
            myReleaseRecycler.loadMoreFinish(false, true);
        }
    }


    private void loadMore(ArrayList<TaskMyReleaseResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        myReleaseData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(myReleaseData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (myReleaseRecycler == null) {
            return;
        }
        myReleaseRecycler.loadMoreFinish(false, true);

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
    public void response(TaskMyReleaseResponse taskMyReleaseResponse) {
        LocalLog.d(TAG, "TaskMyReleaseResponse() enter");
        if (taskMyReleaseResponse.getError() == 1) {
            if (noReleaseRecord.getVisibility() == View.GONE) {
                noReleaseRecord.setVisibility(View.VISIBLE);
                if (myReleaseScroll.getVisibility() != View.GONE) {
                    myReleaseScroll.setVisibility(View.GONE);
                }
                TextView release = (TextView) noReleaseRecord.findViewById(R.id.go_to_release);
                release.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(TaskReleaseActivity.class, null);
                    }
                });
            }
        } else if (taskMyReleaseResponse.getError() == -1) {

        } else if (taskMyReleaseResponse.getError() == 0) {
            if (noReleaseRecord == null) {
                return;
            }
            if (noReleaseRecord.getVisibility() == View.VISIBLE) {
                noReleaseRecord.setVisibility(View.GONE);
            }
            if (myReleaseScroll.getVisibility() != View.VISIBLE) {
                myReleaseScroll.setVisibility(View.VISIBLE);
            }
            pageCount = taskMyReleaseResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
            loadMore((ArrayList<TaskMyReleaseResponse.DataBeanX.DataBean>) taskMyReleaseResponse.getData().getData());
            if (pageIndex == 1) {
                myReleaseRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LocalLog.d(TAG, "滑动到顶端");
                        myReleaseRecycler.scrollToPosition(0);
                    }
                }, 10);
            }
            pageIndex++;
        } else if (taskMyReleaseResponse.getError() == -100) {
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
        LocalLog.d(TAG, "ErrorCode() enter " + errorCode.getMessage());
        if (errorCode.getError() == 1) {
            LocalLog.d(TAG, "没有数据");
            if (myReleaseScroll.getVisibility() == View.VISIBLE) {
                myReleaseScroll.setVisibility(View.GONE);
                goToRelease.setOnClickListener(onClickListener);
            }
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.go_to_release:
                    startActivity(TaskReleaseActivity.class, null);
                    break;
            }
        }
    };
}
