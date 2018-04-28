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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.GoodImageData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.task.GridAdpter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;

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
    ArrayList<SponsorDetailResponse.DataBean.GoodsImgsBean> goodsImgsBeans = new ArrayList<>();
    GoodImageData goodImageData;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private TranslateAnimation animationCircleType;

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
                if (goodsImgsBeans != null) {
                    popImageView(goodsImgsBeans.get(position).getUrl());
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
            }
        }
    }

    public void popImageView(String url) {
        LocalLog.d(TAG, "查看大图");
        int mScreenWidth = ImagePickerComUtils.getScreenWidth(getContext());
        int mScreenHeight = ImagePickerComUtils.getScreenHeight(getContext());
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


        popupSelectWindow.showAtLocation(getActivity().findViewById(R.id.sponsor_goods_pic_fg), Gravity.CENTER, 0, 0);
        popBirthSelectView.startAnimation(animationCircleType);
    }

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
