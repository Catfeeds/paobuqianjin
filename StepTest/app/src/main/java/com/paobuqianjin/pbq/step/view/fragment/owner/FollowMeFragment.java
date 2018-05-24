package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.FollowAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.paobuqianjin.pbq.step.utils.Utils.PAGE_SIZE_DEFAULT;

/**
 * Created by pbq on 2018/3/1.
 */

public class FollowMeFragment extends BaseFragment {
    private final static String TAG = FollowMeFragment.class.getSimpleName();
    @Bind(R.id.invite_dan_recycler)
    SwipeMenuRecyclerView inviteDanRecycler;
    LinearLayoutManager layoutManager;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private FollowAdapter adapter;
    DefineLoadMoreView loadMoreView;
    ArrayList<FollowUserResponse.DataBeanX.DataBean> followMeData = new ArrayList<>();
    private int pageIndexFollowMe = 1, pageFollowMeCount = 0;
    private String keyWord = "";
    private boolean isSearch = false;
    private int mCurrentIndex = 1;
    ArrayList<FollowUserResponse.DataBeanX.DataBean> searchData;

    @Override
    protected int getLayoutResId() {
        return R.layout.invite_dan_fg;
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

        inviteDanRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.invite_dan_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        inviteDanRecycler.setLayoutManager(layoutManager);
        adapter = new FollowAdapter(getActivity(), null);
        inviteDanRecycler.setAdapter(adapter);
        loadMoreView = new DefineLoadMoreView(getContext());
        inviteDanRecycler.addFooterView(loadMoreView); // 添加为Footer。
        inviteDanRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        inviteDanRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        loadFollowMeData(followMeData);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        Presenter.getInstance(getContext()).getFollows("me", pageFollowMeCount, PAGE_SIZE_DEFAULT, keyWord, folloeMeCallBack);
    }

    public void searchKeyWord(String keyWord) {
        this.keyWord = keyWord;
        LocalLog.d(TAG, "keyWord = " + keyWord);
        if (TextUtils.isEmpty(keyWord)) {
            LocalLog.d(TAG, "显示旧数据");
            isSearch = false;
            if (notFoundData == null) {
                return;
            }
            notFoundData.setVisibility(View.GONE);
            inviteDanRecycler.setVisibility(View.VISIBLE);
            pageIndexFollowMe = mCurrentIndex;
            loadFollowMeData(followMeData);
            pageFollowMeCount = 0;
            return;
        }
        isSearch = true;
        searchData = new ArrayList<>();
        loadFollowMeData(searchData);
        pageIndexFollowMe = 1;
        Presenter.getInstance(getContext()).getFollows("me", pageIndexFollowMe, PAGE_SIZE_DEFAULT, keyWord, folloeMeCallBack);
    }

    public void update() {
        if (isAdded()) {
            pageIndexFollowMe = 1;
            followMeData.clear();
            searchData.clear();
            adapter.notifyDataSetChanged(null);
            Presenter.getInstance(getContext()).getFollows("me", pageIndexFollowMe, PAGE_SIZE_DEFAULT, keyWord, folloeMeCallBack);
        }
    }

    private InnerCallBack folloeMeCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof FollowUserResponse) {
                if (((FollowUserResponse) object).getError() == 0) {
                    if (((FollowUserResponse) object).getData() != null) {
                        //followOtoFragment.setAdapter(new FollowAdapter(getContext(), userFollowOtOResponse.getData().getData()));
                        if (!isSearch) {
                            if (notFoundData != null) {
                                notFoundData.setVisibility(View.GONE);
                                inviteDanRecycler.setVisibility(View.VISIBLE);
                            }
                            pageFollowMeCount = ((FollowUserResponse) object).getData().getPagenation().getTotalPage();
                            LocalLog.d(TAG, "pageIndexFollowMe = " + pageIndexFollowMe + "  , pageFollowMeCount = " + pageFollowMeCount);
                            loadFollowMeMore((ArrayList<FollowUserResponse.DataBeanX.DataBean>) ((FollowUserResponse) object).getData().getData());
                            if (pageIndexFollowMe == 1) {
                                scrollTop();
                            }
                            pageIndexFollowMe++;
                            mCurrentIndex = pageIndexFollowMe;
                        } else {
                            pageFollowMeCount = ((FollowUserResponse) object).getData().getPagenation().getTotalPage();
                            LocalLog.d(TAG, "pageIndexFollowMe = " + pageIndexFollowMe + "  , pageFollowMeCount = " + pageFollowMeCount);
                            loadSearch((ArrayList<FollowUserResponse.DataBeanX.DataBean>) ((FollowUserResponse) object).getData().getData());
                            if (pageIndexFollowMe == 1) {
                                scrollTop();
                            }
                            pageIndexFollowMe++;
                        }
                    }
                } else if (((FollowUserResponse) object).getError() == 100) {
                    LocalLog.d(TAG, "Token 过期!");
                    exitTokenUnfect();
                } else if (((FollowUserResponse) object).getError() == 1) {
                    LocalLog.d(TAG, "Not found Data");
                    if (pageIndexFollowMe == 1) {
                        if (notFoundData != null) {
                            notFoundData.setVisibility(View.VISIBLE);
                            inviteDanRecycler.setVisibility(View.GONE);
                        }
                    }
                }
            } else if (object instanceof ErrorCode) {

            }
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            LocalLog.d(TAG, "加载更多!");
            if (pageFollowMeCount == 0) {
                LocalLog.d(TAG, "第一次刷新");
            } else {
                if (pageIndexFollowMe > pageFollowMeCount) {
                    Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                    inviteDanRecycler.loadMoreFinish(false, true);
                    return;
                }
            }

            Presenter.getInstance(getContext()).getFollows("me", pageFollowMeCount, PAGE_SIZE_DEFAULT, keyWord, folloeMeCallBack);
        }
    };

    private void loadFollowMeData(ArrayList<FollowUserResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadFollowMeData() enter");
        adapter.notifyDataSetChanged(dataBeans);


        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            inviteDanRecycler.loadMoreFinish(true, true);
        } else {
            inviteDanRecycler.loadMoreFinish(false, true);
        }
    }

    private void loadSearch(ArrayList<FollowUserResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadFollowMeMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        for (int i = 0; i < newData.size(); i++) {
            for (int j = 0; j < searchData.size(); j++) {
                if (searchData.get(j).getUserid() == newData.get(i).getUserid()) {
                    LocalLog.d(TAG, "重复数据");
                    newData.remove(i);
                }
            }
        }
        searchData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(searchData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        inviteDanRecycler.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private void loadFollowMeMore(ArrayList<FollowUserResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadFollowMeMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        for (int i = 0; i < newData.size(); i++) {
            for (int j = 0; j < followMeData.size(); j++) {
                if (followMeData.get(j).getUserid() == newData.get(i).getUserid()) {
                    LocalLog.d(TAG, "重复数据");
                    newData.remove(i);
                }
            }
        }
        followMeData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(followMeData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        inviteDanRecycler.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    public void add(FollowUserResponse.DataBeanX.DataBean dataBean) {
        followMeData.add(dataBean);
        if (isAdded() && adapter != null) {
            adapter.notifyItemRangeChanged(followMeData.size() - 1, 1);
        }
    }

    public void scrollTop() {
        if (inviteDanRecycler != null) {
            inviteDanRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    inviteDanRecycler.scrollToPosition(0);
                }
            }, 10);
        }
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
            LocalLog.d(TAG, "this = " + this);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
