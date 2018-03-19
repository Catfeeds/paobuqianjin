package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.LikeBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostDynamicContentParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PutVoteParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DanListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicIdDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicPersonResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostDynamicContentResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PutVoteResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicDetailInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
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
    private ArrayList<DynamicLikeListResponse.DataBeanX.DataBean> likeData;
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

    private View popRedPkgView;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;

    private int currentIndexPage = 1;
    private int likeNum, contentNum;
    private ReflashInterface reflashInterface;

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


        dynamicUserIcon = (CircleImageView) viewRoot.findViewById(R.id.dynamic_user_icon);
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
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            dynamicid = intent.getIntExtra(getContext().getPackageName() + "dynamicId", -1);
            userid = intent.getIntExtra(getContext().getPackageName() + "userId", -1);
            LocalLog.d(TAG, "dynamicid= " + dynamicid + "userid = " + userid);
            if (dynamicid != -1) {
                Presenter.getInstance(getContext()).getDynamicCommentList(dynamicid, currentIndexPage, 10);
                Presenter.getInstance(getContext()).getDynamicDetail(dynamicid);
                Presenter.getInstance(getContext()).getDynamicVoteList(dynamicid, userid, 1, 10);
            }
        }
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
        public void notifyReflash() {
            LocalLog.d(TAG, "更新顶层动态UI");
        }
    };

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
                case R.id.content_number_icon:
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
        if (dynamicCommentListResponse.getError() == 0) {
            contentDetailsListItem.setAdapter(new TopLevelContentAdapter(getContext(), dynamicCommentListResponse.getData().getData(), this));
        } else if (dynamicCommentListResponse.getError() == 1) {

        } else if (dynamicCommentListResponse.getError() == -1) {
            LocalLog.d(TAG, "");
        }
    }

    @Override
    public void response(DynamicIdDetailResponse dynamicIdDetailResponse) {
        if (dynamicIdDetailResponse.getError() == 0) {
            LocalLog.d(TAG, "DynamicIdDetailResponse() enter " + dynamicIdDetailResponse.toString());
            Presenter.getInstance(getContext()).getImage(dynamicUserIcon, dynamicIdDetailResponse.getData().getAvatar());
            dynamicUserName.setText(dynamicIdDetailResponse.getData().getNickname());
            long create_time = dynamicIdDetailResponse.getData().getCreate_time();
            //服务器保存到秒级别，本地处理为毫秒级别
            LocalLog.d(TAG, "create_time = " + DateTimeUtil.formatDateTime(create_time * 1000));
            String create_timeStr = DateTimeUtil.formatFriendly(new Date(create_time * 1000));
            dynamicTime.setText(create_timeStr);

            int[] emj = getContext().getResources().getIntArray(R.array.emjio_list);
            String content = dynamicIdDetailResponse.getData().getDynamic();

            if (content != null) {
                for (int i = 0; i < emj.length; i++) {
                    content = content.replace(Utils.getEmojiStringByUnicode(emj[i]), "[0x" + numToHex8(emj[i]) + "]");
                }
            }

            dynamicContentText.setText(content);
            if (dynamicIdDetailResponse.getData().getCity() != null && !dynamicIdDetailResponse.getData().getCity().equals("")) {
                dynamicLocationCity.setText(dynamicIdDetailResponse.getData().getCity());
            }
            likeNum = dynamicIdDetailResponse.getData().getVote();
            contentNum = dynamicIdDetailResponse.getData().getComment();
            contentNumbers.setText(String.valueOf(contentNum));
            contentSupports.setText(String.valueOf(likeNum));

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
                    Presenter.getInstance(getContext()).getImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0));
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
                Presenter.getInstance(getContext()).getImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0));
                Presenter.getInstance(getContext()).getImage(imageViewB, dynamicIdDetailResponse.getData().getImages().get(1));

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
                Presenter.getInstance(getContext()).getImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0));
                Presenter.getInstance(getContext()).getImage(imageViewB, dynamicIdDetailResponse.getData().getImages().get(1));
                Presenter.getInstance(getContext()).getImage(imageViewC, dynamicIdDetailResponse.getData().getImages().get(2));

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
                Presenter.getInstance(getContext()).getImage(imageViewA, dynamicIdDetailResponse.getData().getImages().get(0));
                Presenter.getInstance(getContext()).getImage(imageViewB, dynamicIdDetailResponse.getData().getImages().get(1));
                Presenter.getInstance(getContext()).getImage(imageViewC, dynamicIdDetailResponse.getData().getImages().get(2));
                Presenter.getInstance(getContext()).getImage(imageViewD, dynamicIdDetailResponse.getData().getImages().get(3));

            } else {
                LocalLog.e(TAG, "图片数量超过5");
            }

            adapter = new ImageViewPagerAdapter(getContext(), Mview);
            imageViewpager.setAdapter(adapter);
        }
    }

    @Override
    public void response(DynamicLikeListResponse dynamicLikeListResponse) {
        LocalLog.d(TAG, "DynamicIdDetailResponse() enter " + dynamicLikeListResponse.toString());
        if (dynamicLikeListResponse.getError() == 0) {
            if (supportPics.getVisibility() == View.GONE) {
                supportPics.setVisibility(View.VISIBLE);
            }

            likeData = (ArrayList<DynamicLikeListResponse.DataBeanX.DataBean>) dynamicLikeListResponse.getData().getData();
            supportIconRecycler.setAdapter(new LikeUserAdapter(getContext(), dynamicLikeListResponse.getData().getData()));
        }

    }

    @Override
    public void response(PostDynamicContentResponse postDynamicContentResponse) {
        LocalLog.d(TAG, "PostDynamicContentResponse() enter " + postDynamicContentResponse.toString());
        if (postDynamicContentResponse.getError() == 0) {
            LocalLog.d(TAG, "评论成功");
            if (reflashInterface != null) {
                reflashInterface.notifyReflash();
            }
            popupRedPkgWindow.dismiss();
        }
    }

    @Override
    public void response(PutVoteResponse putVoteResponse) {
        LocalLog.d(TAG, "PutVoteResponse() enter " + putVoteResponse.toString());
        Toast.makeText(getContext(), putVoteResponse.getMessage(), Toast.LENGTH_SHORT).show();
        if (putVoteResponse.getMessage().equals("点赞成功")) {
            likeNumIcon.setImageDrawable(getDrawableResource(R.drawable.fabulous_s));
            likeNum += 1;
            contentSupports.setText(String.valueOf(likeNum));
        } else {
            likeNumIcon.setImageDrawable(getDrawableResource(R.drawable.fabulous_n));
            likeNum -= 1;
            contentSupports.setText(String.valueOf(likeNum));
        }

    }

    @Override
    public void postDynamicAction(PostDynamicContentParam postDynamicContentParam, String dearName, ReflashInterface reflashInterface) {
        LocalLog.d(TAG, "PostDynamicContentParam() 弹出评论框" + postDynamicContentParam.paramString());
        popEdit(postDynamicContentParam, dearName);
        this.reflashInterface = reflashInterface;
    }

    private void popEdit(final PostDynamicContentParam postDynamicContentParam, String dearName) {
        LocalLog.d(TAG, "popRedPkg() enter");
        popRedPkgView = View.inflate(getContext(), R.layout.response_edit_span, null);
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
            }
        });

        final CustomEdit editText = (CustomEdit) popRedPkgView.findViewById(R.id.content_text);
        editText.setHint("回复:" + dearName);
        final EditTextChangeListener editTextChangeListener = new EditTextChangeListener();
        editText.addTextChangedListener(editTextChangeListener);

        Button button = (Button) popRedPkgView.findViewById(R.id.send_content);
        Button buttonIcon = (Button) popRedPkgView.findViewById(R.id.edit_expression);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();
                editText.removeTextChangedListener(editTextChangeListener);
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
        LinearLayout mLlContent = (LinearLayout) popRedPkgView.findViewById(R.id.edit_content);
        EmotionLayout mElEmotion = (EmotionLayout) popRedPkgView.findViewById(R.id.elEmotion);
        mEmotionKeyboard = EmotionKeyboard.with(getActivity());
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.bindToEmotionButton(buttonIcon);
        mEmotionKeyboard.bindToEditText(editText);
        mEmotionKeyboard.setEmotionLayout(mElEmotion);
        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);


        mElEmotion.attachEditText(editText);
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
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
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.dynamic_id_detail), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popRedPkgView.startAnimation(animationCircleType);
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
}
