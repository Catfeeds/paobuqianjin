package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransparentGuideActivity extends Activity {

    private static final String TAG = TransparentGuideActivity.class.getSimpleName();
    @Bind(R.id.btn_home_page)
    TextView btnHomePage;
    @Bind(R.id.btn_shop_live)
    TextView btnTask;
    @Bind(R.id.btn_shang_cheng)
    TextView btnFriendCircle;
    @Bind(R.id.btn_conversion)
    TextView btnHonor;
    @Bind(R.id.btn_owner)
    TextView btnOwner;
    @Bind(R.id.iv_guide)
    ImageView ivGuide;
    @Bind(R.id.main_bottom)
    LinearLayout mainBottom;
    private int index;
    private int[] guideArr1 = {R.mipmap.ic_transparent_page1_a,R.mipmap.ic_transparent_page1_b,R.mipmap.ic_transparent_page1_c,R.mipmap.ic_transparent_page1_d};
    private int[] guideArr4 = {R.mipmap.ic_transparent_page4_a};
    private int currentGuideImageIndex;
    private int[] currentGuideImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
// 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
// 设置显示位置
        onWindowAttributesChanged(wl);

        setContentView(R.layout.activity_transparent_guide);
        ButterKnife.bind(this);

        index = getIntent().getIntExtra("index", -1);
        //LocalLog.d(TAG,index+"");
        if (index < 0) {
            finish();
            return;
        }

        switch (index) {
            case 0:
                break;
            case 1:
                initFragment2Guide();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                initFragment4Guide();
                break;
        }

        initTextViewIcon();
    }


    private void initFragment4Guide() {
        btnOwner.setVisibility(View.VISIBLE);
        currentGuideImageIndex = 0;
        currentGuideImages = guideArr4;
        ivGuide.setImageResource(currentGuideImages[currentGuideImageIndex]);
    }

    private void initFragment2Guide() {
        btnTask.setVisibility(View.VISIBLE);
        currentGuideImageIndex = 0;
        currentGuideImages = guideArr1;
        ivGuide.setImageResource(currentGuideImages[currentGuideImageIndex]);
    }

    //更改图片大小
    private void initTextViewIcon() {
        Drawable home = getResources().getDrawable(R.drawable.home_s);
        Drawable circle = getResources().getDrawable(R.drawable.circle_s);
        Drawable task = getResources().getDrawable(R.drawable.task_s);
        Drawable honor = getResources().getDrawable(R.drawable.list_s);
        Drawable me = getResources().getDrawable(R.drawable.me_s);

        home.setBounds(0, 0, 54, 54);
        btnHomePage.setCompoundDrawables(null, home, null, null);
        circle.setBounds(0, 0, 54, 54);
        btnFriendCircle.setCompoundDrawables(null, circle, null, null);
        task.setBounds(0, 0, 54, 54);
        btnTask.setCompoundDrawables(null, task, null, null);
        honor.setBounds(0, 0, 54, 54);
        btnHonor.setCompoundDrawables(null, honor, null, null);
        me.setBounds(0, 0, 54, 54);
        btnOwner.setCompoundDrawables(null, me, null, null);

    }

    @OnClick(R.id.iv_guide)
    public void onClick() {
        currentGuideImageIndex += 1;
        if (currentGuideImageIndex < currentGuideImages.length) {
            ivGuide.setImageResource(currentGuideImages[currentGuideImageIndex]);
        }else{
            finish();
        }

    }
}
