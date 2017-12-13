package com.paobuqianjin.pbq.step.view.base;

/**
 * Created by pbq on 2017/12/13.
 */

public abstract class BaseBarActivity extends BaseActivity {
    private ToolBarListener mToolBarListener;

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

    public void setToolBarListener() {

    }

    public interface ToolBarListener {
        void clickLeft();

        void clickRight();
    }
}
