package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.fragment.owner.FriendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2019/1/11.
 */

public class FriendActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String TAG = FriendActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.follow_tab)
    TabLayout followTab;
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.cancel_icon)
    RelativeLayout cancelIcon;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    String[] titles = {"好友", "已关注", "关注我的"};
    private List<Fragment> fragments = new ArrayList<>();
    private TextView viewNum;
    private int selectPage = 0;

    @Override
    protected String title() {
        return "关注";
    }

    @Override
    public Object right() {
        return "添加关注";
    }

    public interface UpFollowMeInterface {
        public void update(int data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(this);
        initFragment();
    }

    private UpFollowMeInterface upFollowMeInterface = new UpFollowMeInterface() {
        @Override
        public void update(int data) {
            if (data > 0 && viewNum != null) {
                viewNum.setVisibility(View.VISIBLE);
                viewNum.setText(String.valueOf(data));
            }else{
                if(data == 0 && viewNum !=null){
                    viewNum.setVisibility(View.GONE);
                }
            }
        }
    };

    private void initFragment() {
        searchCircleText.addTextChangedListener(textWatcher);
        cancelIcon.setOnClickListener(onClickListener);
        searchCircleText.setHint("搜索昵称");
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    LocalLog.d(TAG, "onEditorAction() selectPage = " + selectPage);
                    if (selectPage == 0 && fragments.get(selectPage).isAdded()) {
                        ((FriendFragment) fragments.get(selectPage)).setKeyWord(searchCircleText.getText().toString().trim());
                    } else if (selectPage == 1) {
                        ((FriendFragment) fragments.get(selectPage)).setKeyWord(searchCircleText.getText().toString().trim());
                    } else {
                        ((FriendFragment) fragments.get(selectPage)).setKeyWord(searchCircleText.getText().toString().trim());
                    }
                    Utils.hideInput(FriendActivity.this);
                }
                return false;
            }
        });
        for (int i = 0; i < titles.length; i++) {
            FriendFragment friendFragment = new FriendFragment();
            if (!friendFragment.isAdded())
                friendFragment.setFollowAction(titles[i]);
            fragments.add(friendFragment);
            if (i == 2) {
                friendFragment.setUpFollowMeInterface(upFollowMeInterface);
            }
        }
        TabAdapter tabAdapter = new TabAdapter(this
                , getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(tabAdapter);
        followTab.setupWithViewPager(viewpager);

        for (int i = 0; i < followTab.getTabCount(); i++) {
            followTab.getTabAt(i).setCustomView(getTabView(i));
        }
        followTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        selectPage = 0;
                        LocalLog.d(TAG, "onTabSelected() selectPage = " + selectPage);
                        break;
                    case 1:
                        selectPage = 1;
                        LocalLog.d(TAG, "onTabSelected() selectPage = " + selectPage);
                        break;
                    case 2:
                        selectPage = 2;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getTabView(int position) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.text_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            textView.setText(titles[0]);
            view.setGravity(Gravity.LEFT);
        } else if (position == 1) {
            textView.setText(titles[1]);
            view.setGravity(Gravity.CENTER);
        } else if (position == 2) {
            textView.setText(titles[2]);
            view.setGravity(Gravity.RIGHT);
            viewNum = (TextView) view.findViewById(R.id.att_num);
        }
        return view;
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cancel_icon:
                    LocalLog.d(TAG, "取消搜索,显示原来的数据");
                    searchCircleText.setText(null);
                    break;
            }
        }
    };
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            if (!TextUtils.isEmpty(temp)) {
                LocalLog.d(TAG, "显示取消搜索界面");
                cancelIcon.setVisibility(View.VISIBLE);
                cancelIcon.setOnClickListener(onClickListener);
            } else {
                LocalLog.d(TAG, "隐藏搜索界面");
                cancelIcon.setVisibility(View.GONE);
                LocalLog.d(TAG, "afterTextChanged() enter selectPage" + selectPage);
                if (selectPage == 0 && fragments.get(selectPage).isAdded()) {
                    ((FriendFragment) fragments.get(selectPage)).setKeyWord(null);
                } else if (selectPage == 1) {
                    ((FriendFragment) fragments.get(selectPage)).setKeyWord(null);
                } else {
                    ((FriendFragment) fragments.get(selectPage)).setKeyWord(null);
                }
            }
        }
    };

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        Intent friendAddressIntent = new Intent();
        friendAddressIntent.setClass(this, AddFriendAddressActivity.class);
        startActivity(friendAddressIntent);
    }
}
