package com.example.mvvmget.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmget.Model.GetModel;

import java.util.List;

import retrofit2.http.GET;

@Dao
public interface GetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(GetModel getModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<GetModel> getlist);

    @Query("SELECT * from gets ORDER BY id DESC")
    LiveData<List<GetModel>> getAllPosts();

    @Query("SELECT * FROM gets")
    List<GetModel>getAllData();

    @Query("DELETE FROM gets")
    void deleteAll();
}
