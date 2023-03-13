package com.example.mvvmget.Network;

import androidx.room.Query;

import com.example.mvvmget.Model.GetModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("posts")
    Call<List<GetModel>> getData();

    @POST("posts")
    Call<GetModel> createPost(@Body GetModel getModel);
}