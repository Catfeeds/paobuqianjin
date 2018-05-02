package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TimeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SponsorDateActivity extends BaseBarActivity {

    LinearLayoutManager layoutManager;
    @Bind(R.id.time_recycler)
    RecyclerView timeRecycler;

    private boolean[] data = new boolean[7];

    @Override
    protected String title() {
        return "自定义";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_time_select_fg);
        ButterKnife.bind(this);
        data = getIntent().getBooleanArrayExtra("data");
        layoutManager = new LinearLayoutManager(this);
        timeRecycler.setLayoutManager(layoutManager);
        timeRecycler.setAdapter(new TimeAdapter(this, data));
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("data", data);
        setResult(2, intent);
        finish();
    }
}
