package com.example.mvvmget.Repository;

import static com.example.mvvmget.Network.ApiCallingInstance.getRetrofit;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmget.Database.GetDatabase;
import com.example.mvvmget.Model.GetModel;
import com.example.mvvmget.Network.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostRepository {
    private final GetDatabase database;
    private Api api;


    private final MutableLiveData<GetModel> mLiveData = new MutableLiveData<>();

    public CreatePostRepository(Context context, GetDatabase database) {
        api = getRetrofit().create(Api.class);
        this.database = database;
    }

    //Create post
    public LiveData<GetModel> createPost(){
        return mLiveData;
    }

    public void requestDataFromServer(Context context, GetModel getModel) {
        Call<GetModel> call = api.createPost(getModel); // create post on api
        call.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Call<GetModel> call, Response<GetModel> response) {
                if (response.isSuccessful()) {
                    database.getDao().create(response.body()); //Store into the room database
                    mLiveData.postValue(response.body());
                    Toast.makeText(context, "post successful: " + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetModel> call, Throwable t) {
                Toast.makeText(context, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
