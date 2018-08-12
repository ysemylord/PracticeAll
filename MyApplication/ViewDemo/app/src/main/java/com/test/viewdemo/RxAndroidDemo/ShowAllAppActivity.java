package com.test.viewdemo.RxAndroidDemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.test.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

public class ShowAllAppActivity extends AppCompatActivity {
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
                getSortList();
            }
        });
        getSortList();
    }

    private void getSortList() {
        getApps()
                .toSortedList()
                .subscribe(new Subscriber<List<AppInfo>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(ShowAllAppActivity.this, "完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ShowAllAppActivity.this, "出错", Toast.LENGTH_SHORT).show();
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<AppInfo> appInfos) {
                        mAppAdapter.addDatas(appInfos);
                        mRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 获取一个Obdervale,该Observable被订阅时发送手机上安装的App信息
     *
     * @return
     */
    private Observable<AppInfo> getApps() {
        return Observable.create(new Observable.OnSubscribe<AppInfo>() {
            @Override
            public void call(Subscriber<? super AppInfo> subscriber) {
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

                PackageManager pm = getPackageManager();
                List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                for (ResolveInfo resolveInfo : list) {
                    subscriber.onNext(new AppInfo(resolveInfo.activityInfo.packageName));
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }

}
