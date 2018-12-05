package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponCateListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FavorColResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.ShopToolUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.FavorIdActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.TianMaoActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by pbq on 2018/12/5.
 */

public class TaoHomeTopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = TaoHomeTopAdapter.class.getSimpleName();
    private Context context;
    private final static int TYPE_HEAD = 1;
    private final static int TYPE_MIDDLE = 2;
    private final static int TYPE_THREE = 3;
    private FragmentManager fm;
    private int currentPage = 0;
    private int selectTab = 0;
    //商品列表
    private List<CouponListResponse.DataBean.TbkCouponBean> listData = new ArrayList<>();
    private List<CouponCateListResponse.DataBean> strings = new ArrayList<>();
    TianMaoActivity.LoadDataInterface loadDataInterface;

    public void setFirstItem() {

    }

    private void setSecondItem() {

    }

    private void setThirdMoreItem() {

    }


    public int getSelectTab() {
        return selectTab;
    }


    public void setSelectTab(int position) {
        selectTab = position;
        if (strings.size() > 0) {
            getPageData(1, strings.get(position));
        }
    }

    public TaoHomeTopAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    public void setLoadMoreInterface(TianMaoActivity.LoadDataInterface loadMoreInterface) {
        this.loadDataInterface = loadMoreInterface;
    }

    public void loadMore() {
        LocalLog.d(TAG, "loadMore() enter");
        getPageData(currentPage + 1, strings.get(selectTab));
    }

    @Override
    public int getItemCount() {
        return 2 + listData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position == 1) {
            return TYPE_MIDDLE;
        } else {
            return TYPE_THREE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LocalLog.d(TAG, "onBindViewHolder() enter position = " + position);
        if (holder instanceof ThreeViewHolder && position > 1) {
            int dataPosition = position -2;
            try {
                if (((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getPict_url() != null &&
                        !TextUtils.isEmpty(((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getPict_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(((ThreeViewHolder) holder).goodPic, ((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getPict_url(),
                            R.drawable.bitmap_null, R.drawable.bitmap_null);
                    if (((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getUser_type() == 0) {
                        //插入淘宝图标
                    } else if (((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getUser_type() == 1) {
                        //插入天猫图标
                    }
                    ((ThreeViewHolder) holder).goodName.setText(((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getTitle());
                    ((ThreeViewHolder) holder).taobaoPrice.setText("淘宝价: ￥" + String.valueOf(((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getZk_final_price()));

                    ((ThreeViewHolder) holder).salesNum.setText("销量 " + String.valueOf(((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getVolume()));

                    ((ThreeViewHolder) holder).quanAfterPrice.setText("券后价  ￥" + String.valueOf(((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getCoupon_info()));

                    ((ThreeViewHolder) holder).taoquanTv.setText("￥ " + ((CouponListResponse.DataBean.TbkCouponBean) listData.get(dataPosition)).getCoupon_info());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(LayoutInflater.from(context).inflate(R.layout.tao_home_head_item, parent, false), context);
        } else if (viewType == TYPE_MIDDLE) {
            return new MiddleViewHolder(LayoutInflater.from(context).inflate(R.layout.tao_middle_item, parent, false), context, fm);
        } else {
            return new ThreeViewHolder(LayoutInflater.from(context).inflate(R.layout.shop_style_item, parent, false));
        }
    }

    public void getPageData(final int page, CouponCateListResponse.DataBean dataBean) {
        if (TextUtils.isEmpty(dataBean.getCate_id())) {
            return;
        }
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        params.put("cate_id", dataBean.getCate_id());
        params.put("term_id", "1");
        Presenter.getInstance(context).postPaoBuSimple(NetApi.urlCouponCateList, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                }
                CouponListResponse couponListResponse = new Gson().fromJson(s, CouponListResponse.class);
                if (couponListResponse.getData().getTbk_coupon().size() > 0) {
                    listData.addAll(couponListResponse.getData().getTbk_coupon());
                    notifyDataSetChanged();
                }
                if (loadDataInterface != null) {
                    loadDataInterface.canLoadMore();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);

            }
        });
    }


    public static class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            initView(view);
            loadBanner();
            getFavorColumn();
        }

        private void startFavorIdActivity(final String favorId) {
            LocalLog.d(TAG, "favorId = " + favorId);
            Intent intent = new Intent();
            intent.putExtra(context.getPackageName() + "favor_id", favorId);
            intent.setClass(context, FavorIdActivity.class);
            context.startActivity(intent);
        }

        private void initPartI(int i, final FavorColResponse.DataBean dataBean) {
            if (i == 3) {
                partOneSpan.setVisibility(View.VISIBLE);
                title1.setText(dataBean.getFavorites_title());
                if (!TextUtils.isEmpty(dataBean.getIcon_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(pic1, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
                }
                if (TextUtils.isEmpty(dataBean.getAndroid())) {
                    if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                        partOneSpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                                startFavorIdActivity(dataBean.getFavorites_id());
                            }
                        });
                    }
                } else {
                    partOneSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                        }
                    });

                }
            } else if (i == 0) {
                partTwoSpan.setVisibility(View.VISIBLE);
                title2.setText(dataBean.getFavorites_title());
                if (!TextUtils.isEmpty(dataBean.getIcon_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(pic2, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
                }
                if (TextUtils.isEmpty(dataBean.getAndroid())) {
                    if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                        partTwoSpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                                startFavorIdActivity(dataBean.getFavorites_id());
                            }
                        });
                    }
                } else {
                    partTwoSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                        }
                    });

                }
            } else if (i == 2) {
                partThreeSpan.setVisibility(View.VISIBLE);
                title3.setText(dataBean.getFavorites_title());
                if (!TextUtils.isEmpty(dataBean.getIcon_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(pic3, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
                }
                if (TextUtils.isEmpty(dataBean.getAndroid())) {
                    if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                        partThreeSpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                                startFavorIdActivity(dataBean.getFavorites_id());
                            }
                        });
                    }
                } else {
                    partThreeSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                        }
                    });

                }
            } else if (i == 1) {
                partForSpan.setVisibility(View.VISIBLE);
                title4.setText(dataBean.getFavorites_title());
                if (!TextUtils.isEmpty(dataBean.getIcon_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(pic4, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
                }
                if (TextUtils.isEmpty(dataBean.getAndroid())) {
                    if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                        partForSpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                                startFavorIdActivity(dataBean.getFavorites_id());
                            }
                        });
                    }
                } else {
                    partForSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                        }
                    });

                }
            } else if (i == 4) {
                partFivSpan.setVisibility(View.VISIBLE);
                title5.setText(dataBean.getFavorites_title());
                if (!TextUtils.isEmpty(dataBean.getIcon_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(pic5, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
                }
                if (TextUtils.isEmpty(dataBean.getAndroid())) {
                    if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                        partFivSpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                                startFavorIdActivity(dataBean.getFavorites_id());
                            }
                        });
                    }
                } else {
                    partFivSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                        }
                    });

                }
            } else if (i == 5) {
                partSixSpan.setVisibility(View.VISIBLE);
                title6.setText(dataBean.getFavorites_title());
                if (!TextUtils.isEmpty(dataBean.getIcon_url())) {
                    Presenter.getInstance(context).getPlaceErrorImage(pic6, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
                }
                if (TextUtils.isEmpty(dataBean.getAndroid())) {
                    if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                        partSixSpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalLog.d(TAG, "favorites_id = " + dataBean.getFavorites_id());
                                startFavorIdActivity(dataBean.getFavorites_id());
                            }
                        });
                    }
                } else {
                    partSixSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                        }
                    });
                }
            }
        }

        /*1表示Android */
        private void getFavorColumn() {
            Map<String, String> param = new HashMap<>();
            param.put("term_id", "1");
            Presenter.getInstance(context).postPaoBuSimple(NetApi.urlFavorColumnH5, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        FavorColResponse colResponse = new Gson().fromJson(s, FavorColResponse.class);
                        if (colResponse.getData() != null) {
                            int size = colResponse.getData().size();
                            LocalLog.d(TAG, "parts  size = " + size);
                            for (int i = 0; i < size; i++) {
                                if (i == 5)
                                    break;
                                initPartI(i, colResponse.getData().get(i));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        PaoToastUtils.showLongToast(context, "开小差了，请稍后再试");
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (errorBean != null) {
                        PaoToastUtils.showLongToast(context, errorBean.getMessage());
                    } else {
                        PaoToastUtils.showLongToast(context, "开小差了，请稍后再试");
                    }
                }
            });
        }

        private void loadBanner() {
            String bannerUrl = NetApi.urlAd + "?position=red_voucher" + Presenter.getInstance(context).getLocationStrFormat();
            LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
            Presenter.getInstance(context).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                        final ArrayList<AdObject> adList = new ArrayList<>();
                        if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                            int size = adresponse.getData().size();
                            for (int i = 0; i < size; i++) {
                                if (adresponse.getData().get(i).getImgs() != null
                                        && adresponse.getData().get(i).getImgs().size() > 0) {
                                    int imgSize = adresponse.getData().get(i).getImgs().size();
                                    for (int j = 0; j < imgSize; j++) {
                                        AdObject adObject = new AdObject();
                                        adObject.setRid(Integer.parseInt(adresponse.getData().get(i).getRid()));
                                        adObject.setImg_url(adresponse.getData().get(i).getImgs().get(j).getImg_url());
                                        adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                                        adList.add(adObject);
                                    }
                                }
                            }
                        }
                        banner.setImageLoader(new BannerImageLoader())
                                .setImages(adList)
                                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                                .setBannerAnimation(Transformer.Default)
                                .isAutoPlay(true)
                                .setDelayTime(2000)
                                .setIndicatorGravity(BannerConfig.CENTER)
                                .setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        if (adList.get(position).getRid() == 0) {
                                            LocalLog.d(TAG, "复制微信号");
                                            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                            ClipData textClipData = ClipData.newPlainText("Label", context.getString(R.string.wx_code));
                                            cmb.setPrimaryClip(textClipData);
                                            LocalLog.d(TAG, "  msg = " + cmb.getText());
                                            PaoToastUtils.showLongToast(context, "微信号复制成功");
                                        }
                                        String targetUrl = adList.get(position).getTarget_url();
                                        String result = ShopToolUtil.taoBaoString(targetUrl);
                                        if (!TextUtils.isEmpty(result)) {
                                            if (result.startsWith(ShopToolUtil.TaoBaoSchema)
                                                    && Utils.checkPackage(context, ShopToolUtil.TaoBao)) {
                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                context.startActivity(intent);
                                            } else {
                                                context.startActivity(new Intent(context, SingleWebViewActivity.class).putExtra("url", targetUrl));
                                            }
                                        }
                                    }
                                })
                                .start();
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    LocalLog.d(TAG, "获取失败!");
                }
            });
        }

        private void initView(View viewRoot) {
            banner = (Banner) viewRoot.findViewById(R.id.banner);
            title1 = (TextView) viewRoot.findViewById(R.id.title_1);
            title2 = (TextView) viewRoot.findViewById(R.id.title_2);
            title3 = (TextView) viewRoot.findViewById(R.id.title_3);
            title4 = (TextView) viewRoot.findViewById(R.id.title_4);
            title5 = (TextView) viewRoot.findViewById(R.id.title_5);
            title6 = (TextView) viewRoot.findViewById(R.id.title_6);

            pic1 = (ImageView) viewRoot.findViewById(R.id.pic_1);
            pic2 = (ImageView) viewRoot.findViewById(R.id.pic_2);
            pic3 = (ImageView) viewRoot.findViewById(R.id.pic_3);
            pic4 = (ImageView) viewRoot.findViewById(R.id.pic_4);
            pic5 = (ImageView) viewRoot.findViewById(R.id.pic_6);
            pic6 = (ImageView) viewRoot.findViewById(R.id.pic_6);

            partOneSpan = (LinearLayout) viewRoot.findViewById(R.id.part_one_span);
            partTwoSpan = (LinearLayout) viewRoot.findViewById(R.id.part_two_span);
            partThreeSpan = (LinearLayout) viewRoot.findViewById(R.id.part_three_span);
            partForSpan = (LinearLayout) viewRoot.findViewById(R.id.part_for_span);
            partFivSpan = (LinearLayout) viewRoot.findViewById(R.id.part_fiv_span);
            partSixSpan = (LinearLayout) viewRoot.findViewById(R.id.part_six_span);

        }


        Context context;
        Banner banner;
        TextView title1;
        ImageView pic1;
        LinearLayout partOneSpan;
        TextView title4;
        ImageView pic4;
        LinearLayout partForSpan;
        TextView title2;
        ImageView pic2;
        LinearLayout partTwoSpan;
        TextView title3;
        ImageView pic3;
        LinearLayout partThreeSpan;
        TextView title5;
        ImageView pic5;
        LinearLayout partFivSpan;
        TextView title6;
        ImageView pic6;
        LinearLayout partSixSpan;
    }


    public class MiddleViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private FragmentManager fm;

        public MiddleViewHolder(View view, Context context, FragmentManager fragmentManager) {
            super(view);
            this.context = context;
            initView(view);
            fm = fragmentManager;
        }

        private void initView(View viewRoot) {
            tablayout = (TabLayout) viewRoot.findViewById(R.id.tablayout);
            more = (ImageView) viewRoot.findViewById(R.id.more);
            getCouponCateList();
        }


        private void getCouponCateList() {
            Presenter.getInstance(context).postPaoBuSimple(NetApi.urlCouponCateStyleS, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        CouponCateListResponse couponCateListResponse = new Gson().fromJson(s, CouponCateListResponse.class);
                        if (couponCateListResponse.getData() != null) {
                            int size = couponCateListResponse.getData().size();

                            if (size == 0) {
                                return;
                            }
                            strings = new ArrayList<>();
                            for (int i = 0; i < size; i++) {
                                strings.add(couponCateListResponse.getData().get(i));
                            }
                            getPageData(1, couponCateListResponse.getData().get(0));
                            ViewPager viewPager = new ViewPager(context);
                            viewPager.setAdapter(new FragmentPagerAdapter(fm) {
                                @Override
                                public Fragment getItem(int position) {
                                    return null;
                                }

                                @Override
                                public int getCount() {
                                    return strings.size();
                                }

                                @Override
                                public CharSequence getPageTitle(int position) {
                                    try {
                                        LocalLog.d(TAG, "postion =" + position + ",name = " + strings.get(position).getCate_name());
                                        return (String) strings.get(position).getCate_name();
                                    } catch (Exception e) {
                                        return null;
                                    }
                                }
                            });
                            tablayout.setupWithViewPager(viewPager);
                            for (int i = 0; i < tablayout.getTabCount(); i++) {
                                TabLayout.Tab tab = tablayout.getTabAt(i);
                                if (tab != null) {
                                    TextView textView = new TextView(context);
                                    textView.setMinWidth(30);
                                    textView.setText(strings.get(i).getCate_name());
                                    textView.setGravity(Gravity.CENTER);
                                    textView.setTextSize(14);
                                    tab.setCustomView(textView);
                                    if (i == 0) {
                                        textView.setTextColor(ContextCompat.getColor(context, R.color.color_FF3E7E));
                                    }
                                    if (tab.getCustomView() != null) {
                                        View tabView = (View) tab.getCustomView().getParent();
                                        tabView.setTag(i);
                                    }
                                }
                            }

                            tablayout.getTabAt(0).select();
                            selectTab = 0;
                            tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    try {
                                        LocalLog.d(TAG, "postion =" + tab.getPosition());
                                        getPageData(1, strings.get(tab.getPosition()));
                                        selectTab = tab.getPosition();
                                        View customView = tab.getCustomView();
                                        try {
                                            if (customView != null && customView instanceof TextView) {
                                                ((TextView) customView).setTextColor(ContextCompat.getColor(context, R.color.color_FF3E7E));
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {
                                    View customView = tab.getCustomView();
                                    try {
                                        if (customView != null && customView instanceof TextView) {
                                            ((TextView) customView).setTextColor(ContextCompat.getColor(context, R.color.color_646464));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (errorBean != null) {
                        PaoToastUtils.showLongToast(context, errorBean.getMessage());
                    } else {
                        PaoToastUtils.showLongToast(context, "开小差了，请稍后再试");
                    }
                }
            });
        }

        TabLayout tablayout;
        ImageView more;
    }

    public static class ThreeViewHolder extends RecyclerView.ViewHolder {
        CircularImageView goodPic;
        TextView goodName;
        TextView taobaoPrice;
        TextView salesNum;
        TextView quanAfterPrice;
        TextView taoquanTv;

        public ThreeViewHolder(View viewRoot) {
            super(viewRoot);
            initView(viewRoot);
        }

        private void initView(View viewRoot) {
            goodPic = (CircularImageView) viewRoot.findViewById(R.id.good_pic);
            goodName = (TextView) viewRoot.findViewById(R.id.good_name);
            taobaoPrice = (TextView) viewRoot.findViewById(R.id.sales_num);
            salesNum = (TextView) viewRoot.findViewById(R.id.sales_num);
            quanAfterPrice = (TextView) viewRoot.findViewById(R.id.quan_after_price);
            taoquanTv = (TextView) viewRoot.findViewById(R.id.taoquan_tv);
        }
    }
}

