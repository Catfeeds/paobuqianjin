package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TimeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SponsorDateActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    LinearLayoutManager layoutManager;
    @Bind(R.id.time_recycler)
    RecyclerView timeRecycler;

    private String[] data = new String[7];

    @Override
    protected String title() {
        return "自定义";
    }

    @Override
    public Object right() {
        return "确定";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_time_select_fg);
        ButterKnife.bind(this);
        setToolBarListener(this);
        String datas = getIntent().getStringExtra("data");
        if (!TextUtils.isEmpty(datas)) {
            String[] workTimeStrList = datas.split(",");
            for (String time : workTimeStrList) {
                switch (time) {
                    case "周一": {
                        data[0] = time;
                    }
                    break;
                    case "周二": {
                        data[1] = time;
                    }
                    break;
                    case "周三": {
                        data[2] = time;

                    }
                    break;
                    case "周四": {
                        data[3] = time;
                    }
                    break;
                    case "周五": {
                        data[4] = time;
                    }
                    break;
                    case "周六": {
                        data[5] = time;
                    }
                    break;
                    case "周日": {
                        data[6] = time;
                    }
                    break;
                }
            }
        }
        layoutManager = new LinearLayoutManager(this);
        timeRecycler.setLayoutManager(layoutManager);
        timeRecycler.setAdapter(new TimeAdapter(this, this.data));
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        Intent intent = getIntent();
        String date = "";
        for (String time : data) {
            if (!TextUtils.isEmpty(time)) {
                if (!TextUtils.isEmpty(date)) {
                    date += ",";
                }
                date += time;
            }
        }
        LocalLog.d("-------------", date);
        intent.putExtra("data", date);
        setResult(2, intent);
        finish();
    }
}
