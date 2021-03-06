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
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyCreatCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashMyCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateFragment extends BaseFragment {
    private final static String TAG = OwnerCreateFragment.class.getSimpleName();
    @Bind(R.id.owner_create_circle_lists)
    SwipeMenuRecyclerView ownerCreateCircleLists;
    @Bind(R.id.create_circle_swipe)
    SwipeRefreshLayout createCircleSwipe;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private LinearLayoutManager layoutManager;
    private ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> ownerCreateCircleData;
    private boolean isRefresh;
    private int pageIndex = 1;
    private int pageCount = 0;
    ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> myCreateAllData = new ArrayList<>();
    ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> searchData;
    OwnerCreateAdapter adapter;
    private final static int PAGE_SIZE_DEFAULT = 10;
    private String keyWord = "";
    private boolean isSearch = false;
    private int mCurrentIndex = 1;
    private final int REQUEST_MEMBER = 200;
    private final int REQUEST_DETAIL = 100;
    private final static String QUIT_ACTION = "com.paobuqianjin.pbq.step.QUIT";
    private final static String DELETE_ACTION = "com.paobuqianjin.pbq.step.DELETE_CIRCLE";
    private final static String DELETE_MEMBER = "com.paobuqianjin.pbq.step.DELETE_MEMBER";
    private final static String CIRCLE_EDIT = "com.paobuqianjin.pbq.step.EDIT_CIRCLE";
    private final static String SELECT_CIRCLE_ACTION = "com.paobuqianjin.pbq.SELECT_ACTION";
    private String currentAction = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void searchKeyWord(String keyWord) {
        this.keyWord = keyWord;
        if (TextUtils.isEmpty(keyWord)) {
            LocalLog.d(TAG, "显示旧数据");
            isSearch = false;
            if (notFoundData == null) {
                return;
            }
            notFoundData.setVisibility(View.GONE);
            createCircleSwipe.setVisibility(View.VISIBLE);
            pageIndex = mCurrentIndex;
            loadData(myCreateAllData);
            pageCount = 0;
            return;
        }
        isSearch = true;
        searchData = new ArrayList<>();
        loadData(searchData);
        pageIndex = 1;
        Presenter.getInstance(getContext()).getMyCreateCirlce(pageIndex, PAGE_SIZE_DEFAULT, keyWord);
    }

    public void refreshMyCreate() {
        if (isAdded()) {
            pageIndex = 1;
            notFoundData.setVisibility(View.GONE);
            createCircleSwipe.setVisibility(View.VISIBLE);
            pageCount = 0;
            myCreateAllData.clear();
            adapter.notifyDataSetChanged();
            isSearch = false;
            Presenter.getInstance(getContext()).getMyCreateCirlce(pageIndex, PAGE_SIZE_DEFAULT, "");
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_create_cirlce_fg;
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
        Presenter.getInstance(getActivity()).attachUiInterface(myCreatCircleInterface);
        Presenter.getInstance(getActivity()).attachUiInterface(reflashMyCircleInterface);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (SELECT_CIRCLE_ACTION.equals(intent.getAction())) {
                currentAction = SELECT_CIRCLE_ACTION;
            }
        }
        LocalLog.d(TAG, "initView() enter");
        ownerCreateCircleLists = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.owner_create_circle_lists);
        createCircleSwipe = (SwipeRefreshLayout) viewRoot.findViewById(R.id.create_circle_swipe);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ownerCreateCircleLists.setLayoutManager(layoutManager);
        ownerCreateCircleLists.setHasFixedSize(true);
        ownerCreateCircleLists.setNestedScrollingEnabled(false);
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        ownerCreateCircleLists.addFooterView(loadMoreView); // 添加为Footer。
        ownerCreateCircleLists.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        ownerCreateCircleLists.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        adapter = new OwnerCreateAdapter(getContext(), this, null);
        ownerCreateCircleLists.setAdapter(adapter);

        createCircleSwipe.setOnRefreshListener(mRefreshListener);
        loadData(myCreateAllData);
        Presenter.getInstance(getContext()).getMyCreateCirlce(pageIndex, PAGE_SIZE_DEFAULT, keyWord);
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            ownerCreateCircleLists.postDelayed(new Runnable() {
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
                            /*Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();*/
                            ownerCreateCircleLists.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).getMyCreateCirlce(pageIndex, PAGE_SIZE_DEFAULT, keyWord);

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
            ownerCreateCircleLists.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(myCreateAllData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> dataBeans) {
        if (!isAdded()) {
            return;
        }
        adapter.notifyDataSetChanged(dataBeans);
        if (createCircleSwipe == null) {
            return;
        }
        createCircleSwipe.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            ownerCreateCircleLists.loadMoreFinish(true, true);
        } else {
            ownerCreateCircleLists.loadMoreFinish(false, true);
        }
    }

    private void searchMore(ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        if (!isAdded()) {
            return;
        }
        searchData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(searchData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (ownerCreateCircleLists == null) {
            return;
        }
        ownerCreateCircleLists.loadMoreFinish(false, true);
    }

    private void loadMore(ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        if (!isAdded()) {
            return;
        }
        myCreateAllData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(myCreateAllData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (ownerCreateCircleLists == null) {
            return;
        }
        ownerCreateCircleLists.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private MyCreatCircleInterface myCreatCircleInterface = new MyCreatCircleInterface() {
        @Override
        public void response(MyCreateCircleResponse myCreateCircleResponse) {
            if (!isAdded()) {
                return;
            }
            try {
                if (myCreateCircleResponse.getError() == 0) {
                    LocalLog.d(TAG, myCreateCircleResponse.getMessage());
                    if (createCircleSwipe == null) {
                        return;
                    }
                    if (SELECT_CIRCLE_ACTION.equals(currentAction)) {
                        LocalLog.d(TAG, "选中圈子");
                        int size = myCreateCircleResponse.getData().getData().size();
                        for (int i = 0; i < size; i++) {
                            myCreateCircleResponse.getData().getData().get(i).setCan_select(true);
                        }
                    }
                    notFoundData.setVisibility(View.GONE);
                    createCircleSwipe.setVisibility(View.VISIBLE);
                    if (!isSearch) {
                        pageCount = myCreateCircleResponse.getData().getPagenation().getTotalPage();
                        LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                        loadMore((ArrayList<MyCreateCircleResponse.DataBeanX.DataBean>) myCreateCircleResponse.getData().getData());
                        if (pageIndex == 1) {
                            ownerCreateCircleLists.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    LocalLog.d(TAG, "滑动到顶端");
                                    ownerCreateCircleLists.scrollToPosition(0);
                                }
                            }, 10);
                        }
                        pageIndex++;
                        mCurrentIndex = pageIndex;
                    } else {
                        pageCount = myCreateCircleResponse.getData().getPagenation().getTotalPage();
                        LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                        searchMore((ArrayList<MyCreateCircleResponse.DataBeanX.DataBean>) myCreateCircleResponse.getData().getData());
                        if (pageIndex == 1) {
                            ownerCreateCircleLists.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    LocalLog.d(TAG, "滑动到顶端");
                                    ownerCreateCircleLists.scrollToPosition(0);
                                }
                            }, 10);
                        }
                        pageIndex++;
                    }

                } else if (myCreateCircleResponse.getError() == 1) {
                    if (pageIndex == 1) {
                        LocalLog.d(TAG, "显示无数据界面");
                        if (notFoundData == null) {
                            return;
                        }
                        notFoundData.setVisibility(View.VISIBLE);
                        createCircleSwipe.setVisibility(View.GONE);
                    } else {
                        LocalLog.d(TAG, "其他页无数据!");
                    }
                } else if (myCreateCircleResponse.getError() == -100) {
                    LocalLog.d(TAG, "Token 过期!");
                    exitTokenUnfect();
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    private ReflashMyCircleInterface reflashMyCircleInterface = new ReflashMyCircleInterface() {
        @Override
        public void response(MyCreateCircleResponse myCreateCircleResponse) {
            LocalLog.d(TAG, " Reflash MyCreateCircleResponse()");
            isRefresh = false;
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
        return "我创建的";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(getContext()).dispatchUiInterface(reflashMyCircleInterface);
        Presenter.getInstance(getContext()).dispatchUiInterface(myCreatCircleInterface);
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_MEMBER) {
                    LocalLog.d(TAG, "执行过删除成员的操作1");
                    if (data != null) {
                        int position = -1, memberNum = -1;
                        position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                        memberNum = data.getIntExtra(getContext().getPackageName() + "memberNum", -1);
                        LocalLog.d(TAG, "删除圈子 position =  " + position + "memberNum = " + memberNum);
                        if (position != -1) {
                            adapter.notifyItemDataChange(position, memberNum);
                        }
                    }
                } else if (requestCode == REQUEST_DETAIL) {
                    LocalLog.d(TAG, "执行过删除成员的操作2");
                    if (data != null) {
                        LocalLog.d(TAG, "action =  " + data.getAction());
                        int position = -1, memberNum = -1;
                        String circleName = "";
                        switch (data.getAction()) {
                            case QUIT_ACTION:
                                position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                                LocalLog.d(TAG, "退出");
                                adapter.notifyItemRemove(position);
                                break;
                            case DELETE_ACTION:
                                position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                                LocalLog.d(TAG, "删除圈子 position =  " + position);
                                adapter.notifyItemRemove(position);
                                break;
                            case DELETE_MEMBER:
                                position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                                memberNum = data.getIntExtra(getContext().getPackageName() + "memberNum", -1);
                                LocalLog.d(TAG, "删除圈子 position =  " + position + "memberNum = " + memberNum);
                                adapter.notifyItemDataChange(position, memberNum);
                                break;
                            case CIRCLE_EDIT:
                                position = data.getIntExtra(getContext().getPackageName() + "position", -1);
                                circleName = data.getStringExtra(getContext().getPackageName() + "changeName");
                                LocalLog.d(TAG, "删除圈子 position =  " + position + "circleName = " + circleName);
                                adapter.notifyItemDataChange(position, circleName);
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
