package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.UserInfoSettingActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SelectSettingAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/5.
 */

public class UserInfoSettingFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = UserInfoSettingFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.go_pic)
    ImageView goPic;
    @Bind(R.id.user_head_icon_change)
    RelativeLayout userHeadIconChange;
    @Bind(R.id.line_change_ico)
    ImageView lineChangeIco;
    @Bind(R.id.go_pic1)
    ImageView goPic1;
    @Bind(R.id.user_name_change)
    RelativeLayout userNameChange;
    @Bind(R.id.line_change_male)
    ImageView lineChangeMale;
    @Bind(R.id.go_pic2)
    ImageView goPic2;
    @Bind(R.id.change_male)
    RelativeLayout changeMale;
    @Bind(R.id.line_location_name)
    ImageView lineLocationName;
    @Bind(R.id.go_pic3)
    ImageView goPic3;
    @Bind(R.id.change_birth)
    RelativeLayout changeBirth;
    @Bind(R.id.line_change_birth)
    ImageView lineChangeBirth;
    @Bind(R.id.go_pic4)
    ImageView goPic4;
    @Bind(R.id.change_city)
    RelativeLayout changeCity;
    @Bind(R.id.line_change_city)
    ImageView lineChangeCity;

    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;

    @Override
    protected String title() {
        return "修改资料";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.user_info_setting_fg;
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
        userHeadIconChange = (RelativeLayout) viewRoot.findViewById(R.id.user_head_icon_change);
        userNameChange = (RelativeLayout) viewRoot.findViewById(R.id.user_name_change);
        changeMale = (RelativeLayout) viewRoot.findViewById(R.id.change_male);
        changeBirth = (RelativeLayout) viewRoot.findViewById(R.id.change_birth);
        changeCity = (RelativeLayout) viewRoot.findViewById(R.id.change_city);
        setOnClickListener();
    }

    private void setOnClickListener() {
        userHeadIconChange.setOnClickListener(onClickListener);
        userNameChange.setOnClickListener(onClickListener);
        changeMale.setOnClickListener(onClickListener);
        changeBirth.setOnClickListener(onClickListener);
        changeCity.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalLog.d(TAG, "onClick() enter");
            switch (view.getId()) {
                case R.id.user_head_icon_change:
                    LocalLog.d(TAG, "设置头像");
/*                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, UserInfoSettingActivity.CAMERA);*/
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, UserInfoSettingActivity.PICTURE);
                    break;
                case R.id.user_name_change:
                    LocalLog.d(TAG, "设置昵称");
                    break;
                case R.id.change_male:
                    LocalLog.d(TAG, "设置性别");
                    ArrayList<String> male = new ArrayList<>();
                    male.add("男");
                    male.add("女");
                    selectType(male);
                    break;
                case R.id.change_birth:
                    LocalLog.d(TAG, "设置生日");
                    break;
                case R.id.change_city:
                    LocalLog.d(TAG, "设置城市");
                    CityListLoader.getInstance().loadProData(getContext());
                    CityPickerView.getInstance().setConfig(new CityConfig.Builder(getContext()).build());
                    CityPickerView.getInstance().setOnCityItemClickListener(new OnCityItemClickListener() {
                        @Override
                        public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                            //省份
                            if (province != null) {

                            }

                            //城市
                            if (city != null) {

                            }

                            //地区
                            if (district != null) {

                            }
                            LocalLog.d(TAG, province.getName() + "· " + city.getName() + "·" + district.getName());

                        }

                        @Override
                        public void onCancel() {
                            ToastUtils.showLongToast(getContext(), "已取消");
                        }
                    });

                    //显示
                    CityPickerView.getInstance().showCityPicker(getContext());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //单选项
    public void selectType(ArrayList<String> strings) {
        final SelectSettingAdapter selectSettingAdapter = new SelectSettingAdapter(getContext(), strings);

        popupCircleTypeView = View.inflate(getContext(), R.layout.select_dialog_layout, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        popupCircleTypeWindow.setFocusable(true);

        popupCircleTypeWindow.setOutsideTouchable(true);

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        popupCircleTypeView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                LocalLog.d(TAG, "onClick() 取消");
                if (popupCircleTypeWindow.isShowing()) {
                    popupCircleTypeWindow.dismiss();
                    popupCircleTypeWindow = null;
                }
            }
        });

        popupCircleTypeView.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "onClick() 确定");
                String selectString = selectSettingAdapter.getSelectContent();
                LocalLog.d(TAG, "选择结果: " + selectString);
                if (popupCircleTypeWindow.isShowing()) {
                    popupCircleTypeWindow.dismiss();
                    popupCircleTypeWindow = null;
                }

            }
        });
        final RecyclerView recyclerView = (RecyclerView) popupCircleTypeView.findViewById(R.id.select_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(selectSettingAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                LocalLog.d(TAG, "OnItemClick() enter " + position);
                int lastSelectPosition = selectSettingAdapter.getSelectPosition();
                selectSettingAdapter.setSelectPosition(position);
                recyclerView.getAdapter().notifyItemChanged(position);
                recyclerView.getAdapter().notifyItemChanged(lastSelectPosition);
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                LocalLog.d(TAG, "OnItemLongClick() enter " + position);
            }
        }));

        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.user_info_setting_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

}
