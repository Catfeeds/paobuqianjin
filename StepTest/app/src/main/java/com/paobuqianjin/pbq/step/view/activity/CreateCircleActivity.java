package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.SelectDialogFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pbq on 2017/12/14.
 */

public class CreateCircleActivity extends BaseBarActivity {
    private final static String TAG = CreateCircleActivity.class.getSimpleName();

    @Override
    protected String title() {
        return "创建圈子";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_circle_layout);
        initBarView();
    }

    public void onStyleSelect(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.switch_style:
                    List<String> strings = new LinkedList<String>();
                    strings.add("个人圈子");
                    strings.add("企业圈子");
                    SelectDialogFragment selectDialogFragment = SelectDialogFragment.newInstance(strings, uiCreateCircleInterface);
                    selectDialogFragment.show(this.getFragmentManager(), null);
                    break;
            }
        }
    }

    private UiCreateCircleInterface uiCreateCircleInterface = new UiCreateCircleInterface() {

    };
}
