package com.blitzar.testgrability;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Juano on 27/01/2017.
 */
public interface RequestInterface {
    @GET("us/rss/topfreeapplications/limit=20/json")
    Call<JSONResponse> getMyJSON();
}
