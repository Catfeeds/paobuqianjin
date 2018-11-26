package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/26.
 */

public class EmptyTaskFragment extends BaseFragment {
    private final static String TAG = EmptyTaskFragment.class.getSimpleName();
    @Bind(R.id.no_record)
    ImageView noRecord;
    @Bind(R.id.no_task)
    TextView noTask;
    @Bind(R.id.go_to_release)
    TextView goToRelease;
    private final static String PKG_ACTION = "com.paobuqianjin.person.PKG_ACTION";
    private int style = -1;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_empty_fg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View viewRoot) {
        noTask = (TextView) viewRoot.findViewById(R.id.no_task);
        if (style != -1) {
            loadingDes(style);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    private void loadingDes(int style) {
        String des = "";
        switch (style) {
            case 0:
                des = getString(R.string.parent_red_des);
                break;
            case 1:
                des = getString(R.string.child_red_des);
                break;
            case 2:
                des = getString(R.string.dear_red_des);
                break;
            case 3:
                des = getString(R.string.dear_red_des);
                break;
            case 4:
                des = getString(R.string.friend_red_des);
                break;
        }
        noTask.setText(des);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.go_to_release)
    public void onClick() {
        LocalLog.d(TAG, "去发布任务");
        Intent intent = new Intent();
        intent.setAction(PKG_ACTION);
        intent.setClass(getContext(), TaskReleaseActivity.class);
        startActivity(intent);
    }
}
