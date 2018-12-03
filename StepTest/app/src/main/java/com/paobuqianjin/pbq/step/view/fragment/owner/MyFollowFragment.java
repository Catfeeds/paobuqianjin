package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
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

public class MyFollowFragment extends BaseFragment {
    private final static String TAG = MyFollowFragment.class.getSimpleName();
    @Bind(R.id.invite_dan_recycler)
    SwipeMenuRecyclerView inviteDanRecycler;
    LinearLayoutManager layoutManager;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private FollowAdapter adapter;
    DefineLoadMoreView loadMoreView;
    private int pageIndexMyFollow = 1;
    private int pageMyFollowCount = 0;
    private String keyWord = "";
    ArrayList<UserIdFollowResponse.DataBeanX.DataBean> myFollowData = new ArrayList<>();
    private boolean isSearch = false;
    private int mCurrentIndex = 1;
    ArrayList<UserIdFollowResponse.DataBeanX.DataBean> searchData = new ArrayList<>();

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
        layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        inviteDanRecycler.setLayoutManager(layoutManager);
        adapter = new FollowAdapter(getActivity(), null);
        inviteDanRecycler.setAdapter(adapter);
        loadMoreView = new DefineLoadMoreView(getContext());
        inviteDanRecycler.addFooterView(loadMoreView); // 添加为Footer。
        inviteDanRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        inviteDanRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        loadMyFollowData(myFollowData);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        Presenter.getInstance(getContext()).getFollows("my", pageIndexMyFollow, PAGE_SIZE_DEFAULT, keyWord, myFollowCallBack);
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
            pageIndexMyFollow = mCurrentIndex;
            loadMyFollowData(myFollowData);
            pageMyFollowCount = 0;
            return;
        }
        isSearch = true;
        searchData = new ArrayList<>();
        loadMyFollowData(searchData);
        pageIndexMyFollow = 1;
        Presenter.getInstance(getContext()).getFollows("my", pageIndexMyFollow, PAGE_SIZE_DEFAULT, keyWord, myFollowCallBack);
    }

    public void update() {
        if (isAdded()) {
            pageIndexMyFollow = 1;
            myFollowData.clear();
            searchData.clear();
            adapter.notifyDataSetChanged(null);
            Presenter.getInstance(getContext()).getFollows("my", pageIndexMyFollow, PAGE_SIZE_DEFAULT, keyWord, myFollowCallBack);
        }
    }

    private InnerCallBack myFollowCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (!isAdded()) {
                return;
            }
            if (object instanceof UserIdFollowResponse) {
                if (((UserIdFollowResponse) object).getError() == 0) {
                    if (((UserIdFollowResponse) object).getData() != null) {
                        //followOtoFragment.setAdapter(new FollowAdapter(getContext(), userFollowOtOResponse.getData().getData()));
                        if (!isSearch) {
                            if (notFoundData != null) {
                                notFoundData.setVisibility(View.GONE);
                                inviteDanRecycler.setVisibility(View.VISIBLE);
                            }
                            pageMyFollowCount = ((UserIdFollowResponse) object).getData().getPagenation().getTotalPage();
                            LocalLog.d(TAG, "pageIndexFollowOto = " + pageIndexMyFollow + "  , pageFollowOtoCount = " + pageMyFollowCount);
                            loadMyFollowMore((ArrayList<UserIdFollowResponse.DataBeanX.DataBean>) ((UserIdFollowResponse) object).getData().getData());
                            if (pageIndexMyFollow == 1) {
                                scrollTop();
                            }
                            pageIndexMyFollow++;
                            mCurrentIndex = pageIndexMyFollow;
                        } else {
                            pageMyFollowCount = ((UserIdFollowResponse) object).getData().getPagenation().getTotalPage();
                            LocalLog.d(TAG, "pageIndexFollowOto = " + pageIndexMyFollow + "  , pageFollowOtoCount = " + pageMyFollowCount);
                            loadSearch((ArrayList<UserIdFollowResponse.DataBeanX.DataBean>) ((UserIdFollowResponse) object).getData().getData());
                            if (pageIndexMyFollow == 1) {
                                scrollTop();
                            }
                            pageIndexMyFollow++;
                        }
                    }
                } else if (((UserIdFollowResponse) object).getError() == 100) {
                    LocalLog.d(TAG, "Token 过期!");
                    exitTokenUnfect();
                } else if (((UserIdFollowResponse) object).getError() == 1) {
                    LocalLog.d(TAG, "Not found Data");
                    if (pageIndexMyFollow == 1) {
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

    private void loadMyFollowData(ArrayList<UserIdFollowResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadMyFollowData() enter");
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


    private void loadSearch(ArrayList<UserIdFollowResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadMyFollowMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
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

    private void loadMyFollowMore(ArrayList<UserIdFollowResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadMyFollowMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        myFollowData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(myFollowData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (isAdded() && inviteDanRecycler != null) {
            inviteDanRecycler.loadMoreFinish(false, true);
        }

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
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            LocalLog.d(TAG, "加载更多!");

            if (pageIndexMyFollow == 0) {
                LocalLog.d(TAG, "第一次刷新");
            } else {
                if (pageIndexMyFollow > pageMyFollowCount) {
                   /* Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();*/
                    inviteDanRecycler.loadMoreFinish(false, true);
                    return;
                }
            }
            Presenter.getInstance(getContext()).getFollows("my", pageIndexMyFollow, PAGE_SIZE_DEFAULT, keyWord, myFollowCallBack);
        }
    };

    public void scrollTop() {
        if (inviteDanRecycler != null) {
            inviteDanRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (inviteDanRecycler != null) {
                        inviteDanRecycler.scrollToPosition(0);
                    }
                }
            }, 10);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
