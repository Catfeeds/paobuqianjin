package com.paobuqianjin.pbq.step.customview;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.baidu.mapapi.search.core.PoiInfo;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.SearchPositionAdapter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * on 2018/5/3.
 */

public class ChooseListPop<T> {

    private Activity context;
    private WindowManager.LayoutParams layoutParams;
    private PopupWindow popupWindow = null;
    private LinearLayout parentView;
    private SearchPositionAdapter adapter;
    private OnListSelectListener listener;
    private List<T> list = new ArrayList<>();
    private ListView listView;
    private boolean isLoad;

    public ChooseListPop(Activity context, OnListSelectListener listener) {
        this.context = context;
        this.listener = listener;
        init();
    }

    private void init() {
        layoutParams = context.getWindow().getAttributes();
        initView();
        initPopupWindow();
    }

    private void initView() {
        parentView = (LinearLayout) View.inflate(context, R.layout.layout_choose_list, null);
        listView = (ListView) parentView.findViewById(R.id.list);
        adapter = new SearchPositionAdapter(context, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelect(i);
                adapter.notifyDataSetChanged();
                listener.onListSelect(list.get(i));
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i + i1 == i2 && !isLoad) {
                    isLoad = true;
                    listener.onBottom();
                }
            }
        });
    }

    private void initPopupWindow() {
        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT, (int) (ProUtils.getScreenHeight(context) * 2.0 / 5));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.anim_push_bottom);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                layoutParams.alpha = 1.0f;
                context.getWindow().setAttributes(layoutParams);
                popupWindow.dismiss();
            }
        });
    }

    public void setData(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
        listView.setSelection(0);
        adapter.setSelect(-1);
        adapter.notifyDataSetChanged();
    }

    public void setLoad(boolean isLoad) {
        this.isLoad = isLoad;
    }


    public void setMoreData(List<T> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        isLoad = false;
    }

    public void show(View v) {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void cancel() {
        popupWindow.dismiss();
    }

    public interface OnListSelectListener<T> {
        void onListSelect(T info);

        void onBottom();
    }

}
