package com.test.viewdemo.learnStructure.mvp;

import com.test.viewdemo.learnStructure.SearchModel;
import com.test.viewdemo.learnStructure.mvc.SearchRes;
import com.test.viewdemo.learnStructure.mvp.contact.SearchContact;

/**
 * Created by xuyabo on 2018/7/3.
 */

public class SearchPresenter implements SearchContact.SearchPresenter {
    SearchContact.SearchView mSearchView;
    SearchModel mSearchModel = new SearchModel();

    @Override
    public void search() {
        String key = mSearchView.getSearchKey();
        SearchRes searchRes = mSearchModel.search(key);
        mSearchView.showRes(searchRes);

    }

    @Override
    public void setSearchView(SearchContact.SearchView searchView) {
        mSearchView = searchView;
    }
}
