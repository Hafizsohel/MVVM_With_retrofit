package com.example.mvvmget.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmget.DAO.GetDao;
import com.example.mvvmget.Model.GetModel;

@Database(entities = {GetModel.class}, version = 1, exportSchema = true)
public abstract class GetDatabase extends RoomDatabase {
    private static String DATABASE_NAME = "GetsDatabase";
    private static volatile GetDatabase INSTANCE;

    public static GetDatabase getInstance(Context context) {
        synchronized (GetDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = (GetDatabase) Room.databaseBuilder((Context)context.getApplicationContext(),
                                GetDatabase.class, (String)DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
            GetDatabase getDatabase = INSTANCE;
            return getDatabase;
        }
    }

    public abstract GetDao getDao();
}
