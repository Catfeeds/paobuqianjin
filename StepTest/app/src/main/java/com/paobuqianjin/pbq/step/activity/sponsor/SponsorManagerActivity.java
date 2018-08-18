package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.GetUserBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NormalResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorAdapter;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorManagerActivity extends BaseBarActivity implements InnerCallBack {
    private static final String TAG = SponsorManagerActivity.class.getName();
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.location_des)
    TextView locationDes;
    @Bind(R.id.layout_default)
    LinearLayout layoutDefault;
    @Bind(R.id.select_circle)
    ImageView selectCircle;
    @Bind(R.id.layout_delete)
    LinearLayout layoutDelete;
    @Bind(R.id.delete_sponsor)
    ImageView deleteSponsor;
    @Bind(R.id.delete_sponsor_des)
    TextView deleteSponsorDes;
    @Bind(R.id.layout_edit)
    LinearLayout layoutEdit;
    @Bind(R.id.edit_sponsor)
    ImageView editSponsor;
    @Bind(R.id.edit_sponsor_des)
    TextView editSponsorDes;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.sponsor_list)
    SwipeMenuRecyclerView sponsorList;
    @Bind(R.id.tv_add_sponsor)
    Button tv_add_sponsor;
    @Bind(R.id.default_sponsor)
    RelativeLayout default_sponsor;
    @Bind(R.id.tv_default)
    TextView tvDefault;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    LinearLayoutManager layoutManager;
    private final static int REQUEST_SPONSOR_INFO = 1;
    private List<GetUserBusinessResponse.DataBeanX.DataBean> data = new ArrayList<>();
    private SponsorAdapter adapter;
    public boolean isRefresh;
    private boolean isLoad;
    private boolean isFirstLoad = true;
    private Intent intent;
    private int pageNum = 1;
    private NormalDialog deleteNormal;
    public int businessId = -1;
    public boolean isDelete;

    @Override
    protected String title() {
        return "管理商铺信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_mananger_fg);
        ButterKnife.bind(this);
        init();
        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void refresh() {
        isRefresh = true;
        pageNum = 1;
        getData();
    }

    private void init() {
        intent = getIntent();
        businessId = intent.getIntExtra("businessId", -1);
        layoutManager = new LinearLayoutManager(this);
        sponsorList.setLayoutManager(layoutManager);
        adapter = new SponsorAdapter(this, data, intent);
        sponsorList.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isFirstLoad = true;
                refresh();
            }
        });
        sponsorList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= layoutManager.getItemCount() - 1 && !isLoad) {
                        isRefresh = false;
                        getData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    private void getData() {
        isLoad = true;
        GetUserBusinessParam param = new GetUserBusinessParam();
        param.setUserid(Presenter.getInstance(this).getId()).setPage(pageNum);
        Presenter.getInstance(this).getUserBusiness(param, this);
    }

    @OnClick({R.id.tv_add_sponsor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_sponsor: {
                goToAdd();
            }
            break;
        }
    }

    @Override
    public void innerCallBack(Object object) {
        if (object instanceof ErrorCode) {
            PaoToastUtils.showShortToast(this, ((ErrorCode) object).getMessage());
        } else if (object instanceof GetUserBusinessResponse) {
            if (((GetUserBusinessResponse) object).getError() == 0) {
                if (isRefresh) {
                    data.clear();
                    refreshLayout.setRefreshing(false);
                }
                data.addAll(((GetUserBusinessResponse) object).getData().getData());
                if (isRefresh) {
                    if (data.size() > 0 && data.get(0).getDefaultX() == 1) {
                        default_sponsor.setVisibility(View.VISIBLE);
                        line.setVisibility(View.VISIBLE);
                        setDefaultView(data.get(0));
                        data.remove(0);
                    } else {
                        default_sponsor.setVisibility(View.GONE);
                        line.setVisibility(View.INVISIBLE);
                        if (data.size() == 0 && ((GetUserBusinessResponse) object).getData().getPagenation().getPage() == 1) {
                            if (isFirstLoad) {
                                goToAdd();
                            } else {
                                noDataBack();
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                pageNum++;
                isLoad = false;
            } else {
                if (isRefresh) {
                    data.clear();
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                    default_sponsor.setVisibility(View.GONE);
                    line.setVisibility(View.INVISIBLE);
                    if (data.size() == 0 && pageNum == 1) {
                        if (isFirstLoad) {
                            goToAdd();
                        } else {
                            noDataBack();
                        }
                    }
                }
            }
            isFirstLoad = false;
        }
    }

    private void noDataBack() {
        setResult(ReleaseTaskSponsorFragment.RESULT_NO_SPONSOR);
        finish();
    }

    @Override
    public void onBackPressed() {
        LocalLog.d(TAG, SponsorManagerActivity.this.businessId + "------" + isDelete);

        if (isDelete) {
            setResult(ReleaseTaskSponsorFragment.RESULT_DELETE_SPONSOR);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void goToAdd() {
        Intent intent = new Intent();
        intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
        intent.setClass(this, SponsorInfoActivity.class);
        startActivityForResult(intent, REQUEST_SPONSOR_INFO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SPONSOR_INFO && resultCode > 0) {
            refresh();
        }
    }

    private void setDefaultView(final GetUserBusinessResponse.DataBeanX.DataBean dataBean) {
        name.setText(dataBean.getName());
        locationDes.setText(dataBean.getAddra() + dataBean.getAddress());
        selectCircle.setImageResource(R.mipmap.choose);
        layoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                intent.setClass(SponsorManagerActivity.this, SponsorInfoActivity.class);
                intent.putExtra("businessId", dataBean.getBusinessid());
                intent.putExtra("latitude", dataBean.getLatitude());
                intent.putExtra("longitude", dataBean.getLongitude());
                startActivityForResult(intent, REQUEST_SPONSOR_INFO);
            }
        });
        layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(dataBean.getBusinessid());
            }
        });
        layoutDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        default_sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("businessId", dataBean.getBusinessid());
                intent.putExtra("name", dataBean.getName());
                setResult(ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG, intent);
                finish();
            }
        });
    }

    private void showDeleteDialog(final int businessid) {
        if (deleteNormal == null) {
            deleteNormal = new NormalDialog(SponsorManagerActivity.this);
            deleteNormal.setMessage("确认删除商铺信息");
            deleteNormal.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    deleteNormal.dismiss();
                }
            });
        }
        deleteNormal.setYesOnclickListener("删除", new NormalDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Presenter.getInstance(SponsorManagerActivity.this).deleteBusiness(businessid, new InnerCallBack() {
                    @Override
                    public void innerCallBack(Object object) {
                        if (object instanceof ErrorCode) {
                            PaoToastUtils.showShortToast(SponsorManagerActivity.this, ((ErrorCode) object).getMessage());
                        } else if (object instanceof NormalResponse) {
                            if (((NormalResponse) object).getError() == 0) {
                                LocalLog.d(TAG, SponsorManagerActivity.this.businessId + "------" + businessid);
                                PaoToastUtils.showShortToast(SponsorManagerActivity.this, "删除成功");
                                if (SponsorManagerActivity.this.businessId != -1 &&
                                        SponsorManagerActivity.this.businessId == businessid) {
                                    SponsorManagerActivity.this.isDelete = true;
                                }
                                deleteNormal.dismiss();
                                refresh();
                            } else {
                                PaoToastUtils.showShortToast(SponsorManagerActivity.this, ((NormalResponse) object).getMessage());
                            }
                        }
                    }
                });
            }
        });
        deleteNormal.show();
    }
}