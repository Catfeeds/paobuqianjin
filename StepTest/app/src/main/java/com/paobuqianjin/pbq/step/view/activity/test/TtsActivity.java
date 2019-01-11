package com.paobuqianjin.pbq.step.view.activity.test;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2019/1/11.
 */

public class TtsActivity extends BaseBarActivity implements View.OnClickListener {
    private final static String TAG = TtsActivity.class.getSimpleName();
    @Bind(R.id.test)
    EditText test;
    @Bind(R.id.speak)
    TextView speak;
    private TextToSpeech textToSpeech = null;

    @Override
    protected String title() {
        return "TTS";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_layout);
        ButterKnife.bind(this);
        initTTS();
    }

    @Override
    public void onClick(View v) {
        startAuto(test.getText().toString().trim());
    }

    private void initTTS() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    textToSpeech.setPitch(1.0f);
                    textToSpeech.setSpeechRate(1.0f);

                    int result1 = textToSpeech.setLanguage(Locale.US);
                    int result2 = textToSpeech.setLanguage(Locale.SIMPLIFIED_CHINESE);

                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);

                    LocalLog.d(TAG, "US支持否？" + a + "\nzh-cn支持否" + b);
                } else {
                    PaoToastUtils.showLongToast(TtsActivity.this, "数据丢失或不支持！");
                }
            }
        });
    }

    private void startAuto(String data) {
        textToSpeech.setPitch(1.0f);
        textToSpeech.setSpeechRate(1.0f);
        textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }
}
