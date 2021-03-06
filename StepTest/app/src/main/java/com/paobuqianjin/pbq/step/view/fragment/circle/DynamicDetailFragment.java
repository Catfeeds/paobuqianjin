package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.ImageViewPager;
import com.paobuqianjin.pbq.step.customview.BigImageView;
import com.paobuqianjin.pbq.step.data.bean.bundle.LikeBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicAllIndexResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostDynamicContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.FriendDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.LikeSupportActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.TopLevelContentAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;
import com.paobuqianjin.pbq.step.view.emoji.EmotionKeyboard;
import com.paobuqianjin.pbq.step.view.emoji.EmotionLayout;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionExtClickListener;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionSelectedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2017/12/29.
 */

public class DynamicDetailFragment extends BaseBarStyleTextViewFragment implements DynamicDetailInterface {
    private final static String TAG = DynamicDetailFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.dynamic_user_icon)
    CircleImageView dynamicUserIcon;
    @Bind(R.id.dynamic_user_name)
    TextView dynamicUserName;
    @Bind(R.id.dynamic_owner_rel)
    RelativeLayout dynamicOwnerRel;
    @Bind(R.id.dynamic_content_text)
    TextView dynamicContentText;
    @Bind(R.id.pic_content_rel)
    RelativeLayout picContentRel;
    @Bind(R.id.image_viewpager)
    ViewPager imageViewpager;
    @Bind(R.id.dot_1)
    View dot1;
    @Bind(R.id.dot_2)
    View dot2;
    @Bind(R.id.dot_3)
    View dot3;
    @Bind(R.id.dot_4)
    View dot4;
    @Bind(R.id.dot_line)
    LinearLayout dotLine;
    @Bind(R.id.pic_viewpager)
    RelativeLayout picViewpager;
    @Bind(R.id.dynamic_location_city)
    TextView dynamicLocationCity;
    @Bind(R.id.content_numbers)
    TextView contentNumbers;
    @Bind(R.id.content_number_icon)
    ImageView contentNumberIcon;
    @Bind(R.id.content_supports)
    TextView contentSupports;
    @Bind(R.id.like_num_icon)
    ImageView likeNumIcon;
    @Bind(R.id.location_support_rel)
    RelativeLayout locationSupportRel;
    @Bind(R.id.line_content_list)
    ImageView lineContentList;
    @Bind(R.id.src_dynamic_content)
    RelativeLayout srcDynamicContent;
    @Bind(R.id.support_peoples)
    TextView supportPeoples;
    @Bind(R.id.support_icon_recycler)
    RecyclerView supportIconRecycler;
    @Bind(R.id.share_icon)
    ImageView shareIcon;
    @Bind(R.id.support_pics)
    RelativeLayout supportPics;
    @Bind(R.id.support_rel)
    RelativeLayout supportRel;
    @Bind(R.id.line_like_content)
    ImageView lineLikeContent;
    @Bind(R.id.content_details_list_item)
    RecyclerView contentDetailsListItem;
    @Bind(R.id.content_details)
    RelativeLayout contentDetails;
    @Bind(R.id.dynamic_time)
    TextView dynamicTime;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
//    @Bind(R.id.dynamic_id_detail)
//    RelativeLayout dynamicIdDetail;
    private ArrayList<DynamicLikeListResponse.DataBeanX.DataBean> likeData = new ArrayList<>();
    private EmotionKeyboard mEmotionKeyboard;
    private List<View> Mview = new ArrayList<>();
    private List<View> dots;
    private int oldPosition;
    private int currentItem;
    private View imageView0, imageView1, imageView2, imageView3;
    ImageViewPagerAdapter adapter;
    private LayoutInflater inflater;
    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManagerContent;
    int dynamicid = -1;
    int userid = -1;
    int is_vote = 0;
    private View popRedPkgView;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;

    private int currentIndexPage = 1;
    private int likeNum = -1, contentNum = -1, sourceLikeNum = -1, sourceContentNum = -1;
    private ReflashInterface reflashInterface;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private int mScreenWidth;
    private int mScreenHeight;
    LikeUserAdapter likeUserAdapter = null;
    TopLevelContentAdapter topLevelContentAdapter = null;
    ArrayList<DynamicCommentListResponse.DataBeanX.DataBean> topContentData = new ArrayList<>();
    Intent intent = new Intent();
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 300;
    private int position = -1;
    private int topPosition = -1;

    private CustomEdit commentEditText;
    private LinearLayout linear_comment_root;

    @Override
    protected int getLayoutResId() {
        return R.layout.dynamic_detail_fg;
    }

    @Override
    protected String title() {
        return "动态详情";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
        mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
        dots = new ArrayList<View>();
        dot1 = viewRoot.findViewById(R.id.dot_1);
        dot2 = viewRoot.findViewById(R.id.dot_2);
        dot3 = viewRoot.findViewById(R.id.dot_3);
        dot4 = viewRoot.findViewById(R.id.dot_4);

        imageView0 = inflater.inflate(R.layout.image_view_pager, null);
        imageView1 = inflater.inflate(R.layout.image_view_pager, null);
        imageView2 = inflater.inflate(R.layout.image_view_pager, null);
        imageView3 = inflater.inflate(R.layout.image_view_pager, null);


        imageViewpager = (ViewPager) viewRoot.findViewById(R.id.image_viewpager);
        imageViewpager.addOnPageChangeListener(onPageChangeListener);

        adapter = new ImageViewPagerAdapter(getContext(), Mview);
        imageViewpager.setAdapter(adapter);

        supportIconRecycler = (RecyclerView) viewRoot.findViewById(R.id.support_icon_recycler);
        supportIconRecycler.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        supportIconRecycler.setLayoutManager(layoutManager);

        supportIconRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(10));
        shareIcon = (ImageView) viewRoot.findViewById(R.id.share_icon);
        shareIcon.setOnClickListener(onClickListener);

        contentDetailsListItem = (RecyclerView) viewRoot.findViewById(R.id.content_details_list_item);
        layoutManagerContent = new LinearLayoutManager(getContext());
        contentDetailsListItem.setLayoutManager(layoutManagerContent);
        contentDetailsListItem.setHasFixedSize(true);
        contentDetailsListItem.setNestedScrollingEnabled(false);


        dynamicUserIcon = (CircleImageView) viewRoot.findViewById(R.id.dynamic_user_icon);
        dynamicUserIcon.setOnClickListener(onClickListener);
        dynamicUserName = (TextView) viewRoot.findViewById(R.id.dynamic_user_name);
        dynamicContentText = (TextView) viewRoot.findViewById(R.id.dynamic_content_text);
        dynamicTime = (TextView) viewRoot.findViewById(R.id.dynamic_time);
        dynamicLocationCity = (TextView) viewRoot.findViewById(R.id.dynamic_location_city);
        contentNumbers = (TextView) viewRoot.findViewById(R.id.content_numbers);
        supportPeoples = (TextView) viewRoot.findViewById(R.id.support_peoples);
        likeNumIcon = (ImageView) viewRoot.findViewById(R.id.like_num_icon);
        likeNumIcon.setOnClickListener(onClickListener);
        contentSupports = (TextView) viewRoot.findViewById(R.id.content_supports);
        contentNumberIcon = (ImageView) viewRoot.findViewById(R.id.content_number_icon);
        contentNumberIcon.setOnClickListener(onClickListener);
        supportPics = (RelativeLayout) viewRoot.findViewById(R.id.support_pics);
        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        linear_comment_root = (LinearLayout) viewRoot.findViewById(R.id.linear_edit);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            dynamicid = intent.getIntExtra(getContext().getPackageName() + "dynamicId", -1);
            userid = intent.getIntExtra(getContext().getPackageName() + "userId", -1);
            is_vote = intent.getIntExtra(getContext().getPackageName() + "is_vote", 0);
            position = intent.getIntExtra(getContext().getPackageName() + "position", -1);
            LocalLog.d(TAG, "dynamicid= " + dynamicid + "userid = " + userid + "is_vote = " + is_vote + ",position =" + position);
            this.intent.putExtra(getContext().getPackageName() + "position", position);
            topPosition = intent.getIntExtra(getContext().getPackageName() + "topPosition", -1);
            this.intent.putExtra(getContext().getPackageName() + "topPosition", topPosition);
            if (dynamicid != -1) {
                Presenter.getInstance(getContext()).getDynamicCommentList(dynamicid, currentIndexPage, PAGESIZE);
                Presenter.getInstance(getContext()).getDynamicDetail(dynamicid);
                Presenter.getInstance(getContext()).getDynamicVoteList(dynamicid, userid, pageIndex, 10);
            }
        }

    }

    /**
     * 原来的popEdit
     * @param postDynamicContentParam
     * @param dearName
     */
    private void initEditView(final PostDynamicContentParam postDynamicContentParam, String dearName) {
        LocalLog.d(TAG, "initEditView() enter");
        if (commentEditText == null) {
            commentEditText = (CustomEdit) linear_comment_root.findViewById(R.id.content_text);
            ImageView buttonIcon = (ImageView) linear_comment_root.findViewById(R.id.edit_expression);
            LinearLayout mLlContent = (LinearLayout) linear_comment_root.findViewById(R.id.edit_content);
            EmotionLayout mElEmotion = (EmotionLayout) linear_comment_root.findViewById(R.id.elEmotion);
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

        if (dynamicUserName.getText().toString().equals(dearName)) {
            commentEditText.setHint("请输入评论" );
        }else{
            commentEditText.setHint("回复:" + dearName);
        }
        Button button = (Button) linear_comment_root.findViewById(R.id.send_content);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = commentEditText.getText().toString();
                LocalLog.d(TAG, "content = " + content);
                int[] emj = getContext().getResources().getIntArray(R.array.emjio_list);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace(Utils.getEmojiStringByUnicode(emj[i]), "[0x" + numToHex8(emj[i]) + "]");
                    }
                }

                if (content != null && !content.equals("")) {
                    postDynamicContentParam.setContent(content).setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).postContent(postDynamicContentParam);
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
            LocalLog.d(TAG, "onPageSelected");
            dots.get(oldPosition).setBackgroundResource(R.drawable.image_viewpager_dot_unselected);
            dots.get(position).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
            oldPosition = position;
            currentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ReflashInterface reflashTopInterface = new ReflashInterface() {
        @Override
        public void notifyReflash(Object object) {
            LocalLog.d(TAG, "更新顶层动态UI");
            if (object != null) {
                PostDynamicContentResponse postDynamicContentResponse = (PostDynamicContentResponse) object;
                LocalLog.d(TAG, postDynamicContentResponse.toString());
                DynamicCommentListResponse.DataBeanX.DataBean dataBean = new DynamicCommentListResponse.DataBeanX.DataBean();
                dataBean.setAvatar(postDynamicContentResponse.getData().getAvatar());
                dataBean.setContent(postDynamicContentResponse.getData().getContent());
                dataBean.setCreate_time(postDynamicContentResponse.getData().getCreate_time());
                dataBean.setDynamicid(Integer.parseInt(postDynamicContentResponse.getData().getDynamicid()));
                dataBean.setId(Integer.parseInt(postDynamicContentResponse.getData().getId()));
                dataBean.setUserid(Integer.parseInt(postDynamicContentResponse.getData().getUserid()));
                dataBean.setParent_id(Integer.parseInt(postDynamicContentResponse.getData().getParent_id()));
                dataBean.setReply_userid(Integer.parseInt(postDynamicContentResponse.getData().getReply_userid()));
                dataBean.setNickname(postDynamicContentResponse.getData().getNickname());

                if (topLevelContentAdapter == null) {
                    topContentData.add(dataBean);
                    topLevelContentAdapter = new TopLevelContentAdapter(getContext(), topContentData, DynamicDetailFragment.this);
                    contentDetailsListItem.setAdapter(topLevelContentAdapter);
                    DynamicAllIndexResponse.DataBeanX.DataBean.OneCommentBean oneCommentBean = new DynamicAllIndexResponse.DataBeanX.DataBean.OneCommentBean();
                    oneCommentBean.setNickname(postDynamicContentResponse.getData().getNickname());
                    oneCommentBean.setContent(postDynamicContentResponse.getData().getContent());
                    intent.putExtra(getContext().getPackageName() + "oneCommentBean", oneCommentBean);
                } else {
                    topContentData.add(topContentData.size(), dataBean);
                    topLevelContentAdapter.notifyItemInserted(topContentData.size());
                }
            }
        }
    };

    private void resetCommentEdittext() {
        PaoToastUtils.showShortToast(getActivity(),"评论成功");
        commentEditText.setText("");
        hideSoftInput(commentEditText.getWindowToken());
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalLog.d(TAG, "onClick() enter 查看点赞");
            switch (view.getId()) {
                case R.id.share_icon:
                    LikeBundleData likeBundleData = new LikeBundleData(likeData);
                    startActivity(LikeSupportActivity.class, likeBundleData);
                    break;
                case R.id.like_num_icon:
                    LocalLog.d(TAG, "点赞或者取消点赞");
                    PutVoteParam putVoteParam = new PutVoteParam();
                    putVoteParam.setDynamicid(dynamicid).setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).putVote(putVoteParam);
                    break;
                case R.id.dynamic_user_icon:
                    Intent intent = new Intent();
                    intent.putExtra("userid", userid);
                    intent.setClass(getActivity(), FriendDetailActivity.class);
                    startActivity(intent);
                    break;
                case R.id.content_number_icon:
                case R.id.dynamic_user_name:
                case R.id.dynamic_content_text:
                    LocalLog.d(TAG, "弹出发表评论输入框");
                    PostDynamicContentParam postDynamicContentParam = new PostDynamicContentParam();
                    postDynamicContentParam.
                            setDynamicid(dynamicid)
                            .setParent_id(0)
                            .setUserid(Presenter.getInstance(getContext())
                                    .getId()).setReply_userid(userid);
                    postDynamicAction(postDynamicContentParam, dynamicUserName.getText().toString(), reflashTopInterface);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imageViewpager.removeOnPageChangeListener(onPageChangeListener);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        ButterKnife.unbind(this);
        if (popupRedPkgWindow != null) {
            if (popupRedPkgWindow.isShowing()) {
                popupRedPkgWindow.dismiss();
                popupRedPkgWindow = null;
            }
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter " + errorCode.toString());
    }

    @Override
    public void response(DynamicCommentListResponse dynamicCommentListResponse) {
        LocalLog.d(TAG, "DynamicCommentListResponse() enter " + dynamicCommentListResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (dynamicCommentListResponse.getError() == 0) {
            pageCount = dynamicCommentListResponse.getData().getPagenation().getPage();
            if (contentDetailsListItem == null) {
                return;
            }
            if (topLevelContentAdapter == null) {
                topContentData.addAll(dynamicCommentListResponse.getData().getData());
                topLevelContentAdapter = new TopLevelContentAdapter(getContext(), topContentData, this);
            }
            contentDetailsListItem.setAdapter(topLevelContentAdapter);
        } else if (dynamicCommentListResponse.getError() == 1) {

        } else if (dynamicCommentListResponse.getError() == -1) {
            LocalLog.d(TAG, "");
        } else if (dynamicCommentListResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(DynamicIdDetailResponse dynamicIdDetailResponse) {
        if (!isAdded()) {
            return;
        }
        if (dynamicIdDetailResponse.getError() == 0) {
            LocalLog.d(TAG, "DynamicIdDetailResponse() enter " + dynamicIdDetailResponse.toString());
            if (dynamicUserIcon == null) {
                return;
            }
            Presenter.getInstance(getContext()).getPlaceErrorImage(dynamicUserIcon, dynamicIdDetailResponse.getData().getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
            dynamicUserName.setText(dynamicIdDetailResponse.getData().getNickname());
            long create_time = dynamicIdDetailResponse.getData().getCreate_time();

            /*if (dynamicIdDetailResponse.getData().getVip() == 1) {
                vipFlg.setVisibility(View.VISIBLE);
            }*/
            //服务器保存到秒级别，本地处理为毫秒级别
            LocalLog.d(TAG, "create_time = " + DateTimeUtil.formatDateTime(create_time * 1000));
            String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            dynamicTime.setText(create_timeStr);
            if (is_vote == 1) {
                likeNumIcon.setImageDrawable(getDrawableResource(R.drawable.fabulous_s));
            } else {
                likeNumIcon.setImageDrawable(getDrawableResource(R.drawable.fabulous_n));
            }
            int[] emj = getContext().getResources().getIntArray(R.array.emjio_list);
            String content = Base64Util.getUidFromBase64(dynamicIdDetailResponse.getData().getDynamic());

/*            if (content != null) {
                for (int i = 0; i < emj.length; i++) {
                    content = content.replace(Utils.getEmojiStringByUnicode(emj[i]), "[0x" + numToHex8(emj[i]) + "]");
                }
            }*/

            dynamicContentText.setText(content);
            dynamicLocationCity.setText(dynamicIdDetailResponse.getData().getShowAddress());
            likeNum = dynamicIdDetailResponse.getData().getVote();
            sourceLikeNum = likeNum;
            contentNum = dynamicIdDetailResponse.getData().getComment();
            contentNumbers.setText(String.valueOf(contentNum));
            contentSupports.setText(String.valueOf(likeNum));
            sourceContentNum = contentNum;

            int likeNums = dynamicIdDetailResponse.getData().getVote();
            if (likeNums == 0) {
                supportPeoples.setText("还没有人点赞");
                supportPics.setVisibility(View.GONE);
            } else {
                String likePeopleNumFormat = getContext().getString(R.string.like_people);
                String peopleNumStr = String.format(likePeopleNumFormat, likeNums);
                supportPeoples.setText(peopleNumStr);
            }
            int imageSize = dynamicIdDetailResponse.getData().getImages().size();
            LocalLog.d(TAG, "imageSize = " + imageSize);

            if (imageSize <= 0) {
                LocalLog.d(TAG, "动态没有图片");
                picViewpager.setVisibility(View.GONE);
            } else if (imageSize == 1) {
                if (dynamicIdDetailResponse.getData().getImages().get(0).equals("")) {
                    LocalLog.d(TAG, "动态没有图片");
                    picViewpager.setVisibility(View.GONE);
                } else {
                    imageView0 = LayoutInflater.from(getContext()).inflate(R.layout.image_view_pager, null);
                    dots.add(dot1);
                    Mview.add(imageView0);
                    dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                    ImageView imageViewA = (ImageView) imageView0.findViewById(R.id.dynamic_pic);
                    Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0)
                            , R.drawable.bitmap_null, R.drawable.bitmap_null);
                    showBigImage(imageViewA, dynamicIdDetailResponse.getData().getImages(), 0);
                }
            } else if (imageSize == 2) {
                dots.add(dot1);
                dots.add(dot2);

                dot1.setVisibility(View.VISIBLE);
                dot2.setVisibility(View.VISIBLE);

                Mview.add(imageView0);
                Mview.add(imageView1);

                ImageView imageViewA = (ImageView) imageView0.findViewById(R.id.dynamic_pic);
                ImageView imageViewB = (ImageView) imageView1.findViewById(R.id.dynamic_pic);
                dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewB, dynamicIdDetailResponse.getData().getImages().get(1), R.drawable.bitmap_null, R.drawable.bitmap_null);
                showBigImage(imageViewA, dynamicIdDetailResponse.getData().getImages(), 0);
                showBigImage(imageViewB, dynamicIdDetailResponse.getData().getImages(), 1);

            } else if (imageSize == 3) {
                dots.add(dot1);
                dots.add(dot2);
                dots.add(dot3);

                dot1.setVisibility(View.VISIBLE);
                dot2.setVisibility(View.VISIBLE);
                dot3.setVisibility(View.VISIBLE);

                Mview.add(imageView0);
                Mview.add(imageView1);
                Mview.add(imageView2);
                ImageView imageViewA = (ImageView) imageView0.findViewById(R.id.dynamic_pic);
                ImageView imageViewB = (ImageView) imageView1.findViewById(R.id.dynamic_pic);
                ImageView imageViewC = (ImageView) imageView2.findViewById(R.id.dynamic_pic);
                dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewB, dynamicIdDetailResponse.getData().getImages().get(1), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewC, dynamicIdDetailResponse.getData().getImages().get(2), R.drawable.bitmap_null, R.drawable.bitmap_null);
                showBigImage(imageViewA, dynamicIdDetailResponse.getData().getImages(), 0);
                showBigImage(imageViewB, dynamicIdDetailResponse.getData().getImages(), 1);
                showBigImage(imageViewC, dynamicIdDetailResponse.getData().getImages(), 2);

            } else if (imageSize >= 4) {

                dots.add(dot1);
                dots.add(dot2);
                dots.add(dot3);
                dots.add(dot4);

                dot1.setVisibility(View.VISIBLE);
                dot2.setVisibility(View.VISIBLE);
                dot3.setVisibility(View.VISIBLE);
                dot4.setVisibility(View.VISIBLE);


                Mview.add(imageView0);
                Mview.add(imageView1);
                Mview.add(imageView2);
                Mview.add(imageView3);
                ImageView imageViewA = (ImageView) imageView0.findViewById(R.id.dynamic_pic);
                ImageView imageViewB = (ImageView) imageView1.findViewById(R.id.dynamic_pic);
                ImageView imageViewC = (ImageView) imageView2.findViewById(R.id.dynamic_pic);
                ImageView imageViewD = (ImageView) imageView3.findViewById(R.id.dynamic_pic);
                dots.get(0).setBackgroundResource(R.drawable.image_viewpager_dot_selected);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewB, dynamicIdDetailResponse.getData().getImages().get(1), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewC, dynamicIdDetailResponse.getData().getImages().get(2), R.drawable.bitmap_null, R.drawable.bitmap_null);
                Presenter.getInstance(getContext()).getPlaceErrorImage(imageViewD, dynamicIdDetailResponse.getData().getImages().get(3), R.drawable.bitmap_null, R.drawable.bitmap_null);
                showBigImage(imageViewA, dynamicIdDetailResponse.getData().getImages(), 0);
                showBigImage(imageViewB, dynamicIdDetailResponse.getData().getImages(), 1);
                showBigImage(imageViewC, dynamicIdDetailResponse.getData().getImages(), 2);
                showBigImage(imageViewD, dynamicIdDetailResponse.getData().getImages(), 3);

            } else {
                LocalLog.e(TAG, "图片数量超过5");
            }

            adapter = new ImageViewPagerAdapter(getContext(), Mview);
            imageViewpager.setAdapter(adapter);
            PostDynamicContentParam postDynamicContentParam = new PostDynamicContentParam();
            postDynamicContentParam.
                    setDynamicid(dynamicid)
                    .setParent_id(0)
                    .setUserid(Presenter.getInstance(getContext())
                            .getId()).setReply_userid(userid);
            postDynamicAction(postDynamicContentParam, dynamicUserName.getText().toString(), reflashTopInterface);
        } else if (dynamicIdDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(DynamicLikeListResponse dynamicLikeListResponse) {
        LocalLog.d(TAG, "DynamicIdDetailResponse() enter " + dynamicLikeListResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (dynamicLikeListResponse.getError() == 0) {
            if (supportPics == null) {
                return;
            }
            if (supportPics.getVisibility() == View.GONE) {
                supportPics.setVisibility(View.VISIBLE);
            }
            likeData = (ArrayList<DynamicLikeListResponse.DataBeanX.DataBean>) dynamicLikeListResponse.getData().getData();
            if (likeUserAdapter == null) {
                likeUserAdapter = new LikeUserAdapter(getContext(), likeData);
            }
            supportIconRecycler.setAdapter(likeUserAdapter);

        } else if (dynamicLikeListResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }

    }

    @Override
    public void response(PostDynamicContentResponse postDynamicContentResponse) {
        LocalLog.d(TAG, "PostDynamicContentResponse() enter " + postDynamicContentResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (postDynamicContentResponse.getError() == 0) {
            LocalLog.d(TAG, "评论成功");
            contentNum++;
            if (reflashInterface != null) {
                reflashInterface.notifyReflash(postDynamicContentResponse);
            }
            if (popupRedPkgWindow != null) {
//                popupRedPkgWindow.dismiss();
            }
            resetCommentEdittext();
            contentNumbers.setText(String.valueOf(contentNum));
            intent.putExtra(getContext().getPackageName() + "contentNum", contentNum);
            getActivity().setResult(Activity.RESULT_OK, intent);
        } else if (postDynamicContentResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(PutVoteResponse putVoteResponse) {
        LocalLog.d(TAG, "PutVoteResponse() enter " + putVoteResponse.toString());
        if (!isAdded()) {
            return;
        }
        Toast.makeText(getContext(), putVoteResponse.getMessage(), Toast.LENGTH_SHORT).show();
        if (putVoteResponse.getError() == 0) {
            if (putVoteResponse.getMessage().equals("点赞成功")) {
                likeNumIcon.setImageDrawable(getDrawableResource(R.drawable.fabulous_s));
                likeNum += 1;
                contentSupports.setText(String.valueOf(likeNum));
                DynamicLikeListResponse.DataBeanX.DataBean dataBean = new DynamicLikeListResponse.DataBeanX.DataBean();
                dataBean.setUserid(Presenter.getInstance(getContext()).getId());
                dataBean.setAvatar(Presenter.getInstance(getContext()).getAvatar(getContext()));
                dataBean.setNickname(Presenter.getInstance(getContext()).getNickName(getContext()));
                likeData.add(0, dataBean);
                if (likeUserAdapter == null) {
                    if (supportPics.getVisibility() == View.GONE) {
                        supportPics.setVisibility(View.VISIBLE);
                    }
                    likeUserAdapter = new LikeUserAdapter(getContext(), likeData);
                    supportIconRecycler.setAdapter(likeUserAdapter);
                    likeUserAdapter.notifyDataSetChanged(likeData);
                } else {
                    likeUserAdapter.notifyItemInserted(0);
                }
                String likePeopleNumFormat = getContext().getString(R.string.like_people);
                String peopleNumStr = String.format(likePeopleNumFormat, likeNum);
                supportPeoples.setText(peopleNumStr);
                is_vote = 1;
                intent.putExtra(getContext().getPackageName() + "is_vote", 1);
            } else {
                likeNumIcon.setImageDrawable(getDrawableResource(R.drawable.fabulous_n));
                likeNum -= 1;
                String likePeopleNumFormat = getContext().getString(R.string.like_people);
                String peopleNumStr = String.format(likePeopleNumFormat, likeNum);
                supportPeoples.setText(peopleNumStr);
                contentSupports.setText(String.valueOf(likeNum));
                for (int i = 0; i < likeData.size(); i++) {
                    LocalLog.d(TAG, "id = " + likeData.get(i).toString());
                    if (likeData.get(i).getUserid() == Presenter.getInstance(getContext()).getId()) {
                        likeData.remove(i);
                        likeUserAdapter.notifyItemRemoved(i);
                    }
                }
                is_vote = 0;
                intent.putExtra(getContext().getPackageName() + "is_vote", is_vote);
            }
            intent.putExtra(getContext().getPackageName() + "likeNum", likeNum);
            getActivity().setResult(Activity.RESULT_OK, intent);
        } else if (putVoteResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }

    }

    private void showBigImage(ImageView imageView, final List<String> url, final int index) {
        LocalLog.d(TAG, "URL = " + url);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popBigImageInterface != null) {
                    LocalLog.d(TAG, "url = " + url);
                    popBigImageInterface.popImageView(url, index);
                }

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
            LocalLog.d(TAG, "查看大图");
            popBirthSelectView = View.inflate(getContext(), R.layout.image_big_view, null);
            PhotoView photoView = (PhotoView) popBirthSelectView.findViewById(R.id.photo_view);
            ImageDataModel.getInstance().getDisplayer().display(getContext(), url, photoView, mScreenWidth, mScreenHeight);
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


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.dynamic_id_detail), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }

        public void popImageView(List<String> images, int index) {
            if (images == null) {
                return;
            }
            LocalLog.d(TAG, "查看大图 index = "  + index);
            popBirthSelectView = View.inflate(getContext(), R.layout.big_image_view_pager, null);
            ImageViewPager bigImageViewPager = (ImageViewPager) popBirthSelectView.findViewById(R.id.big_image_viewpager);
            List<View> bigImageViews = new ArrayList<>();
            for (String url : images) {
                BigImageView bigImageView = new BigImageView(getContext());
                bigImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                bigImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ImageDataModel.getInstance().getDisplayer().display(getContext(), url, bigImageView, mScreenWidth, mScreenHeight);
                bigImageViews.add(bigImageView);
            }
            ImageViewPagerAdapter pagerAdapter = new ImageViewPagerAdapter(getContext(), bigImageViews);
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


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.dynamic_id_detail), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }
    };

    @Override
    public void postDynamicAction(PostDynamicContentParam postDynamicContentParam, String dearName, ReflashInterface reflashInterface) {
        LocalLog.d(TAG, "PostDynamicContentParam() 弹出评论框" + postDynamicContentParam.paramString());
        //popEdit(postDynamicContentParam, dearName);
        initEditView(postDynamicContentParam, dearName);
        this.reflashInterface = reflashInterface;
    }

    private void popEdit(final PostDynamicContentParam postDynamicContentParam, String dearName) {
        LocalLog.d(TAG, "popEdit() enter");
        if (popupRedPkgWindow == null) {
            popRedPkgView = View.inflate(getContext(), R.layout.response_edit_span, null);
            popupRedPkgWindow = new PopupWindow(popRedPkgView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    popupRedPkgWindow = null;
                }
            });
            commentEditText = (CustomEdit) popRedPkgView.findViewById(R.id.content_text);
            EditTextChangeListener editTextChangeListener = new EditTextChangeListener();
            commentEditText.addTextChangedListener(editTextChangeListener);

            ImageView buttonIcon = (ImageView) popRedPkgView.findViewById(R.id.edit_expression);
            LinearLayout mLlContent = (LinearLayout) popRedPkgView.findViewById(R.id.edit_content);
            EmotionLayout mElEmotion = (EmotionLayout) popRedPkgView.findViewById(R.id.elEmotion);
            mEmotionKeyboard = EmotionKeyboard.with(getActivity());
            mEmotionKeyboard.bindToContent(mLlContent);
            mEmotionKeyboard.bindToEmotionButton(buttonIcon);
            mEmotionKeyboard.bindToEditText(commentEditText);
            mEmotionKeyboard.setEmotionLayout(mElEmotion);
            popupRedPkgWindow.setFocusable(true);
            popupRedPkgWindow.setOutsideTouchable(false);
//            popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());

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

        commentEditText.setHint("回复:" + dearName);


        Button button = (Button) popRedPkgView.findViewById(R.id.send_content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = commentEditText.getText().toString();
                LocalLog.d(TAG, "content = " + content);
                int[] emj = getContext().getResources().getIntArray(R.array.emjio_list);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace(Utils.getEmojiStringByUnicode(emj[i]), "[0x" + numToHex8(emj[i]) + "]");
                    }
                }

                if (content != null && !content.equals("")) {
                    postDynamicContentParam.setContent(content).setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).postContent(postDynamicContentParam);
                }
            }
        });


        if (!popupRedPkgWindow.isShowing()) {
            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);

            popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.dynamic_id_detail), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            popRedPkgView.startAnimation(animationCircleType);
        }

    }


    public class EditTextChangeListener implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            LocalLog.d(TAG, "onTextChanged() enter ");
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            LocalLog.d(TAG, "beforeTextChanged() enter ");
        }

        @Override
        public void afterTextChanged(Editable editable) {
            LocalLog.d(TAG, "afterTextChanged() enter ");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}
