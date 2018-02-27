package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InviteInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.InviteDanAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.CustomViewPager;

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
        Presenter.getInstance(getContext()).getInviteDan(1, 10);
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

    }

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
                break;
            case R.id.invite_btn:
                LocalLog.d(TAG, "邀请好友");
                break;
        }
    }

    @Override
    public void response(MyInviteResponse myInviteResponse) {
        LocalLog.d(TAG, "MyInviteResponse() enter " + myInviteResponse.toString());
        myInviteFragment.setMsg(myInviteResponse.getData().getNumber(), myInviteResponse.getData().getSum_credit(),
                myInviteResponse.getData().getMobile());
    }

    @Override
    public void response(InviteDanResponse inviteDanResponse) {
        LocalLog.d(TAG, "InviteDanResponse() enter " + inviteDanResponse.toString());
        inviteDanFragment.setDanAdapter(new InviteDanAdapter(getContext(), inviteDanResponse.getData().getData()));
    }
}
