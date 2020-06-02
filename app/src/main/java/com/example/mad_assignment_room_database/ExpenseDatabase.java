package com.example.mad_assignment_room_database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.mad_assignment_room_database.Dao.ExpenseDao;
import com.example.mad_assignment_room_database.Dao.ProfitDao;

import com.example.mad_assignment_room_database.Entity.ExpenseEntity;
import com.example.mad_assignment_room_database.Entity.ProfitEntity;

@Database(entities = {ExpenseEntity.class, ProfitEntity.class},version = 1)

public abstract class ExpenseDatabase extends RoomDatabase
{
    private static final String DB_NAME="expense database";
    private static ExpenseDatabase instance;

    public static synchronized ExpenseDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), ExpenseDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract ExpenseDao getexpenseDao();
    public abstract ProfitDao getProfitDao();

}
