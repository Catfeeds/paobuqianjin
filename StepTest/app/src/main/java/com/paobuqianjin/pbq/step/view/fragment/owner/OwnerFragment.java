package com.paobuqianjin.pbq.step.view.fragment.owner;

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
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

/**
 * Created by pbq on 2017/12/1.
 */

public final class OwnerFragment extends BaseFragment {
    //   private TextView logoutTV;
    private BaseBarActivity.ToolBarListener mToolBarListener;
    private TextView tv_title;
    private ImageView tv_right;
    private RelativeLayout rv_right;

    /*@desc 设置导航栏标题
    *@function title
    *@param
    *@return
    */
    protected String title() {
        return "我";
    }

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
        setToolBarListener();
        tv_right.setOnClickListener(clickListener);
        refreshTop(rootView);
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
                        OwnerFragment.this.getActivity().finish();
                    } else {
                        //mToolBarListener.clickLeft();
                        OwnerFragment.this.getActivity().finish();
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
        setRightValue(viewRoot, R.id.bar_tv_right, right());
        tv_title.setText(title());
    }

    protected void setRightValue(View viewRoot, int id, Object obj) {
        if (obj != null && !obj.equals("")) {
            ((TextView) getView(viewRoot, id)).setText("");
            getView(viewRoot, id).setBackgroundDrawable(new BitmapDrawable());
            if (obj instanceof String) {
                ((TextView) getView(viewRoot, id)).setText(obj.toString());
            } else if (obj instanceof Integer) {
                getView(viewRoot, id).setBackgroundResource(Integer.parseInt(obj.toString()));
            } else {
                ((TextView) getView(viewRoot, id)).setText("");
                getView(viewRoot, id).setBackgroundDrawable(new BitmapDrawable());
            }
        }
    }

    public Drawable getDrawableResource(int id) {
        return getResources().getDrawable(id);
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
        intent.setClass(OwnerFragment.this.getActivity(), target);
        if (bundle != null)
            intent.putExtra(OwnerFragment.this.getActivity().getPackageName(), bundle);
        startActivity(intent);
    }

    public interface ToolBarListener {
        void clickLeft();

        void clickRight();
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.owner_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
/*        logoutTV = (TextView) viewRoot.findViewById(R.id.login_out);
        logoutTV.setOnClickListener(clickListener);*/
    }

/*    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view != null) {
                switch (view.getId()) {
                    case R.id.login_out:
                        Presenter.getInstance(getContext()).steLogFlg(false);
                        getActivity().finish();
                        break;
                }
            }

        }
    };*/
}
