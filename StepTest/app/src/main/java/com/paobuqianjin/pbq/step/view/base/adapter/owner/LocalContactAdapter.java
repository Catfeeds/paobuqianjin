package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/3/2.
 */
/*
@className :LocalContactAdapter
*@date 2018/3/2
*@author
*@description 本地联系人
*/
public class LocalContactAdapter extends RecyclerView.Adapter<LocalContactAdapter.LocalContactViewHolder> {
    private final static String TAG = LocalContactAdapter.class.getSimpleName();
    Context context;
    List<Map<String, String>> mData;


    public LocalContactAdapter(Context context, List<Map<String, String>> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(LocalContactViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(LocalContactViewHolder holder, int position) {
        Map<String, String> stringMap = mData.get(position);
        for (String key : stringMap.keySet()) {
            if (key.equals("name")) {
                holder.dearName.setText(stringMap.get(key));
            }else if(key.equals("phone")){
                holder.phoneNum =  stringMap.get(key);
            }
        }
    }

    @Override
    public LocalContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocalContactViewHolder(LayoutInflater.from(context).inflate(R.layout.local_constract_list_item, parent, false));
    }

    public class LocalContactViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_near_icon)
        CircleImageView userNearIcon;
        @Bind(R.id.dear_name)
        TextView dearName;
        @Bind(R.id.bt_follow)
        Button btFollow;
        String phoneNum;

        public LocalContactViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            userNearIcon = (CircleImageView) view.findViewById(R.id.user_near_icon);
            dearName = (TextView) view.findViewById(R.id.dear_name);
            btFollow = (Button) view.findViewById(R.id.bt_follow);
            btFollow.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_follow:
                        LocalLog.d(TAG, "邀请" + phoneNum);
                        break;
                }
            }
        };
    }
}
