package com.paobuqianjin.pbq.step.view.emoji;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.Utils;

/**
 * Created by pbq on 2018/3/14.
 */

public class AndroidIosAdapter extends BaseAdapter {
    private Context mContext;
    private int mStartIndex;
    private int mEmotionLayoutWidth;
    private int mEmotionLayoutHeight;
    private final float mPerWidth;
    private final float mPerHeight;
    private final float mIvSize;
    private int[] emjCode;

    public AndroidIosAdapter(Context context, int emotionLayoutWidth, int emotionLayoutHeight, int startIndex) {
        mContext = context;
        mStartIndex = startIndex;
        mEmotionLayoutWidth = emotionLayoutWidth;
        mEmotionLayoutHeight = emotionLayoutHeight - LQREmotionKit.dip2px(35 + 26 + 50);//减去底部的tab高度、小圆点的高度才是viewpager的高度，再减少30dp是让表情整体的顶部和底部有个外间距

        mPerWidth = mEmotionLayoutWidth * 1f / EmotionLayout.EMOJI_COLUMNS;
        mPerHeight = mEmotionLayoutHeight * 1f / EmotionLayout.EMOJI_ROWS;
        float ivWidth = mPerWidth * .6f;
        float ivHeight = mPerHeight * .6f;
        mIvSize = Math.min(ivWidth, ivHeight);
        emjCode = mContext.getResources().getIntArray(R.array.emjio_list);
    }

    @Override
    public int getCount() {
        if (emjCode != null) {
            Log.e("####", "getCount() enter" + emjCode.length);
            int count = emjCode.length - mStartIndex + 1;
            count = Math.min(count, EmotionLayout.EMOJI_PER_PAGE + 1);
            return count;
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mStartIndex + position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        RelativeLayout rl = new RelativeLayout(mContext);
        rl.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) mPerHeight));
//        rl.setBackgroundColor(Color.RED);

        TextView emojiThumb = new TextView(mContext);
        emojiThumb.setGravity(Gravity.CENTER);
        int count = emjCode.length;
        int index = mStartIndex + position;
        if (position == EmotionLayout.EMOJI_PER_PAGE || index == count) {
            emojiThumb.setText("取消");
        } else if (index < count) {
            Log.e("####", "index = " + index + ",Count = " + count);
            emojiThumb.setText(Utils.getEmojiStringByUnicode(emjCode[index]));
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
  /*      layoutParams.width = (int) 20;
        layoutParams.height = (int) 20;*/
        emojiThumb.setTextSize(20);
        emojiThumb.setLayoutParams(layoutParams);

        rl.setGravity(Gravity.CENTER);
        rl.addView(emojiThumb);

        return rl;
    }

    public static String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
