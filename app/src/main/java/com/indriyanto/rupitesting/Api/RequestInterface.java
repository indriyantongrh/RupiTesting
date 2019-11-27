package com.indriyanto.rupitesting.Api;

import android.renderscript.Sampler;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterface {


 @GET("/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22")
 Call<JSONResponse> getweather();


}
