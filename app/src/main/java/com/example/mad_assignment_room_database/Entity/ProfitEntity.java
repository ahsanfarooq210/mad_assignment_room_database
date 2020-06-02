package com.example.mad_assignment_room_database.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProfitEntity")
public class ProfitEntity
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "profit")
    private int profit;

    @ColumnInfo(name = "remainingAmount")
    private int remainingAmount;

    @ColumnInfo(name = "date")
    private String date;

    public ProfitEntity(int id, int profit, int remainingAmount, String date)
    {
        this.id = id;
        this.profit = profit;
        this.remainingAmount = remainingAmount;
        this.date = date;
    }

    @Ignore
    public ProfitEntity(int profit, int remainingAmount, String date)
    {
        this.profit = profit;
        this.remainingAmount = remainingAmount;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public int getProfit()
    {
        return profit;
    }

    public int getRemainingAmount()
    {
        return remainingAmount;
    }

    public String getDate()
    {
        return date;
    }
}
