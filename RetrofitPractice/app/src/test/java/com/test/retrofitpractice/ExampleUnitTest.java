package com.test.retrofitpractice;

import com.test.retrofitpractice.bean.ExpressUser;
import com.test.retrofitpractice.query.QueryService;
import com.test.retrofitpractice.query.RetrofitInstance;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRe(){
        QueryService queryService=RetrofitInstance.getQueryService();
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