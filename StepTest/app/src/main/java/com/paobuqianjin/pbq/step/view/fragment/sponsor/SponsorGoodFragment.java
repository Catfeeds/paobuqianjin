package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.BigImageView;
import com.paobuqianjin.pbq.step.customview.ImageViewPager;
import com.paobuqianjin.pbq.step.data.bean.bundle.GoodImageData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.task.GridAdpter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorGoodFragment extends BaseBarStyleTextViewFragment implements AbsListView.OnScrollListener {
    private final static String TAG = SponsorGoodFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.sponsor_view)
    GridView sponsorView;
    private int mColumnWidth;
    private int mColumnNum;
    ArrayList<SponsorDetailResponse.DataBean.EnvironmentImgsBean> goodsImgsBeans = new ArrayList<>();
    GoodImageData goodImageData;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;
    private ArrayList<String> goodImages;

    @Override

    protected int getLayoutResId() {
        return R.layout.sponsor_detail_pic_fg;
    }

    @Override
    protected String title() {
        return "商品图片";
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
        super.initView(viewRoot);
        sponsorView = (GridView) viewRoot.findViewById(R.id.sponsor_view);
        sponsorView.setOnScrollListener(this);
        sponsorView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (goodImages != null && goodImages.size() > 0) {
                    /*popImageView(goodsImgsBeans.get(position).getUrl());*/
                    popBigImageInterface.popImageView(goodImages , position);
                }
            }
        });
        calColumn();
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            goodImageData = (GoodImageData) intent.getParcelableExtra(getActivity().getPackageName() + "goods");

            if (goodImageData != null) {
                goodsImgsBeans = goodImageData.getGoodsImgsBeans();
                sponsorView.setAdapter(new GridAdpter(getContext(), goodsImgsBeans, mColumnWidth));
                if (goodsImgsBeans != null) {
                    goodImages = new ArrayList<String>();
                    for (int i = 0; i < goodsImgsBeans.size(); i++) {
                        goodImages.add(goodsImgsBeans.get(i).getUrl());
                    }
                }
            }
        }
    }

    public interface PopBigImageInterface {
        public void popImageView(String url);

        public void popImageView(List<String> images, int index);
    }


    private PopBigImageInterface popBigImageInterface = new PopBigImageInterface() {
        @Override
        public void popImageView(String url) {
            int mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
            int mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
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
            int mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
            int mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
            if (images == null) {
                return;
            }
            LocalLog.d(TAG, "查看大图 index = " + index);
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


            popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.sponsor_goods_pic_fg), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }
    };


    //计算列数和每列宽度
    private void calColumn() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
        int densityDpi = metrics.densityDpi;
        int orientation = getResources().getConfiguration().orientation;
        int minColumn = 2;

        //计算列数
        mColumnNum = screenWidth / densityDpi;
        mColumnNum = mColumnNum < minColumn ? minColumn : mColumnNum;
        //计算每列宽度
        int columnSpace = (int) (2 * metrics.density);
        mColumnWidth = (screenWidth - columnSpace * (mColumnNum - 1)) / mColumnNum;

        if (sponsorView != null) {
            sponsorView.setColumnWidth(mColumnWidth);
            sponsorView.setNumColumns(mColumnNum);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
