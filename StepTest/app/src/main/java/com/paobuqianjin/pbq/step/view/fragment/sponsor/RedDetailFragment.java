package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.BigImageView;
import com.paobuqianjin.pbq.step.customview.ImageViewPager;
import com.paobuqianjin.pbq.step.data.bean.bundle.GoodImageData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByRedResponse;
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
import com.paobuqianjin.pbq.step.view.activity.RoundRedRelActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorGoodsPicLookActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorContentAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.paobuqianjin.pbq.step.view.emoji.EmotionKeyboard;
import com.paobuqianjin.pbq.step.view.emoji.EmotionLayout;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionExtClickListener;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/9/10.
 */
/*
@className :RedDetailFragment
*@date 2018/9/10
*@author
*@description 附近红包是对商家进行评论
*/
public class RedDetailFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = RedDetailFragment.class.getSimpleName();
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
    @Bind(R.id.recv_person)
    TextView recvPerson;
    @Bind(R.id.head_recycler)
    RecyclerView headRecycler;
    @Bind(R.id.go_to_details)
    LinearLayout goToDetails;
    @Bind(R.id.list_reds)
    LinearLayout listReds;
    @Bind(R.id.red_info)
    TextView redInfo;
    @Bind(R.id.red_images)
    ViewPager redImages;
    @Bind(R.id.pic_red_sample)
    ImageView picRedSample;
    @Bind(R.id.current_red_pic)
    TextView currentRedPic;
    @Bind(R.id.red_images_span)
    FrameLayout redImagesSpan;
    @Bind(R.id.sponsor_sample)
    LinearLayout sponsorSample;
    @Bind(R.id.sponsor_pic_line)
    ImageView sponsorPicLine;
    @Bind(R.id.kepp_sponsor_desc)
    TextView keppSponsorDesc;
    @Bind(R.id.sponsor_content)
    ImageView sponsorContent;
    @Bind(R.id.sponsor_opreation)
    LinearLayout sponsorOpreation;
    @Bind(R.id.new_content)
    TextView newContent;
    @Bind(R.id.line_content)
    ImageView lineContent;
    @Bind(R.id.v_empty)
    View vEmpty;
    @Bind(R.id.pic_index)
    RelativeLayout picIndex;
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
    LinearLayoutManager layoutManager, layoutManagerRec;
    private boolean isLoading = false;
    private TranslateAnimation animationCircleType;
    private View selectMapView;
    private PopupWindow selectMapWindow;
    List<View> mRedView = new ArrayList<>();
    List<String> mapList = new ArrayList<>();
    ArrayList<NearByRedResponse.DataBean.ReceiverListBean> arrayRecList = new ArrayList<>();
    private int currentImage = 0;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private int mScreenWidth;
    private int mScreenHeight;
    private String info;
    private String red_id;
    RelativeLayout barNull;


    @Override
    protected String title() {
        return "商家详情";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.red_detail_fg;
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
        mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
        mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
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
        collectSponsor = (LinearLayout) viewRoot.findViewById(R.id.collect_sponsor);
        listReds = (LinearLayout) viewRoot.findViewById(R.id.list_reds);
        sponsorScroll = (BounceScrollView) viewRoot.findViewById(R.id.sponsor_scroll);
        barNull = (RelativeLayout) viewRoot.findViewById(R.id.sponsor_detail);
        picIndex = (RelativeLayout) viewRoot.findViewById(R.id.pic_index);
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
            int red_id = intent.getIntExtra(getContext().getPackageName() + "red_id", -1);
            info = intent.getStringExtra(getContext().getPackageName() + "info");
            LocalLog.d(TAG, "businessid =  " + businessid + "red_id = " + red_id + "info =" + info);
            if (businessid != -1) {
                this.bussinessId = businessid;
                if (red_id != -1) {
                    this.red_id = String.valueOf(red_id);
                    setTitle("红包详情");
                    redResultStr = intent.getStringExtra(getContext().getPackageName() + "red_result");
                    LocalLog.d(TAG, "redResultStr = " + redResultStr);
                    getRedDetail(String.valueOf(red_id));
                    collectSponsor.setVisibility(View.GONE);
                    loadContent(businessid);
  /*                  sponsorScroll.setScrollListener(new BounceScrollView.ScrollListener() {
                        @Override
                        public void scrollOritention(int l, int t, int oldl, int oldt) {
                            LocalLog.d(TAG, "l =  " + l + ",t = " + t + ",oldl= " + oldl + "," + oldt);
                            if (Utils.px2dip(getContext(), (float) t) > 64) {
                                barNull.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_232433));
                            } else {
                                barNull.setBackground(null);
                            }
                        }
                    });*/
                } else {
                    redResultStr = intent.getStringExtra(getContext().getPackageName() + "red_result");
                    LocalLog.d(TAG, "redResultStr = " + redResultStr);
                    Presenter.getInstance(getContext()).businessDetail(businessid, sponsorInnerCallBack);
                    checkCollectState(false);
                    loadContent(businessid);
                }

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
        layoutManagerRec = new LinearLayoutManager(getContext());
        headRecycler = (RecyclerView) viewRoot.findViewById(R.id.head_recycler);
        layoutManagerRec.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(30));
        headRecycler.setLayoutManager(layoutManagerRec);
        contentRecycler.setLayoutManager(layoutManager);
        sponsorContentAdapter = new SponsorContentAdapter(getContext(), arrayList);
        contentRecycler.setAdapter(sponsorContentAdapter);

        redInfo = (TextView) viewRoot.findViewById(R.id.red_info);

        redImagesSpan = (FrameLayout) getActivity().findViewById(R.id.red_images_span);
        redImages = (ViewPager) viewRoot.findViewById(R.id.red_images);
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

        if (Utils.isHaveBaiduMap()) {
            mapList.add("百度");
            LocalLog.d(TAG, "百度");
        }

        if (Utils.isHaveGaodeMap()) {
            mapList.add("高德");
            LocalLog.d(TAG, "高德");
        }

        if (Utils.isHaveTencentMap()) {
            mapList.add("腾讯");
            LocalLog.d(TAG, "腾讯");
        }

    }


    private void getRedDetail(String red_id) {
        Map<String, String> param = new HashMap<>();
        param.put("red_id", red_id);
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlNearRedPkg, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    NearByRedResponse nearByRedResponse = new Gson().fromJson(s, NearByRedResponse.class);
                    final NearByRedResponse.DataBean dataBean = nearByRedResponse.getData();
                    redResultStr = nearByRedResponse.getData().getIncome_money();
                    if (dataBean != null) {
                        LocalLog.d(TAG, "dataBean  =  " + dataBean.toString());
                        if (sponsorTelNumStr == null) {
                            return;
                        }
                        sponsorName.setText(dataBean.getName());
                        sponsorTelNumStr.setText(dataBean.getTel());
                        sponsorTelNumStr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                intent1.setData(Uri.parse("tel:" + dataBean.getTel()));
                                startActivity(intent1);
                            }
                        });
                        String locationDetail = "";
                        if (!TextUtils.isEmpty(dataBean.getAddra())) {
                            locationDetail = dataBean.getAddra();
                        }
                        if (!TextUtils.isEmpty(dataBean.getAddress())) {
                            if (!TextUtils.isEmpty(locationDetail)) {
                                locationDetail += dataBean.getAddress();
                            } else {
                                locationDetail = dataBean.getAddress();
                            }
                        }
                        sponsorTimeNumStr.setText(locationDetail.replace("/", ""));
                        locationStr.setText(dataBean.getScope());
                        sponsorTimeNumStr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "点击地址。。。");
                                if (!TextUtils.isEmpty(dataBean.getAddra())) {
                                    if (mapList.size() > 0) {
                                        selectMap(mapList, null, dataBean.getAddra(), dataBean.getLatitude(), dataBean.getLongitude());
                                    }
                                }
                            }
                        });

                        int sizeRev = dataBean.getReceiver_list().size();
                        for (int i = 0; i < sizeRev; i++) {
                            if (i < 5) {
                                arrayRecList.add(dataBean.getReceiver_list().get(i));
                            } else {
                                break;
                            }
                        }

                        if (arrayRecList.size() > 0) {
                            listReds.setVisibility(View.VISIBLE);
                            LikeUserAdapter likeUserAdapter = new LikeUserAdapter(getContext(), arrayRecList);
                            headRecycler.setAdapter(likeUserAdapter);
                        }

                        if (!TextUtils.isEmpty(dataBean.getRed_content())) {
                            redInfo.setText(dataBean.getRed_content());
                            redInfo.setVisibility(View.VISIBLE);
                        } else {
                            redInfo.setVisibility(View.GONE);
                        }


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

                        int sizeEnv = 0;
                        final String tarUrl = dataBean.getTarget_url();
                        if (dataBean.getRed_img() != null) {
                            sizeEnv = dataBean.getRed_img().size();
                            redImagesSpan.setVisibility(View.VISIBLE);
                            for (int i = 0; i < sizeEnv; i++) {
                                View view = LayoutInflater.from(getContext()).inflate(R.layout.sponsor_image_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.sponsor_env_img);
                                Presenter.getInstance(getContext()).getImage(imageView, dataBean.getRed_img().get(i));
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!TextUtils.isEmpty(tarUrl)) {
                                            startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", tarUrl));
                                        } else {
                                            LocalLog.d(TAG, "查看大图 currentImage = " + currentImage);
                                            if (popBigImageInterface != null && dataBean.getRed_img().size() >= 1) {
                                                popBigImageInterface.popImageView(dataBean.getRed_img(), currentImage);
                                            }
                                        }
                                    }
                                });
                                mRedView.add(view);
                            }
                        }
                        if (Mview.size() > 0) {
                            sponsorImages.setAdapter(new ImageViewPagerAdapter(getContext(), Mview));
                        }
                        if (mRedView.size() > 0) {
                            if (mRedView.size() == 1) {
                                picIndex.setVisibility(View.GONE);
                            } else {
                                currentPic.setText(String.valueOf(1) + "/" + mRedView.size());
                            }
                            redImages.setAdapter(new ImageViewPagerAdapter(getContext(), mRedView));
                            redImages.addOnPageChangeListener(onPageChangeListener);
                        }
                        int size = 0;
                        if (dataBean.getBusiness_img() != null) {
                            size = dataBean.getBusiness_img().size();
                            for (int i = 0; i < size; i++) {
                                SponsorDetailResponse.DataBean.EnvironmentImgsBean environmentImgsBean = new SponsorDetailResponse.DataBean.EnvironmentImgsBean();
                                environmentImgsBean.setUrl(dataBean.getBusiness_img().get(i));
                                goodsImgsBeans.add(environmentImgsBean);
                            }

                            if (size == 1) {
                                goodsA.setVisibility(View.VISIBLE);
                                Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getBusiness_img().get(0));
                            } else if (size == 2) {
                                goodsA.setVisibility(View.VISIBLE);
                                centerPic.setVisibility(View.VISIBLE);
                                Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getBusiness_img().get(0));
                                Presenter.getInstance(getContext()).getImage(centerPic, dataBean.getBusiness_img().get(1));
                            } else if (size >= 3) {

                                goodsA.setVisibility(View.VISIBLE);
                                centerPic.setVisibility(View.VISIBLE);
                                goodsB.setVisibility(View.VISIBLE);
                                Presenter.getInstance(getContext()).getImage(goodsA, dataBean.getBusiness_img().get(0));
                                Presenter.getInstance(getContext()).getImage(centerPic, dataBean.getBusiness_img().get(1));
                                Presenter.getInstance(getContext()).getImage(goodsB, dataBean.getBusiness_img().get(2));
                            }
                        }

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

    private void selectMap(List<String> strings, final String dqAddress, final String gotoAddress,
                           final String gotoLatitude, final String gotoLongitude) {
        selectMapView = View.inflate(getContext(), R.layout.map_source_pop_select, null);
        selectMapWindow = new PopupWindow(selectMapView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        selectMapWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "selectMapWindow onDismiss() enter");
                selectMapWindow = null;
            }
        });

        selectMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMapWindow.dismiss();
            }
        });
        if (strings.contains("腾讯")) {
            TextView tencent = (TextView) selectMapView.findViewById(R.id.tencent_maps);
            tencent.setVisibility(View.VISIBLE);
            tencent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openTencentMap(getContext(), null, gotoAddress, gotoLatitude, gotoLongitude);
                }
            });
        }
        if (strings.contains("百度")) {
            TextView baidu = (TextView) selectMapView.findViewById(R.id.baidu_map);
            baidu.setVisibility(View.VISIBLE);
            baidu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //定位源来源于百度，目标源来自腾讯，需要转换，
                    selectMapWindow.dismiss();
                    Utils.openBaiduMap(getContext(), dqAddress, gotoAddress, String.valueOf(Presenter.getInstance(getContext()).getLocation()[0]),
                            String.valueOf(Presenter.getInstance(getContext()).getLocation()[1]), gotoLatitude, gotoLongitude);
                }
            });
        }
        if (strings.contains("高德")) {
            TextView gaode = (TextView) selectMapView.findViewById(R.id.gaode_map);
            gaode.setVisibility(View.VISIBLE);
            gaode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openGaoDeMap(getContext(), dqAddress, gotoAddress, gotoLatitude, gotoLongitude);
                }
            });
        }
        selectMapWindow.setFocusable(true);
        selectMapWindow.setOutsideTouchable(true);
        selectMapWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        selectMapWindow.showAtLocation(getActivity().findViewById(R.id.red_detail_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        selectMapView.startAnimation(animationCircleType);
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
                    final SponsorDetailResponse.DataBean dataBean = ((SponsorDetailResponse) object).getData();
                    if (dataBean != null) {
                        LocalLog.d(TAG, "dataBean  =  " + dataBean.toString());
                        if (sponsorTelNumStr == null) {
                            return;
                        }
                        sponsorName.setText(dataBean.getName());
                        sponsorTelNumStr.setText(dataBean.getTel());
                        sponsorTelNumStr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                intent1.setData(Uri.parse("tel:" + dataBean.getTel()));
                                startActivity(intent1);
                            }
                        });
                        /*String workTimeStr = dataBean.getDo_day();
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
                        }*/
                        String locationDetail = "";
                        if (!TextUtils.isEmpty(dataBean.getAddra())) {
                            locationDetail = dataBean.getAddra();
                        }
                        if (!TextUtils.isEmpty(dataBean.getAddress())) {
                            if (!TextUtils.isEmpty(locationDetail)) {
                                locationDetail += dataBean.getAddress();
                            } else {
                                locationDetail = dataBean.getAddress();
                            }
                        }
                        sponsorTimeNumStr.setText(locationDetail.replace("/", ""));
                        locationStr.setText(dataBean.getScope());
                        sponsorTimeNumStr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "点击地址。。。");
                                if (!TextUtils.isEmpty(dataBean.getAddra())) {
                                    if (mapList.size() > 0) {
                                        selectMap(mapList, null, dataBean.getAddra(), dataBean.getLatitude(), dataBean.getLongitude());
                                    }
                                }
                            }
                        });

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
                            /*sponsorImages.addOnPageChangeListener(onPageChangeListener);*/
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
            currentImage = position;
            currentPic.setText(String.valueOf(position + 1) + "/" + mRedView.size());
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
        if (popupSelectWindow != null) {
            popupSelectWindow.dismiss();
            popupSelectWindow = null;
        }
    }

    private void onRedInfo() {
        if (!TextUtils.isEmpty(red_id))
            redRelInfo(red_id);
    }

    private void redRelInfo(final String redId) {
        Intent intent = new Intent();
        intent.setClass(getContext(), RoundRedRelActivity.class);
        intent.putExtra(getContext().getPackageName() + "red_id", redId);
        intent.putExtra(getContext().getPackageName() + "near", "near");
        startActivity(intent);
    }

    @OnClick({R.id.collect_sponsor, R.id.like_sponsor_span, R.id.sponsor_content_span, R.id.list_reds, R.id.head_recycler, R.id.go_to_details})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_reds:
            case R.id.head_recycler:
            case R.id.go_to_details:
                LocalLog.d(TAG, "详情");
                onRedInfo();
                break;
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

    public interface PopBigImageInterface {
        public void popImageView(String url);

        public void popImageView(List<String> images, int index);
    }


    private PopBigImageInterface popBigImageInterface = new PopBigImageInterface() {
        @Override
        public void popImageView(String url) {
            if (!isAdded()) {
                return;
            }
            LocalLog.d(TAG, "查看大图");
            popBirthSelectView = View.inflate(getActivity(), R.layout.image_big_view, null);
            PhotoView photoView = (PhotoView) popBirthSelectView.findViewById(R.id.photo_view);
            ImageDataModel.getInstance().getDisplayer().display(getActivity(), url, photoView, mScreenWidth, mScreenHeight);
            popupSelectWindow = new PopupWindow(popBirthSelectView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popImageVie dismiss() ");
                    popupSelectWindow = null;
                }
            });

            popupSelectWindow.setFocusable(true);
            popupSelectWindow.setOutsideTouchable(true);
            popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.red_detail_fg), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }

        public void popImageView(List<String> images, int index) {
            if (!isAdded()) {
                return;
            }
            if (images == null) {
                return;
            }
            LocalLog.d(TAG, "查看大图 index = " + index);
            popBirthSelectView = View.inflate(getActivity(), R.layout.big_image_view_pager, null);
            ImageViewPager bigImageViewPager = (ImageViewPager) popBirthSelectView.findViewById(R.id.big_image_viewpager);
            List<View> bigImageViews = new ArrayList<>();
            for (String url : images) {
                BigImageView bigImageView = new BigImageView(getActivity());
                bigImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                bigImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ImageDataModel.getInstance().getDisplayer().display(getActivity(), url, bigImageView, mScreenWidth, mScreenHeight);
                bigImageViews.add(bigImageView);
            }
            ImageViewPagerAdapter pagerAdapter = new ImageViewPagerAdapter(getActivity(), bigImageViews);
            bigImageViewPager.setAdapter(pagerAdapter);
            bigImageViewPager.setCurrentItem(index, false);
            popupSelectWindow = new PopupWindow(popBirthSelectView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popImageVie dismiss() ");
                    popupSelectWindow = null;
                }
            });

            popupSelectWindow.setFocusable(true);
            popupSelectWindow.setOutsideTouchable(true);
            popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.red_detail_fg), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }
    };

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
