package com.paobuqianjin.pbq.step.view.fragment.home;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.StepProcessDrawable;
import com.paobuqianjin.pbq.step.view.base.view.WaveView;

import java.lang.ref.WeakReference;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/1.
 */

public final class HomePageFragment extends BaseFragment implements HomePageInterface {
    private final static String TAG = HomePageFragment.class.getSimpleName();
    @Bind(R.id.bg_home)
    ImageView bgHome;
    @Bind(R.id.process_step_now)
    View processStepNow;
    @Bind(R.id.toay_step)
    TextView toayStep;
    @Bind(R.id.mu_biao_text)
    TextView muBiaoText;
    @Bind(R.id.process_step)
    FrameLayout processStep;
    @Bind(R.id.wave_view)
    WaveView waveView;
    @Bind(R.id.wave_view_span)
    RelativeLayout waveViewSpan;
    @Bind(R.id.major_bg)
    ImageView majorBg;
    @Bind(R.id.major_span)
    RelativeLayout majorSpan;
    @Bind(R.id.home_title)
    TextView homeTitle;
    @Bind(R.id.home_page)
    RelativeLayout homePage;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private final static int MSG_UPDATE_STEP = 0;
    private UpdateHandler updateHandler = new UpdateHandler(this);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(STEP_ACTION);
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        processStepNow = (View) viewRoot.findViewById(R.id.process_step_now);
        processStepNow.setBackground(new StepProcessDrawable(getContext()).setmAngle(90));
        toayStep = (TextView) viewRoot.findViewById(R.id.toay_step);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        getContext().unregisterReceiver(stepLocationReciver);
    }

    @Override
    public void responseLocation(String city, double latitude, double longitude) {
        LocalLog.d(TAG, "responseLocation() enter city =" + city + " ,latitude = " + latitude
                + ",longitude= " + longitude);
    }

    @Override
    public void responseMonthIncome() {
        LocalLog.d(TAG, "responseMonthIncome() enter");
    }

    @Override
    public void responseStepToday(int stepToday) {
        LocalLog.d(TAG, "responseStepToday() enter");
        //if (toayStep != null) {
        toayStep.setText(String.valueOf(stepToday));
        //}
        Presenter.getInstance(getContext()).postUserStep(stepToday);
        Message message = Message.obtain();
        message.what = MSG_UPDATE_STEP;
        message.arg1 = stepToday;
        updateHandler.sendMessageDelayed(message, 5000);
    }

    @Override
    public void responseTarget(int personTarget) {
        LocalLog.d(TAG, "responseTarget() enter");
    }

    @Override
    public void responseTodayIncome() {
        LocalLog.d(TAG, "responseTodayIncome() enter");
    }

    @Override
    public void responseWeather() {
        LocalLog.d(TAG, "responseWeather() enter");
    }

    @Override
    public void response(PostUserStepResponse postUserStepResponse) {
        LocalLog.d(TAG, "PostUserStepResponse() enter");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    private static class UpdateHandler extends Handler {
        WeakReference<HomePageFragment> weakReference;

        public UpdateHandler(HomePageFragment homePageFragment) {
            weakReference = new WeakReference<HomePageFragment>(homePageFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HomePageFragment homePageFragment = weakReference.get();
            if (homePageFragment != null) {
                switch (msg.what) {
                    case MSG_UPDATE_STEP:
                        if (hasMessages(MSG_UPDATE_STEP)) {
                            removeMessages(MSG_UPDATE_STEP);
                        }
                        //ava.lang.NullPointerException: Attempt to invoke virtual method 'android.content.Context android.content.Context.getApplicationContext()' on a null obje

                        //if (homePageFragment.getContext() != null) {
                        Presenter.getInstance(homePageFragment.getContext()).postUserStep(msg.arg1);
                        //}
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
