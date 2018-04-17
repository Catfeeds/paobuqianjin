package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
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

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InviteInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.FillInviteCodeActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.InviteDanAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/16.
 */

public class InviteFragment extends BaseBarStyleTextViewFragment implements InviteInterface {
    private final static String TAG = InviteFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.invite_code_text)
    TextView inviteCodeText;
    @Bind(R.id.invite_code_span)
    RelativeLayout inviteCodeSpan;
    @Bind(R.id.invite_tab)
    TabLayout inviteTab;
    @Bind(R.id.invite_no_span)
    RelativeLayout inviteNoSpan;
    @Bind(R.id.step_dollar_viewpager)
    ViewPager stepDollarViewpager;
    @Bind(R.id.invite_btn)
    Button inviteBtn;
    MyInviteFragment myInviteFragment;
    InviteDanFragment inviteDanFragment;
    String[] titles = {"邀请达人榜", "我的邀请"};
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;
    private SHARE_MEDIA share_media;
    private ProgressDialog dialog;
    private UMWeb web;
    private int pageIndexDan = 1, pageCountDan = 0;
    private final static int PAGE_SIZE = 10;
    InviteDanAdapter adapter;
    private ArrayList<InviteDanResponse.DataBeanX.DataBean> inviteDan = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.invite_fg;
    }

    @Override
    protected String title() {
        return "邀请有礼";
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
        Presenter.getInstance(getContext()).getInviteDan(pageIndexDan, PAGE_SIZE);
        Presenter.getInstance(getContext()).getMyInviteMsg();
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        myInviteFragment = new MyInviteFragment();
        inviteDanFragment = new InviteDanFragment();

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(inviteDanFragment);
        fragments.add(myInviteFragment);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);

        inviteTab = (TabLayout) viewRoot.findViewById(R.id.invite_tab);
        stepDollarViewpager = (ViewPager) viewRoot.findViewById(R.id.step_dollar_viewpager);
        stepDollarViewpager.setAdapter(tabAdapter);


        inviteTab.setupWithViewPager(stepDollarViewpager);

        for (int i = 0; i < inviteTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            inviteTab.getTabAt(i).setCustomView(getTabView(i));
        }

        inviteTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(inviteTab, 10, 10);
            }
        });

        dialog = new ProgressDialog(getContext());
        web = new UMWeb(NetApi.urlShare + String.valueOf(Presenter.getInstance(getContext()).getId()));
        web.setTitle("跑步钱进");
        web.setThumb(new UMImage(getContext(), R.mipmap.app_icon));
        web.setDescription("邀请好友");
        adapter = new InviteDanAdapter(getContext(), null);
        inviteDanFragment.setDanAdapter(adapter);
        loadData(inviteDan);

    }


    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, share_media.toString() + "开始分享");
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getContext(), "分享成功", Toast.LENGTH_SHORT).show();
            SocializeUtils.safeCloseDialog(dialog);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "失败" + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "取消分享", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(getContext()).release();
    }

    private void selectShare() {
        popupCircleTypeView = View.inflate(getContext(), R.layout.share_pop_window, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        RelativeLayout friendCircle = (RelativeLayout) popupCircleTypeView.findViewById(R.id.friend_circle);
        RelativeLayout wechat = (RelativeLayout) popupCircleTypeView.findViewById(R.id.we_chat);
        RelativeLayout qq_icon = (RelativeLayout) popupCircleTypeView.findViewById(R.id.qq_icon);
        friendCircle.setOnClickListener(onClickListener);
        wechat.setOnClickListener(onClickListener);
        qq_icon.setOnClickListener(onClickListener);
        popupCircleTypeWindow.setFocusable(true);
        popupCircleTypeWindow.setOutsideTouchable(true);
        popupCircleTypeWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.invite_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.friend_circle:
                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                    new ShareAction(getActivity()).withMedia(web)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    break;
                case R.id.we_chat:
                    share_media = SHARE_MEDIA.WEIXIN;
                    new ShareAction(getActivity()).withMedia(web)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    break;
                case R.id.qq_icon:
                    break;
                default:
                    break;
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

    @OnClick({R.id.invite_code_span, R.id.invite_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invite_code_span:
                LocalLog.d(TAG, "填写邀请码");
                startActivity(FillInviteCodeActivity.class, null);
                break;
            case R.id.invite_btn:
                LocalLog.d(TAG, "邀请好友");
                selectShare();
                break;
        }
    }


    private void loadMore(ArrayList<InviteDanResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        inviteDan.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(inviteDan.size() - newData.size(), newData.size());
    }

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<InviteDanResponse.DataBeanX.DataBean> dataBeans) {
        adapter.notifyDataSetChanged(dataBeans);
    }


    @Override
    public void response(MyInviteResponse myInviteResponse) {
        LocalLog.d(TAG, "MyInviteResponse() enter " + myInviteResponse.toString());
        if (myInviteResponse.getError() == 0) {
            myInviteFragment.setMsg(myInviteResponse.getData().getNumber(), myInviteResponse.getData().getSum_credit(),
                    myInviteResponse.getData().getMobile());
        } else if (myInviteResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            Toast.makeText(getContext(), myInviteResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(InviteDanResponse inviteDanResponse) {
        LocalLog.d(TAG, "InviteDanResponse() enter " + inviteDanResponse.toString());
        if (inviteDanResponse.getError() == 0) {
            pageCountDan = inviteDanResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndexDan + "pageCount = " + pageCountDan);
            loadMore((ArrayList<InviteDanResponse.DataBeanX.DataBean>) inviteDanResponse.getData().getData());
            pageIndexDan++;
            if (pageIndexDan <= pageCountDan) {
                Presenter.getInstance(getContext()).getInviteDan(pageIndexDan, PAGE_SIZE);
            }
        } else if (inviteDanResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            Toast.makeText(getContext(), inviteDanResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
