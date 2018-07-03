package com.test.viewdemo.learnStructure.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.viewdemo.R;
import com.test.viewdemo.learnStructure.SearchModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVCDemoActivity extends AppCompatActivity {

    @Bind(R.id.key_edit_text)
    EditText mKeyEditText;
    @Bind(R.id.search_btn)
    Button mSearchBtn;
    @Bind(R.id.res_textview)
    TextView mResTextview;
    SearchModel mSearchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strcut_view_demo);
        ButterKnife.bind(this);
        mSearchModel=new SearchModel();
    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {
        String key=mKeyEditText.getText().toString().trim();
        SearchRes searchRes=mSearchModel.search(key);
        showRes(searchRes);

    }

    /**
     * 这一部分的内容应该放在View层中
     * 但是现在放在了Controller层中
     * @param searchRes
     */
    private void showRes(SearchRes searchRes){
        mResTextview.setText(searchRes.getTitle()+"\n"+searchRes.getContent());
    }
}
