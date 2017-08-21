package com.test.retrofitpractice.query;

import com.test.retrofitpractice.intercept.BasicParamsInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            BasicParamsInterceptor basicParamsInterceptor=new BasicParamsInterceptor.Builder()
                    .addHeaderParam("token","23313")
                    .addQueryParam("client","android")
                    .addParam("body_commen","bodycoment")
                    .build();

            OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(basicParamsInterceptor)
                    .addInterceptor(interceptor)
                    .build();
            mQueryRetrofit = new Retrofit.Builder()
                    .baseUrl("http://wxtest.kuaijiankang.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
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
