package com.paobuqianjin.pbq.step.view.fragment.task;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskMyRecInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/24.
 */

public class TaskFragment extends BaseFragment implements TaskMyRecInterface {
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
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.container_task)
    RelativeLayout containerLive;

    AllTaskFragment allTaskFragment;
    FinishedTaskFragment finishedTaskFragment;
    UnFinishTaskFragment unFinishTaskFragment;
    EmptyTaskFragment emptyTaskFragment;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    private int mCurrentIndex = 0;
    private int mIndex = 0;
    private Fragment[] mFragments;
    private ArrayList<MyRecTaskRecordResponse.DataBeanX.DataBean> allTaskList, doingTaskList, finishTaskList;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_top_fg;
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
        Presenter.getInstance(getContext()).getAllMyRecTask();
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        allTaskFragment = new AllTaskFragment();
        finishedTaskFragment = new FinishedTaskFragment();
        unFinishTaskFragment = new UnFinishTaskFragment();
        emptyTaskFragment = new EmptyTaskFragment();
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTvRight = (TextView) viewRoot.findViewById(R.id.bar_tv_right);
        barTitle.setText("任务列表");
        barTvRight.setText("发布");
        mFragments = new Fragment[]{allTaskFragment, unFinishTaskFragment, finishedTaskFragment};
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container_task, allTaskFragment)
                .add(R.id.container_task, unFinishTaskFragment)
                .add(R.id.container_task, finishedTaskFragment)
                .add(R.id.container_task, emptyTaskFragment)
                .show(allTaskFragment)
                .hide(unFinishTaskFragment)
                .hide(finishedTaskFragment)
                .hide(emptyTaskFragment)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.task_all, R.id.task_un_finish, R.id.task_finished, R.id.bar_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.task_all:
                mIndex = 0;
                LocalLog.d(TAG, "全部");
                onTabIndex(mIndex);
                break;
            case R.id.task_un_finish:
                mIndex = 1;
                LocalLog.d(TAG, "未完成");
                onTabIndex(mIndex);
                break;
            case R.id.task_finished:
                mIndex = 2;
                LocalLog.d(TAG, "已完成");
                onTabIndex(mIndex);
                break;
            case R.id.bar_tv_right:
                LocalLog.d(TAG, "发布");
                startActivity(TaskReleaseActivity.class, null);
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
                if (doingTaskList == null) {
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
            }

            setCurrentIndexStateUnSelect();
        }
        mCurrentIndex = fragmentIndex;
        setCurrentIndexStateSelected();
    }

    @Override
    public void response(MyRecTaskRecordResponse myRecvTaskRecordResponse) {
        LocalLog.d(TAG, "MyRecTaskRecordResponse() enter" + myRecvTaskRecordResponse.toString());
        if (myRecvTaskRecordResponse.getError() == 0) {
            for (int i = 0; i < myRecvTaskRecordResponse.getData().getData().size(); i++) {
                if (myRecvTaskRecordResponse.getData().getData().get(i).getIs_receive() == 1) {
                    LocalLog.d(TAG, "已接任务");
                    if (myRecvTaskRecordResponse.getData().getData().get(i).getIs_finished() == 0) {
                        if (doingTaskList == null) {
                            doingTaskList = new ArrayList<>();
                            doingTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                        } else {
                            doingTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                        }
                    } else {
                        if (finishTaskList == null) {
                            finishTaskList = new ArrayList<>();
                            finishTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                        } else {
                            finishTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                        }
                    }
                } else if (myRecvTaskRecordResponse.getData().getData().get(i).getIs_receive() == 0) {
                    LocalLog.d(TAG, "未接任务");
                    if (doingTaskList == null) {
                        doingTaskList = new ArrayList<>();
                        doingTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                    } else {
                        doingTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
                    }
                }
                if (allTaskList == null) {
                    allTaskList = new ArrayList<>();
                }
                allTaskList.add(myRecvTaskRecordResponse.getData().getData().get(i));
            }
            allTaskFragment.setData(allTaskList);
            unFinishTaskFragment.setData(doingTaskList);
            finishedTaskFragment.setData(finishTaskList);
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
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
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
