package com.test.retrofitpractice;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.test.retrofitpractice.bean.ExpressUser;
import com.test.retrofitpractice.query.QueryService;
import com.test.retrofitpractice.query.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testRe(){
        QueryService queryService= RetrofitInstance.getQueryService();
        Call<ExpressUser> call=queryService.getEXpressInfo("31");
        call.enqueue(new Callback<ExpressUser>() {
            @Override
            public void onResponse(Call<ExpressUser> call, Response<ExpressUser> response) {
                ExpressUser expressUser= response.body();
                System.out.print("info"+expressUser.getInfo().getName());
            }

            @Override
            public void onFailure(Call<ExpressUser> call, Throwable t) {
                System.out.print("info"+t.getLocalizedMessage());
            }
        });
    }
}