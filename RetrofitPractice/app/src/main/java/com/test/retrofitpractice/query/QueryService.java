package com.test.retrofitpractice.query;

import com.test.retrofitpractice.bean.ExpressUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 在java interface中
 *定义请求方法
 */
public interface QueryService {
    @GET("webservice/kuaiJianKang/ExpressInfo")
    Call<ExpressUser> getEXpressInfo(@Query("uid") String uid);

    @GET("webservice/kuaiJianKang/ExpressInfo")
    Call<ResponseBody> getEXpressInfo2(@Query("uid") String uid);
}
