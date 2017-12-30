package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/30.
 */

public class LikeSupportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = LikeSupportAdapter.class.getSimpleName();

    private Context mContext;
    private final static int defaultCount = 7;

    public LikeSupportAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeSupportViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.live_support_list, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    public class LikeSupportViewHolder extends RecyclerView.ViewHolder {
        //@Bind(R.id.circle_logo_search)
        CircleImageView circleLogoSearch;
        //@Bind(R.id.search_circle_des_list_name)
        TextView searchCircleDesListName;
        //@Bind(R.id.love_number)
        Button loveNumber;

        public LikeSupportViewHolder(View view, int viewType) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            circleLogoSearch = (CircleImageView) view.findViewById(R.id.circle_logo_search);
            searchCircleDesListName = (TextView) view.findViewById(R.id.search_circle_des_list_name);
            loveNumber = (Button) view.findViewById(R.id.love_number);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }
}
