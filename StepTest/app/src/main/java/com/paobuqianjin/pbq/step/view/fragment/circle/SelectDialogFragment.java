package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

/**
 * Created by pbq on 2017/12/19.
 */

public class SelectDialogFragment extends DialogFragment {
    private final static String TAG = SelectDialogFragment.class.getSimpleName();
    public final static String SELECT_TYPE = "select_type";
    private static SelectDialogFragment selectDialogFragment;
    private static List<String> mList;
    private static UiCreateCircleInterface mUiCreateCircleInterface;
    private TextView bt_cancelTV, bt_confirmTV;
    private RecyclerView selectRecycler;
    private LinearLayoutManager selectManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LocalLog.d(TAG, "onCreateView() enter");
        View rootView = inflater.inflate(R.layout.select_dialog_layout, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        if (rootView != null) {
            bt_cancelTV = (TextView) rootView.findViewById(R.id.cancel);
            bt_confirmTV = (TextView) rootView.findViewById(R.id.confirm);
            bt_cancelTV.setOnClickListener(onClickListener);
            bt_confirmTV.setOnClickListener(onClickListener);

            selectRecycler = (RecyclerView) rootView.findViewById(R.id.select_recycler_view);
            selectManager = new LinearLayoutManager(this.getActivity());
            selectRecycler.setLayoutManager(selectManager);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.MatchParentDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wpl = window.getAttributes();
        wpl.gravity = Gravity.BOTTOM;
        wpl.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wpl);

        return dialog;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view != null) {
                switch (view.getId()) {
                    case R.id.cancel:
                        break;
                    case R.id.confirm:
                        break;
                    default:
                        break;
                }
            }
        }
    };

}
