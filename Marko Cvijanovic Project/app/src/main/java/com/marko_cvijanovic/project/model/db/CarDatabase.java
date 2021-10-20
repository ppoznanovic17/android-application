package com.marko_cvijanovic.project.model.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.marko_cvijanovic.project.model.data.Car;

@Database(version = 1, entities = {Car.class})
public abstract class CarDatabase extends RoomDatabase {

    private static CarDatabase database;
    public abstract CarDAO getDAO();

    public static CarDatabase getDatabase(Context context){
        if(database == null)
            database = Room.databaseBuilder(
                    context,
                    CarDatabase.class,
                    "car.db"
            ).build();

        return database;
    }
}