package com.test.viewdemo.RxAndroidDemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;

/**
 * Observable.from的实例
 */
public class ShowAllFromAppActivity extends AppCompatActivity {
    private static final String TAG = "ShowAllAppActivity";
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private AppAdapter mAppAdapter;
    private List<AppInfo> mAppInfos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_app);
        ButterKnife.bind(this);

        mAppInfos.add(new AppInfo("应用1"));
        mAppInfos.add(new AppInfo("应用2"));

        mAppAdapter = new AppAdapter(new ArrayList<AppInfo>());
        mListview.setAdapter(mAppAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });
        loadList();
    }

    private void loadList() {
        Observable.from(mAppInfos).subscribe(new Observer<AppInfo>() {
            @Override
            public void onCompleted() {
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(AppInfo appInfo) {
                mAppAdapter.addData(appInfo);
            }
        });
    }




}
