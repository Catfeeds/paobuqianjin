package com.paobuqianjin.pbq.step.customview;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by Administrator on 2018/5/7.
 */

public class LimitTextWatcher implements TextWatcher {
    private int editStart;
    private int editEnd;
    private int maxLen = 32; // 16 个汉字 32 个英文字符
    private EditText editText;

    public LimitTextWatcher(EditText e) {
        editText = e;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        LocalLog.d("slack", "afterTextChanged..." + s);
        editStart = editText.getSelectionStart();
        editEnd = editText.getSelectionEnd();
        // 先去掉监听器，否则会出现栈溢出
        editText.removeTextChangedListener(this);
        if (!TextUtils.isEmpty(s.toString())) {
            while (calculateLength(s.toString()) > maxLen) {
                s.delete(--editStart, editEnd--);
                LocalLog.d("slack", "editStart = " + editStart + " editEnd = " + editEnd + " s = " + s);
            }
        }

        editText.setText(s);
        editText.setSelection(editStart);
        // 恢复监听器
        editText.addTextChangedListener(this);
    }

    /**
     * 英文一个字符  中文两个字符
     */
    private int calculateLength(String string) {
        char[] ch = string.toCharArray();

        int varlength = 0;
        for (char c : ch) {
            // changed by zyf 0825 , bug 6918，加入中文标点范围 ， TODO 标点范围有待具体化
            if ((c >= 0x2E80 && c <= 0xFE4F) || (c >= 0xA13F && c <= 0xAA40) || c >= 0x80) { // 中文字符范围0x4e00 0x9fbb
//                    if (c >= 0x4E00 && c <= 0x9FBB) { // 中文字符范围 0x4E00-0x9FA5 + 0x9FA6-0x9FBB
                varlength = varlength + 2;
            } else {
                varlength++;
            }
        }
        LocalLog.d("slack", "length : " + varlength + " l: " + string.length());
        return varlength;
    }
}
