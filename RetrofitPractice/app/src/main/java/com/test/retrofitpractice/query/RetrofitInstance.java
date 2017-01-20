package com.test.retrofitpractice.query;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/1/20.
 */
public class RetrofitInstance {
    private static Retrofit mQueryRetrofit;
    private static QueryService mQueryService;

    public static Retrofit getRetrofit() {
        if (mQueryRetrofit == null) {
            mQueryRetrofit = new Retrofit.Builder()
                    .baseUrl("http://wxtest.kuaijiankang.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mQueryRetrofit;
    }

    public static QueryService getQueryService() {
        if (mQueryService == null) {
            mQueryService = getRetrofit().create(QueryService.class);
        }
        return mQueryService;
    }
}
