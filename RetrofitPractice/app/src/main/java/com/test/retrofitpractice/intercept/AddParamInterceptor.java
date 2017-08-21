package com.test.retrofitpractice.intercept;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created  on 2017/2/14.
 *
 * @author xyb
 */
public class AddParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        HttpUrl httpUrl = oldRequest.url().newBuilder()
                .addQueryParameter("client", "android").build();

        RequestBody requestBody = oldRequest.body();
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
        }

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(httpUrl)
                .build();
        Response response = chain.proceed(newRequest);
        return response;
    }
}
