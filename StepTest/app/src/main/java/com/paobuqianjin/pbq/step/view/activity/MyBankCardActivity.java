package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.BankCardAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyBankCardActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.rv_cards)
    RecyclerView rvCards;
    LinearLayoutManager layoutManager;

    @Override
    protected String title() {
        return "银行卡";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bank_card);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        layoutManager = new LinearLayoutManager(this);
        rvCards = (RecyclerView) findViewById(R.id.rv_cards);
        rvCards.setLayoutManager(layoutManager);
        rvCards.setAdapter(new BankCardAdapter(this, null));

    }

    @Override
    protected void setRightValue(int id, Object obj) {
        super.setRightValue(id, obj);
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        startActivity(BindCardActivity.class, null);
    }

    @Override
    public Object right() {
        return R.mipmap.ic_add_white;
    }
}
