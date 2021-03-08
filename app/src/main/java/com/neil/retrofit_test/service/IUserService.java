package com.neil.retrofit_test.service;

import com.neil.retrofit_test.bean.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserService {
    @GET("login")
    Call<Response> login(@Query("username") String username, @Query("pwd") String pwd);
}
