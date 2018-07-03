package com.test.viewdemo.learnStructure.mvvm;

import android.databinding.ObservableField;
import android.widget.EditText;

import com.test.viewdemo.learnStructure.SearchModel;
import com.test.viewdemo.learnStructure.mvc.SearchRes;

/**
 * Created by xuyabo on 2018/7/3.
 */

public class SearchViewModel {
    public ObservableField<String> mTitle=new ObservableField<>("");
    public ObservableField<String> mContent=new ObservableField<>("");
    private SearchModel mSearchModel=new SearchModel();
    public void onSearch(EditText editText){
       SearchRes searchRes= mSearchModel.search(editText.getText().toString());
       mTitle.set(searchRes.getTitle());
       mContent.set(searchRes.getContent());
    }

}
