package com.test.viewdemo.RxAndroidDemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.test.viewdemo.AlertDialogFragment;
import com.test.viewdemo.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;

/**
 * Observable.from的实例
 */
public class ShowAllJustAppActivity extends AppCompatActivity {
    private static final String TAG = "ShowAllAppActivity";
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private AppAdapter mAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_app);
        ButterKnife.bind(this);

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
        AppInfo appInfo1 = new AppInfo("应用1");
        AppInfo appInfo2 = new AppInfo("应用2");
        Observable.just(appInfo1, appInfo2)
                .repeat(3)//连续发射三次数据
                .subscribe(new Observer<AppInfo>() {
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
    public void showDialog(View view){
        AlertDialogFragment alertDialogFragment=new AlertDialogFragment();
        alertDialogFragment.show(getSupportFragmentManager().beginTransaction(),"DialogFragment");
    }


}
