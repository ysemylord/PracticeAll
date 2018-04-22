package com.test.viewdemo.viewPager.changeData;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viewdemo.BaseFragment;
import com.test.viewdemo.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TextFragment extends BaseFragment {
    private static final String TAG = "TextFragment";
    @Bind(R.id.textView1)
    TextView mTextView1;
    @Bind(R.id.textView2)
    TextView mTextView2;
    private String mText;
    Handler mHandler=new Handler();

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }


    public TextFragment() {
        Log.i(TAG, "TextFragment 创建 ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: "+getFragmentManager().getFragments().size());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        mTextView1.setText(mText);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {//fragment每次可见时调用

               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           Thread.sleep(100);//模拟网络请求
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       Log.i(TAG, "run: setUserVisibleHint true");
                       mHandler.post(new Runnable() {
                           @Override
                           public void run() {
                               Log.i(TAG, "run: setText");
                              mTextView2.setText("随机数"+new Random().nextInt(100)+"");
                           }
                       });
                   }
               }).start();
        } else {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}