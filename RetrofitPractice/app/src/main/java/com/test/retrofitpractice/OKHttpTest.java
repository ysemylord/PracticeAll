package com.test.retrofitpractice;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created  on 2017/2/15.
 *
 * @author xyb
 */
public class OKHttpTest {
    private static final String TAG = "OKHttpTest";

    public static void test1() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();


        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("wxtest.kuaijiankang.com")//域名
                .addPathSegments("webservice/kuaiJianKang/ExpressScanOrder")//路径
                .addQueryParameter("uid", "31")//url参数
                .addQueryParameter("order_id", "32323")
                .build();

        RequestBody formBody=new FormBody.Builder().add("testFormBody","3233").build();//表单提交

        Request request = new Request.Builder()
                .addHeader("token","32323")
                .url(httpUrl)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + response.body().string());
            }
        });
    }

}
