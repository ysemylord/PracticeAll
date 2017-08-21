package com.test.retrofitpractice;

import com.test.retrofitpractice.bean.ExpressUser;
import com.test.retrofitpractice.query.QueryService;
import com.test.retrofitpractice.query.RetrofitInstance;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.print("info");
        OKHttpTest.test1();
    }

    @Test
    public void testRe() throws IOException {
        QueryService queryService=RetrofitInstance.getQueryService();
       Call<ExpressUser> call=queryService.getEXpressInfo("31");

        ExpressUser expressUser=  call.execute().body();
        System.out.print("info"+expressUser.getInfo().getName());

    }

    @Test
    public void testPrint(){
        System.out.println("testP");
    }

}