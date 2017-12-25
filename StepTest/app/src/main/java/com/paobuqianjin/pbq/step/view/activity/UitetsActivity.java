package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.table.User;
import com.paobuqianjin.pbq.step.utils.LocalLog;

public class UitetsActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = UitetsActivity.class.getSimpleName();
    private RelativeLayout layout;
    private int index = 0;
    private int[] layer = {R.drawable.log_a, R.drawable.log_b, R.drawable.log_c, R.drawable.log_d, R.drawable.jinxuan};
    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_test_layout);
        layout = (RelativeLayout) findViewById(R.id.test_ui);
        layout.setOnClickListener(this);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.emjio_code);
        textView = (TextView) findViewById(R.id.show_apple);
        button = (Button) findViewById(R.id.change_emjio);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.change_emjio:
   /*                     String src = editText.toString();
                        LocalLog.d(TAG, "src=" + src);
                        Integer in = Integer.valueOf(src, 16);
                        LocalLog.d(TAG, "in = " + in);*/
                        String emojiString = getEmojiStringByUnicode(0xE001);
                        LocalLog.d(TAG, "emojiString = " + emojiString);
                        textView.setText(emojiString);
                        break;
                }
            }
        });
    }


    private String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    @TargetApi(21)
    @Override
    public void onClick(View view) {

        Drawable drawable = null;
        if (index % 5 == 0) {
            drawable = getDrawable(layer[0]);

        }
        if (index % 5 == 1) {
            drawable = getDrawable(layer[1]);
        }
        if (index % 5 == 2) {
            drawable = getDrawable(layer[2]);
        }
        if (index % 5 == 3) {
            drawable = getDrawable(layer[3]);
        }
        if (index % 5 == 4) {
            drawable = getDrawable(layer[4]);
        }
        layout.setBackground(drawable);
        index++;

    }
}
