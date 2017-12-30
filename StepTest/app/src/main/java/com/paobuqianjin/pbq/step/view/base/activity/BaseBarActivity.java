package com.paobuqianjin.pbq.step.view.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2017/12/13.
 */

public abstract class BaseBarActivity extends BaseActivity {
    private ToolBarListener mToolBarListener;
    private TextView tv_title;
    private ImageView tv_left;
    private TextView tv_right;
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

    public ToolBarListener setToolBarListener() {
        return null;
    }

    public void initBarView() {
        tv_title = getView(R.id.bar_title);
        tv_right = getView(R.id.bar_tv_right);
        tv_left = getView(R.id.bar_return_drawable);
        rv_left = getView(R.id.button_return_bar);
        setToolBarListener();
        tv_left.setOnClickListener(clickListener);
        rv_left.setOnClickListener(clickListener);
        tv_right.setOnClickListener(clickListener);
        refreshTop();
    }

    public void setToolBarListener(ToolBarListener toolBarListener) {
        mToolBarListener = toolBarListener;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bar_return_drawable:
                case R.id.button_return_bar:
                    if (mToolBarListener == null) {
                        finish();
                    } else {
                        //mToolBarListener.clickLeft();
                        finish();
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

    protected void refreshTop() {
        setLeftView(left() == null ? R.drawable.bar_bg_selector : left());
        setRightValue(R.id.bar_tv_right, right());
        tv_title.setText(title());
    }

    protected void setRightValue(int id, Object obj) {
        if (obj != null && !obj.equals("")) {
            ((TextView) getView(id)).setText("");
            getView(id).setBackgroundDrawable(new BitmapDrawable());
            if (obj instanceof String) {
                ((TextView) getView(id)).setText(obj.toString());
            } else if (obj instanceof Integer) {
                getView(id).setBackgroundResource(Integer.parseInt(obj.toString()));
            } else {
                ((TextView) getView(id)).setText("");
                getView(id).setBackgroundDrawable(new BitmapDrawable());
            }
        }
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

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 启动指定Activity
     *
     * @param target
     * @param bundle
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(this.getPackageName(), bundle);
        startActivity(intent);
    }

    public interface ToolBarListener {
        void clickLeft();

        void clickRight();
    }
}
