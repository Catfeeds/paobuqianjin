package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFollowOtOResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserIdFollowResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UserFollowInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AddFriendAddressActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.OwnerCreateAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.FollowAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.CustomViewPager;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.paobuqianjin.pbq.step.utils.Utils.PAGE_SIZE_DEFAULT;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyFriendFragment extends BaseBarStyleTextViewFragment implements UserFollowInterface {
    private final static String TAG = MyFriendFragment.class.getSimpleName();

    String[] titles = {"互相关注", "已关注", "关注我的"};
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.follow_tab)
    TabLayout followTab;
    @Bind(R.id.tab_span)
    RelativeLayout tabSpan;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.friend_recycler_viewpager)
    ViewPager friendRecyclerViewpager;
    @Bind(R.id.create_circle_swipe)
    SwipeRefreshLayout createCircleSwipe;
    private FollowOtoFragment followOtoFragment;
    private MyFollowFragment myFollowFragment;
    private FollowMeFragment followMeFragment;
    FollowAdapter followOtoAdapter, myFollowAdapter, followMeAdapter;
    ArrayList<UserFollowOtOResponse.DataBeanX.DataBean> followOtoData = new ArrayList<>();
    ArrayList<UserIdFollowResponse.DataBeanX.DataBean> myFollowData = new ArrayList<>();
    ArrayList<FollowUserResponse.DataBeanX.DataBean> followMeData = new ArrayList<>();
    private int pageIndexFollowOto = 1, pageIndexFollowMe = 1, pageIndexMyFollow = 1;
    private int pageFollowOtoCount = 0, pageFollowMeCount = 0, pageMyFollowCount = 0;
    private int selectPage = 0;

    @Override

    protected int getLayoutResId() {
        return R.layout.my_friend_fg;
    }

    @Override
    protected String title() {
        return "我的关注";
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
        Presenter.getInstance(getContext()).getFollows("mutual", pageIndexFollowOto, PAGE_SIZE_DEFAULT);
        Presenter.getInstance(getContext()).getFollows("my", pageIndexMyFollow, PAGE_SIZE_DEFAULT);
        Presenter.getInstance(getContext()).getFollows("me", pageIndexFollowMe, PAGE_SIZE_DEFAULT);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        setToolBarListener(toolBarListener);
        followTab = (TabLayout) viewRoot.findViewById(R.id.follow_tab);
        friendRecyclerViewpager = (ViewPager) viewRoot.findViewById(R.id.friend_recycler_viewpager);
        createCircleSwipe = (SwipeRefreshLayout) viewRoot.findViewById(R.id.create_circle_swipe);

        followOtoFragment = new FollowOtoFragment();
        myFollowFragment = new MyFollowFragment();
        followMeFragment = new FollowMeFragment();
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(followOtoFragment);
        fragments.add(myFollowFragment);
        fragments.add(followMeFragment);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);
        friendRecyclerViewpager.setAdapter(tabAdapter);


        followTab.setupWithViewPager(friendRecyclerViewpager);

        //
        followOtoAdapter = new FollowAdapter(getContext(), null);
        myFollowAdapter = new FollowAdapter(getContext(), null);
        followMeAdapter = new FollowAdapter(getContext(), null);
        followOtoFragment.setAdapter(followOtoAdapter);
        followMeFragment.setAdapter(followMeAdapter);
        myFollowFragment.setAdapter(myFollowAdapter);
        // 自定义的核心就是DefineLoadMoreView类。

        followOtoFragment.listen(mLoadMoreListener);
        followMeFragment.listen(mLoadMoreListener);
        myFollowFragment.listen(mLoadMoreListener);

        loadFollowOtOData(followOtoData);
        loadFollowMeData(followMeData);
        loadMyFollowData(myFollowData);
//
        createCircleSwipe.setOnRefreshListener(mRefreshListener);
        for (int i = 0; i < followTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            followTab.getTabAt(i).setCustomView(getTabView(i));
        }

        followTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(followTab, 10, 10);
            }
        });

        followTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LocalLog.d(TAG, "onTabSelected() enter" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        selectPage = 0;
                        break;
                    case 1:
                        selectPage = 1;
                    case 2:
                        selectPage = 2;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            LocalLog.d(TAG, "刷新当前页面!");
            if (selectPage == 0) {
                loadFollowOtOData(followOtoData);
            } else if (selectPage == 1) {
                loadMyFollowData(myFollowData);
            } else if (selectPage == 2) {
                loadFollowMeData(followMeData);
            }
        }
    };


    /**
     * 第一次加载数据。
     */
    private void loadFollowOtOData(ArrayList<UserFollowOtOResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadFollowOtOData() enter");
        followOtoAdapter.notifyDataSetChanged(dataBeans);

        createCircleSwipe.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            followOtoFragment.loadMoreFinish(true, true);
        } else {
            followOtoFragment.loadMoreFinish(false, true);
        }
    }

    private void loadFollowOtOMore(ArrayList<UserFollowOtOResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadFollowOtOMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        followOtoData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        followOtoAdapter.notifyItemRangeInserted(followOtoData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        followOtoFragment.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private void loadFollowMeData(ArrayList<FollowUserResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadFollowMeData() enter");
        followMeAdapter.notifyDataSetChanged(dataBeans);

        createCircleSwipe.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            followMeFragment.loadMoreFinish(true, true);
        } else {
            followMeFragment.loadMoreFinish(false, true);
        }
    }

    private void loadFollowMeMore(ArrayList<FollowUserResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadFollowMeMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        followMeData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        followMeAdapter.notifyItemRangeInserted(followMeData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        followMeFragment.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    private void loadMyFollowData(ArrayList<UserIdFollowResponse.DataBeanX.DataBean> dataBeans) {
        LocalLog.d(TAG, "loadMyFollowData() enter");
        myFollowAdapter.notifyDataSetChanged(dataBeans);

        createCircleSwipe.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            myFollowFragment.loadMoreFinish(true, true);
        } else {
            myFollowFragment.loadMoreFinish(false, true);
        }
    }

    private void loadMyFollowMore(ArrayList<UserIdFollowResponse.DataBeanX.DataBean> newData) {
        LocalLog.d(TAG, "loadMyFollowMore() enter");
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        myFollowData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        myFollowAdapter.notifyItemRangeInserted(myFollowData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        myFollowFragment.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            LocalLog.d(TAG, "加载更多!");
            if (selectPage == 0) {
                if (pageFollowOtoCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexFollowOto > pageFollowOtoCount) {
                        Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                        followOtoFragment.loadMoreFinish(false, true);
                        return;
                    }
                }

                Presenter.getInstance(getContext()).getFollows("mutual", pageIndexFollowOto, PAGE_SIZE_DEFAULT);
            } else if (selectPage == 2) {
                if (pageFollowMeCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexFollowMe > pageFollowMeCount) {
                        Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                        followMeFragment.loadMoreFinish(false, true);
                        return;
                    }
                }

                Presenter.getInstance(getContext()).getFollows("me", pageFollowMeCount, PAGE_SIZE_DEFAULT);
            } else if (selectPage == 1) {
                if (pageFollowMeCount == 0) {
                    LocalLog.d(TAG, "第一次刷新");
                } else {
                    if (pageIndexMyFollow > pageMyFollowCount) {
                        Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                        myFollowFragment.loadMoreFinish(false, true);
                        return;
                    }
                }
                Presenter.getInstance(getContext()).getFollows("my", pageFollowMeCount, PAGE_SIZE_DEFAULT);
            }
        }
    };

    private View getTabView(int position) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.text_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            textView.setText(titles[0]);
            view.setGravity(Gravity.LEFT);
        } else if (position == 1) {
            textView.setText(titles[1]);
            view.setGravity(Gravity.CENTER);
        } else if (position == 2) {
            textView.setText(titles[2]);
            view.setGravity(Gravity.RIGHT);
        }
        return view;
    }


    public void setIndicator(TabLayout tab, int leftDip, int rightDip) {
        Class<?> tabLayout = tab.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout IITab = null;
        try {
            IITab = (LinearLayout) tabStrip.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < IITab.getChildCount(); i++) {
            View child = IITab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    private BaseBarImageViewFragment.ToolBarListener toolBarListener = new BaseBarImageViewFragment.ToolBarListener() {
        @Override
        public void clickLeft() {

        }

        @Override
        public void clickRight() {
            LocalLog.d(TAG, "邀请好友");
            Intent friendAddressIntent = new Intent();
            friendAddressIntent.setClass(getContext(), AddFriendAddressActivity.class);
            startActivity(friendAddressIntent);
        }
    };

    @Override
    public Object right() {
        return "添加关注";
    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter");

    }

    @Override
    public void response(FollowUserResponse followUserResponse) {
        LocalLog.d(TAG, "FollowUserResponse() enter " + followUserResponse.toString());
        if (followUserResponse.getError() == 0) {
            //followMeFragment.setAdapter(new FollowAdapter(getContext(), followUserResponse.getData().getData()));
            LocalLog.d(TAG, followUserResponse.getMessage());
            pageFollowMeCount = followUserResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndexFollowMe = " + pageIndexFollowMe + " ,pageFollowMeCount = " + pageFollowMeCount);
            loadFollowMeMore((ArrayList<FollowUserResponse.DataBeanX.DataBean>) followUserResponse.getData().getData());
            if (pageIndexFollowMe == 1) {
                followMeFragment.scrollTop();
            }
            pageIndexFollowMe++;
        } else if (followUserResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

    }

    @Override
    public void response(UserIdFollowResponse userIdFollowResponse) {
        LocalLog.d(TAG, "UserIdFollowResponse() enter " + userIdFollowResponse.toString());
        if (userIdFollowResponse.getError() == 0) {
            if (userIdFollowResponse.getData() != null) {
                //myFollowFragment.setAdapter(new FollowAdapter(getContext(), userIdFollowResponse.getData().getData()));
                LocalLog.d(TAG, userIdFollowResponse.getMessage());
                pageMyFollowCount = userIdFollowResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndexFollowOto = " + pageIndexMyFollow + "  ,pageMyFollowCount = " + pageMyFollowCount);
                loadMyFollowMore((ArrayList<UserIdFollowResponse.DataBeanX.DataBean>) userIdFollowResponse.getData().getData());
                if (pageIndexMyFollow == 1) {
                    myFollowFragment.scrollTop();
                }
                pageIndexMyFollow++;
            }
        } else if (userIdFollowResponse.getError() == 1) {
            LocalLog.e(TAG, "没有数据");
        } else if (userIdFollowResponse.getError() == -1) {
            LocalLog.e(TAG, userIdFollowResponse.getMessage());
        } else if (userIdFollowResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(UserFollowOtOResponse userFollowOtOResponse) {
        LocalLog.d(TAG, "UserFollowOtOResponse() enter " + userFollowOtOResponse.toString());
        if (userFollowOtOResponse.getError() == 0) {
            if (userFollowOtOResponse.getData() != null) {
                //followOtoFragment.setAdapter(new FollowAdapter(getContext(), userFollowOtOResponse.getData().getData()));
                LocalLog.d(TAG, userFollowOtOResponse.getMessage());
                pageFollowOtoCount = userFollowOtOResponse.getData().getPagenation().getTotalPage();
                LocalLog.d(TAG, "pageIndexFollowOto = " + pageIndexFollowOto + "  , pageFollowOtoCount = " + pageFollowOtoCount);
                loadFollowOtOMore((ArrayList<UserFollowOtOResponse.DataBeanX.DataBean>) userFollowOtOResponse.getData().getData());
                if (pageIndexFollowOto == 1) {
                    followOtoFragment.scrollTop();
                }
                pageIndexFollowOto++;
            }
        } else if (userFollowOtOResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

    }
}
