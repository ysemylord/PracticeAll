package com.test.viewdemo.learnStructure.mvp.contact;

import com.test.viewdemo.learnStructure.mvc.SearchRes;

/**
 * Created by xuyabo on 2018/7/3.
 */

public interface SearchContact {
    interface SearchView {

        String getSearchKey();

        void onSearchClick();

        void showRes(SearchRes searchRes);

        void setPresenter(SearchPresenter searchPresenter);
    }

    interface SearchPresenter {

        void search();

        void setSearchView(SearchView searchView);

    }

}
