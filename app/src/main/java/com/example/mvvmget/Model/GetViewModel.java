package com.example.mvvmget.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmget.Database.GetDatabase;
import com.example.mvvmget.Repository.CreatePostRepository;
import com.example.mvvmget.Repository.Repository;

import java.util.List;

public class GetViewModel extends AndroidViewModel {

    private CreatePostRepository createPostRepository;
    private Repository repository; //Call repository
    private GetDatabase database;

    public GetViewModel(@NonNull Application application) {
        super(application);
        database = GetDatabase.getInstance(application);
        repository = new Repository(application, database);
        createPostRepository = new CreatePostRepository(application,database);
    }

    //post get
    public LiveData<List<GetModel>> getAllPosts() {
        return repository.getPost();
    }

    // request from server
    public void requestGetData() {
        repository.requestGetDataFromServer();
    }

    public LiveData<List<GetModel>> getAllData() {
        return database.getDao().getAllPosts();
    }


    //View model for create post
    public void requestPost(GetModel getModel){
        createPostRepository.requestDataFromServer(getApplication(),getModel);
    }

    public LiveData<GetModel> getPost() {
        return createPostRepository.createPost();
    }
}


