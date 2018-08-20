package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundDetailStyleResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;
import com.paobuqianjin.pbq.step.view.emoji.EmotionLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/8/17.
 */

public class RoundRedDetailActivity extends BaseBarActivity {
    private final static String TAG = RoundRedDetailActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.red_success)
    TextView redSuccess;
    @Bind(R.id.red_result)
    TextView redResult;
    @Bind(R.id.into_wallet)
    TextView intoWallet;
    @Bind(R.id.recv_person)
    TextView recvPerson;
    @Bind(R.id.head_recycler)
    RecyclerView headRecycler;
    @Bind(R.id.sponsor_describe)
    TextView sponsorDescribe;
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.sponsor_name)
    TextView sponsorName;
    @Bind(R.id.tel_text)
    TextView telText;
    @Bind(R.id.address_text)
    TextView addressText;
    @Bind(R.id.sponsor_text)
    TextView sponsorText;
    @Bind(R.id.sponsor_pic)
    TextView sponsorPic;
    @Bind(R.id.sponsor_more)
    TextView sponsorMore;
    @Bind(R.id.image_a)
    ImageView imageA;
    @Bind(R.id.image_b)
    ImageView imageB;
    @Bind(R.id.image_c)
    ImageView imageC;
    @Bind(R.id.sponsor_pic_linear)
    LinearLayout sponsorPicLinear;
    @Bind(R.id.like_sponsor_icon)
    ImageView likeSponsorIcon;
    @Bind(R.id.like_sponsor_desc)
    TextView likeSponsorDesc;
    @Bind(R.id.like_sponsor_span)
    LinearLayout likeSponsorSpan;
    @Bind(R.id.sponsor_content)
    ImageView sponsorContent;
    @Bind(R.id.sponsor_content_num)
    TextView sponsorContentNum;
    @Bind(R.id.sponsor_content_span)
    LinearLayout sponsorContentSpan;
    @Bind(R.id.sponsor_opreation)
    LinearLayout sponsorOpreation;
    @Bind(R.id.new_content)
    TextView newContent;
    @Bind(R.id.line_content)
    ImageView lineContent;
    @Bind(R.id.content_recycler)
    RecyclerView contentRecycler;
    @Bind(R.id.v_empty)
    View vEmpty;
    @Bind(R.id.edit_expression)
    ImageView editExpression;
    @Bind(R.id.content_text)
    CustomEdit contentText;
    @Bind(R.id.send_content)
    Button sendContent;
    @Bind(R.id.edit_content)
    LinearLayout editContent;
    @Bind(R.id.elEmotion)
    EmotionLayout elEmotion;
    String result_str = "";
    String red_id = "";
    ArrayList<RoundDetailStyleResponse.DataBean.ReceiverListBean> arrayRecList = new ArrayList<>();
    List<View> Mview = new ArrayList<>();
    @Bind(R.id.pic_sample)
    ImageView picSample;
    @Bind(R.id.current_pic)
    TextView currentPic;
    ArrayList<RoundDetailStyleResponse.DataBean.BusinessImgBean> goodsImgsBeans = new ArrayList<>();
    @Bind(R.id.go_to)
    ImageView goTo;

    @Override
    protected String title() {
        return "红包详情";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_detail_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        redSuccess = (TextView) findViewById(R.id.red_success);
        redResult = (TextView) findViewById(R.id.red_result);
        intoWallet = (TextView) findViewById(R.id.into_wallet);
        headRecycler = (RecyclerView) findViewById(R.id.head_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRecycler.setLayoutManager(linearLayoutManager);
        headRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(30));
        sponsorDescribe = (TextView) findViewById(R.id.sponsor_describe);
        likeSponsorIcon = (ImageView) findViewById(R.id.like_sponsor_icon);
        likeSponsorDesc = (TextView) findViewById(R.id.like_sponsor_desc);
        sponsorContentNum = (TextView) findViewById(R.id.sponsor_content_num);
        Intent intent = getIntent();
        if (intent != null) {
            red_id = intent.getStringExtra(getPackageName() + "red_id");
            result_str = intent.getStringExtra(getPackageName() + "red_result");
            if (!TextUtils.isEmpty(red_id)) {
                getRedDetail(red_id);
            }
            if (!TextUtils.isEmpty(result_str)) {
                try {
                    float red_result = Float.parseFloat(result_str);
                    redSuccess.setVisibility(View.VISIBLE);
                    redResult.setText(result_str);
                    intoWallet.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    redResult.setText(result_str);
                }
            }

        }
    }

    private void getRedDetail(final String redId) {
        String url = NetApi.urlRedDetail + redId;
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    RoundDetailStyleResponse roundDetailStyleResponse = new Gson().fromJson(s, RoundDetailStyleResponse.class);
                    if (roundDetailStyleResponse.getData().getReceiver_list() != null) {
                        int size = roundDetailStyleResponse.getData().getReceiver_list().size();
                        for (int i = 0; i < size; i++) {
                            if (i < 5) {
                                arrayRecList.add(roundDetailStyleResponse.getData().getReceiver_list().get(i));
                            } else {
                                break;
                            }
                        }
                        if (arrayRecList.size() > 0) {
                            LocalLog.d(TAG, "#########");
                            LikeUserAdapter likeUserAdapter = new LikeUserAdapter(RoundRedDetailActivity.this, arrayRecList);
                            headRecycler.setAdapter(likeUserAdapter);
                        }
                        sponsorDescribe.setText(roundDetailStyleResponse.getData().getContent());

                        //红包活动图片
                        if (roundDetailStyleResponse.getData().getRed_img() != null) {
                            int imgSize = roundDetailStyleResponse.getData().getRed_img().size();
                            for (int j = 0; j < imgSize; j++) {
                                View view = LayoutInflater.from(RoundRedDetailActivity.this)
                                        .inflate(R.layout.red_iamge_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.red_content_img);
                                Presenter.getInstance(RoundRedDetailActivity.this).getPlaceErrorImage(imageView, roundDetailStyleResponse.getData().getRed_img()
                                        .get(j).getUrl(), R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Mview.add(view);
                            }
                        }

                        if (Mview.size() > 0) {
                            currentPic.setText(String.valueOf(1) + "/" + Mview.size());
                            sponsorImages.setAdapter(new ImageViewPagerAdapter(RoundRedDetailActivity.this, Mview));
                            sponsorImages.addOnPageChangeListener(onPageChangeListener);
                        }

                        //环境照
                        if (roundDetailStyleResponse.getData().getBusiness_img() != null) {
                            size = roundDetailStyleResponse.getData().getBusiness_img().size();
                            goodsImgsBeans.addAll(roundDetailStyleResponse.getData().getBusiness_img());
                            if (size == 1) {
                                imageA.setVisibility(View.VISIBLE);
                                Presenter.getInstance(RoundRedDetailActivity.this).getPlaceErrorImage(imageA, roundDetailStyleResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                            } else if (size == 2) {
                                imageA.setVisibility(View.VISIBLE);
                                imageB.setVisibility(View.VISIBLE);
                                Presenter.getInstance(RoundRedDetailActivity.this).getImage(imageA, roundDetailStyleResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RoundRedDetailActivity.this).getImage(imageB, roundDetailStyleResponse.getData().getBusiness_img().get(1).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                            } else if (size >= 3) {
                                imageA.setVisibility(View.VISIBLE);
                                imageC.setVisibility(View.VISIBLE);
                                imageB.setVisibility(View.VISIBLE);
                                Presenter.getInstance(RoundRedDetailActivity.this).getImage(imageA, roundDetailStyleResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RoundRedDetailActivity.this).getImage(imageB, roundDetailStyleResponse.getData().getBusiness_img().get(1).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RoundRedDetailActivity.this).getImage(imageC, roundDetailStyleResponse.getData().getBusiness_img().get(2).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                            }
                        }

                        //点赞和评论
                        if (roundDetailStyleResponse.getData().getIs_zan() == 0) {
                            likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                        } else {
                            likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                        }
                        //评论列表
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            LocalLog.d(TAG, "position = " + position);
            currentPic.setText(String.valueOf(position + 1) + "/" + Mview.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @OnClick({R.id.go_to, R.id.sponsor_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_to:
                Intent intent = new Intent();
                intent.setClass(this, RoundRedRelActivity.class);
                startActivity(intent);
                break;
            case R.id.sponsor_more:
                //产看商家图片
                break;
        }
    }
}
