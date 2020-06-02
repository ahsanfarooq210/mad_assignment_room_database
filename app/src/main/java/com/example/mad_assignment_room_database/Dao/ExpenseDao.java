package com.example.mad_assignment_room_database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mad_assignment_room_database.Entity.ExpenseEntity;

import java.util.List;

@Dao
public interface ExpenseDao
{
    @Insert
    void insertExpense(ExpenseEntity expenseEntity);

    @Query("select * from ExpenseEntity where date=:date")
    List<ExpenseEntity> todaysReport(String date);

    @Query("select * from ExpenseEntity")
    List<ExpenseEntity> getAllExpense();


}
