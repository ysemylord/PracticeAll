package com.test.retrofitpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.test.retrofitpractice.bean.ExpressUser;
import com.test.retrofitpractice.query.QueryService;
import com.test.retrofitpractice.query.RetrofitInstance;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QueryService queryService = RetrofitInstance.getQueryService();
        Call<ExpressUser> call = queryService.getEXpressInfo("31");
        call.enqueue(new Callback<ExpressUser>() {
            @Override
            public void onResponse(Call<ExpressUser> call, Response<ExpressUser> response) {
                ExpressUser expressUser = response.body();
                Log.i("onResponse", expressUser.getInfo().getName());
            }

            @Override
            public void onFailure(Call<ExpressUser> call, Throwable t) {
                Log.i("onResponse", t.getLocalizedMessage());
            }
        });

        Call<ResponseBody> call2 = queryService.getEXpressInfo2("31");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                ResponseBody responseBody=response.body();
                try {
                    Log.i("onResponse",responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("onResponse", t.getLocalizedMessage());
            }
        });
    }
}
