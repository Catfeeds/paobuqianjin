package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.ui.pager.adapter.ImagePagerAdapter;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.OnPhotoTapListener;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyDynamicInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.DynamicActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.AttentionCircleAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyDynamicAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.wheelpicker.widgets.WheelDatePicker;
import com.paobuqianjin.pbq.step.view.fragment.circle.AttentionCircleFragment;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyDynamicFragment extends BaseBarStyleTextViewFragment implements MyDynamicInterface {
    private final static String TAG = MyDynamicFragment.class.getSimpleName();
    private final static int REQUEST_DETAIL = 0;
    LinearLayoutManager layoutManager;
    int pageIndex = 1;
    int pageCount = 0;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.my_dynamic_recycler)
    SwipeMenuRecyclerView myDynamicRecycler;
    @Bind(R.id.my_dynamic_swip)
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<DynamicPersonResponse.DataBeanX.DataBean> dynamicAllData = new ArrayList<>();
    MyDynamicAdapter adapter;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;
    private int mScreenWidth;
    private int mScreenHeight;

    @Override

    protected String title() {
        return "我的动态";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.my_dynamic_fg;
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
        Presenter.getInstance(getContext()).getMyDynamic(pageIndex, Utils.PAGE_SIZE_DEFAULT);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
        mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
        myDynamicRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.my_dynamic_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myDynamicRecycler.setLayoutManager(layoutManager);

        //myDynamicRecycler.setAdapter(new MyDynamicAdapter(getContext()));
        adapter = new MyDynamicAdapter(getActivity(), null, popBigImageInterface);
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        myDynamicRecycler.addFooterView(loadMoreView); // 添加为Footer。
        myDynamicRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        myDynamicRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        myDynamicRecycler.setSwipeItemClickListener(mItemClickListener);

        myDynamicRecycler.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.my_dynamic_swip);
        swipeRefreshLayout.setOnRefreshListener(mRefreshListener);

        loadData(dynamicAllData);
    }

    public interface PopBigImageInterface {
        public void popImageView(String url);
    }

    private PopBigImageInterface popBigImageInterface = new PopBigImageInterface() {
        @Override
        public void popImageView(String url) {
            LocalLog.d(TAG, "查看大图");
            popBirthSelectView = View.inflate(getContext(), R.layout.image_big_view, null);
            PhotoView photoView = (PhotoView) popBirthSelectView.findViewById(R.id.photo_view);
            ImageDataModel.getInstance().getDisplayer().display(getContext(), url, photoView, mScreenWidth, mScreenHeight);
            popupSelectWindow = new PopupWindow(popBirthSelectView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popImageVie dismiss() ");
                    popupSelectWindow = null;
                }
            });

            popupSelectWindow.setFocusable(true);
            popupSelectWindow.setOutsideTouchable(true);
            popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.my_dynamic_fg), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }
    };

    private void loadMore(ArrayList<DynamicPersonResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        dynamicAllData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(dynamicAllData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        myDynamicRecycler.loadMoreFinish(false, true);

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
            Intent intent = new Intent();
            intent.putExtra(getContext().getPackageName() + "dynamicId", dynamicAllData.get(position).getId());
            intent.putExtra(getContext().getPackageName() + "userId", dynamicAllData.get(position).getUserid());
            intent.putExtra(getContext().getPackageName() + "is_vote", dynamicAllData.get(position).getIs_vote());
            intent.putExtra(getContext().getPackageName() + "position", position);
            intent.setClass(getContext(), DynamicActivity.class);
            startActivityForResult(intent, REQUEST_DETAIL);
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            myDynamicRecycler.postDelayed(new Runnable() {
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
                            myDynamicRecycler.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    Presenter.getInstance(getContext()).getMyDynamic(pageIndex, Utils.PAGE_SIZE_DEFAULT);

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
            myDynamicRecycler.postDelayed(new Runnable() {
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

    private void loadData(ArrayList<DynamicPersonResponse.DataBeanX.DataBean> dataBeans) {
        if (swipeRefreshLayout == null) {
            return;
        }
        adapter.notifyDataSetChanged(dataBeans);
        swipeRefreshLayout.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            myDynamicRecycler.loadMoreFinish(true, true);
        } else {
            myDynamicRecycler.loadMoreFinish(false, true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(DynamicPersonResponse dynamicPersonResponse) {
        LocalLog.d(TAG, "DynamicPersonResponse() enter" + dynamicPersonResponse.toString());
        if (dynamicPersonResponse.getError() == 0) {
            LocalLog.d(TAG, dynamicPersonResponse.getMessage());
            pageCount = dynamicPersonResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);

            loadMore((ArrayList<DynamicPersonResponse.DataBeanX.DataBean>) dynamicPersonResponse.getData().getData());
            if (pageIndex == 1) {
                myDynamicRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LocalLog.d(TAG, "滑动到顶端");
                        myDynamicRecycler.scrollToPosition(0);
                    }
                }, 100);
            }

            pageIndex++;
        } else if (dynamicPersonResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}