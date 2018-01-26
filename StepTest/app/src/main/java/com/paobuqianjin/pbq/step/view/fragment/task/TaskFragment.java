package com.paobuqianjin.pbq.step.view.fragment.task;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/24.
 */

public class TaskFragment extends BaseFragment {
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

    @Override
    protected int getLayoutResId() {
        return R.layout.task_top_fg;
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
        allTaskFragment = new AllTaskFragment();
        finishedTaskFragment = new FinishedTaskFragment();
        unFinishTaskFragment = new UnFinishTaskFragment();
        emptyTaskFragment = new EmptyTaskFragment();
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTvRight = (TextView) viewRoot.findViewById(R.id.bar_tv_right);
        barTitle.setText("任务列表");
        barTvRight.setText("发布");
        mFragments = new Fragment[]{allTaskFragment, finishedTaskFragment, emptyTaskFragment};
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container_task, allTaskFragment)
                .add(R.id.container_task, finishedTaskFragment)
                .add(R.id.container_task, emptyTaskFragment)
                .show(allTaskFragment)
                .hide(finishedTaskFragment)
                .hide(emptyTaskFragment)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
    @TargetApi(23)
    private void setCurrentIndexStateUnSelect() {
        if (mCurrentIndex == 0) {
            taskAll.setBackground(getContext().getDrawable(R.drawable.rectangele_four_full_r_unselected));
            taskAll.setTextColor(getContext().getColor(R.color.color_161727));
        } else if (mCurrentIndex == 1) {
            taskUnFinish.setBackground(getContext().getDrawable(R.drawable.rectangle_four_fill_outline_unselected));
            taskUnFinish.setTextColor(getContext().getColor(R.color.color_161727));
        } else if (mCurrentIndex == 2) {
            taskFinished.setBackground(getContext().getDrawable(R.drawable.rectangle_four_full_left_unselect));
            taskFinished.setTextColor(getContext().getColor(R.color.color_161727));
        }
    }

    /*@desc   当前为选中状态
    *@function
    *@param
    *@return 
    */
    @TargetApi(23)
    private void setCurrentIndexStateSelected() {
        if (mCurrentIndex == 0) {
            taskAll.setBackground(getContext().getDrawable(R.drawable.rectangle_four_full_r_selected));
            taskAll.setTextColor(getContext().getColor(R.color.color_f8));
        } else if (mCurrentIndex == 1) {
            taskUnFinish.setBackground(getContext().getDrawable(R.drawable.rectangle_four_fill_outline_selected));
            taskUnFinish.setTextColor(getContext().getColor(R.color.color_f8));
        } else if (mCurrentIndex == 2) {
            taskFinished.setBackground(getContext().getDrawable(R.drawable.rectangle_four_full_left_select));
            taskFinished.setTextColor(getContext().getColor(R.color.color_f8));
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
            trx.show(mFragments[fragmentIndex]).commit();
            setCurrentIndexStateUnSelect();
        }
        mCurrentIndex = fragmentIndex;
        setCurrentIndexStateSelected();
    }

}
