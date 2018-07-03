package com.test.viewdemo.learnStructure.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.viewdemo.R;
import com.test.viewdemo.learnStructure.mvc.SearchRes;
import com.test.viewdemo.learnStructure.mvp.contact.SearchContact;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuyabo on 2018/7/3.
 * Fragment+xml布局文件充当View
 */

public class SearchView extends Fragment implements SearchContact.SearchView {
    @Bind(R.id.key_edit_text)
    EditText mKeyEditText;
    @Bind(R.id.search_btn)
    Button mSearchBtn;
    @Bind(R.id.res_textview)
    TextView mResTextview;
    SearchContact.SearchPresenter mSearchPresenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mvcdemo, container, false);
        ButterKnife.bind(this, view);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchClick();
            }
        });
        return view;
    }

    @Override
    public String getSearchKey() {
        return mKeyEditText.getText().toString();
    }

    @Override
    public void onSearchClick() {
        mSearchPresenter.search();
    }

    @Override
    public void showRes(SearchRes searchRes) {
        mResTextview.setText(searchRes.getTitle()+"\n"+searchRes.getContent());
    }

    @Override
    public void setPresenter(SearchContact.SearchPresenter searchPresenter) {
        mSearchPresenter=searchPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
