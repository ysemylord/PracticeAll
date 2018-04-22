package com.test.viewdemo.practiceEditText;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;

import com.test.viewdemo.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 目的：
 * 使用富文本，将文字中表示表示表情的文字替换为表情图片
 * 涉及的知识：
 * 1.富文本
 * 2.正则表达式
 */
public class EmotionTextViewActivity extends AppCompatActivity {

    @Bind(R.id.textview)
    TextView mTextview;
    private static final String TAG = "EmotionTextViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_text_view);
        ButterKnife.bind(this);

        String inputString="[大笑]附近[大哭]的飞机[大哭]防守打法[机器人]";

        Map<String ,Integer> textToRes=getTextToRes();

        SpannableStringBuilder spannableStringBuilder = replaceEmotionText(inputString, textToRes);

        mTextview.setText(spannableStringBuilder);

    }

    @NonNull
    private SpannableStringBuilder replaceEmotionText(String inputString, Map<String, Integer> textToRe) {
        String text=inputString;
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(text);
        Pattern pattern= Pattern.compile(getEmotionReg());
        Matcher matcher=pattern.matcher(text);
        while(matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            Integer imgRes = textToRe.get(matcher.group());
            Drawable drawable=getResources().getDrawable(imgRes);
            drawable.setBounds(0,0,50,50);
            spannableStringBuilder.setSpan(new ImageSpan(drawable),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Log.i(TAG, "find"+ start +"-"+ end);
        }
        return spannableStringBuilder;
    }

    private Map<String, Integer> getTextToRes() {
        Map<String, Integer> textToRe=new HashMap<>();
        textToRe.put("[大笑]", R.drawable.glad);
        textToRe.put("[大哭]",R.drawable.cry);
        textToRe.put("[机器人]",R.drawable.robot);
        return textToRe;
    }

    public String getEmotionReg() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(Pattern.quote("[大笑]"));
        stringBuilder.append("|");
        stringBuilder.append(Pattern.quote("[大哭]"));
        stringBuilder.append("|");
        stringBuilder.append(Pattern.quote("[机器人]"));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
