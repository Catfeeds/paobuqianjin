package com.paobuqianjin.pbq.step.view.base.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

/**
 * Created by pbq on 2017/12/29.
 */

public abstract class BaseBarImageViewFragment extends BaseFragment {
    private BaseBarActivity.ToolBarListener mToolBarListener;
    private TextView tv_title;
    private ImageView tv_left;
    private ImageView tv_right;
    private RelativeLayout rv_left;

    /*@desc 设置导航栏标题
    *@function title
    *@param
    *@return
    */
    protected abstract String title();

    /*@desc 左导航栏
    *@function left
    *@param
    *@return
    */
    public Object left() {
        return null;
    }

    /*@desc 左导航栏
    *@function left
    *@param
    *@return
    */
    public Object right() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initBarView(rootView);
        return rootView;
    }

    public BaseBarActivity.ToolBarListener setToolBarListener() {
        return null;
    }

    public void initBarView(View rootView) {
        tv_title = getView(rootView, R.id.bar_title);
        tv_right = getView(rootView, R.id.bar_tv_right);
        tv_left = getView(rootView, R.id.bar_return_drawable);
        tv_right.setImageDrawable((Drawable) right());
        rv_left = getView(rootView,R.id.button_return_bar);
        rv_left.setOnClickListener(clickListener);
        setToolBarListener();
        tv_left.setOnClickListener(clickListener);
        tv_right.setOnClickListener(clickListener);
        tv_title.setText(title());
    }

    public void setToolBarListener(BaseBarActivity.ToolBarListener toolBarListener) {
        mToolBarListener = toolBarListener;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bar_return_drawable:
                case R.id.button_return_bar:
                    if (mToolBarListener == null) {
                        BaseBarImageViewFragment.this.getActivity().finish();
                    } else {
                        //mToolBarListener.clickLeft();
                        BaseBarImageViewFragment.this.getActivity().finish();
                    }
                    break;
                case R.id.bar_tv_right:
                    if (mToolBarListener != null) {
                        mToolBarListener.clickRight();
                    }
                    break;
            }
        }
    };

    protected void refreshTop(View viewRoot) {
        setLeftView(left() == null ? R.drawable.bar_bg_selector : left());
        setRightValue(viewRoot, R.id.bar_tv_right, right());

    }

    protected void setRightValue(View viewRoot, int id, Object obj) {

    }

    public Drawable getDrawableResource(int id) {
        return getResources().getDrawable(id);
    }

    private void setLeftView(Object obj) {
        if (obj != null && !obj.equals("")) {
            tv_left.setVisibility(View.VISIBLE);
            if (obj instanceof Integer) {
                tv_left.setImageResource(Integer.parseInt(obj.toString()));
            } else {
                tv_left.setImageResource(R.drawable.bar_bg_selector);
            }
        } else {
            tv_left.setVisibility(View.INVISIBLE);
        }
    }

    protected <T extends View> T getView(View viewRoot, int id) {
        return (T) viewRoot.findViewById(id);
    }

    /**
     * 启动指定Activity
     *
     * @param target
     * @param bundle
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(BaseBarImageViewFragment.this.getActivity(), target);
        if (bundle != null)
            intent.putExtra(BaseBarImageViewFragment.this.getActivity().getPackageName(), bundle);
        startActivity(intent);
    }

    public interface ToolBarListener {
        void clickLeft();

        void clickRight();
    }
}
