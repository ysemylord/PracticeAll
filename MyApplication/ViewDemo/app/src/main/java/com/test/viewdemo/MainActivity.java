package com.test.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   ImageView circle_drawable_test= (ImageView) findViewById(R.id.circle_drawable_test);
        ExpandingCircleAnimationDrawable expandingCircleAnimationDrawable= new ExpandingCircleAnimationDrawable(50f);
        circle_drawable_test.setImageDrawable(expandingCircleAnimationDrawable);
        expandingCircleAnimationDrawable.start();*/
        final String[] datas = new String[]{"大家好发斯蒂芬方式发生防守打法地方发生大幅度是分散斯蒂芬", "神范德萨发的地方雷", "神兵天降"};
   /*     final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.text_container);
        final TextView textView = new TextView(this);
        int textWidth= (int) textView.getPaint().measureText(datas[0]);
        textView.setText(datas[0]);
        textView.setBackgroundColor(Color.RED);
        linearLayout.addView(textView);
        textView.getLayoutParams().width=textWidth;

        new Thread(new Runnable() {
            int mNowTran = 0;
            int index = 0;

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "getX(): " + textView.getX());
                            Log.i(TAG, "getWidth（）: " + textView.getWidth());

                            textView.setTranslationX(textView.getX() - 1);//相对于left的平移
                            Log.i(TAG, "左移");
                            if (textView.getX() <= -textView.getWidth()) {
                                textView.setX(linearLayout.getWidth());
                                mNowTran = 0;
                                if (index == datas.length - 1) {
                                    index = 0;
                                } else {
                                    index++;
                                }
                                textView.setText(datas[index]);
                                Log.i(TAG, "放到右边");
                            }
                        }
                    });


                }
            }
        }).start();*/


    }
}
