package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InOutCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.utils.LoadBitmap;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleAdapter extends RecyclerView.Adapter<SearchCircleAdapter.SearchCirCleViewHolder> {
    private final static String TAG = SearchCircleAdapter.class.getSimpleName();
    private final static int defaultValue = 15;
    private Context mContext;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data;
    private ChoiceCircleResponse.DataBeanX.DataBean tmpData;
    private JoinCircleParam joinCircleParam;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private TranslateAnimation animationCircleType;
    private Activity activity;
    EditText passEdit;
    ImageView lineMid;
    TextView cancelText;
    TextView confirmText;
    RelativeLayout partTwo;
    InOutCallBackInterface inOutCallBackInterface;
    private final static int REQUEST_DETAIL = 278;
    private final static String ACTION_ENTER_ICON = "coma.paobuqian.pbq.step.ICON_ACTION";
    private Fragment fragment;

    public SearchCircleAdapter(Context context, final Activity activity, InOutCallBackInterface inOutCallBackInterface, final Fragment fragment) {
        super();
        mContext = context;
        this.activity = activity;
        this.inOutCallBackInterface = inOutCallBackInterface;
        this.fragment = fragment;
    }


    public void notifyDataSetChanged(ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        this.data = data;
        super.notifyDataSetChanged();
    }

    @Override
    public SearchCirCleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchCirCleViewHolder holder = new SearchCirCleViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.circle_kan_ban_list, parent, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(SearchCirCleViewHolder holder, int position) {
        updateCircleList(holder, position);
    }

    private void updateCircleList(SearchCirCleViewHolder holder, int position) {
        LocalLog.d(TAG, "updateCircleList() enter" + data.get(position).toString());
        tmpData = data.get(position);
        LocalLog.d(TAG, "city = " + tmpData.getCity() +
                ", name =" + tmpData.getName() + "logo url = " + tmpData.getLogo() + " ,member_number ="
                + tmpData.getMember_number());
        /*Presenter.getInstance(mContext).getImage(holder.circleLogoSearch, tmpData.getLogo());*/
        LoadBitmap.glideLoad(activity, holder.circleLogoSearch, tmpData.getLogo());
        holder.locationDescSearchList.setText(tmpData.getCity());
        holder.searchCircleDesListName.setText(tmpData.getName());
        String sAgeFormat = mContext.getResources().getString(R.string.member_number);
        String sFinalMember = String.format(sAgeFormat, tmpData.getMember_number());
        holder.searchCircleDesListNum.setText(sFinalMember);
        holder.circleMember = tmpData.getMember_number();
        if (tmpData.getIs_pwd() == 1) {
            holder.lock.setVisibility(View.VISIBLE);
            holder.needPass = true;
        }
        if (tmpData.getIs_join() == 0) {
            holder.joinIn.setText("加入");
            holder.isJoin = false;
        } else if (tmpData.getIs_join() == 1) {
            holder.joinIn.setText("已加入");
            holder.isJoin = true;
        }
        holder.circleId = tmpData.getCircleid();

        if (tmpData.getIs_pwd() == 1) {
            holder.is_password = true;
        }
    }


    class SearchCirCleViewHolder extends RecyclerView.ViewHolder {
        boolean needPass;
        ImageView circleLogoSearch;
        TextView searchCircleDesListName;
        ImageView lock;
        TextView searchCircleDesListNum;
        TextView locationDescSearchList;
        Button joinIn;
        int circleId;
        boolean is_password;
        int circleMember = 0;
        boolean isJoin = false;

        public SearchCirCleViewHolder(View view) {
            super(view);
            init(view);
        }


        private JoinCircleInterface joinCircleInterface = new JoinCircleInterface() {
            @Override
            public void response(JoinCircleResponse joinCircleResponse) {
                if (joinCircleResponse.getError() == 0) {
                    LocalLog.d(TAG, "加入成功");
                    joinIn.setText("已加入");
                    isJoin = true;
                    if (inOutCallBackInterface != null) {
                        inOutCallBackInterface.inCallBack(circleId);
                    }
                    String sAgeFormat = mContext.getResources().getString(R.string.member_number);
                    String sFinalMember = String.format(sAgeFormat, circleMember + 1);
                    searchCircleDesListNum.setText(sFinalMember);
                } else if (joinCircleResponse.getError() == -1) {
                    Toast.makeText(mContext, joinCircleResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Presenter.getInstance(mContext).dispatchUiInterface(joinCircleInterface);
            }

            @Override
            public void response(ErrorCode errorCode) {
                if (errorCode.getMessage().equals("请输入密码")) {
                    popPassWordEdit();
                } else {
                    Toast.makeText(mContext, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        private void init(View view) {
            circleLogoSearch = (ImageView) view.findViewById(R.id.circle_logo_search);
            circleLogoSearch.setOnClickListener(onClickListener);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            lock = (ImageView) view.findViewById(R.id.lock);
            searchCircleDesListNum = (TextView) view.findViewById(R.id.search_circle_des_list_num);
            locationDescSearchList = (TextView) view.findViewById(R.id.location_desc_search_list);
            joinIn = (Button) view.findViewById(R.id.join_in);
            joinIn.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_logo_search:
                        if (is_password && !isJoin) {
                            LocalLog.d(TAG, "需要密码");
                            popPassWordEdit();
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setClass(mContext, CirCleDetailActivity.class);
                        intent.setAction(ACTION_ENTER_ICON);
                        LocalLog.d(TAG, "getAdapterPosition() = " + getAdapterPosition());
                        intent.putExtra(mContext.getPackageName() + "circleid", circleId);
                        intent.putExtra(mContext.getPackageName() + "position", getAdapterPosition());
                        fragment.startActivityForResult(intent, REQUEST_DETAIL);
                        break;
                    case R.id.join_in:
                        switch (joinIn.getText().toString()) {
                            case "加入":
                                LocalLog.d(TAG, "加入圈子");
                                if (joinCircleParam == null) {
                                    joinCircleParam = new JoinCircleParam();

                                }
                                joinCircleParam.setCircleid(circleId);
                                if (is_password) {
                                    LocalLog.d(TAG, "需要密码");
                                    popPassWordEdit();
                                    return;
                                }
                                Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);
                                Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                                break;
                            default:
                                break;
                        }
                        break;
                    case R.id.confirm_text:
                        LocalLog.d(TAG, "确定");
                        if (popupOpWindow != null) {
                            if (joinCircleParam == null) {
                                joinCircleParam = new JoinCircleParam();
                            }
                            joinCircleParam.setPassword(passEdit.getText().toString());
                            joinCircleParam.setCircleid(circleId);
                            popupOpWindow.dismiss();
                        }
                        Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);
                        Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                        break;
                    case R.id.cancel_text:
                        LocalLog.d(TAG, "取消");
                        if (popupOpWindow != null) {
                            popupOpWindow.dismiss();
                        }
                        break;
                    default:
                        break;
                }
            }
        };


        public void popPassWordEdit() {
            LocalLog.d(TAG, "popPassWordEdit() enter 弹出密码输入框");
            popCircleOpBar = View.inflate(mContext, R.layout.pass_word_layout, null);
            popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    popupOpWindow = null;
                }
            });

            passEdit = (EditText) popCircleOpBar.findViewById(R.id.pass_edit);
            passEdit.setOnClickListener(onClickListener);
            cancelText = (TextView) popCircleOpBar.findViewById(R.id.cancel_text);
            cancelText.setOnClickListener(onClickListener);
            confirmText = (TextView) popCircleOpBar.findViewById(R.id.confirm_text);
            confirmText.setOnClickListener(onClickListener);


            popupOpWindow.setFocusable(true);
            popupOpWindow.setOutsideTouchable(true);
            popupOpWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new

                    AccelerateInterpolator());
            animationCircleType.setDuration(200);

            popupOpWindow.showAtLocation(activity.findViewById(R.id.search_hot_circle), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            popCircleOpBar.startAnimation(animationCircleType);
        }

    }

}
