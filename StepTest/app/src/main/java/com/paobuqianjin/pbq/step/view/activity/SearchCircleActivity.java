package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.SearchCircleStyleTextViewFragment;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleActivity extends BaseBarActivity {
    private SearchCircleStyleTextViewFragment searchCircleFragment = new SearchCircleStyleTextViewFragment();

    @Override
    protected String title() {
        return "精选圈子";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_circle_main_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.search_fragment_container, searchCircleFragment)
                .show(searchCircleFragment).commit();
    }
}
