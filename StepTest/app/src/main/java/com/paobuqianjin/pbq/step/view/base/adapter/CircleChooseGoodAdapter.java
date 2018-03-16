package com.paobuqianjin.pbq.step.view.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import com.paobuqianjin.pbq.step.presenter.im.JoinCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/13.
 */

public class CircleChooseGoodAdapter extends RecyclerView.Adapter<CircleChooseGoodAdapter.CircleChooseViewHolder> {
    private final static String TAG = CircleChooseGoodAdapter.class.getSimpleName();

    private Context mContext;
    private final static int defaultCount = 3;
    private ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data = new ArrayList<>();
    private ChoiceCircleResponse.DataBeanX.DataBean tmpData;
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    private TranslateAnimation animationCircleType;
    private Activity activity;
    EditText passEdit;
    ImageView lineMid;
    TextView cancelText;
    TextView confirmText;
    RelativeLayout partTwo;
    private JoinCircleParam joinCircleParam;
    private final static String ACTION_ENTER_ICON = "coma.paobuqian.pbq.step.ICON_ACTION";

    public CircleChooseGoodAdapter(final Activity activity, Context context, ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> data) {
        super();
        this.activity = activity;
        mContext = context;
        this.data = data;
    }

    @Override
    public CircleChooseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CircleChooseViewHolder holder = new CircleChooseViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.circle_choose_list,
                        parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(CircleChooseViewHolder holder, int position) {
        updateCircleList(holder, position);
    }

    private void updateCircleList(CircleChooseViewHolder holder, int position) {
        LocalLog.d(TAG, "updateCircleList() enter" + data.get(position).toString());

        tmpData = data.get(position);
        LocalLog.d(TAG, "city = " + tmpData.getCity() +
                ", name =" + tmpData.getName() + "logo url = " + tmpData.getLogo() + " ,member_number ="
                + tmpData.getMember_number());
        Presenter.getInstance(mContext).getImage(holder.circleLogo, tmpData.getLogo());
        holder.circleLocation.setText(tmpData.getCity());
        holder.circleName.setText(tmpData.getName());
        String sAgeFormat = mContext.getResources().getString(R.string.member_number);
        String sFinalMember = String.format(sAgeFormat, tmpData.getMember_number());
        holder.circleNumDesc.setText(sFinalMember);
        if (tmpData.getIs_pwd() == 1) {
            holder.lock.setVisibility(View.VISIBLE);
            holder.is_pwd = true;
        }
        if (tmpData.getIs_join() == 0) {
            LocalLog.d(TAG, "未在该圈可以加入");
            holder.circleJoin.setText("加入");
            holder.circleJoin.setOnClickListener(holder.onClickListener);
        } else if (tmpData.getIs_join() == 1) {
            holder.circleJoin.setText("已加入");
        }
        holder.setCircleId(tmpData.getCircleid());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class CircleChooseViewHolder extends RecyclerView.ViewHolder {
        boolean getIs_pwd() {
            return is_pwd;
        }

        void setIs_pwd(boolean is_pwd) {
            this.is_pwd = is_pwd;
        }

        boolean is_pwd;

        public int getCircleId() {
            return circleId;
        }

        int circleId;

        ImageView circleLogo;

        TextView circleLocation;

        RelativeLayout circleLog;

        TextView circleName;

        TextView circleNumDesc;

        RelativeLayout circleChooseDesc;

        Button circleJoin;

        ImageView lock;


        public CircleChooseViewHolder(View view) {
            super(view);
            init(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, CirCleDetailActivity.class);
                    intent.setAction(ACTION_ENTER_ICON);
                    intent.putExtra(mContext.getPackageName() + "circleid",String.valueOf(getCircleId()));
                    mContext.startActivity(intent);
                }
            });
        }

        private void init(View view) {
            circleLogo = (ImageView) view.findViewById(R.id.circle_logo);
            circleLocation = (TextView) view.findViewById(R.id.circle_location);
            circleName = (TextView) view.findViewById(R.id.circle_name);
            circleNumDesc = (TextView) view.findViewById(R.id.circle_num_desc);
            circleJoin = (Button) view.findViewById(R.id.circle_join);
            lock = (ImageView) view.findViewById(R.id.lock);
            Presenter.getInstance(mContext).attachUiInterface(joinCircleInterface);

        }


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

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new

                    AccelerateInterpolator());
            animationCircleType.setDuration(200);

            popupOpWindow.showAtLocation(activity.findViewById(R.id.main_activity_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            popCircleOpBar.startAnimation(animationCircleType);
        }

        private JoinCircleInterface joinCircleInterface = new JoinCircleInterface() {
            @Override
            public void response(JoinCircleResponse joinCircleResponse) {
                if (joinCircleResponse.getError() == 0) {
                    LocalLog.d(TAG, "加入成功");
                    circleJoin.setText("已加入");
                } else if (joinCircleResponse.getError() == -1) {
                    Toast.makeText(mContext, joinCircleResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
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

        public JoinCircleInterface getJoinCircleInterface() {
            return joinCircleInterface;
        }

        private void setJoinCircleInterface(JoinCircleInterface joinCircleInterface) {
            this.joinCircleInterface = joinCircleInterface;
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.circle_join:
                        switch (circleJoin.getText().toString()) {
                            case "加入":
                                LocalLog.d(TAG, "点击加入+++");
                                if (joinCircleParam == null) {
                                    joinCircleParam = new JoinCircleParam();
                                }
                                joinCircleParam.setCircleid(circleId);
                                if (is_pwd) {
                                    LocalLog.d(TAG, "输入密码");
                                    popPassWordEdit();
                                    return;
                                } else {

                                }
                                Presenter.getInstance(mContext).joinCircle(joinCircleParam);
                                break;
                        }
                        break;
                    case R.id.confirm_text:
                        LocalLog.d(TAG, "确定");
                        if (popupOpWindow != null) {
                            joinCircleParam.setPassword(passEdit.getText().toString());
                            popupOpWindow.dismiss();
                        }
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

        void setCircleId(int circleId) {
            this.circleId = circleId;
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else {
                outRect.bottom = mSpace;
            }
            if (parent.getChildAdapterPosition(view) == defaultCount - 1) {
/*                outRect.bottom = 0;
                LocalLog.d(TAG, "getItemOffsets() last set");*/
            } else {
                outRect.bottom = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
