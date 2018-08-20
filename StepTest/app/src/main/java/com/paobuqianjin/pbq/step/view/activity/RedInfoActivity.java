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
import android.widget.Toast;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorCommentResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorContentAdapter;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.paobuqianjin.pbq.step.view.emoji.EmotionKeyboard;
import com.paobuqianjin.pbq.step.view.emoji.EmotionLayout;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionExtClickListener;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/8/17.
 */

public class RedInfoActivity extends BaseBarActivity {
    private final static String TAG = RedInfoActivity.class.getSimpleName();
    @Bind(R.id.user_head)
    CircleImageView userHead;
    @Bind(R.id.pkg_money)
    TextView pkgMoney;
    @Bind(R.id.op_des)
    TextView opDes;
    @Bind(R.id.recv_person)
    TextView recvPerson;
    @Bind(R.id.head_recycler)
    RecyclerView headRecycler;
    @Bind(R.id.sponsor_describe)
    TextView sponsorDescribe;
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.pic_sample)
    ImageView picSample;
    @Bind(R.id.current_pic)
    TextView currentPic;
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
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    String red_id = "";
    ArrayList<RoundHisResponse.DataBean.BusinessImgBean> goodsImgsBeans = new ArrayList<>();
    String result_str = "";
    ArrayList<RoundHisResponse.DataBean.ReceiverListBean> arrayRecList = new ArrayList<>();
    List<View> Mview = new ArrayList<>();
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
    ArrayList<RoundHisResponse.DataBean.CommentListBean> arrayList = new ArrayList<>();
    SponsorContentAdapter sponsorContentAdapter;
    LinearLayoutManager layoutManager;
    boolean is_vote = false;
    private int localVoteNum = 0;
    private int localCommentNum = 0;
    CustomEdit commentEditText;
    private EmotionKeyboard mEmotionKeyboard;
    LinearLayout editStill;
    ImageView goMore;

    @Override
    protected String title() {
        return "红包信息";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_info_styletw_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        userHead = (CircleImageView) findViewById(R.id.user_head);
        pkgMoney = (TextView) findViewById(R.id.pkg_money);
        opDes = (TextView) findViewById(R.id.op_des);
        headRecycler = (RecyclerView) findViewById(R.id.head_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRecycler.setLayoutManager(linearLayoutManager);
        headRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(30));
        sponsorDescribe = (TextView) findViewById(R.id.sponsor_describe);
        likeSponsorIcon = (ImageView) findViewById(R.id.like_sponsor_icon);
        likeSponsorDesc = (TextView) findViewById(R.id.like_sponsor_desc);
        sponsorContentNum = (TextView) findViewById(R.id.sponsor_content_num);
        likeSponsorSpan = (LinearLayout) findViewById(R.id.like_sponsor_span);
        Intent intent = getIntent();
        if (intent != null) {
            red_id = intent.getStringExtra(getPackageName() + "red_id");
            LocalLog.d(TAG, "red_id =" + red_id);
            if (!TextUtils.isEmpty(red_id)) {
                requestPostRedDetail(red_id);
            }
        }
        contentRecycler = (RecyclerView) findViewById(R.id.content_recycler);
        contentRecycler.setNestedScrollingEnabled(false);
        contentRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        contentRecycler.setLayoutManager(layoutManager);
        sponsorContentAdapter = new SponsorContentAdapter(this, arrayList);
        contentRecycler.setAdapter(sponsorContentAdapter);
        editStill = (LinearLayout) findViewById(R.id.linear_edit);
        goMore = (ImageView) findViewById(R.id.go_to);
        initEditView(null, -1, null);
        contentRecycler.addOnItemTouchListener(new RecyclerItemClickListener(RedInfoActivity.this, contentRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemLongClick(View view, int position) {

            }

            @Override
            public void OnItemClick(View view, final int position) {
                if (position < arrayList.size() && position >= 0) {
                    initEditView(String.valueOf(arrayList.get(position).getEid()), arrayList.get(position).getUserid(), arrayList.get(position).getNickname());
                }
            }
        }));
    }

    private void requestPostRedDetail(final String redId) {
        Map<String, String> param = new HashMap<>();
        param.put("red_id", redId);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedHisDetail, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    final RoundHisResponse roundHisResponse = new Gson().fromJson(s, RoundHisResponse.class);
                    Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(userHead, roundHisResponse.getData().getAvatar()
                            , R.drawable.default_head_ico, R.drawable.default_head_ico);
                    if (roundHisResponse.getData().getIs_me() == 1) {
                        LocalLog.d(TAG, "当前为发红包详情");
                        pkgMoney.setText("自己发的红包无法领取");
                    } else if (roundHisResponse.getData().getIs_me() == 0) {
                        LocalLog.d(TAG, "当前为领红包详情");
                        pkgMoney.setText(roundHisResponse.getData().getIncome_money());
                        opDes.setVisibility(View.VISIBLE);
                    }
                    if (roundHisResponse.getData().getIs_zan() == 1) {
                        is_vote = true;

                    } else {
                        is_vote = false;
                    }

                    if (roundHisResponse.getData().getReceiver_list() != null) {
                        int size = roundHisResponse.getData().getReceiver_list().size();
                        for (int i = 0; i < size; i++) {
                            if (i < 5) {
                                arrayRecList.add(roundHisResponse.getData().getReceiver_list().get(i));
                            } else {
                                break;
                            }
                        }
                        if (arrayRecList.size() > 0) {
                            LocalLog.d(TAG, "#########");
                            LikeUserAdapter likeUserAdapter = new LikeUserAdapter(RedInfoActivity.this, arrayRecList);
                            headRecycler.setAdapter(likeUserAdapter);
                            goMore.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setClass(RedInfoActivity.this, RoundRedRelActivity.class);
                                    intent.putExtra(getPackageName() + "red_id", redId);
                                    startActivity(intent);
                                }
                            });
                        }
                        sponsorDescribe.setText(roundHisResponse.getData().getContent());

                        //红包活动图片
                        if (roundHisResponse.getData().getRed_img() != null) {
                            int imgSize = roundHisResponse.getData().getRed_img().size();
                            for (int j = 0; j < imgSize; j++) {
                                View view = LayoutInflater.from(RedInfoActivity.this)
                                        .inflate(R.layout.red_iamge_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.red_content_img);
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageView, roundHisResponse.getData().getRed_img()
                                        .get(j).getUrl(), R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Mview.add(view);
                            }
                        }
                        final String tarUrl = roundHisResponse.getData().getTarget_url();
                        if (Mview.size() > 0) {
                            currentPic.setText(String.valueOf(1) + "/" + Mview.size());
                            sponsorImages.setAdapter(new ImageViewPagerAdapter(RedInfoActivity.this, Mview));
                            sponsorImages.addOnPageChangeListener(onPageChangeListener);
                            sponsorImages.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!TextUtils.isEmpty(tarUrl)) {
                                        startActivity(new Intent(RedInfoActivity.this, SingleWebViewActivity.class).putExtra("url", tarUrl));
                                    }
                                }
                            });
                        }

                        //环境照
                        if (roundHisResponse.getData().getBusiness_img() != null) {
                            size = roundHisResponse.getData().getBusiness_img().size();
                            LocalLog.d(TAG, "size = " + size);
                            goodsImgsBeans.addAll(roundHisResponse.getData().getBusiness_img());
                            if (size == 1) {
                                imageA.setVisibility(View.VISIBLE);
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageA, roundHisResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                            } else if (size == 2) {
                                imageA.setVisibility(View.VISIBLE);
                                imageB.setVisibility(View.VISIBLE);
                                Presenter.getInstance(RedInfoActivity.this).getImage(imageA, roundHisResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RedInfoActivity.this).getImage(imageB, roundHisResponse.getData().getBusiness_img().get(1).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                            } else if (size >= 3) {
                                imageA.setVisibility(View.VISIBLE);
                                imageC.setVisibility(View.VISIBLE);
                                imageB.setVisibility(View.VISIBLE);
                                Presenter.getInstance(RedInfoActivity.this).getImage(imageA, roundHisResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RedInfoActivity.this).getImage(imageB, roundHisResponse.getData().getBusiness_img().get(1).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RedInfoActivity.this).getImage(imageC, roundHisResponse.getData().getBusiness_img().get(2).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                            }
                        }

                        //点赞和评论
                        if (roundHisResponse.getData().getIs_zan() == 0) {
                            likeSponsorIcon.setImageResource(R.drawable.un_like);
                        } else {
                            likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                        }

                        if (localVoteNum > 0) {
                            likeSponsorDesc.setText("");
                        } else {
                            likeSponsorDesc.setText("0");
                        }
                        localCommentNum = roundHisResponse.getData().getComment_count();
                        if (localCommentNum > 0) {
                            sponsorContentNum.setText(String.valueOf(roundHisResponse.getData().getComment_count()));
                        } else {
                            sponsorContentNum.setText("0");
                        }
                        //评论列表
                        if (roundHisResponse.getData().getComment_list() == null) {
                            return;
                        }

                        arrayList.addAll(roundHisResponse.getData().getComment_list());
                        sponsorContentAdapter.notifyDataSetChanged();

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

    private void initEditView(final String evaluateid, final int userid, final Object object) {
        LocalLog.d(TAG, "initEditView() enter evaluateid " + evaluateid + "name =" + (String) object);
        if (commentEditText == null) {
            commentEditText = (CustomEdit) editStill.findViewById(R.id.content_text);
            ImageView buttonIcon = (ImageView) editStill.findViewById(R.id.edit_expression);
            LinearLayout mLlContent = (LinearLayout) editStill.findViewById(R.id.edit_content);
            EmotionLayout mElEmotion = (EmotionLayout) editStill.findViewById(R.id.elEmotion);
            mEmotionKeyboard = EmotionKeyboard.with(RedInfoActivity.this);
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
                    Toast.makeText(RedInfoActivity.this, "add", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEmotionSettingClick(View view) {
                    Toast.makeText(RedInfoActivity.this, "setting", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RedInfoActivity.this, stickerPath, Toast.LENGTH_SHORT).show();
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
                if (userid == Presenter.getInstance(RedInfoActivity.this).getId()) {
                    PaoToastUtils.showLongToast(RedInfoActivity.this, "不能评论自己");
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
                int[] emj = getResources().getIntArray(R.array.emjio_list);
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

    private void resetCommentEdittext() {
        PaoToastUtils.showShortToast(RedInfoActivity.this, "评论成功");
        commentEditText.setText("");
        hideSoftInput(commentEditText.getWindowToken());
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
        param.put("type", "4");
        param.put("red_map_id", red_id);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlSaveContent, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                resetCommentEdittext();
                /*插入评论*/
                LocalLog.d(TAG, "s = " + s);
                localCommentNum++;
                sponsorContentNum.setText(String.valueOf(localCommentNum));
                if (object == null && evaluateid == null) {
                    //插入一层评论
                    RoundHisResponse.DataBean.CommentListBean commentarrayBean = new RoundHisResponse.DataBean.CommentListBean();
                    commentarrayBean.setAvatar(Presenter.getInstance(RedInfoActivity.this).getAvatar(RedInfoActivity.this));
                    commentarrayBean.setNickname(Presenter.getInstance(RedInfoActivity.this).getNickName(RedInfoActivity.this));
                    commentarrayBean.setUserid(Presenter.getInstance(RedInfoActivity.this).getId());
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
                    RoundHisResponse.DataBean.CommentListBean commentarrayBean = new RoundHisResponse.DataBean.CommentListBean();
                    commentarrayBean.setAvatar(Presenter.getInstance(RedInfoActivity.this).getAvatar(RedInfoActivity.this));
                    commentarrayBean.setNickname(Presenter.getInstance(RedInfoActivity.this).getNickName(RedInfoActivity.this));
                    commentarrayBean.setContent(content);
                    commentarrayBean.setFather_nickname((String) object);
                    commentarrayBean.setUserid(Presenter.getInstance(RedInfoActivity.this).getId());
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
                    PaoToastUtils.showLongToast(RedInfoActivity.this, errorBean.getMessage());
                }
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


    /*点赞*/
    private void voteSponsor(final int status) {
        Map<String, String> param = new HashMap<>();
        param.put("status", String.valueOf(status));
        param.put("type", "4");
        param.put("red_map_id", red_id);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlVoteSponsor, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
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

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });


    }

    @OnClick({R.id.like_sponsor_span, R.id.sponsor_content_span})
    public void onClick(View view) {
        switch (view.getId()) {
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
        }
    }
}
