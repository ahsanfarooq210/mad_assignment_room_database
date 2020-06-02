package com.example.mad_assignment_room_database.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "ExpenseEntity")
public class ExpenseEntity
{
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "expense")
    private int expense;

    @ColumnInfo(name = "expenseName")
    private String expenseName;

//    @ColumnInfo(name = "day")
//    private int day;
//
//    @ColumnInfo(name = "month")
//    private int month;
//
//    @ColumnInfo(name = "year")
//    private int year;

    @ColumnInfo(name = "date")
    private String date;

    public ExpenseEntity(int id, int expense, String expenseName, String date)
    {
        this.id = id;
        this.expense = expense;
        this.expenseName = expenseName;
        this.date = date;
    }

    @Ignore
    public ExpenseEntity(int expense, String expenseName, String date)
    {
        this.expense = expense;
        this.expenseName = expenseName;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public int getExpense()
    {
        return expense;
    }

    public String getExpenseName()
    {
        return expenseName;
    }

    public String getDate()
    {
        return date;
    }
}






