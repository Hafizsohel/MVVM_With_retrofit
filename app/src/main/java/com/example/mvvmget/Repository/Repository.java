package com.example.mvvmget.Repository;

import static com.example.mvvmget.Network.ApiCallingInstance.getRetrofit;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.util.LogTime;
import com.example.mvvmget.Database.GetDatabase;
import com.example.mvvmget.Model.GetModel;
import com.example.mvvmget.Network.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static final String TAG = "Repository";
    private final GetDatabase database;
    private Api api;

    private final MutableLiveData<List<GetModel>> mutableLiveData = new MutableLiveData<>();


    public Repository(Application application, GetDatabase database) {
        api = getRetrofit().create(Api.class); // call api
        this.database = database;
    }

    //Get post
    public LiveData<List<GetModel>> getPost() {
        return mutableLiveData;
    }

    // Request
    public void requestGetDataFromServer() {
        Call<List<GetModel>> call = api.getData();  //get data from api

        call.enqueue(new Callback<List<GetModel>>() {
            @Override
            public void onResponse(Call<List<GetModel>> call, Response<List<GetModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "body: "+response.body().size());
                    database.getDao().insert(response.body());
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GetModel>> call, Throwable t) {

            }
        });
    }
}


