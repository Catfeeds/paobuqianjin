package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.BigImageView;
import com.paobuqianjin.pbq.step.customview.ImageViewPager;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.FriendStepDanActivity;
import com.paobuqianjin.pbq.step.view.activity.GetMoreMoneyActivity;
import com.paobuqianjin.pbq.step.view.activity.InoutcomDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.InviteDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;
import com.paobuqianjin.pbq.step.view.activity.MyDynamicActivity;
import com.paobuqianjin.pbq.step.view.activity.MyFriendActivity;
import com.paobuqianjin.pbq.step.view.activity.MyWalletActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeMakeActivity;
import com.paobuqianjin.pbq.step.view.activity.SettingActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorCollectActivity;
import com.paobuqianjin.pbq.step.view.activity.StepDollarActivity;
import com.paobuqianjin.pbq.step.view.activity.StepHistoryActivity;
import com.paobuqianjin.pbq.step.view.activity.SuggestionActivity;
import com.paobuqianjin.pbq.step.view.activity.TransferActivity;
import com.paobuqianjin.pbq.step.view.activity.UserInfoSettingActivity;
import com.paobuqianjin.pbq.step.view.activity.VipActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/1.
 */

public final class OwnerFragment extends BaseFragment {
    private final static String TAG = OwnerFragment.class.getSimpleName();
    String urlIcon = "";
    @Bind(R.id.user_back)
    ImageView userBack;
    @Bind(R.id.head_icon)
    CircleImageView headIcon;
    @Bind(R.id.sex)
    ImageView sex;
    @Bind(R.id.user_icon)
    RelativeLayout userIcon;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.golden_sponsor)
    ImageView goldenSponsor;
    @Bind(R.id.user_id)
    TextView userId;
    @Bind(R.id.qrcode)
    ImageView qrcode;
    @Bind(R.id.qrcode_rel)
    LinearLayout qrcodeRel;
    @Bind(R.id.user_span)
    LinearLayout userSpan;
    @Bind(R.id.circle)
    ImageView circle;
    @Bind(R.id.circle_rel)
    LinearLayout circleRel;
    @Bind(R.id.friend_span)
    LinearLayout friendSpan;
    @Bind(R.id.like_num)
    ImageView likeNum;
    @Bind(R.id.like_span)
    LinearLayout likeSpan;
    @Bind(R.id.near_by)
    ImageView nearBy;
    @Bind(R.id.near_by_span)
    LinearLayout nearBySpan;
    @Bind(R.id.wallet_money)
    TextView walletMoney;
    @Bind(R.id.crash_button)
    Button crashButton;
    @Bind(R.id.wallet_detail_button)
    Button walletDetailButton;
    @Bind(R.id.center_part)
    LinearLayout centerPart;
    @Bind(R.id.wallet_icon)
    ImageView walletIcon;
    @Bind(R.id.wallet_desc)
    TextView walletDesc;
    @Bind(R.id.wallet_span)
    LinearLayout walletSpan;
    @Bind(R.id.vip_icon)
    ImageView vipIcon;
    @Bind(R.id.vip_desc)
    TextView vipDesc;
    @Bind(R.id.vip_span)
    LinearLayout vipSpan;
    @Bind(R.id.step_dollar_desc)
    TextView stepDollarDesc;
    @Bind(R.id.step_dollar_span)
    LinearLayout stepDollarSpan;
    @Bind(R.id.wallet_dollor_span)
    LinearLayout walletDollorSpan;
    @Bind(R.id.gitf_span)
    ImageView gitfSpan;
    @Bind(R.id.dynamic_span)
    LinearLayout dynamicSpan;
    @Bind(R.id.collect_icon)
    ImageView collectIcon;
    @Bind(R.id.collect_desc)
    TextView collectDesc;
    @Bind(R.id.collect_span)
    LinearLayout collectSpan;
    @Bind(R.id.money_icon)
    ImageView moneyIcon;
    @Bind(R.id.money_span)
    LinearLayout moneySpan;
    @Bind(R.id.suggestion_icon)
    ImageView suggestionIcon;
    @Bind(R.id.suggestion_desc)
    TextView suggestionDesc;
    @Bind(R.id.suggestion_span)
    LinearLayout suggestionSpan;
    @Bind(R.id.setting_icon)
    ImageView settingIcon;
    @Bind(R.id.setting_desc)
    TextView settingDesc;
    @Bind(R.id.setting_span)
    LinearLayout settingSpan;
    @Bind(R.id.collect_sponsor_span)
    LinearLayout collectSponsorSpan;
    @Bind(R.id.step_dollar_number)
    ImageView stepDollarNumber;
    @Bind(R.id.circle_other)
    LinearLayout circleOther;
    @Bind(R.id.dan_icon)
    ImageView danIcon;
    @Bind(R.id.dan_desc)
    TextView danDesc;
    @Bind(R.id.owner_page)
    RelativeLayout ownerPage;
    @Bind(R.id.wallet_step)
    TextView walletStep;
    private String userAvatar;
    ImageView friends;
    private int vip = 0;
    private int cVip = 0;
    private int gVip = 0;
    private UserInfoResponse userInfoResponse;
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private final static String ACTION_STRANGE_ACTION = "com.paobuqianjin.pbq.step.STRANGE_ACTION";
    private final static String ACTION_FRIEND_ACTION = "com.paobuqianjn.step.FRIEND_ACTION";
    private final static String ACTION_FRIEND_HONOR = "com.paobuqianjin.pbq.ACTION_FRIEND_HONOR";
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;
    private int mScreenWidth;
    private int mScreenHeight;
    private RelativeLayout qrLinear;
    String money = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(ownerUiInterface);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_page;
    }

    @Override
    public void onResume() {
        super.onResume();
        Presenter.getInstance(getContext()).getUserInfo(Presenter.getInstance(getContext()).getId());
    }

    @Override
    protected void initView(View viewRoot) {
        headIcon = (CircleImageView) viewRoot.findViewById(R.id.head_icon);
        userIcon = (RelativeLayout) viewRoot.findViewById(R.id.user_icon);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        userId = (TextView) viewRoot.findViewById(R.id.user_id);
        circle = (ImageView) viewRoot.findViewById(R.id.circle);
        friends = (ImageView) viewRoot.findViewById(R.id.friend);
        likeNum = (ImageView) viewRoot.findViewById(R.id.like_num);
        walletSpan = (LinearLayout) viewRoot.findViewById(R.id.wallet_span);
        stepDollarSpan = (LinearLayout) viewRoot.findViewById(R.id.step_dollar_span);
        dynamicSpan = (LinearLayout) viewRoot.findViewById(R.id.dynamic_span);
        suggestionSpan = (LinearLayout) viewRoot.findViewById(R.id.suggestion_span);
        goldenSponsor = (ImageView) viewRoot.findViewById(R.id.golden_sponsor);
        walletMoney = (TextView) viewRoot.findViewById(R.id.wallet_money);
        walletStep = (TextView) viewRoot.findViewById(R.id.wallet_step);
        mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
        mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
        qrLinear = (RelativeLayout) viewRoot.findViewById(R.id.qr_linear);
        stepDollarNumber = (ImageView) viewRoot.findViewById(R.id.step_dollar_number);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        LocalLog.d(TAG, "onDestroyView() enter");
        Presenter.getInstance(getContext()).dispatchUiInterface(ownerUiInterface);
    }

    @OnClick({R.id.wallet_span, R.id.user_span, R.id.wallet_img, R.id.step_dollar_span, R.id.dynamic_span, R.id.suggestion_span, R.id.like_span, R.id.circle_rel, R.id.setting_span, R.id.vip_span,
            R.id.collect_span, R.id.money_span, R.id.crash_button, R.id.wallet_detail_button, R.id.friend_span, R.id.invite_people_span, R.id.gitf_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.user_span:
                LocalLog.d(TAG, "设置头像");
                if (userInfoResponse != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfoResponse.getData());
                    userInfoIntent.setClass(getContext(), UserInfoSettingActivity.class);
                    startActivity(userInfoIntent);
                }
                break;
            case R.id.wallet_span:
                intent = new Intent();
                intent.setAction(ACTION_FRIEND_HONOR);
                intent.setClass(getContext(), StepHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.wallet_img:
                LocalLog.d(TAG, "钱包");
                intent.setClass(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.step_dollar_span:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.gitf_span:
                LocalLog.d(TAG, "推荐人");
                intent.setClass(getContext(), InviteDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.invite_people_span:
                LocalLog.d(TAG, "推荐人");
                intent.setClass(getContext(), InviteDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.dynamic_span:
                LocalLog.d(TAG, "我的动态");
                intent.setClass(getContext(), MyDynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.suggestion_span:
                LocalLog.d(TAG, "意见反馈");
                intent.setClass(getContext(), SuggestionActivity.class);
                startActivity(intent);
                break;
            case R.id.friend_span:
                LocalLog.d(TAG, "好友");
                intent.setAction(ACTION_FRIEND_ACTION);
                intent.setClass(getContext(), MyFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.like_span:
                //抽奖
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.urlChongjiang));
                break;
            case R.id.circle_rel:
                LocalLog.d(TAG, "圈子");
                intent.setClass(getContext(), OwnerCircleActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_span:
                if (this.userInfoResponse != null && this.userInfoResponse.getData() != null) {
                    intent.putExtra("userinfo", this.userInfoResponse.getData());
                    intent.setClass(getContext(), SettingActivity.class);
                    startActivity(intent);
                }
/*                intent.setClass(getContext(), UserInfoSettingActivity.class);
                startActivity(intent);*/
                break;
            case R.id.vip_span:
                intent.putExtra("vip", vip);
                intent.setClass(getContext(), VipActivity.class);
                startActivity(intent);
                break;
            case R.id.collect_span:
                startActivity(SponsorCollectActivity.class, null);
                break;
            case R.id.money_span:
                startActivity(GetMoreMoneyActivity.class, null);
                break;
            case R.id.crash_button:
                if (!TextUtils.isEmpty(money)) {
                    intent.putExtra("total", money);
                    intent.setAction(CRASH_ACTION);
                    intent.setClass(getContext(), TransferActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.wallet_detail_button:
                startActivity(InoutcomDetailActivity.class, null);
                break;
            default:
                break;
        }
    }


    private void qrImage() {
        LocalLog.d(TAG, "qrImage() enter");
        String userid = Presenter.getInstance(getContext()).getNo();
        final String codeInfo = NetApi.urlShareIc + userid;
        encodeBitmap(codeInfo, qrcode.getWidth(), qrcode.getHeight());
        qrLinear.setVisibility(View.VISIBLE);

    }


    public interface PopBigImageInterface {
        public void popImageView(Bitmap bitmap);

        public void popImageView(List<String> images, int index);
    }

    private PopBigImageInterface popBigImageInterface = new PopBigImageInterface() {
        @Override
        public void popImageView(Bitmap bitmap) {
            LocalLog.d(TAG, "查看大图");
            if (popupSelectWindow != null && !popupSelectWindow.isShowing()) {
                return;
            }

            popBirthSelectView = View.inflate(getContext(), R.layout.image_big_view, null);
            PhotoView photoView = (PhotoView) popBirthSelectView.findViewById(R.id.photo_view);
            photoView.setImageBitmap(bitmap);
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


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.owner_page), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }

        public void popImageView(List<String> images, int index) {
            if (images == null) {
                return;
            }
            LocalLog.d(TAG, "查看大图 index = " + index);
            if (popupSelectWindow != null && !popupSelectWindow.isShowing()) {
                return;
            }
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


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.owner_page), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }
    };


    private void encodeBitmap(String url, int mgWidth, int imgWidth) {
        BitMatrix result = null;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
       /* try {
            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        } catch (WriterException e) {
            LocalLog.e(TAG, e.getMessage());
        }*/

        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 内容所使用字符集编码
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);

            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 116, 116, hints);
//            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            final Bitmap bitmap = barcodeEncoder.createBitmap(result);
            qrcode.setImageBitmap(bitmap);
            if (bitmap != null) {
                qrcode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userInfoResponse != null) {
                            Intent intent = new Intent();
                            intent.putExtra("usericon", userInfoResponse.getData().getAvatar());
                            intent.putExtra("username", userInfoResponse.getData().getNickname());
                            intent.putExtra("userid", Presenter.getInstance(getContext()).getNo());
                            intent.setClass(getContext(), QrCodeMakeActivity.class);
                            startActivity(intent);
                        }
                        /*LocalLog.d(TAG, "查看二维码大图!");
                        Bitmap logo = null;
                        if (((ImageView) headIcon).getDrawable() != null) {
                            logo = ((BitmapDrawable) ((ImageView) headIcon).getDrawable()).getBitmap();
                        }
                        if (logo == null) {
                            popBigImageInterface.popImageView(bitmap);
                        } else {
                            popBigImageInterface.popImageView(BitmapUtil.addLogo(bitmap, logo));
                        }*/
                    }
                });
            }
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            return;
        }
        return;
    }

    private OwnerUiInterface ownerUiInterface = new OwnerUiInterface() {
        @Override
        public void response(UserInfoResponse userInfoResponse) {
            if (!isAdded()) {
                return;
            }
            if (userInfoResponse.getError() == 0) {
                LocalLog.d(TAG, "UserInfoResponse() enter" + userInfoResponse.toString());
                OwnerFragment.this.userInfoResponse = userInfoResponse;
                userAvatar = userInfoResponse.getData().getAvatar();
                if (headIcon == null) {
                    LocalLog.d(TAG, "vvvvvvvv");
                    return;
                }
                Presenter.getInstance(getContext()).setMobile(getContext(), userInfoResponse.getData().getMobile());
                Presenter.getInstance(getContext()).setCurrentUser(userInfoResponse.getData());
                Presenter.getInstance(getContext()).setTarget(getContext(), userInfoResponse.getData().getTarget_step());
                Presenter.getInstance(getContext()).setAvatar(getContext(), userAvatar);
                Presenter.getInstance(getContext()).setNo(userInfoResponse.getData().getNo());
                Presenter.getInstance(getContext()).setNickName(getContext(), userInfoResponse.getData().getNickname());
                Presenter.getInstance(getContext()).getPlaceErrorImage(headIcon, userInfoResponse.getData().getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                money = userInfoResponse.getData().getBalance();
                String moneyStr = String.valueOf(userInfoResponse.getData().getBalance()) + " 元";
                SpannableString moneyString = new SpannableString(moneyStr);
                moneyString.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(userInfoResponse.getData().getBalance()).length(), moneyStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                walletMoney.setText(moneyString);
                String creditStr = String.valueOf(userInfoResponse.getData().getCredit()) + " 步币";
                SpannableString creditString = new SpannableString(creditStr);
                creditString.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(userInfoResponse.getData().getCredit()).length(), creditStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                walletStep.setText(creditString);
                userName.setText(userInfoResponse.getData().getNickname());
                userId.setText("跑步钱进号:" + userInfoResponse.getData().getNo());
                urlIcon = userInfoResponse.getData().getAvatar();
                vip = userInfoResponse.getData().getVip();
                cVip = userInfoResponse.getData().getCusvip();
                gVip = userInfoResponse.getData().getGvip();
                qrImage();
                if (vip == 1 || cVip == 1 || gVip == 1) {
                    userIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.golden_back));
                    sex.setVisibility(View.VISIBLE);
                    LocalLog.d(TAG, "当前用户为VIP");
                    if (gVip == 0) {
                        if (cVip == 0) {
                            goldenSponsor.setImageResource(R.drawable.vip_flg);
                            sex.setImageResource(R.drawable.vip_flg);
                        } else {
                            /*goldenSponsor.setImageResource(R.drawable.vip_sponsor);
                            sex.setImageResource(R.drawable.vip_sponsor);*/
                        }

                    } else {
                        sex.setImageResource(R.drawable.golden_little);
                        goldenSponsor.setImageResource(R.drawable.golden_big);
                    }
                    goldenSponsor.setVisibility(View.VISIBLE);

                } else {
                    LocalLog.d(TAG, "非会员显示男女");
                    goldenSponsor.setVisibility(View.INVISIBLE);
                    sex.setVisibility(View.INVISIBLE);
                    userIcon.setBackground(null);
                }
            } else if (userInfoResponse.getError() == 1) {
                exitTokenUnfect();
            } else if (userInfoResponse.getError() == -1) {

            } else if (userInfoResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (!isAdded()) {
                return;
            }
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

    };

}
