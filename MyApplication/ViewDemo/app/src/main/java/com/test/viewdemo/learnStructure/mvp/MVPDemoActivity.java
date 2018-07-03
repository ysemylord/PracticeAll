package com.test.viewdemo.learnStructure.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.viewdemo.R;

public class MVPDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpdemo);
        SearchView searchView = new SearchView();
        SearchPresenter searchPresenter = new SearchPresenter();
        searchPresenter.setSearchView(searchView);
        searchView.setPresenter(searchPresenter);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, searchView)
                .commit();
    }
}
