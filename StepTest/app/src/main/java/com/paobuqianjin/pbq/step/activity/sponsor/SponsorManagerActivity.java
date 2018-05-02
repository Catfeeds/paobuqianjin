package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.activity.SponsorInfoCollectActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorManagerActivity extends BaseBarActivity {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.location_des)
    TextView locationDes;
    @Bind(R.id.select_circle)
    ImageView selectCircle;
    @Bind(R.id.delete_sponsor)
    ImageView deleteSponsor;
    @Bind(R.id.delete_sponsor_des)
    TextView deleteSponsorDes;
    @Bind(R.id.edit_sponsor)
    ImageView editSponsor;
    @Bind(R.id.edit_sponsor_des)
    TextView editSponsorDes;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.sponsor_list)
    SwipeMenuRecyclerView sponsorList;
    @Bind(R.id.tv_add_sponsor)
    Button tv_add_sponsor;

    LinearLayoutManager layoutManager;
    private final static int REQUEST_SPONSOR_INFO = 1;

    @Override
    protected String title() {
        return "管理商铺信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_mananger_fg);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        sponsorList.setLayoutManager(layoutManager);
        sponsorList.setAdapter(new SponsorAdapter(this, null));
    }

    @OnClick({R.id.tv_add_sponsor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_sponsor: {
                Intent intent = new Intent();
                intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                intent.setClass(this, SponsorInfoActivity.class);
                startActivityForResult(intent, REQUEST_SPONSOR_INFO);
            }
            break;
        }
    }
}
