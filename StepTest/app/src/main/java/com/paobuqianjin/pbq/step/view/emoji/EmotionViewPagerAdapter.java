package com.paobuqianjin.pbq.step.view.emoji;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;

import java.util.List;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionLayout.EMOJI_PER_PAGE;

/**
 * CSDN_LQR
 * 表情控件的ViewPager适配器(emoji + 贴图)
 */

public class EmotionViewPagerAdapter extends PagerAdapter {
    private final static String TAG = EmotionViewPagerAdapter.class.getSimpleName();

    int mPageCount = 0;
    int mTabPosi = 0;

    private int mEmotionLayoutWidth;
    private int mEmotionLayoutHeight;

    private IEmotionSelectedListener listener;
    CustomEdit mMessageEditText;
    private int[] emjCode;

    public void attachEditText(CustomEdit messageEditText) {
        mMessageEditText = messageEditText;
    }

    public EmotionViewPagerAdapter(int emotionLayoutWidth, int emotionLayoutHeight, int tabPosi, IEmotionSelectedListener listener) {
        mEmotionLayoutWidth = emotionLayoutWidth;
        mEmotionLayoutHeight = emotionLayoutHeight;
        mTabPosi = tabPosi;

        if (mTabPosi == 0)
            mPageCount = (int) Math.ceil(80 / (float) EMOJI_PER_PAGE);
        else
            mPageCount = (int) Math.ceil(StickerManager.getInstance().getStickerCategories().get(mTabPosi - 1).getStickers().size() / (float) EmotionLayout.STICKER_PER_PAGE);

        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mPageCount == 0 ? 1 : mPageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.e("####", "instantiateItem() enter");
        Context context = container.getContext();
        emjCode = context.getResources().getIntArray(R.array.emjio_list);
        RelativeLayout rl = new RelativeLayout(context);
        rl.setGravity(Gravity.CENTER);

        GridView gridView = new GridView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        gridView.setLayoutParams(params);
        gridView.setGravity(Gravity.CENTER);

        gridView.setTag(position);//标记自己是第几页
        if (mTabPosi == 0) {
            gridView.setOnItemClickListener(emojiListener);
            gridView.setAdapter(new AndroidIosAdapter(context, mEmotionLayoutWidth, mEmotionLayoutHeight, position * EMOJI_PER_PAGE));
            gridView.setNumColumns(EmotionLayout.EMOJI_COLUMNS);
        } else {
/*            StickerCategory category = StickerManager.getInstance().getCategory(StickerManager.getInstance().getStickerCategories().get(mTabPosi - 1).getName());
            gridView.setOnItemClickListener(stickerListener);
            gridView.setAdapter(new StickerAdapter(context, category, mEmotionLayoutWidth, mEmotionLayoutHeight, position * STICKER_PER_PAGE));
            gridView.setNumColumns(EmotionLayout.STICKER_COLUMNS);*/
        }

        rl.addView(gridView);
        container.addView(rl);
        return rl;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //使用1字节就可以表示b
    public static String numToHex8(int b) {
        return String.format("%02x", b);//2表示需要两个16进行数
    }

    //需要使用2字节表示b
    public static String numToHex16(int b) {
        return String.format("%04x", b);
    }

    //需要使用4字节表示b
    public static String numToHex32(int b) {
        return String.format("%08x", b);
    }

    public AdapterView.OnItemClickListener emojiListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            int index = position + (Integer) parent.getTag() * EMOJI_PER_PAGE;
            int count = 80;
            if (position == EMOJI_PER_PAGE || index >= count) {
                if (listener != null) {
                    listener.onEmojiSelected("/DEL");
                }
                onEmojiSelected("/DEL");
            } else {
                Log.e(TAG, "id = " + id);
                String text = "";
                if (id < emjCode.length) {
                    text += "[0x" + numToHex8(emjCode[(int) id]) + "]";
                }

                LocalLog.d(TAG, text);
                if (!TextUtils.isEmpty(text)) {
                    if (listener != null) {
                        listener.onEmojiSelected(text);
                    }
                    onEmojeSelected(text, emjCode[(int) id]);
                }
            }
        }
    };


    public AdapterView.OnItemClickListener stickerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            StickerCategory category = StickerManager.getInstance().getStickerCategories().get(mTabPosi - 1);
            List<StickerItem> stickers = category.getStickers();
            int index = position + (Integer) parent.getTag() * EmotionLayout.STICKER_PER_PAGE;

            if (index >= stickers.size()) {
                Log.i("CSDN_LQR", "index " + index + " larger than size " + stickers.size());
                return;
            }

            if (listener != null) {
                StickerItem sticker = stickers.get(index);
                StickerCategory real = StickerManager.getInstance().getCategory(sticker.getCategory());

                if (real == null) {
                    return;
                }

                listener.onStickerSelected(sticker.getCategory(), sticker.getName(), StickerManager.getInstance().getStickerBitmapPath(sticker.getCategory(), sticker.getName()));
            }
        }
    };

    private void onEmojeSelected(String key, int id) {
        LocalLog.d(TAG, "onEmojeSelected() enter");
        if (mMessageEditText == null)
            return;
        Editable editable = mMessageEditText.getText();
        LocalLog.d(TAG, "TEXT =  " + editable.toString());
        if (key.equals("/DEL")) {
            mMessageEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        } else {
            int start = mMessageEditText.getSelectionStart();
            int end = mMessageEditText.getSelectionEnd();
            start = (start < 0 ? 0 : start);
            end = (start < 0 ? 0 : end);
            LocalLog.d(TAG, "start = " + start + "end = " + end);
            ((CustomEdit) mMessageEditText).setTranslateEmotion(editable.toString() + key);
            editable.replace(start, end, getEmojiStringByUnicode(id));

            int editEnd = mMessageEditText.getSelectionEnd();
            LocalLog.d(TAG, "key =" + key + "id = " + id);
            MoonUtils.replaceEmoticons(LQREmotionKit.getContext(), editable, 0, editable.toString().length(), id);
            mMessageEditText.setSelection(editEnd);
        }
    }


    public static String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void onEmojiSelected(String key) {
        if (mMessageEditText == null)
            return;
        Editable editable = mMessageEditText.getText();
        if (key.equals("/DEL")) {
            mMessageEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        } else {
            int start = mMessageEditText.getSelectionStart();
            int end = mMessageEditText.getSelectionEnd();
            start = (start < 0 ? 0 : start);
            end = (start < 0 ? 0 : end);
            editable.replace(start, end, key);

            int editEnd = mMessageEditText.getSelectionEnd();
            LocalLog.d(TAG, "key =" + key);
            MoonUtils.replaceEmoticons(LQREmotionKit.getContext(), editable, 0, editable.toString().length(), -1);
            mMessageEditText.setSelection(editEnd);
        }
    }
}
