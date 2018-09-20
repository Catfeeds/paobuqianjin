package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.bundle.ChoiceBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTypeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LiveResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InOutCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.QueryRedPkgInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.LiveDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.LiveListActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.SearchCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorRedDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.CircleChooseGoodAdapter;
import com.paobuqianjin.pbq.step.view.emoji.LQREmotionKit;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/11.
 */

public class HotCircleFragment extends BaseFragment {
    private final static String TAG = HotCircleFragment.class.getSimpleName();
    private LinearLayoutManager layoutManagerChoose;
    private RecyclerView choiceRecyclerView;
    private RelativeLayout myHotLa, myHotLb;
    private ImageView createCircleView;
    private CircleImageView myHotCircleIV, secondHotCircleIV;
    private TextView myHotCircleTV, secondHotCircleTV;
    private TextView moreMyCircleTV, moreChoiceTV, moreLiveTV;
    private ImageView readPackAIV, readPackBIV;
    private ImageView iv_live_a, iv_live_b;
    private TextView live_a_desc, live_b_desc;
    private Banner banner;
    private Context mContext;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> choiceCircleData;
    private ArrayList<MyCreateCircleResponse.DataBeanX.DataBean> myCreateCircle;
    private ArrayList<MyJoinCircleResponse.DataBeanX.DataBean> myJoinCirCle;
    private int[] pageCounts = new int[3];
    private int circleIdA = -1, circleIdB = -1;
    private int circleNumA = -1, circleNumB = -1;
    private ArrayList<String> circleTypeList;
    CircleChooseGoodAdapter adapter;
    private final static String ACTION_ENTER_ICON = "coma.paobuqian.pbq.step.ICON_ACTION";
    private int PAGE_INDEX_DEFAULT = 1;
    private int PAGE_DEFAULT_SIZE = 10;
    private static int SEARCH_ADD = 0;
    private static int MY_CIRCLE_ADD = 1;
    private final static int REQUEST_A = 203;
    private final static int REQUEST_B = 204;
    private IntentFilter intentFilter;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private final static String ACTION_JOIN = "com.paobuqianjin.step.pbq.ACTION_JOIN";
    private final static String ACTION_QUIT = "com.paobuqianjin.step.pbq.ACTION_QUIT";
    private final static String ACTION_CREATE = "com.paobuqianjin.step.pbq.ACTION_CREATE";
    private final static String ACTION_DELETE_MEMBER = "com.paobuqianjin.step.pbq.ACTION_MEMBER_DELETE";
    private final static String ACTIONF_DELETE_CIRCLE = "com.paobuqjian.step.pbq.ACTION_DELETE_CIRCLE";
    private final static String DELETE_ACTION = "com.paobuqianjin.pbq.step.DELETE_CIRCLE";
    private final static String QUIT_ACTION = "com.paobuqianjin.pbq.step.QUIT";
    private EditText searchEdit;
    private ArrayList<AdObject> adList;
    private PopupWindow popOpWindowRedButtonHori;
    private int homeIndex = 2;
    private boolean isFirst = true;

    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_JOIN);
        intentFilter.addAction(ACTION_QUIT);
        intentFilter.addAction(ACTION_CREATE);
        intentFilter.addAction(ACTION_DELETE_MEMBER);
        intentFilter.addAction(ACTIONF_DELETE_CIRCLE);

        localReceiver = new LocalReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocalLog.d(TAG, "onCreateView() enter");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hot_circle_fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(mContext).dispatchUiInterface(uiHotCircleInterface);
        Presenter.getInstance(mContext).dispatchUiInterface(queryRedPkgInterface);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalLog.d(TAG, "onResume");
        loadingData();
        if (isFirst) {
            isFirst = false;
            popCirCreate(2);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(localReceiver);
        }
    }

    public void popCirCreate(int homeIndex) {
        this.homeIndex = homeIndex;
        if (isAdded() && !isHidden() && homeIndex == 2) {
            try {
                popRedPkgButton();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pullCirCreate(int homeIndex) {
        LocalLog.d(TAG, "pullCirCreate() 红包隐藏!");
        this.homeIndex = homeIndex;
        if (popOpWindowRedButtonHori != null && popOpWindowRedButtonHori.isShowing()) {
            popOpWindowRedButtonHori.dismiss();
        }
    }

    public void popRedPkgButton() {
        LocalLog.d(TAG, "popRedPkgButton() 弹出红包");
        if (!isAdded()) {
            return;
        }
        if (popOpWindowRedButtonHori != null && popOpWindowRedButtonHori.isShowing()) {
            return;
        }

        View popCircleOpBarHori = View.inflate(getContext(), R.layout.near_by_red_pop_window, null);
        popOpWindowRedButtonHori = new PopupWindow(popCircleOpBarHori,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final Button button = (Button) popCircleOpBarHori.findViewById(R.id.red_pkg_button);
        button.setBackgroundResource(R.drawable.create_icon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), OwnerCircleActivity.class);
                startActivity(intent);
            }
        });
        popOpWindowRedButtonHori.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popRedPkgButton dismiss() ");
            }
        });

        popOpWindowRedButtonHori.setBackgroundDrawable(new BitmapDrawable());
        TranslateAnimation animationCircleTypeHori = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleTypeHori.setInterpolator(new AccelerateInterpolator());
        animationCircleTypeHori.setDuration(200);


        popOpWindowRedButtonHori.showAtLocation(getView().findViewById(R.id.hot_circle), Gravity.BOTTOM | Gravity.RIGHT, 0, 400);
        popCircleOpBarHori.startAnimation(animationCircleTypeHori);
    }

    @Override
    protected void initView(View rootView) {
        LocalLog.d(TAG, "initView() enter");
        //TODO 圈子活动
        //TODO 精选圈子
        circleTypeList = new ArrayList<>();
        choiceRecyclerView = (RecyclerView) rootView
                .findViewById(R.id.live_choose_good_module)
                .findViewById(R.id.live_choose_good_module_recycler);
        layoutManagerChoose = new LinearLayoutManager(getContext());
        layoutManagerChoose.setOrientation(LinearLayoutManager.VERTICAL);
        choiceRecyclerView.setLayoutManager(layoutManagerChoose);
        choiceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        choiceRecyclerView.addItemDecoration(new CircleChooseGoodAdapter.SpaceItemDecoration(5));
        choiceRecyclerView.setHasFixedSize(true);
        choiceRecyclerView.setNestedScrollingEnabled(false);

        //
        createCircleView = (ImageView) rootView.findViewById(R.id.circle_create);
        createCircleView.setOnClickListener(onClickListener);

        myHotLa = (RelativeLayout) rootView.findViewById(R.id.circle_hot_a_span);
        myHotLb = (RelativeLayout) rootView.findViewById(R.id.hot_circle_b);
        myHotCircleIV = (CircleImageView) rootView.findViewById(R.id.circle_hot_a_img);
        myHotCircleIV.setOnClickListener(onClickListener);
        secondHotCircleIV = (CircleImageView) rootView.findViewById(R.id.circle_hot_b_img);
        secondHotCircleIV.setOnClickListener(onClickListener);
        myHotCircleTV = (TextView) rootView.findViewById(R.id.circle_hot_a_name);
        secondHotCircleTV = (TextView) rootView.findViewById(R.id.circle_hot_b_name);
        readPackAIV = (ImageView) rootView.findViewById(R.id.red_pack_a);
        readPackBIV = (ImageView) rootView.findViewById(R.id.red_pack_b);
        iv_live_a = (ImageView) rootView.findViewById(R.id.iv_live_a);
        iv_live_b = (ImageView) rootView.findViewById(R.id.iv_live_b);
        live_a_desc = (TextView) rootView.findViewById(R.id.live_a_desc);
        live_b_desc = (TextView) rootView.findViewById(R.id.live_b_desc);
        banner = (Banner) rootView.findViewById(R.id.banner);

        moreMyCircleTV = (TextView) rootView.findViewById(R.id.find_more_my_circle);
        moreMyCircleTV.setOnClickListener(onClickListener);
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.live_choose_good_module);
        moreChoiceTV = (TextView) relativeLayout.findViewById(R.id.find_more_choice);
        moreChoiceTV.setOnClickListener(onClickListener);
        moreLiveTV = (TextView) rootView.findViewById(R.id.more_lives);
        moreLiveTV.setOnClickListener(onClickListener);
        EditText search_circle_text = rootView.findViewById(R.id.search_circle_text);
        search_circle_text.setClickable(true);
        search_circle_text.setFocusable(false);
        search_circle_text.setOnClickListener(onClickListener);

        Presenter.getInstance(mContext).attachUiInterface(uiHotCircleInterface);
        Presenter.getInstance(mContext).attachUiInterface(queryRedPkgInterface);
        loadBanner();
        searchEdit = (EditText) rootView.findViewById(R.id.search_pan).findViewById(R.id.search_circle_text);
        searchEdit.setHint("请输入圈子名称/ID");
        //((BaseActivity) getActivity()).showLoadingBar();
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {
                Toast.makeText(getContext(), ((ErrorCode) object).getMessage(), Toast.LENGTH_SHORT).show();
            } else if (object instanceof LiveResponse) {
                if (((LiveResponse) object).getError() == 0) {
                    LocalLog.d(TAG, "LiveResponse " + ((LiveResponse) object).toString());
                    final List<LiveResponse.DataBeanX.DataBean> listBean = ((LiveResponse) object).getData().getData();

                    if (listBean.size() > 1) {
                        iv_live_a.setVisibility(View.VISIBLE);
//                        live_a_desc.setVisibility(View.VISIBLE);
                        iv_live_b.setVisibility(View.VISIBLE);
//                        live_b_desc.setVisibility(View.VISIBLE);
                        Presenter.getInstance(getActivity()).getImage(iv_live_a, listBean.get(0).getConver());
                        Presenter.getInstance(getActivity()).getImage(iv_live_b, listBean.get(1).getConver());
                        iv_live_a.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra(getActivity().getPackageName(), listBean.get(0).getRemote_url());
                                intent.putExtra("is_process", listBean.get(0).getIs_process());
                                intent.putExtra("is_receive", listBean.get(0).getIs_receive());
                                intent.putExtra("target", listBean.get(0).getTarget());
                                intent.putExtra("actid", listBean.get(0).getId());
                                intent.setClass(getActivity(), LiveDetailActivity.class);
                                getActivity().startActivity(intent);
                            }
                        });
                        iv_live_b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra(getActivity().getPackageName(), listBean.get(1).getRemote_url());
                                intent.putExtra("is_process", listBean.get(1).getIs_process());
                                intent.putExtra("is_receive", listBean.get(1).getIs_receive());
                                intent.putExtra("target", listBean.get(1).getTarget());
                                intent.putExtra("actid", listBean.get(1).getId());
                                intent.setClass(getActivity(), LiveDetailActivity.class);
                                getActivity().startActivity(intent);
                            }
                        });
                    } else if (listBean.size() > 0) {
                        iv_live_a.setVisibility(View.VISIBLE);
//                        live_a_desc.setVisibility(View.VISIBLE);
                        iv_live_b.setVisibility(View.INVISIBLE);
//                        live_b_desc.setVisibility(View.INVISIBLE);
                        Presenter.getInstance(getActivity()).getImage(iv_live_a, listBean.get(0).getConver());
                        iv_live_a.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra(getActivity().getPackageName(), listBean.get(0).getRemote_url());
                                intent.setClass(getActivity(), LiveDetailActivity.class);
                                intent.putExtra("is_process", listBean.get(0).getIs_process());
                                intent.putExtra("is_receive", listBean.get(0).getIs_receive());
                                intent.putExtra("target", listBean.get(0).getTarget());
                                intent.putExtra("actid", listBean.get(0).getId());
                                getActivity().startActivity(intent);
                            }
                        });
                    } else {
                        iv_live_a.setVisibility(View.INVISIBLE);
                        live_a_desc.setVisibility(View.INVISIBLE);
                        iv_live_b.setVisibility(View.INVISIBLE);
                        live_b_desc.setVisibility(View.INVISIBLE);
                    }
                } else if (((LiveResponse) object).getError() == 1) {

                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        LocalLog.d(TAG, "onPause() enter");
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                if (intent != null) {
                    if (ACTION_JOIN.equals(intent.getAction())) {
                        LocalLog.d(TAG, "加入圈子");
                    } else if (ACTION_QUIT.equals(intent.getAction())) {
                        LocalLog.d(TAG, "退出圈子");
                    } else if (ACTION_CREATE.equals(intent.getAction())) {
                        LocalLog.d(TAG, "创建圈子");
                    } else if (ACTION_DELETE_MEMBER.equals(intent.getAction())) {
                        LocalLog.d(TAG, "删除成员");
                    } else if (ACTIONF_DELETE_CIRCLE.equals(intent.getAction())) {
                        LocalLog.d(TAG, "解散圈子");
                    }
                }
            }
        }
    }

    private void loadingData() {
        Presenter.getInstance(mContext).getCircleChoice(PAGE_INDEX_DEFAULT, PAGE_DEFAULT_SIZE);
        Presenter.getInstance(mContext).getMyHotCircle(PAGE_INDEX_DEFAULT, 2);
        Presenter.getInstance(getContext()).getLiveList(innerCallBack, 1, 2);
    }

    /*@desc  返回Fragment标签
    *@function getTabLabel
    *@param
    *@return
    */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalLog.d(TAG, "onClick() enter");
            switch (view.getId()) {
                case R.id.circle_create:
                    LocalLog.d(TAG, "onClick()创建圈子");
                    Intent intent = new Intent();
                    intent.setClass(HotCircleFragment.this.getContext(), CreateCircleActivity.class);
                    //intent.setClass(HotCircleFragment.this.getContext(),SearchCircleActivity.class);
                    //intent.setClass(HotCircleFragment.this.getContext(), MemberManagerActivity.class);
                    intent.putStringArrayListExtra(getContext().getPackageName() + "circle_type", circleTypeList);
                    HotCircleFragment.this.getActivity().startActivity(intent);
                    break;
                case R.id.circle_hot_a_img:
                    LocalLog.d(TAG, "onClick() 我的第一个圈子被点击");
                    Intent intentA = new Intent();
                    intentA.setClass(getContext(), CirCleDetailActivity.class);
                    intentA.putExtra(mContext.getPackageName() + "circleid", circleIdA);
                    intentA.setAction(ACTION_ENTER_ICON);
                    startActivityForResult(intentA, REQUEST_A);
                    break;
                case R.id.circle_hot_b_img:
                    LocalLog.d(TAG, "onClick() 我的第二个圈子被点击");
                    Intent intentB = new Intent();
                    intentB.setClass(getContext(), CirCleDetailActivity.class);
                    intentB.putExtra(mContext.getPackageName() + "circleid", circleIdB);
                    intentB.setAction(ACTION_ENTER_ICON);
                    startActivityForResult(intentB, REQUEST_B);
                    break;
                case R.id.find_more_my_circle:
                    LocalLog.d(TAG, "查看更多我的圈子");

                    Intent intentCircle = new Intent();
                    intentCircle.setClass(getActivity(), OwnerCircleActivity.class);
                    startActivityForResult(intentCircle, MY_CIRCLE_ADD);
                    break;
                case R.id.live_choose_good_module:

                    break;
                case R.id.find_more_choice:
                    LocalLog.d(TAG, "查看更多圈子！");
                    ChoiceBundleData choiceBundleData = new ChoiceBundleData(choiceCircleData);
                    Intent intentSearch = new Intent();
                    intentSearch.putExtra(getActivity().getPackageName(), choiceBundleData);
                    intentSearch.setClass(getActivity(), SearchCircleActivity.class);
                    startActivityForResult(intentSearch, SEARCH_ADD);
                    break;
                case R.id.search_circle_text:
                    Intent intentSearchCircle = new Intent();
                    intentSearchCircle.setClass(getActivity(), SearchCircleActivity.class);
                    intentSearchCircle.putExtra("isInitData", false);
                    startActivityForResult(intentSearchCircle, SEARCH_ADD);
                    break;
                case R.id.more_lives:
                    LocalLog.d(TAG, "查看更多活动");
                    Intent liveIntent = new Intent();
                    liveIntent.setClass(getActivity(), LiveListActivity.class);
                    startActivity(liveIntent);
                    break;
                default:
                    break;
            }
        }
    };

    public String getTabLabel() {
        return "热门";
    }

    public void setMyHotLa(String name, String urlImage) {
        myHotLa.setVisibility(View.VISIBLE);
        myHotCircleTV.setText(name);

        Presenter.getInstance(mContext).getImage(myHotCircleIV, urlImage);

    }

    public void setMyHotLb(String name, String urlImage) {
        myHotLb.setVisibility(View.VISIBLE);
        secondHotCircleTV.setText(name);
        Presenter.getInstance(mContext).getImage(secondHotCircleIV, urlImage);
    }

    private UiHotCircleInterface uiHotCircleInterface = new UiHotCircleInterface() {

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

        @Override
        public void response(MyHotCircleResponse myHotCircleResponse) {
            if (!isAdded()) {
                return;
            }
            if (myHotCircleResponse.getError() == 0) {
                LocalLog.d(TAG, "myHotCircleResponse ");
                int size = myHotCircleResponse.getData().getData().size();
                LocalLog.d(TAG, "myHotCircleResponse() size =" + size);
                if (size == 1) {

                    setMyHotLa(myHotCircleResponse.getData().getData().get(0).getName(),
                            myHotCircleResponse.getData().getData().get(0).getLogo());
                    circleIdA = myHotCircleResponse.getData().getData().get(0).getId();
                    circleNumA = myHotCircleResponse.getData().getData().get(0).getMember_number();
                    //Presenter.getInstance(getContext()).getCircleDetail(myHotCircleResponse.getData().getData().get(0).getId());
                    if (myHotCircleResponse.getData().getData().get(0).getIs_red_packet() == 1) {
                        readPackAIV.setVisibility(View.VISIBLE);
                    } else {
                        readPackAIV.setVisibility(View.GONE);
                    }

                    if (myHotLb.getVisibility() == View.VISIBLE) {
                        myHotLb.setVisibility(View.INVISIBLE);
                        readPackBIV.setVisibility(View.GONE);
                    }
                } else if (size >= 2) {
                    setMyHotLa(myHotCircleResponse.getData().getData().get(0).getName(),
                            myHotCircleResponse.getData().getData().get(0).getLogo());
                    setMyHotLb(myHotCircleResponse.getData().getData().get(1).getName(),
                            myHotCircleResponse.getData().getData().get(1).getLogo());

                    circleIdA = myHotCircleResponse.getData().getData().get(0).getId();
                    circleNumA = myHotCircleResponse.getData().getData().get(0).getMember_number();

                    circleIdB = myHotCircleResponse.getData().getData().get(1).getId();
                    circleNumB = myHotCircleResponse.getData().getData().get(1).getMember_number();
                    //Presenter.getInstance(getContext()).getCircleDetail(myHotCircleResponse.getData().getData().get(0).getId());
                    if (myHotCircleResponse.getData().getData().get(0).getIs_red_packet() == 1) {
                        readPackAIV.setVisibility(View.VISIBLE);
                    } else {
                        readPackAIV.setVisibility(View.GONE);
                    }
                    //Presenter.getInstance(getContext()).getCircleDetail(myHotCircleResponse.getData().getData().get(1).getId());
                    if (myHotCircleResponse.getData().getData().get(1).getIs_red_packet() == 1) {
                        readPackBIV.setVisibility(View.VISIBLE);
                    } else {
                        readPackBIV.setVisibility(View.GONE);
                    }
                }
                pageCounts[1] = myHotCircleResponse.getData().getPagenation().getTotalPage();
            } else if (myHotCircleResponse.getError() == 1) {
                myHotLa.setVisibility(View.INVISIBLE);
                myHotLb.setVisibility(View.INVISIBLE);
                readPackBIV.setVisibility(View.GONE);
                readPackBIV.setVisibility(View.GONE);
            } else if (myHotCircleResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(CircleTypeResponse circleTypeResponse) {
            if (!isAdded()) {
                return;
            }
            LocalLog.d(TAG, "CircleTypeResponse() ");
            int size = circleTypeResponse.getData().size();
            for (int i = 0; i < size; i++) {
                circleTypeList.add(circleTypeResponse.getData().get(i).getName());
            }
        }

        @Override
        public void response(MyJoinCircleResponse myJoinCircleResponse) {
            /*LocalLog.d(TAG, "myJoinCircleResponse ");
            myJoinCirCle = (ArrayList<MyJoinCircleResponse.DataBeanX.DataBean>) myJoinCircleResponse.getData().getData();
            int size = myJoinCircleResponse.getData().getData().size();
            LocalLog.d(TAG, "myHotCircleResponse() size =" + size);
            if (size == 1) {

                setMyHotLa(myJoinCircleResponse.getData().getData().get(0).getName(),
                        myJoinCircleResponse.getData().getData().get(0).getLogo(),
                        true);
                circleIdA = myJoinCircleResponse.getData().getData().get(0).getId();
                circleNumA = myJoinCircleResponse.getData().getData().get(0).getMember_number();
            } else if (size >= 2) {
                setMyHotLa(myJoinCircleResponse.getData().getData().get(0).getName(),
                        myJoinCircleResponse.getData().getData().get(0).getLogo(),
                        true);
                setMyHotLb(myJoinCircleResponse.getData().getData().get(1).getName(),
                        myJoinCircleResponse.getData().getData().get(1).getLogo(),
                        true);

                circleIdA = myJoinCircleResponse.getData().getData().get(0).getId();
                circleNumA = myJoinCircleResponse.getData().getData().get(0).getMember_number();

                circleIdB = myJoinCircleResponse.getData().getData().get(1).getId();
                circleNumB = myJoinCircleResponse.getData().getData().get(1).getMember_number();
            }
            pageCounts[1] = myJoinCircleResponse.getData().getPagenation().getTotalPage();*/
        }

        @Override
        public void response(MyCreateCircleResponse myCreateCircleResponse) {
          /*  LocalLog.d(TAG, "myCreateCircleResponse  ");
            myCreateCircle = (ArrayList<MyCreateCircleResponse.DataBeanX.DataBean>) myCreateCircleResponse.getData().getData();
            pageCounts[0] = myCreateCircleResponse.getData().getPagenation().getTotalPage();*/
        }

        @Override
        public void response(ChoiceCircleResponse choiceCircleResponse) {
            if (!isAdded()) {
                return;
            }
            //((BaseActivity) getActivity()).hideLoadingBar();
            if (choiceCircleResponse.getError() == 0) {
                LocalLog.d(TAG, " response() 更新精选圈子 size = " + choiceCircleResponse.getData().getData().size());
                choiceCircleData = (ArrayList<ChoiceCircleResponse.DataBeanX.DataBean>) choiceCircleResponse.getData().getData();
                adapter = new CircleChooseGoodAdapter(getActivity(), getContext(),
                        choiceCircleData, inOutCallBackInterface);
                choiceRecyclerView.setAdapter(adapter);
                pageCounts[2] = choiceCircleResponse.getData().getPagenation().getTotalPage();
            } else if (choiceCircleResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }
    };

    private InOutCallBackInterface inOutCallBackInterface = new InOutCallBackInterface() {
        @Override
        public void inCallBack(int circleId) {
            updateMyHotCircle();
        }

        @Override
        public void outCallBack() {

        }
    };

    /*刷新我的热门圈子*/
    private void updateMyHotCircle() {
        LocalLog.d(TAG, "更新界面");
        Presenter.getInstance(mContext).getMyHotCircle(PAGE_INDEX_DEFAULT, 2);
        Presenter.getInstance(mContext).getCircleChoice(PAGE_INDEX_DEFAULT, PAGE_DEFAULT_SIZE);
    }

    private QueryRedPkgInterface queryRedPkgInterface = new QueryRedPkgInterface() {
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

        @Override
        public void response(CircleDetailResponse circleDetailResponse) {
            LocalLog.d(TAG, "获取圈子详情");
            if (!isAdded()) {
                return;
            }
            if (circleDetailResponse.getError() == 0) {
                if (circleDetailResponse.getData().getIs_red_packet() == 1) {
                    if (circleIdA == circleDetailResponse.getData().getId()) {
                        readPackAIV.setVisibility(View.VISIBLE);
                    } else if (circleIdA == circleDetailResponse.getData().getId()) {
                        readPackBIV.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    };

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=circle_list";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(getActivity()).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    final ArrayList<AdObject> adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
                                    adObject.setRid(Integer.parseInt(adresponse.getData().get(i).getRid()));
                                    adObject.setImg_url(adresponse.getData().get(i).getImgs().get(j).getImg_url());
                                    adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                                    adList.add(adObject);
                                }
                            }
                        }
                    }
                    banner.setImageLoader(new BannerImageLoader())
                            .setImages(adList)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .setBannerAnimation(Transformer.Default)
                            .isAutoPlay(true)
                            .setDelayTime(2000)
                            .setIndicatorGravity(BannerConfig.CENTER)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (adList.get(position).getRid() == 0) {
                                        LocalLog.d(TAG, "复制微信号");
                                        ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(getActivity(), "微信号复制成功");
                                    } else {
                                        String targetUrl = adList.get(position).getTarget_url();
                                        if (!TextUtils.isEmpty(targetUrl))
                                            startActivity(new Intent(getActivity(), SingleWebViewActivity.class).putExtra("url", targetUrl));
                                    }

                                }
                            })
                            .start();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取失败!");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_ADD) {
            LocalLog.d(TAG, "SEARCH_ADD ++++");
            LocalLog.d(TAG, "进行过加入圈子的操作");
            if (circleIdA == -1 || circleIdB == -1) {
                updateMyHotCircle();
            }
        }
        if (data != null) {
            if (DELETE_ACTION.equals(data.getAction()) || QUIT_ACTION.equals(data.getAction())) {
                LocalLog.d(TAG, "删除或者退出圈子");
                updateMyHotCircle();
            }
        }
    }
}
