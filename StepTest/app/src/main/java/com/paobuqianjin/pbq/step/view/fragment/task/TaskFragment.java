package com.paobuqianjin.pbq.step.view.fragment.task;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskMyRecInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.AgreementActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.ReleaseRecordFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/24.
 */

public class TaskFragment extends BaseBarStyleTextViewFragment implements TaskMyRecInterface {
    private final static String TAG = TaskFragment.class.getSimpleName();
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.task_all)
    Button taskAll;
    @Bind(R.id.task_un_finish)
    Button taskUnFinish;
    @Bind(R.id.task_finished)
    Button taskFinished;
    @Bind(R.id.bar)
    LinearLayout bar;
    @Bind(R.id.container_task)
    RelativeLayout containerLive;

    AllTaskFragment allTaskFragment;
    FinishedTaskFragment finishedTaskFragment;
    EmptyTaskFragment emptyTaskFragment;
    ReleaseRecordFragment releaseRecordFragment;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.red_rule)
    LinearLayout redRule;
    @Bind(R.id.iv_send_red_bag)
    TextView ivSendRedBag;
    private int mCurrentIndex = 0;
    private int mIndex = 0;
    private Fragment[] mFragments;
    private IntentFilter intentFilter;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private ArrayList<MyRecTaskRecordResponse.DataBeanX.DataBean> allTaskList, finishTaskList;
    private final static String REC_TASK_ACTION = "com.paobuqianjin.pbq.step.REC_TASK_ACTION";
    private final static String REC_GIFT_ACTION = "com.paobuqianjin.pbq.step.REC_GIFT_ACTION";
    private final static String PKG_ACTION = "com.paobuqianjin.person.PKG_ACTION";
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 50;
    private boolean isReloading = false;
    private final static String PERSON_RED_RULE = "com.paobuqianjin.pbq.step.PERSON_RED_RULE";

    @Override

    protected int getLayoutResId() {
        return R.layout.task_top_fg;
    }

    @Override
    protected String title() {
        return "";
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
        Presenter.getInstance(getContext()).getAllMyRecTask(pageIndex, PAGESIZE);
        return rootView;
    }

    public interface ReloadDataInterface {
        public void reloadData();
    }

    private ReloadDataInterface reloadDataInterface = new ReloadDataInterface() {
        @Override
        public void reloadData() {
            if (isAdded() && !isReloading) {
                isReloading = true;
                loadTaskData();
            }
        }
    };

    @Override
    protected void initView(View viewRoot) {
        allTaskFragment = new AllTaskFragment();
        finishedTaskFragment = new FinishedTaskFragment();
        emptyTaskFragment = new EmptyTaskFragment();
        releaseRecordFragment = new ReleaseRecordFragment();
        allTaskFragment.setReloadDataInterface(reloadDataInterface);
        finishedTaskFragment.setReloadDataInterface(reloadDataInterface);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTvRight = (TextView) viewRoot.findViewById(R.id.bar_tv_right);
        barTitle.setText("专享红包");
        banner = (Banner) viewRoot.findViewById(R.id.banner);
        mFragments = new Fragment[]{allTaskFragment, finishedTaskFragment, releaseRecordFragment};
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container_task, allTaskFragment)
                .add(R.id.container_task, finishedTaskFragment)
                .add(R.id.container_task, releaseRecordFragment)
                .add(R.id.container_task, emptyTaskFragment)
                .show(allTaskFragment)
                .hide(finishedTaskFragment)
                .hide(releaseRecordFragment)
                .hide(emptyTaskFragment)
                .commit();

        intentFilter = new IntentFilter();
        intentFilter.addAction(REC_GIFT_ACTION);
        intentFilter.addAction(REC_TASK_ACTION);

        localReceiver = new LocalReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
        loadBanner();
        redRule = (LinearLayout) viewRoot.findViewById(R.id.red_rule);
        redRule.setOnClickListener(onClickListener);
        ivSendRedBag = (TextView) viewRoot.findViewById(R.id.iv_send_red_bag);
        ivSendRedBag.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.red_rule:
                    startActivity(AgreementActivity.class, null, false, PERSON_RED_RULE);
                    break;
                case R.id.iv_send_red_bag:
                    Intent intent = new Intent();
                    intent.setAction(PKG_ACTION);
                    intent.setClass(getContext(), TaskReleaseActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void loadBanner() {
        final String bannerUrl = NetApi.urlAd + "?position=task_list";
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(localReceiver);

        }
        allTaskList = null;

        finishTaskList = null;
    }

    @OnClick({R.id.task_all, R.id.task_un_finish, R.id.task_finished, R.id.bar_tv_right, R.id.bar_return_drawable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.task_all:
                mIndex = 0;
                LocalLog.d(TAG, "今日专享");
                onTabIndex(mIndex);
                break;
            case R.id.task_un_finish:
                mIndex = 1;
                LocalLog.d(TAG, "完成专享");
                onTabIndex(mIndex);
                break;
            case R.id.task_finished:
                mIndex = 2;
                LocalLog.d(TAG, "已发专享");
                onTabIndex(mIndex);
                break;
            case R.id.bar_tv_right:
                LocalLog.d(TAG, "发布");

                break;
            case R.id.bar_return_drawable:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    /*@desc  当前不再选中状态
    *@function setCurrentIndexStateUnSelect
    *@param
    *@return 
    */
    @TargetApi(19)
    private void setCurrentIndexStateUnSelect() {
        if (mCurrentIndex == 0) {
            taskAll.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangele_four_full_r_unselected));
            taskAll.setTextColor(ContextCompat.getColor(getContext(), R.color.color_161727));
        } else if (mCurrentIndex == 1) {
            taskUnFinish.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_four_fill_outline_unselected));
            taskUnFinish.setTextColor(ContextCompat.getColor(getContext(), R.color.color_161727));
        } else if (mCurrentIndex == 2) {
            taskFinished.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_four_full_left_unselect));
            taskFinished.setTextColor(ContextCompat.getColor(getContext(), R.color.color_161727));
        }
    }

    /*@desc   当前为选中状态
    *@function
    *@param
    *@return 
    */
    @TargetApi(19)
    private void setCurrentIndexStateSelected() {
        if (mCurrentIndex == 0) {
            taskAll.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_four_full_r_selected));
            taskAll.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
        } else if (mCurrentIndex == 1) {
            taskUnFinish.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_four_fill_outline_selected));
            taskUnFinish.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
        } else if (mCurrentIndex == 2) {
            taskFinished.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_four_full_left_select));
            taskFinished.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
        }
    }

    private void onTabIndex(int fragmentIndex) {
        LocalLog.d(TAG, "onTabIndex() enter mIndex " + fragmentIndex);
        if (mCurrentIndex != fragmentIndex) {
            FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
            trx.hide(mFragments[mCurrentIndex]);
            if (!mFragments[fragmentIndex].isAdded()) {
                trx.add(R.id.fragment_container, mFragments[fragmentIndex]);
            }
            if (fragmentIndex == 0) {
                if (allTaskList == null) {
                    if (!emptyTaskFragment.isAdded()) {
                        trx.add(R.id.fragment_container, emptyTaskFragment);
                    }
                    trx.show(emptyTaskFragment).commit();
                } else {
                    if (!emptyTaskFragment.isHidden()) {
                        trx.hide(emptyTaskFragment);
                    }
                    trx.show(mFragments[fragmentIndex]).commit();
                }
            } else if (fragmentIndex == 1) {
                if (finishTaskList == null) {
                    if (!emptyTaskFragment.isAdded()) {
                        trx.add(R.id.fragment_container, emptyTaskFragment);
                    }
                    trx.show(emptyTaskFragment).commit();
                } else {
                    if (!emptyTaskFragment.isHidden()) {
                        trx.hide(emptyTaskFragment);
                    }
                    trx.show(mFragments[fragmentIndex]).commit();
                }
            } else if (fragmentIndex == 2) {
                if (!emptyTaskFragment.isHidden()) {
                    trx.hide(emptyTaskFragment);
                }
                trx.show(mFragments[fragmentIndex]).commit();
            }

            setCurrentIndexStateUnSelect();
        }
        mCurrentIndex = fragmentIndex;
        setCurrentIndexStateSelected();
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (REC_TASK_ACTION.equals(intent.getAction())) {
                    LocalLog.d(TAG, "领取任务成功");
                    loadTaskData();

                } else if (REC_GIFT_ACTION.equals(intent.getAction())) {
                    LocalLog.d(TAG, "领取奖励成功");
                    loadTaskData();
                }
            }
        }
    }

    public void loadTaskData() {
        pageIndex = 1;
        pageCount = 0;
        allTaskList = null;
        finishTaskList = null;
        allTaskFragment.setData(null);
        finishedTaskFragment.setData(null);
        Presenter.getInstance(getContext()).getAllMyRecTask(pageIndex, PAGESIZE);
    }

    @Override
    public void response(MyRecTaskRecordResponse myRecvTaskRecordResponse) {
        //LocalLog.d(TAG, "MyRecTaskRecordResponse() enter" + myRecvTaskRecordResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (myRecvTaskRecordResponse.getError() == 0) {
            pageCount = myRecvTaskRecordResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex =  " + pageIndex + ",pageCount =" + pageCount);
            for (int i = 0; i < myRecvTaskRecordResponse.getData().getData().size(); i++) {
                if (allTaskList == null) {
                    allTaskList = new ArrayList<>();
                }
                allTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                allTaskFragment.notifyAddData(myRecvTaskRecordResponse.getData().getData().get(i));
                if (myRecvTaskRecordResponse.getData().getData().get(i).getIs_receive() == 1) {
                    LocalLog.d(TAG, "已接任务");
                    if (myRecvTaskRecordResponse.getData().getData().get(i).getIs_finished() == 0) {

                    } else {
                        if (finishTaskList == null) {
                            finishTaskList = new ArrayList<>();
                            finishTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                        } else {
                            finishTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                        }
                        finishedTaskFragment.notifyAddData(myRecvTaskRecordResponse.getData().getData().get(i));
                    }
                } else if (myRecvTaskRecordResponse.getData().getData().get(i).getIs_receive() == 0) {
                    LocalLog.d(TAG, "未接任务");
                }

            }

            if (pageIndex < pageCount) {
                LocalLog.d(TAG, "加载更多");
                Presenter.getInstance(getContext()).getAllMyRecTask(pageIndex, PAGESIZE);
                pageIndex++;
            }
        } else if (myRecvTaskRecordResponse.getError() == 1) {
            if (getActivity() == null) {
                return;
            }
            FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
            trx.hide(mFragments[mCurrentIndex]);
            if (!emptyTaskFragment.isAdded()) {
                trx.add(R.id.fragment_container, emptyTaskFragment);
            }
            trx.show(emptyTaskFragment).commitAllowingStateLoss();
        } else if (myRecvTaskRecordResponse.getError() == -1) {
            Toast.makeText(getContext(), myRecvTaskRecordResponse.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (myRecvTaskRecordResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
        isReloading = false;
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            exitTokenUnfect();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            LocalLog.d(TAG, "刷新任务列表");
            loadTaskData();
        }
    }
}
