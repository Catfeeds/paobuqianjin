package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.bundle.GoodImageData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorCommentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.SponsorGoodsPicLookActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorContentAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.paobuqianjin.pbq.step.view.emoji.EmotionKeyboard;
import com.paobuqianjin.pbq.step.view.emoji.EmotionLayout;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionExtClickListener;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionSelectedListener;
import com.umeng.commonsdk.debug.E;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imageloader.utils.L;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/1/23.
 */

public class SponsorDetailFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = SponsorDetailFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.pic_sample)
    ImageView picSample;
    @Bind(R.id.current_pic)
    TextView currentPic;
    @Bind(R.id.sponsor_face_span)
    RelativeLayout sponsorFaceSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.sponsor_tel_icon)
    ImageView sponsorTelIcon;
    @Bind(R.id.sponsor_tel_num)
    TextView sponsorTelNum;
    @Bind(R.id.sponsor_tel_span)
    RelativeLayout sponsorTelSpan;
    @Bind(R.id.sponsor_time_icon)
    ImageView sponsorTimeIcon;
    @Bind(R.id.sponsor_time_num)
    TextView sponsorTimeNum;
    @Bind(R.id.sponsor_time_span)
    RelativeLayout sponsorTimeSpan;
    @Bind(R.id.sponsor_location_icon)
    ImageView sponsorLocationIcon;
    @Bind(R.id.sponsor_location_num)
    TextView sponsorLocationNum;
    @Bind(R.id.sponsor_location_span)
    RelativeLayout sponsorLocationSpan;
    @Bind(R.id.line2)
    ImageView line2;
    @Bind(R.id.sponsor_have)
    TextView sponsorHave;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.goto_sponsor)
    RelativeLayout gotoSponsor;
    @Bind(R.id.center_pic)
    ImageView centerPic;
    @Bind(R.id.sponsor_tel_num_str)
    TextView sponsorTelNumStr;
    @Bind(R.id.sponsor_time_num_str)
    TextView sponsorTimeNumStr;
    @Bind(R.id.location_str)
    TextView locationStr;
    @Bind(R.id.more_str)
    TextView moreStr;
    @Bind(R.id.goods_a)
    ImageView goodsA;
    @Bind(R.id.goods_b)
    ImageView goodsB;
    List<View> Mview = new ArrayList<>();
    private final static String SHOW_SPONSOR_PICS_ACTION = "com.paobuqianjin.pbq.step.SHOW_PIC_ACTION";
    ArrayList<SponsorDetailResponse.DataBean.EnvironmentImgsBean> goodsImgsBeans = new ArrayList<>();
    @Bind(R.id.sponsor_name)
    TextView sponsorName;
    //领取商户红包结果
    String redResultStr = "";
    @Bind(R.id.collect_sponsor)
    LinearLayout collectSponsor;
    @Bind(R.id.like_sponsor_span)
    LinearLayout likeSponsorSpan;
    @Bind(R.id.sponsor_content_span)
    LinearLayout sponsorContentSpan;
    @Bind(R.id.kepp_sponsor_icon)
    ImageView keppSponsorIcon;
    @Bind(R.id.like_sponsor_icon)
    ImageView likeSponsorIcon;
    @Bind(R.id.like_sponsor_desc)
    TextView likeSponsorDesc;
    @Bind(R.id.sponsor_content_num)
    TextView sponsorContentNum;
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
    @Bind(R.id.content_recycler)
    RecyclerView contentRecycler;
    @Bind(R.id.sponsor_scroll)
    BounceScrollView sponsorScroll;
    private int bussinessId = -1;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 10;
    private boolean is_vote = false;
    private int localVoteNum = 0, localCommentNum = 0;
    SponsorContentAdapter sponsorContentAdapter;
    CustomEdit commentEditText;
    private EmotionKeyboard mEmotionKeyboard;
    LinearLayout editStill;
    ArrayList<SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean> arrayList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    private boolean isLoading = false;

    @Override
    protected String title() {
        return "商家详情";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_detail_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        sponsorTelNumStr = (TextView) viewRoot.findViewById(R.id.sponsor_tel_num_str);
        sponsorTimeNumStr = (TextView) viewRoot.findViewById(R.id.sponsor_time_num_str);
        locationStr = (TextView) viewRoot.findViewById(R.id.location_str);
        currentPic = (TextView) viewRoot.findViewById(R.id.current_pic);
        centerPic = (ImageView) viewRoot.findViewById(R.id.center_pic);
        goodsA = (ImageView) viewRoot.findViewById(R.id.goods_a);
        goodsB = (ImageView) viewRoot.findViewById(R.id.goods_b);
        gotoSponsor = (RelativeLayout) viewRoot.findViewById(R.id.goto_sponsor);
        sponsorImages = (ViewPager) viewRoot.findViewById(R.id.sponsor_images);
        sponsorName = (TextView) viewRoot.findViewById(R.id.sponsor_name);
        gotoSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "查看更多");
                Intent intent = new Intent();
                intent.setAction(SHOW_SPONSOR_PICS_ACTION);
                if (goodsImgsBeans.size() < 0) {
                    return;
                }
                GoodImageData goodImageData = new GoodImageData(goodsImgsBeans);
                intent.putExtra(getActivity().getPackageName() + "goods", goodImageData);
                intent.setClass(getContext(), SponsorGoodsPicLookActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            int businessid = intent.getIntExtra(getContext().getPackageName() + "businessid", -1);
            if (businessid == -1) businessid = intent.getIntExtra("businessid", -1);
            if (businessid != -1) {
                this.bussinessId = businessid;
                redResultStr = intent.getStringExtra(getContext().getPackageName() + "red_result");
                LocalLog.d(TAG, "redResultStr = " + redResultStr);
                Presenter.getInstance(getContext()).businessDetail(businessid, sponsorInnerCallBack);
                checkCollectState(false);
                loadContent(businessid);
            }
        }
        Mview.clear();
        keppSponsorIcon = (ImageView) viewRoot.findViewById(R.id.kepp_sponsor_icon);
        likeSponsorIcon = (ImageView) viewRoot.findViewById(R.id.like_sponsor_icon);
        collectSponsor = (LinearLayout) viewRoot.findViewById(R.id.collect_sponsor);
        likeSponsorSpan = (LinearLayout) viewRoot.findViewById(R.id.like_sponsor_span);
        editStill = (LinearLayout) viewRoot.findViewById(R.id.linear_edit);
        initEditView(null, -1, null);
        contentRecycler = (RecyclerView) viewRoot.findViewById(R.id.content_recycler);
        contentRecycler.setNestedScrollingEnabled(false);
        contentRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        contentRecycler.setLayoutManager(layoutManager);
        sponsorContentAdapter = new SponsorContentAdapter(getContext(), arrayList);
        contentRecycler.setAdapter(sponsorContentAdapter);
        sponsorScroll = (BounceScrollView) viewRoot.findViewById(R.id.sponsor_scroll);
        sponsorScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                LocalLog.d(TAG, "topOrBottom = " + topOrBottom);
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {

                }
            }
        });


        contentRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), contentRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemLongClick(View view, int position) {

            }

            @Override
            public void OnItemClick(View view, final int position) {
                if (position < arrayList.size() && position >= 0) {
                    initEditView(String.valueOf(arrayList.get(position).getEvaluateid()), arrayList.get(position).getUserid(), arrayList.get(position).getNickname());
                }
            }
        }));
    }

    /*点赞*/
    private void voteSponsor(final int status) {
        Map<String, String> param = new HashMap<>();
        param.put("status", String.valueOf(status));
        param.put("type", "1");
        param.put("businessid", String.valueOf(bussinessId));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlVoteSponsor, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    if (status == 1) {
                        LocalLog.d(TAG, "点赞成功!");
                        is_vote = true;
                        likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                        localVoteNum++;
                    } else if (status == 2) {
                        LocalLog.d(TAG, "取消点赞!");
                        is_vote = false;
                        likeSponsorIcon.setImageResource(R.drawable.un_like);
                        localVoteNum--;

                    }
                    likeSponsorDesc.setText(String.valueOf(localVoteNum));
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });


    }

    /*获取评论列表*/
    private void loadContent(int bussinessId) {
        String urlContent = NetApi.urlContentList + "comment&type=1" + "&page=" + String.valueOf(pageIndex) + "&pagesize="
                + String.valueOf(PAGESIZE) + "&businessid=" + String.valueOf(bussinessId);
        LocalLog.d(TAG, "urlContent = " + urlContent);
        Presenter.getInstance(getContext()).getPaoBuSimple(urlContent, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        SponsorCommentResponse sponsorCommentResponse = new Gson().fromJson(s, SponsorCommentResponse.class);
                        if (sponsorCommentResponse.getData() != null) {
                            if (sponsorCommentResponse.getData().getData() != null) {
                                localVoteNum = sponsorCommentResponse.getData().getData().getVotesum();
                                if (sponsorCommentResponse.getData().getData().getIs_vote() == 0) {
                                    likeSponsorIcon.setImageResource(R.drawable.un_like);
                                    is_vote = false;
                                } else {
                                    likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                                    is_vote = true;
                                }

                                if (localVoteNum > 0) {
                                    likeSponsorDesc.setText(String.valueOf(sponsorCommentResponse.getData().getData().getVotesum()));
                                } else {
                                    likeSponsorDesc.setText("0");
                                }
                                localCommentNum = sponsorCommentResponse.getData().getData().getCommentsum();
                                if (localCommentNum > 0) {
                                    sponsorContentNum.setText(String.valueOf(sponsorCommentResponse.getData().getData().getCommentsum()));
                                } else {
                                    sponsorContentNum.setText("0");
                                }
                                //评论列表
                                pageCount = sponsorCommentResponse.getData().getPagenation().getPage();
                                if (sponsorCommentResponse.getData().getData().getCommentarray() == null) {
                                    return;
                                }
                                if (pageIndex == 1) {
                                    arrayList.clear();
                                    arrayList.addAll(sponsorCommentResponse.getData().getData().getCommentarray());
                                    if (sponsorContentAdapter == null) {
                                        sponsorContentAdapter = new SponsorContentAdapter(getContext(), arrayList);
                                    } else {
                                        sponsorContentAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    arrayList.addAll(sponsorCommentResponse.getData().getData().getCommentarray());
                                    sponsorContentAdapter.notifyItemRangeInserted(arrayList.size() - sponsorCommentResponse.getData().getData().getCommentarray().size(),
                                            sponsorCommentResponse.getData().getData().getCommentarray().size());
                                    contentRecycler.requestLayout();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (isAdded()) {

                }
            }
        });
    }

    /*用户评价*/
    private void saveContent(final String evaluateid, final String content, final Object object) {
        LocalLog.d(TAG, "saveContent() enter evaluateid " + evaluateid);
        Map<String, String> param = new HashMap<>();
        if (TextUtils.isEmpty(content)) {
            return;
        } else {
            param.put("content", content);
        }
        if (!TextUtils.isEmpty(evaluateid)) {
            param.put("father", evaluateid);
        }
        param.put("type", "1");
        param.put("businessid", String.valueOf(bussinessId));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlSaveContent, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                resetCommentEdittext();
                /*插入评论*/
                LocalLog.d(TAG, "s = " + s);
                localCommentNum++;
                sponsorContentNum.setText(String.valueOf(localCommentNum));
                if (object == null && evaluateid == null) {
                    //插入一层评论
                    SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean commentarrayBean = new SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean();
                    commentarrayBean.setAvatar(Presenter.getInstance(getContext()).getAvatar(getContext()));
                    commentarrayBean.setNickname(Presenter.getInstance(getContext()).getNickName(getContext()));
                    commentarrayBean.setUserid(Presenter.getInstance(getContext()).getId());
                    commentarrayBean.setContent(content);
                    try {
                        long create_time = DateTimeUtil.currentDateParserLong() / 1000;
                        LocalLog.d(TAG, "create_time = " + create_time);
                        commentarrayBean.setCreate_time(create_time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (arrayList.size() > 0) {
                        arrayList.add(0, commentarrayBean);
                        sponsorContentAdapter.notifyItemRangeInserted(0, 1);
                    } else {
                        arrayList.add(0, commentarrayBean);
                        sponsorContentAdapter.notifyDataSetChanged();
                    }
                } else {
                    //插入二层评论
                    SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean commentarrayBean = new SponsorCommentResponse.DataBeanX.DataBean.CommentarrayBean();
                    commentarrayBean.setAvatar(Presenter.getInstance(getContext()).getAvatar(getContext()));
                    commentarrayBean.setNickname(Presenter.getInstance(getContext()).getNickName(getContext()));
                    commentarrayBean.setContent(content);
                    commentarrayBean.setFathername((String) object);
                    commentarrayBean.setUserid(Presenter.getInstance(getContext()).getId());
                    try {
                        long create_time = DateTimeUtil.currentDateParserLong() / 1000;
                        LocalLog.d(TAG, "create_time = " + create_time);
                        commentarrayBean.setCreate_time(create_time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    arrayList.add(0, commentarrayBean);
                    sponsorContentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                }
            }
        });
    }

    //检查店铺收藏状态
    private void checkCollectState(final boolean isClick) {
        if (bussinessId != -1) {
            Map<String, String> param = new HashMap<>();
            param.put("type", "1");
            param.put("businessid", String.valueOf(bussinessId));
            Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlCollectCheck, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (isAdded()) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            jsonObject = jsonObject.getJSONObject("data");
                            int status = jsonObject.getInt("status");
                            final String collectionId = jsonObject.getString("collectionid");
                            if (status == 0) {
                                keppSponsorIcon.setImageResource(R.drawable.no_collect);
                                if (isClick) {
                                    collectSponsor();
                                }
                            } else if (status == 1) {
                                keppSponsorIcon.setImageResource(R.drawable.had_collect);
                                if (isClick) {
                                    deleteCollect(collectionId);
                                }
                            } else {
                                LocalLog.d(TAG, "unknown status");
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                }
            });
        }
    }


    private InnerCallBack sponsorInnerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (!isAdded()) {
                return;
            }
            if (object instanceof ErrorCode) {

            } else if (object instanceof SponsorDetailResponse) {
                if (((SponsorDetailResponse) object).getError() == 0) {
                    SponsorDetailResponse.DataBean dataBean = ((SponsorDetailResponse) object).getData();
                    if (dataBean != null) {
                        LocalLog.d(TAG, "dataBean  =  " + dataBean.toString());
                        if (sponsorTelNumStr == null) {
                            return;
                        }
                        sponsorName.setText(dataBean.getName());
                        sponsorTelNumStr.setText(dataBean.getTel());
                        String workTimeStr = dataBean.getDo_day();
                        String displayTime = "";
                        if (workTimeStr != null) {
                            String[] workTimeStrList = workTimeStr.split(",");
                            if (workTimeStrList != null) {
                                if (workTimeStrList.length >= 7) {
                                    displayTime = "周一至周日 " + dataBean.getS_do_time() + "-" + dataBean.getE_do_time();
                                } else if (workTimeStrList.length > 0) {
                                    displayTime = workTimeStr + " " + dataBean.getS_do_time() + "-" + dataBean.getE_do_time();
                                }
                            }
                        } else {
                            displayTime = "周一至周日 " + "-" + dataBean.getE_do_time();
                        }
                        sponsorTimeNumStr.setText(displayTime);
                        String locationDetail = "";
                        if (!TextUtils.isEmpty(dataBean.getAddra())) {
                            locationDetail = dataBean.getAddra();
                        }
                        if (!TextUtils.isEmpty(dataBean.getAddress())) {
                            if (!TextUtils.isEmpty(locationDetail)) {
                                locationDetail += "-" + dataBean.getAddress();
                            } else {
                                locationDetail = dataBean.getAddress();
                            }
                        }
                        locationStr.setText(locationDetail.replace("/", ""));

                        if (!TextUtils.isEmpty(dataBean.getLogo())) {
                            View view = LayoutInflater.from(getContext()).inflate(R.layout.sponsor_image_view, null);
                            ImageView imageView = (ImageView) view.findViewById(R.id.sponsor_env_img);
                            if (!TextUtils.isEmpty(redResultStr)) {
                                imageView.setImageResource(R.drawable.red_result);
                                TextView redResultTV = (TextView) view.findViewById(R.id.red_result);
                                TextView redSuccessTv = (TextView) view.findViewById(R.id.red_success);
                                TextView redInWalletTv = (TextView) view.findViewById(R.id.into_wallet);
                                try {
                                    float money = Float.parseFloat(redResultStr);
                                    if (money > 0f) {
                                        redSuccessTv.setVisibility(View.VISIBLE);
                                        redInWalletTv.setVisibility(View.VISIBLE);
                                        redResultTV.setText("￥" + redResultStr + "元");
                                    }
                                } catch (Exception e) {
                                    redResultTV.setText(redResultStr);
                                }
                            } else {
                                Presenter.getInstance(getContext()).getImage(imageView, dataBean.getLogo());
                            }
                            Mview.add(view);
                        }
                       /* int sizeEnv = 0;

                        if (dataBean.getLogo_imgs() != null) {
                            sizeEnv = dataBean.getEnvironment_imgs().size();
                            *//*for (int i = 0; i < sizeEnv; i++) {
                                View view = LayoutInflater.from(getContext()).inflate(R.layout.sponsor_image_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.sponsor_env_img);
                                Presenter.getInstance(getContext()).getImage(imageView, dataBean.getEnvironment_imgs().get(i).getUrl());
                                Mview.add(view);
                            }*//*
                            if (sizeEnv >= 1) {
                                View view = LayoutInflater.from(getContext()).inflate(R.layout.sponsor_image_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.sponsor_env_img);
                                Presenter.getInstance(getContext()).getImage(imageView, dataBean.getEnvironment_imgs().get(0).getUrl());
                                Mview.add(view);
                            }
                        }*/
                        if (Mview.size() > 0) {

                            sponsorImages.setAdapter(new ImageViewPagerAdapter(getContext(), Mview));
                            sponsorImages.addOnPageChangeListener(onPageChangeListener);
                        }
                        int size = 0;
                        if (dataBean.getEnvironment_imgs() != null) {
                            size = dataBean.getEnvironment_imgs().size();
                            goodsImgsBeans.addAll(dataBean.getEnvironment_imgs());
                            if (size == 1) {
                                goodsA.setVisibility(View.VISIBLE);
                                Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getEnvironment_imgs().get(0).getUrl());
                            } else if (size == 2) {
                                goodsA.setVisibility(View.VISIBLE);
                                centerPic.setVisibility(View.VISIBLE);
                                Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getEnvironment_imgs().get(0).getUrl());
                                Presenter.getInstance(getContext()).getImage(centerPic, dataBean.getEnvironment_imgs().get(1).getUrl());
                            } else if (size >= 3) {

                                goodsA.setVisibility(View.VISIBLE);
                                centerPic.setVisibility(View.VISIBLE);
                                goodsB.setVisibility(View.VISIBLE);
                                Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getEnvironment_imgs().get(0).getUrl());
                                Presenter.getInstance(getContext()).getImage(centerPic, dataBean.getEnvironment_imgs().get(1).getUrl());
                                Presenter.getInstance(getContext()).getImage(goodsB, dataBean.getEnvironment_imgs().get(2).getUrl());
                            }
                        }
                    }

                }
            }
        }
    };

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


    private void resetCommentEdittext() {
        PaoToastUtils.showShortToast(getActivity(), "评论成功");
        commentEditText.setText("");
        hideSoftInput(commentEditText.getWindowToken());
    }


    private void initEditView(final String evaluateid, final int userid, final Object object) {
        LocalLog.d(TAG, "initEditView() enter evaluateid " + evaluateid + "name =" + (String) object);
        if (commentEditText == null) {
            commentEditText = (CustomEdit) editStill.findViewById(R.id.content_text);
            ImageView buttonIcon = (ImageView) editStill.findViewById(R.id.edit_expression);
            LinearLayout mLlContent = (LinearLayout) editStill.findViewById(R.id.edit_content);
            EmotionLayout mElEmotion = (EmotionLayout) editStill.findViewById(R.id.elEmotion);
            mEmotionKeyboard = EmotionKeyboard.with(getActivity());
            mEmotionKeyboard.bindToContent(mLlContent);
            mEmotionKeyboard.bindToEmotionButton(buttonIcon);
            mEmotionKeyboard.bindToEditText(commentEditText);
            mEmotionKeyboard.setEmotionLayout(mElEmotion);

            mElEmotion.attachEditText(commentEditText);
            mElEmotion.setEmotionAddVisiable(false);
            mElEmotion.setEmotionSettingVisiable(false);
            mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
                @Override
                public void onEmotionAddClick(View view) {
                    Toast.makeText(getContext(), "add", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEmotionSettingClick(View view) {
                    Toast.makeText(getContext(), "setting", Toast.LENGTH_SHORT).show();
                }
            });


            mElEmotion.setEmotionSelectedListener(new IEmotionSelectedListener() {
                @Override
                public void onEmojiSelected(String key) {
                    LocalLog.d(TAG, "onEmojiSelected() " + key);
                }

                @Override
                public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
                    String stickerPath = stickerBitmapPath;
                    Toast.makeText(getContext(), stickerPath, Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (object == null && evaluateid == null) {
            commentEditText.setHint("输入评论");
        } else {
            LocalLog.d(TAG, "回复用户!");
            commentEditText.setHint("回复:" + (String) object);
        }

        Button button = (Button) editStill.findViewById(R.id.send_content);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (userid == Presenter.getInstance(getContext()).getId()) {
                    PaoToastUtils.showLongToast(getContext(), "不能评论自己");
                    return;
                }
                view.setEnabled(false);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (view != null) {
                            view.setEnabled(true);
                        }
                    }
                }, 2000);
                String content = commentEditText.getText().toString();
                LocalLog.d(TAG, "content = " + content);
                int[] emj = getContext().getResources().getIntArray(R.array.emjio_list);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace(Utils.getEmojiStringByUnicode(emj[i]), "[0x" + numToHex8(emj[i]) + "]");
                    }
                }

                if (!TextUtils.isEmpty(content)) {
                    saveContent(evaluateid, content, object);
                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.collect_sponsor, R.id.like_sponsor_span, R.id.sponsor_content_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_sponsor:
                LocalLog.d(TAG, "收藏或者取消收藏");
                collectSponsor.setEnabled(false);
                checkCollectState(true);
                collectSponsor.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (collectSponsor != null)
                            collectSponsor.setEnabled(true);
                    }
                }, 2000);
                break;
            case R.id.like_sponsor_span:
                LocalLog.d(TAG, "点赞或者取消点赞");
                likeSponsorSpan.setEnabled(false);
                likeSponsorSpan.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (likeSponsorSpan != null) {
                            likeSponsorSpan.setEnabled(true);
                        }
                    }
                }, 2000);
                if (is_vote) {
                    voteSponsor(2);
                } else {
                    voteSponsor(1);
                }
                break;
            case R.id.sponsor_content_span:
                LocalLog.d(TAG, "评论");
                initEditView(null, -1, null);
                break;
            default:
                break;
        }
    }

    //添加收藏
    private void collectSponsor() {
        if (bussinessId <= 0) {
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("type", "1");
        param.put("businessid", String.valueOf(bussinessId));
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlCollectSponsor, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    LocalLog.d(TAG, "添加收藏成功");
                    getActivity().setResult(Activity.RESULT_OK);
                    keppSponsorIcon.setImageResource(R.drawable.had_collect);
                    PaoToastUtils.showLongToast(getContext(), "店铺收藏成功");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    //取消收藏
    private void deleteCollect(final String collectionId) {
        String url = NetApi.urlDeleteCollect + collectionId;
        Presenter.getInstance(getContext()).deletePaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    LocalLog.d(TAG, "取消收藏成功");
                    getActivity().setResult(Activity.RESULT_OK);
                    keppSponsorIcon.setImageResource(R.drawable.no_collect);
                    PaoToastUtils.showLongToast(getContext(), "取消收藏成功");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

}
