package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InOutCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SearchCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.adapter.SearchCircleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleStyleTextViewFragment extends BaseBarStyleTextViewFragment implements SearchCircleInterface {
    private final static String TAG = SearchCircleStyleTextViewFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.search_hot_circle)
    RelativeLayout searchHotCircle;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.search_choose_circle_recycler)
    SwipeMenuRecyclerView searchChooseCircleRecycler;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private LinearLayoutManager layoutManager;
    private SwipeMenuRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> choiceCircleData;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> searchData;
    SearchCircleAdapter adapter;
    int pageIndex = 2;
    int pageCount = 0;
    boolean addCircle = false;
    private boolean isSearch = false;
    private String keyWord = "";
    private int mCurrentAllPageIndex = 2;
    private ArrayList<Integer> circleIdArray = new ArrayList<>();

    public void setChoiceCircleData(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> choiceCircleData) {
        this.choiceCircleData = choiceCircleData;
        if (choiceCircleData == null) {
            //重新获取
            return;
        }
        LocalLog.d(TAG, "setChoiceCircleData() enter" + choiceCircleData.toString());

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.search_hot_circle_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocalLog.d(TAG, "");
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);

        recyclerView = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.search_choose_circle_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        swipeRefreshLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(mRefreshListener);
        recyclerView.setSwipeItemClickListener(mItemClickListener);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);

        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        recyclerView.addFooterView(loadMoreView); // 添加为Footer。
        recyclerView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new SearchCircleAdapter(this.getContext(), getActivity(), inOutCallBackInterface, this);
        recyclerView.setAdapter(adapter);

        Presenter.getInstance(getContext()).attachUiInterface(this);
        searchIcon = (RelativeLayout) viewRoot.findViewById(R.id.search_icon);
        searchCircleText = (EditText) viewRoot.findViewById(R.id.search_circle_text);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchKeyWord(searchCircleText.getText().toString());
                    Utils.hideInput(getContext());
                }
                return false;
            }
        });
        searchCircleText.addTextChangedListener(textWatcher);
        cancelIcon = (RelativeLayout) viewRoot.findViewById(R.id.cancel_icon);
        cancelIcon.setOnClickListener(onClickListener);
        if (choiceCircleData == null) {
            LocalLog.d(TAG, "choiceCircleData null");
            pageIndex = 1;
            choiceCircleData = new ArrayList<>();
            Presenter.getInstance(getContext()).getMoreCircle(pageIndex, Utils.PAGE_SIZE_DEFAULT, keyWord);
        }
        loadData(choiceCircleData);
        /*searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance(getContext()).getMoreCircle(1, Utils.PAGE_SIZE_DEFAULT, searchCircleText.getText().toString());
            }
        });*/
    }

    public void searchKeyWord(String keyWord) {
        if (TextUtils.isEmpty(keyWord)) {
            Toast.makeText(getContext(), "搜索关键字不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        isSearch = true;
        this.keyWord = keyWord;
        LocalLog.d(TAG, "pageIndex = " + pageIndex);
        pageIndex = 1;
        searchData = new ArrayList<>();
        loadData(searchData);
        Presenter.getInstance(getContext()).getMoreCircle(pageIndex, Utils.PAGE_SIZE_DEFAULT, keyWord);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            if (!TextUtils.isEmpty(temp)) {
                LocalLog.d(TAG, "显示取消搜索界面");
                cancelIcon.setVisibility(View.VISIBLE);
                cancelIcon.setOnClickListener(onClickListener);
            } else {
                LocalLog.d(TAG, "隐藏搜索界面");
                cancelIcon.setVisibility(View.GONE);
                if (notFoundData != null && notFoundData.getVisibility() == View.VISIBLE) {
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    notFoundData.setVisibility(View.GONE);
                }
                if (choiceCircleData != null) {
                    loadData(choiceCircleData);
                }
                pageIndex = mCurrentAllPageIndex;
                keyWord = "";
                pageCount = 0;
            }
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cancel_icon:
                    LocalLog.d(TAG, "取消搜索,显示原来的数据");
                    searchCircleText.setText(null);
                    isSearch = false;
                    keyWord = "";
                    pageCount = 0;
                    pageIndex = mCurrentAllPageIndex;
                    if (notFoundData != null && notFoundData.getVisibility() == View.VISIBLE) {
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        notFoundData.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };
    private InOutCallBackInterface inOutCallBackInterface = new InOutCallBackInterface() {
        @Override
        public void inCallBack(int circleid) {
            LocalLog.d(TAG, "circleid = " + circleid);
            circleIdArray.add(circleid);
            addCircle = true;
        }

        @Override
        public void outCallBack() {

        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        adapter.notifyDataSetChanged(data);

        swipeRefreshLayout.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        recyclerView.loadMoreFinish(false, true);
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            recyclerView.postDelayed(new Runnable() {
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
                            recyclerView.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    LocalLog.d(TAG, "pageIndex = " + pageIndex + ", keyWord = " + keyWord);
                    Presenter.getInstance(getContext()).getMoreCircle(pageIndex, Utils.PAGE_SIZE_DEFAULT, keyWord);
                }
            }, 1000);
        }
    };

    private void searchMore(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> newData) {
        searchData.addAll(newData);
        adapter.notifyItemRangeInserted(searchData.size() - newData.size(), newData.size());
        recyclerView.loadMoreFinish(false, true);
    }

    private void loadMore(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        if (choiceCircleData.size() == 0) {
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.scrollToPosition(0);
                }
            }, 100);
        }
        choiceCircleData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(choiceCircleData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        recyclerView.loadMoreFinish(false, true);

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
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(choiceCircleData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    @Override
    protected String title() {
        return "精选圈子";
    }

    @Override
    public void response(ErrorCode errorCode) {
        recyclerView.loadMoreFinish(false, true);
    }

    @Override
    public void response(ChoiceCircleResponse choiceCircleResponse) {
        if (!isAdded()) {
            return;
        }
        if (choiceCircleResponse.getError() == 0) {
            if (notFoundData != null && notFoundData.getVisibility() == View.VISIBLE) {
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                notFoundData.setVisibility(View.GONE);
            }
            if (!isSearch) {
                pageCount = choiceCircleResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                loadMore((ArrayList<ChoiceCircleResponse.DataBeanX.DataBean>) choiceCircleResponse.getData().getData());
                pageIndex++;
                mCurrentAllPageIndex = pageIndex;
            } else {
                pageCount = choiceCircleResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                searchMore((ArrayList<ChoiceCircleResponse.DataBeanX.DataBean>) choiceCircleResponse.getData().getData());
                pageIndex++;
            }

        } else if (choiceCircleResponse.getError() == 1) {
            if (pageIndex == 1) {
                recyclerView.loadMoreFinish(false, true);
                swipeRefreshLayout.setVisibility(View.GONE);
                notFoundData.setVisibility(View.VISIBLE);
            }
        } else if (choiceCircleResponse.getError() == -1) {
            recyclerView.loadMoreFinish(false, true);
        } else if (choiceCircleResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        ButterKnife.unbind(this);
        LocalLog.d(TAG, "onDestroyView() enter");
        if (addCircle) {
            LocalLog.d(TAG, "进行过加入圈子的操作");
            Intent intent = new Intent();
            int size = circleIdArray.size();
            LocalLog.d(TAG, "size = " + size);
            if (size > 0) {
                int[] circleIds = new int[size];
                for (int i = 0; i < size; i++) {
                    circleIds[i] = circleIdArray.get(i);
                }
                intent.putExtra(getContext().getPackageName() + "circleids", circleIds);
            }
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }
}
