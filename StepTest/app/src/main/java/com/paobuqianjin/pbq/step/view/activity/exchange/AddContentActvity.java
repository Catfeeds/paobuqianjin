package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2019/1/7.
 */

public class AddContentActvity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.good_pic)
    CircularImageView goodPic;
    @Bind(R.id.good_name)
    TextView goodName;
    @Bind(R.id.grid_view)
    RongGridView gridView;
    @Bind(R.id.review_h)
    ImageView reviewH;
    @Bind(R.id.review_d)
    ImageView reviewD;
    @Bind(R.id.review_l)
    ImageView reviewL;
    @Bind(R.id.des_recycler)
    SwipeMenuRecyclerView desRecycler;
    @Bind(R.id.trf_recycler)
    SwipeMenuRecyclerView trfRecycler;
    @Bind(R.id.taidu_recycler)
    SwipeMenuRecyclerView taiduRecycler;

    @Override
    protected String title() {
        return "发表评价";
    }

    @Override
    public Object right() {
        return "发表";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_content_activity);
        ButterKnife.bind(this);
    }

    
}
